<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.0/css/all.css"
      integrity="sha384-Mmxa0mLqhmOeaE8vgOSbKacftZcsNYDjQzuCOm6D02luYSzBG8vpaOykv9lFQ51Y" crossorigin="anonymous">
  </head>

  <body>

    <!-- Button trigger modal -->
    <button @click="showModal=true" type="button" class="btn btn-primary">
      Add File(s)
    </button>

    <!-- Modal -->
    <div v-if="showModal">
      <transition name="modal">
        <div class="modal-mask">

          <div class="modal-wrapper">
            <div class="modal-container">

              <div class="modal-header">
                <slot name="header">
                  File Name
                </slot>
              </div>

              <div class="modal-body">
                <slot name="body">
                  <div id="upload">
                    <div v-if="!file">
                      <input type="file" @change="onFileChange">
                    </div>
                    <div v-else>
                      <button @click="removeFile">Remove File</button>
                      <span>{{file}}</span>
                    </div>
                  </div>
                </slot>
              </div>

              <div class="modal-footer">
                <slot name="footer">
                  <button class="btn btn-primary btn-sm" @click="showModal=false">
                    OK
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </transition>

    </div>

  </body>

  </html>
</template>

<style>
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
    color: #42b983;
  }

  .modal-body {
    margin: 0;
  }

  .modal-default-button {
    float: right;
  }

  /*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }

</style>

<script>
  export default {
    data: () => ({
      showModal: false,
      file: ''
    }),
    methods: {
      onFileChange(e) {
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length)
          return;
        this.file = files[0].name
      },
      removeFile: function (e) {
        this.file = '';
      }
    }
  }

</script>
