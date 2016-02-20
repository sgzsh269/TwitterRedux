
package com.sagarnileshshah.twitterclient.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Entities_ {

    @SerializedName("url")
    @Expose
    private Url___ url;
    @SerializedName("description")
    @Expose
    private Description_ description;

    /**
     * 
     * @return
     *     The url
     */
    public Url___ getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(Url___ url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Description_ getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Description_ description) {
        this.description = description;
    }

}
