<template>
  <div class="map-container">
    <!-- 地图展示区域 -->
    <div ref="chinaMap" class="map"></div>

    <!-- 显示选中地点的信息 -->
    <div v-if="selectedLocation" class="location-info">
      <h3>已选择地点: {{ selectedLocation.name }}</h3>
      <p>经纬度: {{ selectedLocation.latitude }}, {{ selectedLocation.longitude }}</p>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import chinaMapData from '@/assets/maps/china.json'; // 地图数据
import axios from 'axios'; // 使用 axios 调用后端 API
// 设置默认的基础 URL 为 8081 端口
axios.defaults.baseURL = 'http://localhost:8081';
export default {
  data() {
    return {
      chart: null,
      locations: [], // 存储创建的地点
      selectedLocation: null, // 当前选择的地点
    };
  },
  mounted() {
    this.initMap(); // 初始化地图
  },
  methods: {
    // 初始化地图
    initMap() {
      // 注册中国地图数据
      echarts.registerMap('china', chinaMapData);
      this.chart = echarts.init(this.$refs.chinaMap);

      // 地图配置
      const option = {
        series: [
          {
            type: 'map',
            map: 'china',
            roam: true, // 允许地图缩放和平移
            itemStyle: {
              normal: {
                areaColor: '#e0e0e0', // 默认地图颜色
                borderColor: '#111',
              },
              emphasis: {
                areaColor: '#a1c4fd', // 高亮时颜色
              },
            },
            label: {
              show: true,
              formatter: '{b}', // 显示地区名称
            },
          },
        ],
      };

      // 设置地图选项
      this.chart.setOption(option);

      // 地图点击事件监听器
      this.chart.on('click', (params) => {
        const { name, event } = params;
        const latitude = event.offsetY; // 获取点击点的纬度
        const longitude = event.offsetX; // 获取点击点的经度

        // 构建新地点对象
        const newLocation = {
          name: name || `未知地点`, // 可以改成实际需求的地点名称
          latitude: latitude,
          longitude: longitude,
          description: "用户创建的新地点", // 可修改为实际描述
        };

        // 调用创建地点的API
        this.createLocation(newLocation);
      });
    },

    // 调用后端API创建新地点
    createLocation(location) {
      axios.post('/api/locations', location)
        .then((response) => {
          // 将新创建的地点保存到本地的 locations 数组中
          this.locations.push(response.data);
          // 将新创建的地点设置为当前选择的地点
          this.selectedLocation = response.data;

          // 更新地图显示：将新创建的地点高亮显示
          this.chart.setOption({
            series: [
              {
                data: this.locations.map((loc) => ({
                  name: loc.name,
                  itemStyle: {
                    areaColor: loc === this.selectedLocation ? 'blue' : '#e0e0e0', // 已创建的地点变蓝色
                  },
                  value: [loc.longitude, loc.latitude], // 使用经纬度显示位置
                })),
              },
            ],
          });

          alert('地点创建成功！');
        })
        .catch((error) => {
          console.error('创建地点失败', error);
          alert('创建地点失败，请重试！');
        });
    },
  },
};
</script>

<style scoped>
.map-container {
  display: flex;
  justify-content: space-between;
  height: 100vh;
  width: 100vw;
}

.map {
  width: 80%;
  height: 100%;
}

.location-info {
  width: 20%;
  padding: 10px;
  border: 1px solid #ddd;
}

img {
  max-width: 100%;
  margin-top: 10px;
}
</style>
