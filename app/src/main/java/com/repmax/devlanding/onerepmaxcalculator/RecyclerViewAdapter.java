package com.repmax.devlanding.onerepmaxcalculator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by ted on 10/10/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>{
    private int[] repMaxes;
    private Context context;
    public RecyclerViewAdapter(Context context, int[] repMaxes) {
        this.repMaxes = repMaxes;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repmax_row, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        int rep = repMaxes[position];
        holder.weightRepMax.setText(String.valueOf(repMaxes[position]));
        int repNumber = position+1;
        holder.numberRepMax.setText(repNumber + "RM");

    }

    @Override
    public int getItemCount() {
        return repMaxes.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView numberRepMax;
        private TextView weightRepMax;
        public CustomViewHolder(View itemView) {
            super(itemView);
            numberRepMax = (TextView) itemView.findViewById(R.id.numberRepMax);
            weightRepMax = (TextView) itemView.findViewById(R.id.weightRepMax);
        }
    }
}
