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

public class Composants extends Activity {

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
		            		if((json.getJSONObject(i).getString("categories").contains("COMPOSANTS") || 
				            		   json.getJSONObject(i).getString("categories").contains("COMPOSANTS PASSIFS") ||
				            		   json.getJSONObject(i).getString("categories").contains("RESISTANCES FIXES") ||
				            		   json.getJSONObject(i).getString("categories").contains("CARBONE 1/4W") ||
				            		   json.getJSONObject(i).getString("categories").contains("MODULE DE PUISSANCE") ||
				            		   json.getJSONObject(i).getString("categories").contains("COUCHE METAL 1/2W TYPE SFR25") ||
				            		   json.getJSONObject(i).getString("categories").contains("CTN - CTP - CAPTEURS PASSIFS") ||
				            		   json.getJSONObject(i).getString("categories").contains("THERMISTANCES CTN") ||
				            		   json.getJSONObject(i).getString("categories").contains("PHOTO RESISTANCES LDR") ||
				            		   json.getJSONObject(i).getString("categories").contains("CAPTEUR HUMIDITE GAZ TEMPERATURE") ||
				            		   json.getJSONObject(i).getString("categories").contains("DETECTEURS CHOC ET INCLINAISON") || 
				            		   json.getJSONObject(i).getString("categories").contains("CAPTEUR DE COURANT EFFET HALL") ||
				            		   json.getJSONObject(i).getString("categories").contains("POT. AJUSTABLES MONOTOUR") ||
				            		   json.getJSONObject(i).getString("categories").contains("POT. AJUSTABLES MULTITOURS") ||
				            		   json.getJSONObject(i).getString("categories").contains("POTENTIOMETRES DE TABLEAU") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS NON POL.RADIAUX") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS NON POL.AXIAUX") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS NON POL CMS") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS CHIMIQUES-TANTALE") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS POLARISES CMS") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS DE DEMARRAGE") ||
				            		   json.getJSONObject(i).getString("categories").contains("CONDENSATEURS AJUSTABLES") ||
				            		   json.getJSONObject(i).getString("categories").contains("QUARTZ-FILTRES-RESONATEURS-TCXO") ||
				            		   json.getJSONObject(i).getString("categories").contains("COMPOSANTS ACTIFS") ||
				            		   json.getJSONObject(i).getString("categories").contains("1N") ||
				            		   json.getJSONObject(i).getString("categories").contains("BA157 à BYX98-600") ||
				            		   json.getJSONObject(i).getString("categories").contains("REGULATEURS") ||
				            		   json.getJSONObject(i).getString("categories").contains("REGULATEURS FIXES") ||
				            		   json.getJSONObject(i).getString("categories").contains("Triac") ||
				            		   json.getJSONObject(i).getString("categories").contains("Thyristor") ||
				            		   json.getJSONObject(i).getString("categories").contains("CI LOGIQUES") ||
				            		   json.getJSONObject(i).getString("categories").contains("CI DIVERS") ||
				            		   json.getJSONObject(i).getString("categories").contains("MICRO-MEMOIRES") ||
				            		   json.getJSONObject(i).getString("categories").contains("DEVELOPPEMENT ET PROGRAMMATION") ||
				            		   json.getJSONObject(i).getString("categories").contains("COMPOSANTS-MODULES HF") ||
				            		   json.getJSONObject(i).getString("categories").contains("TRANSISTORS")) && !json.getJSONObject(i).getString("name").contains("null")){
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