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
	
	public static int[] changeUnit(long[] values, long unitresizefactor){
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
			ans += days+"d";
		}
		if (is || hours != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += hours+"h";
		}
		if (is || minutes != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += minutes+"m";
		}
		if (is || seconds != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += seconds+"s";
		}
		if (is || milisecs != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += milisecs+"ms";
		}
		return ans;
	}
	
	public static String getString(Long milisecs){
		long value[] = new long[1];
		value[0] = milisecs;
		int milisec = changeUnit(value,1000)[0];
		int seconds = changeUnit(value,60)[0];
		int minutes = changeUnit(value,60)[0];
		int hours = changeUnit(value,24)[0];
		long days = value[0];
		
		String ans = "";
		boolean is = false;
		if (is || days != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += days+"d";
		}
		if (is || hours != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += hours+"h";
		}
		if (is || minutes != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += minutes+"m";
		}
		if (is || seconds != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += seconds+"s";
		}
		if (is || milisec != 0) {
			if (is){
				ans += " ";
			} else {
				is = true;
			}
			ans += milisec+"ms";
		}
		return ans;
	}

}
