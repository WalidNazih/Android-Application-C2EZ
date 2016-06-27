package com.example.c2ez;

import android.graphics.Bitmap;

public class Article {
	
	protected Bitmap image;
	protected String title, price, qte;
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQte() {
		return qte;
	}
	public void setQte(String qte) {
		this.qte = qte;
	}
	
	

}
