<template>
  <el-tag type="success">
    {{typeName}}</el-tag>
</template>

<script>
export default {
  props: {
    type: {
      type: Number,
      required: true
    }
  },
  computed: {
    typeName() {
      if (this.type === 1) {
        return '商品'
      } else if (this.type === 2) {
        return '文章'
      }
    }
  }
}
</script>
