package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/17/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItems {

    @SerializedName("records")
    @Expose
    public List<OrderItems> records = null;

}