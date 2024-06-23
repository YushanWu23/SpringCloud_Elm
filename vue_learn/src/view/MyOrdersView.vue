<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const router = useRouter()
const store = useStore()
const { user } = storeToRefs(store)
const orders = ref([]);
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchOrders(){
    isUser();
    axiosInstance.get('/orders/getOrdersByUserId',{
        params:{
            userId : user.value.userId,
        }
    }).then(response => {
        orders.value = response.data;
        orders.value.forEach((order) => {
            order.showState = false;
        })
    }).catch(error => {
        router.push({
            path:"/falldown"
        })
    });
}
onBeforeMount(()=>{
    fetchOrders();
});
function showInfo(order){
    order.showState = !order.showState
}
function goPayment(orderId){
    router.push({
        path : "/payment",
        query :{
            orderId : orderId,
        }
    })
}

</script>

<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                我的订单
            </div>
        </div>
        <div class="body1">
            <div class="text1" >
                未支付订单信息:
            </div>
            <ul class="nonePayList">
                <li v-for="order in orders" v-show="!order.orderState">
                    <div class="content">
                        <div class="left" @click="showInfo(order)">
                            {{order.business.businessName}}
                            <i class="fa-solid fa-caret-down"></i>
                        </div>
                        <div class="right">
                            <div class="payPrice">
                                ¥{{order.orderTotal}}
                            </div>
                            <div class="payText" @click="goPayment(order.orderId)">
                                去支付
                            </div>
                        </div>
                    </div>
                    <ul class="orderList" v-show="order.showState">
                        <li v-for="item in order.orderDetailets">
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
                                ¥{{order.business.deliveryPrice}}
                            </div>
                        </div>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="body2">
            <div class="text1">
                已支付订单信息:
            </div>
            <ul class="alreadyPayList">
                <li v-for="order in orders" v-show="order.orderState">
                    <div class="content">
                        <div class="left" @click="showInfo(order)">
                            {{order.business.businessName}}
                            <i class="fa-solid fa-caret-down"></i>
                        </div>
                        <div class="right">
                                ¥{{order.orderTotal}}
                        </div>
                    </div>
                    <ul class="orderList" v-show="order.showState">
                        <li v-for="item in order.orderDetailets">
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
                                ¥{{order.business.deliveryPrice}}
                            </div>
                        </div>
                    </ul>
                </li>
            </ul>
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
.wrapper .body1{
    padding: 3vw;
    margin-top: 13vw;
}
.wrapper .body1 .text1{
     font-size:3.8vw;
     color: #888888;
}
.wrapper .body1 .nonePayList{
    margin-top: 2vw;
    width: 100%;
}
.wrapper .body1 .nonePayList li .content{
    display: flex;
    font-size: 4vw;
    justify-content: space-between;
    padding-top: 2vw;
    padding-bottom: 3vw;
}
.wrapper .body1 .nonePayList li .content .left{
    display: flex;
    color: #555555;
    align-items: center;
}
.wrapper .body1 .nonePayList li .content .right{
    display: flex;
    justify-content:flex-end;
    align-items: center;
}
.wrapper .body1 .nonePayList li .content .right .payPrice{
    color: #555555;
}
.wrapper .body1 .nonePayList li .content .right .payText{
    color: white;
    background-color: darkorange;
    margin-left: 2vw;
}
.wrapper .body1 .nonePayList li .orderList{
    margin-top: 1vw;
}
.wrapper .body1 .nonePayList li .orderList li{
    font-size: 3.5vw;
    padding-bottom: 2vw;
    display: flex;
    justify-content: space-between;
    width: 100%;
    color: #555555;
}
.wrapper .body1 .nonePayList li .orderList li .left{
    display: flex;
}
.wrapper .body1 .nonePayList li .orderList li .left .leftText{
    padding-right: 2vw;
}
.wrapper .body1 .nonePayList li .orderList li .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .body1 .nonePayList li .deliveryPrice{
    display: flex;
    justify-content: space-between;
    font-size: 3.5vw;
    color: #555555;
}
.wrapper .body1 .nonePayList li .deliveryPrice .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .body2{
    padding: 3vw;
    margin-top: 10vw;
    margin-bottom: 14vw;
}
.wrapper .body2 .text1{
    font-size:3.8vw;
    color: #888888;
}
.wrapper .body2 .alreadyPayList{
    margin-top: 2vw;
    width: 100%;
}
.wrapper .body2 .alreadyPayList li .content{
    display: flex;
    font-size: 4vw;
    justify-content: space-between;
    padding-top: 2vw;
    padding-bottom: 3vw;
}
.wrapper .body2 .alreadyPayList li .content .left{
    display: flex;
    color: #555555;
    align-items: center;
}
.wrapper .body2 .alreadyPayList li .content .right{
    display: flex;
    justify-content:flex-end;
    align-items: center;
    color: #555555;
}
.wrapper .body2 .alreadyPayList li .orderList{
    margin-top: 1vw;
}
.wrapper .body2 .alreadyPayList li .orderList li{
    font-size: 3.5vw;
    padding-bottom: 2vw;
    display: flex;
    justify-content: space-between;
    width: 100%;
    color: #555555;
}
.wrapper .body2 .alreadyPayList li .orderList li .left{
    display: flex;
}
.wrapper .body2 .alreadyPayList li .orderList li .left .leftText{
    padding-right: 2vw;
}
.wrapper .body2 .alreadyPayList li .orderList li .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .body2 .alreadyPayList li .deliveryPrice{
    display: flex;
    justify-content: space-between;
    font-size: 3.5vw;
    color: #555555;
}
.wrapper .body2 .alreadyPayList li .deliveryPrice .right{
    display: flex;
    justify-content: flex-end;
}
</style>