
package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Entities___ {

    @SerializedName("hashtags")
    @Expose
    private List<Hashtag_> hashtags = new ArrayList<Hashtag_>();
    @SerializedName("symbols")
    @Expose
    private List<Object> symbols = new ArrayList<Object>();
    @SerializedName("user_mentions")
    @Expose
    private List<Object> userMentions = new ArrayList<Object>();
    @SerializedName("urls")
    @Expose
    private List<Url_____> urls = new ArrayList<Url_____>();
    @SerializedName("media")
    @Expose
    private List<Medium____> media = new ArrayList<Medium____>();

    /**
     * 
     * @return
     *     The hashtags
     */
    public List<Hashtag_> getHashtags() {
        return hashtags;
    }

    /**
     * 
     * @param hashtags
     *     The hashtags
     */
    public void setHashtags(List<Hashtag_> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * 
     * @return
     *     The symbols
     */
    public List<Object> getSymbols() {
        return symbols;
    }

    /**
     * 
     * @param symbols
     *     The symbols
     */
    public void setSymbols(List<Object> symbols) {
        this.symbols = symbols;
    }

    /**
     * 
     * @return
     *     The userMentions
     */
    public List<Object> getUserMentions() {
        return userMentions;
    }

    /**
     * 
     * @param userMentions
     *     The user_mentions
     */
    public void setUserMentions(List<Object> userMentions) {
        this.userMentions = userMentions;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url_____> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url_____> urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The media
     */
    public List<Medium____> getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(List<Medium____> media) {
        this.media = media;
    }

}
