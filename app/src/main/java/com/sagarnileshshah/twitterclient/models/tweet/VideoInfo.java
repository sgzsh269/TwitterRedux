
package com.sagarnileshshah.twitterclient.models.tweet;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Table(name = "videoinfo", id = "_id")
@Parcel(value = Parcel.Serialization.BEAN, analyze = {VideoInfo.class})
@Generated("org.jsonschema2pojo")
public class VideoInfo extends Model {

    public VideoInfo() {
        super();
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Medium______ medium;

    public void populateVariantsFromDB() {
        this.variants = getMany(Variant.class, "videoinfo");
    }

    @SerializedName("aspect_ratio")
    @Expose
    public List<Long> aspectRatio = new ArrayList<Long>();
    @SerializedName("duration_millis")
    @Expose
    public Long durationMillis;
    @SerializedName("variants")
    @Expose
    public List<Variant> variants = new ArrayList<Variant>();

    /**
     * @return The aspectRatio
     */
    public List<Long> getAspectRatio() {
        return aspectRatio;
    }

    /**
     * @param aspectRatio The aspect_ratio
     */
    public void setAspectRatio(List<Long> aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * @return The durationMillis
     */
    public Long getDurationMillis() {
        return durationMillis;
    }

    /**
     * @param durationMillis The duration_millis
     */
    public void setDurationMillis(Long durationMillis) {
        this.durationMillis = durationMillis;
    }

    /**
     * @return The variants
     */
    public List<Variant> getVariants() {
        return variants;
    }

    /**
     * @param variants The variants
     */
    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

}
