<template>
  <div class="search-manager">
    <div class="search-content">
      <a-input-search v-model:value="searchText" placeholder="搜索代码生成器" enter-button="搜索" size="large"
                      @search="onSearch" />
    </div>
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane key="1" tab="最新">
        <a-form ref="formRef" name="advanced_search" class="ant-advanced-search-form" :model="formState"
                @finish="onTabSearch">
          <a-row :gutter="24" class="tab-content">

            <a-col :span="9">
              <a-form-item name="authoe" label="作者" :rules="[{ required: false, message: '请输入' }]">
                <a-input v-model:value="formState.author" placeholder="请输入"></a-input>
              </a-form-item>
            </a-col>

            <a-col :span="9">
              <a-form-item name="tags" label="标签" :rules="[{ required: false, message: '请输入' }]">
                <a-select v-model:value="formState.tags" mode="tags" style="width: 100%"
                          placeholder="请选择"></a-select>
              </a-form-item>
            </a-col>

            <a-col :span="5" style="text-align: right">
              <a-button type="primary" html-type="submit" @click="">查询</a-button>
              <a-button style="margin: 0 8px" @click="resetQuery">重置</a-button>
            </a-col>

          </a-row>
        </a-form>
        <a-flex wrap="wrap" gap="large">
          <div style="width: 320px; margin: 0 auto;" v-if="dataSource.length < 1 ? true : false">
            <a-empty description="无数据"></a-empty>
          </div>

          <a-card v-else hoverable style="width: 265px" :bodyStyle="{ 'padding-bottom': '5px', 'height': '140px' }"
                  v-for="(item, index) in dataSource">
            <template #cover>
              <img alt="example" :src="item.picture" />
            </template>
            <div>
              <a-card-meta :title="item.name">
                <template #description>
                  <a-typography-paragraph :ellipsis="ellipsis ? { rows: 2, } : false" :content="item.description" />
                  <div class="tag-content">
                    <a-tag v-for="(record, index) in item.tags">{{ record }}</a-tag>
                  </div>
                </template>
              </a-card-meta>
            </div>
            <template #actions>
              <div style="margin: 0 20px">
                <a-flex justify="space-between">
                  <a-typography-text type="secondary">{{ item.createTime }}</a-typography-text>
                  <a-avatar :src="item.user.userAvatar" />
                </a-flex>
              </div>
            </template>
          </a-card>
        </a-flex>
        <a-row justify="end">
          <a-pagination v-model:pageSize="pageSize" v-model:current="current" :total="total"
                        :show-total="(total: number) => `总共 ${total} 记录`"
                        @change="pageChangeHandle"
          />
        </a-row>
      </a-tab-pane>
      <a-tab-pane key="2" tab="推荐" force-render>后续版本更新</a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from 'vue';
import {listGeneratorVOByPage} from "../api/code-generator.ts";
import {transferTime} from "../utils/transfer";
import {message} from "ant-design-vue";

const activeKey = ref('1');
const searchText = ref('');

const current = ref(1);
const pageSize = ref(10)
const total = ref(0)

const pageChangeHandle = (page: number, pageSize: number) => {
  generatorQueryParams.value.current = current.value
  listGenerator()
}

const onSearch = (value: string) => {
  searchText.value = value
  current.value = 1
  listGenerator()
}

watch(() => current.value, (newValue) => {
  generatorQueryParams.value.current = newValue
})

watch(() => searchText.value, (newValue) => {
  generatorQueryParams.value.searchText = newValue
})

onMounted(() => {
  listGenerator()
})

const dataSource = ref<API.GeneratorVO[]>([])
// 自动溢出省略
const ellipsis = ref(true);

// 查询条件
const generatorQueryParams = ref<API.GeneratorQueryParams>({
  id: "",
  notId: "",
  searchText: "",
  tags: [],
  orTags: [],
  userId: "",
  name: "",
  description: "",
  basePackage: "",
  version: "",
  author: "",
  distPath: "",
  status: 0,
  pageSize: pageSize.value,
  current: current.value
})

const listGenerator = () => {
  listGeneratorVOByPage(generatorQueryParams.value).then(res => {
    if (res.code === 0) {
      dataSource.value = res.data.records.map(record => {
        record.createTime = transferTime(record.createTime)
        return record;
      });
      total.value = res.data.total
    } else {
      message.error(res.message)
    }
  })
}

//#region 小搜索
const formState = ref({
  author: '',
  tags: []
})

const onTabSearch = () => {
  current.value = 1
  generatorQueryParams.value = {
    ...generatorQueryParams.value,
    ...formState.value
  }
  listGenerator()
}

const resetQuery = () => {
  formState.value.author = ''
  formState.value.tags = []
  generatorQueryParams.value = {
    ...generatorQueryParams.value,
    ...formState.value
  }
  current.value = 1
  listGenerator()
}
//#endregion
</script>

<style scoped>
.search-manager {
  max-width: 1140px;
  margin: 50px auto;
}

.search-content {
  width: 725px;
  margin: 0 auto;
}

.tag-content {
  margin-bottom: 15px;
}

.tab-content {
  background: white;
  margin-left: 0px;
  padding: 24px;
  padding-bottom: 4px;
  margin-bottom: 20px;
}
</style>
