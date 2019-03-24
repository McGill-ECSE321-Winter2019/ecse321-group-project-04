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
      .waitForElementVisible('#app', 5000)
      .assert.title('frontend')
      .waitForElementVisible('body', 2000)
      .assert.urlContains('login')

      // test student log-in
      .assert.visible('.form-group')
      .assert.visible('#login')
      .waitForElementVisible('input[id="usr"]', 2000)
      .click('button[id="login"]')
      .assert.containsText('#demo', 'Please Enter Correct Sudent ID')
      .pause(500)
      .setValue('input[id=usr]', '260123456')
      .pause(500)
      .click('button[id="login"]')
      .waitForElementVisible('#nav-bar', 2000)
      .assert.urlContains('dashboard')

      .end()
  },

}
