package 1.基础控件;

public class Activity控件 {

	private void Activity跳转() {
		// 1、普通跳转
		Intent intent = new Intent(Activity1.this,Activity2.class);
		startActivity(intent);									
		finish();

		// 2、跳转传值  跳转传递 “你们好”
		Intent intent = new Intent(Activity1.this,Activity2.class);
		intent.putExtra("data", "你们好");
		startActivity(intent);
		//接收
		Intent intent = getIntent();
		String data = intent.getStringExtra("data").toString().trim();

		// 3、跳转传值（List）
		Intent intent=new Intent();
		intent.setClass(Activity1.this,Activity2.class);
		Bundle bundle=new Bundle();
		bundle.putParcelableArrayList("list", (ArrayList)a);
		intent.putExtras(bundle);
		startActivity(intent);
		//接收
		Bundle bundle=this.getIntent().getExtras();
		ArrayList list2 = bundle.getParcelableArrayList("list");
		textView1.setText(list2.get(0).toString());
	}

	
}
