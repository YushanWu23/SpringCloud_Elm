<template>
    <div class="wrapper">
        <div class="top">
            <div class ="head">
                <i class="fa-solid fa-location-dot"></i>
                <div class="headContent">
                    沈阳市规划大厦
                </div>
                <i class="fa-solid fa-caret-down"></i>
            </div>
            <div class="search">
                <div class="searchText">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <div class="searchContent">
                        搜索饿了么商家、商品名称
                    </div>

                </div>
            </div>
        </div>
        <div class="body">
            <div class="bodyPart1">
                <ul class="foodType">
                    <li @click="getBusiness(1)">
                        <img src="src/assets/img/dcfl01.png">
                        <div class="typeText">
                            美食
                        </div>
                    </li>
                    <li @click="getBusiness(2)">
                        <img src="src/assets/img/dcfl02.png">
                        <div class="typeText">
                            早餐
                        </div>
                    </li>
                    <li @click="getBusiness(3)">
                        <img src="src/assets/img/dcfl03.png">
                        <div class="typeText">
                            跑腿代购
                        </div>
                    </li>
                    <li @click="getBusiness(4)">
                        <img src="src/assets/img/dcfl04.png">
                        <div class="typeText">
                            汉堡披萨
                        </div>
                    </li>
                    <li @click="getBusiness(5)">
                        <img src="src/assets/img/dcfl05.png">
                        <div class="typeText">
                            甜品饮品
                        </div>
                    </li>
                </ul>
                <ul class="foodType">
                    <li @click="getBusiness(6)">
                        <img src="src/assets/img/dcfl06.png">
                        <div class="typeText">
                            速食简餐
                        </div>
                    </li>
                    <li @click="getBusiness(7)">
                        <img src="src/assets/img/dcfl07.png">
                        <div class="typeText">
                            地方小吃
                        </div>
                    </li>
                    <li @click="getBusiness(8)">
                        <img src="src/assets/img/dcfl08.png">
                        <div class="typeText">
                            米粉面馆
                        </div>
                    </li>
                    <li @click="getBusiness(9)">
                        <img src="src/assets/img/dcfl09.png">
                        <div class="typeText">
                            包子粥铺
                        </div>
                    </li>
                    <li @click="getBusiness(10)">
                        <img src="src/assets/img/dcfl10.png">
                        <div class="typeText">
                            炸鸡炸串
                        </div>
                    </li>
                </ul>
            </div>
            <div class="bodyPart2">
                <div class="text1">
                    品质套餐
                </div>
                <div class="text2">
                    搭配齐全吃得好
                </div>
                <div class="text3">
                    立即抢购 >
                </div>
            </div>
            <div class="bodyPart3">
                <img src="src/assets/img/super_member.png">
                <div class="text1">
                    超级会员
                </div>
                <div class="text2">
                    • 每月享超值权益
                </div>
                <div class="text3">
                    立即开通 >
                </div>
            </div>
            <div class="bodyPart4">
                <div class="text1">
                    ——
                    <div class="content">
                        推荐商家
                    </div>
                    ——
                </div>
                <div class="text2">
                    <div class="content">
                        综合排序
                        <i class="fa-solid fa-caret-down"></i>
                    </div>
                    <div class="content">
                        距离最近
                    </div>
                    <div class="content">
                        销量最高
                    </div>
                    <div class="content">
                        筛选
                        <i class="fa-solid fa-filter"></i>
                    </div>
                </div>
            </div>
            <ul class="businessList">
                <li v-for="business in businesses">
                    <img :src='business.businessImg'>
                    <div class="text">
                        <div class="text1">
                            <div class="left">
                                {{business.businessName}}
                            </div>
                            <div class="right">
                                •
                            </div>
                        </div>
                        <div class="text2">
                            <div class="left">
                                <div class="remarks">
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </div>

                                <div class="content">
                                    4.9
                                </div>
                                <div class="content">
                                    月售345单
                                </div>
                            </div>
                            <div class="right">
                                蜂鸟专送
                            </div>
                        </div>
                        <div class="text3">
                            <div class="left">
                                ¥{{business.starPrice}}起送 | ¥{{business.deliveryPrice}}配送
                            </div>
                            <div class="right">
                                3.22km | 30分钟
                            </div>
                        </div>
                        <div class="text4">
                            {{business.businessExplain}}
                        </div>
                    </div>

                </li>
            </ul>
        </div>
        <BottomComponent/>
    </div>
</template>

<script setup>
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {axiosInstance, useStore} from "@/main";
import {useRouter} from "vue-router";
import {storeToRefs} from "pinia";
const router = useRouter();
const store = useStore();
const { user } = storeToRefs(store);
const businesses = ref([]);

function fetchBusinesses(){
    axiosInstance.get('/business/getAll').then(response => {
        console.log(response.data)
            businesses.value.push(...response.data)
        console.log(businesses)
    }).catch(error =>{
        console.error(error)
    });
}
onMounted(fetchBusinesses);
function getBusiness(orderTypeId){
    router.push({
        path : "/businessList",
        query :{
            orderTypeId : orderTypeId
        }
    })
}
</script>

<style scoped>
.wrapper{
    width: 100%;
    height: 100%;
}
.wrapper .top{
    position: fixed;
    width: 100%;
}
.wrapper .top .head{
    width: 100%;
    height: 13vw;
    background-color: #0097ff;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    color: white;
    font-size: 4.5vw;
    padding-left: 3vw;
}
.wrapper .top .head .headContent{
    padding-left: 1vw;
    padding-right: 1vw;
}
.wrapper .top .search{
    background-color: #0097ff;
    width: 100%;
    padding: 2vw 5vw;
}
.wrapper .top .search .searchText{
    height: 10vw;
    color: #999999;
    font-size: 4vw;
    align-items: center;
    display: flex;
    justify-content: center;
    background-color: white;
}
.wrapper .top .search .searchContent{
    padding-left: 1vw;
}
.wrapper .body{
    margin-bottom: 14vw;
    margin-top: 24vw;
}
.wrapper .bodyPart1{
    width: 100%;
    padding: 5vw 2vw;
}
.wrapper .bodyPart1 .foodType{
    display: flex;
    align-items: center;
}
.wrapper .bodyPart1 .foodType li {
    height: 18vw;
    width: 20vw;
 }
.wrapper .bodyPart1 .foodType li img{
    align-items: center;
    display: flex;
    justify-content: center;
    height: 11vw;
    padding-left: 3vw;
}
.wrapper .bodyPart1 .foodType li .typeText{
    font-size: 3.5vw;
    align-items: center;
    display: flex;
    justify-content: center;
    color: #333333;
}
.wrapper .bodyPart2{
    width: 95%;
    margin-left: 3vw;
    margin-right: 3vw;
    height:30vw;
    padding-left: 6vw;
    background-image: url('/src/assets/img/index_banner.png');
    background-repeat: no-repeat;
    background-size: cover;
}
.wrapper .bodyPart2 .text1{
    font-size: 4.2vw;
    font-weight: 550;
    padding-top: 2vw;
}
.wrapper .bodyPart2 .text2{
    padding-top: 1vw;
     font-size: 3.5vw;
    color: #333333;
 }
.wrapper .bodyPart2 .text3{
    padding-top: 3vw;
    font-size: 3.5vw;
    color: darkgoldenrod;
}
.wrapper .bodyPart3{
    background-color: #ffeec2;
    width: 95%;
    margin:2vw 3vw;
    height:11vw;
    display: flex;
    align-items: center;
}
.wrapper .bodyPart3 img{
    padding-left: 4vw;
    padding-right: 2vw;
    height:7vw;
}
.wrapper .bodyPart3 .text1{
    font-size: 3.6vw;
    font-weight: 550;
    color: #644F1B;
}
.wrapper .bodyPart3 .text2{
    font-size: 3.5vw;
    color: #644F1B;
    padding-left: 2vw;
}
.wrapper .bodyPart3 .text3{
    font-size: 3.5vw;
    color: #644F1B;
    padding-left: 15vw;
}
.wrapper .bodyPart4{
    height: 20vw;
}
.wrapper .bodyPart4 .text1{
    font-size: 4vw;
    display: flex;
    justify-content:center;
    padding-top: 2vw;
}
.wrapper .bodyPart4 .text1 .content{
    padding-left: 3vw;
    padding-right: 3vw;
}
.wrapper .bodyPart4 .text2{
    display: flex;
    font-size: 3.7vw;
    color: #333333;
    padding-top: 3vw;
    padding-left: 2vw;
}
.wrapper .bodyPart4 .text2 .content{
   width: 25%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper .businessList{
    width: 95%;
    margin:3vw;
}
.wrapper .businessList li{
    display: flex;
    width: 100%;
    margin-bottom: 2vw;
}
.wrapper .businessList li img{
    width: 20%;
    height: 15%;
}
.wrapper .businessList .text{
    width: 80%;
    margin-left: 3vw;
}
.wrapper .businessList .text .text1{
    font-size: 4.2vw;
    font-weight: 550;
    display: flex;
    justify-content: space-between;
    color: #333333;
}
.wrapper .businessList .text .text1 .right{
    margin-right: 2vw;
    background-color: #333333;
    color: white;
    height: 4vw;
    align-items: center;
    display: flex;
}
.wrapper .businessList .text .text2{
    font-size: 3.5vw;
    display: flex;
}
.wrapper .businessList .text .text2 .left{
    display: flex;
}
.wrapper .businessList .text .text2 .left .remarks{
    display: flex;
    color: #FEC80E;
}
.wrapper .businessList .text .text2 .left .content{
    padding-left: 1vw;
    color: #333333;
}
.wrapper .businessList .text .text2 .right{
    background-color: #0097ff;
    color: white;
    margin-left: auto;
}
.wrapper .businessList .text .text3{
    font-size: 3.5vw;
    display: flex;
    color: #333333;
}
.wrapper .businessList .text .text3 .right{
    margin-left: auto;
}
.wrapper .businessList .text .text4{
    font-size: 3.5vw;
    color: #333333;
    border: solid 1px #DDD;
    width: 16vw;
}
</style>