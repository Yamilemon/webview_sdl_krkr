package com.sora.webview_sdl_krkr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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
        WebView webView = findViewById(R.id.web_view);
        webView.addJavascriptInterface(js_tool,"js_tool");//注入一个工具类，获取屏幕宽高
        WebSettings webSettings = webView.getSettings();
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
        webView.setWebViewClient(cWebViewClient);
        // 使用WebChromeClient的特性处理html页面
        webView.setWebChromeClient(cWebChromeClient);
        //webView.setPictureListener(cWebViewClient);
        webView.setBackgroundColor(1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // 干掉滚动条
        webView.setScrollContainer(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        String game_url = getString(R.string.load_game_host) + ":" + getString(R.string.load_game_port) + getString(R.string.html_path);

        webView.loadUrl(game_url);
    }
}
