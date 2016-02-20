package com.sagarnileshshah.twitterclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitterclient.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sagarnileshshah.twitterclient.models.Medium______;
import com.sagarnileshshah.twitterclient.models.Tweet;
import com.sagarnileshshah.twitterclient.models.Url_____;
import com.sagarnileshshah.twitterclient.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sshah on 2/18/16.
 */
public class TweetsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Tweet> mTweets;
    private Context mContext;

    //private final int WITH_MEDIA = 0, WITHOUT_MEDIA = 1;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivUserProfileImageUrl)
        RoundedImageView ivUserProfileImageUrl;

        @Bind(R.id.tvUserName)
        TextView tvUserName;

        @Bind(R.id.tvRelativeTimestamp)
        TextView tvRelativeTimestamp;

        @Bind(R.id.tvScreenName)
        TextView tvScreenName;

        @Bind(R.id.tvText)
        TextView tvText;

        @Bind(R.id.tvLikes)
        TextView tvLikes;

        @Bind(R.id.tvRetweets)
        TextView tvRetweets;

        @Bind(R.id.ivMedia)
        ImageView ivMedia;

        @Bind(R.id.ivIconVideo)
        ImageView ivIconVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public TweetsRecyclerViewAdapter(Context context, List tweets) {
        mContext = context;
        mTweets = tweets;
    }

//    @Override
//    public int getItemViewType(int position) {
//        Tweet tweet = mTweets.get(position);
//        if (tweet.getEntities().getMedia().size() > 0){
//            return WITH_MEDIA;
//        } else {
//            return WITHOUT_MEDIA;
//        }
//    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet_default, parent, false);

        ViewHolder viewHolder = new ViewHolder(tweetView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        configureViewHolder((ViewHolder) viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    private void configureViewHolder(ViewHolder viewHolder, int position) {
        Tweet tweet = mTweets.get(position);
        viewHolder.tvUserName.setText(tweet.getUser().getName());
        viewHolder.tvScreenName.setText("@" + tweet.getUser().getScreenName());

        viewHolder.ivUserProfileImageUrl.setImageResource(0);
        Glide.with(mContext).load(tweet.getUser().getProfileImageUrl().replace(".png", "_bigger.png")).into(viewHolder.ivUserProfileImageUrl);

        viewHolder.tvRelativeTimestamp.setText(Utils.getFormattedRelativeTimestamp(tweet.getCreatedAt()));
        viewHolder.tvLikes.setText(String.valueOf(tweet.getFavoriteCount()));
        viewHolder.tvRetweets.setText(String.valueOf(tweet.getRetweetCount()));

        String text = tweet.getText();

        if (tweet.getEntities().getUrls() != null && tweet.getEntities().getUrls().size() > 0) {
            for (Url_____ url_____ : tweet.getEntities().getUrls()) {
                String wrapperUrl = url_____.getUrl();
                String displayUrl = url_____.getDisplayUrl();
                text = text.replace(wrapperUrl, displayUrl);
            }
        }

        if (tweet.getExtendedEntities() != null && tweet.getExtendedEntities().getMedia() != null && tweet.getExtendedEntities().getMedia().size() > 0) {
            viewHolder.ivMedia.setVisibility(View.VISIBLE);
            viewHolder.ivMedia.setImageResource(0);
            Medium______ medium______ = tweet.getExtendedEntities().getMedia().get(0);
            String type = medium______.getType();
            if (type.equals("video")) {
                viewHolder.ivIconVideo.setVisibility(View.VISIBLE);

            } else {
                viewHolder.ivIconVideo.setVisibility(View.GONE);
            }

            String wrapperUrl = medium______.getUrl();
            text = text.replace(wrapperUrl, "");
            String media_url = medium______.getMediaUrl() + ":medium";
            Glide.with(mContext).load(media_url).into(viewHolder.ivMedia);
        } else {
            viewHolder.ivMedia.setVisibility(View.GONE);
            viewHolder.ivIconVideo.setVisibility(View.GONE);
        }

        viewHolder.tvText.setText(text);

    }

}
