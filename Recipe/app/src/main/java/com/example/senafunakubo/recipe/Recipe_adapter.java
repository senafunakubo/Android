package com.example.senafunakubo.recipe;

/**
 * Created by senafunakubo on 2017-07-24.
 */

//import android.support.v7.app.AlertController;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by senafunakubo on 2017-07-18.
 */

//extends recyclerView adapter with dataType as classname.InnerClassName

//    implements RecyclerView.OnItemTouchListener
public class Recipe_adapter extends RecyclerView.Adapter<Recipe_adapter.MyViewHolder>{

    private List<Recipe> recipe_list;
    private  int colorNo;
    Boolean isSelectedAll;
    int lastPosition = -1;
    boolean[] checkBoxState;
    CardView cv;
//    private OnItemClickListener mListener;
//    GestureDetector mGestureDetector;


//    into constructor
//    ,Context context, OnItemClickListener listener
    public Recipe_adapter(List<Recipe> recipe_list){

        this.recipe_list = recipe_list;
        checkBoxState = new boolean[recipe_list.size()];

        //For background color
        colorNo = 0;

//        mListener = listener;
//        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return true;
//            }
//        });

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


//    @Override
//    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
//        View childView = view.findChildViewUnder(e.getX(), e.getY());
//        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
//            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
//        }
//        return false;
//    }
//
//    @Override
//    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
//    }
//
//    @Override
//    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipe_title;
        TextView recipe_ingredients;
        TextView recipe_time;
        ImageView imageView;
        CheckBox checkBox;

        MyViewHolder(View view) {
            super(view);
            recipe_title = (TextView) view.findViewById(R.id.re_title);
            recipe_ingredients = (TextView) view.findViewById(R.id.re_ingre);
            recipe_time = (TextView) view.findViewById(R.id.re_time);

            cv = (CardView) view.findViewById(R.id.cv);
            imageView = (ImageView) view.findViewById(R.id.recipe_image);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_recipe,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        //For background color
        int bgColorNo = ColorUtils.getViewHolderBackgroundColorFromInstance(context,colorNo);
        viewHolder.itemView.setBackgroundColor(bgColorNo);
        colorNo++;

        return viewHolder;
    }


    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Recipe recipe = recipe_list.get(position);
        holder.recipe_title.setText(recipe.getRecipe_title());
        holder.recipe_ingredients.setText(recipe.getRecipe_ingredients());
        holder.recipe_time.setText(recipe.getRecipe_time());
        holder.imageView.setImageResource(recipe.getImageUrl());
        holder.checkBox.setChecked(recipe_list.get(position).isSelected());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipe_list.get(position).setSelected(true);
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
        ImageView image = (ImageView)view.findViewById(R.id.recipe_image);
        Animation animation1 =
                AnimationUtils.loadAnimation(c, R.anim.slide);
        image.startAnimation(animation1);
    }

}
