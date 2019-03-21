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
			       <div class="container text-left">
					 <h1>Welcome</h1>
				   </div><hr>
				 </div>
						    
				 <div class="panel panel-default text-center">
				   <div class="panel-body">
					 <br><br><br><br>
					   <div class="form-group">
						 <label for="usr">Student Number:</label>
						   <input type="text" class="form-control form-control-lg" id="usr">
				 	   </div>
			           <button type="button" class="btn btn-primary btn-block">Login</button><br>  
				       <a href="#">need help?</a>
					</div>
				 </div>        
			   </div>	
			 <div class="col-sm-2"> </div>
	       </div>
	    <div class="col-sm-2 sidenav"></div>
	  </div>
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


