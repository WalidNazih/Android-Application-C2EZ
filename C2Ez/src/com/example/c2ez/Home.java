package com.example.c2ez;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends Activity {

	ViewPager viewPager;
	CustomSwipeAdapter custom;
	ImageView accueil, soudage, cc, cr, composants, opto;
	TextView address;
	EditText term;
	Button recherche;

	protected ListView articleList;
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		custom = new CustomSwipeAdapter(this);

		viewPager.setAdapter(custom);

		accueil = (ImageView) findViewById(R.id.accueil);
		soudage = (ImageView) findViewById(R.id.soudage);
		cc = (ImageView) findViewById(R.id.cc);
		cr = (ImageView) findViewById(R.id.cr);
		composants = (ImageView) findViewById(R.id.composants);
		opto = (ImageView) findViewById(R.id.opto);
		address = (TextView) findViewById(R.id.address);
		recherche = (Button) findViewById(R.id.search);
		term = (EditText) findViewById(R.id.term);
		
		recherche.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Search.class);
				i.putExtra("term", term.getText().toString());
				startActivity(i);
			}
		});
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
		address.setTypeface(custom_font);

		accueil.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Accueil.class);
				startActivity(i);
			}
		});
		
		soudage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Soudage.class);
				startActivity(i);
			}
		});
		
		cc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ConnecteursCordons.class);
				startActivity(i);
			}
		});
		
		cr.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Commutateurs.class);
				startActivity(i);
			}
		});
		
		composants.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Composants.class);
				startActivity(i);
			}
		});
		
		opto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), OptoSignalisation.class);
				startActivity(i);
			}
		});
	}

	

}
