package com.yanglaoClient.http.callback;

import android.util.Log;

import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

}
