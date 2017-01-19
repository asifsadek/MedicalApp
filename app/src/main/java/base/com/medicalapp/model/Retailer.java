package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/18/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Retailer {

    @SerializedName("records")
    @Expose
    public List<RetailerRecord> records = null;

}