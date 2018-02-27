<template>
<div class="app-container calendar-list-container">
  <el-row>
    <div class="tip"></div>
    <span class="font">&nbsp&nbsp一级推广商数据:</span>
  </el-row>
  <br>
  <el-row style="background-color:#eef1f6">
      <br>
      <el-col span="6" align="center">
          <label>今日新增：</label>
          <span>{{firstMerchant.today}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本周新增：</label>
          <span>{{firstMerchant.week}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本月新增：</label>
          <span>{{firstMerchant.months}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>总数：</label>
          <span>{{firstMerchant.total}}</span>
      </el-col>
      <br>
      <br>
  </el-row>
  <br>
  <el-row>
    <div class="tip"></div>
    <span class="font">&nbsp&nbsp二级推广商数据:</span>
  </el-row>
  <br>
  <el-row style="background-color:#eef1f6">
      <br>
      <el-col span="6" align="center">
          <label>今日新增：</label>
          <span>{{secondMerchant.today}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本周新增：</label>
          <span>{{secondMerchant.week}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本月新增：</label>
          <span>{{secondMerchant.months}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>总数：</label>
          <span>{{secondMerchant.total}}</span>
      </el-col>
      <br>
      <br>
  </el-row>
  <br>
    <!-- 筛选条件 -->
  <div class="filter-container">
    <el-select class="filter-item" clearable filterable placeholder="请选择时间" v-model="params.date">
      <el-option label="全部" value="1"></el-option>
      <el-option label="上周" value="2"></el-option>
      <el-option label="本周" value="3"></el-option>
      <el-option label="上月" value="4"></el-option>
      <el-option label="本月" value="5"></el-option>
    </el-select>
    <!-- <el-date-picker v-model="params.startDate" class="filter-item" type="date" format="yyyy-MM-dd"  placeholder="选择开始日期">
    </el-date-picker>
    <el-date-picker v-model="params.endDate" class="filter-item" type="date" format="yyyy-MM-dd"  placeholder="选择结束日期">
    </el-date-picker> -->
    <el-date-picker v-model="params.time" type="daterange" placeholder="选择日期范围" class="filter-item">
    </el-date-picker>
    <el-select class="filter-item" clearable filterable placeholder="输入一级分类" v-model="params.firstCategoryId" @change="handleCate1Change">
      <el-option v-for="item in params.first" :key="item.id" :label="item.name" :value="item.id">
      </el-option>
    </el-select>
    <el-select class="filter-item" clearable filterable placeholder="输入二级分类" v-model="params.secondCategoryId">
      <el-option v-for="item in params.second" :key="item.id" :label="item.name" :value="item.id">
      </el-option>
    </el-select>
    <el-select class="filter-item" clearable filterable placeholder="请选择推广商名称" v-model="params.merchantId" >
       <el-option v-for="item in params.merchants" :key="item.id" :label="item.name" :value="item.id">
      </el-option>
    </el-select>
    <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
  </div>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
  </br>

  <el-table :data="goodsSaleDtoList" border style="width: 53%">
    <el-table-column prop="name" label="店铺名称" width="180">
    </el-table-column>
    <el-table-column prop="salePrice" label="交易额" width="180">
    </el-table-column>
    <el-table-column prop="saleNum" label="交易成功单量" width="180">
    </el-table-column>
    <el-table-column prop="saleCount" label="销售量" width="180">
    </el-table-column>
    <el-table-column prop="goodsCount" label="商品在线数" width="180">
    </el-table-column>  
  </el-table>
</div>
</template>

<script>
import { first, second } from '@/api//goods/category'
import { allMerchant, findCount, merchantSearch } from '@/api/merchant/merchant'
import waves from '@/directive/waves.js' // 水波纹指令
import statusOptions from '@/utils/constants.js'
import echarts from 'echarts'
import moment from 'moment'

export default {
  // 折线图
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    id: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '1200px'
    },
    height: {
      type: String,
      default: '400px'
    }
  },
  mounted() {
    this.initChart()
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },

  name: 'goods_list',
  directives: {
    waves
  },
  data() {
    return {
      params: {
        date: undefined,
        startDate: undefined,
        endDate: undefined,
        time: [],
        firstCategoryId: undefined,
        secondCategoryId: undefined,
        merchantId: undefined,
        second: [],
        first: [],
        merchants: []
      },
      firstMerchant: {
        today: 0,
        week: 0,
        months: 0,
        total: 0
      },
      secondMerchant: {
        today: 0,
        week: 0,
        months: 0,
        total: 0
      },
      checks: [],
      options: {
        status: statusOptions
      },
      chart: null,
      shop: {
        name: '',
        goods_count: 0

      },
      goodsSaleDtoList: []
    }
  },
  created() {
    /**
     * 一级分类
     */
    first().then(response => {
      this.params.first = response.first
    }).catch(_ => {

    })
    /**
     * 所有推广商家
     */
    allMerchant().then(response => {
      this.params.merchants = response.allMerchant
    }).catch(_ => {

    })
    /**
     * 一级代理商数据统计
     */
    findCount(1).then(response => {
      this.firstMerchant.today = response.count.today
      this.firstMerchant.week = response.count.week
      this.firstMerchant.months = response.count.months
      this.firstMerchant.total = response.count.total
    }).catch(_ => {

    })
    /**
     * 二级代理商数据统计
     */
    findCount(2).then(response => {
      this.secondMerchant.today = response.count.today
      this.secondMerchant.week = response.count.week
      this.secondMerchant.months = response.count.months
      this.secondMerchant.total = response.count.total
    }).catch(_ => {

    })
    merchantSearch(5, '', '', undefined, undefined, undefined).then(response => {
      this.goodsSaleDtoList = response.statistics.goodsSaleDtoList
      this.chart.setOption({
        xAxis: {
          data: response.statistics.date
        },
        series: [{
          data: response.statistics.saleCount
        },
        {
          data: response.statistics.saleCount
        },
        {
          data: response.statistics.salePrice
        },
        {
          data: response.statistics.salePrice
        }]
      })
    }).catch(_ => {

    })
  },
  methods: {
    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))
      this.chart.setOption({
        backgroundColor: '#FFFFFF',
        title: {
          text: '销售统计:',
          textStyle: {
            fontWeight: 'normal',
            fontSize: 16,
            color: '#F1F1F3'
          },
          left: '6%'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            lineStyle: {
              color: '#57617B'
            }
          }
        },
        legend: {
          icon: 'rect',
          itemWidth: 14,
          itemHeight: 5,
          itemGap: 13,
          data: ['销售量折线（个）', '销售量树状（个）', '销售额折线（元）', '销售额树状（元）'],
          right: '4%',
          textStyle: {
            fontSize: 12,
            color: '#F1F1F3'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#57617B'
            }
          },
          axisTick: {
            alignWithLabel: true
          },
          data: []
        }],
        yAxis: [{
          type: 'value',
          name: '',
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#57617B'
            }
          },
          axisLabel: {
            margin: 10,
            textStyle: {
              fontSize: 14
            }
          },
          splitLine: {
            lineStyle: {
              color: '#57617B'
            }
          }
        }],
        series: [{
          name: '销售量折线（个）',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(137, 189, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(137, 189, 27, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              borderColor: 'rgba(137,189,2,0.27)',
              borderWidth: 12
            }
          },
          data: []
        },
        {
          name: '销售量树状（个）',
          type: 'bar',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(137, 189, 27, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(137, 189, 27, 0)'
              }], false),
              // shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              // borderColor: 'rgba(137,189,2,0.27)',
              borderWidth: 12
            }
          },
          data: []
        },
        {
          name: '销售额折线（元）',
          type: 'line',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0, 136, 212, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(0, 136, 212, 0)'
              }], false),
              shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              borderColor: 'rgba(0,136,212,0.2)',
              borderWidth: 4

            }
          },
          data: []
        },
        {
          name: '销售额树状（元）',
          type: 'bar',
          smooth: true,
          symbol: 'circle',
          symbolSize: 5,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0, 136, 212, 0.3)'
              }, {
                offset: 0.8,
                color: 'rgba(0, 136, 212, 0)'
              }], false),
              // shadowColor: 'rgba(0, 0, 0, 0.1)',
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              // borderColor: 'rgba(0,136,212,0.2)',
              borderWidth: 4

            }
          },
          data: []
        }]
      })
    },
    // 一级分类事件
    handleCate1Change() {
      this.params.second = []
      this.params.secondCategoryId = undefined
      if (!this.params.firstCategoryId) {
        return
      }
      second(this.params.firstCategoryId).then(response => {
        this.params.second = response.second
      }).catch(_ => {
        this.params.second = []
      })
    },
    /**
     * 搜索商品
     */
    handleSearch() {
      if (this.params.time[0] != null) {
        this.params.startDate = moment(this.params.time[0]).format('YYYY-MM-DD 00:00:00')
        this.params.endDate = moment(this.params.time[1]).format('YYYY-MM-DD 00:00:00')
      } else {
        this.params.startDate = new Date()
        this.params.endDate = new Date()
      }
      merchantSearch(this.params.date, this.params.startDate, this.params.endDate,
      this.params.firstCategoryId, this.params.secondCategoryId, this.params.merchantId).then(response => {
        this.goodsSaleDtoList = response.statistics.goodsSaleDtoList
        this.chart.setOption({
          xAxis: {
            data: response.statistics.date
          },
          series: [{
            data: response.statistics.saleCount
          },
          {
            data: response.statistics.saleCount
          },
          {
            data: response.statistics.salePrice
          },
          {
            data: response.statistics.salePrice
          }]
        })
      }).catch(_ => {

      })
    }
  }
}
</script>
<<style>
.font{
  color:#00E3E3;
  font-size:20px;
}
.chart-container{
  position: relative;
  width: 100%;
  height: 80%;
}
</style>