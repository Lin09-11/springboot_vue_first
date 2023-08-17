<template>
  <div id="app">
    <img alt="Vue logo" :src="img">
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'App',
  components: {
  },
  data(){
    return{
      img:''
    }
  },
  mounted() {
    axios.get("/product/search").then(res=>{
      //这个res的MockJS返回的数据
      console.log(res)
      console.log(res.data.data.img);
      this.img=res.data.data.img
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
