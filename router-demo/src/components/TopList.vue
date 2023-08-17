<template>
<h1>TopList</h1>
  <button @click="gotoProduct(2)"></button>
</template>

<script>
export default {
  name: "TopList",
  methods:{
    gotoProduct(id){
      this.$router.push('/movie/${id}')
    }
  }
}
</script>

<style scoped>

</style>