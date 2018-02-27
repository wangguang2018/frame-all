<template>
  <section>

    <el-tooltip :placement="placement" effect="light">
      <div slot="content" style="text-align: center;">
        <img :src="avatar ? avatar : defaultAvatar"/>
      </div>
      <img :src="avatar ? avatar : defaultAvatar" class="full-img-radius"/>
    </el-tooltip>

  </section>
</template>

<script type="text/ecmascript">

  export default {
    name: "avatar",
    props: ['avatarSrc', 'tipPlacement', 'tipEffect'],
    data() {
      return {
        placement: this.tipPlacement || 'right',
        effect: this.tipEffect || 'light'
      }
    },
    methods: {},
    computed: {
      avatar() {
        return this.avatarSrc
      }
    },
    mounted() {
    }
  }
</script>
