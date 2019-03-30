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
      <div class="container text-center" id="img-container">
        <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="300" height="100">
      </div>
    </div>
    <br>
    <div class="container-fluid text-center" id="background">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <br>
          <div class="container-fluid" id="welcome">
            <h2>
              Welcome!
            </h2><br>
            <h3>What type of user are you?</h3>
            <hr>
          </div>
        </div>

        <br><br><br><br>
        <div class="row">
          <div class="col-sm-3">
            <a href="https://cooperator-frontend-0000.herokuapp.com">
              <button type="button" style="background-color: #1F618D; color: white;" class="btn ">
                <font size="5"><b>Student</b></font>
              </button>
            </a>
          </div>

          <div class="col-sm-3">
            <a href="https://cooperator-frontend-123.herokuapp.com/#/">
              <button onclick="" type="button" style="background-color:#2471A3; color: white;" class="btn ">
                <font size="5"><b>Employer</b></font>
              </button>
            </a>
          </div>
          <div class="col-sm-3">
            <a href="https://cooperator-frontend-123.herokuapp.com/#/">
              <button onclick="" type="button" style="background-color: #2980B9; color: white;" class="btn ">
                <font size="5"><b>Admin</b></font>
              </button>
            </a>
          </div>
          <div class="col-sm-3">
            <a href="http://cooperatorapp-frontend-18.herokuapp.com/#/">
              <button onclick="" type="button btn-primary" style="background-color:#5499C7; color: white;" class="btn ">
                <font size="5"><b>Manager</b></font>
              </button>
            </a>
          </div>

        </div>

      </div>

      <div class="col-sm-2 sidenav"></div>
    </div>


  </body>

  </html>
</template>

<style>
  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: #333335;
    color: #ffffff;
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
    font-size: 30px;
    font-family: Lucida;
    font-style: oblique;
    color: #333335;
  }

  .btn {
    //background-color:;

    min-width: 150px;
    min-height: 70px;

  }
</style>

<script>
  export default {
    data() {
      return {}
    },
  }
</script>
