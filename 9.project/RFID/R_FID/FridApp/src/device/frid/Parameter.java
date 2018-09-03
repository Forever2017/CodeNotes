package device.frid;

public class Parameter {
	public static String open_1="echo off > /sys/class/gpio_switch/gpio_fucn4";
	public static String open_2="echo on >/sys/class/gpio_switch/vbat_en";
	public static String open_3="echo on >/sys/class/gpio_switch/usb_dc_en";
	public static String open_4="echo on >/sys/class/gpio_switch/gpio_fucn2";

	public static String close_1="echo off > /sys/class/gpio_switch/gpio_fucn2";
	public static String close_2="echo off >/sys/class/gpio_switch/usb_dc_en";
	public static String close_3="echo off >/sys/class/gpio_switch/vbat_en";

	/**
	 * 请确认demo中的这个算法是否正确
	 * 
	 * @param rssi_msb
	 * @param rssi_lsb
	 * @return
	 */
	public static double rssi_calculate(char rssi_msb, char rssi_lsb) {
		int temp_rssi = (int) (((int) rssi_msb << 8) + (int) rssi_lsb);
		double sh_rssi = (double) (short) temp_rssi / 10;

		return sh_rssi;
	}
}
