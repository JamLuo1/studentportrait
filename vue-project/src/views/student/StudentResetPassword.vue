<script setup>
import { ref } from 'vue'
import useStudentInfoStore from '@/stores/studentInfo.js'
import {ElMessage} from 'element-plus'

const studentInfoStore = useStudentInfoStore();
const studentInfo = ref({...studentInfoStore.info})


</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>重置密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="学号">
                        <el-input v-model="studentInfo.id" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="请输入原密码" prop="nickname">
                        <el-input ></el-input>
                    </el-form-item>
                    <el-form-item label="请输入新密码" prop="nickname">
                        <el-input ></el-input>
                    </el-form-item>
                    <el-form-item label="请再次输入新密码" prop="nickname">
                        <el-input ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatestudentInfo">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>