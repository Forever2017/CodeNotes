package com.frid.view;

import com.frid.fridapp.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NormalTitleBar extends RelativeLayout implements View.OnClickListener {

	protected RelativeLayout mRootView;
	protected TextView titleTextView;
	protected ImageView backImageView;
	protected Button TitleButRight;
	protected String mTitle = "";
	protected boolean left_visible = true;

	public NormalTitleBar(Context context) {
		super(context);
		doInit(context, null);
	}

	public NormalTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		doInit(context, attrs);
	}

	public NormalTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		doInit(context, attrs);
	}

	protected void doInit(Context context, AttributeSet attrs) {
		LayoutInflater.from(context).inflate(setLayoutId(), this, true);
		mRootView = (RelativeLayout) findViewById(R.id.mRootView);
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
		init(context, attributes);
		recycleAttributeSet(attributes);
	}

	protected
	int setLayoutId() {
		return R.layout.view_title_normal;
	}

	protected void init(Context context, TypedArray attrs) {
		titleTextView = (TextView) mRootView.findViewById(R.id.titleTextView);
		TitleButRight = (Button) mRootView.findViewById(R.id.TitleButRight);
		backImageView = (ImageView) mRootView.findViewById(R.id.backImageView);
		backImageView.setOnClickListener(this);
		TitleButRight.setOnClickListener(this);
		if (attrs != null) {
			mTitle = attrs.getString(R.styleable.TopBar_title);
			titleTextView.setText(mTitle);

			left_visible = attrs.getBoolean(R.styleable.TopBar_left_visible, left_visible);
			backImageView.setVisibility(left_visible?View.VISIBLE:View.GONE);
		}
	}

	public void setLeftVisible(boolean b) {
		left_visible = b;
		backImageView.setVisibility(left_visible?View.VISIBLE:View.GONE);
	}


	protected void recycleAttributeSet(TypedArray attributes) {
		if (attributes != null) {
			attributes.recycle();
		}
	}

	public void setTitle(String title) {
		mTitle = title;
		titleTextView.setText(mTitle);
	}
	public void setRight(String text) {
		TitleButRight.setVisibility(View.VISIBLE);
		TitleButRight.setText(text);
	}
	rClick rclick;
	public void rightOnClick(rClick rclick){
		this.rclick = rclick;
	}
	public static abstract class rClick{
		public void onResult(String msg){};
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImageView:
			getActivity().setResult(Activity.RESULT_CANCELED);
			getActivity().onBackPressed();
			break;
		case R.id.TitleButRight:
			if(rclick!=null)
				rclick.onResult("点击右边键");
			break;

		default:
			break;
		}

	}

	private Activity getActivity() {
		Context context = getContext();
		while (!(context instanceof Activity) && context instanceof ContextWrapper) {
			context = ((ContextWrapper) context).getBaseContext();
		}
		if (context instanceof Activity) {
			return (Activity) context;
		}
		throw new RuntimeException("Unable to get Activity.");
	}
}
