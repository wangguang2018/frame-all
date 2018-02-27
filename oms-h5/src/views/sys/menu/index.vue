<template>
  <div class="app-container calendar-list-container">
    <!-- 筛选条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 160px;" class="filter-item" placeholder="菜单名称" v-model="params.search_like_name">
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
      <el-button class="filter-item pull-right" type="success" icon="edit" @click="handleCreate">添加</el-button>
      <el-button v-if="params.search_eq_parentId" class="filter-item pull-right" type="default" v-waves icon="arrow-left" @click="handleBack">返回</el-button>
    </div>

    <Pagination uri="/menus" :request-params="params" ref="pagination">

      <el-table-column align="center" label="名称" min-width="130">
        <template scope="scope">
          <el-button v-if="!params.search_eq_parentId" type="text" @click="viewChildMenus(scope.row.id)">{{scope.row.name}}</el-button>
          <el-button v-if="params.search_eq_parentId" type="text" @click="viewPermissions(scope.row.id)">{{scope.row.name}}</el-button>
          <!-- <span v-if="params.search_eq_parentId">{{scope.row.name}}</span> -->
        </template>
      </el-table-column>

      <el-table-column align="center" label="图标" width="100" v-if="!params.search_eq_parentId">
        <template scope="scope">
          <i :class="'el-icon-fa-' + scope.row.icon"></i>
        </template>
      </el-table-column>

      <el-table-column align="center" label="地址" min-width="200" v-if="params.search_eq_parentId">
        <template scope="scope">
          <span>{{scope.row.url}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="排序" width="82">
        <template scope="scope">
          <span>{{scope.row.sort}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" width="82">
        <template scope="scope">
          <Status :status="scope.row.status"></Status>
        </template>
      </el-table-column>

      <el-table-column align="center" label="创建时间" min-width="150">
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
import { remove, updateStatus } from '@/api/sys/menu'
import waves from '@/directive/waves.js'// 水波纹指令
import Gender from '@/components/Gender'
import Status from '@/components/Status'
import Pagination from '@/components/Pagination'
import hasPermission from '@/utils/permission'

export default {
  name: 'menu_list',
  components: { Gender, Status, Pagination },
  directives: {
    waves
  },
  data() {
    return {
      params: {
        search_like_name: undefined,
        search_eq_status: undefined,
        search_eq_parentId: 0
      },

      options: {
        status: [
          {
            value: 0,
            label: '禁用'
          },
          {
            value: 1,
            label: '可用'
          }
        ]
      }
    }
  },
  created() {
    if (this.$route.query.parentId) {
      this.params.search_eq_parentId = this.$route.query.parentId
    }
  },
  methods: {
    /**
     * 搜索
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 浏览子菜单
     */
    viewChildMenus(id) {
      this.$router.push({ path: '/sys/menu/index', query: { parentId: id }})
    },

    /**
     * 浏览权限点
     */
    viewPermissions(id) {
      this.$router.push({ path: '/sys/permission/index', query: { menuId: id }})
    },

    /**
     * 编辑菜单
     */
    handleEdit(id) {
      this.$router.push({ path: '/sys/menu/edit', query: {
        id: id,
        parentId: this.params.search_eq_parentId === 0 ? undefined : this.params.search_eq_parentId
      }})
    },

    /**
     * 创建菜单
     */
    handleCreate() {
      this.$router.push({ path: '/sys/menu/edit', query: {
        parentId: this.params.search_eq_parentId === 0 ? undefined : this.params.search_eq_parentId
      }})
    },

    /**
     * 处理返回
     */
    handleBack() {
      this.$router.replace({ path: '/sys/menu/index' })
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
     * 是否编辑权限
     */
    hasEditPermission() {
      return hasPermission('menu:edit')
    },

    /**
     * 是否删除权限
     */
    hasDeletePermission() {
      return hasPermission('menu:delete')
    },

    /**
     * 删除管理员
     */
    handleDelete(row) {
      this.$confirm('确认删除该系统菜单？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => {})
    }
  }
}
</script>

