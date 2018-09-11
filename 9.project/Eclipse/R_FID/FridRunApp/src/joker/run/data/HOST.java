package joker.run.data;

public class HOST {
	public static String Token = "D71DBD9608E94C4C8FFC5C99E4146DA1";
	public static String DeviceCode = "TC001";

	/** 管理员ID （包含此ID，脱离外部跑者限制）*/
	public static final String ADMIN = "9527";
	/** 扫码延迟时间，时间内读取到不做处理*/
	public static final int DELAY = 10;
	/** 同时出发，延迟几秒开始扫码*/
	public static final int MEA_DELAY = 30;
	

	/** 计时模式 0：同时出发  1：分别出发*/
	public static int RUN_TYPE = 0;
	/** 单圈距离*/
	public static String RUN_DIS = "";
	/** 允许外部跑者  0：不允许  1：允许*/
	public static int PEO_IS = 0;

}
