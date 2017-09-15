package com.example.senafunakubo.recipe;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senafunakubo on 2017-09-04.
 */

public class RecipeMainAdapter extends RecyclerView.Adapter<RecipeMainAdapter.MyViewHolder> {
    private static int viewHolderCount;
    CardView cardView;
    private List<Recipe> recipeList;
    private List<String> ingreSteps = new ArrayList<>();
    String cookingTime;
    double timeForEveryStep =0L;
    private Context mContext;

//    public RecipeMainAdapter(Context context, List<String> ingreSteps) {
//        mContext = context;
//        this.ingreSteps = ingreSteps;
//    }
    public RecipeMainAdapter(List<String> ingreSteps) {
        this.ingreSteps = ingreSteps;
        Log.d("[Adapter]Const",ingreSteps.get(0));
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // prepareToSetData();
       // prepareTimer();

        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.step_card, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolderCount++;

        Log.d("[Adapter]onCre",ingreSteps.get(0));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.stepNumber.setText(String.valueOf(position + 1) + ".");

//        holder.content.setText(ingreSteps.get(position));
        Log.d("[Adapter]CHECK", ingreSteps.get(position));
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
//        return ingreSteps.size();
        return 1;
    }

    //innerclass creates a view for a single row of data items.

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView stepNumber;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            content = (TextView) view.findViewById(R.id.stepContent);
            stepNumber = (TextView) view.findViewById(R.id.stepNum);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    Toast.makeText(v.getContext(), "you have clicked an item: "+clickedPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void prepareToSetData(){

        int count = recipeList.size();

        for (int i=0; i<count; i++) {
            ingreSteps.add(recipeList.get(i).getRecipe_ingredients());
            ingreSteps.add(recipeList.get(i).getStep1());
            ingreSteps.add(recipeList.get(i).getStep2());
            ingreSteps.add(recipeList.get(i).getStep3());
            ingreSteps.add(recipeList.get(i).getStep4());
            ingreSteps.add(recipeList.get(i).getStep5());

            int cookingTimeInt = recipeList.get(i).getCooking_time();
            cookingTime = Integer.toString(cookingTimeInt);
        }

    }

//    public void prepareTimer(){
//        //To convert this cookingTime into int again then divide 6 (cuz ingredients + 5 steps)(for instance)
//        //then change it to minutes or seconds to show up the data after passing each time
//        timeForEveryStep = (double) Integer.parseInt(cookingTime) / 6;
//        Log.d("Sum " , Double.toString(timeForEveryStep) + "mins");
//
//        timeForEveryStep = timeForEveryStep * 60;
//        Log.d("Sum " , Double.toString(timeForEveryStep) + "secs");
//    }

    private final Handler handler = new Handler();
    private final Runnable finishTask = new Runnable() {
        @Override
        public void run() {
            Log.d("TEST","delay");
        }
    };
}
