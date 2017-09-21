package com.development.id.ns.lifecycletest;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvClickCounter;
    private Button btnClickMe;
    private int counter = 0;
    private LiveModel liveModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClickCounter = (TextView) findViewById(R.id.text_view_click_counter);
        btnClickMe = (Button) findViewById(R.id.btn_click_me);
        liveModel = ViewModelProviders.of(this).get(LiveModel.class);

        btnClickMe.setOnClickListener(this);
        tvClickCounter.setText(String.valueOf(liveModel.getCount()));
    }

    @Override
    public void onClick(View v) {
        if (liveModel.getCount() == 0) {
            setCounter(liveModel);
        } else {
            counter = liveModel.getCount();
            setCounter(liveModel);
        }
    }

    private void setCounter(LiveModel liveModel) {
        counter++;
        liveModel.setCount(counter);
        tvClickCounter.setText(String.valueOf(counter));
    }
}