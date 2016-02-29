package com.sagarnileshshah.twitterclient.activities;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sagarnileshshah.twitterclient.models.friend.Friend;

import org.apache.http.Header;

public class FollowersActivity extends FriendsActivity {

    @Override
    public void loadFriends(Long userId, long nextCursorId) {
        pbLoading.setVisibility(View.VISIBLE);
        boolean requestSent = mTwitterClient.getFollowers(this, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(FollowersActivity.this, "Sorry, unable to get Followers Please try again later.", Toast.LENGTH_LONG).show();
                pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Friend friend = gson.fromJson(responseString, Friend.class);
                mNextCursorId = friend.getNextCursor();
                mFriends.addAll(friend.getUsers());
                mFriendsAdapter.notifyDataSetChanged();
                pbLoading.setVisibility(View.INVISIBLE);
            }
        }, userId, nextCursorId);

        if (!requestSent) {
            pbLoading.setVisibility(View.INVISIBLE);
        }
    }

}
