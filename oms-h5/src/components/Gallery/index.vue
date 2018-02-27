<template>
  <div>
    <div>
      <img style="margin-right:5px" v-for="(item,index) in imgList" :key="item" :src="item" class="preview" @click="showGallery(index)" :width="width">
    </div>
    <el-dialog title="图片" :visible.sync="params.dialogVisible" @open="onOpen">
      <el-carousel indicator-position="outside" :autoplay="false" ref="carousel" height="500px">
        <el-carousel-item v-for="(item,index) in imgList" :key="item" >
          <div style="text-align:center">
            <img :src="item" style="width:auto;height:100%">
          </div>
        </el-carousel-item>
      </el-carousel>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      params: {
        dialogVisible: false,
        index: 0
      }
    }
  },
  methods: {
    showGallery(index) {
      this.params.dialogVisible = true
      this.params.index = index
    },
    onOpen() {
      if (this.$refs.carousel) {
        this.$refs.carousel.setActiveItem(this.params.index)
      }
    }
  },
  props: {
    imgs: {
      type: Array,
      required: true
    },
    width: {
      type: Number,
      default: 100
    }
  },
  computed: {
    imgList() {
      return this.imgs
    }
  }
}
</script>
<style>
.preview {
  height: auto;
  cursor: pointer;
}
</style>
