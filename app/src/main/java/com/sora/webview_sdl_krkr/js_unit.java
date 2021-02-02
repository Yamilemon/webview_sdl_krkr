package com.sora.webview_sdl_krkr;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.webkit.JavascriptInterface;

public class js_unit {

    private Context mContext;

    public js_unit(Context context){
        this.mContext = context;
    }

    @JavascriptInterface
    public String getScreenWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)(this.mContext)).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height + "*" + width;
    }
}
