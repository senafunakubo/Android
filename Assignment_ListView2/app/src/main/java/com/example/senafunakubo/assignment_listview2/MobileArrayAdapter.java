package com.example.senafunakubo.assignment_listview2;

/**
 * Created by senafunakubo on 2017-07-11.
 */
        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;


public class MobileArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    //Create a constructor
    public MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.activity_main, values);
        this.context = context;
        this.values = values;
    }
    //CALL A SERVICE TO CREATE A View DYNAMICALLY

    @Override
    public View getView(int position,View convertView,  ViewGroup parent) {
        //指定したxmlのレイアウト(View)リソースを利用できる仕組み
        LayoutInflater l = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflateでactivity_main.xmlを取得
        // http://d.hatena.ne.jp/nkawamura/20130716/1373980954
        View rowView = l.inflate(R.layout.activity_main,null);

        // 定義
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

        textView.setText(values[position]);

        String s = values[position];

        if(s.equals("Android"))
            imageView.setImageResource(R.drawable.android_logo);
        else if(s.equals("iOS"))
            imageView.setImageResource(R.drawable.ios_logo);
        else if (s.equals("Blackberry"))
            imageView.setImageResource(R.drawable.blackberry_logo);
        else
            imageView.setImageResource(R.drawable.windowsmobile_logo);

        return rowView;

    }

}