<template>
  <div class="app-container app-edit">
    <el-form class="small-space" :model="form" :rules="rules" ref="form" label-position="right" label-width="120px" style="width: 800px;">
      
      <el-row>
        <el-col :span="12">
          <el-form-item label="手机号码" prop="mobile">
            <el-input v-model="form.mobile" placeholder="请输入手机号码"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="form.username" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="性别">
            <el-radio class="radio" v-model="form.gender" :label="1">男</el-radio>
            <el-radio class="radio" v-model="form.gender" :label="2">女</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态">
            <el-radio class="radio" v-model="form.status" :label="1">可用</el-radio>
            <el-radio class="radio" v-model="form.status" :label="0">禁用</el-radio>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="角色">
        <el-checkbox v-for="role in roles" v-model="role.checked" :label="role.roleName">{{role.roleName}}</el-checkbox>
      </el-form-item>
      
      
      <el-form-item>
        <el-button type="primary" :loading="onSubmit" @click="saveAdmin">保存</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { find, save, isMobileExists, isEmailExists } from '@/api/sys/admin'
import { findEnableRoles } from '@/api/sys/role'
import merge from 'merge'

export default {
  data() {
    /**
     * 手机号码验证
     */
    const validateMobile = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号码'))
      } else if (!/^1\d{10}$/.test(value)) {
        callback(new Error('请输入正确的手机号码'))
      }

      // 检查手机号码是否存在
      isMobileExists(value, this.form.id).then(response => {
        if (response.exists) {
          callback(new Error('手机号码已经存在'))
        } else {
          callback()
        }
      })
    }

    /**
     * 邮箱验证
     */
    const validateEmail = (rule, value, callback) => {
      isEmailExists(value, this.form.id).then(response => {
        if (response.exists) {
          callback(new Error('邮箱已存在'))
        } else {
          callback()
        }
      })
    }

    return {
      form: {
        id: undefined,
        mobile: '',
        username: '',
        password: '',
        email: '',
        gender: 1,
        status: 1
      },

      roles: [],

      onSubmit: false,

      // 表单验证规则
      rules: {
        mobile: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: validateMobile, trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { max: 15, message: '姓名长度不能超过15个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '您输入的邮箱格式不正确', trigger: 'blur' },
          { validator: validateEmail, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度不能少于6个字符', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    if (this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.findAdmin(this.form.id)
    }
    this.findEnableRoles(this.form.id)
  },
  methods: {
    /**
     * 查询管理员
     */
    findAdmin(id) {
      find(id).then(response => {
        this.form.mobile = response.admin.mobile
        this.form.username = response.admin.username
        this.form.email = response.admin.email
        this.form.gender = response.admin.gender
        this.form.status = response.admin.status
        this.form.password = 'NOT_MODIFIED'
      })
    },

    /**
     * 查询可用菜单
     */
    findEnableRoles(adminId) {
      findEnableRoles(adminId).then(response => {
        this.roles = response.roles
      })
    },

    /**
     * 获取选中的角色编号
     */
    getCheckedRoleIds() {
      const roleIds = []
      this.roles.forEach((item, index) => {
        item.checked && roleIds.push(item.roleId)
      })
      return {
        'roleIds[]': roleIds
      }
    },

    /**
     * 编辑管理员
     */
    saveAdmin() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }

        this.onSubmit = true
        save(merge({}, this.form, this.getCheckedRoleIds())).then(response => {
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
