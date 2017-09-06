package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-09-06.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyView> {

    private Context context;

    int[] imgList = {R.drawable.shrimpfriedrice, R.drawable.friedudon, R.drawable.asaririce, R.drawable.eggsand,
            R.drawable.chige, R.drawable.karbonara, R.drawable.kadai, R.drawable.eggbene,
            R.drawable.curryudon, R.drawable.biryani,R.drawable.karaage,R.drawable.vegansand};

    String[] nameList = {"One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "11", "12"};

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MyView myView = new MyView(layoutView);
        return myView;
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {
        holder.imageView.setImageResource(imgList[position]);
        holder.textView.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }

    class MyView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyView(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);

        }
    }
}