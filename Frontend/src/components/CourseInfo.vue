<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css" integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
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

    <div class="container-fluid" id="course-title">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-12">
            <h1>{{ enrollmentName }}</h1>
          </div>
        </div>
      </div>
    </div>

    <div class="container text-center">
      <div class="container-fluid" id="nav-bar">
        <div class="row">
          <div class="col-sm-12">
            <ul class="nav nav-tabs">
              <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab = tab">
                <a>
                  <font size="4">{{ tab }}</font>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <br>

    <transition name="slide-fade" mode="out-in" appear>
      <div v-if="selectedTab === 'Overview'" key="overview" class="container" id="overview-container">
        <div class="row">
          <div class="col-sm-6">
            <div class="card border-inverse mb-3">
              <div class="card-body">
                <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Progress</h3>
              </div>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="card border-inverse mb-3">
              <div class="card-body">
                <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Upcoming Deadlines</h3>
                <div v-for="task in upcomingDeadlines">
                  <h4><a href="#" @click="goToTask(task.task)">{{task.task.name}}</a></h4>
                  <h4>{{task.dueTime}}</h4>
                  <hr>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="selectedTab === 'Tasks'" key="tasks" class="container">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Task</h4>
              </th>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Completion Status</h4>
              </th>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Due Date</h4>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="task in pTasks">
              <td>
                <h4><a href="#" @click="goToTask(task)">{{task.name}}</a></h4>
                <h5 style="color:gray">{{task.description}}</h5>
              </td>
              <td style="text-align:center; vertical-align:middle">
                <h5 :style="task.taskStatus == 'COMPLETED' ? 'color:green' : 'color:'">{{taskStatusDisplay[task.taskStatus]}}</h5>
              </td>
              <td style="text-align:center; vertical-align:middle">
                {{ displayDate(task.dueDate) }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div v-if="selectedTab === 'Information'" class="container">
        <div class="container">
          <div class="row">
            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">
                    Course Information
                  </h3>
                  <h4 style="color:gray"><em>Course ID</em></h4>
                  <h4><b>{{courseID}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Course Offering</em></h4>
                  <h4><b>{{courseOffering}}</b></h4>
                </div>
              </div>
            </div>

            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">
                    Employer Information
                  </h3>
                  <h4 style="color:gray"><em>Company Name</em></h4>
                  <h4><b>{{employerInfo.name}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Company Address</em></h4>
                  <h4><b>{{employerInfo.address}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Employer Email Address</em></h4>
                  <h4><b>{{employerInfo.email}}</b></h4>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div><br>

      </div><br>
    </transition>

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

  #logo-button {
    color: #333335;
    background: #333335;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }

  #course-title h1 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 40px;
  }

  #nav-bar {
    margin-top: 25px;
    margin-bottom: 40px;
  }

  /* Tab transition animations */
  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .3s ease;
  }

  .slide-fade-enter,
  .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>

<script>
  import Router from 'vue-router'
  import axios from 'axios'
  var config = require('../../config')
  var moment = require('moment')

  /* Date handling */
  var months = {
    1: 'Jan',
    2: 'Feb',
    3: 'Mar',
    4: 'Apr',
    5: 'May',
    6: 'Jun',
    7: 'Jul',
    8: 'Aug',
    9: 'Sep',
    10: 'Oct',
    11: 'Nov',
    12: 'Dec'
  }

  function parseDate(dateString) {
    var valsString = dateString.split('-');
    var vals = valsString.map(function(n) {
      return parseInt(n, 10);
    })
    var ret = {
      year: vals[0],
      month: vals[1],
      day: vals[2]
    };
    return ret;
  }

  function compareDates(d1, d2) {
    // Compare the years
    if (d1.year > d2.year) {
      return 1;
    } else if (d1.year < d2.year) {
      return -1;
    }

    // If equal compare the months
    if (d1.month > d2.month) {
      return 1;
    } else if (d1.month < d2.month) {
      return -1;
    }

    // If equal compare the days
    if (d1.day > d2.day) {
      return 1;
    } else if (d1.day < d2.day) {
      return -1;
    }
    // Else they are the same
    return 0;
  }

  /* AXIOS object configuration */
  var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'http://' + config.dev.backendHost // + ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl
  })

  export default {
    name: 'courseinfo',
    data() {
      return {
        tabs: ['Overview', 'Tasks', 'Information'],
        selectedTab: 'Overview',
        taskStatusDisplay: {
          COMPLETED: 'Completed',
          INCOMPLETE: 'Incomplete',
          LATE_SUBMITTED: 'Completed Late'
        },
        courseOffering: {},
        tasks: [],
        enrollmentName: null,
        courseID: null,
        courseOffering: null,
        employerInfo: null
      }
    },
    created() {
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id)
        .then(response => {
          this.enrollment = response.data

          // Get the enrollment name
          var enrollmentCode = this.enrollment._links.self.href.split('/')
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

          this.enrollmentName = displayName
          this.courseID = displayName.split('-').pop().trim()
          this.courseOffering = displayName.split('-').shift().trim()
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id + `/courseTasks`)
        .then(response => {
          this.tasks = response.data._embedded.tasks;
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        })
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id + `/studentEmployer`)
        .then(response => {
          var employer = response.data;
          this.employerInfo = {
            name: employer.name,
            address: employer.address,
            email: employer._links.self.href.split('/').pop()
          }
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        })

    },
    methods: {
      goToTask: function(task) {
        var enrollmentID = this.enrollment._links.self.href.split('/').pop()
        var taskID = task._links.self.href.split('/').pop()
        console.log(taskID)
        this.$router.push({
          name: 'TaskView',
          params: {
            'enrollmentID': enrollmentID,
            id: taskID
          }
        })
      },
      goToAccount: function() {
        var studentID = this.enrollment._links.self.href.split('/').pop().split('-').shift()
        this.$router.push({
          name: 'StudentInformation',
          params: {
            id: studentID
          }
        })
      },
      goToDashboard: function() {
        var studentID = this.enrollment._links.self.href.split('/').pop().split('-').shift()
        this.$router.push({
          name: 'Dashboard',
          params: {
            id: studentID
          }
        })
      },
      goToLogin: function() {
        this.$router.push({
          name: 'Login',
          params: {
            id: this.$route.params.id
          }
        })
      },
      displayDate: function(d) {
        d = parseDate(d)
        var display = months[parseInt(d.month)] + ' ' + parseInt(d.day)
        switch (d.day % 10) {
          case 1:
            display += 'st'
            break
          case 2:
            display += 'nd'
            break
          case 3:
            display += 'rd'
            break
          default:
            display += 'th'
            break
        }
        display += ', ' + d.year
        return display
      }
    },
    computed: {
      pTasks: function() {
        var pt = []
        for (var i in this.tasks) {
          pt.push(this.tasks[i])
        }
        pt.sort(function(a, b) {
          return compareDates(parseDate(a.dueDate), parseDate(b.dueDate))
        })
        return pt
      },
      upcomingDeadlines: function(pTasks) {
        var pTasks = []
        for (var i in this.tasks) {
          pTasks.push(this.tasks[i])
        }
        pTasks.sort(function(a, b) {
          return compareDates(parseDate(a.dueDate), parseDate(b.dueDate))
        })

        var ret = []
        var currDate = moment()
        for (var i in pTasks) {
          var dd = moment(pTasks[i].dueDate)
          var timeDiff = Math.floor(moment.duration(dd.diff(currDate)).asDays())
          if (timeDiff < 1) {
            ret.push({
              task: pTasks[i],
              dueTime: 'Due Today'
            })
          } else if (timeDiff < 2) {
            ret.push({
              task: pTasks[i],
              dueTime: 'Due Tomorrow'
            })
          } else if (timeDiff <= 10) {
            ret.push({
              task: pTasks[i],
              dueTime: 'Due In ' + timeDiff + ' Days'
            })
          }
        }
        return ret
      },
    }
  }
</script>
