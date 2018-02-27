<template>
<div class="app-container calendar-list-container">
  <!-- 筛选条件 -->
  <div class="filter-container">
    <el-select v-model="params.search_eq_l$status" style="width: 120px" class="filter-item" clearable placeholder="状态">
      <el-option label="审核通过" value="2"></el-option>
      <el-option label="待审核" value="1"></el-option>
      <el-option label="驳回" value="-1"></el-option>
    </el-select>
    <el-date-picker v-model="params.search_lt_l$startDate" class="filter-item" type="date" format="yyyy-MM-dd"  placeholder="选择开始日期">
    </el-date-picker>
    <el-date-picker v-model="params.search_gt_l$endDate" class="filter-item" type="date"  placeholder="选择结束日期">
    </el-date-picker>
    <el-input v-model="params.search_like_m$nickname" placeholder="赊账人" class="filter-item" style="width: 120px"></el-input>
    <el-input v-model="params.search_like_mm$nickname" placeholder="被赊账人" class="filter-item" style="width: 120px"></el-input>
    <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
    <!-- <el-button class="filter-item pull-right" type="success" icon="edit" @click="handleCreate">添加</el-button> -->
  </div>

  <!-- 列表数据 -->
  <Pagination uri="/loans" :request-params="params" ref="pagination">

    <el-table-column align="center" label="赊账人">
      <template scope="scope">
        <span v-text="scope.row.nickname1"></span>
      </template>
    </el-table-column>

    <el-table-column align="center" label="会员类型">
      <template scope="scope">
        <span v-text="getType(scope.row.type)"></span>
      </template>
    </el-table-column>

    <el-table-column align="center" label="被赊账人">
      <template scope="scope">
        <span v-text="scope.row.nickname2"></span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="赊账时间">
      <template scope="scope">
        <span>{{scope.row.startDate | parseTime('{y}-{m}-{d}')}}~{{scope.row.endDate | parseTime('{y}-{m}-{d}')}}</span>
      </template>
    </el-table-column>
    <!-- 贷款金额 -->
    <el-table-column align="center" label="赊账金额">
      <template scope="scope">
        <span>{{scope.row.amount}}元</span>
      </template>
    </el-table-column>

      <!-- 状态 -->
    <el-table-column align="center" label="状态">
      <template scope="scope">
        <span v-text="getStatus(scope.row.status)"></span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" width="150">
      <template scope="scope">
        <el-button v-if="scope.row.status === -1" size="small" type="text" @click="showReason(scope.row.reason)">驳回原因</el-button>        
      </template>
    </el-table-column>
  </Pagination>
</div>
</template>

<script>
import { remove, updateStatus } from '@/api//loan'
import waves from '@/directive/waves.js'// 水波纹指令
import Pagination from '@/components/Pagination'
import Status from '@/components/Status'
import statusOptions from '@/utils/constants.js'

export default {
  name: 'loan_list',
  components: { Pagination, Status },
  directives: {
    waves
  },
  data() {
    return {
      params: {
        search_eq_l$status: undefined,
        search_lt_l$startDate: undefined,
        search_gt_l$endDate: undefined,
        search_like_m$nickname: undefined,
        search_like_mm$nickname: undefined
      },
      options: {
        status: statusOptions
      }
    }
  },
  created() {
    if (this.$route.query.nickname) {
      this.params.search_like_m$nickname = this.$route.query.nickname
      this.$refs.pagination.pageRequest()
    }
    if (this.$route.query.mnickname) {
      this.params.search_like_mm$nickname = this.$route.query.mnickname
      this.$refs.pagination.pageRequest()
    }
  },
  methods: {
    getStatus(status) {
      if (status === -1) {
        return '驳回'
      } else if (status === 1) {
        return '待审核'
      } else if (status === 2) {
        return '审核通过'
      }
    },
    getType(type) {
      if (type === 1) {
        return '一级推广商'
      } else if (type === 2) {
        return '二级推广商'
      } else if (type === 3) {
        return '农户'
      } else if (type === 4) {
        return '区域管理员'
      }
    },
    showReason(reason) {
      this.$alert(reason, '驳回原因', {
        confirmButtonText: '确定'
      })
    },
    /**
     * 搜索农资贷
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 创建农资贷
     */
    handleCreate() {
      this.$router.push({ path: '/loan/edit' })
    },

    /**
     * 更新农资贷状态
     */
    handleUpdateStatus(row, status) {
      updateStatus(row.id, status).then(response => {
        this.handleSearch()
      })
    },

    /**
     * 删除农资贷
     */
    handleDelete(row) {
      this.$confirm('确认删除该农资贷？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => { })
    }
  }
}
</script>

