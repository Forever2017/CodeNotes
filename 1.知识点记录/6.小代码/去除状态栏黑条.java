package 7.小代码;

public class 去除状态栏黑条 {

	private void 去除状态栏黑条() {

		/*在 styles.xml 中添加的样式*/
		<style name="Hot" parent="@android:style/Theme">
			<item name="android:windowNoTitle">true</item>
			<item name="android:windowContentOverlay">@null</item>
		</style>

		/*在 AndroidManifest.xml中对Activity设置*/
		<activity
			android:name="com.xinyi.ui.MainActivity"
			android:label="@string/app_name"
			android:theme="@style/Hot"  - 这里添加样式就OK了 >
		</activity>


	}
}
