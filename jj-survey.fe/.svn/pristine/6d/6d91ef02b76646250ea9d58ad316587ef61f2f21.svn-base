// const { BundleAnalyzerPlugin } = require('webpack-bundle-analyzer');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const path = require('path');

module.exports = {
    context: path.resolve(__dirname, 'src'),
    entry: {
        "index": './index.tsx'
    },
    // devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname + '/../../jj-survey.be/target/classes/static',
        filename: 'dist/[name].bundle.js',
        chunkFilename: 'dist/[name].[chunkhash].chunk.js',
        publicPath: ''
    },
    resolve: {
        // modules: [ path.join(__dirname, "src"), "node_modules" ],
        // alias: { "@src": path.resolve(__dirname, "src") },
        extensions: ['.js', '.jsx', '.ts', '.tsx']
    },
    mode: 'development', // development, production
    module: {
        rules: [ {
            test: /\.(js|jsx)$/,
            exclude: /(node_modules)/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: [
                        '@babel/preset-env',
                        '@babel/preset-react',
                        {
                            "plugins": [
                                "@babel/plugin-proposal-class-properties"
                            ]
                        }
                    ]
                }
            }
        }, {
            test: /\.(ts|tsx)?$/,
            exclude: /(node_modules)/,
            loader: "awesome-typescript-loader"
        }, {
            test: /\.scss$/,
            loader: 'style-loader!css-loader!sass-loader'
        },{
            test: /\.css$/,
            use: [ 'style-loader', 'css-loader' ]
        }, {
            test: /\.(png|jpg|jpeg|gif)$/,
            use: [ 'file-loader' ]
        }, {
            test: /\.(ttf|eot|svg)$/,
            use: {
                loader: 'file-loader',
                options: {
                    name: 'fonts/[hash].[ext]'
                }
            }
        }, {
            test: /\.(woff|woff2)$/,
            use: {
                loader: 'url-loader',
                options: {
                    name: 'fonts/[hash].[ext]',
                    limit: 5000,
                    mimetype: 'application/font-woff'
                }
            }
        } ]
    },
    plugins: [
        // new BundleAnalyzerPlugin(),
        new CleanWebpackPlugin()
    ],
    optimization: {
        minimize: false
    }
};
