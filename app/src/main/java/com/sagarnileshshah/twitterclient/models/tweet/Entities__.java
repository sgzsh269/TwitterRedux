
package com.sagarnileshshah.twitterclient.models.tweet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Parcel(value = Parcel.Serialization.BEAN, analyze = {Entities__.class})
@Generated("org.jsonschema2pojo")
public class Entities__ {

    @SerializedName("hashtags")
    @Expose
    private List<Hashtag> hashtags = new ArrayList<Hashtag>();
    @SerializedName("urls")
    @Expose
    private List<UrlRetweeted> urls = new ArrayList<UrlRetweeted>();
    @SerializedName("media")
    @Expose
    private List<Medium> media = new ArrayList<Medium>();

    /**
     * @return The hashtags
     */
    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    /**
     * @param hashtags The hashtags
     */
    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }


    /**
     * @return The urls
     */
    public List<UrlRetweeted> getUrls() {
        return urls;
    }

    /**
     * @param urls The urls
     */
    public void setUrls(List<UrlRetweeted> urls) {
        this.urls = urls;
    }

    /**
     * @return The media
     */
    public List<Medium> getMedia() {
        return media;
    }

    /**
     * @param media The media
     */
    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}
