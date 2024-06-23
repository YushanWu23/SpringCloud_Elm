<template>
<div class="wrapper">
    <div class ="head">
        <div class = "headText">
            商家列表
        </div>
    </div>
    <ul class="businessList">
        <li v-for="business in businesses" @click="getBusinessInfo(business.businessId)">
            <div class="photo">
                <img :src='business.businessImg'>
                <div class="cartPhotoQuantity" v-show="business.quantity!==0">
                    {{business.quantity}}
                </div>
            </div>
            <div class="text">
                <div class="text1">
                    {{business.businessName}}
                </div>
                <div class="text2">
                    ¥{{business.deliveryPrice}}起送 | ¥{{business.starPrice}}配送
                </div>
                <div class="text3">
                    {{business.businessExplain}}
                </div>
            </div>
        </li>
    </ul>
    <BottomComponent/>
</div>
</template>

<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const router = useRouter();
const route = useRoute()
const store = useStore();
const carts = ref([]);
const { user } = storeToRefs(store);
const businesses = ref([]);
const orderTypeId = route.query.orderTypeId;
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchBusinesses(orderTypeId){
    isUser();
    axiosInstance.get('/business/getByOrderTypeId',{
        params:{
            orderTypeId : orderTypeId,
        }
    }).then(response => {
        console.log(response.data)
        businesses.value.push(...response.data)
        businesses.value.forEach(business => {
            business.quantity = 0;
            getCart(business.businessId);
        })
        console.log(businesses);
        }).catch(error =>{
        console.error(error)
    });
}
onMounted(() => {
    if (orderTypeId) {
        fetchBusinesses(orderTypeId);
    }
});
function getBusinessInfo(businessId){
    router.push({
        path : "/businessInfo",
        query :{
            businessId : businessId
        }
    })
}
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
        carts.value.push(...response.data) ;
        carts.value.forEach(cart => {
            businesses.value.forEach(business => {
                if (cart.business.businessId === business.businessId){
                    business.quantity += cart.quantity;
                }
            })
        })
        console.log(carts)
    }).catch(error =>{
        console.error(error)
    });
}
</script>

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
    z-index: 1000;
}
.wrapper .head .headText{
    font-size:4.5vw;
    font-weight: 500;
    color: white;
    height: 13vw;
    align-items: center;/* 竖直居中 */
    display: flex;
    justify-content: center; /* 水平居中 */
}
.wrapper .businessList{
    margin-top: 13vw;
    margin-bottom: 14vw;
}
.wrapper .businessList li{
    display: flex;
    width: 100%;
    padding: 3vw;
}
.wrapper .businessList li .photo{
    box-sizing: border-box;
    position: relative;
}
.wrapper .businessList li .photo img{
    height: 19vw;
    width: 25vw;
    padding-right: 3vw;
}
.wrapper .businessList li .photo .cartPhotoQuantity{
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
    right: 1.5vw;
    top: -1.5vw;
}
.wrapper .businessList li .text .text1{
    padding-top: 1vw;
    font-size: 4vw;
    font-weight: 550;
    color: #333333;
}
.wrapper .businessList li .text .text2{
    font-size: 3.5vw;
    color: #888888;
}
.wrapper .businessList li .text .text3{
    font-size: 3.5vw;
    color: #888888;
}
</style>
