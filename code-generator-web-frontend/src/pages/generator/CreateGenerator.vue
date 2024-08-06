<template>
  <div class="add-codegenerator">
    <a-steps :current="current" :items="items" style="width: 85%; margin: 0 auto;"></a-steps>
    <div class="steps-content">
      <div v-if="steps[current].id === 1" class="basic-information">
        <a-form layout='vertical' name="custom-validation" :model="formState" :rules="rulesAdd">
          <a-form-item has-feedback label="名称" name="name">
            <a-input v-model:value="formState.name" autocomplete="off" />
          </a-form-item>
          <a-form-item has-feedback label="描述" name="description">
            <a-input v-model:value="formState.description" autocomplete="off" />
          </a-form-item>

          <a-form-item has-feedback label="基础包" name="basePackage">
            <a-input v-model:value="formState.basePackage" autocomplete="off" />
          </a-form-item>

          <a-form-item has-feedback label="版本" name="version">
            <a-input v-model:value="formState.version" autocomplete="off" />
          </a-form-item>

          <a-form-item has-feedback label="作者" name="author">
            <a-input v-model:value="formState.author" autocomplete="off" />
          </a-form-item>

          <a-form-item label="标签" name="tags">
            <a-select v-model:value="formState.tags" mode="tags" style="width: 100%"
                      placeholder="Tags Mode"></a-select>
          </a-form-item>

          <a-form-item has-feedback label="图片" name="picture">
            <PictureUpload v-model:imageUrl="formState.picture" :biz="FileUploadBizEnum.GENERATOR_PICTURE" :maxCount="1"></PictureUpload>
            {{ formState.picture }}
          </a-form-item>
        </a-form>
      </div>

      <div v-if="steps[current].id === 2">
        <AddModelConfig v-model:value="formState.modelConfig"></AddModelConfig>
      </div>

      <div v-if="steps[current].id === 3">
        <AddFileConfig v-model:value="formState.fileConfig"></AddFileConfig>
      </div>

      <div v-if="steps[current].id === 4" style="width: 500px; margin: 16px auto;">
        <a-form layout='vertical'>
          <a-form-item hasFeedback label="产物路径" name="distPath">
            <FileUpload v-model:value="formState.distPath" :biz="FileUploadBizEnum.GENERATOR_DIST" :maxCount="1"></FileUpload>
          </a-form-item>
        </a-form>

        <a-collapse collapsible="header">
          <a-collapse-panel key="1" header="生成制作工具">
            <a-form layout='vertical'>
              <a-form-item label="模板文件" name="templateFilePath">
                <FileUpload v-model:value="templateFilePath" :biz="FileUploadBizEnum.GENERATOR_MAKE_TEMPLATE" :maxCount="1"></FileUpload>
              </a-form-item>
            </a-form>
            <a-button type="primary" @click="makerTemplate" :loading="makerLoading">制作</a-button>
          </a-collapse-panel>
        </a-collapse>

      </div>

    </div>
    <div class="steps-action">
      <a-button v-if="current < steps.length - 1" type="primary" @click="next">下一步</a-button>

      <a-button v-if="current == steps.length - 1" type="primary" @click="doSubmit">
        提交
      </a-button>

      <a-button v-if="current > 0" style="margin-left: 8px" @click="prev">上一步</a-button>
      <a-button v-if="current == 0 && props.id" style="margin-left: 8px;" @click="routerBack">
        返回
      </a-button>
    </div>
  </div>

</template>

<script setup lang="ts">
import {FileUploadBizEnum} from "../../constants";
import FileUpload from "../../components/upload/FileUpload.vue";
import PictureUpload from "../../components/upload/PictureUpload.vue";
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {message} from "ant-design-vue";
import {saveAs} from "file-saver";
import {addGenerator, editGenerator, getGeneratorVOById, makeGenerator} from "../../api/code-generator.ts";
import AddModelConfig from "./components/AddModelConfig.vue";
import AddFileConfig from "./components/AddFileConfig.vue";

const current = ref<number>(0);
const router = useRouter()
const props = defineProps(['id'])

onMounted(() => {
  loadData()
})

const loadData = async () => {
  if (!props.id) {
    return;
  }
  await getGeneratorVOById(props.id).then(res => {
    if (res.code === 0) {
      formState.value = res.data
      formState.value.modelConfig = JSON.parse(res.data.modelConfig)
      formState.value.fileConfig = JSON.parse(res.data.fileConfig)
    } else {
      message.error(res.message)
    }
  }).catch(error => {
    message.error(error.message)
  })
}

const next = () => {
  current.value++;
};
const prev = () => {
  current.value--;
};
const steps = [
  {
    title: '基本信息',
    id: 1,
    content: 'First-content',
  },
  {
    title: '模型配置',
    id: 2,
    content: 'Second-content',
  },
  {
    title: '文件配置',
    id: 3,
    content: 'Last-content',
  },
  {
    title: '生成器文件',
    id: 4,
    content: 'Last-content',
  },
];
const items = steps.map(item => ({ key: item.id, title: item.title }));

const formState = ref<API.GeneratorAddParams>({
  name: "",
  description: "",
  basePackage: "",
  version: "",
  author: "",
  tags: [],
  picture: "",
  fileConfig: {
    inputRootPath: "",
    outputRootPath: "",
    sourceRootPath: "",
    type: "",
    files: []
  },
  modelConfig: {
    models: []
  },
  distPath: "",
  status: 0
})

const templateFilePath = ref('')

const rulesAdd = {
  name: [{
    required: true,
    trigger: 'change',
    message: '名称不能为空'
  }],
  description: [{
    required: true,
    message: '描述不能为空',
    trigger: 'change'

  }],
  basePackage: [{
    required: true,
    trigger: 'change'
  }],
  version: [{
    required: true,
    trigger: 'change'
  }],
  author: [{
    required: true,
    message: '作者名字不能为空',
    trigger: 'change'
  }],
  picture: [{
    required: true,
    trigger: 'change',
    message: '上传正确的图片'
  }],
  fileConfig: [{
    require: true,
    trigger: 'change',
    message: '文件配置不能为空'
  }],
  modelConfig: [{
    require: true,
    trigger: 'change',
    message: '模型配置不能为空'
  }],
  disPath: [{
    require: true,
    trigger: 'change',
    message: '代码生成器的路径不能为空'
  }]
}

const doSubmit = async () => {
  // 创建
  if (!props.id) {
    if (!formState.value.fileConfig) {
      formState.value.fileConfig = {}
    }
    if (!formState.value.modelConfig) {
      formState.value.modelConfig = {}
    }
    await addGenerator(formState.value).then(res => {
      if (res.code === 0) {
        message.success('新增成功')
        router.push('/generator/detail/' + res.data)
      } else {
        message.error(res.message)
      }
    }).catch(error => {
      message.error(error.message)
    })
  } else {
    // 更新
    await editGenerator({id: props.id, ...formState.value}).then(res => {
      if (res.code === 0) {
        message.success('更新成功')
        router.push('/generator/detail/' + props.id)
      } else {
        message.error(res.message)
      }
    }).catch(error => {
      message.error(error.message)
    })
  }
}

const makerLoading = ref(false)
const makerTemplate = async () => {
  makerLoading.value = true
  const meta: API.Meta = {
    name: formState.value.name,
    description: formState.value.description,
    basePackage: formState.value.basePackage,
    version: formState.value.version,
    author: formState.value.author,
    modelConfig: formState.value.modelConfig,
    fileConfig: formState.value.fileConfig,
  }
  const res = await makeGenerator({ zipFilePath: templateFilePath.value, meta: meta })
  saveAs(res, `${formState.value.name}.zip`)
  message.success('下载成功')
  templateFilePath.value = ''
  makerLoading.value = false
}

const routerBack = () => {
  router.back()
}

</script>

<style scoped>
.add-codegenerator {
  margin: 20px auto;
  padding: 20px;
  background-color: white;
}

.steps-content {
  width: 85%;
  margin: 16px auto;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: white;
  min-height: 200px;
}

.basic-information {
  margin: 16px auto;
  width: 450px;
  background-color: white;
  min-height: 200px;
}

.steps-action {
  margin: 24px auto;
  width: 450px;
}

[data-theme='dark'] .steps-content {
  background-color: #2f2f2f;
  border: 1px dashed #404040;
}
</style>
