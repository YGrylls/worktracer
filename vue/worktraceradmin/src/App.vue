<template>
  <div id="app">
    <Mainpage/>
  </div>
</template>

<script>
import Mainpage from './components/Mainpage.vue'

export default {
  name: 'App',
  components: {
    Mainpage
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-width: 1080px;
}
</style>
