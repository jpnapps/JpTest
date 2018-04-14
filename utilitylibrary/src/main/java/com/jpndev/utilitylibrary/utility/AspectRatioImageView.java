package com.jpndev.utilitylibrary.utility;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jpndev.utilitylibrary.R;


/**
 * Created by jithish on 18-08-2014.
 */
public class AspectRatioImageView extends ImageView
{

	public static final int			MEASUREMENT_WIDTH				= 0;
	public static final int			MEASUREMENT_HEIGHT				= 1;

	private static final float		DEFAULT_ASPECT_RATIO			= 1f;
	private static final boolean	DEFAULT_ASPECT_RATIO_ENABLED	= false;
	private static final int		DEFAULT_DOMINANT_MEASUREMENT	= MEASUREMENT_HEIGHT;
    private static final boolean	DEFAULT_NORMAL	= false;
	private float					aspectRatio;
	private boolean					aspectRatioEnabled,normal;
	private int						dominantMeasurement;
    public static  int			past_newHeight				= 0;
	public AspectRatioImageView(Context context)
	{
		this(context, null);
	}

	public AspectRatioImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
		aspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspectRatio, DEFAULT_ASPECT_RATIO);
		aspectRatioEnabled = a.getBoolean(R.styleable.AspectRatioImageView_aspectRatioEnabled, DEFAULT_ASPECT_RATIO_ENABLED);
        normal = a.getBoolean(R.styleable.AspectRatioImageView_normal, DEFAULT_NORMAL);
        dominantMeasurement = a.getInt(R.styleable.AspectRatioImageView_dominantMeasurement, DEFAULT_DOMINANT_MEASUREMENT);
		a.recycle();
	}
    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int newWidth= Resources.getSystem().getDisplayMetrics().widthPixels;;
            int newHeight;
			if (!aspectRatioEnabled)
				return;
			Drawable drawable = getDrawable();
            if(normal)
            {

                switch (dominantMeasurement) {
                    case MEASUREMENT_WIDTH:
                        aspectRatio=0.56f;
                        //     newWidth = getMeasuredWidth();
                        newWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                        newHeight = (int) (newWidth * aspectRatio);
                        LogUtils.LOGD("jp", " newHeight " + newHeight);
                        break;

                    case MEASUREMENT_HEIGHT:


                        newHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
                        aspectRatio=1.3f;
                        newWidth = (int) (newHeight * aspectRatio);
                        LogUtils.LOGD("jp", " newHeight " + newHeight);
                        break;

                    default:
                        throw new IllegalStateException("Unknown measurement with ID " + dominantMeasurement);
                }
                setMeasuredDimension(newWidth, newHeight);
            }
            else {
                if(drawable == null) {
                    setMeasuredDimension(0, 0);
                } else {

                   aspectRatio = (float) drawable.getMinimumWidth() / drawable.getMinimumHeight();


                    //	aspectRatio = (float) drawable.getMinimumHeight() / drawable.getMinimumWidth();
                    LogUtils.LOGD("jp", " aspectRatio " + aspectRatio);
                    LogUtils.LOGD("jp", " drawable getMinimumWidth " + drawable.getMinimumWidth());
                    LogUtils.LOGD("jp", " drawable.getMinimumHeight() " + drawable.getMinimumHeight());


                    switch (dominantMeasurement) {
                        case MEASUREMENT_WIDTH:
                            newWidth = getMeasuredWidth();
                            newHeight = (int) (newWidth * aspectRatio);
                            LogUtils.LOGD("jp", " newHeight " + newHeight);
                            break;

                        case MEASUREMENT_HEIGHT:
                            // newHeight=dpToPx(250);
                            // newHeight=dpToPx(250);


                 /*           int device_height = Resources.getSystem().getDisplayMetrics().heightPixels;
                            // if(device_height>100)
                            //  {
                            newHeight = ((device_height / 2) / 5) * 3;*/

                            //  }
                           int   lockedWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
                          newHeight=(int)(lockedWidth / aspectRatio );
                       /*     if((device_height-lockedHeight)-newHeight<350)
                            {
                              //  if(device_height-lockedHeight>=350)
                                    newHeight =device_height-lockedHeight-350;
                               // else
                                   // newHeight = ((device_height / 2) / 5) * 2;

                            }
                            // int  newHeight2=getMeasuredHeight();
                            //  if(newHeight)
                            if(past_newHeight == 0) past_newHeight = newHeight;
                            //  newHeight = Resources.getSystem().getDisplayMetrics().heightPixels;//getMaxHeight();// getMeasuredHeight();
                            newWidth = (int) (past_newHeight * aspectRatio);
                            LogUtils.logD("jp", " newWidth " + newWidth);*/
                            break;
                      /*
                           newHeight =getMeasuredHeight();
                            if(past_newHeight==0)
                              past_newHeight=newHeight;
							newWidth = (int) (past_newHeight * aspectRatio);
							LogUtils.logD("jp", " newWidth " + newWidth);
							break;*/

                        default:
                            throw new IllegalStateException("Unknown measurement with ID " + dominantMeasurement);
                    }
                    setMeasuredDimension(newWidth, newHeight);
                }
            }


		}

	/** Get the aspect ratio for this image view. */
	public float getAspectRatio()
		{
			return aspectRatio;
		}

	/**
	 * Set the aspect ratio for this image view. This will update the view
	 * instantly.
	 */
	public void setAspectRatio(float aspectRatio)
		{
			this.aspectRatio = aspectRatio;
			if (aspectRatioEnabled)
			{
				requestLayout();
			}
		}

	/** Get whether or not forcing the aspect ratio is enabled. */
	public boolean getAspectRatioEnabled()
		{
			return aspectRatioEnabled;
		}

	/**
	 * set whether or not forcing the aspect ratio is enabled. This will
	 * re-layout the view.
	 */
	public void setAspectRatioEnabled(boolean aspectRatioEnabled)
		{
			this.aspectRatioEnabled = aspectRatioEnabled;
			requestLayout();
		}

	/** Get the dominant measurement for the aspect ratio. */
	public int getDominantMeasurement()
		{
			return dominantMeasurement;
		}

	/**
	 * Set the dominant measurement for the aspect ratio.
	 * 
	 * @see #MEASUREMENT_WIDTH
	 * @see #MEASUREMENT_HEIGHT
	 */
	public void setDominantMeasurement(int dominantMeasurement)
		{
			if (dominantMeasurement != MEASUREMENT_HEIGHT && dominantMeasurement != MEASUREMENT_WIDTH)
			{
				throw new IllegalArgumentException("Invalid measurement type.");
			}
			this.dominantMeasurement = dominantMeasurement;
			requestLayout();
		}
}