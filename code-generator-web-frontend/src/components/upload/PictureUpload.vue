<template>
  <a-upload
      class="avatar-uploader" v-model:file-list="fileList" name="file" :multiple="false" :maxCount="maxCount" list-type="picture-card"
      :before-upload="beforeUpload" @preview="handlePreview" @remove="handleRemove" :customRequest="customPicture"
  >
    <div v-if="fileList.length < maxCount">
      <plus-outlined />
      <div style="margin-top: 8px">Upload</div>
    </div>
  </a-upload>
  <a-modal :open="previewVisible" :title="previewTitle" :footer="null" @cancel="handleCancel">
    <img alt="example" style="width: 100%" :src="previewImage" />
  </a-modal>
</template>
<script lang="ts" setup>
import { ref, defineProps, defineEmits } from 'vue';
import { PlusOutlined } from '@ant-design/icons-vue';
import {message, UploadProps} from 'ant-design-vue';
import { Form } from 'ant-design-vue';
import {uploadFile} from "../../api/file.ts";
import {COS_HOST} from "../../constants";

const props = defineProps({
  imageUrl: String,
  biz: String,
  maxCount: {
    type: Number,
    default: 8
  }
})
const biz = props.biz
const maxCount = props.maxCount

const emit = defineEmits(['update:imageUrl'])

const formItemContext = Form.useInjectFormItemContext();
const triggerChange = (changedValue: any) => {
    emit('update:imageUrl', changedValue);
    formItemContext.onFieldChange();
};

const fileList = ref([]);
const loading = ref<boolean>(false);

const previewVisible = ref(false)
const previewImage = ref('')
const previewTitle = ref('')

const customPicture = async (fileObj: any) => {
    console.log('fileObj: ' + fileObj)
    loading.value = true
    try {
      const res = await uploadFile(biz, fileObj.file)
      if (res.code === 0) {
        triggerChange(COS_HOST + res.data)
        message.success('上传成功')
        console.log(`上传成功: ${fileObj.filename}上传成功`)
        fileObj.onSuccess(res.data)
      } else {
        message.error(`上传失败: ${res.data}`);
        console.log(`file upload failed: ${res.data}`);
        fileObj.onError(res.data)
      }
    } catch (error: any) {
      message.error(`上传失败: ${error}`);
      console.log(`file upload failed: ${error}`);
      fileObj.onError(error)
    }
    loading.value = false
}

const beforeUpload = (file: any) => {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
    if (!isJpgOrPng) {
        message.error('你只能上传image和png类型的图片');
    }
    const isLt4M = file.size / 1024 / 1024 < 4;
    if (!isLt4M) {
        message.error('图片大小要小于4M');
    }
    return isJpgOrPng && isLt4M;
}
const handleCancel = () => {
  previewVisible.value = false;
  previewTitle.value = '';
}
function getBase64(file: File) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}
const handlePreview = async (file: UploadProps['fileList'][number]) => {
  if (!file.url && !file.preview) {
    file.preview = (await getBase64(file.originFileObj)) as string;
  }
  previewImage.value = file.url || file.preview;
  previewVisible.value = true;
  previewTitle.value = file.name || file.url.substring(file.url.lastIndexOf('/') + 1);
};
const handleRemove = () => {
  // imageUrl 设为空
  triggerChange('')
}
</script>
<style scoped>
.avatar-uploader>.ant-upload {
    width: 128px;
    height: 128px;
}

.ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
}
</style>
