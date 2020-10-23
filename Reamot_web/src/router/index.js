import Vue from 'vue'
import Router from 'vue-router';
import message from '@/page/manage/message/message'
import user_info from '@/page/manage/Doctor/user_info'
import medicine_info from '@/page/manage/Medicine/medicine_info'
Vue.use(Router);
const routerConfig = [

    {
        path: '/',
        redirect: '/doctor',
    },
    {
        path: '/medicine',
        name: '患者信息',
        icon: 'el-icon-menu',
        component: medicine_info,
        meta: {
            requireAuth: true, // 判断是否需要登录
        },
    },
    {
        path: '/doctor',
        name: '患者信息',
        icon: 'el-icon-menu',
        component: user_info,
        meta: {
            requireAuth: true, // 判断是否需要登录
        },
    },

    {
        path: '/message',
        name: '消息管理',
        icon: 'el-icon-menu',
        component: message,
        meta: {
            requireAuth: true, // 判断是否需要登录
        },
    },

]
const router = new Router({
    mode: 'history',
    routes: routerConfig
})
export { routerConfig, router }
//export的语法，可以先定义变量，然后再export {要导出的变量}
//第二种方法： exports.变量名 = ......
