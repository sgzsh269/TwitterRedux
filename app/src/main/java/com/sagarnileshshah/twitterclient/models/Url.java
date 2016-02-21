
package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Parcel(value = Parcel.Serialization.BEAN, analyze = {Url.class})
@Generated("org.jsonschema2pojo")
public class Url {

    @SerializedName("urls")
    @Expose
    private List<Url_> urls = new ArrayList<Url_>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url_> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url_> urls) {
        this.urls = urls;
    }

}
