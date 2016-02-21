package com.sagarnileshshah.twitterclient.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitterclient.R;
import com.loopj.android.http.TextHttpResponseHandler;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sagarnileshshah.twitterclient.TwitterApplication;
import com.sagarnileshshah.twitterclient.clients.TwitterClient;
import com.sagarnileshshah.twitterclient.models.Tweet;
import com.sagarnileshshah.twitterclient.utils.Utils;
import com.yqritc.scalablevideoview.ScalableVideoView;

import org.apache.http.Header;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetDetailActivity extends AppCompatActivity {

    TwitterClient mTwitterClient;

    @Bind(R.id.ivUserProfileImage)
    RoundedImageView ivUserProfileImage;

    @Bind(R.id.tvUserName)
    TextView tvUserName;

    @Bind(R.id.tvScreenName)
    TextView tvScreenName;

    @Bind(R.id.tvText)
    TextView tvText;

    @Bind(R.id.ivMedia)
    ImageView ivMedia;

    @Bind(R.id.ivIconVideo)
    ImageView ivIconVideo;

    @Bind(R.id.tvCreationTimestamp)
    TextView tvCreationTimestamp;

    @Bind(R.id.tvRetweetNumber)
    TextView tvRetweetNumber;

    @Bind(R.id.tvRetweetLabel)
    TextView tvRetweetLabel;

    @Bind(R.id.tvLikeNumber)
    TextView tvLikeNumber;

    @Bind(R.id.tvLikeLabel)
    TextView tvLikeLabel;

    @Bind(R.id.tvReplyPlaceholder)
    TextView tvReplyPlaceholder;

    @Bind(R.id.rlReplyPlaceholder)
    RelativeLayout rlReplyPlaceholder;

    @Bind(R.id.rlReplyBox)
    RelativeLayout rlReplyBox;

    @Bind(R.id.etReply)
    EditText etReply;

    @Bind(R.id.tvTweet)
    TextView tvTweet;

    @Bind(R.id.tvCharacterCount)
    TextView tvCharacterCount;

    @Bind(R.id.ivTopIconRetweet)
    ImageView ivTopIconRetweet;

    @Bind(R.id.tvRetweetUser)
    TextView tvRetweetUser;

    @Bind(R.id.svvVideo)
    ScalableVideoView svvVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_twitter_bird);


        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));

        if (tweet.getRetweetedStatus() != null) {
            renderRetweet(tweet);
        } else {
            render(tweet);
        }

        mTwitterClient = TwitterApplication.getRestClient();
    }

    private void renderRetweet(Tweet tweet) {
        ivTopIconRetweet.setVisibility(View.VISIBLE);
        tvRetweetUser.setVisibility(View.VISIBLE);
        tvRetweetUser.setText(tweet.getUser().getName() + " Retweeted");
        render(tweet.getRetweetedStatus());
    }

    private void render(final Tweet tweet) {

        if (tweet.getUser() != null) {
            ivUserProfileImage.setImageResource(0);
            Glide.with(this).load(tweet.getUser().getProfileImageUrl()).error(R.drawable.photo_placeholder).placeholder(R.drawable.photo_placeholder).dontAnimate().into(ivUserProfileImage);
            tvUserName.setText(tweet.getUser().getName());
            tvScreenName.setText("@" + tweet.getUser().getScreenName());
            tvReplyPlaceholder.setText("Reply to " + tweet.getUser().getName());
        }

        Utils.unwrapAndRenderTweetTextLinks(this, tweet, ivMedia, svvVideo, ivIconVideo, tvText);

        DateTime createdAt = new DateTime(tweet.getCreatedAt());
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("h:mm a - dd MMM yy");
        tvCreationTimestamp.setText(dateTimeFormatter.print(createdAt));

        if (tweet.getRetweetCount() > 0) {
            if (tweet.getRetweetCount() > 1) {
                tvRetweetLabel.setText(getString(R.string.retweet) + "s");
            }
            tvRetweetNumber.setText(String.valueOf(tweet.getRetweetCount()));
        } else {
            tvRetweetLabel.setVisibility(View.INVISIBLE);
        }

        if (tweet.getFavoriteCount() > 0) {
            if (tweet.getFavoriteCount() > 1) {
                tvLikeLabel.setText(getString(R.string.like) + "s");
            }
            tvLikeNumber.setText(String.valueOf(tweet.getFavoriteCount()));
        } else {
            tvLikeLabel.setVisibility(View.INVISIBLE);
        }

        if (tweet.getFavoriteCount() == 0 && tweet.getRetweetCount() == 0) {
            tvRetweetLabel.setVisibility(View.GONE);
            tvLikeLabel.setVisibility(View.GONE);
        }


        rlReplyPlaceholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlReplyBox.setVisibility(View.VISIBLE);
                etReply.setText("@" + tweet.getUser().getScreenName() + " ");
                etReply.requestFocus();
            }
        });

        etReply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int MAX_CHAR_COUNT = 140;
                String text = s.toString();
                int textLength = text.length();
                int diff = MAX_CHAR_COUNT - textLength;
                tvCharacterCount.setText(String.valueOf(diff));

                if (diff < 0) {
                    tvCharacterCount.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    tvTweet.setTextColor(getResources().getColor(R.color.colorGrey));
                    tvTweet.setOnClickListener(null);
                } else {
                    tvCharacterCount.setTextColor(getResources().getColor(R.color.colorGrey));
                    tvTweet.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tvTweet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rlReplyBox.setVisibility(View.GONE);
                            mTwitterClient.postMessage(TweetDetailActivity.this, new TextHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String response) {
                                    response = "[" + response + "]";
                                    TimelineActivity.loadTweets(response, 0);
                                    Toast.makeText(TweetDetailActivity.this, "Reply sent Successfully!", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, String response, Throwable error) {
                                    Toast.makeText(TweetDetailActivity.this, "Sorry, Reply wasn't updated. Please try again.", Toast.LENGTH_LONG).show();
                                }
                            }, tweet.getRemoteId(), etReply.getText().toString());
                        }
                    });
                }
            }
        });


    }

}