
package com.sagarnileshshah.twitterclient.models.tweet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Search {

    @SerializedName("statuses")
    @Expose
    private List<Tweet> statuses = new ArrayList<Tweet>();
    @SerializedName("search_metadata")
    @Expose
    private SearchMetadata searchMetadata;

    /**
     * 
     * @return
     *     The statuses
     */
    public List<Tweet> getStatuses() {
        return statuses;
    }

    /**
     * 
     * @param statuses
     *     The statuses
     */
    public void setStatuses(List<Tweet> statuses) {
        this.statuses = statuses;
    }

    /**
     * 
     * @return
     *     The searchMetadata
     */
    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    /**
     * 
     * @param searchMetadata
     *     The search_metadata
     */
    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

}
