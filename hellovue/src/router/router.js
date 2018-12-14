import Vue from 'vue'
import Router from 'vue-router'
import common from '@/components/common'
import login from   '@/views/login'
import org from '@/views/org'
import storeList from '@/views/storeList'
import news from '@/views/news'
import msgDemo from  '@/views/msgDemo'
import workFlow from  '@/views/workFlow'
import oklog from  '@/views/oklog'
import errlog from  '@/views/errlog'
import commonAlertBox from '@/components/commonAlertBox'
Vue.use(Router)


const router =new Router({
  routes: [
    {
      path: '/',
      name: 'common',
      redirect: '/msgDemo',
      component: common,
      children:[
        {path: '/msgDemo', name: 'msgDemo', component: msgDemo},
        {path: '/oklog', name: 'oklog', component: oklog},
        {path: '/workFlow', name: 'workFlow', component: workFlow},
        {path: '/storeList', name: 'storeList', component: storeList},
        {path: '/org', name: 'org', component: org,
          children:[
            { path: '/news', name: 'news', component: news},
          ]
        },
        {path: '/errlog', name: 'errlog', component: errlog},
      ]
    },
    {path: '/login', name: 'login', component: login},
  ]
})

export default router
