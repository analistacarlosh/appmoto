package br.com.chfmr.appmotos.commonApp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by carlosfmr on 08/02/15.
 */

public class AppHttp {

    private static final Object APPGUIA = "APPGUIA";

    public static HttpURLConnection connect(String urlWebService) throws IOException {

        final int SEGUNDOS = 1000;
        URL url = new URL(urlWebService);
        HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean hasConect(Context ctx){

        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();

        if(info != null && info.isConnected()){
            return true;
        } else {
            return false;
        }
    }

    public static String bytesToString (InputStream is) throws IOException {
        Log.i("APPBUS", "bytesToString");
        byte[] buffer = new byte[1024];

        ByteArrayOutputStream bufferMemory = new ByteArrayOutputStream();
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1){
            bufferMemory.write(buffer, 0, bytesRead);
        }

        return new String(bufferMemory.toByteArray(), "UTF-8");
    }

}
