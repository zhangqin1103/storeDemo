/*
import Vue from 'vue';
import globalAlertBox from '../components/commonAlertBox'
const myAlert = {};
// 注册
myAlert.install = function () {
  const VueComponent = Vue.extend(globalAlertBox);
  const vm = new VueComponent().$mount();
  document.body.appendChild(vm.$el)
  Vue.prototype.$myAlert = (options) => {
    vm.show = true
    vm.message = options.message
    if(options.description){
      vm.description = options.description
    };
    if(options.type){
      vm.type = options.type;
    };
    if(options.closable!==undefined){
      vm.closable = options.closable;
    }
    if(options.showIcon!==undefined){
      vm.showIcon = options.showIcon;
    }
    vm.callBack =() => {
      vm.show = false;
      if(options.callBack){
        options.callBack();
      }
    }
  }
}

export default myAlert
*/
