'use strict';

const gulp = require('gulp'),
    browserify = require('browserify'),
    babelify = require('babelify'),
    gutil = require('gulp-util'),
    watch = require('gulp-watch'),
    glob  = require('glob'),
    source = require('vinyl-source-stream');


gulp.task('build', () => {

    let files = glob.sync('./src/*.jsx');
    return browserify({
        entries: files,
        extensions: ['.jsx'],
        debug: true
    })
        .transform('babelify', {
            presets: ['es2015', 'react']
        })
        .bundle()
        .on('error', function(err){
            gutil.log(gutil.colors.red.bold('[browserify error]'));
            gutil.log(err.message);
            this.emit('end');
        })
        .pipe(source('bundle.js'))
        .pipe(gulp.dest('./target/js'));

});

gulp.task('default', function() {
    gulp.run('build');

    gulp.watch(['src/*', 'src/*/*'], function (event){
        gulp.run('build');
    });
});
