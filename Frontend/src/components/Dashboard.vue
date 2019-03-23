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
          <div class="col-sm-8">
            <h2>
              <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="240" height="80">
            </h2>
          </div>
          <div class="col-sm-4">
            <button type="button" class="btn btn-primary dropdown-toggle" @click="goToAccount" style="margin-top:55px">
              <span class="glyphicon glyphicon-user"></span>
              Account
            </button>
          </div>

        </div>
      </div>
    </div>

    <div class="container text-center">
      <div class="container-fluid" id="nav-bar">
        <div class="row">
          <div class="col-sm-9">
            <ul class="nav nav-tabs">
              <li v-for="tab in tabs" :class="selectedTab == tab ? 'active' : ''" @click="selectedTab = tab"><a href="#">{{tab}}</a></li>
            </ul>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li><button type="button" class="btn btn-success" href="#">Register Course</button></li>
          </ul>
        </div>
      </div>
    </div>

    <transition name="slide-fade" mode="out-in">
      <div v-if="selectedTab === 'Active Courses'" key="active" class="container" id="course-list">
        <div v-for="enrollment in enrollments" v-if="enrollment.status === 'ONGOING'" class="row">
          <div class="col-sm-12">
            <div class="panel panel-default">
              <div class="panel-body" style="margin-left:10px; text-align:center"><a href="#">{{getEnrollmentID(enrollment)}}</a></div>
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

    </div>

    <br>
    <br>

  </body>

  </html>
</template>

<style>
  #top-container {
    margin-bottom: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 20px;
  }

  #account-btn {
    margin-top: 30px;
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

  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .3s ease;
  }

  .slide-fade-enter,
  .slide-fade-leave-to
  /* .slide-fade-leave-active below version 2.1.8 */
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
    /*headers: {
      'Access-Control-Allow-Origin': frontendUrl
    }*/
  })

  export default {
    name: 'dashboard',
    data() {
      return {
        tabs: ['Active Courses', 'Archieved Courses'],
        selectedTab: 'Active Courses',
        student: null,
        enrollments: null
      }
    },
    created() {
      AXIOS.get(`/students/` + this.$route.params.id)
        .then(response => {
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
      console.log(this.enrollments)
    },
    methods: {
      goToAccount: function() {
        this.$router.push({
          name: 'StudentInformation',
          params: {
            id: this.$route.params.id
          }
        })
      },
      getEnrollmentID: function(enrollment) {
        var tmp = enrollment._links.self.href.split('/')
        tmp = tmp[tmp.length - 1]

        var offeringCode = tmp.split('-')
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
