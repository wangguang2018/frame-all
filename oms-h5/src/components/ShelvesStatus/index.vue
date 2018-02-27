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
        return '下架'
      } else if (this.status === 0) {
        return '上架'
      } else {
        return '未知'
      }
    },
    type() {
      if (this.status === 0) {
        return 'success'
      } else if (this.status === 1) {
        return 'danger'
      } else {
        return 'warning'
      }
    }
  }
}
</script>
