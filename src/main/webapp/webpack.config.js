var path = require('path');

module.exports = {
    entry: './static/js/main.js',
    resolve: {
        root: path.resolve('./static/js')
    },
    output: {
        filename: './static/js/bundle.js',
    },
    devtool: 'source-map'
};