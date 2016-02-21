
package com.sagarnileshshah.twitterclient.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import javax.annotation.Generated;

@Table(name = "variant", id = "_id")
@Parcel(value= Parcel.Serialization.BEAN, analyze = {Variant.class})
@Generated("org.jsonschema2pojo")
public class Variant extends Model {

    public Variant(){
        super();
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public VideoInfo videoInfo;
    
    @SerializedName("bitrate")
    @Expose
    public Long bitrate;
    @SerializedName("content_type")
    @Expose
    public String contentType;

    @Column
    @SerializedName("url")
    @Expose
    public String url;

    /**
     * 
     * @return
     *     The bitrate
     */
    public Long getBitrate() {
        return bitrate;
    }

    /**
     * 
     * @param bitrate
     *     The bitrate
     */
    public void setBitrate(Long bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * 
     * @return
     *     The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     *     The content_type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

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

}
