
package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Parcel(value = Parcel.Serialization.BEAN, analyze = {Description.class})
@Generated("org.jsonschema2pojo")
public class Description {

    @SerializedName("urls")
    @Expose
    private List<Url__> urls = new ArrayList<Url__>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url__> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url__> urls) {
        this.urls = urls;
    }

}
