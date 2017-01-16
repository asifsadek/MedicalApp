package base.com.medicalapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import base.com.medicalapp.R;
import base.com.medicalapp.activity.ProductDetailActivity;
import base.com.medicalapp.activity.SchemeDetailActivity;
import base.com.medicalapp.model.Scheme;
import base.com.medicalapp.model.SchemeRecord;


public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder> {

    Context context;
    private List<SchemeRecord> schemeRecordArray ;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cell_scheme, parent, false);

        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final SchemeRecord jsonObject = getItem(position);
        if (jsonObject.fields.code!=null) {
                holder.schemeName.setText(jsonObject.fields.code);
                holder.schemeType.setText(jsonObject.fields.type);
                holder.schemeExpiry.setText(dateToDisplay(jsonObject.fields.expiryDate));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 2
                Intent detailIntent = new Intent(context, SchemeDetailActivity.class);

                // 3
                detailIntent.putExtra("record", jsonObject.id);


                // 4
                context.startActivity(detailIntent);
            }
        });
    }

    public void updateData(List<SchemeRecord> recordArray) {

        // update the adapter's dataset
        schemeRecordArray = recordArray;
        notifyDataSetChanged();
    }

    public SchemeRecord getItem(int position) {
        return schemeRecordArray.get(position);
    }

    @Override public long getItemId(int position) {

        // your particular dataset uses String IDs
        // but you have to put something in this method
        return position;
    }

    @Override
    public int getItemCount() {

        if (schemeRecordArray != null) {

            return schemeRecordArray.size();
        }
        return 0;
    }

    public String dateToDisplay(String dateFromJSON){

        String date = dateFromJSON.substring(8,10)+"/"+dateFromJSON.substring(5,7)+"/"+dateFromJSON.substring(0,4);
        return date;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView schemeName;
        public TextView schemeExpiry;
        public TextView schemeType;

        public ViewHolder(View v) {
            super(v);
            schemeName = (TextView) v.findViewById(R.id.schemeId);
            schemeExpiry = (TextView) v.findViewById(R.id.schemeExpiry);
            schemeType = (TextView)v.findViewById(R.id.schemeType);
        }
    }
}

