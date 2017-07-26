package com.example.senafunakubo.movielist;

        import android.content.Context;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.AlphaAnimation;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by CICCC-CIRAC on 2017-07-17.
 */
//extends REcylerView adpater with dataType as ClassName.InnerClassName
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private static int viewHolderCount;
    CardView cv;
    boolean[] checkBoxState;
    private List<Movie> movie_list;
    public static int[] checkedMovie = new int[200];
    public static int count=0;

    public MovieAdapter(List<Movie> movie_list) {
        this.movie_list = movie_list;
        checkBoxState = new boolean[movie_list.size()];
        viewHolderCount = 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_of_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemview);
        int bg = ColorUtils.getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(bg);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //    Movie movie = movie_list.get(position);
        holder.title.setText(movie_list.get(position).getTitle());
        holder.genre.setText(movie_list.get(position).getGenre());
        holder.year.setText(movie_list.get(position).getYear());
//        holder.cast.setText(movie_list.get(position).getCast());
        holder.movieCheck.setChecked(movie_list.get(position).isSelected());
        //setOnClickListener for checkbox
        holder.movieCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_list.get(position).setSelected(true);
                checkedMovie[count] = position;
                count++;
                // Toast.makeText(v.getContext(),"item checked " + position ,Toast.LENGTH_SHORT).show();
            }
        });
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
        return movie_list.size();
    }

    //innerclass creates a view for a single row of data items.

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre, cast;
        public CheckBox movieCheck;

        public MyViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.cv);
            title = (TextView) view.findViewById(R.id.tv_title);
            year = (TextView) view.findViewById(R.id.tv_year);
            genre = (TextView) view.findViewById(R.id.tv_genre);
            cast = (TextView) view.findViewById(R.id.tv_cast);
            movieCheck = (CheckBox) view.findViewById(R.id.checkbox);
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



//
////import android.support.v7.app.AlertController;
//import android.content.Context;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.TextView;
//import java.util.List;
//
///**
// * Created by senafunakubo on 2017-07-18.
// */
//
////extends recyclerView adapter with dataType as classname.InnerClassName
//public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
//
//    private List<Movie> movie_list;
//    private  int colorno;
//    Boolean isSelectedAll;
//    int lastPosition = -1;
//    boolean[] checkBoxState;
//    CardView cv;
//
//    public MovieAdapter(List<Movie> movie_list){
//
//        this.movie_list = movie_list;
//        checkBoxState = new boolean[movie_list.size()];
//
//        //For background color
//        colorno = 0;
//
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView movieTitle;
//        TextView movieGenre;
//        TextView movieYear;
//        ImageView imageView;
//        CheckBox checkBox;
//
//        MyViewHolder(View view) {
//            super(view);
//            movieTitle = (TextView) view.findViewById(R.id.tv_title);
//            movieGenre = (TextView) view.findViewById(R.id.tv_genre);
//            movieYear = (TextView) view.findViewById(R.id.tv_year);
//            cv = (CardView) view.findViewById(R.id.cv);
//            imageView = (ImageView) view.findViewById(R.id.movie_image);
//            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
//
//        }
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.number_of_list,parent,false);
//        MyViewHolder viewHolder = new MyViewHolder(view);
//
//        //For background color
//        int bgcolorno = ColorUtils.getViewHolderBackgroundColorFromInstance(context,colorno);
//        viewHolder.itemView.setBackgroundColor(bgcolorno);
//        colorno++;
//
//        return viewHolder;
//    }
//
//
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//
//        final Movie movie = movie_list.get(position);
//        holder.movieTitle.setText(movie.getTitle());
//        holder.movieGenre.setText(movie.getGenre());
//        holder.movieYear.setText(movie.getYear());
//        holder.imageView.setImageResource(movie.getImageUrl());
//        holder.checkBox.setChecked(movie_list.get(position).isSelected());
//
//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                movie_list.get(position).setSelected(true);
//                // ここのpositionをarrayに保存してdeleteで使う???
//            }
//        });
//
//        setAnimation(holder.itemView, position);
//        setFadeAnimation (holder.itemView);
//    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
//    public int getItemCount() {
//        return movie_list.size();
//    }
//
//    private void setAnimation (View view, int position){
//       // int lastPosition= -1;
//        Context c = view.getContext();
//
//        if(position > lastPosition){
//            Animation animation = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
//            view.startAnimation(animation);
//            lastPosition = position;
//        }
//
////        slide(view);
////        slideButton(view);
//    }
//
//
//    private void setFadeAnimation(View view) {
//        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(1000);
//        view.startAnimation(anim);
//    }
//
//    public void zoom(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation = AnimationUtils.loadAnimation(c,
//                R.anim.alphaanimation);
//        image.startAnimation(animation);
//    }
//
//    public void clockwise(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation1 = AnimationUtils.loadAnimation(c,
//                R.anim.clockwise);
//        image.startAnimation(animation1);
//    }
//
//    public void fade(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation1 =
//                AnimationUtils.loadAnimation(c,
//                        R.anim.fade);
//        image.startAnimation(animation1);
//    }
//
//    public void blink(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation1 =
//                AnimationUtils.loadAnimation(c,
//                        R.anim.blink);
//        image.startAnimation(animation1);
//    }
//
//    public void move(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation1 =
//                AnimationUtils.loadAnimation(c, R.anim.move);
//        image.startAnimation(animation1);
//    }
//
//    public void slide(View view){
//        Context c = view.getContext();
//        ImageView image = (ImageView)view.findViewById(R.id.movie_image);
//        Animation animation1 =
//                AnimationUtils.loadAnimation(c, R.anim.slide);
//        image.startAnimation(animation1);
//    }
//
////    public void slideButton(View view){
////        Context c = view.getContext();
////        Button button = (Button) view.findViewById(R.id.clear_all);
////        Animation animation2 =
////                AnimationUtils.loadAnimation(c, R.anim.slide);
////        button.startAnimation(animation2);
////    }
//
//
//
//}