<template>
  <div id="app">
     <Movie v-for="movie in movies" :key="movie.id"
            :title="movie.title"
            :rating="movie.rating"
     ></Movie>
    <Hello></Hello>
  </div>
</template>

<script>
import Movie from './components/Movie.vue'
import Hello from './components/Hello'
import axios from "axios";
export default {
  name: 'App',
  components: {
    Movie,
    Hello
  },
  data(){
    return {
      movies:[
        {id:1,title:"经过1",rating:8.9},
        {id:2,title:"经过2",rating:8.1},
        {id:3,title:"经过3",rating:8.2},
      ]
    }
  },
  created() {
    // console.log("App组件被创建")
    //注意点：这里前后端的端口号不同，存在跨域问题。所以链接要写明端口号
    // axios.get("http://localhost:9090/user/findAll").then(
    //     function (response){//response接收后端返回的JSON数据
    //       console.log(response)
    //     }
    // )
  },
  mounted() {
    console.log("App挂载完毕")
  }
}
</script>

<style>
</style>
