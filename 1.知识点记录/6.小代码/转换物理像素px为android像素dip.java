package com.ui;

public class 转换物理像素px为android像素dip {
	
	//转换物理像素px为android像素dip(自适应)
	public int dip2px( float dipValue){   
		final float scale = this.getResources().getDisplayMetrics().density;   
		return (int)(dipValue * scale + 0.5f);   
	} 
}
