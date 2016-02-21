package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshah on 2/19/16.
 */

@Parcel(value = Parcel.Serialization.BEAN, analyze = {UrlRetweeted.class})
public class UrlRetweeted {

    @SerializedName("urls")
    @Expose
    private List<UrlRetweeted_> urls = new ArrayList<UrlRetweeted_>();

    /**
     *
     * @return
     *     The urls
     */
    public List<UrlRetweeted_> getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     *     The urls
     */
    public void setUrls(List<UrlRetweeted_> urls) {
        this.urls = urls;
    }
}
