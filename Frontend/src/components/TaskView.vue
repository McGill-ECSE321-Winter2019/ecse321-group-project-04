<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css"
      integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
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
          <div class="col-sm-1 sidenav"></div>
          <div class="col-sm-10">
            <!-- Change Task name to the actual -->
            <h1>Task name</h1>
            <hr>
          </div>
          <div class="col-sm-1 sidenav"></div>
        </div>
      </div>
    </div>
    <br>

    <div class="task-card">

      <div class="row">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8">
          <h3>
            Task Description
          </h3>
          <h4 class="task-description">
            Reports are due on the Friday of the week following the lab session. Only one report per group of two
            students should be submitted. Make sure to put the name and student numbers of both group members on the
            report,
            otherwise grades will not be assigned. Students are responsible for all content in the reports.</h4>
          <h3>
            Due date
          </h3>
          <h4 class="task-description"> 2019-03-21 </h4>
          <br><br>
        </div>
        <div class="col-sm-2 sidenav"></div>
      </div>
    </div>

    <br />
    <br />

    <div class="container-fluid" id="task-submission">

      <div class="row">
        <div class="col-sm-1 sidenav"></div>
        <div class="col-sm-10">
          <ul class="nav nav-tabs">
            <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab = tab">
              <a>
                <font size="4">{{ tab }}</font>
              </a>
            </li>
          </ul>
        </div>
        <div class="col-sm-1 sidenav"></div>
      </div>

      <br>

      <div v-if="selectedTab === 'Submit Document'" class="container-fluid" id="Submit Document-container">
        <div class="row">
          <div class="col-sm-1 sidenav"></div>
          <div class="col-sm-10">
            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:20px;">Attach Document(s)</h3>

                  <button @click="showModal=true" type="button" class="btn btn-primary">
                    Add File(s)
                  </button>

                  <!-- Modal -->
                  <div v-if="showModal">
                    <transition name="modal">
                      <div class="modal-mask">

                        <div class="modal-wrapper" @click="showModal=false">
                          <div class="modal-container" @click.stop>

                            <div class="modal-header">
                              <slot name="header">
                                File Name
                              </slot>
                            </div>

                            <div class="modal-body">
                              <slot name="body">
                                <div id="upload">
                                  <div v-if="!file">
                                    <input type="file" @change="onFileChange">
                                  </div>
                                  <div v-else>
                                    <button @click="removeFile">Remove File</button>
                                    <span>{{file}}</span>
                                  </div>
                                </div>
                              </slot>
                            </div>

                            <div class="modal-footer">
                              <slot name="footer">
                                <button class="btn btn-primary btn-sm" @click="showModal=false">
                                  OK
                                </button>
                              </slot>
                            </div>
                          </div>
                        </div>
                      </div>
                    </transition>

                  </div>

                </div>
              </div>
            </div>

            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Information</h3>
                </div>
              </div>
            </div>
          </div>
          <div class="col-sm-1 sidenav"></div>
        </div>
      </div>

      <div v-if="selectedTab === 'Submission History'" class="container-fluid">
        <div class="row">
          <div class="col-sm-1 sidenav"></div>
          <div class="col-sm-10">
            <table class="table table-striped table-bordered">
              <thead>
                <tr>
                  <th scope="col" style="text-align:center; vertical-align:middle">
                    <h4>Submission(s)</h4>
                  </th>
                  <th scope="col" style="text-align:center; vertical-align:middle">
                    <h4>Date submitted</h4>
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="submisson in submissions">
                  <td style="text-align:center; vertical-align:middle">
                    <!-- the name of file submitted should be displayed -->
                    <h5>allo</h5>
                  </td>
                  <td style="text-align:center; vertical-align:middle">
                    <h5>{{ displayDate(submisson.dueDate) }}</h5>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="col-sm-1 sidenav"></div>

        </div>
        </div>
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

  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }

  #logo-link {
    margin-bottom: 0px;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #course-title h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 15px;
  }

  #nav-bar {
    min-width: 100%;
  }

  .task-card {
    box-shadow: 0 6px 12px 0 rgba(0, 0, 0, 0.2);
    width: 66%;
    height: auto;
    margin: auto;
    text-align: left;
  }

  .task-description {
    font-style: italic;
    font-weight: 400;
  }

  #task-submission {
    width: 80%;
  }

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 400px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header {
    margin-top: 0;
    font-size: 24px;
  }

  .modal-body {
    margin: 0;
  }

  .modal-default-button {
    float: right;
  }

  /*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
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
    baseURL: backendUrl
  })
  export default {
    name: 'courseinfo',
    data() {
      return {
        showModal: false,
        file: '',
        tabs: ['Submit Document', 'Submission History'],
        selectedTab: 'Submit Document',
        submissions: [{
            "dueDate": "2019-03-21",
            "name": "Report CO-OP Position Acceptance"
          },
          {
            "dueDate": "2019-07-21",
            "name": "Internship Evaluation Report"
          },
          {
            "dueDate": "2019-07-21",
            "name": "Technical Experience Report"
          }
        ]
      }
    },

    /*data() {
      // TODO: Replace course offering and tasks with real REST calls
      return {
        tabs: ['Submit Document', 'Submission History'],
        selectedTab: 'Submit Document',
        submissions: [{
            "dueDate": "2019-03-21",
            "name": "Report CO-OP Position Acceptance"
          },
          {
            "dueDate": "2019-07-21",
            "name": "Internship Evaluation Report"
          },
          {
            "dueDate": "2019-07-21",
            "name": "Technical Experience Report"
          }
        ]
      }
    },*/

    created() {
      // Convert all the dates to date objects
      for (var submission in this.submissions) {
        submission.dueDate = parseDate(submisson.dueDate)
      }
    },

    methods: {
      onFileChange(e) {
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length)
          return;
        this.file = files[0].name
      },
      removeFile: function (e) {
        this.file = '';
      },
      displayDate: function (d) {
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
      },
      goToDashboard: function () {
        this.$router.push({
          name: 'Dashboard',
          params: {
            id: this.$route.params.id
          }
        })
      },
      goToLogin: function () {
        this.$router.push({
          name: 'Login',
        })
      },
      goToAccount: function () {
        this.$router.push({
          name: 'StudentInformation',
          params: {
            id: this.$route.params.id
          }
        })
      }
    }
  }

</script>
