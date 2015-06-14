public class PriorityHeap {
	private HeapNode[] Heap;
	private int maxsize;
	public int size;

	public PriorityHeap(int max) {
		maxsize = max;
		Heap = new HeapNode[maxsize];
		size = 0;
		Heap[0] = new HeapNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	private int leftchild(int pos) {
		return 2 * pos;
	}

	private int rightchild(int pos) {
		return 2 * pos + 1;
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private boolean isleaf(int pos) {
		return ((pos > size / 2) && (pos <= size));
	}

	private void swap(int pos1, int pos2) {
		HeapNode tmp;

		tmp = Heap[pos1];
		Heap[pos1] = Heap[pos2];
		Heap[pos2] = tmp;
	}

	public void insert(int elem, int priority) {
		size++;
		HeapNode node = new HeapNode(elem, priority);
		Heap[size] = node;
		int current = size;

		while (Heap[current].priority < Heap[parent(current)].priority) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		int i;
		for (i = 1; i <= size; i++)
			System.out.print(Heap[i] + " ");
		System.out.println();
	}

	public int removeMin() {
		swap(1, size);
		size--;
		if (size != 0)
			pushdown(1);
		return Heap[size + 1].elem;
	}

	private void pushdown(int position) {
		int smallestchild;
		while (!isleaf(position)) {
			smallestchild = leftchild(position);
			if ((smallestchild < size)
					&& (Heap[smallestchild].priority > Heap[smallestchild + 1].priority))
				smallestchild = smallestchild + 1;
			if (Heap[position].priority <= Heap[smallestchild].priority)
				return;
			swap(position, smallestchild);
			position = smallestchild;
		}
	}

	public void reduceKey(int elem, int priority) {
		for (int i = 1; i < this.size; i++) {
			if (Heap[i].elem == elem) {
				Heap[i].priority = priority;
				pushdown(1);
				break;
			}
		}
	}

	public boolean contains(int elem) {
		for (int i = 1; i < this.size; i++) {
			if (Heap[i].elem == elem) {
				return true;
			}
		}
		return false;
	}

	public class HeapNode {
		int elem;
		int priority;

		HeapNode(int elem, int priority) {
			this.elem = elem;
			this.priority = priority;
		}
	}

}
