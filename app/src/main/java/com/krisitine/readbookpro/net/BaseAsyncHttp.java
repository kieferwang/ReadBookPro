package com.krisitine.readbookpro.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Kristine on 2016/1/24.
 */
public class BaseAsyncHttp extends AsyncHttpClient {
    public static final String HOST = "https://api.douban.com";

    public static void getReq(String url,RequestParams params,JsonHttpResponseHandler handler){
        new AsyncHttpClient().get(HOST+url,params,handler);
    }
}
