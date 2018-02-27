<template>
  <div class="app-container app-edit">
    <el-form class="small-space" :model="form" :rules="rules" ref="form" label-position="right" label-width="120px" style="width: 500px;">
      
      <el-form-item label="上级菜单" v-if="form.parentId">
        <el-input v-model="parentName" :readonly="true"></el-input>
      </el-form-item>
    
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入菜单名称"></el-input>
      </el-form-item>

      <el-form-item label="菜单地址" prop="url" v-if="form.parentId">
        <el-input v-model="form.url" placeholder="请输入菜单地址"></el-input>
      </el-form-item>

      <el-form-item label="图标" prop="icon" v-if="!form.parentId">
        <el-input v-model="form.icon" placeholder="请输入图标" :icon="'fa-' + form.icon"></el-input>
      </el-form-item>

      <el-form-item label="排序">
        <el-input-number v-model="form.sort" :min="0"></el-input-number>
      </el-form-item>
     
      <el-form-item label="状态">
        <el-radio class="radio" v-model="form.status" :label="1">可用</el-radio>
        <el-radio class="radio" v-model="form.status" :label="0">禁用</el-radio>
      </el-form-item>

      <el-form-item label="备注说明">
        <el-input v-model="form.remark" type="textarea" :maxlength="256" :rows="3" placeholder="请输入备注说明"></el-input>
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" :loading="onSubmit" @click="save">保存</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import { find, save } from '@/api/sys/menu'

export default {
  data() {
    return {
      form: {
        id: undefined,
        parentId: undefined,
        name: '',
        url: '',
        icon: '',
        sort: 0,
        status: 1,
        remark: ''
      },

      // 上级菜单名称
      parentName: undefined,
      onSubmit: false,

      // 表单验证规则
      rules: {
        name: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { max: 6, message: '菜单名称不能超过6个字符', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入菜单地址', trigger: 'blur' }
        ],
        icon: [
          { required: true, message: '请输入图标', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    if (this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.findMenu(this.form.id)
    }
    if (this.$route.query.parentId) {
      this.form.parentId = this.$route.query.parentId
      this.findParentMenu(this.form.parentId)
    }
  },
  methods: {
    /**
     * 查询系统菜单
     */
    findMenu(id) {
      find(id).then(response => {
        this.form.parentId = response.menu.parentId
        this.form.name = response.menu.name
        this.form.url = response.menu.url
        this.form.icon = response.menu.icon
        this.form.status = response.menu.status
        this.form.sort = response.menu.sort
        this.form.remark = response.menu.remark
      })
    },

    /**
     * 查询上级菜单
     */
    findParentMenu(parentId) {
      find(parentId).then(response => {
        this.parentName = response.menu.name
      })
    },

    /**
     * 编辑系统菜单
     */
    save() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }

        this.onSubmit = true
        save(this.form).then(response => {
          this.$router.go(-1)
        }).finally(() => {
          this.onSubmit = false
        })
      })
    },

    /**
     * 取消
     */
    handleCancel() {
      this.$router.go(-1)
    }
  }
}
</script>
