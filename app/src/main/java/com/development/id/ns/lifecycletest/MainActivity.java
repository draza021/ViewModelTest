package com.development.id.ns.lifecycletest;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String NUMBER_OF_CLICKS = "20";
    private TextView tvClickCounter;
    private Button btnClickMe, btnReset;
    private int counter = 0;
    private LiveModel liveModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClickCounter = (TextView) findViewById(R.id.text_view_click_counter);
        btnClickMe = (Button) findViewById(R.id.btn_click_me);
        btnReset = (Button) findViewById(R.id.btn_reset);
        liveModel = ViewModelProviders.of(this).get(LiveModel.class);

        btnClickMe.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        tvClickCounter.setText(String.valueOf(liveModel.getCount()));

        RxTextView.textChanges(tvClickCounter)
                .subscribe(charSequence -> {
                    if (Objects.equals(charSequence.toString(), NUMBER_OF_CLICKS)) {
                        Toast.makeText(this, "Clicks have reached: " + NUMBER_OF_CLICKS, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_click_me) {
            setCounter(liveModel);
        } else if (v.getId() == R.id.btn_reset) {
            resetCounter();
        }
    }

    private void resetCounter() {
        counter = 0;
        liveModel.setCount(counter);
        tvClickCounter.setText(String.valueOf(counter));
    }

    private void setCounter(LiveModel liveModel) {
        if (liveModel.getCount() == 0) {
            counter++;
            liveModel.setCount(counter);
            tvClickCounter.setText(String.valueOf(counter));
        } else {
            counter = liveModel.getCount();
            counter++;
            liveModel.setCount(counter);
            tvClickCounter.setText(String.valueOf(counter));
        }
    }

}
