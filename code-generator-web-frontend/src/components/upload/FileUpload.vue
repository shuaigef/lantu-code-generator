<template>
  <a-upload-dragger v-model:fileList="fileList" name="file" :multiple="false" :maxCount="maxCount" :disable="loading"
        @change="" @drop="handleDrop" :customRequest="customUpload" @remove="removeValue">
    <p class="ant-upload-drag-icon">
      <inbox-outlined></inbox-outlined>
    </p>
    <p class="ant-upload-text">点击或拖拽文件上传</p>
    <p class="ant-upload-hint">
      <template v-if="props.biz === 'generator_dist'">
        请上传生成器文件的压缩包
      </template>

      <template v-if="props.biz === 'generator_maker_template'">
        请上传压缩包，打包时不要添加最外层目录
      </template>
    </p>
  </a-upload-dragger>
</template>

<script lang="ts" setup>
import {defineProps, onMounted, ref, watch,} from 'vue';
import {InboxOutlined} from '@ant-design/icons-vue';
import {Form, message} from 'ant-design-vue';
import {uploadFile} from "../../api/file.ts";

const fileList = ref<any>([]);
const loading = ref(false)

onMounted(() => {
  if (props.value) {
    fileList.value.push({
      uid: '-1',
      name: props.value.slice(props.value.lastIndexOf('/') + 1),
      status: 'done',
      url: props.value,
    })
  }
})

const props = defineProps({
  value: String,
  biz: String,
  maxCount: {
    type: Number,
    default: 8
  }
})
const biz = props.biz
const maxCount = props.maxCount

const emit = defineEmits(['update:value'])

const formItemContext = Form.useInjectFormItemContext();
const triggerChange = (changedValue: any) => {
  emit('update:value', changedValue);
  formItemContext.onFieldChange();
};

const customUpload = async (fileObj: any) => {
  console.log('fileObj: ' + fileObj)
  loading.value = true
  try {
    const res = await uploadFile(biz, fileObj.file)
    if (res.code === 0) {
      // @ts-ignore
      // fileList.value.push(res.data)
      triggerChange(res.data)
      message.success('上传成功');
      console.log(`file uploaded successfully: ${res.data}`);
      fileObj.onSuccess(res.data)
    } else {
      message.error(`上传失败: ${res.data}`);
      console.log(`file upload failed: ${res.data}`);
      fileObj.onError(res.data)
    }
  } catch (error) {
    message.error(`上传失败: ${error}`);
    console.log(`file upload failed: ${error}`);
    fileObj.onError(error)
  }
  loading.value = false
}

const removeValue = (file: any) => {
  triggerChange('')
  return true
}

function handleDrop(e: DragEvent) {
  console.log(e);
}

watch(() => props.value, (newValue, oldValue) => {
  if (props.biz === 'generator_maker_template') {
    if (newValue === '') {
      fileList.value = []
    }
  }
})
</script>

<style scoped lang="scss">

</style>
