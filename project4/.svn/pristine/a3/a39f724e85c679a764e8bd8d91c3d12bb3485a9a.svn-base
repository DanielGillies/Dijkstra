public class OpenHash {

	int size;
	Node[] hashNodes;
	Node head;
	Node temp;

	public OpenHash(int size) {

		hashNodes = new Node[size];
		this.size = size;
	}

	static long hash(String key, int size) {
		long h = 0;
		int i;

		for (i = 0; i < key.length(); i++) {
			h = (h << 4) + key.charAt(i);
		}

		return h % size;
	}

	public void insert(String[] name) {

		for (int j = 0; j < name.length; j++) {
			int index = (int) hash(name[j], name.length);
			if (hashNodes[index] == null) {
				hashNodes[index] = new Node(j, name[j]);
			} else {
				Node node = hashNodes[index];
				while (node.getNext() != null) {
					node = node.getNext();
				}
				node.setNext(new Node(j, name[j]));
			}
		}
	}

	public int getID(String city, int length) {
		long h = hash(city, length);

		for (Node curr = hashNodes[(int) h]; curr != null; curr = curr.getNext()) {
			if (city.compareTo(curr.getCity()) == 0) {
				return curr.getID();
			}
		}
		return -1;
	}

	private class Node {

		private final int id;
		private final String city;
		private Node next;

		public Node(int cityId, String cityName) {

			id = cityId;
			city = cityName;
			next = null;
		}

		public int getID() {
			return this.id;
		}

		public Node getNext() {
			return this.next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public String getCity() {
			return city;
		}

	}

}