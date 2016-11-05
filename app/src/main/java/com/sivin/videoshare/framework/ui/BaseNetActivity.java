package com.sivin.videoshare.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sivin.videoshare.framework.utils.NetworkUtil;

public abstract class BaseNetActivity extends AbsNetActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstRequestData();
    }

    protected void firstRequestData(){
       boolean canNext = beforeRequestData();
        if(!canNext)
            return;
        duringRefreshLoadData();
        startRefreshLoadData();
    }



    private boolean beforeRequestData() {
        if (!NetworkUtil.isConnected(this)){
            netWorkErr();
            return false;
        }
        return true;
    }

    protected abstract void duringRefreshLoadData();
    protected abstract void startRefreshLoadData();
}
