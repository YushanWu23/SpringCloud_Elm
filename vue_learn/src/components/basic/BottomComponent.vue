<template>
  <div class="bottomBar">
    <div class="group">
      <i class="group-hover:cursor-pointer fa-solid fa-house-chimney"></i>
      <button @click="goIndex">首页</button>
    </div>
    <div class="group">
      <i class="group-hover:cursor-pointer fa-regular fa-compass"></i>
      <button>发现</button>
    </div>
    <div class="group">
      <i class="group-hover:cursor-pointer fa-regular fa-file-lines"></i>
      <button @click="goOrders">订单</button>
    </div>
    <div class="group">
        <i class="group-hover:cursor-pointer fa-regular fa-user"></i>
        <button>我的</button>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
const router = useRouter()

function goIndex(){
  router.push({
    path : "/",
  })
  console.log("首页")
}
function goOrders(){
    router.push({
        path : "/myOrders",
    })
    console.log("进入订单页面")
}
</script>

<style scoped>
.bottomBar{
    display: flex;
    width: 100%;
    height: 14vw;
    color: gray;
    bottom: 0;
    left: 0;
    justify-content: space-around;
    align-items: center;
    position: fixed;
    background-color: white;
    border-top: solid 1px #DDD;
}
.bottomBar .group{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 4vw;
}
</style>