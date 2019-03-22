<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css" integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y"
      crossorigin="anonymous">

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
            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>
            Account
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid" id="task-title">
      <div class="container text-center">
        <div class="row">
          <div class="col-sm-12 text-left">
            <h2>FACC 201 - Report 1</h2>
          </div>
        </div>
      </div>
    </div>

    <div class="container text-center">
      <div class="container-fluid" id="nav-bar">
        <div class="row">
          <div class="col-sm-9">
            <ul class="nav nav-tabs">
              <li v-for="tab in tabs" :class="selectedTab === tab ? 'active' : ''" @click="selectedTab = tab">
                <a>
                  <font size="4">{{ tab }}</font>
                </a>
              </li>
            </ul>
          </div>
          <div class="col-sm-3">
            <ul class="nav navbar-nav navbar-right">
              <li>
                <button type="button" class="btn btn-primary btn-lg">
                  <i class="fas fa-file-upload"></i>
                  Submit Document
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <br />

    <div v-if="selectedTab === 'Overview'" class="container" id="overview-container">
      <div class="row">
        <div class="col-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:20px;">Attached Document</h3>
            </div>
          </div>
        </div>

        <div class="col-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:30px;">Information</h3>
              <h4>Some description of the task</h4>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="selectedTab === 'Submissions'" class="container" id="submissions-container">

      <div class="col">
        <div class="row-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:20px;">
                Document 1
              </h3>
            </div>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="row-sm-6">
          <div class="card border-inverse mb-3">
            <div class="card-body">
              <h3 class="card-title" style="margin-top:10px; margin-bottom:20px;">
                Document 2
              </h3>
            </div>
          </div>
        </div>
      </div>

  </body>

  </html>

</template>

<script>
  export default {
    data: () => ({
      tabs: ['Overview', 'Submissions'],
      selectedTab: 'Overview'
    }),
    methods: {}
  }

</script>

<style>

</style>
