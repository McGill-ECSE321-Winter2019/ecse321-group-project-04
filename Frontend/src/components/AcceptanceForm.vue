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
          <div class="col-sm-6">
            <div class="container  text-left">
              <div @click="goToDashboard" style="display: inline-block;" id="img-container">
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

    <div class="container-fluid" id="background">
      <div class="container text-center">
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
                      <font><b>Course Information</b></font>
                    </h3>
                  </div>
                  <hr>
                </div>
                <br <div class="row">
                  <div class="col-sm-6">
                    <form class="form-inline" action="/action_page.php">
                      <label for="courseid" class="mb-2 mr-sm-2">Course ID:</label>
                      <input type="text" class="form-control mb-2 mr-sm-2" id="CourseID" placeholder="Enter Course ID" name="Course ID">
                    </form>
                  </div>
                  <br>
                  <div class="col-sm-6">
                    <div class="form-group form-inline">
                      <label for="sel1">Coop Term:</label>
                      <select class="form-control" id="CoopTerm">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                      </select>
                    </div>
                  </div>
                </div>
                <form class="form-inline" action="/action_page.php">
                  <label for="acdemicterm2" class="mb-2 mr-sm-2">Academic Term:</label>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" checked="true" id="Fall">
                      <h4 style="margin-left:5px">Fall</h4>
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="Winter">
                      <h4 style="margin-left:5px">Winter</h4>
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="Summer">
                      <h4 style="margin-left:5px">Summer</h4>
                    </label>
                  </div>
                </form>
                <br>
                <br>


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
                  <label for="acdemicterm2" class="mb-2 mr-sm-2">Work Permit Required:</label>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" checked="true" id="W-Yes">
                      <h4 style="margin-left:5px">Yes</h4>
                    </label>
                  </div>

                  <div class="form-check-inline">
                    <label class="form-check-label">
                      <input type="radio" class="form-check-input" name="optradio" id="W-No">
                      <h4 style="margin-left:5px">No</h4>
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
              <form class="form-inline" action="/action_page.php">
                <label for="companyname2" class="mb-2 mr-sm-2">Company Name:</label>
                <input type="text" class="form-control mb-2 mr-sm-2" id="Company-Name" placeholder="Enter Company Name" name="Company Name">
              </form>
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
                    <p style="color:red" id="postalCodeErrorMsg"></p>
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
            <form class="form-inline">
              <label for="Employer-Contract" class="mb-2 mr-sm-2">Employer Contract:</label>
              <div class="custom-file">
                <input type="file" @change="onFileSelected" class="custom-file-input" id="inputGroupFile01">
                <label class="custom-file-label" for="inputGroupFile01" >{{selectedFileName}}</label>
              </div>
            </form>
            <br>
            <br>
            <div class="row">
              <div class="col-sm-4">
              </div>
              <div class="col-sm-4">
                <div class="row">
                  <div class="col-sm-6 text-center">
                    <button @click="showModalWarningRegistration=true" type="button" class="btn btn-outline-secondary">
                      <font size="4">Cancel</b></font>
                    </button>
                  </div>
                  <div class="col-sm-6 text-center">
                    <button @click="showModalConfirmRegistration=true" ype="button" class="btn btn-outline-primary">
                      <font size="4">Submit</b></font>
                    </button>
                  </div>
                </div>
              </div>
              <div class="col-sm-4">
              </div>
            </div>
            <br>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Sucessful Modal -->
  <transition name="modal" mode="out-in">
    <div v-if="showModalSuccessRegistration" key="success">
      <div class="modal-mask">
        <div class="modal-wrapper" @click="showModalSuccessRegistration=false">
          <div class="modal-container" @click.stop>
            <div class="modal-header">
              <slot name="header">
                Successful Registration
              </slot>
            </div>
            <div style="text-align:center">
              <slot>
                <br>
                <br>
                <button class="btn btn-primary" style="min-width:120px" @click="goToDashboard">
                  <font size="3">Done</font>
                </button>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>

  <!-- Failure Modal -->
  <transition name="modal" mode="out-in">
    <div v-if="showModalFailRegistration" key="failure">
      <div class="modal-mask">
        <div class="modal-wrapper" @click="showModalFailRegistration=false">
          <div class="modal-container" @click.stop>
            <div class="modal-header">
              <slot name="header">
                Failed Registration
              </slot>
            </div>
            <div class="modal-body">
              <h4 style="text-align:center">There was a problem processing your request.<br><br>Please try again later.</h4>
              <br>
            </div>
            <div style="text-align:center">
              <slot>
                <br>
                <br>
                <button class="btn btn-primary" style="min-width:120px" @click="showModalFailRegistration=false">
                  <font size="3">Done</font>
                </button>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>

  <!-- Warning Modal -->
  <transition name="modal" mode="out-in">
    <div v-if="showModalWarningRegistration" key="warning">
      <div class="modal-mask">
        <div class="modal-wrapper" @click="showModalWarningRegistration=false">
          <div class="modal-container" @click.stop>
            <div class="modal-header">
              <slot name="header">
                Warning
              </slot>
            </div>
            <div class="modal-body">
              <h4 style="text-align:center">Do you want to cancel?<br><br>Any information entered will be lost.</h4>
              <br>
            </div>
            <div style="text-align:center">
              <slot>
                <br>
                <br>
                <button class="btn btn-primary" style="min-width:120px" @click="goToDashboard">
                  <font size="3">Yes</font>
                </button>
                <button class="btn btn-secondary" style="min-width:120px" @click="showModalWarningRegistration=false">
                  <font size="3">No</font>
                </button>
              </slot>
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>

  <!-- Confirm Modal -->
  <transition name="modal" mode="out-in">
    <div v-if="showModalConfirmRegistration" key="confirm">
      <div class="modal-mask">
        <div class="modal-wrapper" @click="showModalConfirmRegistration=false">
          <div class="modal-container" @click.stop>
            <div class="modal-header">
              <slot name="header">
                Confirm Registration
              </slot>
            </div>
            <div class="modal-body">
              <h4 style="text-align:center">Do you want to register for this course?</h4>
              <br>
            </div>
            <div style="text-align:center">
              <slot>
                <br>
                <br>
                <button class="btn btn-primary" style="min-width:120px" @click="submitForm">
                  <font size="3">Yes</font>
                </button>
                <button class="btn btn-secondary" style="min-width:120px" @click="showModalConfirmRegistration=false">
                  <font size="3">No</font>
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
    <div v-if="showModalProcessingRegistration" key="processing">
      <div class="modal-mask">
        <div class="modal-wrapper" @click="showModalProcessingRegistration=false">
          <div class="modal-container" @click.stop>
            <div class="modal-header">
              <slot name="header">
                Processing Registration
              </slot>
            </div>
            <div class="modal-body">
              <h4 style="text-align:center">Your request is being processed.</h4>
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
  #img-container:hover img {
    opacity: 0.8;
  }

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
    margin-bottom: 20px;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #Acceptance-Form {
    max-width: 100%;
    margin-top: 20px;
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

  #CourseID {
    margin-left: 10px;
    min-width: 27.7%;
  }

  #CoopTerm {
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
    min-width: 51%;
  }

  #Employer-Name {
    margin-left: 13px;
    min-width: 30%;
  }

  #Employer-Email {
    margin-left: 10px;
    min-width: 51%;
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

  #Employer-Contract {
    margin-left: 15px;
    min-width: 73%;
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


  .modal-enter .modal-container,
  .modal-leave-active .modal-container,
  .modal-leave-to .modal-container {
    /*-webkit-transform: scale(1.1);*/
    transform: translateY(100px);
  }
</style>

<script>
  import jsPDF from 'jspdf';
  import axios from 'axios'
  var config = require('../../config')

  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
    var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

    var AXIOS = axios.create({
      baseURL: backendUrl
    })

    function checkInput(elementID) {
      var element = document.getElementById(elementID)
      var value = element.value

      if (value === '' || value === null) {
        element.className = 'form-control form-control-lg is-invalid'
        return false
      } else {
        element.className = 'form-control form-control-lg'
        return true
      }
    }

    export default {

      data() {
        return {
          employer: null,
          showModalSuccessRegistration: false,
          showModalFailRegistration: false,
          showModalConfirmRegistration: false,
          showModalWarningRegistration: false,
          showModalProcessingRegistration: false,
          selectedFile : null,
          selectedFileName : 'Choose File',
          ContractURL : null,
          AcceptanceFormURL: null
        }
      },
      methods: {


        /* Method to cancel registration */
        alert: function() {
          var txt;
          var click = confirm("Do you want to cancel the registration?");
          if (click == true) {
            this.$router.push({
              name: 'Dashboard',
              params: {
                id: this.$route.params.id
              }
            })
          }
        },


        /* Method to update the selected file */
        onFileSelected(event){
          this.selectedFile = event.target.files[0]
          this.selectedFileName = this.selectedFile.name
          console.log(event.target.files[0])
        },


        /* Method to upload the employer contract */
        async onUpload(){
          // Set up the file
          const fd = new FormData();
          var id = Math.floor((Math.random() * 10000000));
          var filename = this.selectedFile.name;
          var nameNoExt = filename.split('.',2)[0];
          var ext = filename.split('.',2)[1];
          filename = nameNoExt+id+'.'+ext; //add id to the file name

          fd.append('doc', this.selectedFile, filename)
          // Upload the file to Firebase
          try{
            let response = await axios.post('https://us-central1-cooperator-2b466.cloudfunctions.net/uploadFile', fd,{
              onUploadProgress: uploadEvent =>{
                console.log('upload pregress: ' + Math.floor(uploadEvent.loaded / uploadEvent.total)*100 )
              }
            })
            this.ContractURL = response.data.shortURL
            console.log(this.ContractURL)
            return response;
          }
          // show registration failed modal
          catch(e){
            this.error = e
            this.showModalProcessingRegistration = false
            this.showModalFailRegistration = true
            console.log(e);
          }
        },


        /* Method to generate and store the coop acceptance form */
        async genFile(){
          // Set up the file
          const fd = new FormData();
          var id = Math.floor((Math.random() * 10000000));
          var filename = "AcceptanceForm.pdf";
          var nameNoExt = filename.split('.',2)[0];
          var ext = filename.split('.',2)[1];
          filename = nameNoExt+id+'.'+ext; //add id to the file name

          // Data for the PDF (Already checked by caller)
          var companyName = document.getElementById("Company-Name").value;
          var employerEmail = document.getElementById("Employer-Email").value;
          //Address
          var addressline1 = document.getElementById("Address-Line-1").value;
          var addressline2 = document.getElementById("Address-Line-2").value;
          var city = document.getElementById("City").value;
          var province = document.getElementById("Province").value;
          var postalCode = document.getElementById("Postal-Code").value;
          var country = document.getElementById("Country").value;
          var address = addressline1 + " " + addressline2 + ", " + city + ", " + province + " " + postalCode + ", " + country

          // Get the course details
          var studentID = this.$route.params.id;
          var courseID = document.getElementById("CourseID").value
          var coopTerm = document.getElementById("CoopTerm").value

          // Get the offering Details
          var term = null;
          var year = document.getElementById("Start").value.substring(0, 4)
          if (document.getElementById("Fall").checked) {
            term = "FALL";
          } else if (document.getElementById("Winter").checked) {
            term = "WINTER";
          } else if (document.getElementById("Summer").checked) {
            term = "SUMMER";
          }

          // Get the Enrollment Details
          var jobID = document.getElementById("JobID").value;
          var startDate = document.getElementById("Start").value;
          var endDate = document.getElementById("End").value;
          var workPermit = document.getElementById("W-Yes").checked;
          if(workPermit)
            workPermit = 'Yes'
          else
            workPermit = 'No'

          /*Generate the PDF File from the form*/
          var doc = new jsPDF();
          //Title
          doc.setFontSize(28);
          doc.text(10, 20, 'Co-Op Acceptance Form');
          // Course Info
          doc.setFontSize(18);
          doc.text(10, 30,'Course Information');
          doc.setFontSize(14);
          doc.text(20, 40,'Student ID: ' + studentID);
          doc.text(20, 50,'Academic Semseter: ' + term + ' ' + year);
          doc.text(20, 60, 'CourseCode: ' + courseID);
          // Job Info
          doc.setFontSize(18);
          doc.text(10, 70,'Job Information');
          doc.setFontSize(14);
          doc.text(20, 80, 'Job Posting ID: ' + jobID);
          doc.text(20, 90,'Work Permit Required: ' + workPermit);
          doc.text(20, 100,'Start Date: ' + startDate);
          doc.text(20, 110,'End Date: ' + endDate);
          // Empoyer Info
          doc.setFontSize(18);
          doc.text(10, 120,'Employer Information');
          doc.setFontSize(14);
          doc.text(20, 130,'Company Name: ' + companyName);
          doc.text(20, 140,'Employer Email: ' + employerEmail);
          doc.text(20, 150,'Address: ' + address);

          var pdf = doc.output('blob')
          fd.append('doc', pdf, filename)
          // Upload the file to Firebase
          try{
            let response = await axios.post('https://us-central1-cooperator-2b466.cloudfunctions.net/uploadFile', fd,
            {
              onUploadProgress: uploadEvent =>{
                console.log('upload pregress: ' + Math.floor(uploadEvent.loaded / uploadEvent.total)*100 )
              }
            })
            this.AcceptanceFormURL = response.data.shortURL
            console.log(this.AcceptanceFormURL)
            return response;

          }
          // Show registration failed modal
          catch(e){
            this.error = e
            this.showModalProcessingRegistration = false
            this.showModalFailRegistration = true
            console.log(e);
          }
        },


        /* Method submit the registration form */
        submitForm: async function() {
          this.showModalConfirmRegistration = false
        // Format postal code correctly
        var ps = document.getElementById("Postal-Code")
        ps.value = ps.value.replace(/\s/g, '').toUpperCase()
        // Format course code correctly
        var cID = document.getElementById("CourseID")
        cID.value = cID.value.replace(/[\s-]/g, '').toUpperCase()
        // Set up to check validity of input data
        var valid = true
        var elements = ["Company-Name",
        "Employer-Email",
        "Address-Line-1",
        "City",
        "Province",
        "Postal-Code",
        "Country",
        "CourseID",
        "JobID",
        ]

        // Check that the field elements aren't empty
        elements.forEach(function(el) {
          if (!checkInput(el)) {
            valid = false
          }
        })

        // Check that the postal code is valid
        var canadianPC = /^[a-zA-Z]\d[a-zA-Z]\d[a-zA-Z]\d$/
        var internationalPC = /^\d{5}$/
        var inputPC = document.getElementById('Postal-Code').value
        if (inputPC.search(canadianPC) == -1 && inputPC.search(internationalPC) == -1) {
          valid = false
          document.getElementById('Postal-Code').className = 'form-control mb-2 mr-sm-2 is-invalid'
          document.getElementById("postalCodeErrorMsg").innerHTML = 'Please Enter A Valid Postal Code. e.g. H2X1V8 or 94240'
        } else {
          document.getElementById('Postal-Code').className = 'form-control mb-2 mr-sm-2'
          document.getElementById("postalCodeErrorMsg").innerHTML = ''
        }

        // Check that the input dates are valid
        var startEl = document.getElementById("Start")
        var endEl = document.getElementById("End")
        if (startEl.value == "") {
          valid = false
          startEl.className = "form-control mb-2 mr-sm-2 is-invalid"
        } else {
          startEl.className = "form-control mb-2 mr-sm-2"
        }
        if (endEl.value == "") {
          valid = false
          endEl.className = "form-control mb-2 mr-sm-2 is-invalid"
        } else {
          endEl.className = "form-control mb-2 mr-sm-2"
        }

        //Check that the document is valid
        var inputDoc = document.getElementById('inputGroupFile01').value
        if(inputDoc === null || inputDoc === ''){
          valid = false
          document.getElementById('inputGroupFile01').className = 'custom-file-input is-invalid'
        }else {
          valid = valid ? true : false
          document.getElementById('inputGroupFile01').className = 'custom-file-input form-control-lg'
        }

        // Register the student if all input data is valid
        if (valid) {
          // Show processing modal
          this.showModalProcessingRegistration = true

          //Get the Student Details
          var studentID = this.$route.params.id;

          //Get the Employer Details
          var companyName = document.getElementById("Company-Name").value;
          var employerEmail = document.getElementById("Employer-Email").value;
          //Address
          var addressline1 = document.getElementById("Address-Line-1").value;
          var addressline2 = document.getElementById("Address-Line-2").value;
          var city = document.getElementById("City").value;
          var province = document.getElementById("Province").value;
          var postalCode = document.getElementById("Postal-Code").value;
          var country = document.getElementById("Country").value;
          var address = addressline1 + " " + addressline2 + ", " + city + ", " + province + " " + postalCode + ", " + country

          // Get the course details
          var courseID = document.getElementById("CourseID").value
          var coopTerm = document.getElementById("CoopTerm").value

          // Get the offering Details
          var active = true; //default for new course
          var term = null;
          var year = document.getElementById("Start").value.substring(0, 4)
          var yearID = document.getElementById("Start").value.substring(2, 4);
          if (document.getElementById("Fall").checked) {
            term = "FALL";
          } else if (document.getElementById("Winter").checked) {
            term = "WINTER";
          } else if (document.getElementById("Summer").checked) {
            term = "SUMMER";
          }
          if (term != null){
            var offeringID = courseID + "-" + term.charAt() + yearID;
          }

          // Get the Enrollment Details
          var jobID = document.getElementById("JobID").value;
          var startDate = document.getElementById("Start").value;
          var endDate = document.getElementById("End").value;
          var workPermit = document.getElementById("W-Yes").checked;
          var status = "ONGOING"; //default for new enrollment

          //Push the two forms to firebase
          try{
            let wait1 = await this.genFile() // Generate an acceptance form pdf
            let wait2 = await this.onUpload() // Upload the employer contract
          }catch(e){
            this.error = e
            this.showModalProcessingRegistration = false
            this.showModalFailRegistration = true
            console.log(e);
          }

          //Create the employer
          AXIOS.post(`/employer/`, {
            "name": companyName,
            "email": employerEmail,
            "address": address
          })
          // Create the course
          .then(response => {
            AXIOS.post(`/coopCourse/`, {
              "courseCode": courseID,
              "coopTerm": coopTerm
            })
            // Create the course offering
            .then(response => {
              AXIOS.post(`/coopCourseOffering?courseCode=` + courseID, {
                "year": year,
                "term": term,
                "active": active
              })
              // Create the registration (enrollment)
              .then(response => {
                AXIOS.post(`studentEnrollment?courseOfferingID=` + offeringID + `&studentID=` + studentID + `&employerEmail=` + employerEmail, {
                  "se" :{
                    "active": active,
                    "status": status,
                    "startDate": startDate,
                    "endDate": endDate,
                    "workPermit": workPermit,
                    "jobID": jobID
                  },
                  "acceptanceFormURL" : this.AcceptanceFormURL,
                  "employerContractURL" : this.ContractURL
                })
                // Show registration was successful modal
                .then(response => {
                  this.showModalProcessingRegistration = false
                  this.showModalSuccessRegistration = true
                })
                // Show registration failed modal
                .catch(e => {
                  var errorMsg = e.message
                  console.log(errorMsg)
                  this.error = errorMsg
                  this.showModalProcessingRegistration = false
                  this.showModalFailRegistration = true
                })
              })
              // Show registration failed modal
              .catch(e => {
                var errorMsg = e.message
                console.log(errorMsg)
                this.error = errorMsg
                this.showModalProcessingRegistration = false
                this.showModalFailRegistration = true
              })
            })
            // Show registration failed modal
            .catch(e => {
              var errorMsg = e.message
              console.log(errorMsg)
              this.error = errorMsg
              console.log('Hello')
              this.showModalProcessingRegistration = false
              this.showModalFailRegistration = true
            })
          })
          // Show registration failed modal
          .catch(e => {
            var errorMsg = e.message
            console.log(errorMsg)
            this.error = errorMsg
            this.showModalProcessingRegistration = false
            this.showModalFailRegistration = true
          })
        }
      },


      /* Method to go to login page */
      goToLogin: function() {
        this.$router.push({
          name: 'Login',
        })
      },


      /* Method to go to dashboard */
      goToDashboard: function() {
        this.$router.push({
          name: 'Dashboard',
          params: {
            id: this.$route.params.id
          }
        })
      },


      /* Method to go to account info */
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
