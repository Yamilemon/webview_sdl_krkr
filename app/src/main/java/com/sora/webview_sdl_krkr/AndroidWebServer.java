package com.sora.webview_sdl_krkr;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import fi.iki.elonen.NanoHTTPD;

public class AndroidWebServer extends NanoHTTPD {

    public static final String TAG = "AndroidWebServer";
    private Context mContext;

    public AndroidWebServer(int port, Context context) {
        super(port);
        this.mContext = context;
    }

    public AndroidWebServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        //return super.serve(session);

        String uri = session.getUri();
        Log.d(TAG,"uri:" + uri);
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            AssetManager am = this.mContext.getAssets();
            Log.d(TAG,"filename:" + fileName);
            //InputStream abpath = getClass().getResourceAsStream("/assets/" + fileName);
            InputStream fis = am.open(fileName);// FileInputStream(uri);
            //String path = new String(InputStreamToByte(abpath));
            //int lenght = fis.available();
            //byte[]  buffer = new byte[lenght];
            //fis.read(buffer);
            //new File(buffer);
            //Log.d(TAG,"filepath:" + path);
            return render200(uri, fis);
            //return newFixedLengthResponse(Response.Status.OK,"application/octet-stream",fis,fis.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.serve(session);
    }

    private Response render500(String text) {
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, text);
    }

    private Response render200(String uri, InputStream file) {
        try {
            String type = myMIMEType(uri);
            //String type = NanoHTTPD.getMimeTypeForFile(uri);
            Log.d(TAG,"type:" + type);
            return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, type, file, file.available());
        } catch (FileNotFoundException e) {
            return render500(e.getMessage());
        } catch (IOException e) {
            return render500(e.getMessage());
        }
    }

    // 针对部分手机响应头里必须为application/wasm
    private String myMIMEType(String uri){
        String type = NanoHTTPD.getMimeTypeForFile(uri);
        if (type.equals("application/octet-stream")&&uri.endsWith(".wasm")){
            type = "application/wasm";
        }
        return type;
    }

}
