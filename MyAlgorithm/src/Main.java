import com.thaipumi.datastructure.kdtree.KdTree;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello world");
		double signals[][] = new double[1000000][2];
		KdTree<double[]> kdTree = new KdTree<double[]>(signals[0].length);
		for (int i = 0 ;i < signals.length ;i++){
			for (int j = 0 ; j< signals[i].length; j++){
				signals[i][j] = Math.random()*100;
			}
			kdTree.insert(signals[i], signals[i]);
		}
		int clearcount = 0;
		double test[][] = new double[1000][signals[0].length];
		for (int i = 0 ; i < test.length ; i++){
			for (int j = 0 ; j< test[i].length; j++){
				test[i][j] = Math.random()*100;
			}
		}
		
		long time1 = System.currentTimeMillis();
		for (int i = 0 ; i < test.length ; i++){
			double ans1[] = kdTree.findNearestNode(test[i]).getEntity();
		}
		time1 = System.currentTimeMillis() - time1;


		long time2 = System.currentTimeMillis();
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
		}
		time2 = System.currentTimeMillis() - time2;

		System.out.println(String.format("END[%d,%d,%d]",time2 ,time1 , time2-time1));
	}
	
	public static double distanceSquare(double a[], double b[]) {
		double ans = 0;
		for (int i = 0; i < a.length ; i++){
			ans += Math.pow(a[i]-b[i], 2);
		}
		return ans;
	}

}
