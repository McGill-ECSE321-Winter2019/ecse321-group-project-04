// For authoring Nightwatch tests, see
// http://nightwatchjs.org/guide#usage

module.exports = {
  'Login': function (browser) {
    // automatically uses dev Server port from /config.index.js
    // default: http://localhost:8080
    // see nightwatch.conf.js
    const devServer = browser.globals.devServerURL

    browser
      .url(devServer)
      .waitForElementVisible('#app', 6000)
      .assert.title('frontend')
      .waitForElementVisible('body', 2000)
      .assert.urlContains('login')

      // Test student log-in
      .assert.visible('.form-group')
      .assert.visible('#login')
      .waitForElementVisible('input[id="usr"]', 2000)
      .click('button[id="login"]')
      .assert.containsText('#demo', 'Please Enter a Correct Student ID')
      .pause(500)
      .setValue('input[id=usr]', '260123456')
      .pause(500)
      // Go to Dashboard
      .click('button[id="login"]')
      .waitForElementVisible('#nav-bar', 2000)
      .assert.urlContains('dashboard')
  },

  'ViewStudentInfo': function (browser) {
    browser
      .assert.visible('#Account-but')
      .pause(500)
      .click('#Account-but')
      .waitForElementVisible('#Student-Info', 2000)
      .assert.urlContains('studentinformation')
      .assert.visible('.student-card')
      // Go back to DashBoard
      .pause(500)
      .back()
  },

  'SubmitDocument': function (browser) {
    browser
      .waitForElementVisible('#course-list', 2000)
      .element('css selector', '#course-list div', function (result_c) {
        // If there is at least an active Course
        if (result_c.value.ELEMENT) {
          // Go to CourseInfo
          browser
            .click('#course-list>.row:first-child .panel-body>a')
            .waitForElementVisible('#course-title', 2000)
            .assert.urlContains('courseinfo')
            .assert.visible('#nav-bar')
            .click('#nav-bar>.row>div>ul>li:nth-child(2)>a')
            .waitForElementVisible('table', 2000)
            .pause(500)
            .element('css selector', 'table>tbody>tr', function (result_t) {
              // If there is at least a Task
              if (result_t.value.ELEMENT) {
                browser
                  // Go to TaskView
                  .click('table>tbody>tr:first-child>td:first-child a')
                  .waitForElementVisible('#task-submission', 2000)
                  .assert.urlContains('taskview')
                  .assert.visible('button[class="btn btn-success"]')
                  .pause(500)
                  // Open Document Submission modal
                  .click('button[class="btn btn-success"]')
                  .waitForElementVisible('#task-submission .modal-mask', 2000)
                  .assert.visible('.modal-mask .modal-container')
                  .pause(500)
                var docName = 'CoopReport 0001';
                browser
                  .setValue('input[id="docName"]', docName)
                  .setValue('input[id="docURL"]', 'https://www.dropbox.com/sh/coopreport0001')
                  .pause(500)
                  // Submit Document
                  .click('.modal-container button')
                  .pause(500)
                  .assert.containsText('.modal-container button', 'Done')
                  .click('.modal-container button')
                  // Check if Document is added
                  .pause(500)
                  .click('#task-submission>.row ul>li:nth-child(2)')
                  .waitForElementVisible('table', 2000)
                  .assert.containsText('tbody', docName)
              }
            })
        }
      })
  },

  'Logout': function (browser) {
    browser
      .pause(500)
      .assert.visible('button[id="Logout-but"]')
      // Go back to Login 
      .click('button[id="Logout-but"]')
      .waitForElementVisible('button[id="login"]', 2000)
      .assert.urlContains('login')
  },

  'Close': function (browser) {
    browser
      .pause(2000)
      .end()
  }
}
