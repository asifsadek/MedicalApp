package base.com.medicalapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.model.OrderRecord;



public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderRecord> orderRecordArray;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_order, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        OrderRecord jsonObject = getItem(position);
        if (jsonObject.orderFields!=null) {
            holder.orderId.setText(Integer.toString(jsonObject.orderFields.iD));
            holder.date.setText(dateToDisplay(jsonObject.orderFields.date));
            holder.status.setText(jsonObject.orderFields.status);

        }

    }


    public String dateToDisplay(String dateFromJSON){

        String date = dateFromJSON.substring(8,10)+"/"+dateFromJSON.substring(5,7)+"/"+dateFromJSON.substring(0,4);
        return date;
    }

    public void updateData(List<OrderRecord> recordArray) {

        // update the adapter's dataset
        orderRecordArray = recordArray;
        notifyDataSetChanged();
    }


    public OrderRecord getItem(int position) {
        return orderRecordArray.get(position);
    }

    @Override
    public int getItemCount() {

        if (orderRecordArray != null) {
            return orderRecordArray.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId;
        public TextView status;
        public TextView date;

        public ViewHolder(View v) {
            super(v);
            orderId = (TextView) v.findViewById(R.id.schemeId);
            date = (TextView) v.findViewById(R.id.date);
            status = (TextView) v.findViewById(R.id.status);
        }
    }
}

