package com.sora.webview_sdl_krkr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String port = getString(R.string.load_game_port);

        // 原理：在本地拉起一个简易http服务器，再用webview去访问这个html页面
        try {
            AndroidWebServer androidWebServer = new AndroidWebServer(Integer.parseInt(port),this);
            androidWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        js_unit js_tool = new js_unit(this);
        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.addJavascriptInterface(js_tool,"js_tool");//注入一个工具类，获取屏幕宽高
        WebSettings webSettings = mWebView.getSettings();
        // 允许执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 浏览器不支持多窗口显示
        webSettings.setSupportMultipleWindows(false);
        // 页面不可以进行缩放
        webSettings.setSupportZoom(true);
        // 调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        WebViewClient cWebViewClient = new WebViewClient();
        WebChromeClient cWebChromeClient = new WebChromeClient();
        // 使用WebViewClient的特性处理html页面
        mWebView.setWebViewClient(cWebViewClient);
        // 使用WebChromeClient的特性处理html页面
        mWebView.setWebChromeClient(cWebChromeClient);
        //webView.setPictureListener(cWebViewClient);
        mWebView.setBackgroundColor(1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // 干掉滚动条
        mWebView.setScrollContainer(false);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        //String game_url = "http://soft.imtt.qq.com/browser/tes/feedback.html";
        String game_url = getString(R.string.load_game_host) + ":" + getString(R.string.load_game_port) + getString(R.string.html_path);

        mWebView.loadUrl(game_url);
    }
}
