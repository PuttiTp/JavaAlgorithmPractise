import java.util.ArrayList;

import com.thaipumi.datastructure.kdtree.KdTree;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello world");
		double signals[][] = new double[1000][2];
		KdTree<double[]> kdTree = new KdTree<double[]>(signals[0].length);
		ArrayList<double[]> toKdTree = new ArrayList<double[]>();
		for (int i = 0 ;i < signals.length ;i++){
			for (int j = 0 ; j< signals[i].length; j++){
				signals[i][j] = Math.random()*100-200;
			}
			toKdTree.add(signals[i]);
		}

		kdTree.initTree(toKdTree, toKdTree);
		
		int clearcount = 0;
		double test[][] = new double[1000][signals[0].length];
		for (int i = 0 ; i < test.length ; i++){
			for (int j = 0 ; j< test[i].length; j++){
				test[i][j] = Math.random()*100-200;
			}
		}
		
		long time1 = System.currentTimeMillis();
		double ans1[][] = new double[test.length][];
		for (int i = 0 ; i < test.length ; i++){
			ans1[i] = kdTree.findNearestEntity(test[i]);
		}
		time1 = System.currentTimeMillis() - time1;


		long time2 = System.currentTimeMillis();
		double ans2[][] = new double[test.length][];
		for (int i = 0 ; i < test.length ; i++){
			int min = 0;
			double minDisSquare = distanceSquare(test[i], signals[0]);
			for (int j = 1 ; j < signals.length ; j++){
				double disSquare = distanceSquare(test[i], signals[j]);
				if (disSquare < minDisSquare) {
					minDisSquare = disSquare;
					min = j;
				}
			}
			ans2[i] = signals[min];
		}
		time2 = System.currentTimeMillis() - time2;

		System.out.println(String.format("END[%d,%d,%d]",time2 ,time1 , time2-time1));
		
		int foundCount = 0;
		for (int i = 0 ; i < signals.length; i++) {
			if (kdTree.getNode(signals[i], signals[i]) == null) {
				System.err.println("เหี้ย"); break;
			} else {
				foundCount++;
			}
		}
		System.out.println("foundCount = "+foundCount);
		
		for (int i = 0 ; i < ans1.length ; i++){
			if (!isEquals(ans1[i], ans2[i])) {
				System.out.println("Not Equals!!!");
				System.out.print("("+test[i][0]);
				for (int j = 1 ; j < test[i].length ; j++){
					System.out.print(","+test[i][j]);
				}
				System.out.println(")");
				System.out.print("("+ans1[i][0]);
				for (int j = 1 ; j < ans1[i].length ; j++){
					System.out.print(","+ans1[i][j]);
				}
				System.out.println(")");
				System.out.print("("+ans2[i][0]);
				for (int j = 1 ; j < ans2[i].length ; j++){
					System.out.print(","+ans2[i][j]);
				}
				System.out.println(")");
				System.exit(0);
			}
		}
		System.out.println("No error");
	}
	
	public static boolean isEquals(double a[], double b[]) {
		for (int i = 0 ; i < a.length ; i++){
			if (a[i] != b[i]) return false;
		}
		return true;
	}
	
	public static double distanceSquare(double a[], double b[]) {
		double ans = 0;
		for (int i = 0; i < a.length ; i++){
			ans += Math.pow(a[i]-b[i], 2);
		}
		return ans;
	}

}
