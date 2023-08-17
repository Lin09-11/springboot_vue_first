<template>
<div>
  <h1>{{title}}</h1>
  <span>{{rating}}</span>
  <button @click="fun">点击输出</button>
</div>
</template>
<script>
export default{
  name:"Movie",
  data(){
      return {
        
      }
    },
    //自定义属性,将值向外暴露
    props:["title","rating"],
  methods:{
    fun(){
      alert("收藏成功")
    }
  },
  created() {
    console.log("Movie组件被创建")
  }
}
</script>
<style scoped>

</style>