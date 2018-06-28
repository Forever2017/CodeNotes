package 8.开发工具;

public class AndroidStudio相关 {

	private void 运行速度慢() {
		//关闭自动更新
		点击setting》appearance》System setting》updates，去掉图中勾选的相关更新

		//增加运行内存
		方法：到 Android Studio 安装目录，找到 bin/studio(64?).vmoptions
		（文件名可能因操作系统而不同，但大同小异）然后把 -xmx 后面的数字改大一点，比如 2048m 或4096m。
		查看：在 Settings -> Appearance 页里，打开 Show memory indicator 选项，
		然后主界面右下角会显示 Heap 总大小以及使用状况。
		这样操作一下，就可以确认 Heap Size 修改是否生效。
	}


}
