package com.example.c2ez;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class Connector {

	    public JSONArray GetAll(String url)
	    {
	    	
	        HttpEntity httpEntity = null;

	        try
	        {
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url);
	            HttpResponse httpResponse = httpClient.execute(httpGet);
	            httpEntity = httpResponse.getEntity();
	            if(httpEntity == null ){
	            	return null;
	            }else{
	            	JSONArray jsonArray = null;

	    	        if (httpEntity != null) {
	    	            try {
	    	                String entityResponse = EntityUtils.toString(httpEntity);
	    	                jsonArray = new JSONArray(entityResponse);
	    	            } catch (JSONException e) {
	    	                e.printStackTrace();
	    	            } catch (IOException e) {
	    	                e.printStackTrace();
	    	            }
	    	        }

	    	        return jsonArray;
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    public String GetImagePath(String url)
	    {
	    	
	        HttpEntity httpEntity = null;

	        try
	        {
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url);
	            HttpResponse httpResponse = httpClient.execute(httpGet);
	            httpEntity = httpResponse.getEntity();
	            if(httpEntity == null ){
	            	return null;
	            }else{
	    	        if (httpEntity != null) {
	    	            try {
	    	                String entityResponse = EntityUtils.toString(httpEntity);
	    	                return entityResponse;
	    	            } catch (IOException e) {
	    	                e.printStackTrace();
	    	            }
	    	        }

	    	        
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}
