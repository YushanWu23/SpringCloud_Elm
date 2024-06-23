<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                地址管理
            </div>
        </div>
        <ul class="address">
            <li v-for="deliveryAddress in deliveryAddresses">
                <div class="left" @click="getOrder(deliveryAddress.daId)">
                    <div class="leftRow">
                        <div class="text1">
                            {{deliveryAddress.contactName}}
                        </div>
                        <div class="text1">
                            {{getContactSexText(deliveryAddress.contactSex)}}
                        </div>
                        <div class="text2">
                            {{deliveryAddress.contactTel}}
                        </div>
                    </div>
                    <div class="addressText">
                        {{deliveryAddress.address}}
                    </div>
                </div>
                <div class="rightRow">
                    <div class="right">
                        <i class="fa-solid fa-pen-to-square" @click="update(deliveryAddress.daId)"></i>
                    </div>
                    <div class="right1">
                        <i class="fa-solid fa-xmark" @click="remove(deliveryAddress.daId)"></i>
                    </div>
                </div>

            </li>
        </ul>
        <div class="save">
            <i class="fa-solid fa-circle-plus" @click="save">
                新增收货地址
            </i>
        </div>
        <BottomComponent/>
    </div>
</template>

<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const router = useRouter()
const route =useRoute();
const store = useStore()
const { user ,defaultAddressInfo} = storeToRefs(store)
const deliveryAddresses = ref([]);
const businessId = route.query.businessId;
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}
function fetchDeliveryAddress(){
    isUser();
    axiosInstance.get('/deliveryAddress/getDeliveryAddressByUserId',{
        params: {
            userId: user.value.userId
        }
    }).then(response => {
        console.log(response.data)
        deliveryAddresses.value.push(...response.data)
        console.log(deliveryAddresses)
    }).catch(error =>{
        console.error(error)
    });
}
onMounted(fetchDeliveryAddress);
function getOrder(daId){
    axiosInstance.get('/deliveryAddress/getDeliveryAddressByDaId',{
        params: {
            daId: daId,
        }
    }).then(response => {
        console.log(response.data)
        defaultAddressInfo.value = response.data;
        router.push({
            path:'/orders',
            query:{
                businessId :businessId,
            }
        });
    }).catch(error =>{
        console.error(error)
    });
}
function getContactSexText(contactSex){
    return contactSex === 1 ? '先生' : '女士';
}
function update(daId){
    router.push({
        path : "/editAddress",
        query :{
            daId : daId,
            businessId :businessId,
        }
    })
}
function remove(daId){
    axiosInstance.get('/deliveryAddress/removeDeliveryAddress',{
        params:{
            daId : daId,
            userId:user.value.userId,
        }
    }).then(() =>{
        deliveryAddresses.value = []
        fetchDeliveryAddress();
    }).catch(error =>{
        console.error(error)
    })
}
function save() {
    router.push({
        path : "/addAddress",
        query:{
            businessId :businessId,
        }
    })
}
</script>

<style scoped>
.wrapper{
    width: 100%;
    height: 100%;
    background-color: #EEEEEE;
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
.wrapper .address{
    margin-top: 13vw;
}
.wrapper .address li{
    padding: 3vw;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: white;
    margin-bottom: 0.5vw;
}
.wrapper .address li .left{
    width: 80%;
}
.wrapper .address li .left .leftRow {
    display: flex;
    align-items: center;
}
.wrapper .address li .left .text1{
    font-size:4.5vw;
    font-weight: 500;
    color: #999999;
}
.wrapper .address li .left .text2{
    margin-left: 2vw;
    font-size:4.5vw;
    font-weight: 500;
    color: #999999;
}
.wrapper .address li .left .addressText{
    font-size: 3.8vw;
    color: #555555;
}
.wrapper .address li .rightRow {
    display: flex;
    align-items: center;
    width:20%;
    justify-content: flex-end;
}
.wrapper .address li .rightRow .right{
    align-items: center;
    display: flex;
    color: #999999;
}
.wrapper .address li .rightRow .right1{
    align-items: center;
    display: flex;
    margin-left: 3vw;
    color: #999999;
}
.wrapper .save{
    padding-top: 5vw;
    height: 13vw;
    font-size:4.3vw;
    color: #0097ff;
    align-items: center;
    display: flex;
    justify-content: center;
    background-color: white;
}
</style>
