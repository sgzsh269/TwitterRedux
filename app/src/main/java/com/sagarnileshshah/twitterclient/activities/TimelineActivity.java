package com.sagarnileshshah.twitterclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codepath.apps.twitterclient.R;
import com.sagarnileshshah.twitterclient.TwitterApplication;
import com.sagarnileshshah.twitterclient.adapters.TimelineFragmentPagerAdapter;
import com.sagarnileshshah.twitterclient.fragments.TimelineFragment;
import com.sagarnileshshah.twitterclient.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TimelineActivity extends BaseTimelineActivity {

    @Bind(R.id.tvOfflineMode)
    TextView tvOfflineMode;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Bind(R.id.sliding_tabs)
    TabLayout slidingTabs;

    private TimelineFragmentPagerAdapter mTimelineFragmentPagerAdapter;

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
                renderComposeFragment();
            }
        });

        mTimelineFragmentPagerAdapter = new TimelineFragmentPagerAdapter(getSupportFragmentManager(), TimelineActivity.this);
        mSmartFragmentStatePagerAdapter = mTimelineFragmentPagerAdapter;

        viewpager.setAdapter(mTimelineFragmentPagerAdapter);
        slidingTabs.setupWithViewPager(viewpager);


        mTwitterClient = TwitterApplication.getRestClient();


        if (!Utils.isNetworkAvailable(this) || !Utils.isOnline(this)) {
            tvOfflineMode.setVisibility(View.VISIBLE);
        } else {
            tvOfflineMode.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_logout:
                mTwitterClient.clearAccessToken();
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                return true;
            case R.id.menu_user_profile:
                Intent profileIntent = new Intent(this, UserProfileActivity.class);
                startActivity(profileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        TimelineFragment timelineFragement = (TimelineFragment) mSmartFragmentStatePagerAdapter.getRegisteredFragment(viewpager.getCurrentItem());
        if(timelineFragement != null){
            timelineFragement.reload();
        }
    }

//    public void clearDB(){
//        try {
//            ApplicationInfo ai = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
//            Bundle bundle = ai.metaData;
//            String dbName = bundle.getString("AA_DB_NAME");
//            ActiveAndroid.dispose();
//            this.deleteDatabase(dbName);
//            Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName(dbName).create();
//            ActiveAndroid.initialize(dbConfiguration);
//            ActiveAndroid.setLoggingEnabled(true);
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void saveTweetsToDB(List<Tweet> tweets) {
//        for (Tweet tweet : tweets) {
//            tweet.save();
//            saveToDBHelper(tweet);
//
//            Tweet retweetStatus = tweet.getRetweetedStatus();
//            if (retweetStatus != null) {
//                retweetStatus.retweeted_tweet = tweet;
//                retweetStatus.save();
//
//                saveToDBHelper(retweetStatus);
//            }
//        }
//    }
//
//    private void saveToDBHelper(Tweet tweet) {
//        User user = tweet.getUser();
//        user.tweet = tweet;
//        user.save();
//
//        Entities___ entities = tweet.getEntities();
//        entities.tweet = tweet;
//        entities.save();
//
//
//        for (Url_____ url : entities.getUrls()) {
//            url.entities = entities;
//            url.save();
//        }
//
//        ExtendedEntities_ extendedEntities = tweet.getExtendedEntities();
//        if (extendedEntities != null) {
//            extendedEntities.tweet = tweet;
//            extendedEntities.save();
//
//            for (Medium______ medium : extendedEntities.getMedia()) {
//                medium.extendedentities = extendedEntities;
//                medium.save();
//
//                VideoInfo videoInfo = medium.getVideoInfo();
//                if (videoInfo != null) {
//                    videoInfo.medium = medium;
//                    videoInfo.save();
//
//                    for (Variant variant : videoInfo.getVariants()) {
//                        variant.videoInfo = videoInfo;
//                        variant.save();
//                    }
//                }
//            }
//        }
//    }
//
//    public void loadTweetsFromDB() {
//        List<Tweet> tweets = Tweet.getAllTweetsFromDB();
//        for (Tweet tweet : tweets) {
//            tweet.populateUserFromDB();
//            tweet.populateEntitiesFromDB();
//            tweet.populateExtendedEntitiesFromDB();
//            tweet.populateRetweetFromDB();
//            if (tweet.retweetedStatus != null) {
//                tweet.retweetedStatus.populateUserFromDB();
//                tweet.retweetedStatus.populateEntitiesFromDB();
//                tweet.retweetedStatus.populateExtendedEntitiesFromDB();
//            }
//        }
//        mTweets.addAll(tweets);
//        //mTweetsRecyclerViewAdapter.notifyItemRangeInserted(0, tweets.size());
//    }


}
