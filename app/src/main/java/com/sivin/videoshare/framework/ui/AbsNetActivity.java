package com.sivin.videoshare.framework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.sivin.videoshare.R;
import com.sivin.videoshare.framework.iview.AbsNetIView;
import com.sivin.videoshare.framework.utils.NetworkUtil;

/**
 * 网络页面切换逻辑
 * Created by Sivin on 2016/11/4.
 */

public abstract class AbsNetActivity extends AbsActivity implements AbsNetIView {

    private static final int FIRST_CHILD = 0;

    //网络响应错误显示的布局
    private View mNetWorkFailLayout;

    private View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        netRootInit();
    }

    private void netRootInit() {
        initRootView();

    }

    private void initRootView() {
        FrameLayout rootContainer = (FrameLayout) findViewById(R.id.activity_root);
        mContentView = LayoutInflater.from(this).inflate(setContentResView(), null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootContainer.addView(mContentView, FIRST_CHILD, lp);
    }


    /**
     * 加载网络错误布局
     */
    private void inflateErrLayout() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.id_net_err);
        if (viewStub != null) {
            mNetWorkFailLayout = viewStub.inflate();
            mNetWorkFailLayout.findViewById(R.id.net_error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startReLoad();
                }
            });
        }
    }


    /**
     * 开始从新获取数据
     */
    private void startReLoad() {
        boolean canNext = beforeReLoad();
        if (!canNext)
            return;
        duringReloadData();
        startReLoadData();
    }

    private boolean beforeReLoad() {
        if (NetworkUtil.isConnected(this)) {
            mNetWorkFailLayout.setVisibility(View.GONE);
            return false;
        }
        return true;
    }


    @Override
    public void netWorkErr() {
        if (mNetWorkFailLayout != null) {
            mNetWorkFailLayout.setVisibility(View.VISIBLE);
            return;
        }
        inflateErrLayout();
    }


    /**
     * 设置内容布局Id
     *
     * @return
     */
    protected abstract int setContentResView();

    /**
     * 开始加载数据
     */
    protected abstract void startReLoadData();

    /**
     * 当正在加载数据时处理的事
     */
    protected abstract void duringReloadData();
}
