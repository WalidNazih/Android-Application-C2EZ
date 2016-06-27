package com.example.c2ez;

import java.math.BigDecimal;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInfo extends Activity {
	
	protected ImageView image;
	protected TextView name, prix, quantite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_info);
		
		String imageUrl = getIntent().getStringExtra("imageUrl");
		String title = getIntent().getStringExtra("title");
		String price = getIntent().getStringExtra("price");
		String qte = getIntent().getStringExtra("quantity");
		
		image = (ImageView) findViewById(R.id.imageView1);
		name = (TextView) findViewById(R.id.name);
		prix = (TextView) findViewById(R.id.price);
		quantite = (TextView) findViewById(R.id.qte);
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
		name.setTypeface(custom_font);
		prix.setTypeface(custom_font);
		quantite.setTypeface(custom_font);
		
		name.setText(title);
		prix.setText("Prix : "+ new BigDecimal(price)
			    .setScale(3, BigDecimal.ROUND_HALF_UP)
			    .doubleValue()+" DHs");
		quantite.setText("Quantite : "+qte);
		
		Picasso.with(this).cancelRequest(image); 
		Picasso.with(this).load(imageUrl).tag(this).into(image);
	}
}
