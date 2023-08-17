import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'

axios.defaults.baseURL="http://localhost:9090"
//将axios挂载带vue身上
Vue.prototype.$http=axios;

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  render: h => h(App),
}).$mount('#app')
