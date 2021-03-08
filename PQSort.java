import java.util.PriorityQueue;

public class PQSort {
	
	private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }
	
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
	
	public static <E extends Comparable <? super E>> void heapSort(E[]a) {
		PriorityQueue<E> heap = new PriorityQueue<E>();
		
		for(int i=0;i<a.length;i++) {
			heap.add(a[i]);
		}
		
		int n = heap.size();
		
		// heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(a, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(a, 1, k--);
            sink(a, 1, k);
        }
	}
	
	public static <E extends Comparable <? super E>> void heapSort2011(E[]a) {
		PriorityQueue2011<E> heap = new PriorityQueue2011<E>();		
		
		for(int i=0;i<a.length;i++) {
			heap.add(a[i]);
		}
		
		int n = heap.size();
		
		// heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(a, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(a, 1, k--);
            sink(a, 1, k);
        }
	}
}
