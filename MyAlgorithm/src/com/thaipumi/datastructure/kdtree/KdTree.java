package com.thaipumi.datastructure.kdtree;

import java.util.ArrayList;
import java.util.Arrays;

public class KdTree <T> {
	
	private final int k;
	private KdNode<T> root;
	private KdNodeComparator kdNodeComparator = new KdNodeComparator();
	
	public KdTree(final int k) {
		this.k = k;		
		maxPosValue = new double[k];
		minPosValue = new double[k];
		for (int i = 0 ; i < k ;i++){
			maxPosValue[i] = Double.MAX_VALUE;
			minPosValue[i] = Double.MIN_VALUE;
		}
	}

	public void initTree(ArrayList<T> entities, ArrayList<double[]> positions){
		int entitiesSize = entities.size();
		ArrayList<KdNode<T>> nodes = new ArrayList<KdNode<T>>();
		for (int i = 0 ; i < entitiesSize; i++){
			nodes.add(new KdNode<T>(entities.get(i), positions.get(i)));
		}
		
		root = initNode(nodes, 0);
	}
	
	public KdNode<T> initNode(ArrayList<KdNode<T>> nodes, int splitDimension){
		KdNode<T> node = null;
		
		if (nodes.size() == 1) {
			node = nodes.get(0);
			node.setSplitDimension(splitDimension);
		} else if (nodes.size() > 1) {
			int nextSplitDimension = (splitDimension+1)%k;

			kdNodeComparator.compareDimension = splitDimension;
			node = selectIndexNode(nodes,nodes.size()/2);
			node.setSplitDimension(splitDimension);

			ArrayList<KdNode<T>> lessers = new ArrayList<KdNode<T>>();
			ArrayList<KdNode<T>> greaters = new ArrayList<KdNode<T>>();
			
			KdNode<T> tmpNode;
			
			for (int i = 0 ; i < nodes.size() ; i++){
				tmpNode = nodes.get(i);
				if (node.equals(tmpNode)) continue;
				if (node.isLesser(tmpNode.getPosition())) {
					lessers.add(tmpNode);
				} else {
					greaters.add(tmpNode);
				}
			}
			
			node.lesser = initNode(lessers, nextSplitDimension);
			if (node.lesser != null) node.lesser.parent = node;
			node.greater = initNode(greaters, nextSplitDimension);
			if (node.greater != null) node.greater.parent = node;
		}
		return node;
	}
	
	@SuppressWarnings("unchecked")
	private KdNode<T> selectIndexNode(ArrayList<KdNode<T>> nodes, int lesserNum){
		if (nodes.size() <= 5) {
			KdNode<?> nodesa[] = nodes.toArray(new KdNode[0]);
			Arrays.sort(nodesa,kdNodeComparator );
			return (KdNode<T>) nodesa[lesserNum];
		}
		
		ArrayList<KdNode<T>> lessers = new ArrayList<KdNode<T>>();
		ArrayList<KdNode<T>> greaters = new ArrayList<KdNode<T>>();
		for (int i = 0 ; i < nodes.size(); i++ ) {
			lessers.add(nodes.get(i));
			if (lessers.size() == 5) {
				greaters.add(selectIndexNode(lessers,2));
				lessers.clear();
			}
		}
		greaters.add(selectIndexNode(lessers,lessers.size()/2));
		KdNode<T> node = selectIndexNode(greaters,greaters.size()/2);
		
		lessers.clear();
		greaters.clear();
		KdNode<T> tmpNode;
		
		for (int i = 0 ; i < nodes.size() ; i++){
			tmpNode = nodes.get(i);
			if (node.equals(tmpNode)) continue;
			if (kdNodeComparator.compare(node, tmpNode) < 0) {
				lessers.add(tmpNode);
			} else {
				greaters.add(tmpNode);
			}
		}
		
		if (lessers.size() > lesserNum) {
			return selectIndexNode(lessers,lesserNum);
		} 
		
		if (lessers.size() < lesserNum) {
			return selectIndexNode(greaters,lesserNum - lessers.size());
		}
		
		return node;
	}
	
	public void insert(T entity, double position[]){
		root = insert(root,entity,position, 0);
	}
	
	private KdNode<T> insert(KdNode<T> node, T entity, double position[], int splitDimension){
		if (node == null) { 
			// insert new node here.
			node = new KdNode<T>(entity, position, splitDimension);
		}
		else if (node.getEntity().equals(entity)) { 
			// duplicate data
			return node;
		}
		else if (node.isLesser(position)) { 
			// go to lesser site of tree
			node.lesser = insert(node.lesser,entity,position, (splitDimension+1)%k);
		}
		else { 
			// go to non lesser than site.
			node.greater = insert(node.greater,entity,position, (splitDimension+1)%k);
		}
		return node;
	}
	
	public boolean remove(T entity, double position[]){
		return remove(getNode(entity, position));
	}
	
	private boolean remove(KdNode<T> node){
		if (node == null) return false;
		
		KdNode<T> tmpNode;
		if (node.greater != null) {
			tmpNode = findMinKdNode(node.greater, null, node.getSplitDimension());
		} else {
			tmpNode = findMinKdNode(node.lesser, null, node.getSplitDimension());
			node.greater = node.lesser;
			node.lesser = null;
		}

		remove(tmpNode);
		
		if (node.parent == null) {
			// This node is root;
			root = tmpNode;
		} else {
			KdNode<T> parent = node.parent;
			if (node.equals(parent.greater)) {
				parent.greater = tmpNode;
			} else {
				parent.lesser = tmpNode;
			}
		}
		
		if (tmpNode != null) {
			tmpNode.parent = node.parent;
			tmpNode.lesser = node.lesser;
			tmpNode.greater = node.greater;
		}
		
		return true;
	}
	
	public KdNode<T> findMinKdNode(KdNode<T> node, KdNode<T> minNode, int minDimension){
		if (node == null) return minNode;
		if (minNode == null || minNode.KdCompareTo(node, minDimension) > 0) {
			minNode = node;
		}
		
		minNode = findMinKdNode(node.lesser, minNode, minDimension);
		
		if (node.getSplitDimension() != minDimension) {
			minNode = findMinKdNode(node.greater, minNode, minDimension);
		}
		
		return minNode;
	}
	
	public KdNode<T> getNode(T entity , double position[]) {
		return getNode(root,entity,position);
	}
	
	public KdNode<T> getNode(KdNode<T> node, T entity , double position[]) {
		if (node == null) return null;
		
		if (node.getEntity().equals(entity)) return node;
		
		if (node.isLesser(position)) {
			return getNode(node.lesser,entity,position);
		} 
		
		return getNode(node.greater,entity,position);
	}
	
	// =============== Nearest neighbor finding elements =====================
	
	private double[] minPosValue, maxPosValue, tarketPosition;
	private double minRangeSquare;
	private KdNode<T> nearestNode;
	
	public KdNode<T> findNearestNode(double position[]){
		nearestNode = null;
		minRangeSquare = Double.MAX_VALUE;
		tarketPosition = position;
		findNearestNode(root);
		return nearestNode;
	}
	
	private void findNearestNode( KdNode<T> node ) {
		int compareDimension = node.getSplitDimension();
		double tmpMinPos = minPosValue[compareDimension];
		double tmpMaxPos = maxPosValue[compareDimension];
		
		double disSquare = distanceSquare(tarketPosition, node.getPosition());
		if (disSquare < minRangeSquare) {
			minRangeSquare = disSquare;
			nearestNode = node;
		}
		
		if (node.isLesser(tarketPosition)) {
			if (node.lesser != null) {
				maxPosValue[compareDimension] = node.getPosition()[compareDimension];
				findNearestNode(node.lesser);
				maxPosValue[compareDimension] = tmpMaxPos;
			}
			if (node.greater != null) {
				minPosValue[compareDimension] = node.getPosition()[compareDimension];
				if (isInRange()) {
					findNearestNode(node.greater);
				}
				minPosValue[compareDimension] = tmpMinPos;
			}
		} else {
			if (node.greater != null) {
				minPosValue[compareDimension] = node.getPosition()[compareDimension];
				findNearestNode(node.greater);
				minPosValue[compareDimension] = tmpMinPos;
			}
			if (node.lesser != null) {
				maxPosValue[compareDimension] = node.getPosition()[compareDimension];
				if (isInRange()) {
					findNearestNode(node.lesser);
				}
				maxPosValue[compareDimension] = tmpMaxPos;
			}
		}
		
	}
	
	private double distanceSquare(double p1[] , double p2[]){
		double ans = 0,d;
		for (int i = 0 ; i < k ; i++){
			d = p1[i] - p2[i];
			ans += d*d;
		}
		return ans;
	}
	
	private boolean isInRange() {
		double tmpRangeSquare = 0,d;
		for (int i = 0 ; i < k ; i++){
			if (tarketPosition[i] < minPosValue[i]) {
				d = minPosValue[i] - tarketPosition[i];
				tmpRangeSquare += d*d;
			} else if (tarketPosition[i] > maxPosValue[i]) {
				d = tarketPosition[i] - maxPosValue[i];
				tmpRangeSquare += d*d;
			}
		}
		
		return tmpRangeSquare < minRangeSquare;
	}
}
