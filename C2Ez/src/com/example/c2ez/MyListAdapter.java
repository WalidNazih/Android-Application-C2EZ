package com.example.c2ez;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {

	protected JSONArray results;
	protected Activity activity;
	protected int layout;
	protected Context context;
	private static LayoutInflater inflater = null;
	static ArrayList<Integer> map;

	public MyListAdapter(JSONArray results, Activity activity, int layout, Context context) {
		this.results = results;
		this.activity = activity;
		this.layout = layout;
		this.context = context;
		map = new ArrayList<Integer>();
		inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.results.length();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(!map.contains(position)){
			Cell cell;
			JSONObject object = null;
			try {
				object = this.results.getJSONObject(position);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (convertView == null) {
				cell = new Cell();
				convertView = inflater.inflate(layout, null);
				
			} else {
				cell = (Cell) convertView.getTag();
			}

			cell.image = (ImageView) convertView.findViewById(R.id.image);
			cell.title = (TextView) convertView.findViewById(R.id.title);
			Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/digital-7.ttf");
			cell.title.setTypeface(custom_font);
			try {
				cell.title.setText(object.getString("name"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				new DownloadImageTask(cell.image).execute("http://c2ez.ma/image.php?id=" + object.getString("id_product"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			convertView.setTag(cell);
			map.add(position);
		}
		return convertView;
	}

	public String getUrl(int position) throws JSONException {
		JSONObject object = this.results.getJSONObject(position);
		return object.getString("url");
	}

	private static class Cell {
		protected TextView title;
		protected ImageView image;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, String> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		@Override
		protected void onPreExecute() {
			bmImage.setImageResource(R.drawable.defaultimg);
		}

		protected String doInBackground(String... urls) {
			String imgUrl = null;
			imgUrl = new Connector().GetImagePath(urls[0]);
			return imgUrl;
		}

		protected void onPostExecute(String result) {
			if (result == null) {
				bmImage.setImageResource(R.drawable.defaultimg);
			} else {
				Picasso.with(context).load(result).resize(50, 50).centerCrop().into(bmImage);
				Log.v("image", result);
				bmImage.setTag(result);
			}
		}
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount();
	}

	
	
}
