import Vue from 'vue'
import App from './App.vue'
//import c from '../node_modules/echarts'
import echarts from 'echarts'
import china from 'echarts/map/json/china.json'

Vue.prototype.$echarts = echarts
echarts.registerMap('china', china)
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
