import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import './assets/main.css'
import BottomComponent from "@/components/basic/BottomComponent.vue";
import {createPinia, defineStore} from "pinia";

export const axiosInstance = axios.create({
    baseURL: 'http://localhost:40000',
})
export const useStore = defineStore('main',{
    state : () => {
        return {
            user : {
                userId : '',
                password : '',
                userName : '',
                userSex : 1,
            },
            defaultAddressInfo : {
                daId : -1,
                contactName : '',
                contactSex : null,
                contactTel : '',
                address:'',
            },
            isAuth : false,
        }
    }
})

createApp(App)
    .use(router)
    .use(createPinia())
    .component("BottomComponent",BottomComponent)
    .mount('#app')

//对 Axios 实例进行请求拦截
axiosInstance.interceptors.request.use(config=>{
    const token = sessionStorage.getItem("token")
    config.headers.setAuthorization(token)
    return config
});

//使用 Vue Router 的全局前置守卫，在每次路由切换之前会执行指定的逻辑
router.beforeEach(function(to,from,next){
    //登录、注册、首页、商家列表、商家信息不需要判断是否登录
    const token = sessionStorage.getItem("token")
    if((to.path==='/'||to.path==='/businessList'||to.path==='/regist'||to.path==='/businessInfo'||to.path==='/login')){
        next()
    }else {
        if(token==null){
            router.push('/login');
        }
        next();
    }
});
