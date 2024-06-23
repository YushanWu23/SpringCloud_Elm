<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                新增送货地址
            </div>
        </div>
        <div class="name">
            <div class="Text1">
                联系人：
            </div>
            <div class="Text2">
                <input type="text" v-model="deliveryAddress.contactName" placeholder="联系人姓名">
            </div>
        </div>
        <div class="sex">
            <div class="Text1">
                性别：
            </div>
            <div class="Text2">
                <input type="radio" v-model="deliveryAddress.contactSex" value="1">男
                <input type="radio" v-model="deliveryAddress.contactSex" value="0">女
            </div>
        </div>
        <div class="phone">
            <div class="Text1">
                电话：
            </div>
            <div class="Text2">
                <input type="text" v-model="deliveryAddress.contactTel" placeholder="电话">
            </div>
        </div>
        <div class="address">
            <div class="Text1">
                收货地址：
            </div>
            <div class="Text2">
                <input type="text" v-model="deliveryAddress.address" placeholder="收货地址">
            </div>
        </div>
        <div class="save">
            <button @click="saveDeliveryAddress">保存</button>
        </div>
        <BottomComponent/>
    </div>
</template>

<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const store = useStore()
const { user } = storeToRefs(store);
const router = useRouter()
const route = useRoute()
const deliveryAddress = ref({
    contactName : '',
    contactSex : 1,
    contactTel : '',
    address : '',
})
const businessId = route.query.businessId;
function isUser() {
    if (user.value.userId ===''){
        alert("先登陆")
        router.push({
            path:"/login"
        })
    }
}

function saveDeliveryAddress() {
    isUser()
    if (!deliveryAddress.value.contactName) {
        alert('联系人姓名不能为空！');
        return;
    }
    if (!deliveryAddress.value.contactTel) {
        alert('电话不能为空！');
        return;
    }
    if (!deliveryAddress.value.address) {
        alert('收货地址不能为空！');
        return;
    }
    axiosInstance.post('/deliveryAddress/saveDeliveryAddress',{
        contactName : deliveryAddress.value.contactName,
        contactSex: deliveryAddress.value.contactSex,
        contactTel :deliveryAddress.value.contactTel,
        address : deliveryAddress.value.address,
        userId : user.value.userId,
    },{
        headers : {
            "Content-Type" : "application/x-www-form-urlencoded"
        }
    }).then((response)=>{
        if (!response.data){
            alert('保存失败，请重试！');
            return;
        }
        alert('保存成功！');
        router.push({
            path:'/userAddress',
            query:{
                businessId : businessId,
            }
        })
    }).catch(error=>{
        console.error(error);
    });
}
</script>

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
    align-items: center;/* 竖直居中 */
    display: flex;
    justify-content: center; /* 水平居中 */
}
.wrapper .name{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
    margin-top: 15vw;
}
.wrapper .name .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .name .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 5vw;
}
.wrapper .phone{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
}
.wrapper .phone .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .phone .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 9vw;
}
.wrapper .address{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
}
.wrapper .address .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .address .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;
    margin-left: 1vw;
}
.wrapper .sex{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
}
.wrapper .sex .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;
    margin-left: 3vw;
}
.wrapper .sex .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;
    margin-left: 8vw;
}
.wrapper .sex .Text2 input[type="radio"] {
    margin-right: 2vw; /* 添加右边距为2vw */
    margin-left: 2vw;
}
.wrapper .save{
    background-color: #38CA73 ;
    margin-top: 2vw;
    margin-left: 3vw;
    margin-right: 3vw;
    height: 10vw;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .save button{
    font-size:4vw;
    height:10vw;
    color: white;
    font-weight: 550;
}
</style>
