package com.sdwfqin.uidemo.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sdwfqin on 2017/3/10.
 */

public class HttpUtils {

    private onHttpListener mOnHttpListener;

    private HttpUtils(Builder builder){
        this.mOnHttpListener = builder.mOnHttpListener;

        doPost(builder.urlStr,builder.param);
    }

    public void doPost(final String urlStr, final String param) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(urlStr);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(param);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));

                    if(connection.getResponseCode() == 200){
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }

                        mOnHttpListener.onSuccess(builder.toString());
                    } else {
                        mOnHttpListener.onError(connection.getResponseCode());
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Builder{

        private onHttpListener mOnHttpListener;
        private String urlStr;
        private String param;

        public Builder setOnHttpListener(onHttpListener mOnHttpListener){
            this.mOnHttpListener = mOnHttpListener;
            return this;
        }

        public Builder doPost(String urlStr, String param){
            this.urlStr = urlStr;
            this.param = param;
            return this;
        }

        public HttpUtils build(){
            return new HttpUtils(this);
        }
    }

    public interface onHttpListener {
        void onSuccess(String response);

        void onError(int code);
    }
}
