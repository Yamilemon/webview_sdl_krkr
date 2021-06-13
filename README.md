# webview_sdl_krkr
将[sdl2版的krkr](https://github.com/krkrsdl2/krkrsdl2)搭载进安卓webview中的程序，可以在安卓手机上打包运行xp3包。目前使用了腾讯的X5内核作为浏览器内核，[nanoHttpd](https://github.com/NanoHttpd/nanohttpd)作为本地服务器框架，通过在手机内运行服务器并用webview进行访问来达到在浏览器上运行webassembly的目的。


## 使用方式
从[鱧天 for 吉里吉里SDL2 α版](https://github.com/xyx0no646/hamoten4kirikirisdl2)中clone工程，并使用该工程中提供的kag相关语句，打包成xp3包后，将xp3包放入本工程的assets包中（注意xp3包的文件名为data.xp3）然后即可编译运行本工程来运行你的krkr游戏。建议在电脑上用pc版的krkrz进行调试，然后再挪到手机上运行。

## 运行截图
![avatar](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-06-09-20-49-13-924_com.sora.webvi.jpg)
![avatar](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-06-09-20-49-22-345_com.sora.webvi.jpg)
![avatar](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-06-09-20-49-28-420_com.sora.webvi.jpg)
![avatar](https://github.com/Yamilemon/webview_sdl_krkr/blob/master/Screenshot_2021-06-09-20-49-38-198_com.sora.webvi.jpg)
**注意：本工程中的xp3文件里面的人物立绘只做演示用途，请勿将该立绘用于其他用途，本工程除立绘外，其余皆为免费素材。**

## 目前存在的问题
* 腾讯X5内核的webview初次启动时加载时间长，虽然其文档上说明可以共享手机的微信和QQ的X5内核，但实际调试时并无此行为。
* 腾讯X5内核在低端手机上运行webassembly性能比较不足，卡顿明显，但是已经比安卓5.0以前的webview兼容性好了。
* 音频部分存在部分bug，在音频播放结束时，会莫名其妙的loop起来，这个和sdl版的krkr有关，可以用web浏览器本身的音频功能进行补足。（[TJS和浏览器JS交互的API](https://gist.github.com/uyjulian/b9d41c776112eb4a919bcb6893ed0174)）
* 测试的机型还不完善，目前经过测试能运行的机型有：魅蓝2（安卓5.1），魅蓝note6（安卓7.1），红米K30（安卓10）更多的机型并未实际运行，是否有未知的兼容性问题无法得知。

## 联系方式
由于本项目到目前为止尚未有比较好的实际应用或者技术突破，欢迎有更好想法的朋友联系我共同完善：[純情可憐的紫之花](https://weibo.com/uuzzi)
