<template>
  <div style="float:right;display: inline">
    <!-- 抽屉 -->
    <el-drawer
      :with-header="false"
      title="编辑收藏"
      size="500px"
      :visible.sync="drawer"
      direction="ttb"
    >
      <!-- 抽屉显示区域 -->
      <el-container>
        <el-main>
          <el-form
            ref="websiteEditForm"
            :model="websiteEditForm"
            :rules="rules"
            size="small"
            label-width="150px"
            class="demo-ruleForm"
          >
            <el-form-item
              label="网址类型"
              prop="type"
            >
              <el-radio-group v-model="websiteEditForm.typeId">
                <el-radio
                  v-for="item in typeList"
                  :key="item.id"
                  :label="item.id"
                >{{ item.name }}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item
              label="网站名称"
              prop="name"
            >
              <el-input v-model="websiteEditForm.name" />
            </el-form-item>
            <el-form-item
              label="网站URL"
              prop="url"
            >
              <el-input v-model="websiteEditForm.url" />
            </el-form-item>
            <el-form-item
              label="网站favicon.ioc"
              prop="iconUrl"
            >
              <el-input v-model="websiteEditForm.iconUrl" />
            </el-form-item>

            <el-form-item
              label="收藏"
              prop="favorite"
            >
              <el-switch v-model="websiteEditForm.favorite" />
            </el-form-item>

            <el-form-item
              label="描述"
              prop="descp"
            >
              <el-input
                v-model="websiteEditForm.descp"
                type="textarea"
              />
            </el-form-item>
            <el-form-item>
              <el-button
                type="primary"
                @click="submitForm('websiteEditForm')"
              >SAVE</el-button>
              <el-button @click="resetForm('websiteEditForm')">RESET</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-drawer>
  </div>
</template>
<script>
import { favoriteApi } from '@/api/favorite'
export default {
  components: {
  },
  data () {
    return {
      drawer: false,
      typeList: [],
      websiteEditForm: {
          id: null,
          name: '',
          url: '',
          iconUrl: '',
          descp: '',
          favorite: false,
          typeId: 1
        },
        rules: {
          name: [
            { required: true, message: '请输入网址名称', trigger: 'blur' },
            { min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur' }
          ],
          url: [
            { required: true, message: '请输入网址URL', trigger: 'blur' },
            { min: 8, max: 150, message: '长度在 8 到 150 个字符', trigger: 'blur' }
          ],
          typeId: [
            { required: true, message: '请选择一个类型', trigger: 'change' }
          ]
        }
    }
  },

  created () {
    this.getTypeList()
  },
  methods: {

    getTypeList() {
      favoriteApi.typeList().then(response => {
        this.typeList = response.typeList
      })
    },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.save(true)
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },
    // 打开抽屉方法
    open (id, typeId) {
      if (id !== null) {
        var _ = this
        favoriteApi.websiteInfo(id).then(response => {
          _.websiteEditForm = response.websiteForm
        })
      } else {
        this.websiteEditForm = {
          id: null,
          name: '',
          url: '',
          iconUrl: '',
          descp: '',
          favorite: false,
          typeId: typeId
        }
      }
      this.drawer = true
    },
    save (close) {
      favoriteApi.websiteEdit(this.websiteEditForm).then(response => {
        if (close) {
          this.$emit('parentCallback')
          this.drawer = false
        }
      })
    },
    handleSaveWord() {
      this.save(true)
    }
  }
}
</script>
<style scope>
.demo-ruleForm {
  margin-right: 50px;
}
</style>
