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
            <h2>Co-Op-Erator {{this.$route.params.studentID}} {{student != null?student.firstName: '-'}}</h2>
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

    <nav class="navbar navbar">
      <div class="container-fluid">
        <div class="navbar-header">
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav nav-tabs">
            <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab = tab">
              <a>
                <font size="4">{{ tab }}</font>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
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

    <div v-if="selectedTab === 'Tasks'" class="container">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td colspan="2">Larry the Bird</td>
            <td>@twitter</td>
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
  import axios from 'axios'
  var config = require('../../config')

  var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
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
        tabs: ['Overview', 'Tasks', 'Information'],
        selectedTab: 'Overview',
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
        tasks: [{
            "taskStatus": "COMPLETED",
            "description": "Submit the CO-OP position acceptance form.",
            "dueDate": "2019-03-21",
            "name": "Report CO-OP Position Acceptance",
            "_links": {
              "self": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11830"
              },
              "task": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11830"
              },
              "documents": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11830/documents"
              }
            }
          },
          {
            "taskStatus": "INCOMPLETE",
            "description": "Submit the final evaluation report for the internship experience.",
            "dueDate": "2019-07-21",
            "name": "Internship Evaluation Report",
            "_links": {
              "self": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11836"
              },
              "task": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11836"
              },
              "documents": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11836/documents"
              }
            }
          },
          {
            "taskStatus": "INCOMPLETE",
            "description": "Submit the term technical report about the internship experience.",
            "dueDate": "2019-07-21",
            "name": "Technical Experience Report",
            "_links": {
              "self": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11835"
              },
              "task": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11835"
              },
              "documents": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11835/documents"
              }
            }
          },
          {
            "taskStatus": "INCOMPLETE",
            "description": "Submit an initial report of the tasks and workload of the internship.",
            "dueDate": "2019-04-04",
            "name": "Initial Workload Report",
            "_links": {
              "self": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11834"
              },
              "task": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11834"
              },
              "documents": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11834/documents"
              }
            }
          },
          {
            "taskStatus": "COMPLETED",
            "description": "Submit the employer contract document.",
            "dueDate": "2019-03-21",
            "name": "Upload Employer Contract",
            "_links": {
              "self": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11832"
              },
              "task": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11832"
              },
              "documents": {
                "href": "http://cooperator-backend-0000.herokuapp.com/tasks/11832/documents"
              }
            }
          }
        ]
      }
    },
    created() {
      AXIOS.get(`/students/` + this.$route.params.studentID)
        .then(response => {
          this.student = response.data
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
    },
    methods: {}
  }
</script>
