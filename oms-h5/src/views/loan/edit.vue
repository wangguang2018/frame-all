<template>
  <div class="app-container app-edit">
    <el-form class="small-space" :model="form" :rules="rules" ref="form" label-position="right" label-width="120px" style="width: 700px;">

      <!-- 状态 -->
      <el-form-item label="状态">
        <el-radio class="radio" v-model="form.status" :label="1">可用</el-radio>
        <el-radio class="radio" v-model="form.status" :label="0">禁用</el-radio>
      </el-form-item>

      <!-- 订单编号 -->
      <el-form-item label="订单编号" prop="orderId">
        <el-input v-model="form.orderId" placeholder="请输入订单编号"></el-input>
      </el-form-item>

      <!-- 会员编号 -->
      <el-form-item label="会员编号" prop="memberId">
        <el-input v-model="form.memberId" placeholder="请输入会员编号"></el-input>
      </el-form-item>

      <!-- 店铺编号 -->
      <el-form-item label="店铺编号" prop="merchantId">
        <el-input v-model="form.merchantId" placeholder="请输入店铺编号"></el-input>
      </el-form-item>

      <!-- 店主编号 -->
      <el-form-item label="店主编号" prop="shopkeeperId">
        <el-input v-model="form.shopkeeperId" placeholder="请输入店主编号"></el-input>
      </el-form-item>

      <!-- 贷款金额 -->
      <el-form-item label="贷款金额" prop="amount">
        <el-input v-model="form.amount" placeholder="请输入贷款金额"></el-input>
      </el-form-item>

      <!-- 结束时间 -->
      <el-form-item label="结束时间" prop="startDate">
        <el-input v-model="form.startDate" placeholder="请输入结束时间"></el-input>
      </el-form-item>

      <!-- 结束时间 -->
      <el-form-item label="结束时间" prop="endDate">
        <el-input v-model="form.endDate" placeholder="请输入结束时间"></el-input>
      </el-form-item>

      <!-- 驳回原因 -->
      <el-form-item label="驳回原因" prop="reason">
        <el-input v-model="form.reason" placeholder="请输入驳回原因"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :loading="onSubmit" @click="handleSave">保存</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { find, save } from '@/api//loan'

export default {
  data() {
    return {
      form: {
        id: undefined,
        status: 1,
        orderId: '',
        memberId: '',
        merchantId: '',
        shopkeeperId: '',
        amount: '',
        startDate: '',
        endDate: '',
        reason: ''
      },

      onSubmit: false,

      // 表单验证规则
      rules: {
//        mobile: [
//          { required: true, message: '请输入手机号码', trigger: 'blur' }
//        ],
//        username: [
//          { required: true, message: '请输入姓名', trigger: 'blur' },
//          { max: 15, message: '姓名长度不能超过15个字符', trigger: 'blur' }
//        ],
//        email: [
//          { required: true, message: '请输入邮箱', trigger: 'blur' },
//          { type: 'email', message: '您输入的邮箱格式不正确', trigger: 'blur' }
//        ],
//        password: [
//          { required: true, message: '请输入密码', trigger: 'blur' },
//          { min: 6, message: '密码长度不能少于6个字符', trigger: 'blur' }
//        ]
      }
    }
  },
  created() {
    if (this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.findById(this.form.id)
    }
  },
  methods: {
    /**
     * 查询农资贷
     */
    findById(id) {
      find(id).then(response => {
        this.form.status = response.loan.status
        this.form.orderId = response.loan.orderId
        this.form.memberId = response.loan.memberId
        this.form.merchantId = response.loan.merchantId
        this.form.shopkeeperId = response.loan.shopkeeperId
        this.form.amount = response.loan.amount
        this.form.startDate = response.loan.startDate
        this.form.endDate = response.loan.endDate
        this.form.reason = response.loan.reason
      })
    },

    /**
     * 保存农资贷
     */
    handleSave() {
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
