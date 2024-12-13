import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.preprocessing import  StandardScaler
plt.rcParams["font.family"] = "FangSong" # 设置字体
file_path = 'study.xlsx'


data = pd.read_excel(file_path)
scaler = StandardScaler()
data[data.columns[1:]] = scaler.fit_transform(data[data.columns[1:]])
def create_radar_chart(row, features, save_path):
    labels = features
    stats = row[features].values

    # Create radar chart
    angles = np.linspace(0, 2 * np.pi, len(labels), endpoint=False).tolist()
    stats = np.concatenate((stats, [stats[0]]))
    angles += angles[:1]

    fig, ax = plt.subplots(figsize=(6, 6), subplot_kw=dict(polar=True))
    ax.fill(angles, stats, color='red', alpha=0.25)
    ax.plot(angles, stats, color='red', linewidth=2)  # Draw the outline of our data
    ax.set_yticklabels([])
    ax.set_xticks(angles[:-1])
    ax.set_xticklabels(labels)

    # Save the plot
    plt.savefig(save_path, bbox_inches='tight')
    plt.close()


feature_columns = data.columns[1:]  # Assuming the first column is student ID
# Generate a radar chart for each student again
for index, row in data.iterrows():
    student_id = row[0]  # Assuming the first column is student ID
    image_path = ( f'radar/{student_id}.png')
    create_radar_chart(row, feature_columns, image_path)