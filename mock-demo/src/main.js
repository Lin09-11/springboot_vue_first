import Vue from 'vue'
import App from './App.vue'
//导入
import './mock/index'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
