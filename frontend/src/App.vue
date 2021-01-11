<template>
  <Navbar />
  <router-view></router-view>
</template>

<script>
import Navbar from './baseComps/Navbar.vue'
export default {
  name: 'App',
  components: {
    Navbar
  }
}
</script>

<style scoped>
body{
  background: black;
}
</style>
