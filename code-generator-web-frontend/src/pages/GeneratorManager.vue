<template>
  <div class="code-manager">
    <a-typography-title :level="4">生成器管理</a-typography-title>
    <div class="usermanage-search">
      <a-form ref="formRef" name="advanced_search" class="ant-advanced-search-form" :model="formState"
              @finish="onFinishSearch">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item name="name" label="id" :rules="[{ required: false, message: '请输入' }]">
              <a-input v-model:value="formState.id" placeholder=""></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item name="name" label="名称" :rules="[{ required: false, message: '请输入' }]">
              <a-input v-model:value="formState.name" placeholder=""></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6"> <a-form-item name="description" label="描述" :rules="[{ required: false, message: '请输入' }]">
            <a-input v-model:value="formState.description" placeholder=""></a-input>
          </a-form-item>
          </a-col>
          <a-col :span="5" style="text-align: right">
            <a-button type="primary" html-type="submit">查询</a-button>
            <a-button style="margin: 0 8px" @click="resetQuery">重置</a-button>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <div class="usermanage-list-header">
      <a-row justify="space-between">
        <a-col :span="2" style="line-height: 32px;">
          <span style="font-size: 16px; font-weight: 500;">查询表格</span>
        </a-col>
        <a-col :span="1">
          <a-button type="primary" @click="addHandle">新增</a-button>
        </a-col>
      </a-row>
    </div>
    <div class="usermanage-list">
      <a-table :columns="columns" :row-key="(record: any) => record.id" :data-source="dataSource" :loading="loading"
               :pagination="pagination">
        <template #bodyCell="{ column, text, record }">
          <template v-if="column.dataIndex === 'picture'">
            <div style="width: 80px ;height: 80px;  overflow: hidden;">
              <a-image :width="80" :height="80" :src="record.picture" />
            </div>
          </template>
          <template v-if="column.dataIndex === 'tags'">
            <a-space>
              <a-tag v-for="item in record.tags">{{ item }}</a-tag>
            </a-space>
          </template>
          <template v-if="column.dataIndex === 'distPath'">
            {{ record.distPath == 'null' ? '-' : record.distPath }}
          </template>
          <template v-if="column.dataIndex === 'status'">
            {{ record.status == 0 ? '默认' : '异常' }}
          </template>
          <template v-else-if="column.dataIndex === 'createTime'">
            <span>
              {{ dayjs(record.createTime).format('YYYY-MM-DD HH:mm') }}
            </span>
          </template>
          <template v-else-if="column.dataIndex === 'updateTime'">
            <span>
              {{ dayjs(record.createTime).format('YYYY-MM-DD HH:mm') }}
            </span>
          </template>
          <template v-if="column.dataIndex === 'operation'">
            <a @click="editHandle">编辑</a>
            <a-divider type="vertical" />
            <a-popconfirm v-if="dataSource.length" title="确定删除吗" okText="确定" cancelText="取消" @confirm="deleteHandle(record.id)">
              <a>删除</a>
            </a-popconfirm>
          </template>
        </template>

      </a-table>
    </div>
  </div>
</template>

<script setup lang="ts">
// import {useUserStore} from "../store";
// import {useRouter} from "vue-router";
import {computed, onMounted, ref} from "vue";
import {deleteGenerator, listGeneratorByPage} from "../api/code-generator.ts";
import {message} from "ant-design-vue";
import dayjs from 'dayjs'

// const userStore = useUserStore()
// const prop = defineProps(['id'])
// const router = useRouter()


const loading = ref(true)
const dataSource = ref<API.Generator[]>([])
const pagination = computed(() => ({
  total: 0,
  current: 1,
  pageSize: 10,
  showTotal(total: number) {
    return '总共 ' + total + ' 条记录'
  },
  onChange(page: number, pageSize: number){
    console.log(page, pageSize)
    this.current = page
    this.pageSize = pageSize
    formState.value.current = this.current
    listGenerator()
  }
}));

onMounted(() => {
  listGenerator()
})

//#region 搜索功能
const formState = ref<API.GeneratorQueryParams>({
  id: "",
  notId:"",
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
  current: pagination.value.current,
  pageSize: pagination.value.pageSize
});
const onFinishSearch = (values: any) => {
  console.log('Received values of form: ', values);
  console.log('formState: ', formState);
  pagination.value.current = 1
  pagination.value.pageSize = 10
  listGenerator()
};

const resetQuery = () => {
  console.log("点击重置");
  formState.value.id = ''
  formState.value.name = ''
  formState.value.description = ''
  pagination.value.current = 1;
  pagination.value.pageSize = 10;
  listGenerator()
}

//#endregion


//#region 新增用户功能
const addHandle = () => {
  message.warn('后续版本更新')
}
//#endregion


// #region 表格的数据和函数
const columns = [
  {
    title: 'id',
    dataIndex: 'id'
  },
  {
    title: '名称',
    dataIndex: 'name'
  },
  {
    title: '描述',
    dataIndex: 'description',
    ellipsis: true
  },
  {
    title: '基础包',
    dataIndex: 'basePackage',
    ellipsis: true
  },
  {
    title: '版本',
    dataIndex: 'version'
  },
  {
    title: '作者',
    dataIndex: 'author',
    ellipsis: true,
  },
  {
    title: '标签',
    dataIndex: 'tags',
    width: 130
  },
  {
    title: '图片',
    dataIndex: 'picture',
    ellipsis: true
  },
  {
    title: '文件配置',
    dataIndex: 'fileConfig',
    ellipsis: true
  },
  {
    title: '模型配置',
    dataIndex: 'modelConfig',
    ellipsis: true
  },
  {
    title: '代码生成器产物路径',
    dataIndex: 'distPath',
    ellipsis: true
  },
  {
    title: '状态',
    dataIndex: 'status'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    ellipsis: true,
  },
  {
    title: '修改时间',
    dataIndex: 'updateTime',
    ellipsis: true,
  },
  {
    title: '操作',
    dataIndex: 'operation',
  },
];

const listGenerator = () => {
  loading.value = true;
  console.log(formState);
  listGeneratorByPage(formState.value).then(res => {
    if (res.code === 0) {
      dataSource.value = res.data.records.map(record => {
        if (typeof record.tags === 'string') {
          record.tags = JSON.parse(record.tags);
        }
        return record;
      });
      loading.value = false
      pagination.value.total = res.data.total
    } else {
      message.error(res.message)
    }
  })
}

// #endregion


// #region 编辑和注销的功能
const editHandle = () => {
  message.warn('后续版本更新')
}

const deleteHandle = async (id: string) => {
  deleteGenerator(id).then(res => {
    if (res.code === 0) {
      message.success('删除成功')
      // 重新获取数据
      listGenerator()
    } else {
      message.error(res.message)
    }
  })
};
// #endregion

</script>

<style scoped>
.usermanage-list-header {
  background-color: white;
  height: 50px;
  padding-top: 10px;
  padding-left: 10px;
  padding-right: 25px;
}

.usermanage-search {
  background: white;
  margin-left: 0px;
  padding: 24px;
  padding-bottom: 4px;
  margin-bottom: 20px;
}

.code-manager {
  margin: 40px auto;
}

</style>
