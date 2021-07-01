package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    Tweet tweet;

    //View objects
    ImageView ivProfileImage;
    TextView tvBody;
    TextView tvScreenName;
    TextView tvTime;
    TextView tvName;
    ImageView ivTweetPic;
    Button btnReply;
    Button btnRetweet;
    Button btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //resolve the view objects
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvName = (TextView) findViewById(R.id.tvName);
        ivTweetPic = (ImageView) findViewById(R.id.ivTweetPic);
        btnReply = (Button) findViewById(R.id.btnReply);
        btnRetweet = (Button) findViewById(R.id.btnRetweet);
        btnLike = (Button) findViewById(R.id.btnLike);

        // unwrap the tweet passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        // set elements
        tvBody.setText(tweet.body);
        tvBody.setLinksClickable(true);
        tvScreenName.setText(" @" + tweet.user.screenName);
        tvName.setText(tweet.user.name);
        Glide.with(DetailsActivity.this).load(tweet.user.profileImageUrl).circleCrop().into(ivProfileImage);

        tvTime.setText(tweet.time);
        if (tweet.tweetPic != null) {
            Glide.with(DetailsActivity.this).load(tweet.tweetPic).into(ivTweetPic);
            Log.i("TweetAdapter", "Tweet img link: " +tweet.tweetPic);
            ivTweetPic.setVisibility(View.VISIBLE);
        } else {
            ivTweetPic.setVisibility(View.GONE);
        }
    }
}

