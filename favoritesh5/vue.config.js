module.exports = {
    publicPath: './',
    outputDir: 'dist',
    assetsDir: 'assets',
    lintOnSave: false,
    runtimeCompiler: false,
    productionSourceMap: false,
    devServer: {
        open: false, // 浏览器自动打开页面
        disableHostCheck: true, // 内网穿透 配置
        host: '0.0.0.0', // 如果是真机测试，就使用这个IP
        port: 8888,
        https: false,
        hotOnly: false, // 热更新（webpack已实现了，这里false即可）
        proxy: {
            // 配置跨域
            '/favorite-api/*': {
                target: 'http://localhost:7788/favorite-api/',
                ws: true,
                secure: false,
                changOrigin: true,
                pathRewrite: {
                    '^/favorite-api': ''
                }
            }
        }
    }
}