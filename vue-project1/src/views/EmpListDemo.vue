<script setup>
    import { ref, onMounted } from 'vue'; 
    import { reactive } from 'vue';
    import axios from 'axios';

    const emp = ref({
        name: '',
        gender: '',
        job: '',  
    });

    const search = async () => {
        const result = await axios.get(`https://web-server.itheima.net/emps/list?name=${emp.value.name}&gender=${emp.value.gender}&job=${emp.value.job}`);
        empList.value = result.data.data;
    }
    const clear = () => {
        emp.value = {name:'',gender:'',job:''};
        search();
    }

    onMounted(() => {
        // 页面加载时自动查询
        search();
    });

    const empList = ref([]);
        
</script>

<template>
    <div id = "container">
        <el-form :inline="true" :model="emp" class="demo-form-inline">
        <el-form-item label="姓名">
        <el-input v-model="emp.name" placeholder="请输入姓名" clearable />
        </el-form-item>

        <el-form-item label="性别">
        <el-select
            v-model="emp.gender"
            placeholder="请选择性别"
            clearable
        >
            <el-option label="男" value="1" />
            <el-option label="女" value="2" />
        </el-select>
        </el-form-item>

        <el-form-item label="职位">
            <el-select
            v-model="emp.job"
            placeholder="请选择"
            clearable
        >
            <el-option label="班主任" value="1" />
            <el-option label="讲师" value="2" />
        </el-select>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
        </el-form-item>
        </el-form>

        <el-table :data="empList" stripe style="width: 80%">
            <el-table-column prop="id" label="ID" width="180" align="center"/>
            <el-table-column prop="name" label="姓名" width="180" align="center"/>
            <el-table-column label="头像" align="center">
                <template #default="scope">
                    <img :src="scope.row.image" style="width: 50px; height: 50px; border-radius: 50%;"/>
                </template>
            </el-table-column>
            <el-table-column prop="gender" label="性别" align="center">
                <template #default="scope">
                    {{ scope.row.gender == 1 ? '男' : '女' }}
                </template>
            </el-table-column>
            <el-table-column prop="job" label="职位" align="center"/>
            <el-table-column prop="entrydate" label="入职日期" align="center"/>
            <el-table-column prop="updatetime" label="更新时间" align="center"/>
        </el-table>
    </div>
  
</template>

<style scoped>
   #container{
        width: 80%;
        margin-left: 15%;
        margin-right: 15%;
   }
</style>
