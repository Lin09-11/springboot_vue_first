### 声明路由链接和占位标签

使用<router-link>标签来声明路由链接，并使用<router-view>标签来声明路由占位符
App.vue
```vue
<template>
  <div id="app">
    <!-- 声明路由链接 -->
    <router-link to="/discover">发现音乐</router-link>
    <router-link to="/my">我的音乐</router-link>
    <router-link to="/friend">关注</router-link>
    <!--    声明路由的占位标签-->
    <router-view></router-view>
  </div>
</template>
```
### 创建路由模块
在项目中创建index.js路由模块
```JavaScript
import Vue from "vue";
import VueRouter from "vue-router";
import Discover from '../components/Discover'
import Friends from '../components/Friends'
import My from '../components/My'

//将VueRouter 设置为Vue的插件
Vue.use(VueRouter)

const router=new VueRouter({
    //指定hash属性与组件的对应关系
    routes:[
        {path:'/discover',component:Discover},
        {path:'/friends',component:Friends},
        {path:'/my',component:My}
    ]
})
//导出
export default router;
```

### 在main.js中导入
```js
import Vue from 'vue'
import App from './App.vue'
//导入
import router from "@/router";

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
```

### 路由重定向
路由重定向指的是：用户在访问地址A的时候，强制用户跳转到地址C，从而展示特定的组件页面
通过路由规则的redirect属性，指定一个新的路由地址
router/index.js
```js
const router=new VueRouter({
    //指定hash属性与组件的对应关系
    routes:[
        //当用户访问/时，跳转到/discover
        {path:'/discover',redirect:Discover},
        {path:'/friends',component:Friends},
        {path:'/my',component:My}
    ]
})
```

### 嵌套路由
在Discover.vue组件中，声明tiplist和playlist的子路由以及子路由占位符
Discover.vue
```vue
<template>
  <div>
    <h1>发现音乐</h1>
<!--    子路由链接-->
    <router-link to="/discover/toplist">推荐</router-link>
    <router-link to="/discover/playlist">歌单</router-link>
    <hr>
    <router-view></router-view>
  </div>
</template>
```

router/index.js
```js
const router=new VueRouter({
    //指定hash属性与组件的对应关系
    routes:[
        //当用户访问/时，跳转到/discover
        {path:'/discover',redirect:Discover,
            //通过children属性，嵌套声明子路由
        children:[
            {path:'toplist',component:TopList},
            {path:'playlist',component:PlayList}
        ]},
        {path:'/friends',component:Friends},
        {path:'/my',component:My}
    ]
})

```


### 动态路由
```vue
<!--动态路由-->
  <router-link to="/my/1">商品1</router-link>
  <router-link to="/my/2">商品2</router-link>
  <router-link to="/my/3">商品3</router-link>
  <router-view></router-view>
```
```js
const router=new VueRouter({
    router:[
        {path:'/product/1',component:Product},
        {path:'/product/3',component:Product},
        {path:'/product/2',component:Product},
    ]
})
```
以上代码复用性差

使用动态获取
```js
{path:'/product/:id',component:Product}
```
Product.vue
```vue
<template>
<h1>商品{{$router.params.id}}</h1>
</template>
```

### 编程式导航
##### 声明式：<router-link :to="...">
##### 编程式:router.push(...)
test.vue
```vue
<template>
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
```


### 导航守卫
全局导航守卫拦截每一个路由规则，从而对每一个路由进行访问权限的控制
vue-router提供的导航守卫主要用来拦截导航，让它完成跳转或取消。
 to：Route：即将要进入的目标路由。
 from：Route：当前导航正要离开的路由。
 next：在守卫方法中如果声明了next形参，则必须调用 next() 函数，否则不允许用户访问任何一个路由
 直接放行：next()，强制其跳转到登录页面：next('/login')
```js
  router.beforeEach((to,from,next)=>{
    if(to.path==='/main' && !isAuthenticated){
      next('login')
    }else{
      next()
    }
  })
}
```
