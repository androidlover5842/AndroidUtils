package com.androidlover5842.androidUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlover5842.androidUtils.Utils.Utils;

public class AndroidRecyclerView extends RecyclerView {

    private boolean loading;
    private String text;
    private int progressColor,textColor;

    private RectF rectF=new RectF();
    private Paint paint=new Paint();
    private TextPaint textPaint=new TextPaint();
    private int progressArcRadius=dip2px(15);
    private RotateAnimation rotateProgress;
    private int height,width;
    private Adapter adapter;
    private BaseAdapter baseAdapter;

    public AndroidRecyclerView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public AndroidRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AndroidRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet){
        if (getLayoutManager()==null)
            setLayoutManager(Utils.linear(VERTICAL));

        TypedArray a = getContext().obtainStyledAttributes(attributeSet,
                R.styleable.AndroidRecyclerView, 0, 0);
        loading=a.getBoolean(R.styleable.AndroidRecyclerView_loading,false);
        text=a.getString(R.styleable.AndroidRecyclerView_android_text);
        progressColor=a.getColor(R.styleable.AndroidRecyclerView_android_progressTint,Color.BLACK);
        textColor=a.getColor(R.styleable.AndroidRecyclerView_android_textColor,Color.BLACK);

        baseAdapter=new BaseAdapter();

        rotateProgress = new RotateAnimation(0f,
                360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotateProgress.setInterpolator(new LinearInterpolator());
        rotateProgress.setDuration(400);
        rotateProgress.setRepeatCount(Animation.INFINITE);
        setupPaint();
        setLoading(loading);

        a.recycle();
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {


        if (adapter!=null){
            if (!adapter.getClass().equals("com.androidlover5842.androidUtils.BaseAdapter"))
                this.adapter=adapter;

            if (!adapter.hasObservers())
                adapter.registerAdapterDataObserver(observer);
            if (adapter.getItemCount()>0 && loading)
                adapter=baseAdapter;
            else
                adapter = this.adapter;

        }
        super.setAdapter(adapter);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int[] spec=getViewSize(widthSpec,heightSpec);
        super.onMeasure(spec[0],spec[1]);
    }

    @Override
    public void onDraw(Canvas c) {
        if (!loading) {
            boolean empty=getAdapter()!=null?getAdapter().getItemCount()==0:false;

            if (empty && text!=null){
                int xPos = (width/ 2);
                int yPos = (int) ((height / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
                c.drawText(text,xPos, yPos,textPaint);
            }else {
                super.onDraw(c);
            }
        }
        else{
            rectF.set((width/2)-progressArcRadius,
                    (height/2)-progressArcRadius,
                    (width/2)+progressArcRadius,
                    (height/2)+progressArcRadius);

            c.drawArc(rectF,90,90,false,paint);
        }

    }

    public void setText(String text) {
        this.text = text;
    }

    private void setupPaint(){
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dip2px(5));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dip2px(13));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(textColor);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;

        if (loading){
            setAdapter(null);
            startAnimation(rotateProgress);
        }
        else
        {
            setAdapter(adapter);
            rotateProgress.cancel();
        }
        invalidate();
    }

    public boolean isLoading() {
        return loading;
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setLoading(loading);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            setLoading(loading);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            setLoading(loading);
        }
    };

}
