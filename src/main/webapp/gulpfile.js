var gulp = require('gulp');
var webserver = require('gulp-webserver');
 
gulp.task('webserver', function() {
  gulp.src('')
    .pipe(webserver({
      host: '192.168.0.153', port: '80',
      livereload: true,
      directoryListing: false,
      open: true,
      fallback: 'index.html'
    }));
});