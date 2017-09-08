package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-09-06.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyView> {

    private Context context;
    private List<Recipe> recipeList = new ArrayList<>();

    int[] imgList = {R.drawable.shrimpfriedrice, R.drawable.eggsand, R.drawable.nooveg, R.drawable.asaririce,
            R.drawable.tacopi, R.drawable.karaage, R.drawable.chige, R.drawable.karbonara, R.drawable.kadai,
            R.drawable.eggbene, R.drawable.curryudon,R.drawable.vegetariansushi, R.drawable.biryani,
            R.drawable.yakiudon, R.drawable.vegansand,R.drawable.spaghettimeat, R.drawable.onigiri,
            R.drawable.breadchana, R.drawable.inari, R.drawable.veghakka, R.drawable.frencht,
            R.drawable.bhindi};

    String[] nameList = {"1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12","13","14","15","16","17","18","19","20","21","22"};

    public ItemAdapter(Context context,List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
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
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getItemCount() {
        return recipeList.size();
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