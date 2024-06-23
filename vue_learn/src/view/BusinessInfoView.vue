<script setup>
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const store = useStore()
const { user } = storeToRefs(store);
const route = useRoute()
const router = useRouter();
const foods = ref([]);
const carts = ref([]);
const business = ref('');

const businessId = route.query.businessId;
const totalPrice = computed(() =>{
    let total = 0;
    for (let item of foods.value) {
        total += item.foodPrice * item.quantity;
    }
    return total;
})
const totalQuantity = computed(() => {
    let quantity = 0;
    for (let item of foods.value) {
        quantity += item.quantity;
    }
    return quantity;
});
const totalSettle = computed(() => {
    return totalPrice.value + business.value.deliveryPrice;
});
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchBusiness(businessId) {
    isUser()
    axiosInstance.get('/business/getBusinessByBusinessId', {
        params: {
            businessId: businessId,
        }
    }).then(response => {
        const data = response.data;
        business.value = {
            businessId: data.businessId,
            businessName: data.businessName,
            businessAddress: data.businessAddress,
            businessExplain: data.businessExplain,
            businessImg: data.businessImg,
            starPrice: data.starPrice,
            deliveryPrice: data.deliveryPrice,
        };
        fetchFood(businessId);
    }).catch(error => {
        console.error(error);
    });
}
// 在页面加载时获取原先的信息
onMounted(() => {
    fetchBusiness(businessId);
});
function getCart(businessId){
    axiosInstance.post('/cart/getCartAll',{
        userId: user.value.userId,
        businessId : businessId,
    }, {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(response =>{
        console.log(response.data);
        carts.value .push(...response.data);
        carts.value.forEach(cart => {
            foods.value.forEach(food => {
                if (cart.food.foodId === food.foodId){
                    food.quantity = cart.quantity
                }
            })
        })
        foods.value.sort(function (food1,food2) {
            return food2.quantity - food1.quantity
        })
        console.log(carts)
    }).catch(error =>{
        console.error(error)
    });
}
function fetchFood(businessId){
    axiosInstance.get('/food/getFoodByBusinessId',{
        params:{
            businessId : businessId,
        }
    }).then(response =>{
        console.log(response.data)
        foods.value.push(...response.data)
        foods.value.forEach(food => {
            food.quantity = 0;
        })
        console.log(foods);
        getCart(businessId);
    }).catch(error =>{
        console.error(error)
    });

}

function minus(food){
    updateCart(food,-1)
    if (food.quantity ===1) {
        deleteCart(food);
    }
    food.quantity-=1;
}
function updateCart(food ,num){
    axiosInstance.post('/cart/updateCart',{
        userId: user.value.userId,
        businessId : businessId,
        foodId : food.foodId,
        quantity : food.quantity + num,
    },{
        headers : {
            "Content-Type" : "application/x-www-form-urlencoded"
        }
    }).then(response => {
        if (response.data === 1){
            food.quantity += num;
            foods.value.sort(function (food1,food2) {
                return food2.quantity - food1.quantity
            })
        }else {
            alert('购物车食品数量修改失败！');
        }
    }).catch(error => {
        console.error(error);
    });
}
function deleteCart(food) {
    axiosInstance.post('cart/deleteCart',{
        userId : user.value.userId,
        businessId : businessId,
        foodId : food.foodId,
    },{
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(response => {
        if (response.data === 1){
            food.quantity = 0;
            foods.value.sort(function (food1,food2) {
                return food2.quantity - food1.quantity
            })
        }else {
            alert('在购物车中移除食品失败！');
        }
    }).catch(error => {
        console.error(error);
    });
}
function add(food){
    if (food.quantity === 0) {
        saveCart(food);
    } else {
        updateCart(food, 1);
    }
}
function saveCart(food) {
    axiosInstance.post('cart/insertFoodIntoCart',{
        userId : user.value.userId,
        businessId : businessId,
        foodId : food.foodId,
    },{
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(response => {
        if (response.data === 1){
            food.quantity = 1;
            foods.value.sort(function (food1,food2) {
                return food2.quantity - food1.quantity
            })
        }else {
            alert('添加食品到购物车失败！');
        }
    }).catch(error => {
        console.error(error);
    });
}
function goOrder(){
    router.push({
        path : "/orders",
        query :{
            businessId : businessId
        }
    })
}
</script>

<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                商家信息
            </div>
        </div>
        <div class="photo">
            <img :src="business.businessImg">
        </div>
        <div class="name">
            {{business.businessName}}
        </div>
        <div class="explain">
            ¥{{ business.starPrice }}起送 ¥{{business.deliveryPrice}}配送
        </div>
        <div class="explain">
            {{business.businessExplain}}
        </div>
        <ul class="foodList">
            <li v-for="food in foods" :key="food.foodId">
                <img :src="food.foodImg">
                <div class="explain">
                    <div class="text1">
                        {{food.foodName}}
                    </div>
                    <div class="text2">
                        {{food.foodExplain}}
                    </div>
                    <div class="text2">
                        ¥{{food.foodPrice}}
                    </div>
                </div>
                <div class="order">
                    <div class="minusButton">
                        <i class="fa-solid fa-circle-minus" @click="minus(food)" v-show="food.quantity!==0"></i>
                    </div>
                    <div class="quantity">
                        <span v-show="food.quantity!==0">{{food.quantity}}</span>
                    </div>
                    <div class="plusButton">
                        <i class="fa fa-plus-circle" @click="add(food)"></i>
                    </div>
                </div>
            </li>
        </ul>
        <div class="cart">
            <div class="price">
                <div class="cartPhoto" :style="totalQuantity===0?'background-color:#505051;':'background-color:#3190E8;'">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <div class="cartPhotoQuantity" v-show="totalQuantity!==0">
                        {{totalQuantity}}
                    </div>
                </div>
                <div class="cartPrice">
                    <div class="totalPrice">
                        ¥{{totalPrice}}
                    </div>
                    <div class="deliveryPrice">
                        另需配送费{{business.deliveryPrice}}元
                    </div>
                </div>
            </div>
            <div class="payment">
                <div class="item" v-show="totalSettle<business.starPrice"
                     style="background-color: #535356;cursor: default;">
                    ¥{{business.starPrice}}起送
                </div>
                <div class="item" @click="goOrder" v-show="totalSettle>=business.starPrice">
                    去结算
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.wrapper{
    position: relative;
    width: 100%;
    height: 100%;
    overflow: auto;
}
.wrapper .head {
    width: 100%;
    height: 13vw;
    background-color: #0097ff;
    position: fixed;
    top:0;
    z-index: 1000; /* 确保头部在最上层 */
}
.wrapper .head .headText{
    font-size:4.5vw;
    font-weight: 500;
    color: white;
    height: 13vw;
    align-items: center;
    display: flex;
    justify-content: center;
}
.wrapper .photo{
    align-items: center;
    display: flex;
    justify-content: center;
    margin-top: 16vw;
}
.wrapper .photo img{
    width: 43vw;
    height: 30vw;
}
.wrapper .name{
    font-size:4.5vw;
    font-weight: 550;
    align-items: center;
    display: flex;
    justify-content: center;
    margin-top: 3vw;
}
.wrapper .explain{
    font-size:3.5vw;
    align-items: center;
    display: flex;
    justify-content: center;
}
.wrapper .foodList{
    margin-bottom: 16vw;
}
.wrapper .foodList li{
    width: 100%;
    margin: 2vw 2vw;
    display: flex;
    align-items: center;

}
.wrapper .foodList li img{
    display: flex;
    align-items: center;
    width:20vw;
    height:18vw;
    margin-right: 3vw;
}
.wrapper .foodList li .explain{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.wrapper .foodList li .explain .text1{
    font-size: 3.8vw;
    font-weight: 550;
    color: #555555;
    margin-top: 1vw;
}
.wrapper .foodList li .explain .text2{
    font-size:3.5vw;
    color: #888888;
    margin-top: 1vw;

}
.wrapper .foodList li .order{
    margin-right: 5vw;
    flex:1;
    display: flex;
    align-items: center;
    justify-content: flex-end;
}
.wrapper .foodList li .order .minusButton{
    font-size: 5vw;
    color: #999999;
    cursor: pointer;
}
.wrapper .foodList li .order .quantity {
    font-size: 3.5vw;
    color: #333333;
}
.wrapper .foodList li .order .plusButton {
    font-size: 5vw;
    color: #0097EF;
    cursor: pointer;
}
.wrapper .cart {
    width: 100%;
    height: 14vw;
    position: fixed;
    left: 0;
    bottom: 0;
    display: flex;
    z-index: 1000;
}
.wrapper .cart .price {
    flex: 2;
    background-color: #505051;
    display: flex;
}
.wrapper .cart .price .cartPhoto {
    width: 16vw;
    height: 16vw;
    box-sizing: border-box;
    border: solid 1.6vw #444;
    border-radius: 8vw;
    background-color: #3190E8;
    font-size: 7vw;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: -4vw;
    margin-left: 3vw;
    position: relative;
}
.wrapper .cart .price .cartPhoto .cartPhotoQuantity {
    width: 5vw;
    height: 5vw;
    border-radius: 2.5vw;
    background-color: red;
    color: #fff;
    font-size: 3.6vw;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: -1.5vw;
    top: -1.5vw;
}
.wrapper .cart .price .cartPrice .totalPrice {
    font-size: 4vw;
    color: #fff;
    margin-top: 1vw;
}
.wrapper .cart .price .cartPrice .deliveryPrice {
    font-size: 3.5vw;
    color: #AAA;
}
.wrapper .cart .payment {
    flex: 1;
}
.wrapper .cart .payment .item {
    width: 100%;
    height: 100%;
    background-color: #38CA73;
    color: #fff;
    font-size: 4.5vw;
    font-weight: 550;
    user-select: none;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>