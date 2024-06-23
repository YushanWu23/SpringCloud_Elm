<template>
  <div class="wrapper">
      <div class ="head">
          <div class = "headText">
              用户登陆
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
      <div class="login">
          <button @click="login">登陆</button>
      </div>
      <div class = "regist">
          <button @click="regist">去注册</button>
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
function login() {
    if (!user.value.userId) {
        alert('手机号码不能为空！');
        return;
    }
    if (!user.value.password) {
        alert('密码不能为空！');
        return;
    }
    axiosInstance.post('/user/login',{
        userId : user.value.userId,
        pwd: user.value.password,
    },{
        headers : {
            "Content-Type" : "application/x-www-form-urlencoded"
        }
    }).then(response=>{
        if (!response.data){
            alert('用户名或密码不正确！');
            return
        }
        sessionStorage.setItem("token",response.data)
        alert('登录成功！');
        router.push({
            path : "/"
        })
    }).catch(error=>{
        console.error(error);
    });
}
function regist() {
    router.push({
        path : "/regist"
    })
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
    align-items: center;
    display: flex;
    justify-content: center;
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
    align-items: center;
    margin-left: 3vw;
}
.wrapper .phoneNum .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;
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
    align-items: center;
    margin-left: 3vw;
}
.wrapper .pwd .Text2{
    font-size:3.8vw;
    font-weight: 500;
    height:10vw;
    display: flex;
    align-items: center;
    margin-left: 9vw;
}
.wrapper .login{
    background-color: #38CA73 ;
    margin-top: 2vw;
    margin-left: 3vw;
    margin-right: 3vw;
    height: 10vw;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .login button{
    font-size:4vw;
    height:10vw;
    color: white;
    font-weight: 550;
}
.wrapper .regist{
    background-color: #EEEEEE;
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
    color: #888888;
    font-weight: 550;
}
</style>