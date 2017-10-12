package structures;

public class Heap {
	private final String[] heap;

	public Heap(String[] args) {
		heap = args.clone();
	}

	private void swap(int i, int j) {
		String t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	private void pullUp(int k) {
		while (k - 1 > 0 && heap[k - 1].compareTo(heap[(k - 2) / 2]) > 0) {
			swap(k - 1, (k - 2) / 2);
			k = (k - 2) / 2 + 1;
		}
	}

	public void buildHeap() {
		int length = heap.length;
		for (int i = 2; i <= length; i++)
			pullUp(i);
	}

	private void pullDown(int i, int k) {
		int t = i;
		if (2 * i + 1 < k && heap[t].compareTo(heap[2 * i + 1]) < 0)
			t = 2 * i + 1;
		if (2 * i + 2 < k && heap[t].compareTo(heap[2 * i + 2]) < 0)
			t = 2 * i + 2;
		if (i != t) {
			swap(i, t);
			pullDown(t, k);
		}
	}

	private void sortHeap() {
		int length = heap.length;
		for (int i = 0; i < length - 1; i++) {
			swap(0, length - 1 - i);
			pullDown(0, length - i - 1);
		}
	}

	public String[] sort() {
		this.buildHeap();
		this.sortHeap();
		return heap.clone();
	}
}
