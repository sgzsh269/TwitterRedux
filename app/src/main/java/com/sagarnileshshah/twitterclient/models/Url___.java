
package com.sagarnileshshah.twitterclient.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Url___ {

    @SerializedName("urls")
    @Expose
    private List<Url____> urls = new ArrayList<Url____>();

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url____> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url____> urls) {
        this.urls = urls;
    }

}
