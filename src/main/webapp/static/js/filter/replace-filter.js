'use strict';

module.exports = require('js/app.js').filter('replace', function () {
    return function (input, target, replacement) {
        return input.split(target).join(replacement);
    }
});