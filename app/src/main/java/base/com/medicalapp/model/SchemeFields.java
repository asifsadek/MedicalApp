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
    @SerializedName("Is Active")
    @Expose
    public Boolean isActive;
    @SerializedName("Expiry Date")
    @Expose
    public String expiryDate;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Is MRP")
    @Expose
    public Boolean isMRP;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("Is Product")
    @Expose
    public Integer isProduct;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Retailer Schemes")
    @Expose
    public List<Object> retailerSchemes = null;

}
