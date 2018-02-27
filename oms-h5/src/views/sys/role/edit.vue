<template>
  <div class="app-container app-edit">
    <el-form class="small-space" :model="form" :rules="rules" ref="form" label-position="right" label-width="120px" style="width: 700px;">
      
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入角色名称"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-radio class="radio" v-model="form.status" :label="1">可用</el-radio>
        <el-radio class="radio" v-model="form.status" :label="0">禁用</el-radio>
      </el-form-item>
      <el-form-item label="菜单权限">
        <RoleTree :role-id="form.id" ref="roleTree"></RoleTree>
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" :loading="onSubmit" @click="handleEdit">保存</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { find, save } from '@/api/sys/role'
import RoleTree from '@/components/RoleTree'
import merge from 'merge'

export default {
  components: { RoleTree },
  data() {
    return {
      form: {
        id: undefined,
        name: undefined,
        status: 1
      },

      onSubmit: false,

      // 表单验证规则
      rules: {
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    if (this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.handleSearch(this.form.id)
    }
  },
  methods: {
    /**
     * 查询
     */
    handleSearch(id) {
      find(id).then(response => {
        this.form.name = response.role.name
        this.form.status = response.role.status
      })
    },

    /**
     * 编辑
     */
    handleEdit() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }

        this.onSubmit = true
        save(merge({}, this.form, this.$refs.roleTree.getCheckedIds())).then(response => {
          this.$router.go(-1)
        }).finally(() => {
          this.onSubmit = false
        })
      })
    },

    /**
     * 取消
     */
    cancel() {
      this.$router.go(-1)
    }
  }
}
</script>
