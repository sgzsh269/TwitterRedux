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
        inflater.inflate(R.menu.menu_timeline, menu);
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
            case R.id.menu_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
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
}
