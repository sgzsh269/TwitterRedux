
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


@Table(name = "extended_entities", id = "_id")
@Parcel(value= Parcel.Serialization.BEAN, analyze = {ExtendedEntities_.class})
@Generated("org.jsonschema2pojo")
public class ExtendedEntities_ extends Model {
    
    public ExtendedEntities_(){
        super();
    }

    @Column(onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Tweet tweet;

    public void populateMediaFromDB() {
        this.media = getMany(Medium______.class, "extendedentities");
    }

    @SerializedName("media")
    @Expose
    public List<Medium______> media = new ArrayList<Medium______>();

    /**
     * 
     * @return
     *     The media
     */
    public List<Medium______> getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(List<Medium______> media) {
        this.media = media;
    }

}
