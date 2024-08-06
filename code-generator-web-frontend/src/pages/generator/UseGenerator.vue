<template>
  <div class="detail-basic">
    <a-row>
      <a-col :span="16">
        <a-space :size="30">
          <a-typography-title :level="3">{{ formStateDes.name }}</a-typography-title>
          <a-tag v-for="item in formStateDes.tags">
            {{ item }}
          </a-tag>
        </a-space>
        <a-typography-paragraph>{{ formStateDes.description }}</a-typography-paragraph>
        <a-typography-paragraph type="secondary">创建时间：{{ dayjs(formStateDes.createTime).format('YYYY-MM-DD HH:mm:ss') }}</a-typography-paragraph>
        <a-typography-paragraph type="secondary">基础包：{{ formStateDes.basePackage }}</a-typography-paragraph>
        <a-typography-paragraph type="secondary">版本：{{ formStateDes.version }}</a-typography-paragraph>
        <a-typography-paragraph type="secondary">作者：{{ formStateDes.author }}</a-typography-paragraph>
        <a-form :model="formState">
          <template v-for="item in listForm.models">

            <template v-if="!item.hasOwnProperty('groupKey')">
              <a-form-item :label="item.description">
                <a-input v-if="item.type === 'String'" v-model:value="formState[item.fieldName]"/>
                <a-radio-group v-if="item.type === 'boolean'" v-model:value="formState[item.fieldName]">
                  <a-radio value="true">是</a-radio>
                  <a-radio value="false">否</a-radio>
                </a-radio-group>
              </a-form-item>
            </template>

            <template v-if="item.hasOwnProperty('groupKey')">
              <a-collapse v-model:activeKey="activeKey" collapsible="header">
                <a-collapse-panel :key="item.groupKey" :header="item.groupName">
                  <template v-for="(el, _index) in item.models" :key="_index">

                    <a-form-item :label="el.description">
                      <a-radio-group v-if="el.type === 'boolean'" v-model:value="formState[item.groupKey][el.fieldName]">
                        <a-radio value="true">是</a-radio>
                        <a-radio value="false">否</a-radio>
                      </a-radio-group>
                      <a-input v-if="el.type === 'String'" v-model:value="formState[item.groupKey][el.fieldName]"/>
                    </a-form-item>

                  </template>
                </a-collapse-panel>
              </a-collapse>
            </template>
          </template>
        </a-form>

        <a-divider/>
        <a-space>
          <a-button :loading="useLoading" type="primary" @click="useCodeGenerator">
            <DownloadOutlined v-if="!useLoading"></DownloadOutlined>
            生成代码
          </a-button>
          <a-button @click="toDetail">
            <CreditCardOutlined/>
            查看详情
          </a-button>
        </a-space>
      </a-col>
      <a-col :span="7" :offset="1">
        <a-image :src="formStateDes.picture" :width="300"/>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
import {defineProps, onMounted, reactive, ref} from 'vue'
import {CreditCardOutlined, DownloadOutlined} from '@ant-design/icons-vue';
import {saveAs} from 'file-saver'
import {message} from 'ant-design-vue';
import {useRouter} from 'vue-router';
import {getGeneratorVOById, useGenerator} from "../../api/code-generator.ts";
import dayjs from "dayjs";

const router = useRouter()
const props = defineProps(['id'])

onMounted(() => {
  getGeneratorById()
})

const activeKey = ref(false)
const formState = ref<any>({
  // isNeed: null,
  // loopSet: null,
  // strName: ''
})

const formStateDes = ref<API.GeneratorVO>({})

let listForm = ref<API.ModelConfig>({
  models: []
})

const getGeneratorById = async () => {
  try {
    const res = await getGeneratorVOById(props.id)
    if (res.code === 0) {
      formStateDes.value = res.data

      const dataObject = JSON.parse(res.data.modelConfig)
      // const dataObject = JSON.parse(modelConfig)
      let groupArr: any = []
      let fileArr: any = []

      dataObject.models.forEach((el: API.ModelInfo) => {
        if (el.hasOwnProperty('groupKey') && el.groupKey) {
          formState.value[el.groupKey] = {}

          let item: any = {
            groupKey: el.groupKey,
            groupName: el.groupName,
            models: []
          }

          el.models.forEach((elModel: any) => {
            let itemModel = {
              fieldName: elModel.fieldName,
              type: elModel.type,
              description: elModel.description
            }
            formState.value[el.groupKey][elModel.fieldName] = elModel.defaultValue
            item.models.push(itemModel)
          })
          groupArr.push(item)
        } else {
          let itemFile: any = {
            fieldName: el.fieldName,
            type: el.type,
            description: el.description
          }
          formState.value[el.fieldName] = el.defaultValue
          fileArr.push(itemFile)
        }
      })
      listForm.value.models = [...fileArr, ...groupArr]
    }
  } catch (error: any) {
    message.error(error.message)
  }
}

const useLoading = ref(false)

const useCodeGenerator = async () => {
  useLoading.value = true
  console.log('formState.value', formState.value)
  try {
    const res = await useGenerator({id: props.id, dataModel: formState.value})
    console.log(res)
    saveAs(res, 'out-generator.zip')
    message.success('下载成功')
    useLoading.value = false
  } catch (error: any) {
    message.error(error.message)
  }
}

const toDetail = () => {
  router.push(`/generator/detail/${props.id}`)
}
</script>

<style scoped>
.detail-basic {
  max-width: 1152px;
  margin: 50px auto;
  background-color: white;
  padding: 24px;
}
</style>
