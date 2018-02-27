<template>
<div class="app-container calendar-list-container">
  <el-row>
    <div class="tip"></div>
    <span class="font">&nbsp;&nbsp;会员数据</span>
  </el-row>
  <br>
  <el-row style="background-color:#eef1f6">
      <br>
      <el-col span="6" align="center">
          <label>今日新增农户：</label>
          <span>{{member.today}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本周新增农户：</label>
          <span>{{member.week}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>本月新增农户：</label>
          <span>{{member.months}}</span>
      </el-col>
      <el-col span="6" align="center">
          <label>农户总数：</label>
          <span>{{member.total}}</span>
      </el-col>
      <br>
      <br>
  </el-row>
  <br>
    <!-- 筛选条件 -->
  <div class="filter-container">
      <el-select class="filter-item" clearable filterable placeholder="请选择时间" v-model="params.date" >
      <el-option label="全部" value="1"></el-option>
      <el-option label="上周" value="2"></el-option>
      <el-option label="本周" value="3"></el-option>
      <el-option label="上月" value="4"></el-option>
      <el-option label="本月" value="5"></el-option>
    </el-select>
    <el-date-picker v-model="params.time" type="daterange" placeholder="选择日期范围" class="filter-item">
    </el-date-picker>
    <el-select class="filter-item" clearable filterable placeholder="请选择会员类型" v-model="params.type" >
      <el-option label="农户" value="1"></el-option>
      <el-option label="一级推广商" value="2"></el-option>
      <el-option label="二级推广商" value="3"></el-option>
    </el-select>
    <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
  </div>
  <br>
  <div :class="className" :id="id" :style="{height:height,width:width}"></div>
    </br>

  <el-table :data="memberList" border style="width: 42.4%">
    <el-table-column prop="name" label="会员昵称" width="180">
    </el-table-column>
    <el-table-column prop="typeName" label="会员类型" width="180">
    </el-table-column>
    <el-table-column prop="buyNum" label="成功下单量" width="180">
    </el-table-column>
    <el-table-column prop="buyPrice" label="消费金额" width="180">
    </el-table-column>
  </el-table>
</div>
</template>
<script>
import echarts from 'echarts'
import { findCount, memberSearch } from '@/api//member'
import moment from 'moment'

export default {
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
  data() {
    return {
      chart: null,
      params: {
        date: undefined,
        startDate: undefined,
        endDate: undefined,
        time: [],
        type: undefined
      },
      member: {
        today: 0,
        week: 0,
        months: 0,
        total: 0
      },
      memberList: []
    }
  },
  created() {
    /**
     * 农户数据统计
     */
    findCount().then(response => {
      this.member.today = response.count.today
      this.member.week = response.count.week
      this.member.months = response.count.months
      this.member.total = response.count.total
    }).catch(_ => {

    })
    memberSearch(undefined, '', '', undefined).then(response => {
      this.memberList = response.statistics.memberInfoDtos
      this.chart.setOption({
        xAxis: {
          data: response.statistics.date
        },
        series: [{
          data: response.statistics.countNum
        },
        {
          data: response.statistics.countNum
        }]
      })
    }).catch(_ => {

    })
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
  methods: {
    handleSearch() {
      if (this.params.time[0] != null) {
        this.params.startDate = moment(this.params.time[0]).format('YYYY-MM-DD 00:00:00')
        this.params.endDate = moment(this.params.time[1]).format('YYYY-MM-DD 00:00:00')
      } else {
        this.params.startDate = new Date()
        this.params.endDate = new Date()
      }
      memberSearch(this.params.date, this.params.startDate, this.params.endDate, this.params.type).then(response => {
        this.memberList = response.statistics.memberInfoDtos
        this.chart.setOption({
          xAxis: {
            data: response.statistics.date
          },
          series: [{
            data: response.statistics.countNum
          },
          {
            data: response.statistics.countNum
          }]
        })
      }).catch(_ => {

      })
    },
    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))
      this.chart.setOption({
        backgroundColor: '#FFFFFF',
        title: {
          text: '新增会员统计',
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
          data: ['会员折线图', '会员树状图'],
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
          name: '人数（人）',
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
          name: '会员折线图',
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
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              borderWidth: 12
            }
          },
          data: []
        },
        {
          name: '会员树状图',
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
              shadowBlur: 10
            }
          },
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              borderWidth: 12
            }
          },
          data: []
        }
        ]
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
      