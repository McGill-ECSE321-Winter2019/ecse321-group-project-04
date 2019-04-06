const functions = require('firebase-functions')
const os = require('os')
const path = require('path')
const cors = require('cors')({ origin: true })
const Busboy = require('busboy')
const fs = require('fs')

var admin = require("firebase-admin");
// Set account details
var serviceAccount = {
  "type": "service_account",
  "project_id": "cooperator-2b466",
  "private_key_id": "652cdd38da81ebd10ae86c6d3edae61a55ed084a",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDHqYAXskXRjhoz\naL60dbFrtLBhqn/ZMDDY5OqlTA5XWJa1Tebi4sfk1cB3F0WHzfRyukMFhAJ+Rt75\nPLA2TmwsM+5yHCPmgy4d0SAFQGoeaPuBiKxrmSYj1l+QSudVrU5HKjiSHm2WlqNw\nZ8kmhqXj+MvlAmqgSuKgFBFAf64KUIXN7/1UTkufsLfRc35b7Fa0DBvI8oyR/slF\nnecaU1ZK4s3tYvijYnD7l6pIkNy14t7lm4DVAslIEYRYGlc4h4wFybnbT1Ig1Gw2\n6wxGqgEDRBHRwHx/A2Oq4aWK9UzA8KDap96UEVggxy2HvM6W5qPeK6oit5t7sb0l\nmIB+jy2BAgMBAAECggEAAZ85p2T1yrKo5Aml/B8v5BoqBpFrvmuKXQu0hofc9AuS\noK8iTi3q6JZuMiV5WuW4MSGnW1qifszpIS0TmSMB/39YlhBEQCT+YBnXn3qhy2S9\neiSZJ9AHtS8yfuswhd5th4Ktu5hQJM2ChG5vvf6bvMOugS9yfGzT+m+5TBI8IKYv\nFMqNcwdjW/6WdDgyheGUs/TWJV319LmbQzhA6Jg9plS2F5ZJ5ec8GqCL9EATT4Gq\ndUKQrA+xWHD2yu0BWm7OR9SRPL9fzPN1T8cE/w7Us5KRWDga6poPijJpVpr/GlH0\n/QzL2U8WWRHpMY3MeG6jm7F8ZUlV+turS3OWEhjPcQKBgQD3vb+pjUdixaCW+2Wy\nq17BTvuDj4grnVWjNZ2/z68HaDTVCL9nQOTfzMujFc4YK6YeJrm69xoNblElVJsy\nnoPGgwDhglEGRh9Af4/xOm3oZaZnf2iexF3TkawOnaSLmREk7T27Roa0jR3aRs8y\nXVB1PqmVi5vFsdhfj8a2R2GIEQKBgQDOUXB0WwuSSTa7GY9WQ/t8g16AoAZRW+6q\ntYYY601yuVqIUnBs1p7xOlhOQrmFs0AvmyhzIXZ1KXkX852fYxykjr+i7r44dtFf\n0I+tOkIzz1L0KeqRpWFdch52RX/v/wCfzA8tMyVTWazdbGNlRww4YXQxfYXLIvv2\niUO6wq4+cQKBgF78+gfgi4Zrj+9T5OJkoqgryHPhDKzsXnF9lUZ+MvX8FoW4HOtS\nDm9g7pNRQPwzgDX/UydEp1CPA730eq0l6hCrGiNc/AQTztfEEUTEym9YyjzUtSBq\n2i7Mo9PWzchYoCIwdILhv7Rqhw+yJq+A5+CMbilQpiFe1z+MuPwa0mKBAoGAYBys\nKGgpcdJNfsefcG5m/1Are/wQNWKAQmIhwAYNpD4YoJzW5rzsei/V509KIbCpXAQJ\nyehyyI2zbab9bLy9ISOmvn49ewxO6TW9WNNk98HDQR8tuP+z5dY9vpcNzH2citSC\n1WmYH8YMs3HEs+9vD+zGNi/lXgZEHks/lAcRKOECgYAArh3Y6ftgZIgyNrLdu0Lo\nkfT02IEcEUgd+obqjfYW5luPHJHOrfUtBtC/MNfXTXYH7phFeIBO9DUAgwCErc64\nuvf1EpBnGaUb/TzHmD51CRAFDM0ahw6PKmRloIwji6ahmVBqI3yifBlTQTKRqWOB\nQ40R2GFD7+DKNKhYgBPxDg==\n-----END PRIVATE KEY-----\n",
  "client_email": "firebase-adminsdk-vlvqo@cooperator-2b466.iam.gserviceaccount.com",
  "client_id": "116675765837513945976",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-vlvqo%40cooperator-2b466.iam.gserviceaccount.com"
};
// Start an instance of the application
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://cooperator-2b466.firebaseio.com"
});
// Get the Google cloud storage bucket
const gcs = admin.storage();
// Generate our own UUID so we can build shorter URL
const UUID = require("uuid-v4");

/*Function that saves documents in cloud storage (RESTful endpoint) */
exports.uploadFile = functions.https.onRequest((req, res) => {
  // enable CORS for browser requests
  cors(req, res, () => {
    if (req.method !== 'POST') {
      return res.status(500).json({
        message: 'Not allowed',
      })
    }
    const busboy = new Busboy({ headers: req.headers })
    let uploadData = null
    // Parse the form data
    busboy.on('file', (fieldname, file, filename, encoding, mimetype) => {
      const filepath = path.join(os.tmpdir(), filename)
      uploadData = { file: filepath, type: mimetype }
      file.pipe(fs.createWriteStream(filepath))
    })

    // Upload the file to storage bucket
    busboy.on('finish', () => {
      let uuid = UUID();
      const bucket = gcs.bucket('cooperator-2b466.appspot.com')
      bucket
      .upload(uploadData.file, {
        uploadType: 'media',
        metadata: {
          metadata: {
            contentType: uploadData.type,
            firebaseStorageDownloadTokens: uuid,
          },
        },
      })
      // Generate the download URLs and send them in the success response
      .then(snapshot => {
        var file = gcs.bucket('cooperator-2b466.appspot.com').file(snapshot[0].name)
        var shortUrl = 'https://firebasestorage.googleapis.com/v0/b/cooperator-2b466.appspot.com/o/'+snapshot[0].name+'?alt=media&token='+uuid
        // This second URL is too long for our database
        var url = file.getSignedUrl({
          action: 'read',
          expires: '03-09-2491'
        }).then(signedUrls => {
          url = signedUrls[0]
          return res.status(200).json({
            message: 'It worked!',
            longURL: url,
            shortURL: shortUrl,
          })
        })
        return res
      })
      // Return Status 500 if request fails
      .catch(err => {
        res.status(500).json({
          error: err,
        })
      })
    })
    busboy.end(req.rawBody)
  })
})