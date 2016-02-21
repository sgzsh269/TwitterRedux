
package com.sagarnileshshah.twitterclient.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;


@Table(name = "entities", id = "_id")
@Parcel(value= Parcel.Serialization.BEAN, analyze = {Entities___.class})
@Generated("org.jsonschema2pojo")
public class Entities___ extends Model {

    public Entities___() {
        super();
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Tweet tweet;

    @SerializedName("hashtags")
    @Expose
    public List<Hashtag_> hashtags = new ArrayList<Hashtag_>();
    @SerializedName("urls")
    @Expose
    public List<Url_____> urls = new ArrayList<Url_____>();
    @SerializedName("media")
    @Expose
    public List<Medium____> media = new ArrayList<Medium____>();

    public void populateUrlsFromDB() {
        this.urls = getMany(Url_____.class, "entities");
    }

    /**
     * @return The hashtags
     */
    public List<Hashtag_> getHashtags() {
        return hashtags;
    }

    /**
     * @param hashtags The hashtags
     */
    public void setHashtags(List<Hashtag_> hashtags) {
        this.hashtags = hashtags;
    }


    /**
     * @return The urls
     */
    public List<Url_____> getUrls() {
        return urls;
    }

    /**
     * @param urls The urls
     */
    public void setUrls(List<Url_____> urls) {
        this.urls = urls;
    }

    /**
     * @return The media
     */
    public List<Medium____> getMedia() {
        return media;
    }

    /**
     * @param media The media
     */
    public void setMedia(List<Medium____> media) {
        this.media = media;
    }

}
