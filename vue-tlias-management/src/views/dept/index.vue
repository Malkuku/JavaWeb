<script setup>
import { ref, onMounted } from 'vue';
import { queryAllApi, addApi} from '@/api/dept';
import { ElMessage, ElMessageBox } from 'element-plus';
import { deleteByIdApi, queryByIdApi, updateApi } from '../../api/dept';

//钩子函数
onMounted(() => {
  search();
})

//查询
const deptList = ref([])
const search = async () => {
  const result = await queryAllApi();
  if(result.code){
    deptList.value = result.data;
  }
}

//Dialog对话框
const dialogFormVisible = ref(false);
const dept = ref({name: ''});
const formTitle = ref('');

//添加部门
const addDept = ()=>{
  dialogFormVisible.value = true;
  formTitle.value = '新增部门';
  dept.value = {name:''};
  if(deptFormRef.value){
    deptFormRef.value.resetFields();
  }
}

const save = async()=>{
  if(!deptFormRef.value) return;
  deptFormRef.value.validate(async (valid) => {
    if(valid){ //表单验证是否通过
      let result; 
      if(dept.value.id){
        result = await updateApi(dept.value);
      }else{
        result = await addApi(dept.value);
      }
      if(result.code){
          ElMessage.success("操作成功");
          dialogFormVisible.value = false;
          search();
      }else{
          ElMessage.error(result.msg);
      }
    }else{
      ElMessage.error("验证不通过");
    }
  })
 
}

const rules = ref({
  name:[
    {required: true,message:'必填项',trigger:'blur'}, //trigger事件监听
    {min:2,max:10,message:'长度在2-10之间',trigger:'blur'}
  ]
})

const deptFormRef = ref();

const edit = async(id)=>{
  const result = await queryByIdApi(id);
  if(result.code){
    dept.value = result.data;
    dialogFormVisible.value = true;
    formTitle.value = '修改部门';
    if(deptFormRef.value){
      deptFormRef.value.resetFields();
    }
  }else{
    ElMessage.error(result.msg);
  }
}

const delById = async(id)=>{
  ElMessageBox.confirm("确认删除？","提示",
    { confirmButtonText:'确认',cancelButtonText:'取消',type:'warning'}
  ).then(async ()=>{
    const result = await deleteByIdApi(id);
    if(result.code){
      ElMessage.success("删除成功");
      search();
    }else{
      ElMessage.error(result.msg);
    }
  }).catch(()=>{
    ElMessage.info("取消删除");
  })
}

</script>

<template>
  <h1>部门管理</h1>
  <div class="container">
    <el-button type="primary" @click="addDept"> + 新增部门</el-button>
  </div>

  <!-- 表格 -->
  <div class="container">
    <el-table :data="deptList" border style="width: 100%">
      <el-table-column type="index" label="序号" width="100" align="center"/>
      <el-table-column prop="name" label="部门名称" width="300" align="center"/>
      <el-table-column prop="updateTime" label="最后操作时间" width="350" align="center"/>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)"><el-icon><EditPen /></el-icon> 编辑</el-button>
          <el-button type="danger" size="small" @click="delById(scope.row.id)"><el-icon><Delete /></el-icon> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- Dialog对话框 -->

  <el-dialog v-model="dialogFormVisible" :title="formTitle">
    <el-form :model="dept" :rules="rules" ref="deptFormRef"> <!-- 绑定校验规则 -->
      <el-form-item label="部门名称" label-width="80px" prop="name"> <!--绑定数据名称-->
        <el-input v-model="dept.name"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 15px 0px;
}
</style>
