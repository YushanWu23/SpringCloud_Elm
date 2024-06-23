import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import('../view/HomeView.vue')
    },
     {
       path: '/login',
       name: 'login',
       component: () => import("../view/LoginView.vue")
     },
     {
       path: '/regist',
       name: 'regist',
       component: () => import("../view/RegistView.vue")
     },
     {
       path: '/businessList',
       name: 'businessList',
       component: () => import("../view/BusinessListView.vue")
     },
     {
       path: '/businessInfo',
       name: 'businessInfo',
       component: () => import("../view/BusinessInfoView.vue"),
     },
     {
       path: '/orders',
       name: 'orders',
       component: () => import("../view/OrdersView.vue")
     },
     {
       path: '/userAddress',
       name: 'userAddress',
       component: () => import("../view/UserAddressView.vue")
     },
     {
       path: '/addAddress',
       name: 'addAddress',
       component: () => import("../view/AddAddressView.vue")
     },
     {
       path: '/editAddress',
       name: 'editAddress',
       component: () => import("../view/EditAddressView.vue")
     },
     {
       path: '/payment',
       name: 'payment',
       component: () => import("../view/PaymentView.vue")
     },
      {
          path: '/myOrders',
          name: 'myOrders',
          component: () => import("../view/MyOrdersView.vue")
      },
      {
          path: '/falldown',
          name: 'falldown',
          component: () => import("../view/FallDownView.vue")
      }
  ]
})
export default router
