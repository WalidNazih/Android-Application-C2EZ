package com.example.c2ez;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Soudage extends Activity {
	
	protected ListView articleList;
	protected Context context;
	protected JSONArray articles;
	protected ProgressBar spin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soudage);
		spin = (ProgressBar) findViewById(R.id.spin);
		articleList = (ListView) findViewById(R.id.acList);
		articles = new JSONArray();
		new GetAllArticles().execute(new Connector());
		context = this;
		articleList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, ProductInfo.class);
				ImageView image = (ImageView) arg1.findViewById(R.id.image);
				TextView title = (TextView) arg1.findViewById(R.id.title);
				i.putExtra("imageUrl", image.getTag().toString());
				i.putExtra("title", title.getText());
				try {
					i.putExtra("price", articles.getJSONObject(arg2).getString("price"));
					i.putExtra("quantity", articles.getJSONObject(arg2).getString("quantity"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startActivity(i);
			}
		});
	}
	
	
	public void setListAdapter(JSONArray array){
		
		this.articleList.setAdapter(new MyListAdapter(array, this, R.layout.productlist, context));
		
	}

	    private class GetAllArticles extends AsyncTask<Connector,Long,JSONArray>
	    {
	    	
	        @Override
			protected void onPreExecute() {
	        	spin.setVisibility(View.VISIBLE);
			}

			@Override
	        protected JSONArray doInBackground(Connector... params) {
	             JSONArray json = params[0].GetAll("http://c2ez.ma/getArticles.php");
	             for(int i=0; i<json.length();i++){
		            	try {
		            		if((json.getJSONObject(i).getString("categories").contains("PRODUITS CIF ET DIVERS") || 
		            		   json.getJSONObject(i).getString("categories").contains("INSOLEUSES ET PIECES DETACHEES") ||
		            		   json.getJSONObject(i).getString("categories").contains("PLAQUES DE CIRCUIT IMPRIME CIF") ||
		            		   json.getJSONObject(i).getString("categories").contains("PLAQUES CIRCUIT IMPRIME BUNGARD") ||
		            		   json.getJSONObject(i).getString("categories").contains("FERS A SOUDER ELECTRIQUES")) && !json.getJSONObject(i).getString("name").contains("null")){
		            			articles.put(json.getJSONObject(i));
		            		}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		         }
	             return articles;
	        }

	        @Override
	        protected void onPostExecute(JSONArray jsonArray) {
	        	spin.setVisibility(View.GONE);
	            setListAdapter(jsonArray);

	        }
	    }
	    
}
