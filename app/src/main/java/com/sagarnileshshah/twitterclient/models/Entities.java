
package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Parcel(value = Parcel.Serialization.BEAN, analyze = {Entities.class})
@Generated("org.jsonschema2pojo")
public class Entities {

    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("description")
    @Expose
    private Description description;

    /**
     * 
     * @return
     *     The url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(Url url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

}
