package base.com.medicalapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.model.OrderItemRecords;
import base.com.medicalapp.model.ProductRecord;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<OrderItemRecords> orderItemsRecordArray ;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_cart, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        OrderItemRecords jsonObject = getItem(position);
        if (jsonObject.fields.productName!=null) {

                holder.productName.setText(jsonObject.fields.productName.toString());
                holder.productQuantity.setText(jsonObject.fields.quantity.toString());
                holder.productPrice.setText(jsonObject.fields.mRPTotal.toString());
        }
    }

    public void updateData(List<OrderItemRecords> recordArray) {

        // update the adapter's dataset
        orderItemsRecordArray = recordArray;
        notifyDataSetChanged();
    }

    public OrderItemRecords getItem(int position) {
        return orderItemsRecordArray.get(position);
    }

    @Override public long getItemId(int position) {

        // your particular dataset uses String IDs
        // but you have to put something in this method
        return position;
    }

    @Override
    public int getItemCount() {
        if (orderItemsRecordArray != null) {
            return orderItemsRecordArray.size();
        }

        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;
        public TextView productQuantity;

        public ViewHolder(View v) {
            super(v);
            productName = (TextView) v.findViewById(R.id.cart_product_name);
            productPrice = (TextView) v.findViewById(R.id.cart_product_price);
            productQuantity = (TextView)v.findViewById(R.id.cart_product_quantity);

        }
    }
}

