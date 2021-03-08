import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue2011<E extends Comparable <? super E>> extends AbstractQueue<E> {
	private Comparable[] heap;
	private int size;
	
	public PriorityQueue2011() {
		heap = new Comparable[1];
		size = 0;
	}
	
	protected void swap(int i, int j) {
		Comparable temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	private void resize(int capacity) {
		Comparable[] temp = new Comparable[capacity];
		for(int i=1; i<=size; i++) {
			temp[i] = heap[i];
		}
		heap = temp;
	}
	
	@Override
	public boolean offer(E e){
		if(size == heap.length-1)
			resize(2*heap.length);
		heap[++size] = e;
		swim(size);
		return true;
	}
	
	public boolean add(E e) {
		if(size == heap.length-1)
			resize(2*heap.length);
		heap[++size] = e;
		swim(size);
		return true;
	}
	
	private void swim(int k) {
        while (k > 1 && heap[k/2].compareTo(heap[k])>0) {
        	swap(k,k/2);
        	k = k/2;
        }
    }
	
	protected void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && heap[j].compareTo(heap[j+1])>0) j++;
            if (heap[k].compareTo(heap[j])<=0) break;
            swap(k,j);
            k = j;            
        }
    }
		
	@Override
	public E poll() {
		if(size == 0)
			return null;
		
		Comparable max = heap[1];
		
		swap(1,size);
		size--;
		
		sink(1);
		heap[size+1] = null;
		
		if((size>0) && (size == (heap.length-1)/4))
			resize(heap.length/2);
		return (E) max;
	}
	
	public E remove() {
		if(size == 0)
			throw new NoSuchElementException();
		
		Comparable max = heap[1];
		
		swap(1,size);
		size--;
		
		sink(1);
		heap[size+1] = null;
		
		if((size>0) && (size == (heap.length-1)/4))
			resize(heap.length/2);
		return (E) max;
	}

	@Override
	public E peek() {
		if(size == 0)
			return null;
		
		return (E) heap[1];
	}
	
	public E Element() {
		if(size == 0)
			throw new NoSuchElementException();
		
		return (E) heap[1];
	}	

	@Override
	public int size() {
		return size;
	}
	
	public String toString() {
		String s = "[" + heap[0];
		for(int i=1;i<=size;i++) {
			s+=", " + heap[i];
		}
		s+= "]";
		return s;
	}
	
	public String toTree() {
		String s = "";
		int h = height();
		for(int i=0; i<h; i++) {
			s+= "\n";
			s+= printEmpty((int) Math.pow(2, h - 1 - i) - 1);
			s+= printElements(i);
		}
		
		return s;
	}
	
	private String printEmpty(int x) {
		String s = "";
		for(int i=0; i<x; i++) {
			s+="  ";
		}
		return s;
	}
	
	private String printElements(int level) {
		String s = "";
		int h = height();		
		int empties = (int) Math.pow(2, h - level) - 1;		
		int first = (int) Math.pow(2, level);
		int n = size - first;
		
		int i = first;
		while(n>=0 && i<first*2) {
			s+= heap[i];
			s+= printEmpty(empties);
			i++;
			n--;
		}
		
		return s;
	}
	
	private int height() {
		int count = 1;
		int n = size;
		while(n/2 > 0) {
			n/=2;
			count++;
		}
		return count;
	}
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}

}
