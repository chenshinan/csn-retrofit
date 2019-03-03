package com.chenshinan.retrofit.config;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author shinan.chen
 * @since 2019/3/3
 */
public interface RequestInterface {
    @GET("get")
    Call<String> getCall();
}
