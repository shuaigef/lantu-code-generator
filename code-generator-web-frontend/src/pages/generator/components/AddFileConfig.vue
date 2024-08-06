<template>
  <div style="width: 80%; margin: 0px auto; margin-bottom: 20px;">
    <a-alert closable message="如果不需要使用在线制作功能，可不填写" type="warning"/>
  </div>
  <div class="add-model">
    <template v-for="(item, index) in fileConfig.files">
      <a-card v-if="!item.hasOwnProperty('groupKey')" size="small" style="margin: 16px auto;" title="未分组字段">
        <template #extra>
          <CloseOutlined style="cursor: pointer;" @click="removeFiles(item)"></CloseOutlined>
        </template>
        <a-form :model="item" layout='vertical'>
          <a-space>
            <a-form-item :rules="{required: true, message: '请输入输入路径'}" label="输入路径" name="inputPath">
              <a-input v-model:value="item.inputPath"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入输出路径'}" label="输出路径" name="outputPath">
              <a-input v-model:value="item.outputPath"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入类型'}" label="类型" name="type">
              <a-input v-model:value="item.type"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入模板文件类型'}" label="模板文件类型" name="generateType">
              <a-input v-model:value="item.generateType"/>
            </a-form-item>
          </a-space>
        </a-form>
      </a-card>

      <a-card v-if="item.hasOwnProperty('groupKey')" size="small" style="margin: 16px auto;" title="分组">
        <template #extra>
          <CloseOutlined style="cursor: pointer;" @click="removeFiles(item)"></CloseOutlined>
        </template>
        <a-form :model="item" layout='vertical'>
          <a-space>
            <a-form-item label="分组键">
              <a-input v-model:value="item.groupKey"/>
            </a-form-item>
            <a-form-item label="分组名称">
              <a-input v-model:value="item.groupName"/>
            </a-form-item>
            <a-form-item label="类型">
              <a-input v-model:value="item.type"/>
            </a-form-item>
            <a-form-item label="条件">
              <a-input v-model:value="item.condition"/>
            </a-form-item>
          </a-space>
        </a-form>
        <p>分组字段</p>
        <a-form ref="formRef" :model="item" layout='vertical' name="dynamic_form_nest_item">
          <a-space v-for="(file, modelIndex) in item.files" :key="modelIndex"
                   align="center" style="display: flex; margin-bottom: 8px">
            <a-form-item :name="['files', modelIndex, 'inputPath']" :rules="{required: true, message: '请输入输入路径'}" label="输入路径">
              <a-input v-model:value="file.inputPath"/>
            </a-form-item>
            <a-form-item :name="['files', modelIndex, 'outputPath']" :rules="{required: true, message: '请输入输出路径'}" label="输出路径">
              <a-input v-model:value="file.outputPath"/>
            </a-form-item>
            <a-form-item :name="['files', modelIndex, 'type']" :rules="{required: true, message: '请输入类型'}" label="类型">
              <a-input v-model:value="file.type"/>
            </a-form-item>
            <a-form-item :name="['files', modelIndex, 'generateType']" :rules="{required: true, message: '请输入模板类型'}" label="模板类型">
              <a-input v-model:value="file.generateType"/>
            </a-form-item>
            <a-button danger type="text" @click="removeFile(file, index)">删除</a-button>
          </a-space>
          <a-form-item>
            <a-button block type="dashed" @click="addModel(index)">
              <PlusOutlined/>
              添加组内字段
            </a-button>
          </a-form-item>
        </a-form>
      </a-card>
    </template>

    <div>
      <a-row>
        <a-col :span="24">
          <a-button block type="dashed" @click="addItem">
            <PlusOutlined/>
            增加字段
          </a-button>
        </a-col>
      </a-row>
      <a-row style="margin-top: 20px;">
        <a-col :span="24">
          <a-button block type="dashed" @click="addGroup">
            <PlusOutlined/>
            增加分组
          </a-button>
        </a-col>
      </a-row>
    </div>

  </div>
</template>

<script lang="ts" setup>
import {CloseOutlined, PlusOutlined} from '@ant-design/icons-vue';
import {defineEmits, defineProps, onMounted, ref, watch} from 'vue';
import type {FormInstance} from 'ant-design-vue';
import {Form} from 'ant-design-vue';

const props = defineProps(['value'])
const emit = defineEmits(['update:value'])

const formItemContext = Form.useInjectFormItemContext()
const triggerChange = (changedValue: any) => {
  emit('update:value', changedValue)
  formItemContext.onFieldBlur()
}

onMounted(() => {
  const dataObject = props.value
  if (dataObject.hasOwnProperty('files')) {
    fileConfig.value.files = [...dataObject.files]
  }
})

const fileConfig = ref<API.FileConfig>({
  files: []
})

const addItem = () => {
  // console.log('点击增加未分组字段');
  fileConfig.value.files.push({
    inputPath: '',
    type: '',
    outputPath: '',
    generateType: '',
  })
}

const addGroup = () => {
  // console.log('点击增加分组');
  fileConfig.value.files.push({
    groupKey: '',
    groupName: '',
    type: '',
    condition: '',
    files: []
  })
}

const formRef = ref<FormInstance>();

const removeFile = (file: API.FileInfo, fileIndex: number) => {
  const index = fileConfig.value.files[fileIndex].files.indexOf(file);
  if (index !== -1) {
    fileConfig.value.files[fileIndex].files.splice(index, 1);
  }
};

const removeFiles = (file: API.FileInfo) => {
  const index = fileConfig.value.files.indexOf(file);
  if (index !== -1) {
    fileConfig.value.files.splice(index, 1);
  }
};

const addModel = (index: number) => {
  fileConfig.value.files[index].files.push({
    inputPath: '',
    type: '',
    outputPath: '',
    generateType: '',
  })
};

watch(fileConfig.value, (newValue, oldValue) => {
  // console.log('监听到modelConfig的变化', fileConfig.value)
  triggerChange(fileConfig.value)
})
</script>

<style scoped>
.add-model {
  background-color: white;
  width: 80%;
  margin: 0px auto;
}
</style>
