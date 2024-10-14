<template>
  <div class="map-container">
    <!-- 地图展示区域 -->
    <div ref="chinaMap" class="map"></div>

    <!-- 地点信息 -->
    <div v-if="selectedLocation" class="location-info">
      <h3>已选择地点: {{ selectedLocation.name }}</h3>
      <p>经纬度: {{ selectedLocation.latitude }}, {{ selectedLocation.longitude }}</p>
      <div v-if="selectedLocation.photos.length">
        <h4>最后上传的图片:</h4>
        <img :src="selectedLocation.photos[selectedLocation.photos.length - 1]" alt="Last uploaded" />
      </div>
      <input type="file" @change="uploadPhoto" />
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import chinaMapData from '@/assets/maps/china.json';

export default {
  data() {
    return {
      chart: null,
      locations: [], // 存储创建的地点
      selectedLocation: null, // 当前选择的地点
    };
  },
  mounted() {
    this.initMap();
  },
  methods: {
    // 初始化地图
    initMap() {
      echarts.registerMap('china', chinaMapData);
      this.chart = echarts.init(this.$refs.chinaMap);

      // 地图配置
      const option = {
        series: [
          {
            type: 'map',
            map: 'china',
            roam: true, // 允许地图缩放和平移
          },
        ],
      };

      this.chart.setOption(option);

      // 地图点击事件
      this.chart.on('click', (params) => {
        const { name, event } = params;
        const latitude = event.offsetY;
        const longitude = event.offsetX;

        // 如果已有地点，选择上传图片
        const existingLocation = this.locations.find(
            (loc) => loc.name === name
        );
        if (existingLocation) {
          this.selectedLocation = existingLocation;
        } else {
          // 否则创建新地点
          const newLocation = {
            name: name,
            latitude: latitude,
            longitude: longitude,
            photos: [],
          };
          this.locations.push(newLocation);
          this.selectedLocation = newLocation;
        }
      });
    },
    // 上传图片
    uploadPhoto(event) {
      const file = event.target.files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        if (this.selectedLocation) {
          this.selectedLocation.photos.push(e.target.result);
        }
      };
      reader.readAsDataURL(file);
    },
  },
};
</script>

<style scoped>
.map-container {
  display: flex;
  justify-content: space-between;
}

.map {
  width: 70%;
  height: 500px;
}

.location-info {
  width: 25%;
  padding: 10px;
  border: 1px solid #ddd;
}

img {
  max-width: 100%;
  margin-top: 10px;
}
</style>
