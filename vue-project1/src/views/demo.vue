<script setup>
    //引入 ref和钩子函数
    import{ref, onMounted } from 'vue';

    //声明响应式数据
    const count = ref(0);

    //声明函数
    function increment() {
        count.value++;
    }
    
    //钩子函数
    onMounted(() => {
        console.log('组件已挂载');
    });

</script>

<template>
    <button @click="increment">count: {{ count }} </button>
</template>