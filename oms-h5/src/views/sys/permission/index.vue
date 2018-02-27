<template>
  <div class="app-container calendar-list-container">
    <!-- 筛选条件 -->
    <div class="filter-container">
      <el-input @keyup.enter.native="handleSearch" style="width: 160px;" class="filter-item" placeholder="权限点名称" v-model="params.search_like_name">
      </el-input>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleSearch">搜索</el-button>
      <el-button class="filter-item pull-right" v-waves type="success" icon="edit" @click="handleCreate">添加</el-button>
      <el-button class="filter-item pull-right" v-waves type="default" icon="arrow-left" @click="handleGoBack">返回</el-button>
    </div>

    <Pagination uri="/permissions" :page-size="20" :request-params="params" ref="pagination">
      <el-table-column align="center" label="名称" min-width="130">
        <template scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="权限点" min-width="130">
        <template scope="scope">
          <span>{{scope.row.key}}</span>
        </template>
      </el-table-column>
      
      <el-table-column align="center" label="创建时间" min-width="150">
        <template scope="scope">
          <span>{{scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="150">
        <template scope="scope">
          <el-button v-if="hasEditPermission()" size="small" type="text" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button v-if="hasDeletePermission()" size="small" type="text" class="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </Pagination>

    <!-- 编辑权限点 -->
    <el-dialog title="编辑权限点" size="tiny" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="editForm" label-position="left" label-width="70px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="权限点" prop="key">
          <el-input v-model="form.key" placeholder="请输入权限点"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" :loading="onSubmit"  @click="handleSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { remove, save, isKeyExists } from '@/api/sys/permission'
import waves from '@/directive/waves.js'// 水波纹指令
import Pagination from '@/components/Pagination'
import hasPermission from '@/utils/permission'

export default {
  name: 'permission_list',
  components: { Pagination },
  directives: {
    waves
  },
  data() {
    const validateKey = (rule, value, callback) => {
      // 检查权限点是否存在
      isKeyExists(value, this.form.id).then(response => {
        if (response.exists) {
          callback(new Error('权限点已存在'))
        } else {
          callback()
        }
      })
    }

    return {
      params: {
        search_like_name: undefined,
        search_eq_menuId: undefined
      },

      dialogFormVisible: false,

      form: {
        id: undefined,
        menuId: undefined,
        name: '',
        key: ''
      },

      onSubmit: false,

      rules: {
        name: [
          { required: true, message: '请输入权限点名称', trigger: 'blur' }
        ],
        key: [
          { required: true, message: '请输入权限点', trigger: 'blur' },
          { validator: validateKey, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.form.menuId = this.$route.query.menuId
    this.params.search_eq_menuId = this.form.menuId
  },

  methods: {
    /**
     * 搜索
     */
    handleSearch() {
      this.$refs.pagination.pageRequest()
    },

    /**
     * 编辑菜单
     */
    handleEdit(row) {
      this.form.id = row.id
      this.form.name = row.name
      this.form.key = row.key
      this.dialogFormVisible = true
    },

    /**
     * 创建菜单
     */
    handleCreate() {
      this.form.id = undefined
      this.form.name = ''
      this.form.key = ''
      this.dialogFormVisible = true
    },

    /**
     * 保存权限点
     */
    handleSave() {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          this.onSubmit = true
          save(this.form).then(response => {
            this.handleSearch()
          }).finally(() => {
            this.onSubmit = false
          })
        }
        return false
      })
    },

    /**
     * 取消编辑
     */
    handleCancel() {
      this.dialogFormVisible = false
    },

    /**
     * 返回
     */
    handleGoBack() {
      this.$router.go(-1)
    },

    /**
     * 删除管理员
     */
    handleDelete(row) {
      this.$confirm('确认删除该权限点？').then(_ => {
        remove(row.id).then(response => {
          this.$refs.pagination.removeItem(row)
        })
      }).catch(_ => {})
    },

    /**
     * 是否编辑权限
     */
    hasEditPermission() {
      return hasPermission('permission:edit')
    },

    /**
     * 是否删除权限
     */
    hasDeletePermission() {
      return hasPermission('permission:delete')
    }

  }
}
</script>

