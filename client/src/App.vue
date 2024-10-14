<template>
  <div id="app">
    <MapView />
  </div>
</template>

<script>
import MapView from './components/MapView.vue';

export default {
  components: {
    MapView,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  margin-top: 20px;
}
</style>
