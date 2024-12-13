import pandas as pd
from wordcloud import WordCloud
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt


# 加载数据
data_path = 'label.xlsx'  # 修改为实际的文件路径
data = pd.read_excel(data_path)

# 为每个标签定义关键词
keywords = {
    'sex':{
        1:['男生']*5000,
        0:['女生']*5000
    },
    'home_label': {
        0: ["家庭支持", "家庭温暖", "家庭贫困"]*100,
        1: ["家风优良", "生活水平高", "家庭关系和谐"]*100,
        2: ["家庭朴素", "家庭关系疏远", "家庭关系需改善"]*100
    },
    'edu_label': {
        0: ["教育背景平均", "教育背景基础"]*2000,
        1: ["教育背景良好", "教育背景扎实"]*2000,
        2: ["教育背景不足", "教育背景挑战"]*2000,
        3: ["教育背景优秀", "教育背景卓越"]*2000
    },
    'study_label': {
        0: ["中等生", "缺乏努力"]*30000,
        1: ["学霸", "勤奋", "优异"]*30000,
        2: ["学习需关注", "学习挑战多", "学业预警"]*30000
    },
    'life_label': {
        0: ["健康需关注", "缺少运动"]*30000,
        1: ["活跃", "健康良好", '外向']*30000,
        2: ["内向", "健康乐观","文静"]*30000
    }
}

# 加载形状图片
male_mask = np.array(Image.open("boy.webp"))
female_mask = np.array(Image.open("girl.webp"))


def generate_wordcloud(data_row):
    # 根据性别选择形状
    mask = male_mask if data_row['sex'] == 1 else female_mask

    # 合并所有标签的关键词
    words = []
    for label_type, label_dict in keywords.items():
        try:
            words.extend(label_dict[data_row[label_type]])
        except KeyError:
            print(f"KeyError: Check the value of '{label_type}' which is {data_row[label_type]} in row: {data_row}")
            continue

    word_string = ' '.join(words)

    # 生成词云图
    wordcloud = WordCloud(width=800, height=800, max_words=2000,repeat=True,background_color='white', mask=mask, contour_width=1,
                          contour_color='steelblue',font_path='STFANGSO.TTF',max_font_size = 70,min_font_size = 7).generate(word_string)

    # 显示词云图
    plt.figure(figsize=(8,8))
    plt.imshow(wordcloud, interpolation='bilinear')
    plt.axis('off')
    plt.savefig(f'src/{row['id']}.png')  # 指定保存路径和文件名

# 为数据集中的每位学生生成词云
for index, row in data.iterrows():
    generate_wordcloud(row)