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
          <div class="col-sm-8">
            <h2>Co-Op-Erator</h2>
          </div>
          <div class="col-sm-4">
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" id="account-btn" data-toggle="dropdown">
                <span class="glyphicon glyphicon-user"></span>
                Account
              </button>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Account Information</a></li>
                <li><a href="#">Log Out</a></li>
              </ul>
            </div>
          </div>

        </div>
      </div>
    </div>

    <div class="container-fluid" id="course-title">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-12">
            <!-- The course name here on line 36 has to be changed to the real one that link to the dashboard -->

            <h2>Winter 2019 - FACC 201</h2>
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

    <div v-if="selectedTab === 'Overview'" class="container" id="overview-container">
      <div class="row">
        <div class="col-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:20px;">Progress</h3>
            </div>
          </div>
        </div>

        <div class="col-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Upcoming Deadlines</h3>
              <h4><a href="#">FACC 201 - Term Report</a></h4>
              <h4>Due Tomorrow</h4>
              <hr>
              <h4><a href="#">FACC 202 - Employer Evaluation Report</a></h4>
              <h4>Due In 3 Days</h4>
              <hr>
              <h4><a href="#">FACC 202 - Term Report</a></h4>
              <h4>Due In 5 Days</h4>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="selectedTab === 'Submissions'" class="container">
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
          <tr v-for="task in tasks">
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

  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }

  #course-title h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 15px;
  }

  nav {
    margin-top: 15px;
  }
</style>

<script>
  import Router from 'vue-router'
  import axios from 'axios'
  var config = require('../../config')

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
    var vals = dateString.split('-')
    return {
      year: vals[0],
      month: vals[1],
      day: vals[2]
    }
  }

  function compareDates(d1, d2) {
    // Compare the years
    if (d1.year > d2.year) {
      return 1
    } else if (d1.year < d2.year) {
      return -1
    }

    // If equal compare the months
    if (d1.month > d2.month) {
      return 1
    } else if (d1.month < d2.month) {
      return -1
    }

    // If equal compare the days
    if (d1.day > d2.day) {
      return 1
    } else if (d1.day < d2.day) {
      return -1
    }

    // Else they are the same
    return 0
  }

  /* AXIOS object configuration */
  var frontendUrl = 'http://' + config.dev.hoiust + ':' + config.dev.port
  var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: {
      'Access-Control-Allow-Origin': frontendUrl
    }
  })
  export default {
    name: 'courseinfo',
    data() {
      // TODO: Replace course offering and tasks with real REST calls
      return {
        tabs: ['Overview', 'Submissions'],
        selectedTab: 'Overview',
        taskStatusDisplay: {
          COMPLETED: 'Completed',
          INCOMPLETE: 'Incomplete',
          LATE_SUBMITTED: 'Completed Late'
        },
        courseOffering: {
          "term": "WINTER",
          "year": 2019,
          "active": true,
          "_links": {
            "self": {
              "href": "http://cooperator-backend-0000.herokuapp.com/coopCourseOfferings/ECSE-321-W19"
            },
            "coopCourseOffering": {
              "href": "http://cooperator-backend-0000.herokuapp.com/coopCourseOfferings/ECSE-321-W19"
            },
            "studentEnrollments": {
              "href": "http://cooperator-backend-0000.herokuapp.com/coopCourseOfferings/ECSE-321-W19/studentEnrollments"
            },
            "coopCourse": {
              "href": "http://cooperator-backend-0000.herokuapp.com/coopCourseOfferings/ECSE-321-W19/coopCourse"
            }
          }
        },
        task:{}
      }
    },
    created() {
      // Convert all the dates to date objects
      for (var task in this.tasks) {
        task.dueDate = parseDate(task.dueDate)
      }
    },
    methods: {
      goToTask: function(task) {
        var taskURL = task._links.task.href.split('/')
        var taskID = taskURL[taskURL.length - 1]
        console.log(taskID)
        this.$router.push({
          name: 'Taskview',
          params: {
            id: taskID
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
    }
  }
</script>
