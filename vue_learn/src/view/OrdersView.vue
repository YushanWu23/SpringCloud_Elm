<script setup>
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const router = useRouter()
const route = useRoute()
const store = useStore()
const { user,defaultAddressInfo } = storeToRefs(store)
const business = ref('');
const carts = ref([]);
const businessId = route.query.businessId;
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchBusiness(businessId){
    isUser();
    axiosInstance.get('/business/getBusinessByBusinessId',{
        params:{
            businessId : businessId,
        }
    }).then(response => {
        business.value = response.data;
        getCart(businessId);
    }).catch(error => {
        console.error(error);
    });
}
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
        carts.value = response.data;
    }).catch(error =>{
        console.error(error)
    });
}
function getContactSexText(contactSex){
    if(contactSex === 1 ){
        return '先生';
    }else if(contactSex === 0 ){
        return '女士';
    }else {
        return '';
    }
}
function goUserAddress(){
    router.push({
        path:'/userAddress',
        query:{
            businessId : businessId,
        }
    });
}
function goPayment(){
    if(defaultAddressInfo.value.daId === -1||!defaultAddressInfo.value){
        alert('请选择送货地址！');
        return;
    }
    axiosInstance.post('/orders/createOrders',{
        userId:user.value.userId,
        businessId:businessId,
        daId:defaultAddressInfo.value.daId,
        orderTotal:totalPrice.value
    },{
        headers : {
            "Content-Type" : "application/x-www-form-urlencoded"
        }}).then(response=>{
            if(response.data!==0){
                router.push(
                    {path:'/payment',
                        query: {
                        orderId:response.data
                    }
                });
            }
        }).catch(error=>{
            console.error(error);
        });
}
const totalPrice = computed(() => {
    let total = 0;
    for (let cart of carts.value) {
        total += cart.food.foodPrice * cart.quantity;
    }
    total += business.value.deliveryPrice
    return total ;
});
</script>
<template>
  <div class="wrapper">
      <div class="head">
          <div class = "headText">
              确认订单
          </div>
      </div>
      <div class="noneDeliveryInfo" @click="goUserAddress" v-show="defaultAddressInfo.daId===-1">
          请选择收货信息
      </div>
      <div class="deliveryInfo" @click="goUserAddress" v-show="defaultAddressInfo.daId!==-1">
          <div class="text1">
              订单配送至：
          </div>
          <div class="text2">
              <div class="left" >
                  {{defaultAddressInfo.address}}
              </div>
              <div class="right">
                  >
              </div>
          </div>
          <div class="text3">
              <div class="content">
                  {{defaultAddressInfo.contactName}}
                  {{getContactSexText(defaultAddressInfo.contactSex)}}
              </div>
              <div class="content">
                  {{defaultAddressInfo.contactTel}}
              </div>
          </div>
      </div>
      <div class="businessName">
          {{business.businessName}}
      </div>
      <ul class="orderList">
          <li v-for="cart in carts">
              <div class="left">
                  <img :src="cart.food.foodImg">
                  <div class="leftText">
                      {{cart.food.foodName}}
                  </div>
                  <div class="leftText">
                      x {{cart.quantity}}
                  </div>
              </div>
              <div class="right">
                  ¥{{cart.food.foodPrice}}
              </div>
          </li>
      </ul>
      <div class="deliveryPrice">
          配送费
          <div class="right">
              ¥{{business.deliveryPrice}}
          </div>
      </div>
      <div class="bottom">
          <div class="left">
              ¥{{totalPrice}}
          </div>
          <div class="right" @click="goPayment">
              去支付
          </div>
      </div>
  </div>
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
.wrapper .noneDeliveryInfo{
    width: 100%;
    height: 20vw;
    background-color: #0097EF;
    color: white;
    padding: 1vw 3vw;
    margin-top: 13vw;
    font-size: 5.5vw;
    font-weight: 550;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .deliveryInfo{
    width: 100%;
    height: 20vw;
    background-color: #0097EF;
    color: white;
    padding: 1vw 3vw;
    margin-top: 13vw;
}
.wrapper .deliveryInfo .text1{
    font-size: 3.5vw;
}
.wrapper .deliveryInfo .text2{
    display: flex;
    justify-content: space-between;
    font-size: 5vw;
}
.wrapper .deliveryInfo .text2 .left{
    font-weight: 550;
}
.wrapper .deliveryInfo .text2 .right{
    justify-content: flex-end;
}
.wrapper .deliveryInfo .text3{
    display: flex;
    font-size: 3.5vw;
}
.wrapper .deliveryInfo .text3 .content{
    padding-right: 2vw;
}
.wrapper .businessName{
    padding-left: 3vw;
    height: 10vw;
    display: flex;
    align-items: center;
    color: #555555;
    font-size: 4vw;
}
.wrapper .orderList{
    margin-top: 1vw;
}
.wrapper .orderList li{
    display: flex;
    width: 100%;
    justify-content: space-between;
    padding:3vw;
    height: 16vw;
}
.wrapper .orderList li .left{
    display: flex;
}
.wrapper .orderList li .left img{
    height: 10vw;
    display: flex;
    align-items: center;
}
.wrapper .orderList li .left .leftText{
    padding-left: 2vw;
    font-size: 3.8vw;
    color: #666666;
}
.wrapper .orderList li .right{
    display: flex;
    justify-content: flex-end;
    font-size: 3.8vw;
    color: #666666;
}
.wrapper .deliveryPrice{
    display: flex;
    padding: 3vw;
    justify-content: space-between;
    font-size: 3.5vw;
    color: #666666;
}
.wrapper .deliveryPrice .right{
    display: flex;
    justify-content: flex-end;
}
.wrapper .bottom{
    width: 100%;
    height: 14vw;
    position: fixed;
    left: 0;
    bottom: 0;
    display: flex;
}
.wrapper .bottom .left{
    flex:2;
    color: white;
    font-size: 4.5vw;
    background-color: #444444;
    justify-content: center;
    align-items: center;
    display: flex;
}
.wrapper .bottom .right{
    flex:1;
    background-color: #38CA73;
    color: white;
    font-size: 4.5vw;
    justify-content: center;
    align-items: center;
    display: flex;
}
</style>