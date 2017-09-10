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
            R.drawable.tacopi, R.drawable.karaage, R.drawable.chige1, R.drawable.karbonara, R.drawable.gingerpork,
            R.drawable.kadai, R.drawable.eggbene, R.drawable.curryudon,R.drawable.vegetariansushi1,R.drawable.gyozave,
            R.drawable.biryani, R.drawable.yakiudon, R.drawable.vegansand,R.drawable.spaghettimeat, R.drawable.onigiri,
            R.drawable.breadchana, R.drawable.inari, R.drawable.veghakka, R.drawable.frencht,
            R.drawable.bhindi};

    String[] nameList = {"Shrimp Fried Rice", "Egg Sandwich", "Noodle Soup (VEGETARIAN)", "Clam Rice",
            "Taco Pizza", "Japanese Fried Chicken", "Sundubu-jjigae", "Karbonara", "Ginger Pork",
            "Kadai Masala", "Eggs Benedict", "Curry Udon","Vegetarian Sushi","Vegan Gyoza",
            "Chicken Biryani","Fried Udon","Vegan Sandwich with Tofu","Spaghetti Meat Sauce",
            "Rice Ball","Bread Channa","Inari Sushi","Veg Hakka Noodles","French Toast",
            "Bhindi Masala"};

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