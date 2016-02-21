
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

@Table(name = "medium", id = "_id")
@Parcel(value = Parcel.Serialization.BEAN, analyze = {Medium______.class})
@Generated("org.jsonschema2pojo")
public class Medium______ extends Model {

    public Medium______() {
        super();
    }

    public void populateVideoInfoFromDB() {
        List<VideoInfo> videoInfos = getMany(VideoInfo.class, "medium");
        if (videoInfos.size() > 0) {
            this.videoInfo = videoInfos.get(0);
        }
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public ExtendedEntities_ extendedentities;

    @SerializedName("display_url")
    @Expose
    public String displayUrl;
    @SerializedName("expanded_url")
    @Expose
    public String expandedUrl;
    @SerializedName("features")
    @Expose
    public Features features;
    @SerializedName("id")
    @Expose
    public Float id;
    @SerializedName("id_str")
    @Expose
    public String idStr;
    @SerializedName("indices")
    @Expose
    public List<Long> indices = new ArrayList<Long>();

    @Column
    @SerializedName("media_url")
    @Expose
    public String mediaUrl;

    @SerializedName("media_url_https")
    @Expose
    public String mediaUrlHttps;
    @SerializedName("sizes")
    @Expose
    public Sizes___ sizes;

    @Column
    @SerializedName("type")
    @Expose
    public String type;

    @Column
    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("video_info")
    @Expose
    public VideoInfo videoInfo;

    /**
     * @return The displayUrl
     */
    public String getDisplayUrl() {
        return displayUrl;
    }

    /**
     * @param displayUrl The display_url
     */
    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    /**
     * @return The expandedUrl
     */
    public String getExpandedUrl() {
        return expandedUrl;
    }

    /**
     * @param expandedUrl The expanded_url
     */
    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    /**
     * @return The features
     */
    public Features getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(Features features) {
        this.features = features;
    }

    /**
     * @return The id
     */
    public Float getRemoteId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Float id) {
        this.id = id;
    }

    /**
     * @return The idStr
     */
    public String getIdStr() {
        return idStr;
    }

    /**
     * @param idStr The id_str
     */
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     * @return The indices
     */
    public List<Long> getIndices() {
        return indices;
    }

    /**
     * @param indices The indices
     */
    public void setIndices(List<Long> indices) {
        this.indices = indices;
    }

    /**
     * @return The mediaUrl
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * @param mediaUrl The media_url
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    /**
     * @return The mediaUrlHttps
     */
    public String getMediaUrlHttps() {
        return mediaUrlHttps;
    }

    /**
     * @param mediaUrlHttps The media_url_https
     */
    public void setMediaUrlHttps(String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }

    /**
     * @return The sizes
     */
    public Sizes___ getSizes() {
        return sizes;
    }

    /**
     * @param sizes The sizes
     */
    public void setSizes(Sizes___ sizes) {
        this.sizes = sizes;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The videoInfo
     */
    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    /**
     * @param videoInfo The video_info
     */
    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

}
