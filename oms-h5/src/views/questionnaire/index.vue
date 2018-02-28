<template>
<div class="app-container calendar-list-container">
  <!-- 筛选条件 -->
  <div class="filter-container">
      <el-select v-model="params.search_eq_status" style="width: 120px" class="filter-item" clearable placeholder="状态">
      <el-option v-for="item in options.status"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>

    <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">{{$t('message.search')}}</el-button>
    <el-button class="filter-item pull-right" type="success" icon="edit" @click="handleCreate">{{$t('message.add')}}</el-button>
  </div>

  <!-- 列表数据 -->
  <Pagination uri="/questionnaires" :request-params="params" ref="pagination">

      <!-- 问卷调查标题 -->
      <el-table-column align="center" :label="$t('questionnaire.title')">
        <template scope="scope">
          <span>{{scope.row.title}}</span>
        </template>
      </el-table-column>

      <!-- 0下线 1草稿 2上线 -->
      <el-table-column align="center" :label="$t('questionnaire.status')">
        <template scope="scope">
          <Status :status="scope.row.status"></Status>
        </template>
      </el-table-column>

      <!-- 创建时间 -->
      <el-table-column align="center" :label="$t('message.createTime')">
        <template scope="scope">
          <span>{{scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

    <el-table-column :label="$t('message.handle')" align="center" width="150">
      <template scope="scope">
        <el-button v-if="scope.row.status === 1" size="small" type="text" @click="handleUpdateStatus(scope.row, 0)">禁用</el-button>
        <el-button v-if="scope.row.status === 0" size="small" type="text" @click="handleUpdateStatus(scope.row, 1)">启用</el-button>
        <el-button size="small" type="text" @click="handleEdit(scope.row.id)">编辑</el-button>
        <el-button size="small" type="text" class="danger" @click="handleDelete(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </Pagination>
</div>
</template>

<script>
import { remove, updateStatus } from '@/api//questionnaire'
import waves from '@/directive/waves.js'// 水波纹指令
import Pagination from '@/components/Pagination'
import Status from '@/components/Status'
import statusOptions from '@/utils/constants.js'

export default {
  name: 'questionnaire_list',
  components: { Pagination, Status },
  directives: {
    waves
  },
  data() {
    return {
      params: {
        search_eq_status: undefined
      },

      options: {
        status: statusOptions
      }
    }
  },
  created() {
  },
  methods: {
    /**
     * 搜索问卷调查
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 编辑问卷调查
     */
    handleEdit(id) {
      this.$router.push({ path: '/questionnaire/edit', query: { id: id }})
    },

    /**
     * 创建问卷调查
     */
    handleCreate() {
      this.$router.push({ path: '/questionnaire/edit' })
    },

    /**
     * 更新问卷调查状态
     */
    handleUpdateStatus(row, status) {
      updateStatus(row.id, status).then(response => {
        this.handleSearch()
      })
    },

    /**
     * 删除问卷调查
     */
    handleDelete(row) {
      this.$confirm('确认删除该问卷调查？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => {})
    }
  }
}
</script>

