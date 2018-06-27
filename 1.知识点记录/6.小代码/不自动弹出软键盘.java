package 7.小代码;

public class 不自动弹出软键盘 {
	
	进入新 Activity界面，想阻止软键盘自动弹出，只要在 AndroidManifest.xml 中对应的Activity下设置 
	
	android:windowSoftInputMode="adjustUnspecified|stateHidden"  
	
}
