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
edu_data = pd.read_excel('e.xlsx')
def calculate_silhouette_scores(data, cluster_range, clustering_type="kmeans"):
    silhouette_scores = []
    for n_clusters in cluster_range:
        if clustering_type == "kmeans":
            model = KMeans(n_clusters=n_clusters, random_state=42)
        elif clustering_type == "hierarchical":
            model = AgglomerativeClustering(n_clusters=n_clusters)

        labels = model.fit_predict(data)
        score = silhouette_score(data, labels)
        silhouette_scores.append(score)

    return silhouette_scores



# One-hot encode the categorical data
scaler = StandardScaler()
home_scaled = scaler.fit_transform(edu_data)

# Hierarchical Clustering - create linkage matrix
linkage_matrix = linkage(home_scaled, method='ward')

# Plot the dendrogram
plt.figure(figsize=(10, 7))
dendrogram(linkage_matrix)
plt.title('层次聚集的树状图')
plt.xlabel('数据点')
plt.ylabel('欧氏距离')
plt.show()


# Calculate silhouette scores for hierarchical clustering
hierarchical_silhouette_scores = calculate_silhouette_scores(home_scaled, range(2, 7), "hierarchical")

# Calculate silhouette scores for K-means
kmeans_silhouette_scores = calculate_silhouette_scores(home_scaled, range(2, 7), "kmeans")

# Plotting the silhouette scores
fig, ax = plt.subplots(1, 2, figsize=(14, 5))
ax[0].plot(range(2, 7), hierarchical_silhouette_scores, marker='o')
ax[0].set_title('层次聚集的轮廓系数')
ax[0].set_xlabel('K')
ax[0].set_ylabel('轮廓系数')

ax[1].plot(range(2, 7), kmeans_silhouette_scores, marker='o')
ax[1].set_title('K-means 的轮廓系数')
ax[1].set_xlabel('K')
ax[1].set_ylabel('轮廓系数')

plt.show()
#od for K-means
sse = {}
for k in range(2, 7):
    kmeans = KMeans(n_clusters=k, random_state=42).fit(home_scaled)
    sse[k] = kmeans.inertia_  # Sum of squared distances of samples to their closest cluster center

# Plotting the Elbow Curve
plt.figure(figsize=(10, 6))
plt.plot(list(sse.keys()), list(sse.values()), marker='o')
plt.title(' K-Means肘部图')
plt.xlabel('K')
plt.ylabel('SSE')
plt.show()

#inal clustering with 3 clusters for both methods
kmeans_final = KMeans(n_clusters=4, random_state=42)
hierarchical_final = AgglomerativeClustering(n_clusters=4,linkage='ward')

# Fit the models
kmeans_labels = kmeans_final.fit_predict(home_scaled)
hierarchical_labels = hierarchical_final.fit_predict(home_scaled)

# Adding the cluster labels to the original data for interpretation
edu_data['KMeans_Cluster'] = kmeans_labels
edu_data['Hierarchical_Cluster'] = hierarchical_labels

edu_data.to_excel("edu聚类结果.xlsx")
# Function to display cluster characteristics
def display_cluster_characteristics(data, cluster_label):
    return data.groupby(cluster_label).mean()

# Display characteristics of each cluster for both methods
kmeans_clusters = display_cluster_characteristics(edu_data, 'KMeans_Cluster')
hierarchical_clusters = display_cluster_characteristics(edu_data, 'Hierarchical_Cluster')
print(kmeans_clusters)
print(hierarchical_clusters)