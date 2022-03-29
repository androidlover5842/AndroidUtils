package com.androidlover5842.androidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class MeterView  extends View {

    private String[] colors={"#FF228654","#FF09C488","#FFF7941E","#FFE35744","#FFED1C24"};
    private AttributeSet attrs;
    private RectF rectF=new RectF();
    private Paint paint=new Paint();
    private int height,width;

    public MeterView(Context context) {
        super(context,null);
        init();
    }

    public MeterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.attrs=attrs;
        init();
    }

    public MeterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs=attrs;

    }

    private void init(){
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.ProgressButton, 0, 0);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        createPi(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] size=getViewSize(widthMeasureSpec,heightMeasureSpec);
        super.onMeasure(size[0],size[1]);
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void setText(String text){

    }

    public void setPosition(int position){
//        if (position==0) {
//            indicator.setRotation(-105f);
//            textView.setTextColor(getResources().getColor(R.color.dark_green));
//        }
//        else if (position==1) {
//            indicator.setRotation(-60f);
//            textView.setTextColor(getResources().getColor(R.color.light_green));
//        }
//        else if (position==2) {
//            indicator.setRotation(0f);
//            textView.setTextColor(getResources().getColor(R.color.orange));
//        }
//        else if (position==3) {
//            indicator.setRotation(60f);
//            textView.setTextColor(getResources().getColor(R.color.light_red));
//        }
//        else if (position==4) {
//            indicator.setRotation(105f);
//            textView.setTextColor(getResources().getColor(R.color.dark_red));
//        }

    }

    private void createPi(Canvas canvas){
        float mRadius = (float) (height/2f);

        rectF.set(width/2f-mRadius,
                height/2f-mRadius,
                width/2f+mRadius,
                height/2f+mRadius);

        int initialAngel=150;
        int sweepAngel=48;

        for (int i = 0; i < colors.length; i++) {
            paint.setColor(Color.parseColor(colors[i]));
            paint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawArc(rectF,initialAngel,sweepAngel,true,paint);
            initialAngel=initialAngel+sweepAngel;

        }

        paint.setColor(Color.WHITE);
        mRadius = (float) (height/3f);

        canvas.drawCircle(width/2f,height/2f,mRadius,paint);
    }

    private int[] getViewSize(int widthMeasureSpec,int heightMeasureSpec){
        int heightModeSpec=MeasureSpec.getMode(heightMeasureSpec);
        int widthModeSpec=MeasureSpec.getMode(widthMeasureSpec);
        int defaultHeight=400;
        int defaultWidth=400;
        if (heightModeSpec==MeasureSpec.AT_MOST)
            heightMeasureSpec=MeasureSpec.makeMeasureSpec(defaultHeight,MeasureSpec.EXACTLY);
        if(widthModeSpec==MeasureSpec.AT_MOST)
            widthMeasureSpec=MeasureSpec.makeMeasureSpec(defaultWidth,MeasureSpec.EXACTLY);
        this.width=MeasureSpec.getSize(widthMeasureSpec);
        this.height=MeasureSpec.getSize(heightMeasureSpec);
        return new int[]{widthMeasureSpec,heightMeasureSpec};
    }
}
