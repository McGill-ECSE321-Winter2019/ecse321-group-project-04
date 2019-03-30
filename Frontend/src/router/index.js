import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/Login'
import CourseInfo from '@/components/CourseInfo'
import AcceptanceForm from '@/components/AcceptanceForm'
import StudentInformation from '@/components/StudentInformation'
import HomePage from '@/components/HomePage'
import TaskView from '@/components/TaskView'
import SampleModal from '@/components/SampleModal'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      redirect: '/login'
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
      path: '/courseinfo/:id',
      name: 'CourseInfo',
      component: CourseInfo
    },
    {
      path: '/acceptanceform/:id',
      name: 'AcceptanceForm',
      component: AcceptanceForm
    },
    {
      path: '/studentinformation/:id',
      name: 'StudentInformation',
      component: StudentInformation
    },
    {
      path: '/home/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/taskview/:enrollmentID/:id',
      name: 'TaskView',
      component: TaskView
    }
  ]
})
