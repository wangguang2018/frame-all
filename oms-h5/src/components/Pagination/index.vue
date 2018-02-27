<template>
  <div class="table-pagination-container">

    <!-- <el-button-group> -->
      <!-- <el-button type="primary">取消</el-button>
      <el-button type="primary">禁用</el-button>
      <el-button type="primary">删除</el-button> -->
    <!-- </el-button-group> -->

    <!-- 数据列表 -->
    <el-table :data="data" @selection-change="selectionChange" v-loading="isLoading" current-row-key="aaa" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 100%" :show-header="showHeader">
      <!-- <el-table-column type="selection" width="55">
      </el-table-column> -->
      <el-table-column type="selection" width="55" v-if="showCheck">
      </el-table-column>
      <el-table-column v-if="getShowIndex" align="center" label="序号" width="70" >
        <template scope="scope">
          <span>{{(pagination.page - 1) * pagination.pageSize + scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <slot></slot>
    </el-table>

    <!-- 分页信息 -->
    <div v-show="!isLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="pagination.page"
        :page-sizes="pageSizes" :page-size="pagination.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="pagination.total">
      </el-pagination>
    </div>
  </div>  
</template>

<script>
import http from '@/utils/http'
import merge from 'merge'
import { Message } from 'element-ui'

export default {
  props: {
    showHeader: {
      type: Boolean,
      default: true
    },
    showIndex: {
      type: Boolean,
      default: true
    },
    // 数据请求地址
    uri: {
      type: String,
      required: true
    },
    // 请求参数
    requestParams: {
      type: Object,
      required: false
    },
    // 每页记录数
    pageSize: {
      type: Number,
      required: false
    },
    // 页码
    page: {
      type: Number,
      required: false
    },
    selectionChange: {
      type: Function,
      default: function () { }
    },
    showCheck: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      isLoading: true,
      // 分页参数
      pagination: {
        page: this.page || 1,
        pageSize: this.pageSize || 10,
        total: null
      },
      pageSizes: [],
      data: undefined
    }
  },
  created() {
    this.pageRequest()
    this.pageSizes = [
      this.pagination.pageSize,
      this.pagination.pageSize * 5,
      this.pagination.pageSize * 10,
      this.pagination.pageSize * 30,
      this.pagination.pageSize * 50
    ]
  },
  computed: {
    getShowIndex() {
      return this.showIndex
    }
  },
  methods: {
    /**
     * 分页请求
     */
    pageRequest() {
      this.isLoading = true
      http.get(this.uri, merge({}, this.pagination, this.requestParams)).then(response => {
        this.pagination.total = response.page.total_records
        this.data = response.page.data
        this.isLoading = false
      }).catch(error => {
        Message({
          message: error.message,
          type: 'error'
        })
      }).finally(() => {
        this.isLoading = false
      })
    },

    /**
     * 切换页面
     */
    handleSizeChange(size) {
      this.pagination.pageSize = size
      this.pageRequest()
    },

    /**
     * 当前页码
     */
    handleCurrentChange(currentPage) {
      this.pagination.page = currentPage
      this.pageRequest()
    },

    /**
     * 删除一行
     */
    removeItem(row) {
      const index = this.data.indexOf(row)
      this.data.splice(index, 1)
    }
  }
}
</script>
