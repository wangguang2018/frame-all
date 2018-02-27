<template>
<div class="app-container calendar-list-container">
  <!-- 筛选条件 -->
  <div class="filter-container">
    <el-input @keyup.enter.native="handleSearch" style="width: 120px;" class="filter-item" placeholder="敏感词" v-model="params.search_like_name"></el-input>

    <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
    <el-button class="filter-item pull-right" type="success" icon="edit" @click="handleCreate">添加</el-button>
  </div>

  <!-- 列表数据 -->
  <Pagination uri="/sensitive/keywordss" :request-params="params" ref="pagination">

      <!-- 敏感词 -->
      <el-table-column align="center" label="敏感词">
        <template scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>

      <!-- 创建时间 -->
      <el-table-column align="center" label="创建时间">
        <template scope="scope">
          <span>{{scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

    <el-table-column label="操作" align="center" width="150">
      <template scope="scope">
        <el-button size="small" type="text" @click="handleEdit(scope.row.id)">编辑</el-button>
        <el-button size="small" type="text" class="danger" @click="handleDelete(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </Pagination>
</div>
</template>

<script>
import { remove } from '@/api//sensitive/keywords'
import waves from '@/directive/waves.js'// 水波纹指令
import Pagination from '@/components/Pagination'

export default {
  name: 'sensitiveKeywords_list',
  components: { Pagination },
  directives: {
    waves
  },
  data() {
    return {
      params: {
        search_like_name: undefined
      }
    }
  },
  created() {
  },
  methods: {
    /**
     * 搜索敏感词
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 编辑敏感词
     */
    handleEdit(id) {
      this.$router.push({ path: '/sensitive/keywords/edit', query: { id: id }})
    },

    /**
     * 创建敏感词
     */
    handleCreate() {
      this.$router.push({ path: '/sensitive/keywords/edit' })
    },

    /**
     * 删除敏感词
     */
    handleDelete(row) {
      this.$confirm('确认删除该敏感词？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => { })
    }
  }
}
</script>

