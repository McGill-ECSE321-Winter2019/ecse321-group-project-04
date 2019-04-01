<!DOCTYPE html>

<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css" integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
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

    <div class="container-fluid" id="course-title">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-12">
            <h1>{{ enrollmentName }}</h1>
          </div>
        </div>
      </div>
    </div>

    <div class="container text-center">
      <div class="container-fluid" id="nav-bar">
        <div class="row">
          <div class="col-sm-12">
            <ul class="nav nav-tabs">
              <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab = tab">
                <a>
                  <font size="4">{{ tab }}</font>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <br>

    <transition name="slide-fade" mode="out-in" appear>
      <div v-if="selectedTab === 'Overview'" key="overview" class="container" id="overview-container">
        <div class="row">
          <div class="col-sm-6">
            <div class="card border-inverse mb-3">
              <h3 class="card-title align-items-left d-flex justify-content-left" style="margin-top:23px; margin-bottom:20px; margin-left:13px;">Progress</h3>
              <div class="card-body align-items-center d-flex justify-content-center">
                <!--Important To display the correct % change the
                number inside the span, to make the grean bar grow the
                correct amount change p# in " "-->
                <div :class="getCourseProgress()">
                  <span>{{courseProgress}}</span>
                  <div class="slice">
                    <div class="bar"></div>
                    <div class="fill"></div>
                  </div>
                </div>

              </div>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="card border-inverse mb-3">
              <div class="card-body">
                <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Upcoming Deadlines</h3>
                <div v-if="upcomingDeadlinesNotEmpty">
                  <div v-for="task in upcomingDeadlines">
                    <h4><a href="" @click="goToTask(task.task)">{{task.task.name}}</a></h4>
                    <h4>{{task.dueTime}}</h4>
                    <hr>
                  </div>
                </div>
                <div v-else>
                  <h4 style="color:gray"><em>There are no upcoming deadlines in the next 10 days.</em></h4>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="selectedTab === 'Tasks'" key="tasks" class="container">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Task</h4>
              </th>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Completion Status</h4>
              </th>
              <th scope="col" style="text-align:center; vertical-align:middle">
                <h4>Due Date</h4>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="task in pTasks">
              <td>
                <h4><a href="" @click="goToTask(task)">{{task.name}}</a></h4>
                <h5 style="color:gray">{{task.description}}</h5>
              </td>
              <td style="text-align:center; vertical-align:middle">
                <h5 :style="task.taskStatus == 'COMPLETED' ? 'color:green' : 'color:'">{{taskStatusDisplay[task.taskStatus]}}</h5>
              </td>
              <td style="text-align:center; vertical-align:middle">
                {{ displayDate(task.dueDate) }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div v-if="selectedTab === 'Information'" class="container">
        <div class="container">
          <div class="row">
            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">
                    Course Information
                  </h3>
                  <h4 style="color:gray"><em>Course ID</em></h4>
                  <h4><b>{{courseID}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Course Offering</em></h4>
                  <h4><b>{{courseOffering}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Course Status</em></h4>
                  <h4><b>{{courseStatusDisplay[courseStatus]}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Activity Status</em></h4>
                  <h4><b>{{activityStatus}}</b></h4>
                </div>
              </div>
            </div>

            <div class="col-sm-6">
              <div class="card border-inverse mb-3">
                <div class="card-body">
                  <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">
                    Employer Information
                  </h3>
                  <h4 style="color:gray"><em>Company Name</em></h4>
                  <h4><b>{{employerInfo.name}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Company Address</em></h4>
                  <h4><b>{{employerInfo.address}}</b></h4>
                  <br>
                  <h4 style="color:gray"><em>Employer Email Address</em></h4>
                  <h4><b>{{employerInfo.email}}</b></h4>
                </div>
              </div>
            </div>
          </div>
          <br>
          <br>
          <hr style="margin-bottom:0px">
          <div v-if="courseStatus === 'ONGOING'">
            <div class="row">
              <div class="col-sm-4"></div>
              <div class="col-sm-4" style="text-align:center">
                <button type="button" class="btn btn-danger" @click="showModalWarning=true" id="Logout-but" style="min-width: 100px; margin-left: 5px; margin-top: 35px">
                  <font size="4">Withdraw Course</font>
                </button>
                <div class="col-sm-4"></div>
              </div>
            </div>
          </div>
        </div>
      </div><br>

      </div><br>
    </transition>

    <!-- Warning Modal -->
    <transition name="modal" mode="out-in">
      <div v-if="showModalWarning" key="warning">
        <div class="modal-mask">
          <div class="modal-wrapper" @click="showModalWarning=false">
            <div class="modal-container" @click.stop>
              <div class="modal-header">
                <slot name="header">
                  Course Withdrawal
                </slot>
              </div>
              <div class="modal-body">
                <h4 style="text-align:center">Do you want to withdraw from {{courseID}}?</h4>
                <br>
                <h4 style="text-align:center">This action cannot be undone.</h4>
                <br>
              </div>
              <div style="text-align:center">
                <slot>
                  <button class="btn btn-danger" style="min-width:120px" @click="withdrawCourse">
                    <font size="3">Withdraw Course</font>
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Sucessful Withdrawal Modal -->
      <div v-if="showModalSuccessWithdrawal" key="success">
        <div class="modal-mask">
          <div class="modal-wrapper" @click="showModalSuccessWithdrawal=false">
            <div class="modal-container" @click.stop>
              <div class="modal-header">
                <slot name="header">
                  Successful Withdrawal
                </slot>
              </div>
              <div style="text-align:center">
                <slot>
                  <br>
                  <br>
                  <button class="btn btn-primary" style="min-width:120px" @click="showModalSuccessWithdrawal=false">
                    <font size="3">Done</font>
                  </button>
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

  #logo-button {
    color: #333335;
    background: #333335;
  }

  #Account-but {
    margin-left: 100px;
    min-width: 0%;
  }

  #Logout-but {
    margin-left: 50px;
    min-width: 50%;
  }

  #top-container a {
    display: inline-block;
    margin-top: 35px;
  }

  #course-title h1 {
    text-align: left;
    margin-top: 30px;
    margin-bottom: 40px;
  }

  #nav-bar {
    margin-top: 25px;
    margin-bottom: 40px;
  }

  /* Tab transition animations */
  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .3s ease;
  }

  .slide-fade-enter,
  .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }


  /* CSS Percentage Circle*/
  .rect-auto,
  .c100.p51 .slice,
  .c100.p52 .slice,
  .c100.p53 .slice,
  .c100.p54 .slice,
  .c100.p55 .slice,
  .c100.p56 .slice,
  .c100.p57 .slice,
  .c100.p58 .slice,
  .c100.p59 .slice,
  .c100.p60 .slice,
  .c100.p61 .slice,
  .c100.p62 .slice,
  .c100.p63 .slice,
  .c100.p64 .slice,
  .c100.p65 .slice,
  .c100.p66 .slice,
  .c100.p67 .slice,
  .c100.p68 .slice,
  .c100.p69 .slice,
  .c100.p70 .slice,
  .c100.p71 .slice,
  .c100.p72 .slice,
  .c100.p73 .slice,
  .c100.p74 .slice,
  .c100.p75 .slice,
  .c100.p76 .slice,
  .c100.p77 .slice,
  .c100.p78 .slice,
  .c100.p79 .slice,
  .c100.p80 .slice,
  .c100.p81 .slice,
  .c100.p82 .slice,
  .c100.p83 .slice,
  .c100.p84 .slice,
  .c100.p85 .slice,
  .c100.p86 .slice,
  .c100.p87 .slice,
  .c100.p88 .slice,
  .c100.p89 .slice,
  .c100.p90 .slice,
  .c100.p91 .slice,
  .c100.p92 .slice,
  .c100.p93 .slice,
  .c100.p94 .slice,
  .c100.p95 .slice,
  .c100.p96 .slice,
  .c100.p97 .slice,
  .c100.p98 .slice,
  .c100.p99 .slice,
  .c100.p100 .slice {
    clip: rect(auto, auto, auto, auto);
  }

  .pie,
  .c100 .bar,
  .c100.p51 .fill,
  .c100.p52 .fill,
  .c100.p53 .fill,
  .c100.p54 .fill,
  .c100.p55 .fill,
  .c100.p56 .fill,
  .c100.p57 .fill,
  .c100.p58 .fill,
  .c100.p59 .fill,
  .c100.p60 .fill,
  .c100.p61 .fill,
  .c100.p62 .fill,
  .c100.p63 .fill,
  .c100.p64 .fill,
  .c100.p65 .fill,
  .c100.p66 .fill,
  .c100.p67 .fill,
  .c100.p68 .fill,
  .c100.p69 .fill,
  .c100.p70 .fill,
  .c100.p71 .fill,
  .c100.p72 .fill,
  .c100.p73 .fill,
  .c100.p74 .fill,
  .c100.p75 .fill,
  .c100.p76 .fill,
  .c100.p77 .fill,
  .c100.p78 .fill,
  .c100.p79 .fill,
  .c100.p80 .fill,
  .c100.p81 .fill,
  .c100.p82 .fill,
  .c100.p83 .fill,
  .c100.p84 .fill,
  .c100.p85 .fill,
  .c100.p86 .fill,
  .c100.p87 .fill,
  .c100.p88 .fill,
  .c100.p89 .fill,
  .c100.p90 .fill,
  .c100.p91 .fill,
  .c100.p92 .fill,
  .c100.p93 .fill,
  .c100.p94 .fill,
  .c100.p95 .fill,
  .c100.p96 .fill,
  .c100.p97 .fill,
  .c100.p98 .fill,
  .c100.p99 .fill,
  .c100.p100 .fill {
    position: absolute;
    border: 0.08em solid #307bbb;
    width: 0.84em;
    height: 0.84em;
    clip: rect(0em, 0.5em, 1em, 0em);
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    -ms-border-radius: 50%;
    -o-border-radius: 50%;
    border-radius: 50%;
    -webkit-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }

  .pie-fill,
  .c100.p51 .bar:after,
  .c100.p51 .fill,
  .c100.p52 .bar:after,
  .c100.p52 .fill,
  .c100.p53 .bar:after,
  .c100.p53 .fill,
  .c100.p54 .bar:after,
  .c100.p54 .fill,
  .c100.p55 .bar:after,
  .c100.p55 .fill,
  .c100.p56 .bar:after,
  .c100.p56 .fill,
  .c100.p57 .bar:after,
  .c100.p57 .fill,
  .c100.p58 .bar:after,
  .c100.p58 .fill,
  .c100.p59 .bar:after,
  .c100.p59 .fill,
  .c100.p60 .bar:after,
  .c100.p60 .fill,
  .c100.p61 .bar:after,
  .c100.p61 .fill,
  .c100.p62 .bar:after,
  .c100.p62 .fill,
  .c100.p63 .bar:after,
  .c100.p63 .fill,
  .c100.p64 .bar:after,
  .c100.p64 .fill,
  .c100.p65 .bar:after,
  .c100.p65 .fill,
  .c100.p66 .bar:after,
  .c100.p66 .fill,
  .c100.p67 .bar:after,
  .c100.p67 .fill,
  .c100.p68 .bar:after,
  .c100.p68 .fill,
  .c100.p69 .bar:after,
  .c100.p69 .fill,
  .c100.p70 .bar:after,
  .c100.p70 .fill,
  .c100.p71 .bar:after,
  .c100.p71 .fill,
  .c100.p72 .bar:after,
  .c100.p72 .fill,
  .c100.p73 .bar:after,
  .c100.p73 .fill,
  .c100.p74 .bar:after,
  .c100.p74 .fill,
  .c100.p75 .bar:after,
  .c100.p75 .fill,
  .c100.p76 .bar:after,
  .c100.p76 .fill,
  .c100.p77 .bar:after,
  .c100.p77 .fill,
  .c100.p78 .bar:after,
  .c100.p78 .fill,
  .c100.p79 .bar:after,
  .c100.p79 .fill,
  .c100.p80 .bar:after,
  .c100.p80 .fill,
  .c100.p81 .bar:after,
  .c100.p81 .fill,
  .c100.p82 .bar:after,
  .c100.p82 .fill,
  .c100.p83 .bar:after,
  .c100.p83 .fill,
  .c100.p84 .bar:after,
  .c100.p84 .fill,
  .c100.p85 .bar:after,
  .c100.p85 .fill,
  .c100.p86 .bar:after,
  .c100.p86 .fill,
  .c100.p87 .bar:after,
  .c100.p87 .fill,
  .c100.p88 .bar:after,
  .c100.p88 .fill,
  .c100.p89 .bar:after,
  .c100.p89 .fill,
  .c100.p90 .bar:after,
  .c100.p90 .fill,
  .c100.p91 .bar:after,
  .c100.p91 .fill,
  .c100.p92 .bar:after,
  .c100.p92 .fill,
  .c100.p93 .bar:after,
  .c100.p93 .fill,
  .c100.p94 .bar:after,
  .c100.p94 .fill,
  .c100.p95 .bar:after,
  .c100.p95 .fill,
  .c100.p96 .bar:after,
  .c100.p96 .fill,
  .c100.p97 .bar:after,
  .c100.p97 .fill,
  .c100.p98 .bar:after,
  .c100.p98 .fill,
  .c100.p99 .bar:after,
  .c100.p99 .fill,
  .c100.p100 .bar:after,
  .c100.p100 .fill {
    -webkit-transform: rotate(180deg);
    -moz-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    -o-transform: rotate(180deg);
    transform: rotate(180deg);
  }

  .c100 {
    position: relative;
    font-size: 120px;
    width: 1em;
    height: 1em;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    -ms-border-radius: 50%;
    -o-border-radius: 50%;
    border-radius: 50%;
    float: left;
    margin: 0 0.1em 0.1em 0;
    background-color: #cccccc;
  }

  .c100 *,
  .c100 *:before,
  .c100 *:after {
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
  }

  .c100.big {
    font-size: 240px;
  }

  .c100>span {
    position: absolute;
    width: 100%;
    z-index: 1;
    left: 0;
    top: 0;
    width: 5em;
    line-height: 5em;
    font-size: 0.2em;
    color: #cccccc;
    display: block;
    text-align: center;
    white-space: nowrap;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.2s;
    -moz-transition-duration: 0.2s;
    -o-transition-duration: 0.2s;
    transition-duration: 0.2s;
    -webkit-transition-timing-function: ease-out;
    -moz-transition-timing-function: ease-out;
    -o-transition-timing-function: ease-out;
    transition-timing-function: ease-out;
  }

  .c100:after {
    position: absolute;
    top: 0.08em;
    left: 0.08em;
    display: block;
    content: " ";
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    -ms-border-radius: 50%;
    -o-border-radius: 50%;
    border-radius: 50%;
    background-color: whitesmoke;
    width: 0.84em;
    height: 0.84em;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.2s;
    -moz-transition-duration: 0.2s;
    -o-transition-duration: 0.2s;
    transition-duration: 0.2s;
    -webkit-transition-timing-function: ease-in;
    -moz-transition-timing-function: ease-in;
    -o-transition-timing-function: ease-in;
    transition-timing-function: ease-in;
  }

  /*Stop the green bar from growing back*/
  .c100 .slice {
    position: absolute;
    width: 1em;
    height: 1em;
    clip: rect(0em, 1em, 1em, 0.5em);
  }

  /*Make the bar growing*/
  .c100.p1 .bar {
    -webkit-transform: rotate(3.6deg);
    -moz-transform: rotate(3.6deg);
    -ms-transform: rotate(3.6deg);
    -o-transform: rotate(3.6deg);
    transform: rotate(3.6deg);
  }

  .c100.p2 .bar {
    -webkit-transform: rotate(7.2deg);
    -moz-transform: rotate(7.2deg);
    -ms-transform: rotate(7.2deg);
    -o-transform: rotate(7.2deg);
    transform: rotate(7.2deg);
  }

  .c100.p3 .bar {
    -webkit-transform: rotate(10.8deg);
    -moz-transform: rotate(10.8deg);
    -ms-transform: rotate(10.8deg);
    -o-transform: rotate(10.8deg);
    transform: rotate(10.8deg);
  }

  .c100.p4 .bar {
    -webkit-transform: rotate(14.4deg);
    -moz-transform: rotate(14.4deg);
    -ms-transform: rotate(14.4deg);
    -o-transform: rotate(14.4deg);
    transform: rotate(14.4deg);
  }

  .c100.p5 .bar {
    -webkit-transform: rotate(18deg);
    -moz-transform: rotate(18deg);
    -ms-transform: rotate(18deg);
    -o-transform: rotate(18deg);
    transform: rotate(18deg);
  }

  .c100.p6 .bar {
    -webkit-transform: rotate(21.6deg);
    -moz-transform: rotate(21.6deg);
    -ms-transform: rotate(21.6deg);
    -o-transform: rotate(21.6deg);
    transform: rotate(21.6deg);
  }

  .c100.p7 .bar {
    -webkit-transform: rotate(25.2deg);
    -moz-transform: rotate(25.2deg);
    -ms-transform: rotate(25.2deg);
    -o-transform: rotate(25.2deg);
    transform: rotate(25.2deg);
  }

  .c100.p8 .bar {
    -webkit-transform: rotate(28.8deg);
    -moz-transform: rotate(28.8deg);
    -ms-transform: rotate(28.8deg);
    -o-transform: rotate(28.8deg);
    transform: rotate(28.8deg);
  }

  .c100.p9 .bar {
    -webkit-transform: rotate(32.4deg);
    -moz-transform: rotate(32.4deg);
    -ms-transform: rotate(32.4deg);
    -o-transform: rotate(32.4deg);
    transform: rotate(32.4deg);
  }

  .c100.p10 .bar {
    -webkit-transform: rotate(36deg);
    -moz-transform: rotate(36deg);
    -ms-transform: rotate(36deg);
    -o-transform: rotate(36deg);
    transform: rotate(36deg);
  }

  .c100.p11 .bar {
    -webkit-transform: rotate(39.6deg);
    -moz-transform: rotate(39.6deg);
    -ms-transform: rotate(39.6deg);
    -o-transform: rotate(39.6deg);
    transform: rotate(39.6deg);
  }

  .c100.p12 .bar {
    -webkit-transform: rotate(43.2deg);
    -moz-transform: rotate(43.2deg);
    -ms-transform: rotate(43.2deg);
    -o-transform: rotate(43.2deg);
    transform: rotate(43.2deg);
  }

  .c100.p13 .bar {
    -webkit-transform: rotate(46.8deg);
    -moz-transform: rotate(46.8deg);
    -ms-transform: rotate(46.8deg);
    -o-transform: rotate(46.8deg);
    transform: rotate(46.8deg);
  }

  .c100.p14 .bar {
    -webkit-transform: rotate(50.4deg);
    -moz-transform: rotate(50.4deg);
    -ms-transform: rotate(50.4deg);
    -o-transform: rotate(50.4deg);
    transform: rotate(50.4deg);
  }

  .c100.p15 .bar {
    -webkit-transform: rotate(54deg);
    -moz-transform: rotate(54deg);
    -ms-transform: rotate(54deg);
    -o-transform: rotate(54deg);
    transform: rotate(54deg);
  }

  .c100.p16 .bar {
    -webkit-transform: rotate(57.6deg);
    -moz-transform: rotate(57.6deg);
    -ms-transform: rotate(57.6deg);
    -o-transform: rotate(57.6deg);
    transform: rotate(57.6deg);
  }

  .c100.p17 .bar {
    -webkit-transform: rotate(61.2deg);
    -moz-transform: rotate(61.2deg);
    -ms-transform: rotate(61.2deg);
    -o-transform: rotate(61.2deg);
    transform: rotate(61.2deg);
  }

  .c100.p18 .bar {
    -webkit-transform: rotate(64.8deg);
    -moz-transform: rotate(64.8deg);
    -ms-transform: rotate(64.8deg);
    -o-transform: rotate(64.8deg);
    transform: rotate(64.8deg);
  }

  .c100.p19 .bar {
    -webkit-transform: rotate(68.4deg);
    -moz-transform: rotate(68.4deg);
    -ms-transform: rotate(68.4deg);
    -o-transform: rotate(68.4deg);
    transform: rotate(68.4deg);
  }

  .c100.p20 .bar {
    -webkit-transform: rotate(72deg);
    -moz-transform: rotate(72deg);
    -ms-transform: rotate(72deg);
    -o-transform: rotate(72deg);
    transform: rotate(72deg);
  }

  .c100.p21 .bar {
    -webkit-transform: rotate(75.6deg);
    -moz-transform: rotate(75.6deg);
    -ms-transform: rotate(75.6deg);
    -o-transform: rotate(75.6deg);
    transform: rotate(75.6deg);
  }

  .c100.p22 .bar {
    -webkit-transform: rotate(79.2deg);
    -moz-transform: rotate(79.2deg);
    -ms-transform: rotate(79.2deg);
    -o-transform: rotate(79.2deg);
    transform: rotate(79.2deg);
  }

  .c100.p23 .bar {
    -webkit-transform: rotate(82.8deg);
    -moz-transform: rotate(82.8deg);
    -ms-transform: rotate(82.8deg);
    -o-transform: rotate(82.8deg);
    transform: rotate(82.8deg);
  }

  .c100.p24 .bar {
    -webkit-transform: rotate(86.4deg);
    -moz-transform: rotate(86.4deg);
    -ms-transform: rotate(86.4deg);
    -o-transform: rotate(86.4deg);
    transform: rotate(86.4deg);
  }

  .c100.p25 .bar {
    -webkit-transform: rotate(90deg);
    -moz-transform: rotate(90deg);
    -ms-transform: rotate(90deg);
    -o-transform: rotate(90deg);
    transform: rotate(90deg);
  }

  .c100.p26 .bar {
    -webkit-transform: rotate(93.6deg);
    -moz-transform: rotate(93.6deg);
    -ms-transform: rotate(93.6deg);
    -o-transform: rotate(93.6deg);
    transform: rotate(93.6deg);
  }

  .c100.p27 .bar {
    -webkit-transform: rotate(97.2deg);
    -moz-transform: rotate(97.2deg);
    -ms-transform: rotate(97.2deg);
    -o-transform: rotate(97.2deg);
    transform: rotate(97.2deg);
  }

  .c100.p28 .bar {
    -webkit-transform: rotate(100.8deg);
    -moz-transform: rotate(100.8deg);
    -ms-transform: rotate(100.8deg);
    -o-transform: rotate(100.8deg);
    transform: rotate(100.8deg);
  }

  .c100.p29 .bar {
    -webkit-transform: rotate(104.4deg);
    -moz-transform: rotate(104.4deg);
    -ms-transform: rotate(104.4deg);
    -o-transform: rotate(104.4deg);
    transform: rotate(104.4deg);
  }

  .c100.p30 .bar {
    -webkit-transform: rotate(108deg);
    -moz-transform: rotate(108deg);
    -ms-transform: rotate(108deg);
    -o-transform: rotate(108deg);
    transform: rotate(108deg);
  }

  .c100.p31 .bar {
    -webkit-transform: rotate(111.6deg);
    -moz-transform: rotate(111.6deg);
    -ms-transform: rotate(111.6deg);
    -o-transform: rotate(111.6deg);
    transform: rotate(111.6deg);
  }

  .c100.p32 .bar {
    -webkit-transform: rotate(115.2deg);
    -moz-transform: rotate(115.2deg);
    -ms-transform: rotate(115.2deg);
    -o-transform: rotate(115.2deg);
    transform: rotate(115.2deg);
  }

  .c100.p33 .bar {
    -webkit-transform: rotate(118.8deg);
    -moz-transform: rotate(118.8deg);
    -ms-transform: rotate(118.8deg);
    -o-transform: rotate(118.8deg);
    transform: rotate(118.8deg);
  }

  .c100.p34 .bar {
    -webkit-transform: rotate(122.4deg);
    -moz-transform: rotate(122.4deg);
    -ms-transform: rotate(122.4deg);
    -o-transform: rotate(122.4deg);
    transform: rotate(122.4deg);
  }

  .c100.p35 .bar {
    -webkit-transform: rotate(126deg);
    -moz-transform: rotate(126deg);
    -ms-transform: rotate(126deg);
    -o-transform: rotate(126deg);
    transform: rotate(126deg);
  }

  .c100.p36 .bar {
    -webkit-transform: rotate(129.6deg);
    -moz-transform: rotate(129.6deg);
    -ms-transform: rotate(129.6deg);
    -o-transform: rotate(129.6deg);
    transform: rotate(129.6deg);
  }

  .c100.p37 .bar {
    -webkit-transform: rotate(133.2deg);
    -moz-transform: rotate(133.2deg);
    -ms-transform: rotate(133.2deg);
    -o-transform: rotate(133.2deg);
    transform: rotate(133.2deg);
  }

  .c100.p38 .bar {
    -webkit-transform: rotate(136.8deg);
    -moz-transform: rotate(136.8deg);
    -ms-transform: rotate(136.8deg);
    -o-transform: rotate(136.8deg);
    transform: rotate(136.8deg);
  }

  .c100.p39 .bar {
    -webkit-transform: rotate(140.4deg);
    -moz-transform: rotate(140.4deg);
    -ms-transform: rotate(140.4deg);
    -o-transform: rotate(140.4deg);
    transform: rotate(140.4deg);
  }

  .c100.p40 .bar {
    -webkit-transform: rotate(144deg);
    -moz-transform: rotate(144deg);
    -ms-transform: rotate(144deg);
    -o-transform: rotate(144deg);
    transform: rotate(144deg);
  }

  .c100.p41 .bar {
    -webkit-transform: rotate(147.6deg);
    -moz-transform: rotate(147.6deg);
    -ms-transform: rotate(147.6deg);
    -o-transform: rotate(147.6deg);
    transform: rotate(147.6deg);
  }

  .c100.p42 .bar {
    -webkit-transform: rotate(151.2deg);
    -moz-transform: rotate(151.2deg);
    -ms-transform: rotate(151.2deg);
    -o-transform: rotate(151.2deg);
    transform: rotate(151.2deg);
  }

  .c100.p43 .bar {
    -webkit-transform: rotate(154.8deg);
    -moz-transform: rotate(154.8deg);
    -ms-transform: rotate(154.8deg);
    -o-transform: rotate(154.8deg);
    transform: rotate(154.8deg);
  }

  .c100.p44 .bar {
    -webkit-transform: rotate(158.4deg);
    -moz-transform: rotate(158.4deg);
    -ms-transform: rotate(158.4deg);
    -o-transform: rotate(158.4deg);
    transform: rotate(158.4deg);
  }

  .c100.p45 .bar {
    -webkit-transform: rotate(162deg);
    -moz-transform: rotate(162deg);
    -ms-transform: rotate(162deg);
    -o-transform: rotate(162deg);
    transform: rotate(162deg);
  }

  .c100.p46 .bar {
    -webkit-transform: rotate(165.6deg);
    -moz-transform: rotate(165.6deg);
    -ms-transform: rotate(165.6deg);
    -o-transform: rotate(165.6deg);
    transform: rotate(165.6deg);
  }

  .c100.p47 .bar {
    -webkit-transform: rotate(169.2deg);
    -moz-transform: rotate(169.2deg);
    -ms-transform: rotate(169.2deg);
    -o-transform: rotate(169.2deg);
    transform: rotate(169.2deg);
  }

  .c100.p48 .bar {
    -webkit-transform: rotate(172.8deg);
    -moz-transform: rotate(172.8deg);
    -ms-transform: rotate(172.8deg);
    -o-transform: rotate(172.8deg);
    transform: rotate(172.8deg);
  }

  .c100.p49 .bar {
    -webkit-transform: rotate(176.4deg);
    -moz-transform: rotate(176.4deg);
    -ms-transform: rotate(176.4deg);
    -o-transform: rotate(176.4deg);
    transform: rotate(176.4deg);
  }

  .c100.p50 .bar {
    -webkit-transform: rotate(180deg);
    -moz-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    -o-transform: rotate(180deg);
    transform: rotate(180deg);
  }

  .c100.p51 .bar {
    -webkit-transform: rotate(183.6deg);
    -moz-transform: rotate(183.6deg);
    -ms-transform: rotate(183.6deg);
    -o-transform: rotate(183.6deg);
    transform: rotate(183.6deg);
  }

  .c100.p52 .bar {
    -webkit-transform: rotate(187.2deg);
    -moz-transform: rotate(187.2deg);
    -ms-transform: rotate(187.2deg);
    -o-transform: rotate(187.2deg);
    transform: rotate(187.2deg);
  }

  .c100.p53 .bar {
    -webkit-transform: rotate(190.8deg);
    -moz-transform: rotate(190.8deg);
    -ms-transform: rotate(190.8deg);
    -o-transform: rotate(190.8deg);
    transform: rotate(190.8deg);
  }

  .c100.p54 .bar {
    -webkit-transform: rotate(194.4deg);
    -moz-transform: rotate(194.4deg);
    -ms-transform: rotate(194.4deg);
    -o-transform: rotate(194.4deg);
    transform: rotate(194.4deg);
  }

  .c100.p55 .bar {
    -webkit-transform: rotate(198deg);
    -moz-transform: rotate(198deg);
    -ms-transform: rotate(198deg);
    -o-transform: rotate(198deg);
    transform: rotate(198deg);
  }

  .c100.p56 .bar {
    -webkit-transform: rotate(201.6deg);
    -moz-transform: rotate(201.6deg);
    -ms-transform: rotate(201.6deg);
    -o-transform: rotate(201.6deg);
    transform: rotate(201.6deg);
  }

  .c100.p57 .bar {
    -webkit-transform: rotate(205.2deg);
    -moz-transform: rotate(205.2deg);
    -ms-transform: rotate(205.2deg);
    -o-transform: rotate(205.2deg);
    transform: rotate(205.2deg);
  }

  .c100.p58 .bar {
    -webkit-transform: rotate(208.8deg);
    -moz-transform: rotate(208.8deg);
    -ms-transform: rotate(208.8deg);
    -o-transform: rotate(208.8deg);
    transform: rotate(208.8deg);
  }

  .c100.p59 .bar {
    -webkit-transform: rotate(212.4deg);
    -moz-transform: rotate(212.4deg);
    -ms-transform: rotate(212.4deg);
    -o-transform: rotate(212.4deg);
    transform: rotate(212.4deg);
  }

  .c100.p60 .bar {
    -webkit-transform: rotate(216deg);
    -moz-transform: rotate(216deg);
    -ms-transform: rotate(216deg);
    -o-transform: rotate(216deg);
    transform: rotate(216deg);
  }

  .c100.p61 .bar {
    -webkit-transform: rotate(219.6deg);
    -moz-transform: rotate(219.6deg);
    -ms-transform: rotate(219.6deg);
    -o-transform: rotate(219.6deg);
    transform: rotate(219.6deg);
  }

  .c100.p62 .bar {
    -webkit-transform: rotate(223.2deg);
    -moz-transform: rotate(223.2deg);
    -ms-transform: rotate(223.2deg);
    -o-transform: rotate(223.2deg);
    transform: rotate(223.2deg);
  }

  .c100.p63 .bar {
    -webkit-transform: rotate(226.8deg);
    -moz-transform: rotate(226.8deg);
    -ms-transform: rotate(226.8deg);
    -o-transform: rotate(226.8deg);
    transform: rotate(226.8deg);
  }

  .c100.p64 .bar {
    -webkit-transform: rotate(230.4deg);
    -moz-transform: rotate(230.4deg);
    -ms-transform: rotate(230.4deg);
    -o-transform: rotate(230.4deg);
    transform: rotate(230.4deg);
  }

  .c100.p65 .bar {
    -webkit-transform: rotate(234deg);
    -moz-transform: rotate(234deg);
    -ms-transform: rotate(234deg);
    -o-transform: rotate(234deg);
    transform: rotate(234deg);
  }

  .c100.p66 .bar {
    -webkit-transform: rotate(237.6deg);
    -moz-transform: rotate(237.6deg);
    -ms-transform: rotate(237.6deg);
    -o-transform: rotate(237.6deg);
    transform: rotate(237.6deg);
  }

  .c100.p67 .bar {
    -webkit-transform: rotate(241.2deg);
    -moz-transform: rotate(241.2deg);
    -ms-transform: rotate(241.2deg);
    -o-transform: rotate(241.2deg);
    transform: rotate(241.2deg);
  }

  .c100.p68 .bar {
    -webkit-transform: rotate(244.8deg);
    -moz-transform: rotate(244.8deg);
    -ms-transform: rotate(244.8deg);
    -o-transform: rotate(244.8deg);
    transform: rotate(244.8deg);
  }

  .c100.p69 .bar {
    -webkit-transform: rotate(248.4deg);
    -moz-transform: rotate(248.4deg);
    -ms-transform: rotate(248.4deg);
    -o-transform: rotate(248.4deg);
    transform: rotate(248.4deg);
  }

  .c100.p70 .bar {
    -webkit-transform: rotate(252deg);
    -moz-transform: rotate(252deg);
    -ms-transform: rotate(252deg);
    -o-transform: rotate(252deg);
    transform: rotate(252deg);
  }

  .c100.p71 .bar {
    -webkit-transform: rotate(255.6deg);
    -moz-transform: rotate(255.6deg);
    -ms-transform: rotate(255.6deg);
    -o-transform: rotate(255.6deg);
    transform: rotate(255.6deg);
  }

  .c100.p72 .bar {
    -webkit-transform: rotate(259.2deg);
    -moz-transform: rotate(259.2deg);
    -ms-transform: rotate(259.2deg);
    -o-transform: rotate(259.2deg);
    transform: rotate(259.2deg);
  }

  .c100.p73 .bar {
    -webkit-transform: rotate(262.8deg);
    -moz-transform: rotate(262.8deg);
    -ms-transform: rotate(262.8deg);
    -o-transform: rotate(262.8deg);
    transform: rotate(262.8deg);
  }

  .c100.p74 .bar {
    -webkit-transform: rotate(266.4deg);
    -moz-transform: rotate(266.4deg);
    -ms-transform: rotate(266.4deg);
    -o-transform: rotate(266.4deg);
    transform: rotate(266.4deg);
  }

  .c100.p75 .bar {
    -webkit-transform: rotate(270deg);
    -moz-transform: rotate(270deg);
    -ms-transform: rotate(270deg);
    -o-transform: rotate(270deg);
    transform: rotate(270deg);
  }

  .c100.p76 .bar {
    -webkit-transform: rotate(273.6deg);
    -moz-transform: rotate(273.6deg);
    -ms-transform: rotate(273.6deg);
    -o-transform: rotate(273.6deg);
    transform: rotate(273.6deg);
  }

  .c100.p77 .bar {
    -webkit-transform: rotate(277.2deg);
    -moz-transform: rotate(277.2deg);
    -ms-transform: rotate(277.2deg);
    -o-transform: rotate(277.2deg);
    transform: rotate(277.2deg);
  }

  .c100.p78 .bar {
    -webkit-transform: rotate(280.8deg);
    -moz-transform: rotate(280.8deg);
    -ms-transform: rotate(280.8deg);
    -o-transform: rotate(280.8deg);
    transform: rotate(280.8deg);
  }

  .c100.p79 .bar {
    -webkit-transform: rotate(284.4deg);
    -moz-transform: rotate(284.4deg);
    -ms-transform: rotate(284.4deg);
    -o-transform: rotate(284.4deg);
    transform: rotate(284.4deg);
  }

  .c100.p80 .bar {
    -webkit-transform: rotate(288deg);
    -moz-transform: rotate(288deg);
    -ms-transform: rotate(288deg);
    -o-transform: rotate(288deg);
    transform: rotate(288deg);
  }

  .c100.p81 .bar {
    -webkit-transform: rotate(291.6deg);
    -moz-transform: rotate(291.6deg);
    -ms-transform: rotate(291.6deg);
    -o-transform: rotate(291.6deg);
    transform: rotate(291.6deg);
  }

  .c100.p82 .bar {
    -webkit-transform: rotate(295.2deg);
    -moz-transform: rotate(295.2deg);
    -ms-transform: rotate(295.2deg);
    -o-transform: rotate(295.2deg);
    transform: rotate(295.2deg);
  }

  .c100.p83 .bar {
    -webkit-transform: rotate(298.8deg);
    -moz-transform: rotate(298.8deg);
    -ms-transform: rotate(298.8deg);
    -o-transform: rotate(298.8deg);
    transform: rotate(298.8deg);
  }

  .c100.p84 .bar {
    -webkit-transform: rotate(302.4deg);
    -moz-transform: rotate(302.4deg);
    -ms-transform: rotate(302.4deg);
    -o-transform: rotate(302.4deg);
    transform: rotate(302.4deg);
  }

  .c100.p85 .bar {
    -webkit-transform: rotate(306deg);
    -moz-transform: rotate(306deg);
    -ms-transform: rotate(306deg);
    -o-transform: rotate(306deg);
    transform: rotate(306deg);
  }

  .c100.p86 .bar {
    -webkit-transform: rotate(309.6deg);
    -moz-transform: rotate(309.6deg);
    -ms-transform: rotate(309.6deg);
    -o-transform: rotate(309.6deg);
    transform: rotate(309.6deg);
  }

  .c100.p87 .bar {
    -webkit-transform: rotate(313.2deg);
    -moz-transform: rotate(313.2deg);
    -ms-transform: rotate(313.2deg);
    -o-transform: rotate(313.2deg);
    transform: rotate(313.2deg);
  }

  .c100.p88 .bar {
    -webkit-transform: rotate(316.8deg);
    -moz-transform: rotate(316.8deg);
    -ms-transform: rotate(316.8deg);
    -o-transform: rotate(316.8deg);
    transform: rotate(316.8deg);
  }

  .c100.p89 .bar {
    -webkit-transform: rotate(320.4deg);
    -moz-transform: rotate(320.4deg);
    -ms-transform: rotate(320.4deg);
    -o-transform: rotate(320.4deg);
    transform: rotate(320.4deg);
  }

  .c100.p90 .bar {
    -webkit-transform: rotate(324deg);
    -moz-transform: rotate(324deg);
    -ms-transform: rotate(324deg);
    -o-transform: rotate(324deg);
    transform: rotate(324deg);
  }

  .c100.p91 .bar {
    -webkit-transform: rotate(327.6deg);
    -moz-transform: rotate(327.6deg);
    -ms-transform: rotate(327.6deg);
    -o-transform: rotate(327.6deg);
    transform: rotate(327.6deg);
  }

  .c100.p92 .bar {
    -webkit-transform: rotate(331.2deg);
    -moz-transform: rotate(331.2deg);
    -ms-transform: rotate(331.2deg);
    -o-transform: rotate(331.2deg);
    transform: rotate(331.2deg);
  }

  .c100.p93 .bar {
    -webkit-transform: rotate(334.8deg);
    -moz-transform: rotate(334.8deg);
    -ms-transform: rotate(334.8deg);
    -o-transform: rotate(334.8deg);
    transform: rotate(334.8deg);
  }

  .c100.p94 .bar {
    -webkit-transform: rotate(338.4deg);
    -moz-transform: rotate(338.4deg);
    -ms-transform: rotate(338.4deg);
    -o-transform: rotate(338.4deg);
    transform: rotate(338.4deg);
  }

  .c100.p95 .bar {
    -webkit-transform: rotate(342deg);
    -moz-transform: rotate(342deg);
    -ms-transform: rotate(342deg);
    -o-transform: rotate(342deg);
    transform: rotate(342deg);
  }

  .c100.p96 .bar {
    -webkit-transform: rotate(345.6deg);
    -moz-transform: rotate(345.6deg);
    -ms-transform: rotate(345.6deg);
    -o-transform: rotate(345.6deg);
    transform: rotate(345.6deg);
  }

  .c100.p97 .bar {
    -webkit-transform: rotate(349.2deg);
    -moz-transform: rotate(349.2deg);
    -ms-transform: rotate(349.2deg);
    -o-transform: rotate(349.2deg);
    transform: rotate(349.2deg);
  }

  .c100.p98 .bar {
    -webkit-transform: rotate(352.8deg);
    -moz-transform: rotate(352.8deg);
    -ms-transform: rotate(352.8deg);
    -o-transform: rotate(352.8deg);
    transform: rotate(352.8deg);
  }

  .c100.p99 .bar {
    -webkit-transform: rotate(356.4deg);
    -moz-transform: rotate(356.4deg);
    -ms-transform: rotate(356.4deg);
    -o-transform: rotate(356.4deg);
    transform: rotate(356.4deg);
  }

  .c100.p100 .bar {
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }

  .c100:hover {
    cursor: default;
  }

  .c100:hover>span {
    width: 3.33em;
    line-height: 3.33em;
    font-size: 0.3em;
    color: #307bbb;
  }

  .c100:hover:after {
    top: 0.04em;
    left: 0.04em;
    width: 0.92em;
    height: 0.92em;
  }

  /*Fill in green color*/
  .c100.green .bar,
  .c100.green .fill {
    border-color: #1f67c1 !important;
  }

  .c100.green:hover>span {
    color: #1f67c1;
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
  import Router from 'vue-router'
  import axios from 'axios'
  var config = require('../../config')
  var moment = require('moment')
  const UD_DAYS = 10

  /* Date handling */
  var months = {
    1: 'Jan',
    2: 'Feb',
    3: 'Mar',
    4: 'Apr',
    5: 'May',
    6: 'Jun',
    7: 'Jul',
    8: 'Aug',
    9: 'Sep',
    10: 'Oct',
    11: 'Nov',
    12: 'Dec'
  }

  function parseDate(dateString) {
    var valsString = dateString.split('-');
    var vals = valsString.map(function(n) {
      return parseInt(n, 10);
    })
    var ret = {
      year: vals[0],
      month: vals[1],
      day: vals[2]
    };
    return ret;
  }

  function compareDates(d1, d2) {
    // Compare the years
    if (d1.year > d2.year) {
      return 1;
    } else if (d1.year < d2.year) {
      return -1;
    }

    // If equal compare the months
    if (d1.month > d2.month) {
      return 1;
    } else if (d1.month < d2.month) {
      return -1;
    }

    // If equal compare the days
    if (d1.day > d2.day) {
      return 1;
    } else if (d1.day < d2.day) {
      return -1;
    }
    // Else they are the same
    return 0;
  }

  /* AXIOS object configuration */
  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost // + ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl
  })

  /* Get the upcoming deadlines */
  function getUpcomingDeadlines(tasks) {
    var pTasks = []
    for (var i in tasks) {
      pTasks.push(tasks[i])
    }
    pTasks.sort(function(a, b) {
      return compareDates(parseDate(a.dueDate), parseDate(b.dueDate))
    })

    var ret = []
    var currDate = moment()
    for (var i in pTasks) {
      var dd = moment(pTasks[i].dueDate)
      var timeDiff = Math.floor(moment.duration(dd.diff(currDate)).asDays() + 1)
      if (timeDiff < 1 && timeDiff >= 0) {
        ret.push({
          task: pTasks[i],
          dueTime: 'Due Today'
        })
      } else if (timeDiff < 2 && timeDiff > 0) {
        ret.push({
          task: pTasks[i],
          dueTime: 'Due Tomorrow'
        })
      } else if (timeDiff <= 10 && timeDiff > 0) {
        ret.push({
          task: pTasks[i],
          dueTime: 'Due In ' + timeDiff + ' Days'
        })
      }
    }

    console.log(ret.length)

    return ret
  }

  export default {
    name: 'courseinfo',
    data() {
      return {
        tabs: ['Overview', 'Tasks', 'Information'],
        selectedTab: 'Overview',
        taskStatusDisplay: {
          COMPLETED: 'Completed',
          INCOMPLETE: 'Incomplete',
          LATE_COMPLETED: 'Late Completed'
        },
        courseStatusDisplay: {
          PASSED: 'Passed',
          FAILED: 'Failed',
          WITHDRAWED: 'Withdrew',
          ONGOING: 'Ongoing'
        },
        courseOffering: {},
        tasks: [],
        enrollmentName: null,
        courseID: null,
        courseOffering: null,
        courseStatus: null,
        activityStatus: null,
        employerInfo: null,
        courseProgress: null,
        upcomingDeadlinesNotEmpty: true,
        showModalWarning: false,
        showModalSuccessWithdrawal: false
      }
    },
    created() {
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id)
        .then(response => {
          this.enrollment = response.data

          // Get the enrollment name
          var enrollmentCode = this.enrollment._links.self.href.split('/')
          enrollmentCode = enrollmentCode[enrollmentCode.length - 1]
          var offeringCode = enrollmentCode.split('-')
          offeringCode.shift()
          var term = offeringCode.pop()
          var courseCode = offeringCode.join('')
          var displayName = null
          switch (term[0]) {
            case 'W':
              displayName = 'Winter '
              break;
            case 'F':
              displayName = 'Fall '
              break;
            case 'S':
              displayName = 'Summer '
              break;
          }
          displayName += '20' + term.slice(1) + ' - ' + courseCode

          this.enrollmentName = displayName
          this.courseID = displayName.split('-').pop().trim()
          this.courseOffering = displayName.split('-').shift().trim()
          this.courseStatus = this.enrollment.status
          this.activityStatus = this.enrollment.active ? 'Active' : 'Inactive'
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        })
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id + `/courseTasks`)
        .then(response => {
          this.tasks = response.data._embedded.tasks;
          var ret = getUpcomingDeadlines(this.tasks)
          if (ret.length == 0) {
            this.upcomingDeadlinesNotEmpty = false
          } else {
            this.upcomingDeadlinesNotEmpty = true
          }
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        })
      AXIOS.get(`/studentEnrollments/` + this.$route.params.id + `/studentEmployer`)
        .then(response => {
          var employer = response.data;
          this.employerInfo = {
            name: employer.name,
            address: employer.address,
            email: employer._links.self.href.split('/').pop()
          }
        })
        .catch(e => {
          var errorMsg = e.message;
          console.log(errorMsg);
          this.errorPerson = errorMsg;
        })
    },
    methods: {
      goToTask: function(task) {
        var enrollmentID = this.enrollment._links.self.href.split('/').pop()
        var taskID = task._links.self.href.split('/').pop()
        console.log(taskID)
        this.$router.push({
          name: 'TaskView',
          params: {
            'enrollmentID': enrollmentID,
            id: taskID
          }
        })
      },
      goToAccount: function() {
        var studentID = this.enrollment._links.self.href.split('/').pop().split('-').shift()
        this.$router.push({
          name: 'StudentInformation',
          params: {
            id: studentID
          }
        })
      },
      goToDashboard: function() {
        var studentID = this.enrollment._links.self.href.split('/').pop().split('-').shift()
        this.$router.push({
          name: 'Dashboard',
          params: {
            id: studentID
          }
        })
      },
      goToLogin: function() {
        this.$router.push({
          name: 'Login',
          params: {
            id: this.$route.params.id
          }
        })
      },
      withdrawCourse: function() {
        var enrollmentCode = this.enrollment._links.self.href.split('/').pop()
        AXIOS.put(`studentEnrollment?enrollmentID=` + enrollmentCode + `&active=false&status=WITHDRAWED`)
          .then(response => {
            // Update the enrollment info
            AXIOS.get(`/studentEnrollments/` + this.$route.params.id)
              .then(response => {
                this.enrollment = response.data
                this.courseStatus = this.enrollment.status
                this.activityStatus = this.enrollment.active ? 'Active' : 'Inactive'
              })
              .catch(e => {
                var errorMsg = e.message
                console.log(errorMsg)
                this.errorPerson = errorMsg
              })

            // Show the success withdrawal modal
            this.showModalWarning = false
            this.showModalSuccessWithdrawal = true
          })
          .catch(e => {
            var errorMsg = e.message;
            console.log(errorMsg);
            this.errorPerson = errorMsg;
          })
      },
      displayDate: function(d) {
        return moment(d).format("MMM Do, YYYY")
      },
      getCourseProgress: function() {
        var progress = 100
        if (this.tasks.length != 0) {
          var completed = 0
          for (var i in this.tasks) {
            if (this.tasks[i].taskStatus != 'INCOMPLETE') {
              completed++
            }
          }
          progress = Math.round((completed / this.tasks.length) * 100)
        }
        this.courseProgress = progress + '%'
        return 'c100 p' + progress + ' big green'
      }
    },
    computed: {
      pTasks: function() {
        var pt = []
        for (var i in this.tasks) {
          pt.push(this.tasks[i])
        }
        pt.sort(function(a, b) {
          return compareDates(parseDate(a.dueDate), parseDate(b.dueDate))
        })
        return pt
      },
      upcomingDeadlines: function() {
        var ret = getUpcomingDeadlines(this.tasks)
        if (ret.length == 0) {
          this.upcomingDeadlinesNotEmpty = false
        } else {
          this.upcomingDeadlinesNotEmpty = true
        }
        return ret
      },
    }
  }
</script>
