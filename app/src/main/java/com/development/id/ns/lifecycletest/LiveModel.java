package com.development.id.ns.lifecycletest;
/*
 * LiveModel.java
 *
 * Created by Drago on 9/21/2017
 */

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

public class LiveModel extends ViewModel {

    @Nullable
    private int count;

    @Nullable
    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }
}
