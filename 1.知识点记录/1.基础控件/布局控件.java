package 1.基础控件;

public class 布局控件 {

	private void 通用属性 XML设置() {//属性值为具体的像素值，如30dip，40px
		//离某元素底边缘的距离
		android:layout_marginBottom
		//离某元素左边缘的距离
		android:layout_marginLeft  
		//离某元素右边缘的距离
		android:layout_marginRight
		//离某元素上边缘的距离
		android:layout_marginTop                   
	}

	//相对布局  简介:为某一个组件为参照物，来定位下一个组件的位置的布局方式。
	private void RelativeLayout() {
		/**1、属性值为true或false*/
		//水平居中
		android:layout_centerHrizontal 
		//垂直居中
		android:layout_centerVertical  
		//相对于父元素完全居中
		android:layout_centerInparent   
		//贴紧父元素的下边缘
		android:layout_alignParentBottom    
		//贴紧父元素的左边缘
		android:layout_alignParentLeft             
		// 贴紧父元素的右边缘
		android:layout_alignParentRight        
		//贴紧父元素的上边缘
		android:layout_alignParentTop              
		//如果对应的兄弟元素找不到的话就以父元素做参照物
		android:layout_alignWithParentIfMissing
		
		/**2、属性值必须为id的引用名“@id/id-name”*/
		 在某元素的下方
		android:layout_below                      
		//在某元素的的上方
		android:layout_above                       
		//在某元素的左边
		android:layout_toLeftOf                    
		//在某元素的右边
		android:layout_toRightOf                   
		// 本元素的上边缘和某元素的的上边缘对齐
		android:layout_alignTop                   
		//本元素的左边缘和某元素的的左边缘对齐
		android:layout_alignLeft                   
		// 本元素的下边缘和某元素的的下边缘对齐
		android:layout_alignBottom                
		//本元素的右边缘和某元素的的右边缘对齐
		android:layout_alignRight                  
	}

	//框架布局    简介:放入其中的所有元素都被放置在最左上的区域，
	//而且无法为这些元素指定一个确切的位置,下一个子元素会重叠覆盖上一个子元素
	private void FrameLayout() {

	}

	//线性布局  简介:放主要提供控件水平或者垂直排列的模型，每个子组件
	private void LinearLayout() {
		android:orientation="vertical";   设置布局内控件的排列方式
	}

	//绝对布局  简介:采用坐标轴的方式定位组件，左上角是（0，0）点，往右x轴递增，
	//往下Y轴递增,组件定位属性为android:layout_x和 android:layout_y来确定坐标。
	private void AbsoluteLayout() {
		layout_x：指定控件的x坐标
		layout_y：指定控件的x坐标
	}

	//表格布局  简介:类似Html里的Table.使用TableRow来布局，其中TableRow代表一行，
	//TableRow的每一个视图组件代表一个单元格。
	private void TableLayout() {

	}

}
