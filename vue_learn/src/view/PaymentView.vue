<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";

const router = useRouter()
const store = useStore()
const route = useRoute();
const { user } = storeToRefs(store)
const orderId = route.query.orderId;
const orders = ref('');
const showState = ref(false)
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchOrders1(){
    isUser();
    axiosInstance.get('/orders/getOrdersByOrdersId',{
        params:{
            orderId : orderId,
        }
    }).then(response => {
        orders.value = response.data;
    }).catch(error => {
        console.error(error);
    });
}

function pay(){
    axiosInstance.get('/orders/updateOrderState',{
        params:{
            orderId : orderId,
        }
    }).then(response => {
        orders.value = response.data;
        alert("支付成功")
        router.push('/myOrders')
    }).catch(error => {
        console.error(error);
    });

}
function showInfo(){
    showState.value = !showState.value;
}

onBeforeMount(fetchOrders1)
</script>
<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                在线支付
            </div>
        </div>
        <div class="body">
            <div class="text1">
                订单信息:
            </div>
            <div class="text2">
                <div class="left" @click="showInfo">
                    {{orders.business.businessName}}
                    <i class="fa-solid fa-caret-down"></i>
                </div>
                <div class="right">
                    ¥{{orders.orderTotal}}
                </div>
            </div>
            <ul class="orderList" v-show="showState">
                <li v-for="item in orders.orderDetailets">
                    <div class="left">
                        <div class="leftText">
                            {{item.food.foodName}}
                        </div>
                        <div class="leftText">
                            x {{item.quantity}}
                        </div>
                    </div>
                    <div class="right">
                        ¥{{item.food.foodPrice}}
                    </div>
                </li>
                <div class="deliveryPrice">
                    <div class="left">
                        配送费
                    </div>
                    <div class="right">
                        ¥{{orders.business.deliveryPrice}}
                    </div>
                </div>
            </ul>

            <div class="payMethod">
                <img src="src/assets/img/alipay.png">
                <img src="src/assets/img/wechat.png">
            </div>
            <div class="pay">
                <button @click="pay">确认支付</button>
            </div>
        </div>
    </div>
  <BottomComponent/>
</template>
<style scoped>
.wrapper{
    width: 100%;
    height: 100%;
}
.wrapper .head {
    width: 100%;
    height: 13vw;
    background-color: #0097ff;
    position: fixed;
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
.wrapper .body{
    padding: 3vw;
    margin-bottom: 14vw;
    margin-top: 13vw;
}
.wrapper .body .text1{
    font-size:3.8vw;
    color: #888888;
}
.wrapper .body .text2{
    display: flex;
    font-size: 4vw;
    justify-content: space-between;
    padding-top: 2vw;
    padding-bottom: 3vw;
}
.wrapper .body .text2 .left{
    display: flex;
    color: #555555;
    align-items: center;
}
.wrapper .body .text2 .right{
    color: red;
    display: flex;
    justify-content:flex-end;
}
.wrapper .body .orderList{
    margin-top: 1vw;
}
.wrapper .body .orderList li{
    font-size: 3.5vw;
    padding-bottom: 2vw;
    display: flex;
    justify-content: space-between;
    width: 100%;
    color: #555555;
}
.wrapper .body .orderList li .left{
    display: flex;
}
.wrapper .body .orderList li .left .leftText{
    padding-right: 2vw;
}
.wrapper .body .orderList li .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .body .orderList .deliveryPrice{
    display: flex;
    justify-content: space-between;
    font-size: 3.5vw;
    color: #555555;
}
.wrapper .body .orderList .deliveryPrice .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .body .payMethod{
    width: 100%;
    padding-bottom: 2vw;
}
.wrapper .body .payMethod img{
    height: 16vw;
    padding-top: 3vw;
    padding-bottom: 3vw;
}
.wrapper .body .pay{
    background-color: #38CA73 ;
    height: 10vw;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .body .pay button{
    font-size:4vw;
    color: white;
    font-weight: 550;
}

</style>