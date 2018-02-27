<template>
  <div class='tabs-view-container'>
    <router-link class="tabs-view" v-for="tag in Array.from(visitedViews)" :to="tag.uri" :key="tag.path">
      <el-tag :closable="true" :type="isActive(tag.path)?'primary':''" @close='closeViewTabs(tag,$event)'>
        {{tag.name}}
      </el-tag>
    </router-link>
  </div>
</template>

<script>
export default {
  computed: {
    visitedViews() {
      return this.$store.state.app.visitedViews.slice(-6)
    }
  },
  methods: {
    closeViewTabs(view, $event) {
      this.$store.dispatch('delVisitedViews', view).then((views) => {
        if (this.isActive(view.path)) {
          const latestView = views.slice(-1)[0]
          if (latestView) {
            this.$router.push(latestView.path)
          } else {
            this.$router.push('/')
          }
        }
      })
      $event.preventDefault()
    },
    generateRoute() {
      if (this.$route.matched[this.$route.matched.length - 1].name) {
        var route = this.$route.matched[this.$route.matched.length - 1]
        if (this.$route.query) {
          var path = '?'
          for (var e in this.$route.query) {
            path += e + '=' + this.$route.query[e] + '&'
          }
          path = path.substring(0, path.length - 1)
          this.$set(route, 'uri', route.path + path)
        }
        return route
      }
      this.$route.matched[0].path = '/'
      this.$set(this.$route.matched[0], 'uri', '/')
      return this.$route.matched[0]
    },
    addViewTabs() {
      this.$store.dispatch('addVisitedViews', this.generateRoute())
    },
    isActive(path) {
      return path === this.$route.path
    }
  },
  watch: {
    $route() {
      this.addViewTabs()
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.tabs-view-container {
  display: inline-block;
  vertical-align: top;
  margin-left: 10px;
  .tabs-view {
    margin-left: 10px;
  }
}
</style>
