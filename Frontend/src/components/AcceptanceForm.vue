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
            <div class="container text-left">
              <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="240" height="80">
            </div>
          </div>
          <div class="col-sm-4">
            <br>

            <div class="row">
              <div class="col-sm-6">
                <button type="button" class="btn btn-primary" @click="goToAccount" id = "Account-but">
                  <span class="glyphicon glyphicon-user"></span>
                  Account
                </button>
              </div>
              <div class="col-sm-6">
                <button type="button" class="btn btn-danger" @click="goToLogin" id = "Logout-but">
                  Logout
                </button>
              </div>
            </div>

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
            <div class="container text-left" id="Acceptance-Form">
              <h2>
                <font>Acceptance Form</font>
              </h2>
            </div>
            <hr>
          </div>

          <div class="panel panel-default text-center">
            <div class="panel-body">
              <br>
              <div class="form-group">
                <div class="container-fluid">
                  <div class="container text-left" id="Job-Information">
                    <h3>
                      <font><b>Job Information</b></font>
                    </h3>
                  </div>
                  <hr>
                </div>
                <br>
                <form class="form-inline" action="/action_page.php">
                  <label for="jobid2" class="mb-2 mr-sm-2">Job Posting ID:</label>
                  <input type="text" class="form-control mb-2 mr-sm-2" id="JobID" placeholder="Enter Job ID" name="Job ID">
                </form>
                <br>

                <div class="row">
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="start2" class="mb-2 mr-sm-2">Starting date:</label>
                      <input type="date" class="form-control mb-2 mr-sm-2" id="Start" name="Start">
                    </form>
                  </div>

                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="end2" class="mb-2 mr-sm-2">Ending date:</label>
                      <input type="date" class="form-control mb-2 mr-sm-2" id="End" name="End">
                    </form>
                  </div>
                </div>
                <br>

                <form class="form-inline" action="/action_page.php">
                  <label for="acdemicterm2" class="mb-2 mr-sm-2">Acdemic Term:</label>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="Fall">Fall
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="Fall">Winter
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="Fall">Summer
                    </label>
                  </div>
                </form>
                <br>

                <form class="form-inline" action="/action_page.php">
                  <label for="acdemicterm2" class="mb-2 mr-sm-2">Work Permit Required:</label>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="W-Yes">Yes
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="W-No">No
                    </label>
                  </div>

                </form>
                </br>

                <div class="container-fluid">
                  <div class="container text-left" id="Employer-Information">
                    <h3>
                      <font><b>Employer Information</b></font>
                    </h3>
                  </div>
                  <hr>
                </div>
                <br>
                <div class="row">
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="companyname2" class="mb-2 mr-sm-2">Company Name:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" id="Company-Name" placeholder="Enter Company Name" name="Company Name">
                    </form>
                  </div>

                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="employername2" class="mb-2 mr-sm-2">Employer Name:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" margin="" id="Employer-Name" placeholder="Enter Employer Name" name="Employer Name">
                    </form>
                  </div>
                </div>
                <br>
                <form class="form-inline" action="/action_page.php">
                  <label for="employeremail2" class="mb-2 mr-sm-2">Employer E-mail:</label>
                  <input type="text" class="form-control mb-2 mr-sm-2" margin="" id="Employer-Email" placeholder="Enter Employer Email" name="Employer Email">
                </form>
                <br>
                <form class="form-inline" action="/action_page.php">
                  <label for="addressline1" class="mb-2 mr-sm-2">Address Line 1:</label>
                  <input type="text" class="form-control mb-2 mr-sm-2" id="Address-Line-1" placeholder="Address Line 1" name="Address Line 1">
                </form>
                <br>
                <form class="form-inline" action="/action_page.php">
                  <label for="addressline2" class="mb-2 mr-sm-2">Address Line 2:</label>
                  <input type="text" class="form-control mb-2 mr-sm-2" id="Address-Line-2" placeholder="Address Line 2" name="Address Line 2">
                </form>
                <br>
                <div class="row">
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="city2" class="mb-2 mr-sm-2">City:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" id="City" placeholder="City" name="City">
                    </form>
                  </div>
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="province2" class="mb-2 mr-sm-2">Province:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" id="Province" placeholder="Province" name="Province">
                    </form>
                  </div>
                </div>
                <br>
                <div class="row">
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="postalcode2" class="mb-2 mr-sm-2">Postal Code:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" id="Postal-Code" placeholder="Postal Code" name="Postal Code">
                    </form>
                  </div>
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="country2" class="mb-2 mr-sm-2">Country:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" size="30" id="Country" placeholder="Country" name="Country">
                    </form>
                  </div>
                </div>
              </div>
              <br>
              <br>

              <div class="row">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-4">
                  <div class="row">
                    <div class="col-sm-6 text-center">
                      <button type="button" class="btn btn-outline-secondary">
                        <font size="4">Cancel</b></font>
                      </button>
                    </div>
                    <div class="col-sm-6 text-center">
                      <button type="button" class="btn btn-outline-primary">
                        <font size="4">Submit</b></font>
                      </button>
                    </div>
                  </div>
                </div>
                <div class="col-sm-4">
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-2 sidenav"></div>
      </div>
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

  #Account-but{
    margin-left: 100px;
    min-width: 0%;
  }
  #Logout-but{
    margin-left: 50px;
    min-width: 50%;
  }
  #Acceptance-Form {
    max-width: 100%;
    margin-top: 0px;
  }

  #Acceptance-Form h2 {
    text-align: center;
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 37px
  }

  #Fall {
    margin-left: 20px;
  }

  #Winter {
    margin-left: 30px;
  }

  #Summer {
    margin-left: 30px;
  }

  #W-Yes {
    margin-left: 30px;
  }

  #W-No {
    margin-left: 20px;
  }

  #Job-Information {
    max-width: 100%;
    margin-top: 0px;
  }

  #Job-Information h3 {
    text-align: center;
  }

  #Employer-Information {
    max-width: 100%;
    margin-top: 0px;
  }

  #Employer-Information h3 {
    text-align: center;
  }

  #JobID {
    margin-left: 10px;
    min-width: 27.7%;
  }

  #Start {
    margin-left: 20px;

  }

  #End {
    margin-left: 20px;
    min-width: 0%;
  }

  #Company-Name {
    margin-left: 13px;
    min-width: 30%;
  }

  #Employer-Name {
    margin-left: 13px;
    min-width: 30%;
  }

  #Employer-Email {
    margin-left: 10px;
    min-width: 52%;
  }

  #Address-Line-1 {
    min-width: 100%;
  }

  #Address-Line-2 {
    min-width: 100%;
  }

  #City {
    margin-left: 66px;
    min-width: 65%;
  }

  #Province {
    margin-left: 5px;
    min-width: 65%;
  }

  #Postal-Code {
    margin-left: 10px;
    min-width: 65%;
  }

  #Country {
    margin-left: 10px;
    min-width: 65%;
  }
</style>

<script>
  import axios from 'axios'
  var config = require('../../config')

  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: {
      'Access-Control-Allow-Origin': frontendUrl
    }
  })

  export default {

    data() {
      return {}
    },
    methods: {
      goToLogin: function(){
        this.$router.push({
          name: 'Login',
        })
      },
      
      goToAccount: function() {
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
