package com.sora.webview_sdl_krkr.js_interface;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import com.sora.webview_sdl_krkr.component.BasePlayer;
import com.tencent.smtt.sdk.WebView;


public class splayer {

    private Context mContext;
    private BasePlayer BPlayer;
    private FrameLayout frameLayout;
    private WebView mWebview;
    public splayer(Context context, FrameLayout frameLayout, WebView webview){
        this.mContext = context;
        this.frameLayout = frameLayout;
        this.mWebview = webview;
        this.BPlayer = new BasePlayer(context, frameLayout, new playCallback() {
            @Override
            public void onStart() {
                // TODO 这里可以用遮挡层处理，这里先隐藏简单处理一下
                mWebview.setVisibility(View.GONE);
            }
            @Override
            public void onEnd() {
                // TODO 播放完成后显示回来
                mWebview.setVisibility(View.VISIBLE);
            }
        });
    }

    public interface playCallback{
        public void onStart();
        public void onEnd();
    }

    @JavascriptInterface
    public void play(final int left, final String url, final boolean isLoop){
        AssetManager am = this.mContext.getAssets();
        AssetFileDescriptor afd = null;
        int res_id = mContext.getResources().getIdentifier(url, "raw", "com.sora.webview_sdl_krkr");
        int videoId = new Integer(res_id);
        afd = this.mContext.getResources().openRawResourceFd(videoId);
        final AssetFileDescriptor finalAfd = afd;
        ((Activity)(this.mContext)).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                frameLayout.setVisibility(View.VISIBLE);

                BPlayer.setVideoArea(left,0,1280,720);// 设置播放大小
                BPlayer.play(finalAfd, isLoop);
            }
        });
    }

    @JavascriptInterface
    public void start(){
        this.BPlayer.start();
    }

    @JavascriptInterface
    public void pause(){
        this.BPlayer.pause();
    }

    @JavascriptInterface
    public void stop(){
        this.BPlayer.stop();
    }

}
