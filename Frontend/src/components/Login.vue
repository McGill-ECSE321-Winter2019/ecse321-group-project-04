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
       <img  src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png"
       width="300" height="100">
     </div>
  </div>

  <div class="container-fluid text-center" id = "background">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <div class="col-sm-2"></div>
          <div class="col-sm-8">
            <br>
            <div class="container-fluid">
              <div class="container text-left" id = "welcome">
                <h2><font>Welcome!</font></h2>
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
                <a href="#"><i><b>Need help?</b></i></a>
              </div>
             </div>
           </div>
           <div class="col-sm-2"> </div>
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
    max-width: 100%;
  }

  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: #333335;
    color: #ffffff;
  }


  #welcome {
    max-width: 100%;
    margin-top: 0px;
  }

  #welcome h2 {
    text-align: center;
    margin-top: 15px;
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
    color: #333335;
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
