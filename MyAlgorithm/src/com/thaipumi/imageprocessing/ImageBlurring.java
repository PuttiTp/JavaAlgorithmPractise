package com.thaipumi.imageprocessing;

public class ImageBlurring {

	public static double[][] generateGaussianKernel(int size, double sigma){
		if (size % 2 == 0) {
			size--;
		}
		if (size < 3) size = 3;
		
		double[][] kernel = new double[size][size];
		
		int mid = size / 2;
		
		double twoSSth = 1/((2*sigma*sigma));
		double twoPSSth = 1/((2*Math.PI*sigma*sigma));
		
		for (int x = 0 ; x < size ; x++){
			for (int y = 0 ; y < size ; y++){
				int xx = x - mid;
				int yy = y - mid;
				kernel[y][x] = Math.pow(Math.E, -(xx*xx+yy*yy)*twoSSth)*twoPSSth;
			}
		}
		
		
		return kernel;
	}
	
	public static void main(String args[]){
		double [][] kernel = generateGaussianKernel(7, 0.84089642);
		
		for (int i = 0; i < kernel.length; i++) {
			for (int j = 0; j < kernel[0].length; j++) {
				System.out.print(String.format("%.8f ", kernel[i][j]));
			}
			System.out.println();
		}
	}

}
