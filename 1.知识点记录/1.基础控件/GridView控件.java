package 1.基础控件;

public class GridView控件 {

	private void 属性 XML中设置() {
		//GridView的列数设置为自动
		android:numColumns="auto_fit"  

		//每列的宽度，也就是Item的宽度
		android:columnWidth="90dp " 

		//缩放与列宽大小同步
		android:stretchMode="columnWidth"

		//两行之间的边距
		android:verticalSpacing="10dp"  

		//两列之间的边距 
		android:horizontalSpacing="10dp"  

		//去除拖动时默认的黑色背景
		android:cacheColorHint="#00000000" 

		//去除选中时的黄色底色
		android:listSelector="#00000000"        

		//隐藏GridView的滚动条
		android:scrollbars="none"                   

		//设置为true就可以实现滚动条的自动隐藏和显示
		android:fadeScrollbars="true"             

		//GridView出现快速滚动的按钮(至少滚动4页才会显示)
		android:fastScrollEnabled="true"      

		//GridView衰落(褪去)边缘颜色为空，缺省值是vertical。(可以理解为上下边缘的提示色)
		android:fadingEdge="none"                

		//定义的衰落(褪去)边缘的长度
		android:fadingEdgeLength="10dip"   

		//设置为true时，你做好的列表就会显示你列表的最下面
		android:stackFromBottom="true"       

		//当你动态添加数据时，列表将自动往下滚动最新的条目可以自动滚动到可视范围内
		android:transcriptMode="alwaysScroll" 

		//点击某条记录不放，颜色会在记录的后面成为背景色,内容的文字可见(缺省为false)
		android:drawSelectorOnTop="false"  
	}


}
