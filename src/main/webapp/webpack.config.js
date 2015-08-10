var path = require('path');

module.exports = {
    entry: './static/main.js',
    resolve: {
        root: path.resolve('./static')
    },
    output: {
        filename: './static/bundle.js',
    },
    devtool: 'source-map',
    module: {
        loaders: [
            {
                test: /\.html$/,
                loader: "html"
            }
        ]
    }
};