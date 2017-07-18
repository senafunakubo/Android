package com.example.senafunakubo.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by senafunakubo on 2017-07-17.
 */

public class GreenAdapter2 extends RecyclerView.Adapter<GreenAdapter2.NumberViewHolder>{

    private int mNumberListItems;
    public GreenAdapter2(int mNumberListItems){
        this.mNumberListItems = mNumberListItems;
    }

    //create an inner class
    //For every object
    class NumberViewHolder extends RecyclerView.ViewHolder{
        TextView ListItemNumberView;
        NumberViewHolder(View itemView){
            super(itemView);
            ListItemNumberView = (TextView) itemView.findViewById(R.id.tv_listitem);
        }

        public void bind(int ListIndex){
            ListItemNumberView.setText(String.valueOf(ListIndex));
        }
    }


    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.number_list_item,parent,false);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberListItems;
    }
}
