package com.example.c2ez;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Products extends Activity {

	protected ListView articleList;
	protected Context context;
	protected List<JSONObject> articles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		articleList = (ListView) findViewById(R.id.productList);
		articles = new ArrayList<JSONObject>();
		new GetAllArticles().execute(new Connector());
		context = this;
		articleList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
	}
	
	public void setListAdapter(JSONArray array){
		
		this.articleList.setAdapter(new MyListAdapter(array, this, R.layout.productlist, context));
		
	}

	    private class GetAllArticles extends AsyncTask<Connector,Long,JSONArray>
	    {
	        @Override
	        protected JSONArray doInBackground(Connector... params) {
	             return params[0].GetAll("http://c2ez.ma/getArticles.php");
	        }

	        @Override
	        protected void onPostExecute(JSONArray jsonArray) {

	            setListAdapter(jsonArray);
	            for(int i=0; i<jsonArray.length();i++){
	            	try {
						articles.add(jsonArray.getJSONObject(i));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }

	        }
	    }
	    
	    
}
