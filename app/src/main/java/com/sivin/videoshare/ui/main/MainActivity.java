package com.sivin.videoshare.ui.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.sivin.videoshare.R;
import com.sivin.videoshare.framework.ui.AbsActivity;
import com.sivin.videoshare.ui.mine.MineFragment;
import com.sivin.videoshare.ui.newest.NewFragment;
import com.sivin.videoshare.ui.topic.TopicFragment;
import com.sivin.videoshare.ui.video.VideoFragment;

public class MainActivity extends AbsActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int TYPE_NEW = 0;
    private static final int TYPE_VIDEO = 1;
    private static final int TYPE_TOPIC = 2;
    private static final int TYPE_MINE = 3;

    private static int mCurrentFragmentType = -1;


    private Context mContext;
    /**
     * 四个底部标签切换按钮
     */
    private BottomTab mNewestTab;
    private BottomTab mVideoTab;
    private BottomTab mTopicTab;
    private BottomTab mMineTab;

    private NewFragment mNewestFragment;
    private VideoFragment mVideoFragment;
    private TopicFragment mTopicFragment;
    private MineFragment mMineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initData();
        initEvent();
//        switchFragment(TYPE_NEW);
    }

    private void initView() {
        mNewestTab = (BottomTab) findViewById(R.id.newest_tab);
        mNewestTab.setTabText(getString(R.string.newest_tab_text));
        resTab(TYPE_NEW);
        mNewestTab.setOnClickListener(this);

        mVideoTab = (BottomTab) findViewById(R.id.video_tab);
        mVideoTab.setTabText(getString(R.string.video_tab_text));
        resTab(TYPE_VIDEO);
        mVideoTab.setOnClickListener(this);

        mTopicTab = (BottomTab) findViewById(R.id.topic_tab);
        mTopicTab.setTabText(getString(R.string.topic_tab_text));
        resTab(TYPE_TOPIC);
        mTopicTab.setOnClickListener(this);

        mMineTab = (BottomTab) findViewById(R.id.mine_tab);
        mMineTab.setTabText(getString(R.string.mine_tab_text));
        resTab(TYPE_MINE);
        mMineTab.setOnClickListener(this);
    }

    private void initData() {


    }

    private void initEvent() {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentType", mCurrentFragmentType);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int type = savedInstanceState.getInt("currentType");
        switchFragment(type);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.newest_tab:
                switchFragment(TYPE_NEW);
                break;
            case R.id.video_tab:
                switchFragment(TYPE_VIDEO);
                break;
            case R.id.topic_tab:
                switchFragment(TYPE_TOPIC);
                break;
            case R.id.mine_tab:
                switchFragment(TYPE_MINE);
                break;
        }
    }


    public void switchFragment(int type) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        hideAllFragment(ft, mCurrentFragmentType);

        switch (type) {
            case TYPE_NEW:
                if (mNewestFragment == null) {
                    mNewestFragment = new NewFragment();
                    ft.add(R.id.container, mNewestFragment, "newest");
                }
                ft.show(mNewestFragment);

                if (Build.VERSION.SDK_INT < 23) {
                    mNewestTab.setTabBackground(R.mipmap.newest_red, getResources().getColor(R.color.colorPrimary));
                } else {
                    mNewestTab.setTabBackground(R.mipmap.newest_red, getColor(R.color.colorPrimary));
                }

                mCurrentFragmentType = TYPE_NEW;
                break;

            case TYPE_VIDEO:
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    ft.add(R.id.container, mVideoFragment, "video");
                }
                ft.show(mVideoFragment);


                if (Build.VERSION.SDK_INT < 23) {
                    mVideoTab.setTabBackground(R.mipmap.video_red, getResources().getColor(R.color.colorPrimary));
                } else {
                    mVideoTab.setTabBackground(R.mipmap.video_red, getColor(R.color.colorPrimary));
                }

                mCurrentFragmentType = TYPE_VIDEO;
                break;

            case TYPE_TOPIC:
                if (mTopicFragment == null) {
                    mTopicFragment = new TopicFragment();
                    ft.add(R.id.container, mTopicFragment, "topic");
                }
                ft.show(mTopicFragment);

                if (Build.VERSION.SDK_INT < 23) {
                    mTopicTab.setTabBackground(R.mipmap.topic_red, getResources().getColor(R.color.colorPrimary));
                } else {
                    mTopicTab.setTabBackground(R.mipmap.topic_red, getColor(R.color.colorPrimary));
                }

                mCurrentFragmentType = TYPE_TOPIC;
                break;


            case TYPE_MINE:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    ft.add(R.id.container, mMineFragment, "mine");
                }
                ft.show(mMineFragment);

                if (Build.VERSION.SDK_INT < 23) {
                    mMineTab.setTabBackground(R.mipmap.mine_red, getResources().getColor(R.color.colorPrimary));
                } else {
                    mMineTab.setTabBackground(R.mipmap.mine_red, getColor(R.color.colorPrimary));
                }

                mCurrentFragmentType = TYPE_MINE;
                break;
        }
        ft.commit();
    }


    private void hideAllFragment(FragmentTransaction ft, int CurrentShowType) {
        if (CurrentShowType < 0)
            return;
        switch (CurrentShowType) {
            case TYPE_NEW:
                ft.hide(mNewestFragment);
                resTab(TYPE_NEW);
                break;
            case TYPE_VIDEO:
                ft.hide(mVideoFragment);
                resTab(TYPE_VIDEO);

                break;
            case TYPE_TOPIC:
                ft.hide(mTopicFragment);
                resTab(TYPE_TOPIC);
                break;
            case TYPE_MINE:
                ft.hide(mMineFragment);
                resTab(TYPE_MINE);
                break;
        }
    }

    private void resTab(int currentShowType) {

        Log.d(TAG, "resTab: "+currentShowType);

        switch (currentShowType) {
            case TYPE_NEW:
                if (Build.VERSION.SDK_INT < 23) {
                    mNewestTab.setTabBackground(R.mipmap.newest_grey, getResources().getColor(R.color.color_tab_text_grey));
                } else {
                    mNewestTab.setTabBackground(R.mipmap.newest_grey, getColor(R.color.color_tab_text_grey));
                }
                break;
            case TYPE_VIDEO:
                if (Build.VERSION.SDK_INT < 23) {
                    mVideoTab.setTabBackground(R.mipmap.video_grey, getResources().getColor(R.color.color_tab_text_grey));
                } else {
                    mVideoTab.setTabBackground(R.mipmap.video_grey, getColor(R.color.color_tab_text_grey));
                }
                break;
            case TYPE_TOPIC:

                if (Build.VERSION.SDK_INT < 23) {
                    Log.d(TAG, "resTab: ");
                    mTopicTab.setTabBackground(R.mipmap.topic_grey, getResources().getColor(R.color.color_tab_text_grey));
                } else {
                    mTopicTab.setTabBackground(R.mipmap.topic_grey, getColor(R.color.color_tab_text_grey));
                }
                break;
            case TYPE_MINE:
                if (Build.VERSION.SDK_INT < 23) {
                    mMineTab.setTabBackground(R.mipmap.mine_grey, getResources().getColor(R.color.color_tab_text_grey));
                } else {
                    mMineTab.setTabBackground(R.mipmap.mine_grey, getColor(R.color.color_tab_text_grey));
                }
                break;
        }


    }


}
