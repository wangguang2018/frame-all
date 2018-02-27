<template>
  <el-tag :type="type">
    {{visibleName}}</el-tag>
</template>

<script>
export default {
  props: {
    visible: {
      type: Number,
      required: true
    }
  },
  computed: {
    visibleName() {
      if (this.visible === 0) {
        return '显示'
      } else if (this.visible === 1) {
        return '不显示'
      }
    },
    type() {
      if (this.visible === 0) {
        return 'success'
      } else if (this.visible === 1) {
        return 'danger'
      } else {
        return 'warning'
      }
    }
  }
}
</script>
