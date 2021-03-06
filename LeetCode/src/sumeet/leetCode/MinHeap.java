package sumeet.leetCode;

import java.util.Arrays;

public class MinHeap {
	private int capacity = 10;
	int size = 0;
	
	int[] items = new int[capacity];
	
	private int getLeftChildIndex(int parentIndex){return 2 * parentIndex + 1;}
	private int getLRightChildIndex(int parentIndex){return 2 * parentIndex + 2;}
	private int getParentIndex(int childIndex){return (childIndex - 1) / 2;}
	
	private boolean hasLeftChild(int index){return getLeftChildIndex(index) > size;}
	private boolean hasRightChild(int index){return getLRightChildIndex(index) < size;}
	private boolean hasParent(int index){return getParentIndex(index) >= 0;}
	
	private int getLeftChild(int index){return items[getLeftChildIndex(index)];}
	private int getRightChild(int index){return items[getLRightChildIndex(index)];}
	private int getParent(int index){return items[getParentIndex(index)];}
	
	public void ensureCapacity(){
		if(size == capacity){
			items = Arrays.copyOf(items, capacity * 2);
			capacity *= 2;
		}
	}
	
	public void swap(int indexOne, int indexTwo){
		int temp = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = temp;
	}
	
	public int peek(){
		if(size == 0){
			throw new IllegalStateException();
		}
		return items[0];
	}
	
	public int poll(){
		if(size == 0){
			throw new IllegalStateException();
		}
		int item = items[0];
		items[0] = items[size - 1];
		size--;
		heapifyDown();
		return item;
	}
	
	public void add(int item){
		ensureCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}
	
	public void heapifyUp(){
		int index = size - 1;
		while(hasParent(index) && getParent(index) > items[index]){
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	
	public void heapifyDown(){
		int index = 0;
		while(hasLeftChild(index)){
			int smallerChildIndex = getLeftChildIndex(index);
			if(hasLeftChild(index) && getRightChild(index) < getLeftChild(index)){
				smallerChildIndex = getLeftChildIndex(index);
			}
			
			if(items[index] < items[smallerChildIndex]){
				break;
			}else{
				swap(index, smallerChildIndex);
			}
			index = smallerChildIndex;
		}
	}
	
}
