package com.jpndev.jpmusicplayer.retrofit;

import com.ceino.snapsupport.model.SuccesMessage;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @Multipart
    @Headers(
            {"support-app-id: appid","support-app-key: appkey","enctype: multipart/form-data"}
            )
    @POST("question")
    Call<SuccesMessage> getUploadedStatus(@Part("data") RequestBody data);
}