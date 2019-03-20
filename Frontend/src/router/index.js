import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Registration from '@/components/Registration'
import Report from '@/components/Report'
import Progress from '@/components/progress'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/Registration',
      name: 'Registration',
      component: Registration
    },
    {
      path: '/Report',
      name: Report,
      component: Report
    },
    {
      path: 'Progress',
      name: Progress,
      component: Progress
    }
  ]
})
