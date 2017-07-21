package com.example.senafunakubo.checkapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

TextView view = (TextView)findViewById(R.id.ch);
// SDKのバージョン取得
String strVer = "OS Version " + Build.VERSION.SDK_INT;
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
// 4.1以上
strVer += " (Jelly Bean)";
}
else {
// 1.x, 2.x, 3.x
}
view.setText(strVer);
}
}
