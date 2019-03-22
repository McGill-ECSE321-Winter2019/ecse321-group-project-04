import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/Login'
import CourseInformation from '@/components/CourseInformation'
import AcceptanceForm from '@/components/AcceptanceForm'
import StudentInformation from '@/components/StudentInformation'
import TaskView from '@/components/TaskView'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/dashboard/:id',
      name: 'Dashboard',
      component: Dashboard
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/courseinformation',
      name: 'CourseInformation',
      component: CourseInformation
    },
    {
      path: '/acceptanceform',
      name: 'AcceptanceForm',
      component: AcceptanceForm
    },
    {
	path: '/studentinformation',
	name: 'StudentInformation',
	component: StudentInformation
     },
     {
      path: '/taskview',
      name: 'TaskView',
      component: TaskView
    }
  ]
})
