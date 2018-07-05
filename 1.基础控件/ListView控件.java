package 1.基础控件;

public class ListView控件 {
	
	ListView  -  android:divider="@null"  去掉默认的中间间隔的黑线

			ListView  -  android:cacheColorHint="#00000000"  可以item背景常常成黑色改成透明~美观需要

			ListView  -  android:scrollbars="none"与setVerticalScrollBarEnabled(true); 的效果是一样的，不活动的时候隐藏，活动的时候也隐藏滚动条

			ListView  -  android:fastScrollEnabled="true"  Java代码 	myListView.setFastScrollEnabled(true); 辅助滚动条..就右边	个大点的实现快速上下拉 


			adapter.notifyDataSetChanged();// 通知listView刷新数据
}
