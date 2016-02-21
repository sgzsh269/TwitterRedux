
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

@Table(name = "url", id = "_id")
@Parcel(value = Parcel.Serialization.BEAN, analyze = {Url_____.class})
@Generated("org.jsonschema2pojo")
public class Url_____ extends Model {

    public Url_____(){
        super();
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Entities___ entities;

    @Column
    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("expanded_url")
    @Expose
    public String expandedUrl;

    @Column
    @SerializedName("display_url")
    @Expose
    public String displayUrl;

    @SerializedName("indices")
    @Expose
    public List<Long> indices = new ArrayList<Long>();

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The expandedUrl
     */
    public String getExpandedUrl() {
        return expandedUrl;
    }

    /**
     * 
     * @param expandedUrl
     *     The expanded_url
     */
    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    /**
     * 
     * @return
     *     The displayUrl
     */
    public String getDisplayUrl() {
        return displayUrl;
    }

    /**
     * 
     * @param displayUrl
     *     The display_url
     */
    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    /**
     * 
     * @return
     *     The indices
     */
    public List<Long> getIndices() {
        return indices;
    }

    /**
     * 
     * @param indices
     *     The indices
     */
    public void setIndices(List<Long> indices) {
        this.indices = indices;
    }

}
