package base.com.medicalapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("records")
    @Expose
    public List<ProductRecord> records = null;

}