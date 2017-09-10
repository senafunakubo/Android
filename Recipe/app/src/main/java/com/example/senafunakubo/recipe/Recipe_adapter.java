/*
Fav page's adapter
*/


package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-24.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by senafunakubo on 2017-07-18.
 */

//extends recyclerView adapter with dataType as classname.InnerClassName

public class Recipe_adapter extends RecyclerView.Adapter<Recipe_adapter.MyViewHolder>{

    private List<Recipe> recipe_list;
    private  int colorNo;
    Boolean isSelectedAll;
    int lastPosition = -1;
    boolean[] checkBoxState;
    CardView cv;
    Context context;
    Recipe recipe;

    public Recipe_adapter(List<Recipe> recipe_list){

        this.recipe_list = recipe_list;
        checkBoxState = new boolean[recipe_list.size()];

        //For background color
//        colorNo = 0;

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipe_title;
        TextView recipe_ingredients;
        TextView recipe_time;
        ImageView imageView;
        CheckBox checkBox;


        MyViewHolder(View view) {
            super(view);
            recipe_title = view.findViewById(R.id.re_title);
            recipe_ingredients = view.findViewById(R.id.re_ingre);
            recipe_time = view.findViewById(R.id.re_time);

            cv = view.findViewById(R.id.cv);
            imageView = view.findViewById(R.id.recipe_image);
            checkBox = view.findViewById(R.id.checkbox);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fav_recipe,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }


    public void onBindViewHolder(MyViewHolder holder, final int position) {

        recipe = recipe_list.get(position);
        holder.recipe_title.setText(recipe.getRecipe_title());
//        holder.recipe_ingredients.setText(recipe.getRecipe_ingredients());
        int cooking_time_int = recipe.getCooking_time();
        String cooking_time = Integer.toString(cooking_time_int);
        holder.recipe_time.setText(cooking_time + " mins");

        String getImage_st = recipe.getImageUrl();
//        String getImage_st = recipe.getImageUrl().substring(11);
//        Log.d("Drawable",getImage_st);

        Picasso.with(this.context).load(getImage_st).error(R.drawable.error).into(holder.imageView);

//        int getImage = Integer.parseInt(getImage_st);
//        holder.imageView.setImageResource(getImage);

        holder.checkBox.setChecked(recipe_list.get(position).isSelected());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipe_list.get(position).setSelected(true);
                Toast.makeText(view.getContext(),"item checked " + position ,Toast.LENGTH_SHORT).show();
            }
        });

        setAnimation(holder.itemView, position);
        setFadeAnimation (holder.itemView);
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getItemCount() {
        return recipe_list.size();
    }

    private void setAnimation (View view, int position){
        // int lastPosition= -1;
        Context c = view.getContext();

        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }

//        slide(view);

    }


    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }


    public void slide(View view){
        Context c = view.getContext();
        ImageView image = view.findViewById(R.id.recipe_image);
        Animation animation1 =
                AnimationUtils.loadAnimation(c, R.anim.slide);
        image.startAnimation(animation1);
    }




}