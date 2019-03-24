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
      .assert.title('Co-Op-Erator')
      .waitForElementVisible('body', 2000)
      .assert.urlContains('login')

      // Test student log-in
      .assert.visible('.form-group')
      .assert.visible('#login')
      .waitForElementVisible('input[id="usr"]', 2000)
      .click('button[id="login"]')
      .assert.containsText('#demo', 'Please Enter a Correct Student ID')
      .pause(500)
      .setValue('input[id=usr]', '260654321') // Student account for testing
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

  'RegisterCourse': function (browser) {
    browser
      .waitForElementVisible('#nav-bar button', 2000)
      // Go to AcceptanceForm
      .click('#nav-bar button')
      .waitForElementVisible('#Acceptance-Form', 2000)
      .assert.urlContains('acceptanceform')
      .pause(500)
      // Fill the AcceptanceForm
    var courseId = 'ECSE450';
    browser
      .setValue('input[id="CourseID"]', courseId)
      .setValue('select[id="CoopTerm"]', 1)
    var academicTerm = 'Winter';
    browser
      .click('input[id=' + academicTerm + ']')
      .setValue('input[id="JobID"]', 233233)
      .setValue('input[id="Start"]', '0020190501')
      .setValue('input[id="End"]', '0020190830')
      .click('input[id="W-Yes"]')
      .setValue('input[id="Company-Name"]', 'Microsoft')
      .setValue('input[id="Employer-Email"]', 'ms@gmail.com')
      .setValue('input[id="Address-Line-1"]', '100 Soft Street')
      .setValue('input[id="Address-Line-2"]', '')
      .setValue('input[id="City"]', 'Redmond')
      .setValue('input[id="Province"]', 'Washington')
      .setValue('input[id="Postal-Code"]', '98052')
      .setValue('input[id="Country"]', 'US')
      .setValue('input[id="Employer-Contract"]', 'https://www.microsoft.com')
      .pause(500)
      // Submit the AcceptanceForm
      .click('.panel-body .row .row>div:last-child>button:last-child')
      .pause(500)
      .acceptAlert()
      .pause(1500)
      // Check if Course is added
      .refresh()
      .pause(1500)
    var courseFullName = academicTerm + ' 2019' + ' - ' + courseId;
    browser
      .assert.visible('#course-list')
      .assert.containsText('#course-list', courseFullName)
  },

  'SubmitDocument': function (browser) {
    browser
      .waitForElementVisible('#nav-bar button', 6000)
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

      .pause(2000)
      .end()
  }
}
