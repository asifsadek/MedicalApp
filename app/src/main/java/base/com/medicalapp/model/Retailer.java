package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/16/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Retailer {

    @SerializedName("fields")
    @Expose
    public RetailerFields retailerFields;

}
