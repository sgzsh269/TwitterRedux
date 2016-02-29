package com.sagarnileshshah.twitterclient.clients;

import android.app.Activity;
import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sagarnileshshah.twitterclient.utils.Utils;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "0wQMDXvV0rvK20uEyJZKbU740";
	public static final String REST_CONSUMER_SECRET = "FmJE6Tb2WBrcbwIfUqHohLyXGrCOKuXJev1gbVk4dr6YUhO5Ri";
	public static final String REST_CALLBACK_URL = "oauth://macaw";

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public boolean getNewTweets(Activity activity, AsyncHttpResponseHandler handler, long sinceId, String timeline, long userId) {
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("statuses/" + timeline + "_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count", 10);
		params.put("since_id", sinceId);
		if(userId != -1){
			params.put("user_id", userId);
		}
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean getOldTweets(Activity activity, AsyncHttpResponseHandler handler, long maxId, String timeline, long userId) {
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("statuses/" + timeline + "_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count", 10);
		params.put("max_id", maxId);
		if(userId != -1){
			params.put("user_id", userId);
		}
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean postMessage(Activity activity, AsyncHttpResponseHandler handler, long tweetId, String message){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status", message);
		if(tweetId != -1)
			params.put("in_reply_to_status_id", tweetId);
		client.post(apiUrl, params, handler);
		return true;
	}

	public boolean getUserProfile(Activity activity, AsyncHttpResponseHandler handler){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("account/verify_credentials.json");
		client.get(apiUrl, handler);
		return true;
	}

	public boolean getUserProfile(Activity activity, AsyncHttpResponseHandler handler, long userId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("user_id", userId);
		String apiUrl = getApiUrl("users/show.json");
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean postRetweet(Activity activity, AsyncHttpResponseHandler handler, long tweetId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		String apiUrl = getApiUrl("statuses/retweet/" + tweetId + ".json");
		client.post(apiUrl, params, handler);
		return true;
	}

	public boolean postUnRetweet(Activity activity, AsyncHttpResponseHandler handler, long tweetId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		String apiUrl = getApiUrl("statuses/unretweet/" + tweetId + ".json");
		client.post(apiUrl, params, handler);
		return true;
	}

	public boolean postLike(Activity activity, AsyncHttpResponseHandler handler, long tweetId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		String apiUrl = getApiUrl("favorites/create.json");
		client.post(apiUrl, params, handler);
		return true;
	}

	public boolean postUnlike(Activity activity, AsyncHttpResponseHandler handler, long tweetId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("id", tweetId);
		String apiUrl = getApiUrl("favorites/destroy.json");
		client.post(apiUrl, params, handler);
		return true;
	}

	public boolean getFriends(Activity activity, AsyncHttpResponseHandler handler, long userId, long nextCursorId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("user_id", userId);
		params.put("cursor", nextCursorId);
		String apiUrl = getApiUrl("friends/list.json");
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean getFollowers(Activity activity, AsyncHttpResponseHandler handler, long userId, long nextCursorId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		RequestParams params = new RequestParams();
		params.put("user_id", userId);
		params.put("cursor", nextCursorId);
		String apiUrl = getApiUrl("followers/list.json");
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean searchForNewerTweet(Activity activity, AsyncHttpResponseHandler handler, String searchText, long sinceId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("search/tweets.json");
		RequestParams params = new RequestParams();
		params.put("q", searchText);
		params.put("count", 10);
		params.put("since_id", sinceId);
		client.get(apiUrl, params, handler);
		return true;
	}

	public boolean searchForOlderTweet(Activity activity, AsyncHttpResponseHandler handler, String searchText, long maxId){
		if(!Utils.isNetworkAvailable(activity) || !Utils.isOnline(activity)){
			return false;
		}
		String apiUrl = getApiUrl("search/tweets.json");
		RequestParams params = new RequestParams();
		params.put("q", searchText);
		params.put("count", 10);
		params.put("max_id", maxId);
		client.get(apiUrl, params, handler);
		return true;
	}
}