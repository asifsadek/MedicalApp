package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("filename")
    @Expose
    public String filename;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("thumbnails")
    @Expose
    public Thumbnails thumbnails;

}
