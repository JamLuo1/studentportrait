<script setup>
import { ref } from 'vue'
import { getwarn_life} from '@/api/student.js'

let picurl=ref("")
const warn = async()=>{
    let result = await getwarn_life();
    picurl.value=result.data
}
warn()
</script>
<template>
    <el-container class="image-display-container">
      <el-header>
        <h1>生活行为预警情况</h1>
      </el-header>
      <el-main>
        <div class="image-container">
          <img :src="picurl" alt="警告信息" class="main-image"/>
        </div>
        <p class="image-description">高预警（红色）：生活得分低于60分的学生，这表明你在生活表现上存在严重问题，需要立即关注和干预。<br>
            中等预警（橙色）：生活得分在60到78分之间的学生，这表示你的生活表现有改进的空间，应提供额外的支持和指导。<br>
            低预警（绿色）：生活得分高于75分的学生，这表明你的生活表现是令人满意的，不需要特别的关注。</p>
      </el-main>
    </el-container>
  </template>

  <style scoped>
  .image-display-container {
    max-width: 600px;
    margin: auto;
    text-align: center;
  }
  .main-image {
    width: 100%;
    height: auto;
    border-radius: 8px;
  }
  .image-description {
    color: #666;
    font-size: 16px;
    padding: 20px;
  }
  .image-description {
  text-align: justify; /* 设置文本两端对齐 */
  text-justify: inter-word; /* 改善对齐的方式，使单词间隔均匀 */
}
  </style>