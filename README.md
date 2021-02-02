# webview_sdl_krkr
## Chinses
把krkrsdl2（[https://github.com/uyjulian/krkrsdl2](https://github.com/uyjulian/krkrsdl2)）编译成webassembly嵌入到安卓webview中的工程，配合nanohttpd（[https://github.com/NanoHttpd/nanohttpd](https://github.com/NanoHttpd/nanohttpd)）拉起http服务实现在安卓手机上的webview运行krkr的目的

代码可直接clone下来用android studio编译运行，建议在真机上运行以获取到手机的真实分辨率和屏幕像素密度，在红米K30下运行结果如下：

![image](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-02-02-13-07-57-290_com.sora.webvi.jpg)

### 目前存在的问题
不同机型的webview对webassembly的支持差异较大，可能部分机型无法正常运行，后面会统一换成腾讯x5的内核（但是广告有点多，还在斟酌是否使用这种国产垃圾）

### 警告：里面的人物立绘禁止使用在本项目以外的其他一切用途

## English
Use krkrsdl2 ([https://github.com/uyjulian/krkrsdl2](https://github.com/uyjulian/krkrsdl2)) Compiled into webassembly and embedded in Android WebView project. Use nanohttpd([https://github.com/NanoHttpd/nanohttpd](https://github.com/NanoHttpd/nanohttpd)) to start HTTP service on Android, to achieve the purpose of running krkr on Android WebView.

The code can be compiled by Android studio. It is recommended to run on the real machine to obtain the real resolution and screen pixel density of the mobile phone. The results of running on Redmi K30 are as follows:

![image](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-02-02-13-07-57-290_com.sora.webvi.jpg)

### problems
WebView in different Android versions has different support for webassembly. Some mobile phones may not work normally, but I will be replaced with Tencent X5 kernel later. But there are a lot of advertisements, and we are still considering whether to use this kind of rubbish.

### Warning: It is forbidden to use the girl picture in the xp3 file for any purpose other than this project.
