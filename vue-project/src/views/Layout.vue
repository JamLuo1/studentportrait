<script setup>
import {
    PictureRounded,
    OfficeBuilding,
    School,
    Histogram,
    SwitchFilled,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom,
    WarningFilled
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import {studentInfo} from '@/api/student.js'
import useStudentInfoStore from '@/stores/studentInfo.js'
import {useTokenStore} from '@/stores/token.js'
const tokenStore=useTokenStore();
const studentInfoStore =useStudentInfoStore();
const getStudentInfo = async()=>{
    let result = await studentInfo();
    studentInfoStore.setInfo(result.data);
    console.log(result)
}
getStudentInfo();
import {useRouter} from 'vue-router';
import { ElMessage,ElMessageBox } from 'element-plus';
const router =useRouter();
const handlecommand = (command)=>{
    if(command == 'logout'){
        ElMessageBox.confirm(
        '你确认要退出登录吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            tokenStore.removeToken()
            studentInfoStore.removeInfo()
            router.push('/login')
            ElMessage({
                type: 'success',
                message: '退出登录成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '用户取消了退出登录',
            })
        })
    }else{
        router.push('/student/'+command)
    }

}
</script>

<template>
    <el-container class="layout-container">
        <!-- 头部区域 -->
        <el-header>
            <div>学生画像和学业管理系统                        当前用户：<strong>{{studentInfoStore.info.name}}</strong></div>
            <el-dropdown placement="bottom-end" @command="handlecommand">
                <span class="el-dropdown__box">
                    <el-avatar :src="avatar" />
                    <el-icon>
                        <CaretBottom />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                        <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                        <el-dropdown-item command="resetPassword" :icon="EditPen">重置密码</el-dropdown-item>
                        <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </el-header>
        <!-- 下侧主区域 -->
        <el-container>
            <!-- 左侧菜单 -->
            <el-aside width="200px">
                <div class="el-aside__logo"></div>
                <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                    router>
                    <el-menu-item index="/student/portrait">
                        <el-icon>
                            <PictureRounded />
                        </el-icon>
                        <span>个人画像</span>
                    </el-menu-item>
                    <el-menu-item index="/student/warn">
                        <el-icon>
                            <WarningFilled />
                        </el-icon>
                        <span>学业预警</span>
                    </el-menu-item>
                    <el-menu-item index="/student/portrait_life">
                        <el-icon>
                            <SwitchFilled />
                        </el-icon>
                        <span>生活行为</span>
                    </el-menu-item>
                    <el-menu-item index="/student/warn_life">
                        <el-icon>
                            <WarningFilled />
                        </el-icon>
                        <span>生活预警</span>
                    </el-menu-item>
                    <el-sub-menu >
                        <template #title>
                            <el-icon>
                                <UserFilled />
                            </el-icon>
                            <span>个人中心</span>
                        </template>
                        <el-menu-item index="/student/info">
                            <el-icon>
                                <User />
                            </el-icon>
                            <span>基本资料</span>
                        </el-menu-item>
                        <el-menu-item index="/student/avatar">
                            <el-icon>
                                <Crop />
                            </el-icon>
                            <span>更换头像</span>
                        </el-menu-item>
                        <el-menu-item index="/student/resetPassword">
                            <el-icon>
                                <EditPen />
                            </el-icon>
                            <span>重置密码</span>
                        </el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </el-aside>
            <!-- 中间区域 -->
            <el-container>
                <el-main>
                    <!-- <div style="width: 1400px; height: 770px;border: 1px solid red;">
                        内容展示区
                    </div> -->
                    <router-view></router-view>
                </el-main>
                <!-- 底部区域 -->
                <el-footer>学生画像和学业管理系统 ©2024 Created by B20060627陈家洛</el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>