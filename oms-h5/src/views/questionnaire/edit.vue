<template>
  <div class="app-container app-edit">
    <el-form class="small-space" :model="form" :rules="rules" ref="form" label-position="right" label-width="120px" style="width: 700px;">

      <!-- 问卷调查标题 -->
      <el-form-item label="问卷调查标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入问卷调查标题"></el-input>
      </el-form-item>
      <el-form-item label="题目" prop="question">
          <div v-for="(item,index) in form.items" :key="item">
              <div v-if="item.type === 1" style="margin-bottom:5px">
                <label>{{item.question}}</label><el-button type="primary"  @click="removeQ(index)" style="margin-left:3px" size="mini" icon="delete"></el-button><br/>
                <el-checkbox-group>
                  <el-checkbox v-for="checkbox in item.checkboxs" :key="checkbox" :label="checkbox"></el-checkbox>
                </el-checkbox-group>
              </div>
              <div v-if="item.type === 2" style="margin-bottom:5px">
                <label>{{item.question}}</label><el-button type="primary"  @click="removeQ(index)" style="margin-left:3px" size="mini" icon="delete"></el-button><br/>
                <el-radio-group>
                  <el-radio v-for="radio in item.radios" :key="radio" :label="radio"></el-radio>
                </el-radio-group>
              </div>
              <div v-if="item.type === 3" style="margin-bottom:5px">
                <label>{{item.question}}</label><el-button type="primary" @click="removeQ(index)" style="margin-left:3px" size="mini" icon="delete"></el-button><br/>
                <el-input type="textarea" :rows="3">
                </el-input>
              </div>
          </div>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" :loading="onSubmit" @click="handleSave">保存草稿</el-button>
        <el-button type="primary" :loading="onSubmit" @click="handleSave">保存并发布</el-button>
        <el-button type="primary" :loading="onSubmit" @click="showQuestionDialog">添加题目</el-button>
        <el-button @click="handleCancel">取消</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="题目" :visible.sync="dialogFormVisible" size="tiny">
      <el-form :model="question"  ref="questionForm" :rules="questionRules">
        <el-form-item label="题目名称" prop="name">
          <el-input v-model="question.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <template>
            <el-radio-group v-model="question.type" @change="onChange">
              <el-radio class="radio" :label="1">多选题</el-radio>
              <el-radio class="radio" :label="2">单选题</el-radio>
              <el-radio class="radio" :label="3">文本框</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
      </el-form>
      <div>
        <div v-for="(item,index) in question.items" :key="item" >
          <el-input v-model="item.text" style="margin-bottom:5px">
            <el-button type="primary" @click="removeA(index)" slot="append" icon="delete"></el-button>  
          </el-input>
          
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAddQ">确 定</el-button>
        <el-button type="primary" @click="addQItem" v-if="question.type != 3">添加选项</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { find, save } from '@/api//questionnaire'

export default {
  data() {
    const validateQType = (rule, value, callback) => {
      if (this.question.type !== 3) {
        var flag = false
        this.question.items.forEach(element => {
          if (element.text) {
            flag = true
          }
        })
        if (!flag) { callback(new Error('至少添加一个选项')) } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const validateQuestion = (rule, value, callback) => {
      if (this.form.items.length <= 0) {
        callback(new Error('至少添加一个问题'))
      } else {
        callback()
      }
    }
    return {
      form: {
        id: undefined,
        title: '',
        question: '',
        status: 1,
        items: []
      },
      question: {
        name: '',
        type: 1,
        items: []
      },
      dialogFormVisible: false,
      onSubmit: false,
      questionRules: {
        name: [
         { required: true, message: '请输入题目', trigger: 'blur' }
        ],
        type: [
          { required: true, trigger: 'blur', validator: validateQType }
        ]
      },
      // 表单验证规则
      rules: {
        title: [
         { required: true, message: '请输入问卷调查标题', trigger: 'blur' }
        ],
        question: [
          { required: true, trigger: 'blur', validator: validateQuestion }
        ]
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
    removeA(index) {
      this.question.items.splice(index, 1)
    },
    removeQ(index) {
      this.form.items.splice(index, 1)
    },
    onChange() {
      if (this.question.type === 3) {
        this.question.items = []
      }
    },
    showQuestionDialog() {
      this.dialogFormVisible = true
      this.question.name = ''
      this.question.type = 1
      this.question.items = []
    },
    addQItem() {
      this.question.items.push({ 'text': '' })
    },
    handleAddQ() {
      this.$refs.questionForm.validate(valid => {
        if (valid) {
          var option = []
          this.question.items.forEach(item => {
            if (item.text) {
              option.push(item.text)
            }
          })

          if (this.question.type === 1) {
            var item = { 'type': this.question.type, 'question': this.question.name, 'checkboxs': option }
            this.form.items.push(item)
          } else if (this.question.type === 2) {
            item = { 'type': this.question.type, 'question': this.question.name, 'radios': option }
            this.form.items.push(item)
          } else if (this.question.type === 3) {
            item = { 'type': this.question.type, 'question': this.question.name }
            this.form.items.push(item)
          }
          this.dialogFormVisible = false
        }
      })
    },
    /**
     * 查询问卷调查
     */
    findById(id) {
      find(id).then(response => {
        this.form.title = response.questionnaire.title
        this.form.items = response.questionnaire.questionList
        this.form.status = response.questionnaire.status
      })
    },

    /**
     * 保存问卷调查
     */
    handleSave() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return false
        }
        this.onSubmit = true
        this.form.question = JSON.stringify(this.form.items)
        save(this.form).then(response => {
          this.$router.go(-1)
        }).finally(() => {
          this.onSubmit = false
        })
      })
    },

    /**
     * 取消
     */
    handleCancel() {
      this.$router.go(-1)
    }
  }
}
</script>
