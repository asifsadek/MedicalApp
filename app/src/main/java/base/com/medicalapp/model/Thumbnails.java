package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("small")
    @Expose
    public Small small;
    @SerializedName("large")
    @Expose
    public Large large;

}
