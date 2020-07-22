<template>
  <div class="hello">
  <div id="myChart" style=" height: 250px;
          width: 450px;
          background-color: rgba(255, 230, 0, 0.2);
          margin: auto;"></div>
  
  </div>
</template>


<script>
export default {
  name: 'Temperature',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.drawLine();
  },
  methods: {
    
    drawLine () {
      var echarts = require('echarts');
      var myChart = echarts.init(document.getElementById('myChart'));
      myChart.setOption({
        title: {
        text: '体温详情'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['00:00', '01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00',
        '11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
    },
    yAxis: {
        type: 'category',
        axisLabel: {
            formatter: '{value}℃'
        },
        axisTick:{show:false},
        axisPointer: {
            snap: true
        }
    },
    visualMap: {
        show: false,
        dimension: 0,
        pieces: [{
            gt: 0,
            lte: 23,
            color: 'green'
        }]
    },
    series: [
        {
            name: '摄氏度',
            type: 'line',
            smooth: true,
            data: [0,0,0,0,0,0,0,0,0,0,0,36.5,36.4,36.4,36.6,36.7,36.8,36.5,36.6,36.7,36.6,36.6,36.8,36.5],
        }
    ]
      });
    }
  }
}
</script>
<style scoped>
</style>