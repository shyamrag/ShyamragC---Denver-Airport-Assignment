package com.denver.airport.conveyor.route;

/**
*
* @author shyamrag
*/
public class PriorityQueue {

	private Gate[] array;
	public int count;
	public int capacity;
	
	public PriorityQueue(int capacity)
	{
		this.count = 0;
		this.capacity = capacity;
		this.array = new Gate[capacity];
	}
	
	public boolean isEmpty()
	{
		return count == 0;
	}
	
	public int getMinimumTimeIndex()
	{
		if(this.count == 0)
			return -1;
		
		return this.array[0].getIndex();
	}
	
	public void insert (int index, int timeTakenFromSource)
	{
		if(count == capacity)
			resizePriorityQueue();
		count++;
		
		int nodeIndex = count - 1;
		
		while(nodeIndex!=(nodeIndex-1)/2 && nodeIndex >= 0 && array[(nodeIndex-1)/2]!=null && (timeTakenFromSource > array[(nodeIndex-1)/2].timeTakenFromSource))
		{
			this.array[nodeIndex] = array[(nodeIndex-1)/2];
			nodeIndex = (nodeIndex-1)/2;
		}
		
		array[nodeIndex] = new Gate(index,timeTakenFromSource);
	}
	
	public void update(int index, int newTimetakenFromSource)
	{
		if(index < 0 || index > count)
			return;
		Gate node = array[index];
		node.setTime(newTimetakenFromSource);
		heapify(index);
	}
	
	public int removeMin()
	{
		if(this.count == 0)
			return -1;
		
		int index = this.array[0].getIndex();
		array[0] = array[count -1];
		count--;
		heapify(0);
		return index;
	}

	private void heapify(int i) {
		int min = i;
		
		int leftChild = getLeftChildIndex(i);
		int rightChild = getRightChildIndex(i);
		
		if(leftChild!=-1 && array[i].getTimeTaken() > array[leftChild].getTimeTaken())
		{
			min = leftChild;
		}
		
		if(rightChild !=-1 && array[min].getTimeTaken() >  array[rightChild].getTimeTaken())
		{
			min = rightChild;
		}
		
		if(min !=i)
		{
			Gate temp = array[i];
			array[i] = array[min];
			array[min]= temp;
			heapify(min);
		}
	}


	private int getLeftChildIndex(int i) {
		int left = 2*i + 1;
		if(left >= count )
			return -1;
		return left;
	}
	
	private int getRightChildIndex(int i) {
		int right = 2*i + 2;
		if(right >= count)
			return -1;
		return right;
			
	}

	private void resizePriorityQueue() {
		Gate tempArr[] = new Gate[capacity];
		System.arraycopy(array, 0, tempArr, 0, count);
		
		capacity = 2*capacity;
		array = new Gate[capacity];
		
		if(array == null)
		{
			System.out.println("Memory Error");
			return;
		}
		
		for(int i=0;i<count;i++)
			array[i] = tempArr[i];
		
		tempArr = null;
	}
	
	
	class Gate{
		
		int index;
		int timeTakenFromSource;
		
		public Gate(int index,int timeTakenFromSource)
		{
			this.index = index;
			this.timeTakenFromSource = timeTakenFromSource;
		}
		
		public void setIndex(int newIndex)
		{
			index = newIndex;
		}
		
		public void setTime(int newTime)
		{
			timeTakenFromSource = newTime;
		}
		public int getIndex()
		{
			return index;
		}
		
		public int getTimeTaken()
		{
			return timeTakenFromSource;
		}
	}
}
