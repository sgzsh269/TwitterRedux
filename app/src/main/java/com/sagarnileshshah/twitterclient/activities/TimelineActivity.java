package com.sagarnileshshah.twitterclient.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.codepath.apps.twitterclient.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sagarnileshshah.twitterclient.TwitterApplication;
import com.sagarnileshshah.twitterclient.adapters.TweetsRecyclerViewAdapter;
import com.sagarnileshshah.twitterclient.clients.TwitterClient;
import com.sagarnileshshah.twitterclient.listeners.EndlessRecyclerViewScrollListener;
import com.sagarnileshshah.twitterclient.models.Tweet;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {

    @Bind(R.id.rvTweets)
    RecyclerView rvTweets;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    private TwitterClient mTwitterClient;
    private TweetsRecyclerViewAdapter mTweetsRecyclerViewAdapter;
    private ArrayList<Tweet> mTweets;

    private long mTwitterMaxId;
    private long mTwitterSinceId;

    private HashSet<Long> mTweetIdsHashSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_twitter_bird);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTwitterClient = TwitterApplication.getRestClient();

        mTweets = new ArrayList<>();
        mTweetsRecyclerViewAdapter = new TweetsRecyclerViewAdapter(this, mTweets);
        rvTweets.setAdapter(mTweetsRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTweets.setLayoutManager(linearLayoutManager);

        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                getOldTweets();
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewTweets();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        mTwitterMaxId = 0;
        mTwitterSinceId = 1;

        mTweetIdsHashSet = new HashSet<>();

        getNewTweets();
    }

    private void getNewTweets() {
        mTwitterClient.getNewTweets(new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadTweets(response, 0);
                if (mTweets.size() > 0) {
                    mTwitterSinceId = mTweets.get(0).getId();
                }

                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), String.valueOf(statusCode));
                swipeContainer.setRefreshing(false);
            }
        }, mTwitterSinceId);

    }

    private void getOldTweets() {
        mTwitterClient.getOldTweets(new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadTweets(response, mTweets.size());
                if (mTweets.size() > 0) {
                    mTwitterMaxId = mTweets.get(mTweets.size() - 1).getId();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), responseString);
            }
        }, mTwitterMaxId);

    }

    private void loadTweets(String response, int positionStart) {
        Type collectionType = new TypeToken<ArrayList<Tweet>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Tweet.DATE_FORMAT);
        Gson gson = gsonBuilder.create();
        ArrayList<Tweet> tweets = gson.fromJson(response, collectionType);
        Iterator<Tweet> iterator = tweets.iterator();
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            if (mTweetIdsHashSet.contains(tweet.getId())) {
                iterator.remove();
            } else {
                mTweetIdsHashSet.add(tweet.getId());
            }
        }
        if (mTweets.size() == 0 & tweets.size() > 0) {
            mTwitterMaxId = tweets.get(tweets.size() - 1).getId();
        }
        mTweets.addAll(positionStart, tweets);
        mTweetsRecyclerViewAdapter.notifyItemRangeInserted(positionStart, tweets.size());
    }

}
