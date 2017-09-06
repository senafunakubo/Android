package com.example.senafunakubo.recipe;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by senafunakubo on 2017-09-04.
 */

public class RecipeMainAdapter extends RecyclerView.Adapter<RecipeMainAdapter.MyViewHolder> {
    private static int viewHolderCount;
    CardView cardView;
    private List<Recipe> recipeList; // change to String
    public static int[] checkedRecipe = new int[6];
    public static int count=0;

    public RecipeMainAdapter(List<Recipe> recipeList) { /// change to String-
        this.recipeList = recipeList;
        viewHolderCount = 0;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.step_card, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.content.setText(recipeList.get(position).getStep1());

        // Here you apply the animation when the view is bound
        // setAnimation(holder.itemView, position);
        // Set the view to fade in
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    //innerclass creates a view for a single row of data items.

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            content = (TextView) view.findViewById(R.id.stepNum);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    Toast.makeText(v.getContext(), "you have clicked an item: "+clickedPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
