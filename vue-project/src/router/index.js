import { createRouter, createWebHistory } from 'vue-router'

//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import PortraitVue from '@/views/student/Portrait.vue'
import StudentAvatarVue from '@/views/student/StudentAvatar.vue'
import StudentInfoVue from '@/views/student/StudentInfo.vue'
import StudentResetPasswordVue from '@/views/student/StudentResetPassword.vue'
import StudentwarnVue from '@/views/student/Studentwarn.vue'
import StudentwarnVue_life from '@/views/student/Studentwarn_life.vue'
import PortraitVue_life from '@/views/student/Portrait_life.vue'
//定义路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/', component: LayoutVue,redirect:'/student/portrait', children:[
            { path: '/student/portrait', component: PortraitVue },
            { path: '/student/info', component: StudentInfoVue },
            { path: '/student/avatar', component: StudentAvatarVue },
            { path: '/student/resetPassword', component: StudentResetPasswordVue },
            { path: '/student/warn', component: StudentwarnVue }, 
            { path: '/student/warn_life', component: StudentwarnVue_life },
            { path: '/student/portrait_life', component: PortraitVue_life }
        ]
    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
