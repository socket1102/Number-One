<template>
  <div id="app">
    <div><HelloWorld msg="Welcome to Your Vue.js App"/></div>
    <div><Temperature msg="Welcome to Your Vue.js App"/></div>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Temperature from './components/Temperature.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    Temperature
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
  margin-top: 60px;
}
div{
  display: inline-block;
}
</style>
