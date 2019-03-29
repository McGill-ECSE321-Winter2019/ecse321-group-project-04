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
        <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="300" height="100">
      </div>
    </div>

    <div class="container-fluid text-center" id="background">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <div class="col-sm-2"></div>
          <div class="col-sm-8">
            <br>
            <div class="container-fluid">
              <div class="container text-left" id="welcome">
                <h2>
                  <font>Welcome!</font>
                </h2>
                <h3>You look amazing today</h3>
              </div>
              <hr>
            </div>

            <div class="panel panel-default text-center">
              <div class="panel-body">
                <br><br><br><br>
                <div class="form-group">
                  <label for="usr">
                    <font size="4">Student Number:</font>
                  </label>
                  <input type="text" class="form-control form-control-lg" id="usr">
                  <p id="demo"></p>
                </div>
                <button @click="goToDashboard" type="button" class="btn btn-primary btn-block" id="login">
                  <font size="4"><b>Login</b></font>
                </button>
                <br>
                <a href="#" @click="showModalHelp=true"><i><b>Need help?</b></i></a>
              </div>
            </div>
          </div>
          <div class="col-sm-2"> </div>
        </div>
      </div>
      <div class="col-sm-2 sidenav"></div>
    </div>

    <!-- Help Modal -->
    <transition name="modal">
      <div v-if="showModalHelp" key="helpModal">
        <div class="modal-mask">
          <div class="modal-wrapper" @click="showModalHelp=false">
            <div class="modal-container" @click.stop>
              <div class="modal-header">
                <slot name="header">
                  IT Help Information
                </slot>
              </div>
              <div class="modal-body">
                <h4 style="color:gray"><em>Email</em></h4>
                <h4><b>it.helpdesk@cooperator.ca</b></h4>
                <br>
                <h4 style="color:gray"><em>Phone</em></h4>
                <h4><b>438-382-2349</b></h4>
                <br>
              </div>
              <br>
              <div style="text-align:center">
                <slot>
                  <button class="btn btn-primary" style="min-width:120px" @click="showModalHelp=false">
                    <font size="3">Done</font>
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </div>
    </transition>

    <!-- Processing Modal -->
    <transition name="modal" mode="out-in">
      <div v-if="showModalProcessingLogin" key="processing">
        <div class="modal-mask">
          <div class="modal-wrapper" @click="showModalProcessingLogin=false">
            <div class="modal-container" @click.stop>
              <div class="modal-header">
                <slot name="header">
                  Processing Login
                </slot>
              </div>
              <div class="modal-body">
                <h4 style="text-align:center">Your account information is being loaded.</h4>
                <br>
                <div style="text-align:center">
                  <img src="https://user-images.githubusercontent.com/22506116/54891974-f9cba300-4e85-11e9-8842-9ab32c10658f.gif" width="200" height="200">
                </div>
                <br>
              </div>
              <div style="text-align:center">
                <slot>
                  <br>
                  <br>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </div>
    </transition>

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

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
    transition: all .3s ease;
  }

  /*.modal-enter-active {
    opacity: 0;
    transition: all .3s ease;
  }*/

  .modal-enter .modal-container,
  .modal-leave-active .modal-container,
  .modal-leave-to .modal-container {
    /*-webkit-transform: scale(1.1);*/
    transform: translateY(100px);
  }
</style>

<script>
  import axios from 'axios'
  var config = require('../../config')

  /* AXIOS object setup */
  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl
  })

  var checkInput = async (input) => {
    if (input.trim().search(/\d{9}/) == -1) {
      return 1; // Bad input
    } else {
      try {
        let response = await AXIOS.get(/students/ + input);
        if (response.data !== {}) {
          return 0; // Student records found
        } else {
          return 2; // No matching records
        }
      } catch (e) {
        return 3; // Error while making request
      }

    }
    return false;
  }

  function refreshErrorMessage(result) {
    if (result == 0) {
      document.getElementById("usr").className = 'form-control form-control-lg'
      document.getElementById("demo").style.color = ''
      document.getElementById("demo").innerHTML = ''
    } else {
      document.getElementById("usr").className = 'form-control form-control-lg is-invalid'
      document.getElementById("demo").style.color = 'red'
      if (result == 1) {
        document.getElementById("demo").innerHTML = "Please Enter A Valid Student ID";
      } else if (result == 2 || result == 3) {
        var input = document.getElementById("usr").value
        document.getElementById("demo").innerHTML = "There Are No Records For Student ID " + input;
      }
    }
  }

  export default {
    name: 'login',
    data() {
      return {
        student: null,
        showModalHelp: false,
        showModalProcessingLogin: false
      }
    },
    methods: {
      goToDashboard: function() {
        this.showModalProcessingLogin = true
        var result = 3
        var input = document.getElementById("usr").value;

        if (input.trim().search(/\d{9}/) == -1) {
          result = 1; // Bad input
          refreshErrorMessage(result)
          this.showModalProcessingLogin = false
          console.log(1)
        } else {
          try {
            AXIOS.get(`/students/` + input)
              .then(response => {
                if (response.data !== {}) {
                  result = 0; // Student records found
                } else {
                  result = 2; // No matching records
                }

                if (result == 0) {
                  result = 0
                  this.$router.push({
                    name: 'Dashboard',
                    params: {
                      id: input
                    }
                  })
                } else {
                  console.log(1)
                  refreshErrorMessage(result)
                  this.showModalProcessingLogin = false
                }
              })
              .catch(e => {
                var errorMsg = e.message;
                console.log(errorMsg);
              })
          } finally {
            this.showModalProcessingLogin = false
            refreshErrorMessage(result)
          }
        }

      },
    }
  }
</script>
