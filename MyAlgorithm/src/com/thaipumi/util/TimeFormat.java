package com.thaipumi.util;

public class TimeFormat {
	long days;
	int hours;
	int minutes;
	int seconds;
	int milisecs;
	
	public TimeFormat(Long milisecs) {
		long value[] = new long[1];
		value[0] = milisecs;
		this.milisecs = changeUnit(value,1000)[0];
		this.seconds = changeUnit(value,60)[0];
		this.minutes = changeUnit(value,60)[0];
		this.hours = changeUnit(value,24)[0];
		this.days = value[0];
	}
	
	public int[] changeUnit(long[] values, long unitresizefactor){
		int[] ans = new int[values.length];
		
		for (int i = 0 ; i < values.length ; i++){
			ans[i] = (int) (values[i]%unitresizefactor);
			values[i] /= unitresizefactor;
		}
				
		return ans;
	}
	
	@Override
	public String toString() {
		String ans = "";
		boolean is = false;
		if (is || days != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += getString(days,"day");
		}
		if (is || hours != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += getString(hours,"hour");
		}
		if (is || minutes != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += getString(minutes,"minute");
		}
		if (is || seconds != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += getString(seconds,"second");
		}
		if (is || milisecs != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += getString(milisecs,"milisecond");
		}
		return ans;
	}
	
	private String getString(long num, String unit) {
		String ans = num + " "+unit;
		if (num > 1) ans+="s";
		return ans;
	}
}
