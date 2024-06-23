<template>
    <div class="wrapper">
        <div class ="head">
            <div class = "headText">
                用户注册
            </div>
        </div>
        <div class="phoneNum">
            <div class="Text1">
                手机号码：
            </div>
            <div class="Text2">
                <input type="text" v-model="user.userId" placeholder="手机号码">
            </div>
        </div>
        <div class="pwd">
            <div class="Text1">
                密码：
            </div>
            <div class="Text2">
                <input type="password" v-model="user.password" placeholder="密码">
            </div>
        </div>
        <div class="rePwd">
            <div class="Text1">
                确认密码：
            </div>
            <div class="Text2">
                <input type="password" v-model="rePassword" placeholder="确认密码">
            </div>
        </div>
        <div class="name">
            <div class="Text1">
                用户姓名：
            </div>
            <div class="Text2">
                <input type="text" v-model="user.userName" placeholder="用户姓名">
            </div>
        </div>
        <div class="sex">
            <div class="Text1">
                性别：
            </div>
            <div class="Text2">
                <input type="radio" v-model="user.userSex" value="1">男
                <input type="radio" v-model="user.userSex" value="0">女
            </div>
        </div>
        <div class="regist">
            <button @click="regist">注册</button>
        </div>
        <BottomComponent/>
    </div>
</template>

<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRouter} from "vue-router";
import {storeToRefs} from "pinia";

const router = useRouter()
const store = useStore()
const { user } = storeToRefs(store)
const rePassword = ref('')
function regist() {
    if (!user.value.userId) {
        alert('手机号码不能为空！');
        return;
    }
    if (!user.value.password) {
        alert('密码不能为空！');
        return;
    }
    if (user.value.password !== rePassword.value) {
        alert("密码和确认密码不匹配！");
        return;
    }
    if (!user.value.userName) {
        alert('用户姓名不能为空！');
        return;
    }
    axiosInstance.post('/user/register',{
        userId : user.value.userId,
        password: user.value.password,
        userName :user.value.userName,
        userSex : user.value.userSex,
    },{
        headers : {
            "Content-Type" : "application/x-www-form-urlencoded"
        },
    }).then((response)=>{
        if (!response.data){
            alert('注册失败，请重试！');
            return;
        }
        alert('注册成功！');
        router.go(-1);
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
.wrapper .phoneNum{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
    margin-top: 15vw;
}
.wrapper .phoneNum .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .phoneNum .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 1vw;
}
.wrapper .pwd{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
}
.wrapper .pwd .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .pwd .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 9vw;
}
.wrapper .rePwd{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
}
.wrapper .rePwd .Text1{
    font-size:4vw;
    font-weight: 550;
    color: #666666;
    height:10vw;
    display: flex;
    align-items: center;/* 竖直居中 */
    margin-left: 3vw;
}
.wrapper .rePwd .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;
    margin-left: 1vw;
}
.wrapper .name{
    display: flex;
    justify-content: flex-start; /* 水平排列靠左对齐 */
    align-items: center;
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
.wrapper .regist{
    background-color: #38CA73 ;
    margin-top: 2vw;
    margin-left: 3vw;
    margin-right: 3vw;
    height: 10vw;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .regist button{
    font-size:4vw;
    height:10vw;
    color: white;
    font-weight: 550;
}
</style>
