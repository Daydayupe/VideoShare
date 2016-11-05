package com.sivin.videoshare.ui.main;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sivin.videoshare.R;


public class BottomTab extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public BottomTab(Context context) {
        super(context);
        init(context);
    }

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);




        init(context);
    }

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.bottom_tabview_layout, this, true);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mTextView = (TextView) findViewById(R.id.textview);
    }


    /**
     * 设置tab下面的文字
     *
     * @param text text
     */
    public void setTabText(String text) {
        mTextView.setText(text);
    }

    /**
     * 设置组件背景
     * @param drawableId drawableId
     * @param textColor  textColor
     */
    public void setTabBackground(int drawableId, int textColor) {
        mImageView.setImageResource(drawableId);
        mTextView.setTextColor(textColor);
    }

}
