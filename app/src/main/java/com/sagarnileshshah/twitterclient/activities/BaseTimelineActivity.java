package com.sagarnileshshah.twitterclient.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.sagarnileshshah.twitterclient.adapters.SmartFragmentStatePagerAdapter;
import com.sagarnileshshah.twitterclient.clients.TwitterClient;
import com.sagarnileshshah.twitterclient.fragments.ComposeFragment;
import com.sagarnileshshah.twitterclient.fragments.TimelineFragment;
import com.sagarnileshshah.twitterclient.models.tweet.Tweet;

import org.apache.http.Header;

/**
 * Created by sshah on 2/27/16.
 */
public abstract class BaseTimelineActivity extends AppCompatActivity implements ComposeFragment.OnFragmentInteractionListener, TimelineFragment.OnTimelineFragmentInteractionListener {

    public TwitterClient mTwitterClient;
    public SmartFragmentStatePagerAdapter mSmartFragmentStatePagerAdapter;


    public void renderComposeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ComposeFragment composeFragment = ComposeFragment.newInstance();
        composeFragment.show(fm, "compose");
    }

    public void renderReplyFragment(Tweet tweet) {
        FragmentManager fm = getSupportFragmentManager();
        ComposeFragment composeFragment = ComposeFragment.newInstance(tweet);
        composeFragment.show(fm, "reply");
    }

    @Override
    public void postMessage(long id, String message) {
        final String name;
        if (id == -1) {
            name = "Tweet";
        } else {
            name = "Reply";
        }
        mTwitterClient.postMessage(this, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                Toast.makeText(BaseTimelineActivity.this, name + " sent Successfully!", Toast.LENGTH_LONG).show();
                response = "[" + response + "]";
                TimelineFragment timelineFragment = (TimelineFragment) mSmartFragmentStatePagerAdapter.getRegisteredFragment(0);
                timelineFragment.loadTweets(response, 0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(BaseTimelineActivity.this, "Sorry, " + name + " wasn't sent. Please try again.", Toast.LENGTH_LONG).show();
            }
        }, id, message);
    }

}
