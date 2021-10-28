package com.sora.webview_sdl_krkr.component;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sora.webview_sdl_krkr.js_interface.splayer;

public class BasePlayer implements SurfaceHolder.Callback {

    private final static String TAG = BasePlayer.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;
    private FrameLayout mFrameLayout;
    private splayer.playCallback cb;
    // 缩放比例
    private float mScaleRate;

    public BasePlayer(Context context, FrameLayout frameLayout, splayer.playCallback cb) {
        this.mMediaPlayer = new MediaPlayer();
        this.mSurfaceView = new SurfaceView(context);
        this.mFrameLayout = frameLayout;
        this.cb = cb;
        frameLayout.addView(mSurfaceView);
        SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        // 给SurfaceView添加CallBack监听
        surfaceHolder.addCallback(this);

        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float widthRate = (float) dm.widthPixels / 1280;
        float heightRate = (float) dm.heightPixels / 720;
        if (heightRate > widthRate) {
            mScaleRate = widthRate;
        } else {
            mScaleRate = heightRate;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // 当SurfaceView中的Surface被创建的时候被调用
        //在这里我们指定MediaPlayer在当前的Surface中进行播放
        mMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void setVideoArea(int left, int top, int width, int height) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mFrameLayout.getLayoutParams();
        lp.leftMargin = (int) (left * mScaleRate);
        lp.topMargin = (int) (top * mScaleRate);
        lp.width = (int) (width * mScaleRate);
        lp.height = (int) (height * mScaleRate);
        mFrameLayout.setLayoutParams(lp);
    }

    public void setVideoSize(int width, int height) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mFrameLayout.getLayoutParams();
        lp.width = (int) (width * mScaleRate);
        lp.height = (int) (height * mScaleRate);
        mFrameLayout.setLayoutParams(lp);
    }

    public void play(AssetFileDescriptor url, final boolean isLoop){
        try {
            if(mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            } else {
                mMediaPlayer.reset();
            }
            mMediaPlayer.setDataSource(url.getFileDescriptor(),url.getStartOffset(),url.getLength());
            // 异步的方式装载流媒体文件
            mMediaPlayer.prepareAsync();

            mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {

                    }
                    return false;
                }
            });
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    try {
                        if (mSurfaceView.getHolder().getSurface().isValid()) {
                            mp.setDisplay(mSurfaceView.getHolder());
                        } else {
                            Log.e(TAG, "MediaPlayer playVideo failed by mSurfaceView.getHolder is not valid");
                        }
                        mp.start();
                        cb.onStart();
                        mp.setLooping(isLoop);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d(TAG, "触发播放完成回调");
                    mFrameLayout.removeView(mSurfaceView);// 播放完成，移除serfaceView
                    mFrameLayout.setVisibility(View.GONE);
                    cb.onEnd();
                }
            });
            mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    Log.d(TAG, "触发快进回调");
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    String errorMes = "播放器出错啦-(what:" + what + ")(extra:" + extra + ")";
                    Log.w(TAG, errorMes);
                    return true;
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "MediaPlayer playVideo failed", e);
        }
    }

    public void start(){
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    public void pause(){
        try {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    public void stop(){
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
