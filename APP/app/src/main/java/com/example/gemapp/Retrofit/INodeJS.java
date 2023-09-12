package com.example.gemapp.Retrofit;

import com.example.gemapp.models.Jobs;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface INodeJS {



    @POST("/signup/")
    @FormUrlEncoded
    Observable<String> registerUser (@Field("nom") String nom,
                                     @Field("prenom") String prenom,
                                     @Field("email") String email,
                                     @Field("password") String password,
                                     @Field("numero") String numero);

    @POST("/login/")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String login,
                                 @Field("password") String pwd);

    @GET("/GetJobs/")
    Call<List<Jobs>> getJobs();





}
