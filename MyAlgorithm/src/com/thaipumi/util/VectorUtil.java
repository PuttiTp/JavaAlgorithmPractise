package com.thaipumi.util;

public class VectorUtil {
	public static double normalize(double v[]) {
		double vsize = size(v);
		if (vsize == 0 || vsize == 1) return vsize;
		for (int i = 0 ; i < v.length ; i++){
			v[i] /= vsize;
		}
		
		return vsize;
	}
	public static double size(double v[]) {
		double size = 0;
		for (int i = 0; i < v.length ; i++) {
			size += v[i]*v[i];
		}
		
		return Math.sqrt(size);
	}

}
