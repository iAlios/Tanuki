package com.alio.structure;

import java.util.ArrayList;
import java.util.List;

public class Tree<K, W> {

	private List<Node<K, W>> mNodeList;

	public static class Pair<K, W> {

		K k;

		W w;

		public Pair(K k, W w) {
			super();
			this.k = k;
			this.w = w;
		}

		public K getK() {
			return k;
		}

		public void setK(K k) {
			this.k = k;
		}

		public W getW() {
			return w;
		}

		public void setW(W w) {
			this.w = w;
		}

	}

	@SuppressWarnings("hiding")
	private class Node<K, W> {
		K k;
		W w;

		private List<Node<K, W>> mSubNodes;

		private Node(K k) {
			super();
			this.k = k;
			mSubNodes = new ArrayList<>();
		}

		private Node(K k, W w) {
			super();
			this.k = k;
			this.w = w;
			mSubNodes = new ArrayList<>();
		}

		public Node<K, W> getSubNode(List<K> kList, boolean needNewNode) {
			K k = kList.remove(0);
			Node<K, W> kNode = null;
			Node<K, W> temp = new Node<K, W>(k);
			if (mSubNodes.contains(temp)) {
				kNode = mSubNodes.get(mSubNodes.indexOf(temp));
			} else {
				if (needNewNode) {
					kNode = new Node<K, W>(k);
					mSubNodes.add(kNode);
				} else {
					return null;
				}
			}
			if (kList.size() > 0) {
				return kNode.getSubNode(kList, needNewNode);
			} else {
				return kNode;
			}
		}

		public List<Pair<List<K>, W>> dump(List<K> superList) {
			List<Pair<List<K>, W>> result = new ArrayList<>();
			if (w != null) {
				result.add(new Pair<>(superList, w));
			} else {
				List<K> superList1 = null;
				for (Node<K, W> node : mSubNodes) {
					superList1 = new ArrayList<K>();
					superList1.addAll(superList);
					superList1.add(node.getKey());
					result.addAll(node.dump(superList1));
				}
			}
			return result;
		}

		public void setWeight(W w) {
			this.w = w;
		}

		public W getWeight() {
			return this.w;
		}

		public K getKey() {
			return this.k;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this.k == null && obj == null) {
				return true;
			} else if (this.k == null && obj != null || obj == null && this.k != null) {
				return false;
			} else if (obj instanceof Node) {
				return this.k.equals(((Node<K, W>) obj).k);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return this.k.hashCode();
		}

	}

	public Tree() {
		super();
		mNodeList = new ArrayList<>();
	}

	private Node<K, W> getNode(List<K> kList, boolean needNewNode) {
		K k = kList.remove(0);
		Node<K, W> kNode = null;
		Node<K, W> temp = new Node<K, W>(k);
		if (mNodeList.contains(temp)) {
			kNode = mNodeList.get(mNodeList.indexOf(temp));
		} else {
			if (needNewNode) {
				kNode = new Node<K, W>(k);
				mNodeList.add(kNode);
			} else {
				return null;
			}
		}
		if (kList.size() > 0) {
			return kNode.getSubNode(kList, needNewNode);
		} else {
			return kNode;
		}
	}

	public void append(List<K> kList, W w) {
		getNode(kList, true).setWeight(w);
	}

	public W getNodeWeight(List<K> kList) {
		Node<K, W> result = getNode(kList, false);
		return result == null ? null : result.getWeight();
	}

	public boolean isLeaf(List<K> kList) {
		Node<K, W> result = getNode(kList, false);
		return result != null && result.getWeight() != null;
	}

	public boolean contains(List<K> kList) {
		Node<K, W> result = getNode(kList, false);
		return result != null;
	}

	public List<Pair<List<K>, W>> dump() {
		List<Pair<List<K>, W>> result = new ArrayList<>();
		List<K> superList = null;
		for (Node<K, W> node : mNodeList) {
			superList = new ArrayList<K>();
			superList.add(node.getKey());
			result.addAll(node.dump(superList));
		}
		return result;
	}

	public List<Pair<List<K>, W>> dump(List<K> superList) {
		List<K> kList = new ArrayList<>();
		kList.addAll(superList);
		Node<K, W> node = getNode(kList, false);
		return node.dump(superList);
	}

	public static final String[] toStringArray(String word) {
		char[] cList = word.toCharArray();
		String[] result = new String[cList.length];
		int index = 0;
		for (char c : cList) {
			result[index++] = String.valueOf(c);
		}
		return result;
	}

	@SafeVarargs
	public static final <T> List<T> asList(T... tList) {
		ArrayList<T> result = new ArrayList<>();
		for (T t : tList) {
			result.add(t);
		}
		return result;
	}

	public static final Pair<String, Integer> toString(Pair<List<String>, Integer> word) {
		StringBuffer result = new StringBuffer();
		for (String s : word.getK()) {
			result.append(s);
		}
		return new Pair<>(result.toString(), word.getW());
	}

	public static void main(String[] args) {
		Tree<String, Integer> tree = new Tree<>();
		tree.append(asList(toStringArray("apple")), 100);
		tree.append(asList(toStringArray("application")), 130);
		tree.append(asList(toStringArray("book")), 110);
		tree.append(asList(toStringArray("bug")), 140);
		tree.append(asList(toStringArray("bike")), 160);
		tree.append(asList(toStringArray("cat")), 1050);
		tree.append(asList(toStringArray("cut")), 190);
		tree.append(asList(toStringArray("cup")), 1200);
		tree.append(asList(toStringArray("computer")), 1030);
		tree.append(asList(toStringArray("work")), 1400);

		List<Pair<List<String>, Integer>> allNodes = tree.dump();
		Pair<String, Integer> word = null;
		for (Pair<List<String>, Integer> pair : allNodes) {
			word = toString(pair);
			System.out.println(String.format("%s - %s", word.getK(), word.getW()));
		}
		System.out.println("===========================");
		System.out.println(tree.isLeaf(asList(toStringArray("appl"))));
		System.out.println("===========================");
		allNodes = tree.dump(asList(toStringArray("appl")));
		for (Pair<List<String>, Integer> pair : allNodes) {
			word = toString(pair);
			System.out.println(String.format("%s - %s", word.getK(), word.getW()));
		}

	}

}
