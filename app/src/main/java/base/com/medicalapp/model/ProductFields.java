package base.com.medicalapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductFields {

    @SerializedName("SKU")
    @Expose
    public String sKU;
    @SerializedName("Image")
    @Expose
    public List<Image> image = null;
    @SerializedName("Composition")
    @Expose
    public String composition;
    @SerializedName("Unit Pack")
    @Expose
    public String unitPack;
    @SerializedName("Product")
    @Expose
    public String product;
    @SerializedName("PTR (per Unit Pack)")
    @Expose
    public Float pTRPerUnitPack;
    @SerializedName("Type")
    @Expose
    public List<String> type = null;
    @SerializedName("MRP")
    @Expose
    public Float mRP;
    @SerializedName("Order Items")
    @Expose
    public List<String> orderItems = null;
    @SerializedName("Product Schemes")
    @Expose
    public List<String> productSchemes = null;
    @SerializedName("Retail Packing")
    @Expose
    public String retailPacking;


}