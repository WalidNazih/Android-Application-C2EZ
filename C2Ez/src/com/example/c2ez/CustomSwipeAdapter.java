package com.example.c2ez;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomSwipeAdapter extends PagerAdapter{
	
	private int[] imageResources = {R.drawable.slide_011, R.drawable.slide_12, R.drawable.slide_13, R.drawable.slide_15, R.drawable.slide_19, R.drawable.slide_20};
	private Context context;
	private LayoutInflater layout;
	
	public CustomSwipeAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageResources.length;
	}

	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layout.inflate(R.layout.swipe, container, false);
		ImageView image = (ImageView) view.findViewById(R.id.slide);
		image.setImageResource(imageResources[position]);
		container.addView(view);
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((LinearLayout)object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0==(LinearLayout) arg1);
	}

}
