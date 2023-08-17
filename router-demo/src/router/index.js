import Vue from "vue";
import VueRouter from "vue-router";
import Discover from '../components/Discover'
import Friends from '../components/Friends'
import My from '../components/My'
import TopList from "@/components/TopList";
import PlayList from "@/components/PlayList";
import Product from "@/components/Product";

//将VueRouter 设置为Vue的插件
Vue.use(VueRouter)

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
        {path:'/my',component:My,children:[
                {path: ':id',component: Product},
            ]}
    ]
})
export default router;