<template>
  <div class="detail-code">
    <div class="detail-basic">
      <a-row>
        <a-col :span="16">
          <a-space :size="30">
            <a-typography-title :level="3">{{ generator.name }}</a-typography-title>
            <a-tag v-for="item in generator.tags">
              {{ item }}
            </a-tag>
          </a-space>
          <a-typography-paragraph>{{ generator.description }}</a-typography-paragraph>
          <a-typography-paragraph type="secondary">创建时间：{{ dayjs(generator.createTime).format('YYYY-MM-DD HH:mm:ss') }}</a-typography-paragraph>
          <a-typography-paragraph type="secondary">基础包：{{ generator.basePackage }}</a-typography-paragraph>
          <a-typography-paragraph type="secondary">版本：{{ generator.version }}</a-typography-paragraph>
          <a-typography-paragraph type="secondary">作者：{{ generator.author }}</a-typography-paragraph>
          <a-divider />
          <a-space>
            <a-button type="primary" @click="toUseGenerator">立即使用</a-button>
            <a-button @click="toDownload">
              下载
            </a-button>
            <a-button v-if="generator.userId == loginUser.id" @click="toEdit">
              编辑
            </a-button>
          </a-space>
        </a-col>
        <a-col :span="8">
          <a-image :width="300" :src="generator.picture" />
        </a-col>
      </a-row>
    </div>

    <div class="detail-other">
      <a-tabs v-model:activeKey="activeKey">
        <a-tab-pane key="1" tab="文件配置">
          <FileConfigDetail :value="generator.fileConfig"></FileConfigDetail>
        </a-tab-pane>
        <a-tab-pane key="2" tab="模型配置" force-render>
          <ModelConfigDetail :value="generator.modelConfig"></ModelConfigDetail>
        </a-tab-pane>
        <a-tab-pane key="3" tab="作者信息">
          <!--<a-card>-->
          <!--  <a-card-meta :title="generator.author" :description="generator.user.userProfile">-->
          <!--    <template #avatar>-->
          <!--      <a-avatar :src="generator.user.userAvatar" />-->
          <!--    </template>-->
          <!--  </a-card-meta>-->
          <!--</a-card>-->

          <a-space :size="20">
            <a-flex>
              <a-avatar :size="64" :src="generator.user.userAvatar"></a-avatar>
              <a-flex vertical style="margin-left: 15px">
                <span style="font-weight: bold; font-size: 18px">
                  {{ generator.author }}
                </span>
                <span style="margin-top: 6px; font-size: 14px; color: grey">
                  {{ generator.user.userProfile }}
                </span>
              </a-flex>
            </a-flex>
          </a-space>
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {downloadGeneratorById, getGeneratorVOById} from "../../api/code-generator.ts";
import {message} from "ant-design-vue";
import dayjs from "dayjs";
import {useUserStore} from "../../store";
import {storeToRefs} from "pinia";
import {useRouter} from "vue-router";
import {saveAs} from "file-saver";
import FileConfigDetail from "./components/FileConfigDetail.vue";
import ModelConfigDetail from "./components/ModelConfigDetail.vue";

const props = defineProps(['id'])
const router = useRouter()

const userStore = useUserStore()
const { loginUser } = storeToRefs(userStore)

const activeKey = ref('1');

onMounted(() => {
  loadData()
})

const generator = ref<API.GeneratorVO>({})

const loadData = async () => {
  if (!props.id) {
    return;
  }
  await getGeneratorVOById(props.id).then(res => {
    if (res.code === 0) {
      generator.value = res.data
      console.log(JSON.parse(generator.value.fileConfig))
    } else {
      message.error(res.message)
    }
  }).catch(error => {
    message.error(error.message)
  })
}

const toUseGenerator = () => {
  router.push(`/generator/use/${props.id}`)
}
  const toDownload = async () => {
  const res = await downloadGeneratorById(props.id)
  const fullPath = generator.value.distPath || ''
  saveAs(res, fullPath.substring(fullPath.lastIndexOf('/') + 1))
}
  const toEdit = () => {
  router.push(`/generator/update/${props.id}`)
}
</script>

<style scoped>
.detail-code {
  max-width: 1152px;
  margin: 50px auto;
}

  .detail-basic {
    height: 345px;
  background-color: white;
  padding: 24px;
}

.detail-other {
  background-color: white;
  padding: 24px;
  margin-top: 25px;
}
</style>
