package com.pam.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    private Integer logo;
    private Integer color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        String version = getString(R.string.version);
        String isDebug = getString(R.string.isDebug);
        tv.setText("Version 2023(" + version + ")" + isDebug);

        logo = R.drawable.verif;

        iv.setImageDrawable(getDrawable(logo));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}