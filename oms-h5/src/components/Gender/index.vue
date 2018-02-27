<template>
  <el-tag type="gray">{{genderName}}</el-tag>
</template>

<script>
export default {
  props: {
    gender: {
      type: Number,
      required: true
    }
  },
  computed: {
    genderName() {
      if (this.gender === 1) {
        return '男'
      } else if (this.gender === 2) {
        return '女'
      } else {
        return '未知'
      }
    }
  }
}
</script>
