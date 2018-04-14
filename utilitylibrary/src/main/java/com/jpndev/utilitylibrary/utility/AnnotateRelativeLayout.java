package com.jpndev.utilitylibrary.utility;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jpndev.utilitylibrary.R;


/**
 * Created by Mani-Ceino on 11/15/2017.
 */

public class AnnotateRelativeLayout extends PercentRelativeLayout {
    public AnnotateRelativeLayout(Context context) {
        //super(context);
        this(context, null);
    }

/*    public JpRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }*/

    public AnnotateRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private View mValue;
    private ImageView mImage;
    DeviceFitImageView qs_dimv;
    private int dominantMeasurement;

    public static final int ASPECT_WIDTH = 0;
    public static final int ASPECT_HEIGHT = 1;

    public static final int FIXED_RATIO = 2;
    public static final int MEASUREMENT_WIDTH = 3;
    public static final int MEASUREMENT_HEIGHT = 4;
    public static final int SQUARE = 5;
    public static final int IMAGE_FIXED = 6;

    private static final int DEFAULT_DOMINANT_MEASUREMENT = ASPECT_WIDTH;
    public int percentage_width, percentage_height, tolerance_width, tolerance_height,blur_radius;

    private static final float DEFAULT_ASPECT_RATIO = 1f;
    public static final int DEFAULT_PERCENTAGE_WIDTH = 20;
    public static final int DEFAULT_PERCENTAGE_HEIGHT = 20;
    public static final int DEFAULT_TOLERANCE_WIDTH = 0;
    public static final int DEFAULT_TOLERANCE_HEIGHT = 1;
    private ImageView spinview;

    public AnnotateRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AnnotateRelativeLayout, 0, 0);
   /*     String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
        @SuppressWarnings("ResourceAsColor")
        int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,
                android.R.color.holo_blue_light);*/
        dominantMeasurement = a.getInt(R.styleable.AnnotateRelativeLayout_arl_parameter, DEFAULT_DOMINANT_MEASUREMENT);

        percentage_width = a.getInt(R.styleable.AnnotateRelativeLayout_arl_width_percentage, DEFAULT_PERCENTAGE_WIDTH);
        percentage_height = a.getInt(R.styleable.AnnotateRelativeLayout_arl_height_percentage, DEFAULT_PERCENTAGE_HEIGHT);
        // a.get
        tolerance_width = a.getInt(R.styleable.AnnotateRelativeLayout_arl_tolerance_width, DEFAULT_TOLERANCE_WIDTH);
        tolerance_height = a.getInt(R.styleable.AnnotateRelativeLayout_arl_tolerance_height, DEFAULT_TOLERANCE_HEIGHT);
        a.recycle();

      /*  setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);*/

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view= inflater.inflate(R.layout.anotate_rlay_view, this, true);

/*        TextView title = (TextView) getChildAt(0);
        title.setText(titleText);

        mValue = getChildAt(1);
        mValue.setBackgroundColor(valueColor);*/
        qs_dimv = (DeviceFitImageView) view.findViewById(R.id.qs_dimv);
        spinview = (ImageView) view.findViewById(R.id.spinview);
    //    qs_dimv.setHeightWidth();
        //qs_dimv = (DeviceFitImageView) getChildAt(0);
    }
    public void setImageBitmap(Bitmap bitmap) {
        try {
            LogUtils.LOGD("jp", "ANRL setImageBitmap qs_dimv set " );
            qs_dimv.setImageBitmap(bitmap);
            spinview.setVisibility(View.GONE);
            LogUtils.LOGD("jp", "ANRL setImageBitmap qs_dimv getHeight "+qs_dimv.getHeight() );
            LogUtils.LOGD("jp", "ANRL setImageBitmap qs_dimv getWidth "+qs_dimv.getWidth() );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setImageDrawable(Drawable bitmap) {
        try {
            LogUtils.LOGD("jp", "ANRL setImageDrawable qs_dimv set " );
            qs_dimv.setImageDrawable(bitmap);
            spinview.setVisibility(View.GONE);
            LogUtils.LOGD("jp", "ANRL setImageDrawable qs_dimv getHeight "+qs_dimv.getHeight() );
            LogUtils.LOGD("jp", "ANRL setImageDrawable qs_dimv getWidth "+qs_dimv.getWidth() );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
   public int newWidth ;
    public int newHeight ;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      // newWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        //newHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        switch (dominantMeasurement) {
            case SQUARE:
                newWidth = Resources.getSystem().getDisplayMetrics().widthPixels * percentage_width / 100;
              //  newWidth = newWidth - dpToPx(tolerance_width);
                // newHeight = newWidth;
                newHeight = newWidth;
                break;
            case ASPECT_WIDTH:
                newWidth = Resources.getSystem().getDisplayMetrics().widthPixels * percentage_width / 100;
                newWidth = newWidth - dpToPx(tolerance_width);
                // newHeight = newWidth;
                newHeight = (int) (newWidth /aspectRatio);
                break;

            case ASPECT_HEIGHT:

           /*         newWidth= Resources.getSystem().getDisplayMetrics().widthPixels/3;
                    newWidth=newWidth-dpToPx(tolerance_width);
                  //  int  threebywidth=newWidth/3;
                    newHeight=newWidth;*/

                newHeight = Resources.getSystem().getDisplayMetrics().heightPixels * percentage_height / 100;
               // newHeight = newHeight - dpToPx(tolerance_height);
                // newWidth = newHeight;
                newWidth = (int) (newHeight * aspectRatio);
                break;
            case FIXED_RATIO:
                newWidth = Resources.getSystem().getDisplayMetrics().widthPixels * percentage_width / 100;
              //  newWidth = newWidth - dpToPx(tolerance_width);
                // newHeight = newWidth;
                newHeight = Resources.getSystem().getDisplayMetrics().heightPixels * percentage_height / 100;
                newHeight = newHeight - dpToPx(tolerance_height);
                // newHeight = (int) (newWidth /aspectRatio);
                break;
            case MEASUREMENT_WIDTH:
                newWidth = getMeasuredWidth();
                newHeight = (int) (newWidth / aspectRatio);
              //  LogUtils.LOGD("jp", " newHeight " + newHeight);
                break;
            case MEASUREMENT_HEIGHT:
                newHeight = getMeasuredHeight();
                newWidth = (int) (newHeight * aspectRatio);
              //  LogUtils.LOGD("jp", " newHeight " + newHeight);
                break;
            case IMAGE_FIXED:
               // newHeight = heightMeasureSpec;
                //newWidth = widthMeasureSpec;
                LogUtils.LOGD("jp", " OTHER IMAGE_FIXED newWidth " + newWidth);
                LogUtils.LOGD("jp", " OTHER IMAGE_FIXED newHeight " + newHeight);
                break;
            default:
                throw new IllegalStateException("Unknown measurement with ID " + dominantMeasurement);
        }
     //   newHeight = newWidth;
        LogUtils.LOGD("jp", "ANRL OTHER switch onMeasure  newWidth" + newWidth);
        LogUtils.LOGD("jp", "ANRL OTHER switch onMeasure  newHeight" + newHeight);
        setMeasuredDimension(newWidth, newHeight);
        if(dominantMeasurement!=IMAGE_FIXED) {
            if (qs_dimv != null)
                qs_dimv.setPercentWidth(newWidth, newHeight,percentage_width);
        }
    }

    public void setHeightWidth(int widthMeasureSpec, int heightMeasureSpec)
    {
        LogUtils.LOGD("jp", "ANRL OTHER setHeightWidth  heightMeasureSpec " + heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
    public void setAspectHeightWidth(int widthMeasureSpec, int heightMeasureSpec,int pw,int ph)
    {
        LogUtils.LOGD("jp", "JPIMl setAspectHeightWidth   heightMeasureSpec " + heightMeasureSpec);
        LogUtils.LOGD("jp", "JPIMl setAspectHeightWidth   widthMeasureSpec " + widthMeasureSpec);
        if(qs_dimv!=null)
            qs_dimv.setAspectHeightWidth(widthMeasureSpec,heightMeasureSpec,pw,ph);

        if(heightMeasureSpec>widthMeasureSpec)
        {
            setPercentHeight(widthMeasureSpec,heightMeasureSpec,ph);
        }
        else

        {
            setPercentWidth(widthMeasureSpec,heightMeasureSpec,pw);
        }



    }
    public void setPercentHeightWidth(int widthMeasureSpec, int heightMeasureSpec,int pw,int ph)
    {
        if(qs_dimv!=null)
            qs_dimv.setPercentHeightWidth(widthMeasureSpec,heightMeasureSpec,pw,ph);

        double aspectRatio=(double)widthMeasureSpec/heightMeasureSpec;

        heightMeasureSpec = Resources.getSystem().getDisplayMetrics().heightPixels * ph / 100;

        widthMeasureSpec = Resources.getSystem().getDisplayMetrics().widthPixels * pw / 100;

        LogUtils.LOGD("jp", "anrl setPercentWidth  Resources.getSystem().getDisplayMetrics().widthPixels " + Resources.getSystem().getDisplayMetrics().widthPixels);
        LogUtils.LOGD("jp", "anrl setPercentWidth  aspectRatio " + aspectRatio);
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + heightMeasureSpec);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + widthMeasureSpec);
        dominantMeasurement=IMAGE_FIXED;
        newWidth=widthMeasureSpec;
        newHeight=heightMeasureSpec;
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + newHeight);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + newWidth);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

/*        if(qs_dimv!=null)
            qs_dimv.setPercentWidth(newWidth,newHeight,pw);*/

    }
    public void setPercentHeight(int widthMeasureSpec, int heightMeasureSpec,int pw)
    {
        if(qs_dimv!=null)
            qs_dimv.setPercentHeight(widthMeasureSpec,heightMeasureSpec,pw);

        double aspectRatio=(double)widthMeasureSpec/heightMeasureSpec;

        heightMeasureSpec = Resources.getSystem().getDisplayMetrics().heightPixels * pw / 100;
        //  newWidth = newWidth - dpToPx(tolerance_width);
        // newHeight = newWidth;
        widthMeasureSpec = (int) (heightMeasureSpec *aspectRatio);
        LogUtils.LOGD("jp", "anrl setPercentWidth  Resources.getSystem().getDisplayMetrics().widthPixels " + Resources.getSystem().getDisplayMetrics().widthPixels);
        LogUtils.LOGD("jp", "anrl setPercentWidth  aspectRatio " + aspectRatio);
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + heightMeasureSpec);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + widthMeasureSpec);
        dominantMeasurement=IMAGE_FIXED;
        newWidth=widthMeasureSpec;
        newHeight=heightMeasureSpec;
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + newHeight);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + newWidth);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

/*        if(qs_dimv!=null)
            qs_dimv.setPercentWidth(newWidth,newHeight,pw);*/

    }



    public void setPercentWidth(int widthMeasureSpec, int heightMeasureSpec,int pw)
    {
        if(qs_dimv!=null)
            qs_dimv.setPercentWidth(widthMeasureSpec,heightMeasureSpec,pw);

        double aspectRatio=(double)widthMeasureSpec/heightMeasureSpec;
        widthMeasureSpec = Resources.getSystem().getDisplayMetrics().widthPixels * pw / 100;
        //  newWidth = newWidth - dpToPx(tolerance_width);
        // newHeight = newWidth;
        heightMeasureSpec = (int) (widthMeasureSpec /aspectRatio);
        LogUtils.LOGD("jp", "anrl setPercentWidth  Resources.getSystem().getDisplayMetrics().widthPixels " + Resources.getSystem().getDisplayMetrics().widthPixels);
        LogUtils.LOGD("jp", "anrl setPercentWidth  aspectRatio " + aspectRatio);
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + heightMeasureSpec);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + widthMeasureSpec);
        dominantMeasurement=IMAGE_FIXED;
        newWidth=widthMeasureSpec;
        newHeight=heightMeasureSpec;
        LogUtils.LOGD("jp", "anrl setPercentWidth  heightMeasureSpec " + newHeight);
        LogUtils.LOGD("jp", "anrl setPercentWidth  widthMeasureSpec " + newWidth);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

/*        if(qs_dimv!=null)
            qs_dimv.setPercentWidth(newWidth,newHeight,pw);*/

    }

    public void setHeightWidthParamters(int dominantMeasurement, int pw,int ph)
    {
        int widthMeasureSpec = Resources.getSystem().getDisplayMetrics().widthPixels * pw / 100;

        int  heightMeasureSpec =  Resources.getSystem().getDisplayMetrics().heightPixels * ph / 100;
        if(qs_dimv!=null) {
            qs_dimv.setHeightWidthParamters( pw,ph);
            qs_dimv.setScaleType(ImageView.ScaleType.FIT_START);
        }
        dominantMeasurement=IMAGE_FIXED;
        newWidth=widthMeasureSpec;
        newHeight=heightMeasureSpec;
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
    public void setHeightWidthRatio(int widthMeasureSpec, int heightMeasureSpec)
    {
        LogUtils.LOGD("jp", "ANRL OTHER  setHeightWidthRatio   heightMeasureSpec " + heightMeasureSpec);
        double aspectRatio=(double)widthMeasureSpec/heightMeasureSpec;
        newWidth = Resources.getSystem().getDisplayMetrics().widthPixels * percentage_width / 100;
      //  newWidth = newWidth - dpToPx(tolerance_width);
        // newHeight = newWidth;
        newHeight = (int) (newWidth /aspectRatio);
       /*
        if (isValid(info.height)) {
            //  device_height= info.height;
            device_height = (int) (device_width /aspectRatio);
        }*/

        //measure(newWidth, newHeight);
        LogUtils.LOGD("layerutils", "ARL setHeightWidthRatio  newWidth " + newWidth);
        LogUtils.LOGD("layerutils", "ARL setHeightWidthRatio  newHeight " + newHeight);
        setMeasuredDimension(newWidth, newHeight);
    //    if(qs_dimv!=null)
            //qs_dimv.setHeightWidth(newWidth,newHeight);
    }
    public DeviceFitImageView getImageView()
    {
        return qs_dimv;
    }

    public void setValueColor(int color) {
        mValue.setBackgroundColor(color);
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    public void setSpinVisible(boolean visible) {
        spinview.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
