package com.example.wk01hw02v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {

    TextView postResult;
    JsonPlaceHolderAPI jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Intent intent = getIntent();
        final int USER_ID = intent.getIntExtra("userID",0);
        postResult=(TextView)findViewById(R.id.postResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderAPI.class);
        createPosts(getCall(USER_ID));
    }

    //get the call from json api
    public Call getCall(int givenUserID){
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(givenUserID);
        return call;
    }
    //append posts to textview
    public void createPosts(Call givenCall) {
        givenCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //possible 404
                if (!response.isSuccessful()){
                    postResult.setText("Code: " +response.code());
                    return;
                }
                //valid call and response
                List<Post> posts = response.body();
                for (Post post: posts){
                    String content = "";
                    content += "Post ID: " + post.getPostID() + "\n";
                    content += "userID: " + post.getUserID() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    postResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //failure; typo in call to api, etc
                postResult.setText(t.getMessage());
            }
        });
    }
    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LandingActivity.class);
        return intent;
    }
}