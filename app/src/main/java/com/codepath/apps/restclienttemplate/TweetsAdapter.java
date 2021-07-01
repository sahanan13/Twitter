package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.databinding.ItemTweetBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    //Context context;
    Activity activity; // for viewbinding
    List<Tweet> tweets;

    // Pass in the context and list of tweets
    public TweetsAdapter(Activity activity, List<Tweet> tweets) {
        this.activity = activity;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        //return new ViewHolder(view);
        ItemTweetBinding binding = ItemTweetBinding.inflate(activity.getLayoutInflater(), parent, false);
        View tweetView = binding.getRoot();
        return new ViewHolder(tweetView, binding);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }


    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTime;
        TextView tvName;
        ImageView ivTweetPic;

        public ViewHolder(View itemView, ItemTweetBinding binding) {
            super(itemView);
            ivProfileImage = binding.ivProfileImage;
            tvBody = binding.tvBody;
            tvScreenName = binding.tvScreenName;
            tvTime = binding.tvTime;
            tvName = binding.tvName;
            ivTweetPic = binding.ivTweetPic;

            /*ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvName = itemView.findViewById(R.id.tvName);
            ivTweetPic = itemView.findViewById(R.id.ivTweetPic);*/
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(" @" + tweet.user.screenName);
            tvName.setText(tweet.user.name);
            Glide.with(activity).load(tweet.user.profileImageUrl).into(ivProfileImage);
            tvTime.setText(tweet.time);
            //Log.i("TweetAdapter", "HasTweetPic: " + tweet.hasTweetPic + " " + tweet.body);
            if (tweet.tweetPic != null) {
                Glide.with(activity).load(tweet.tweetPic).into(ivTweetPic);
                //Glide.with(context).load("http://pbs.twimg.com/media/E5LiJp6WUAIy0dp.jpg").into(ivTweetPic);
                Log.i("TweetAdapter", "Tweet img link: " +tweet.tweetPic);
                ivTweetPic.setVisibility(View.VISIBLE);
            } else {
                //Glide.with(context).load(tweet.user.profileImageUrl).into(ivTweetPic);
                ivTweetPic.setVisibility(View.GONE);
            }
            Log.i("Time tweet", "time ago: "+tweet.time);
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }
}
