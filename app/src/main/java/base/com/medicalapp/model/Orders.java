package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Orders
{

    @SerializedName("records")
    @Expose
    public List<OrderRecord> orderRecords = null;

}
