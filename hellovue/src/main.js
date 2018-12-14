// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import Filter from './js/filter';
import  util  from   './js/util.class';
import commonGrid from  '@/components/commonGrid';
import commonOpenWin from  '@/components/commonOpenWin';
Vue.component("com-win",commonOpenWin)
//公共表格组件
Vue.component("se-com-grid",commonGrid)
//如果不在这里声明为全局方法  也可以在指定的组件中import
Vue.prototype.$util = util

Vue.prototype.detailLink= function (val) {
  return "<a href='javascript:void(0);'>"+val+"</a>";
};

Vue.use(VueAxios, axios)
Vue.use(Element)
//全局过滤器
Object.keys(Filter).forEach(key => {
  Vue.filter(key, Filter[key])
})


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
