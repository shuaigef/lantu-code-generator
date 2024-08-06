<template>
  <a-descriptions :column="1">
    <template #title>
      <FileOutlined/>
      模型信息
    </template>
    <template v-for="item in modelConfig.models ">
      <template v-if="item.groupName">
        <a-descriptions-item>
          <a-descriptions :column="1" :title="item.groupName">
            <a-descriptions-item label="分组key">{{ item.groupKey }}</a-descriptions-item>
            <a-descriptions-item label="分组名">{{ item.groupName }}</a-descriptions-item>
            <a-descriptions-item label="分组类型">{{ item.type }}</a-descriptions-item>
            <a-descriptions-item label="分组描述">{{ item.description }}</a-descriptions-item>
            <a-descriptions-item label="分组命令">{{ item.abbr }}</a-descriptions-item>
            <a-descriptions-item label="条件">{{ item.condition }}</a-descriptions-item>
            <a-descriptions-item label="组内文件">
              <a-descriptions :column="1">
                <template v-for="(el, index) in item.models">
                  <a-descriptions-item>
                    <a-descriptions :column="2">
                      <a-descriptions-item label="字段名称">{{ el.fieldName }}</a-descriptions-item>
                      <a-descriptions-item label="类型">{{ el.type }}</a-descriptions-item>
                      <a-descriptions-item label="描述">{{ el.description }}</a-descriptions-item>
                      <a-descriptions-item label="默认值">{{ el.defaultValue }}</a-descriptions-item>
                      <a-descriptions-item label="缩写">{{ el.abbr }}</a-descriptions-item>
                    </a-descriptions>
                  </a-descriptions-item>
                </template>
              </a-descriptions>

            </a-descriptions-item>
            <!-- <a-descriptions-item> <a-divider></a-divider></a-descriptions-item> -->
          </a-descriptions>
        </a-descriptions-item>
        <a-descriptions-item span="2">
          <a-divider></a-divider>
        </a-descriptions-item>


      </template>
      <template v-if="!item.groupName">
        <a-descriptions-item>
          <a-descriptions :column="2">
            <a-descriptions-item label="字段名称">{{ item.fieldName }}</a-descriptions-item>
            <a-descriptions-item label="类型">{{ item.type }}</a-descriptions-item>
            <a-descriptions-item label="描述">{{ item.description }}</a-descriptions-item>
            <a-descriptions-item label="默认值">{{ item.defaultValue }}</a-descriptions-item>
            <a-descriptions-item label="缩写">{{ item.abbr }}</a-descriptions-item>
            <!-- <a-descriptions-item span="2"> <a-divider></a-divider></a-descriptions-item> -->
          </a-descriptions>

        </a-descriptions-item>
        <a-descriptions-item span="2">
          <a-divider></a-divider>
        </a-descriptions-item>


      </template>
    </template>
  </a-descriptions>
</template>

<script lang="ts" setup>
import {FileOutlined} from '@ant-design/icons-vue';
import {defineProps, ref, watch} from 'vue'

const props = defineProps(['value'])

const modelConfig = ref<API.ModelConfig>({
  models: []
})

//字符串转换为对象
// const transferFileConfig = (data: string) => {
//   if (data) {
//     console.log(data);
//
//     const res = JSON.parse(data)
//     console.log(res)
//     let tempGroup: any = []
//     let tempFile: any = []
//     res.models.forEach((el: any) => {
//       if (el.groupName) {
//         tempGroup.push(el)
//       } else {
//         tempFile.push(el)
//       }
//     })
//
//     res.models = [...tempGroup, ...tempFile]
//     modelConfig.value.models = res.models
//   }
//
// }

//监听事件
watch(() => props.value, (newValue, oldValue) => {
  // transferFileConfig(props.value)
  modelConfig.value = JSON.parse(props.value)
})
</script>

<style scoped>

</style>
