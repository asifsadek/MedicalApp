package base.com.medicalapp.model;

/**
 * Created by ctsuser1 on 1/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchemeFields {

    @SerializedName("Code")
    @Expose
    public String code;
    @SerializedName("Associated Product")
    @Expose
    public List<String> associatedProduct = null;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Retailer Product Schemes")
    @Expose
    public List<Object> retailerProductSchemes = null;
    @SerializedName("Is Product")
    @Expose
    public Integer isProduct;
}
