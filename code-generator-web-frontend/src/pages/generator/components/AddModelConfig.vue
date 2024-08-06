<template>
  <div class="add-model">
    <template v-for="(item, index) in modelConfig.models">
      <a-card v-if="!item.hasOwnProperty('groupKey')" size="small" style="width: 80%;margin: 16px auto;" title="未分组字段">
        <template #extra>
          <CloseOutlined style="cursor: pointer;" @click="removeModels(item)"></CloseOutlined>
        </template>
        <a-form :model="item" layout='vertical'>
          <a-space>
            <a-form-item :rules="{required: true, message: '请输入字段名'}" label="字段名" name="fieldName">
              <a-input v-model:value="item.fieldName"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入类型'}" label="类型" name="type">
              <a-input v-model:value="item.type"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入描述'}" label="描述" name="description">
              <a-input v-model:value="item.description"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入默认值'}" label="默认值" name="defaultValue">
              <a-input v-model:value="item.defaultValue"/>
            </a-form-item>
            <a-form-item :rules="{required: true, message: '请输入缩写'}" label="缩写" name="abbr">
              <a-input v-model:value="item.abbr"/>
            </a-form-item>
          </a-space>
        </a-form>
      </a-card>

      <a-card v-if="item.hasOwnProperty('groupKey')" size="small" style="width: 80%;margin: 16px auto;" title="分组">
        <template #extra>
          <CloseOutlined style="cursor: pointer;" @click="removeModels(item)"></CloseOutlined>
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
            <a-form-item label="描述">
              <a-input v-model:value="item.description"/>
            </a-form-item>
            <a-form-item label="默认值">
              <a-input v-model:value="item.defaultValue"/>
            </a-form-item>
            <a-form-item label="简写">
              <a-input v-model:value="item.abbr"/>
            </a-form-item>
            <a-form-item label="条件">
              <a-input v-model:value="item.condition"/>
            </a-form-item>
          </a-space>
        </a-form>
        <p>组内字段</p>
        <a-form ref="formRef" :model="item" layout='vertical' name="dynamic_form_nest_item">
          <a-space v-for="(model, modelIndex) in item.models" :key="modelIndex"
                   align="center" style="display: flex; margin-bottom: 8px">
            <a-form-item :name="['models', modelIndex, 'fieldName']" :rules="{required: true, message: '请输入字段名'}" label="字段名">
              <a-input v-model:value="model.fieldName"/>
            </a-form-item>
            <a-form-item :name="['models', modelIndex, 'type']" :rules="{required: true, message: '请输入类型'}" label="类型">
              <a-input v-model:value="model.type"/>
            </a-form-item>
            <a-form-item :name="['models', modelIndex, 'description']" :rules="{required: true, message: '请输入描述'}" label="描述">
              <a-input v-model:value="model.description"/>
            </a-form-item>
            <a-form-item :name="['models', modelIndex, 'defaultValue']" :rules="{required: true, message: '请输入默认值'}" label="默认值">
              <a-input v-model:value="model.defaultValue"/>
            </a-form-item>
            <a-form-item :name="['models', modelIndex, 'abbr']" :rules="{required: true, message: '请输入缩写'}" label="缩写">
              <a-input v-model:value="model.abbr"/>
            </a-form-item>

            <a-button danger type="text" @click="removeModel(model, index)">删除</a-button>
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
        <a-col :span="20" offset="2">
          <a-button block type="dashed" @click="addItem">
            <PlusOutlined/>
            增加字段
          </a-button>
        </a-col>
      </a-row>
      <a-row style="margin-top: 20px;">
        <a-col :span="20" offset="2">
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

// onMounted(() => {
//   const dataObject = props.value
//   console.log('modelConfig', dataObject)
//   if (dataObject.hasOwnProperty('models')) {
//     console.log('yes yes yes yes')
//     modelConfig.value.models = [...dataObject.models]
//   }
// })

const modelConfig = ref<API.ModelConfig>(props.value)
console.log('modelConfig.value', modelConfig.value)

const addItem = () => {
  // console.log('点击增加未分组字段');
  modelConfig.value.models.push({
    fieldName: '',
    type: '',
    description: '',
    defaultValue: '',
    abbr: ''
  })
  console.log('点击增加未分组字段', modelConfig.value)
}

const addGroup = () => {
  // console.log('点击增加分组');
  modelConfig.value.models.push({
    groupKey: '',
    groupName: '',
    type: '',
    description: '',
    defaultValue: '',
    abbr: '',
    condition: '',
    models: []
  })
}

const formRef = ref<FormInstance>();

const removeModel = (model: API.ModelInfo, modelIndex: number) => {
  const index = modelConfig.value.models[modelIndex].models.indexOf(model);
  if (index !== -1) {
    modelConfig.value.models[modelIndex].models.splice(index, 1);
  }
};

const removeModels = (model: API.ModelInfo) => {
  const index = modelConfig.value.models.indexOf(model);
  if (index !== -1) {
    modelConfig.value.models.splice(index, 1);
  }
};

const addModel = (index: any) => {
  modelConfig.value.models[index].models.push({
    fieldName: '',
    type: '',
    description: '',
    defaultValue: '',
    abbr: ''
  })
};

watch(modelConfig.value, (newValue, oldValue) => {
  // console.log('监听到modelConfig的变化')
  triggerChange(modelConfig.value)
})
</script>

<style scoped>
.add-model {
  background-color: white;
}
</style>
