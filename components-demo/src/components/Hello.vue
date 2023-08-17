<template>
<div>
  <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName">
    <el-table-column
        prop="id"
        label="编号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="username"
        label="姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="birthday"
        label="生日">
    </el-table-column>
  </el-table>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "Hello",
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    }
  },
  created() {
    // console.log("App组件被创建")
    //注意点：这里前后端的端口号不同，存在跨域问题。所以链接要写明端口号
    // axios.get("/user/findAll").then(//箭头函数作用域继承了父级
    this.$http.get("/user/findAll").then(
        //注意点：这里要使用箭头函数，因为this要继承父类
        (response)=>{//response接收后端返回的JSON数据
          console.log(response)
          this.tableData=response.data
        }
    )
  },
}
</script>

<style scoped>

</style>