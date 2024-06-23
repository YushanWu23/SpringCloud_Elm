<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {useRouter} from "vue-router";

const router = useRouter()
function goBack() {
    router.go(-1)
}
</script>

<template>
    <div class="wrapper">
        <div class="text">
            哎呀，服务器繁忙中...
        </div>
        <div class="button">
            <button @click="goBack">返回重试</button>
        </div>
        <BottomComponent/>
    </div>
</template>

<style scoped>
.wrapper{
    width: 100%;
    height: 100%;
}
.wrapper .text{
    margin-top: 60vw;
    color: #444444;
    font-size: 3.8vw;
    align-items: center;
    display: flex;
    justify-content: center;
}
.wrapper .button{
    margin-top: 2vw;
    font-size: 4vw;
    align-items: center;
    display: flex;
    justify-content: center;
}
</style>