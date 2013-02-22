package com.thaipumi.datastructure.kdtree;

public class KdNode <T> {
	private T entity;
	private double[] position;
	private int splitDimension;
	
	public KdNode<T> parent;
	public KdNode<T> lesser;
	public KdNode<T> greater;
	
	public KdNode(T entity, double[] position, int splitDimension){
		this.entity = entity;
		this.position = position;
		this.splitDimension = splitDimension;
	}
	
	public KdNode(T entity, double[] position){
		this.entity = entity;
		this.position = position;
		this.splitDimension = -1;
	}
	
	public boolean isLesser(double[] position){
		return position[splitDimension] < this.position[splitDimension];
	}
	
	public int KdCompareTo(final KdNode<?> o2, final int k) {
		if (this.position[k] > o2.position[k]) return 1;
		if (this.position[k] < o2.position[k]) return -1;
		return 0;
	}
	
	public boolean equals(KdNode<T> that) {
		if (that == null) return false;
		return this.entity.equals(that.entity);
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public int getSplitDimension() {
		return splitDimension;
	}

	public void setSplitDimension(int splitDimension) {
		this.splitDimension = splitDimension;
	}
}
