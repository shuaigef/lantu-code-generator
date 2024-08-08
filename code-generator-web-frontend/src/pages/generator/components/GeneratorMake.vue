<template>
  <a-form layout='vertical'>
    <a-form-item label="模板文件" name="templateFilePath">
      <a-upload-dragger v-model:fileList="fileList" name="file" :multiple="false" :maxCount="maxCount" :disable="loading"
                        :beforeUpload="beforeUploadHandle" @drop="handleDrop" @remove="removeValue">
        <p class="ant-upload-drag-icon">
          <inbox-outlined></inbox-outlined>
        </p>
        <p class="ant-upload-text">点击或拖拽文件上传</p>
        <p class="ant-upload-hint">
            请上传压缩包，打包时不要添加最外层目录
        </p>
      </a-upload-dragger>
      <a-button type="primary" @click="makerTemplate" :loading="makerLoading">制作</a-button>
    </a-form-item>
  </a-form>
</template>

<script setup lang="ts">
import {defineProps, ref} from "vue";
import {makeGenerator} from "../../../api/code-generator.ts";
import {saveAs} from "file-saver";
import {message} from "ant-design-vue";
import {InboxOutlined} from "@ant-design/icons-vue";

const props = defineProps(['meta', 'biz'])

const makerLoading = ref(false)

const makerTemplate = async () => {
  makerLoading.value = true
  const meta: API.Meta = {
    name: props.meta.name,
    description: props.meta.description,
    basePackage: props.meta.basePackage,
    version: props.meta.version,
    author: props.meta.author,
    modelConfig: props.meta.modelConfig,
    fileConfig: props.meta.fileConfig,
  }
  const formData = new FormData()
  formData.append("file", fileList.value[0].originFileObj)
  formData.append("meta", JSON.stringify(meta))
  const res = await makeGenerator(formData)
  saveAs(res, `${props.meta.name}.zip`)
  message.success('下载成功')
  makerLoading.value = false
}


// #region 文件上传
const fileList = ref<any>([]);
const loading = ref(false)

const maxCount = ref(1)

const removeValue = (file: any) => {
  return true
}

function handleDrop(e: DragEvent) {
  console.log(e);
}

function beforeUploadHandle(file: any) {
  fileList.value = [file]
  return false
}
// #endregion


</script>

<style scoped>

</style>
