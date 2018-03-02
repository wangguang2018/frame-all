<template>
    <div class="app-container calendar-list-container">
        <div v-for="(item,index) in form.items" :key="item.id">
            <label>{{index+1}}.{{item.question}}</label><br/>
            <div v-if="item.type === 1 || item.type === 2">
               <el-table :data="item.options" stripe style="width: 100%">
                <el-table-column  prop="name" label="选项" >
                </el-table-column>
                <el-table-column  prop="count" label="小计" >
                </el-table-column>
                <el-table-column label="百分比">
                  <template scope="scope">
                      <label>{{scope.count / 100}}</label>
                  </template>
                </el-table-column>
              </el-table>
            </div>
        </div>
    </div>
</template>
<script>
import { statistics } from '@/api//questionnaire'
export default {
  name: 'questionnaire_statistics',
  data() {
    return {
      form: {
        id: undefined,
        items: []
      }
    }
  },
  created() {
    if (this.$route.query.id) {
      this.form.id = this.$route.query.id
      this.findById(this.form.id)
    }
  },
  methods: {
    findById(id) {
      statistics(id).then(response => {
        this.form.items = response.questionnaire.questionList
      })
    }
  }
}
</script>

