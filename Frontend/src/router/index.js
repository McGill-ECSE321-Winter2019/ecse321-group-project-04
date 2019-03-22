import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/Login'
import CourseInfo from '@/components/CourseInfo'
import AcceptanceForm from '@/components/AcceptanceForm'
import Task from '@/components/Task'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/courseinfo',
        name: 'CourseInfo',
        component: CourseInfo
    },
    {
        path: '/acceptanceform',
        name: 'AcceptanceForm',
        component: AcceptanceForm
    },
    {
      path: '/task',
      name: 'Task',
      component: Task
    }
  ]
})
