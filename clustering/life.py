import pandas as pd
from scipy.cluster.hierarchy import dendrogram,linkage
from sklearn.cluster import KMeans, AgglomerativeClustering
from sklearn.metrics import silhouette_score
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
import seaborn as sns
import scipy.cluster.hierarchy as sch

plt.rcParams["font.family"] = "FangSong" # 设置字体
# 加载数据
life_data = pd.read_excel('l.xlsx')
# Preprocessing
scaler = StandardScaler()
features = ['freetime', 'goout', 'health']
life_data_scaled = scaler.fit_transform(life_data[features])

# Hierarchical Clustering
# Plot the dendrogram
plt.figure(figsize=(10, 7))
dendrogram = sch.dendrogram(sch.linkage(life_data_scaled, method='ward'))
plt.title('层次聚集的树状图')
plt.xlabel('数据点')
plt.ylabel('欧氏距离')
plt.show()

# Find the optimal number of clusters using silhouette score for hierarchical clustering
range_clusters = range(2, 9)
silhouette_scores_hc = []

for k in range_clusters:
    hc = AgglomerativeClustering(n_clusters=k)
    cluster_labels = hc.fit_predict(life_data_scaled)
    silhouette_avg = silhouette_score(life_data_scaled, cluster_labels)
    silhouette_scores_hc.append(silhouette_avg)
    print(f"Silhouette Score for {k} clusters: {silhouette_avg}")

# Plotting silhouette scores
plt.figure(figsize=(8, 4))
plt.plot(range_clusters, silhouette_scores_hc, marker='o')
plt.title('层次聚集的轮廓系数')
plt.xlabel('K')
plt.ylabel('轮廓系数')
plt.show()

# K-means Clustering
# Elbow method
inertias = []
silhouette_scores_km = []

for k in range_clusters:
    km = KMeans(n_clusters=k, random_state=42)
    km.fit(life_data_scaled)
    inertias.append(km.inertia_)
    if k > 1:
        silhouette_avg = silhouette_score(life_data_scaled, km.labels_)
        silhouette_scores_km.append(silhouette_avg)
        print(f"Silhouette Score for {k} clusters: {silhouette_avg}")

# Plotting the elbow graph
plt.figure(figsize=(8, 4))
plt.plot(range_clusters, inertias, marker='o')
plt.title('K-Means肘部图')
plt.xlabel('K')
plt.ylabel('SSE')
plt.show()

# Plotting silhouette scores for K-means
plt.figure(figsize=(8, 4))
plt.plot(range(2, 9), silhouette_scores_km, marker='o')
plt.title('K-means 的轮廓系数')
plt.xlabel('K')
plt.ylabel('轮廓系数')
plt.show()

hc_final = AgglomerativeClustering(n_clusters=3,linkage='ward')
hc_labels = hc_final.fit_predict(life_data_scaled)

km_final = KMeans(n_clusters=3, random_state=42)
km_labels = km_final.fit_predict(life_data_scaled)

# 添加聚类标签到原始数据
life_data['Cluster_HC'] = hc_labels
life_data['Cluster_KM'] = km_labels
life_data.to_excel("life聚类结果.xlsx")
# 分析每个簇的特征
cluster_characteristics_hc = life_data.groupby('Cluster_HC').mean()
cluster_characteristics_km = life_data.groupby('Cluster_KM').mean()
pd.set_option('display.max_columns', None) # 设置显示最大列，None为显示所有列
print(cluster_characteristics_km)
print(cluster_characteristics_hc)
def plot_cluster_features_modified(cluster_data, cluster_method):
    fig, axs = plt.subplots(1, 3, figsize=(20, 10))  # Adjusted for 7 subplots

    # Plot each feature
    features = ['freetime', 'goout',  'health']
    labels = ['平均空闲时间', '平均外出次数', '平均健康状况']
    for i, feature in enumerate(features):
        # 计算子图的行列索引
        sns.barplot(x=cluster_data.index, y=feature, data=cluster_data, ax=axs[i])
        axs[i].set_xlabel('簇')
        axs[i].set_ylabel(labels[i])

    plt.tight_layout()
    plt.suptitle(f'{cluster_method}聚类的特征平均值', y=1.05)
    plt.show()

selected_features = ['freetime', 'goout','health']
h_cluster_means = cluster_characteristics_hc[selected_features]
k_means_means = cluster_characteristics_km[selected_features]
# Plotting K-means cluster feature averages
plot_cluster_features_modified(cluster_characteristics_km, "K-means")

# Plotting Hierarchical cluster feature averages
plot_cluster_features_modified(cluster_characteristics_hc, "层次")