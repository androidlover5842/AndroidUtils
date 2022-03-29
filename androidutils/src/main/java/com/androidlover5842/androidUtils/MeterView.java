package com.androidlover5842.androidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class MeterView  extends View {

    private String[] colors={"#FF228654","#FF09C488","#FFF7941E","#FFE35744","#FFED1C24"};
    int[] needleOrient=new int[]{-180,-135,-90,-45,0};

    private AttributeSet attrs;
    private RectF rectF=new RectF();
    private Paint paint=new Paint();
    private TextPaint textPaint=new TextPaint(TextPaint.ANTI_ALIAS_FLAG);

    private int height,width;
    private int position;

    private String text;
    public MeterView(Context context) {
        super(context);
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
        init();
    }

    private void init(){
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.MeterView, 0, 0);
        text= a.getString(R.styleable.MeterView_android_text);
        position=a.getInteger(R.styleable.MeterView_defaultNeedlePosition,0);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        createPi(canvas);
        createNeedle(canvas);
        if (text!=null)
            createText(canvas,text);
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
        this.text=text;
    }

    public void setPosition(int position){
        this.position=position;
        invalidate();
    }

    private void createPi(Canvas canvas){

        float mRadius = (float) (width/2f)-dip2px(10);

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
        mRadius = (float) (width/3f)-dip2px(5);
        canvas.drawCircle(width/2f,height/2f,mRadius,paint);
        canvas.save();
    }

    private void createNeedle(Canvas canvas){
        canvas.rotate(needleOrient[position],width/2,height/2);
        paint.setStrokeWidth(dip2px(4));
        paint.setColor(Color.parseColor(colors[position]));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        canvas.drawLine(width/2,height/2,getWidth()/2+getWidth()/4,getHeight()/2,paint);
        canvas.drawCircle(width/2,height/2,10,paint);
        canvas.restore();
        canvas.save();
    }
    private void createText(Canvas canvas,String text){
        textPaint.setColor(Color.parseColor(colors[position]));
        textPaint.setTextSize(dip2px(13));
        textPaint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        canvas.drawText(text, xPos, yPos+height/4, textPaint);
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
