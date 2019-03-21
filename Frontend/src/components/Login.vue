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
       <h1>Co-Op-Erator</h1>
     </div>
  </div>

  <div class="container-fluid text-center">
    <div class="row content">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <div class="col-sm-2"></div>
          <div class="col-sm-8">
            <br><br>
            <div class="container-fluid">
              <div class="container text-left" id = "welcome">
                <h2>Welcome!</h2>
                <h3>You look amazing today</h3>
              </div><hr>
            </div>

            <div class="panel panel-default text-center">
              <div class="panel-body">
                <br><br><br><br>
                <div class="form-group">
                  <label for="usr">
                    <font size="4">Student Number:</font>
                  </label>
                  <input type="text" class="form-control form-control-lg" id="usr">
                </div>
                <button type="button" class="btn btn-primary btn-block">
                  <font size="4" face = "Times"><b>Login</b></font>
                </button>
                <br>
                <a href="#"><i><b>need help?</b></i></a>
              </div>
             </div>
           </div>
           <div class="col-sm-2"> </div>
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
    max-width: 100%;
  }

  #top-container {
    margin-bottom: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #top-container h1 {
    text-align: center;
    margin-top: 30px;
    margin-bottom: 15px;
    font-size:45px
  }

  #welcome {
    max-width: 100%;
  }

  #welcome h2 {
    text-align: center;
    margin-top: 0px;
    margin-bottom: 10px;
    font-size:37px
  }
  #welcome h3 {
    text-align: center;
    margin-top: 5px;
    margin-bottom: 0px;
    font-size:25px;
    font-family: Lucida;
    font-style: oblique;
    color:#005CB9;
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
