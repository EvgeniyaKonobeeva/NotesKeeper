package com.example.ekonobeeva.noteskeeper;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Evgenia on 20.09.2016.
 */
public class SizedCardView extends CardView {
    private final static int MAX_SIZE = 500;
    public SizedCardView(Context context) {
        super(context);
    }

    public SizedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SizedCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MAX_SIZE, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
