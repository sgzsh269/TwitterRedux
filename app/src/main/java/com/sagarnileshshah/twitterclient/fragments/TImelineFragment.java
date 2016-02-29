package com.sagarnileshshah.twitterclient.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codepath.apps.twitterclient.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sagarnileshshah.twitterclient.TwitterApplication;
import com.sagarnileshshah.twitterclient.adapters.TweetsRecyclerViewAdapter;
import com.sagarnileshshah.twitterclient.clients.TwitterClient;
import com.sagarnileshshah.twitterclient.decorations.DividerItemDecoration;
import com.sagarnileshshah.twitterclient.listeners.EndlessRecyclerViewScrollListener;
import com.sagarnileshshah.twitterclient.models.tweet.Search;
import com.sagarnileshshah.twitterclient.models.tweet.Tweet;
import com.sagarnileshshah.twitterclient.utils.Utils;

import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sshah on 2/27/16.
 */
public class TimelineFragment extends Fragment {

    @Bind(R.id.rvTweets)
    RecyclerView rvTweets;

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    private String mTimeline;
    private OnTimelineFragmentInteractionListener mListener;
    private Activity mActivity;
    private TwitterClient mTwitterClient;
    private TweetsRecyclerViewAdapter mTweetsRecyclerViewAdapter;
    private ArrayList<Tweet> mTweets;
    private HashSet<Long> mTweetIdsHashSet;
    private long mUserId = -1;
    private String mSearchText;
    private long mSearchSinceId;
    private long mSearchMaxId;


    private static final String TIMELINE_ARG = "timeline";
    private static final String USER_ID_ARG = "userId";

    public static TimelineFragment newInstance(String timeline, long userId) {
        Bundle args = new Bundle();
        args.putString(TIMELINE_ARG, timeline);
        args.putLong(USER_ID_ARG, userId);
        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static TimelineFragment newInstance() {
        TimelineFragment fragment = new TimelineFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        if (context instanceof OnTimelineFragmentInteractionListener) {
            mListener = (OnTimelineFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTimelineFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mActivity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            if (getArguments().containsKey(TIMELINE_ARG)) {
                mTimeline = getArguments().getString(TIMELINE_ARG).toLowerCase();
            }
            if (getArguments().containsKey(USER_ID_ARG)) {
                mUserId = getArguments().getLong(USER_ID_ARG);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTwitterClient = TwitterApplication.getRestClient();
        mTweets = new ArrayList<>();
        mTweetIdsHashSet = new HashSet<>();

        mTweetsRecyclerViewAdapter = new TweetsRecyclerViewAdapter(mActivity, mTweets);
        rvTweets.setAdapter(mTweetsRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        rvTweets.setLayoutManager(linearLayoutManager);

        rvTweets.addItemDecoration(new DividerItemDecoration((Context) mListener, DividerItemDecoration.VERTICAL_LIST));

        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);


        if (mTimeline != null) {
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

            if (!Utils.isNetworkAvailable(mActivity) || !Utils.isOnline(mActivity)) {
                //loadTweetsFromDB();
            } else {
                getNewTweets();
            }
        } else {
            rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore() {
                    searchForOlderTweets();
                }
            });


            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    searchForNewerTweets(mSearchText);
                }
            });
        }
    }

    public void clearTweets(){
        mTweetsRecyclerViewAdapter.notifyItemRangeRemoved(0, mTweets.size());
        mTweets.clear();
        mTweetIdsHashSet.clear();
    }

    public void searchForNewerTweets(String searchText) {
        mSearchText = searchText;
        pbLoading.setVisibility(View.VISIBLE);
        long twitterSinceId = 1;

        if (mTweets.size() > 0) {
            twitterSinceId = mTweets.get(0).getRemoteId();
        }

        boolean requestSent = mTwitterClient.searchForNewerTweet(mActivity, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadSearchTweets(response, 0);

                if (mTweets.size() > 0) {
                }
                pbLoading.setVisibility(View.INVISIBLE);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), "in for new");
                Log.e(this.toString(), String.valueOf(responseString));
                swipeContainer.setRefreshing(false);
                Toast.makeText(mActivity, "Sorry, unable to get Tweets. Please try again later.", Toast.LENGTH_LONG).show();
                pbLoading.setVisibility(View.INVISIBLE);
            }
        }, mSearchText, twitterSinceId);

        if (!requestSent) {
            swipeContainer.setRefreshing(false);
            pbLoading.setVisibility(View.INVISIBLE);
        }
    }

    private void searchForOlderTweets() {
        pbLoading.setVisibility(View.VISIBLE);
        long twitterMaxId = 0;
        if (mTweets.size() > 0) {
            twitterMaxId = mTweets.get(mTweets.size() - 1).getRemoteId() - 1;
        }

        boolean requestSent = mTwitterClient.searchForOlderTweet(mActivity, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadSearchTweets(response, mTweets.size());
                pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), "in for old");
                Log.e(this.toString(), String.valueOf(responseString));
                Toast.makeText(mActivity, "Sorry, unable to get Tweets. Please try again later.", Toast.LENGTH_LONG).show();
                pbLoading.setVisibility(View.INVISIBLE);
            }
        }, mSearchText, twitterMaxId);

        if (!requestSent) {
            pbLoading.setVisibility(View.INVISIBLE);
        }
    }


    private void getNewTweets() {
        pbLoading.setVisibility(View.VISIBLE);
        long twitterSinceId = 1;

        if (mTweets.size() > 0) {
            twitterSinceId = mTweets.get(0).getRemoteId();
        }

        boolean requestSent = mTwitterClient.getNewTweets(mActivity, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadTweets(response, 0);

                if (mTweets.size() > 0) {
                    //clearDB();
                    //saveTweetsToDB(mTweets);
                }
                pbLoading.setVisibility(View.INVISIBLE);
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), String.valueOf(statusCode));
                swipeContainer.setRefreshing(false);
                Toast.makeText(mActivity, "Sorry, unable to get Tweets. Please try again later.", Toast.LENGTH_LONG).show();
                pbLoading.setVisibility(View.INVISIBLE);
            }
        }, twitterSinceId, mTimeline, mUserId);

        if (!requestSent) {
            swipeContainer.setRefreshing(false);
            pbLoading.setVisibility(View.INVISIBLE);
        }

    }

    private void getOldTweets() {
        pbLoading.setVisibility(View.VISIBLE);
        long twitterMaxId = 0;
        if (mTweets.size() > 0) {
            twitterMaxId = mTweets.get(mTweets.size() - 1).getRemoteId() - 1;
        }

        boolean requestSent = mTwitterClient.getOldTweets(mActivity, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                loadTweets(response, mTweets.size());
                pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(this.toString(), String.valueOf(statusCode));
                Toast.makeText(mActivity, "Sorry, unable to get Tweets. Please try again later.", Toast.LENGTH_LONG).show();
                pbLoading.setVisibility(View.INVISIBLE);
            }
        }, twitterMaxId, mTimeline, mUserId);

        if (!requestSent) {
            pbLoading.setVisibility(View.INVISIBLE);
        }

    }

    public void loadTweets(String response, int positionStart) {
        Type collectionType = new TypeToken<ArrayList<Tweet>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Tweet.DATE_FORMAT);
        Gson gson = gsonBuilder.create();
        ArrayList<Tweet> tweets = gson.fromJson(response, collectionType);
        Iterator<Tweet> iterator = tweets.iterator();
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            if (mTweetIdsHashSet.contains(tweet.getRemoteId())) {
                iterator.remove();
            } else {
                mTweetIdsHashSet.add(tweet.getRemoteId());
            }
        }

        mTweets.addAll(positionStart, tweets);
        mTweetsRecyclerViewAdapter.notifyItemRangeInserted(positionStart, tweets.size());
    }

    public void loadSearchTweets(String response, int positionStart) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Tweet.DATE_FORMAT);
        Gson gson = gsonBuilder.create();
        Search search = gson.fromJson(response, Search.class);
        mSearchSinceId = search.getSearchMetadata().getSinceId();
        mSearchMaxId = search.getSearchMetadata().getMaxId() - 1;
        List<Tweet> tweets = search.getStatuses();
        Iterator<Tweet> iterator = tweets.iterator();
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            if (mTweetIdsHashSet.contains(tweet.getRemoteId())) {
                iterator.remove();
            } else {
                mTweetIdsHashSet.add(tweet.getRemoteId());
            }
        }

        mTweets.addAll(positionStart, tweets);
        mTweetsRecyclerViewAdapter.notifyItemRangeInserted(positionStart, tweets.size());
    }

    public void reload() {
        getNewTweets();
    }

    public interface OnTimelineFragmentInteractionListener {

    }


}
