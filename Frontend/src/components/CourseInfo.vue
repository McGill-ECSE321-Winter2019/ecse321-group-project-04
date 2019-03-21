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
            
            <!-- the my account button will have to be changed, waiting for Angel's code -->
            
            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> 
            My Account
            </button>
          </div>
        </div>
      </div>
    </div>
   
    <div class="container-fluid" id = "course-title">
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
            <li class="active"><a href="#">Active Courses</a></li>
            <li><a href="#">Archieved Courses</a></li>
            <li><button type="button" class="btn btn-success" href="#">Register Course</button></li>
          </ul>
        </div>
      </div>
    </nav>
    
    
    
    <div class="container">
      <div class="row">
        <div class="col-sm-6">
          <div class="panel panel-primary">
            <div class="panel-heading">BLACK FRIDAY DEAL</div>
            <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
            <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
          </div>
        </div>
 
        <div class="col-sm-6">
          <div class="panel panel-success">
            <div class="panel-heading">BLACK FRIDAY DEAL</div>
            <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
            <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
          </div>
        </div>
      </div>
    </div><br>
    <br>
    

  </body>

  </html>
</template>

<style>
  /* Remove the navbar's default rounded borders and increase the bottom margin */
  .navbar {
    margin-bottom: 50px;
    border-radius: 0;
  }

  /* Remove the jumbotron's default bottom margin */
  #top-container {
    margin-bottom: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h2 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 15px;
  }

  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }

  #course-title h2{
    text-align: left;
    margin-top: 30px;
    margin-bottom: 15px;
  
  }
  
  nav {
    margin-top: 15px;
  }
  
</style>

<script>
  export default {
    data: () => ({}),
    mounted() {
      let s1 = document.createElement('script')
      s1.setAttribute('src', 'https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js')
      document.head.appendChild(s1)
      let s2 = document.createElement('script')
      s2.setAttribute('src', 'https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js')
      document.head.appendChild(s2)
    },
    methods: {}
  }
</script>
