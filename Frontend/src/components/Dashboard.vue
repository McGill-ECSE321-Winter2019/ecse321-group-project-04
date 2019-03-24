<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  </head>

  <body>

    <div class="container-fluid" id="top-container">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-6">
            <div class="container  text-left">
              <div @click="goToDashboard" style="display: inline-block;">
                <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="300" height="100">
              </div>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="row">
              <div class="col-sm-12">
                <button type="button" class="btn btn-primary" @click="goToAccount" id="Account-but" style="min-width: 100px; margin-right: 0px; margin-top: 35px">
                  <span class="glyphicon glyphicon-user"></span>
                  Account
                </button>
                <button type="button" class="btn btn-danger" @click="goToLogin" id="Logout-but" style="min-width: 100px; margin-left: 5px; margin-top: 35px">
                  Logout
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid" id="nav-bar">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-9">
            <ul class="nav nav-tabs">
              <li v-for="tab in tabs" :class="selectedTab == tab ? 'active' : ''" @click="selectedTab = tab">
                <a>
                  {{tab}}
                </a>
              </li>
            </ul>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li><button @click="goToAcceptanceForm" type="button" class="btn btn-success" href="#">Register Course</button></li>
          </ul>
        </div>
      </div>
    </div>

    <transition name="slide-fade" mode="out-in" appear>
      <div v-if="selectedTab === 'Active Courses'" key="active" class="container" id="course-list">
        <div v-for="enrollment in enrollments" v-if="enrollment.status === 'ONGOING'" class="row">
          <div class="col-sm-12">
            <div class="panel panel-default">
              <div class="panel-body" style="margin-left:10px; text-align:center"><a href="#" @click="goToCourseview(enrollment)">{{getEnrollmentName(enrollment)}}</a></div>
            </div>
          </div>
        </div>
      </div>

      <div v-else="selectedTab === 'Archieved Courses'" key="archieved" class="container" id="course-list">
        <div v-for="enrollment in enrollments" v-if="enrollment.status !== 'ONGOING'" class="row">
          <div class="col-sm-12">
            <div class="panel panel-default">
              <div class="panel-body" style="margin-left:10px; text-align:center"><a href="#">{{getEnrollmentID(enrollment)}}</a></div>
            </div>
          </div>
        </div>
      </div>
    </transition>

    <br>
    <br>

  </body>

  </html>
</template>

<style>
  #top-container {
    margin-bottom: 0%;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 20px;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #nav-bar {
    margin-top: 25px;
    margin-bottom: 25px;
  }

  #course-list .panel-body {
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: large;
  }

  /* Tab transition animations */
  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .3s ease;
  }

  .slide-fade-enter,
  .slide-fade-leave-to
    {
    transform: translateX(10px);
    opacity: 0;
  }
</style>

<script>
  import axios from 'axios'
  var config = require('../../config')

  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl
  })

  export default {
    name: 'dashboard',
    data() {
      return {
        tabs: ['Active Courses', 'Archieved Courses'],
        selectedTab: 'Active Courses',
        enrollments: [],
        student: null
      }
    },
    created() {
      AXIOS.get(`/students/` + this.$route.params.id)
        .then(response => {
          //console.log(response.data)
          this.student = response.data
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
      AXIOS.get(`/students/` + this.$route.params.id + `/courseEnrollments`)
        .then(response => {
          console.log(response.data)
          this.enrollments = response.data._embedded.studentEnrollments
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
    },
    methods: {
      goToDashboard: function() {
        this.$router.push({
          name: 'Dashboard',
          params: {
            id: this.$route.params.id
          }
        })
      },
      goToAcceptanceForm: function() {
        this.$router.push({
          name: 'AcceptanceForm',
          params: {
            id: this.$route.params.id
          }
        })
      },
      goToAccount: function() {
        this.$router.push({
          name: 'StudentInformation',
          params: {
            id: this.$route.params.id
          }
        })
      },
      goToLogin: function() {
        this.$router.push({
          name: 'Login',
        })
      },
      goToCourseview: function(enrollment) {
        var enrollmentID = enrollment._links.self.href.split('/').pop()
        this.$router.push({
          name: 'CourseInfo',
          params: {
            id: enrollmentID
          }
        })
      },
      getEnrollmentName: function(enrollment) {
        var enrollmentCode = enrollment._links.self.href.split('/')
        enrollmentCode = enrollmentCode[enrollmentCode.length - 1]
        var offeringCode = enrollmentCode.split('-')
        offeringCode.shift()
        var term = offeringCode.pop()
        var courseCode = offeringCode.join('')
        var displayName = null
        switch (term[0]) {
          case 'W':
            displayName = 'Winter '
            break;
          case 'F':
            displayName = 'Fall '
            break;
          case 'S':
            displayName = 'Summer '
            break;
        }
        displayName += '20' + term.slice(1) + ' - ' + courseCode

        return displayName
      }
    }
  }
</script>
