
package com.sagarnileshshah.twitterclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

import javax.annotation.Generated;

@Parcel(value = Parcel.Serialization.BEAN, analyze = {RetweetedStatus.class})
@Generated("org.jsonschema2pojo")
public class RetweetedStatus {

    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("id_str")
    @Expose
    private String idStr;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("truncated")
    @Expose
    private Boolean truncated;
    @Expose
    private Boolean isQuoteStatus;
    @SerializedName("retweet_count")
    @Expose
    private Long retweetCount;
    @SerializedName("favorite_count")
    @Expose
    private Long favoriteCount;
    @SerializedName("entities")
    @Expose
    private Entities__ entities;
    @SerializedName("extended_entities")
    @Expose
    private ExtendedEntities extendedEntities;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("retweeted")
    @Expose
    private Boolean retweeted;
    @SerializedName("possibly_sensitive")
    @Expose
    private Boolean possiblySensitive;
    @SerializedName("possibly_sensitive_appealable")
    @Expose
    private Boolean possiblySensitiveAppealable;
    @SerializedName("lang")
    @Expose
    private String lang;

    /**
     * @return The createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Long id) {
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
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return The truncated
     */
    public Boolean getTruncated() {
        return truncated;
    }

    /**
     * @param truncated The truncated
     */
    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    /**
     * @return The isQuoteStatus
     */
    public Boolean getIsQuoteStatus() {
        return isQuoteStatus;
    }

    /**
     * @param isQuoteStatus The is_quote_status
     */
    public void setIsQuoteStatus(Boolean isQuoteStatus) {
        this.isQuoteStatus = isQuoteStatus;
    }

    /**
     * @return The retweetCount
     */
    public Long getRetweetCount() {
        return retweetCount;
    }

    /**
     * @param retweetCount The retweet_count
     */
    public void setRetweetCount(Long retweetCount) {
        this.retweetCount = retweetCount;
    }

    /**
     * @return The favoriteCount
     */
    public Long getFavoriteCount() {
        return favoriteCount;
    }

    /**
     * @param favoriteCount The favorite_count
     */
    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    /**
     * @return The entities
     */
    public Entities__ getEntities() {
        return entities;
    }

    /**
     * @param entities The entities
     */
    public void setEntities(Entities__ entities) {
        this.entities = entities;
    }

    /**
     * @return The extendedEntities
     */
    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    /**
     * @param extendedEntities The extended_entities
     */
    public void setExtendedEntities(ExtendedEntities extendedEntities) {
        this.extendedEntities = extendedEntities;
    }

    /**
     * @return The favorited
     */
    public Boolean getFavorited() {
        return favorited;
    }

    /**
     * @param favorited The favorited
     */
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    /**
     * @return The retweeted
     */
    public Boolean getRetweeted() {
        return retweeted;
    }

    /**
     * @param retweeted The retweeted
     */
    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    /**
     * @return The possiblySensitive
     */
    public Boolean getPossiblySensitive() {
        return possiblySensitive;
    }

    /**
     * @param possiblySensitive The possibly_sensitive
     */
    public void setPossiblySensitive(Boolean possiblySensitive) {
        this.possiblySensitive = possiblySensitive;
    }

    /**
     * @return The possiblySensitiveAppealable
     */
    public Boolean getPossiblySensitiveAppealable() {
        return possiblySensitiveAppealable;
    }

    /**
     * @param possiblySensitiveAppealable The possibly_sensitive_appealable
     */
    public void setPossiblySensitiveAppealable(Boolean possiblySensitiveAppealable) {
        this.possiblySensitiveAppealable = possiblySensitiveAppealable;
    }

    /**
     * @return The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

}
