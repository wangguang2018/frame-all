<template>
  <el-tag :type="type">{{statusName}}</el-tag>
</template>

<script>
export default {
  props: {
    status: {
      type: Number,
      required: true
    }
  },
  computed: {
    statusName() {
      if (this.status === 1) {
        return '可用'
      } else if (this.status === 0) {
        return '禁用'
      } else if (this.status === -1) {
        return '黑名单'
      } else {
        return '未知'
      }
    },
    type() {
      if (this.status === 1) {
        return 'success'
      } else if (this.status === 0) {
        return 'danger'
      } else if (this.status === 0) {
        return 'danger'
      } else {
        return 'warning'
      }
    }
  }
}
</script>
