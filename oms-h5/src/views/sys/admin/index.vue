<template>
  <div class="app-container calendar-list-container">
    <!-- 筛选条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 120px;" class="filter-item" placeholder="姓名" v-model="params.search_like_username">
      </el-input>
      <el-input @keyup.enter.native="handleSearch" style="width: 120px;" class="filter-item" placeholder="手机号码" v-model="params.search_like_mobile">
      </el-input>
      <el-input @keyup.enter.native="handleSearch" style="width: 200px;" class="filter-item" placeholder="邮箱" v-model="params.search_like_email">
      </el-input>
      <el-select v-model="params.search_eq_status" style="width: 120px" class="filter-item" clearable placeholder="状态">
        <el-option
          v-for="item in options.status"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
      <el-button v-if="hasEditPermission()" class="filter-item pull-right" type="success" icon="edit" @click="handleCreate">添加</el-button>
    </div>

    <Pagination uri="/admins" :request-params="params" ref="pagination">
      <el-table-column align="center" label="姓名">
        <template scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>

      <el-table-column label="手机号码" align="center" width="140">
        <template scope="scope">
          <span>{{scope.row.mobile}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="邮箱" width="220">
        <template scope="scope">
          <span>{{scope.row.email}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="性别" width="70">
        <template scope="scope">
          <Gender :gender="scope.row.gender"></Gender>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" width="82">
        <template scope="scope">
          <Status :status="scope.row.status"></Status>
        </template>
      </el-table-column>

      <el-table-column align="center" label="创建时间" width="150">
        <template scope="scope">
          <span>{{scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="150">
        <template scope="scope">
          <el-button v-if="hasEditPermission() && scope.row.status === 1" size="small" type="text" @click="handleUpdateStatus(scope.row, 0)">禁用</el-button>
          <el-button v-if="hasEditPermission() && scope.row.status === 0" size="small" type="text" @click="handleUpdateStatus(scope.row, 1)">启用</el-button>
          <el-button v-if="hasEditPermission()" size="small" type="text" @click="handleEdit(scope.row.id)">编辑</el-button>
          <el-button v-if="hasDeletePermission()" size="small" type="text" class="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </Pagination>
  </div>
</template>

<script>
import { remove, updateStatus } from '@/api/sys/admin'
import waves from '@/directive/waves.js'// 水波纹指令
import Gender from '@/components/Gender'
import Status from '@/components/Status'
import statusOptions from '@/utils/constants.js'
import Pagination from '@/components/Pagination'
import hasPermission from '@/utils/permission'

export default {
  name: 'admin_list',
  components: { Gender, Status, Pagination },
  directives: {
    waves
  },
  data() {
    return {
      params: {
        search_like_username: undefined,
        search_like_mobile: undefined,
        search_like_email: undefined,
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
     * 搜索
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 编辑管理员
     */
    handleEdit(id) {
      this.$router.push({ path: '/sys/admin/edit', query: { id: id }})
    },

    /**
     * 创建管理员
     */
    handleCreate() {
      this.$router.push({ path: '/sys/admin/edit' })
    },

    /**
     * 更新管理员状态
     */
    handleUpdateStatus(row, status) {
      updateStatus(row.id, status).then(response => {
        this.handleSearch()
      })
    },

    /**
     * 判断是否编辑权限
     */
    hasEditPermission() {
      return hasPermission('admin:edit')
    },

    /**
     * 判断是否删除权限
     */
    hasDeletePermission() {
      console.info('delete permission', hasPermission('admin:delete'))
      return hasPermission('admin:delete')
    },

    /**
     * 删除管理员
     */
    handleDelete(row) {
      this.$confirm('确认删除该管理员？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => {})
    }
  }
}
</script>

