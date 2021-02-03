package com.example.wk01hw02v3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("users/{id}/posts")
    Call<List<Post>> getPosts(@Path("id") int postId);
}
