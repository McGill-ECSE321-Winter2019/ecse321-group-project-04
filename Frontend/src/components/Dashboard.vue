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
                <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png"
                width="240" height="80">
               {{this.$route.params.id}} {{student != null?student.firstName: '-'}}</h2>
          </div>
          <div class="col-sm-4">
              <button type="button" class="btn btn-primary dropdown-toggle" @click="goToAccount" style="margin-top:30px">
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
              <li class="active"><a href="#">Active Courses</a></li>
              <li><a href="#">Archieved Courses</a></li>
            </ul>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li><button type="button" class="btn btn-success" href="#">Register Course</button></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container" id="course-list">
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default">
            <div class="panel-body">FACC 201 - Winter 2019</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default">
            <div class="panel-body">FACC 202 - Winter 2019</div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default">
            <div class="panel-body">FACC 203 - Winter 2019</div>
          </div>
        </div>
      </div>
    </div>
    </div><br>
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
    name: 'dashboard',
    data() {
      return {
        student : null
      }
    },
    created() {
      console.log('Hello!!')
      AXIOS.get(`/students/` + this.$route.params.id)
        .then(response => {
          this.student = response.data
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
        console.log(this.student)
    },
    methods: {
      goToAccount: function() {
        this.$router.push({name:'StudentInformation', params:{id:this.$route.params.id}})
      }
    }
  }
</script>
