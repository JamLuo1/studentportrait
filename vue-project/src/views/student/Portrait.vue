<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getportrait} from '@/api/student.js'
import { getradar} from '@/api/student.js'
let picurl1=ref("")
let picurl2=ref("")
const portrait = async()=>{
    let result = await getportrait();
    picurl1.value=result.data
}
portrait()
const radar = async()=>{
    let result = await getradar();
    console.log(result)
    picurl2.value=result.data
}
radar()
</script>
<template>
    <el-row class="portrait-container">
      <el-col :span="12">
        <div class="image-container">
          <img :src="picurl1" alt="个人画像" class="portrait-image"/>
          <p class="image-description">个人画像</p>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="image-container">
          <img :src="picurl2" alt="能力雷达图" class="portrait-image"/>
          <p class="image-description">学业能力雷达图</p>
        </div>
      </el-col>
    </el-row>
  </template>
<style scoped>
.portrait-container {
  margin-top: 20px;
}
.image-container {
  text-align: center;
}
.portrait-image {
  width: 1000px; /* 设置图片宽度 */
  height: 800px; /* 设置图片高度 */
  object-fit: cover; /* 保证图片填充整个容器，但不变形 */
  border-radius: 10px;
}
.image-description {
  text-align: center;
  color: #666;
  margin-top: 10px;
}
</style>