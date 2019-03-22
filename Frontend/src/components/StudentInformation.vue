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
            <h2>Co-Op-Erator</h2>
          </div>
          <div class="col-sm-4">
            <br>

            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>
              Account
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid text-center" id="background">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <br>
          <div class="container-fluid">
            <div class="container text-left" id="welcome">
              <h2>
                <font>Student Information</font>
              </h2>
            </div>
            <hr>
          </div>

          <div class="panel panel-default text-center">
            <div class="panel-body">
              <br><br>
              <div class="form-group">
                <form class="form-inline" action="/action_page.php">
                  <label for="email2" class="mb-2 mr-sm-2">First Name:  {{student.firstName}}</label>
                </form>
                <br>
                <form class="form-inline" action="/action_page.php">
                  <label for="email2" class="mb-2 mr-sm-2">Last Name: {{student.lastName}}</label>
                </form>
                <br>
                 <form class="form-inline" action="/action_page.php">
                  <label for="email2" class="mb-2 mr-sm-2">Email: {{student.mcgillEmail}}</label>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-2 sidenav"></div>
    </div>
  </body>

  </html>
</template>

<style>
  .panel {
    min-height: 80%;
    min-width: 100%;
  }

  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h2 {
    text-align: left;
  }

  #welcome {
    max-width: 100%;
    margin-top: 0px;
  }

  #welcome h2 {
    text-align: center;
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 37px
  }

  #welcome h3 {
    text-align: center;
    margin-top: 5px;
    margin-bottom: 0px;
    font-size: 25px;
    font-family: Lucida;
    font-style: oblique;
    color: #333335;
  }
</style>

<script>
 import axios from 'axios'
  var config = require('../../config')

  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
  })

  export default {
    data(){
        return {
        student: null
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
    methods: {
    }
  }
</script>
