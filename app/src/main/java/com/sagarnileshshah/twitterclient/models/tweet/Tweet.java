
package com.sagarnileshshah.twitterclient.models.tweet;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

@Table(name = "tweet", id = "_id")
@Parcel(value = Parcel.Serialization.BEAN, analyze = {Tweet.class})
@Generated("org.jsonschema2pojo")
public class Tweet extends Model {

    public Tweet() {
        super();
    }

    public static final String DATE_FORMAT = "ccc MMM dd hh:mm:ss Z yyyy";

    @Column
    @SerializedName("created_at")
    @Expose
    public Date createdAt;

    @Column
    @SerializedName("id")
    @Expose
    public Long remoteId;

    @SerializedName("id_str")
    @Expose
    public String idStr;

    @Column(length=150)
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("truncated")
    @Expose
    public Boolean truncated;

    @SerializedName("user")
    @Expose
    public User user;

    @Column(name="retweeted_tweet", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Tweet retweeted_tweet;

    @SerializedName("retweeted_status")
    @Expose
    public Tweet retweetedStatus;


    @SerializedName("is_quote_status")
    @Expose
    public Boolean isQuoteStatus;

    @Column
    @SerializedName("retweet_count")
    @Expose
    public Long retweetCount;

    @Column
    @SerializedName("favorite_count")
    @Expose
    public Long favoriteCount;

    @SerializedName("entities")
    @Expose
    public Entities___ entities;

    @SerializedName("extended_entities")
    @Expose
    public ExtendedEntities_ extendedEntities;

    @SerializedName("favorited")
    @Expose
    public Boolean favorited;
    @SerializedName("retweeted")
    @Expose
    public Boolean retweeted;
    @SerializedName("possibly_sensitive")
    @Expose
    public Boolean possiblySensitive;
    @SerializedName("possibly_sensitive_appealable")
    @Expose
    public Boolean possiblySensitiveAppealable;
    @SerializedName("lang")
    @Expose
    public String lang;


    public void populateUserFromDB() {
        List<User> users = getMany(User.class, "tweet");
        if (users.size() > 0) {
            this.user = users.get(0);
        }
    }

    public void populateEntitiesFromDB() {
        List<Entities___> entities = getMany(Entities___.class, "tweet");
        if (entities.size() > 0) {
            this.entities = entities.get(0);
            this.entities.populateUrlsFromDB();
        }
    }

    public void populateExtendedEntitiesFromDB() {
        List<ExtendedEntities_> extendedEntities = getMany(ExtendedEntities_.class, "tweet");
        if (extendedEntities.size() > 0) {
            this.extendedEntities = extendedEntities.get(0);
            this.extendedEntities.populateMediaFromDB();
            for (Medium______ medium : this.extendedEntities.getMedia()) {
                medium.populateVideoInfoFromDB();
                if (medium.getVideoInfo() != null) {
                    medium.getVideoInfo().populateVariantsFromDB();
                }
            }
        }
    }

    public void populateRetweetFromDB(){
        List<Tweet> retweet = getMany(Tweet.class, "retweeted_tweet");
        if (retweet.size() > 0){
            this.retweetedStatus = retweet.get(0);
        }
    }


    public static List<Tweet> getAllTweetsFromDB() {
        return new Select()
                .from(Tweet.class)
                .where("retweeted_tweet is null")
                .execute();
    }

    public static Tweet getOneTweetFromDB(){
        return new Select()
                .from(Tweet.class)
                .orderBy("RANDOM()")
                .executeSingle();
    }


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
    public Long getRemoteId() {
        return remoteId;
    }

    /**
     * @param id The id
     */
    public void setRemoteId(Long remoteId) {
        this.remoteId = remoteId;
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
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The retweetedStatus
     */
    public Tweet getRetweetedStatus() {
        return retweetedStatus;
    }

    /**
     * @param retweetedStatus The retweeted_status
     */
    public void setRetweetedStatus(Tweet retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
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
    public Entities___ getEntities() {
        return entities;
    }

    /**
     * @param entities The entities
     */
    public void setEntities(Entities___ entities) {
        this.entities = entities;
    }

    /**
     * @return The extendedEntities
     */
    public ExtendedEntities_ getExtendedEntities() {
        return extendedEntities;
    }

    /**
     * @param extendedEntities The extended_entities
     */
    public void setExtendedEntities(ExtendedEntities_ extendedEntities) {
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
