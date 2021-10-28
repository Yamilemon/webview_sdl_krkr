package com.sora.webview_sdl_krkr;

import android.app.Application;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            QbSdk.preinstallStaticTbs(getApplicationContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //fileDelete.DeleteFile(getExternalFilesDir("/VideoCache"));
       /* HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);*/
    }
}
