package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderRecord {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("fields")
    @Expose
    public OrderFields orderFields;
    @SerializedName("createdTime")
    @Expose
    public String createdTime;

}
