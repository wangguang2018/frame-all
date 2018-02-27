<template>
   <el-upload
      name="video"
      class="avatar-uploader"
      action="/api/upload/video"
      :show-file-list="false"
      :on-success="handleUploadSuccess">
      <video v-if="getVideo" :src="getVideo" controls  class="avatar"/>
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
</template>
<script>
import { Notification } from 'element-ui'
export default {
  name: 'upload_video',
  components: { Notification },
  data() {
    return {
      url: ''
    }
  },
  props: {
    video: {
      type: String,
      default: ''
    },
    handleSuccess: {
      type: Function
    }
  },
  created() {
    this.url = this.video
  },
  methods: {
    handleUploadSuccess(response, file, fileList) {
      if (response.err_code !== 0) {
        Notification.error({
          title: '失败',
          message: '上传失败',
          duration: 2000
        })
      } else {
        this.url = response.video.url
        if (this.handleSuccess) {
          this.handleSuccess(this.url)
        }
      }
    }
  },
  computed: {
    getVideo() {
      this.url = this.video
      return this.url
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 148px;
  height: 148px;
  line-height: 148px;
  text-align: center;
}
.avatar {
  width: 148px;
  height: 148px;
  display: block;
}
</style>

