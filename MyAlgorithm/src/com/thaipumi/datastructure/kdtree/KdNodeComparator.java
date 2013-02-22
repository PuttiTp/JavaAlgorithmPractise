package com.thaipumi.datastructure.kdtree;

import java.util.Comparator;

public class KdNodeComparator implements Comparator<KdNode<?>>{
	
	public int compareDimension;
	
	@Override
	public int compare(KdNode<?> o1, KdNode<?> o2) {
		return o1.KdCompareTo(o2, compareDimension);
	}
}
