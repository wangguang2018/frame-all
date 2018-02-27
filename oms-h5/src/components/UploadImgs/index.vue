<template>
  <div>
    <el-upload name="image" ref="upload" action="/api/upload/image" :on-error="handleError" :on-success="handleSuccess" :file-list="getFileList" list-type="picture-card" 
    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-change="handleChange" :on-progress="handleOverSize">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog v-model="dialogVisible" size="tiny">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
import { Notification } from 'element-ui'
export default {
  name: 'upload_img',
  components: { Notification },
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      params: {
        currentSize: 0,
        input: undefined,
        imgs: []
      }
    }
  },
  props: {
    maxSize: {
      type: Number,
      default: 1
    },
    fileList: {
      type: Array,
      default: []
    }
  },
  computed: {
    getFileList() {
      this.params.imgs = this.fileList
      return this.fileList
    },
    getMaxSize() {
      return this.maxSize
    }
  },
  methods: {
    handleRemove(file, fileList) {
      this.params.currentSize = fileList.length
      this.checkImgSize()
      this.params.imgs = fileList
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleOverSize(event, file, fileList) {
      const overSize = file.size / 1024 < 1024
      if (!overSize) {
        fileList.length = fileList.length - 1
        Notification.error({
          title: '失败',
          message: '请上传小于1M的图片',
          duration: 2000
        })
      }
    },
    checkImgSize() {
      if (this.params.currentSize >= this.maxSize) {
        this.params.input.hidden = true
      } else {
        this.params.input.hidden = false
      }
    },
    handleChange(file, fileList) {
      this.params.currentSize = fileList.length
      this.checkImgSize()
      this.params.imgs = fileList
    },
    handleSuccess(response, file, fileList) {
      // 上传失败后把图片移除
      if (response.err_code !== 0) {
        for (var i = 0; i < fileList.length; i++) {
          if (file.name === fileList[i].name) {
            fileList.splice(i, 1)
          }
        }
        Notification.error({
          title: '失败',
          message: '上传失败',
          duration: 2000
        })
      } else {
        file.url = response.img.url
      }
    },
    handleError(err, file, fileList) {
      Notification.error({
        title: '失败',
        message: '上传失败',
        duration: 2000
      })
      Promise.reject(err)
    }
  },
  created() {
    this.params.imgs = this.fileList
  },
  mounted() {
    this.params.input = this.$refs.upload.$refs['upload-inner'].$refs.input.parentElement
    this.params.currentSize = this.fileList.length
    this.checkImgSize()
  }
}
</script>

<style>

</style>
