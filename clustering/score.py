import pandas as pd
import seaborn as sns
from scipy.cluster.hierarchy import dendrogram,linkage
from sklearn.cluster import KMeans, AgglomerativeClustering
from sklearn.metrics import silhouette_score
import matplotlib.pyplot as plt
from kmodes.kmodes import KModes
import scipy.cluster.hierarchy as sch
from sklearn.preprocessing import StandardScaler

plt.rcParams["font.family"] = "FangSong" # 设置字体
file_path = 'life1.xlsx'
data = pd.read_excel(file_path)
# Calculate individual scores
data['空余时间得分'] = data['空余时间'] / data['空余时间'].max() * 100
data['外出频率得分'] = data['外出频率'] / data['外出频率'].max() * 100
data['饮酒量得分'] = (1 - data['饮酒量'] / data['饮酒量'].max()) * 100  # 饮酒量越少越好
data['健康程度得分'] = data['健康程度'] / data['健康程度'].max() * 100

# Weighted average of scores
data['总得分'] = (0.25 * data['空余时间得分'] +
                0.20 * data['外出频率得分'] +
                0.15 * data['饮酒量得分'] +
                0.40 * data['健康程度得分'])

# Define the warning level based on total score
data['预警等级'] = pd.cut(data['总得分'], bins=[0, 40, 60, 100], labels=['高', '中', '低'], right=False)



for index, row in data.iterrows():
    fig, ax = plt.subplots(figsize=(8, 2))
    score = row['总得分']
    student_id = row['id']
    color = 'green' if score > 75 else 'orange' if score > 60 else 'red'
    ax.barh([0], [score], color=color)
    ax.set_xlim([0, 100])
    ax.set_xticks([0, 20, 40, 60, 80, 100])
    ax.set_xticklabels(['0', '20', '40', '60', '80', '100'])
    ax.set_yticks([])
    ax.set_xlabel('生活行为得分')
    ax.annotate(f'得分：{score:.2f}', xy=(score, 0), xytext=(score + 5, 0),
                textcoords='offset points', va='center', ha='left')
    plt.tight_layout()
    plt.savefig(f'w_life/{student_id}.png')  # 保存到当前工作目录
    plt.close()  # 关闭图表以节省内存

print("所有学生的图表已保存完成。")