import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/Login'
import CourseInfo from '@/components/CourseInfo'
import AcceptanceForm from '@/components/AcceptanceForm'
<<<<<<< HEAD
import StudentInformation from '@/components/StudentInformation'
=======
import TaskView from '@/components/TaskView'
>>>>>>> a3ddf39d05357bfc2380d330e533c504214e0ac1

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
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
<<<<<<< HEAD
        path: '/acceptanceform',
        name: 'AcceptanceForm',
        component: AcceptanceForm
    },
    {
	path: '/studentInformation',
	name: 'StudentInformation',
	component: StudentInformation
=======
      path: '/taskview',
      name: 'TaskView',
      component: TaskView
>>>>>>> a3ddf39d05357bfc2380d330e533c504214e0ac1
    }
  ]
})
