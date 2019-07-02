package com.qlvb.vnpt.botttt.schedule.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class SecondLevelExpandableListView extends ExpandableListView
{
    public SecondLevelExpandableListView(Context context) {
        super(context);
    }

    public SecondLevelExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecondLevelExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SecondLevelExpandableListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations. 
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
}