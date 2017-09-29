package com.alio.structure;

import java.util.ArrayList;

import com.alio.base.PriorityConstant;

public class GraphBuilder<T> {

	protected class Edge<T1, T2> {

		private T1 mT1;

		private T2 mT2;

		private AnyObject mWeight = AnyObject.valueOf(PriorityConstant.NORMAL);

		public Edge(T1 t1, T2 t2) {
			super();
			mT1 = t1;
			mT2 = t2;
		}

		public Edge(T1 t1, T2 t2, AnyObject weight) {
			super();
			mT1 = t1;
			mT2 = t2;
			mWeight = weight;
		}

		public T1 getT1() {
			return mT1;
		}

		public T2 getT2() {
			return mT2;
		}

		public AnyObject getWeight() {
			return mWeight;
		}

		public void setWeight(AnyObject mWeight) {
			this.mWeight = mWeight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Edge) {
				try {
					@SuppressWarnings("unchecked")
					Edge<T, T> cEdge = (Edge<T, T>) obj;
					return mT1.equals(cEdge.mT1) && mT2.equals(cEdge.mT2);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return super.equals(obj);
			}
		}

	}

	private ArrayList<T> mVertexList;

	private ArrayList<Edge<T, T>> mEdgeList;

	public GraphBuilder() {
		super();
		mVertexList = new ArrayList<>();
		mEdgeList = new ArrayList<>();
	}

	public void clearAll() {
		mVertexList.clear();
		mEdgeList.clear();
	}

	public int getVertexCount() {
		return mVertexList.size();
	}
	
	public int getEdgeCount() {
		return mEdgeList.size();
	}
	
	public GraphBuilder<T> insertVertex(T vertex) {
		mVertexList.add(vertex);
		return this;
	}

	public GraphBuilder<T> deleteVertex(T vertex) {
		mEdgeList.remove(vertex);
		return this;
	}

	public GraphBuilder<T> insertEdge(T vertex1, T vertex2) {
		return insertEdge(vertex1, vertex2, false);
	}

	public GraphBuilder<T> insertEdge(T vertex1, T vertex2, boolean isBidirection) {
		mEdgeList.add(new Edge<T, T>(vertex1, vertex2));
		if (isBidirection) {
			mEdgeList.add(new Edge<T, T>(vertex2, vertex1));
		}
		return this;
	}

	public GraphBuilder<T> insertEdge(T vertex1, T vertex2, AnyObject weight) {
		mEdgeList.add(new Edge<T, T>(vertex1, vertex2, weight));
		return this;
	}

	public GraphBuilder<T> deleteEdge(T vertex1, T vertex2) {
		mEdgeList.remove(new Edge<T, T>(vertex1, vertex2));
		return this;
	}

	
	
	public Graph<T> build() {
		Graph<T> result = new Graph<>(mVertexList.size());
		result.insertAllVertex(mVertexList);
		for (Edge<T, T> edge : mEdgeList) {
			result.insertEdge(mVertexList.indexOf(edge.getT1()), mVertexList.indexOf(edge.getT2()), edge.getWeight());
		}
		return result;
	}

	public static void main(String[] args) {
		String labels[] = { "1", "2", "3", "4", "5", "6", "7", "8" };// 节点的标识
		GraphBuilder<String> graphBuilder = new GraphBuilder<String>();
		for (String label : labels) {
			graphBuilder.insertVertex(label);// 插入节点
		}
		graphBuilder.insertEdge("1", "2");
		graphBuilder.insertEdge("1", "3");
		graphBuilder.insertEdge("2", "4");
		graphBuilder.insertEdge("2", "5");
		graphBuilder.insertEdge("4", "8");
		graphBuilder.insertEdge("5", "8");
		graphBuilder.insertEdge("3", "6");
		graphBuilder.insertEdge("3", "7");
		graphBuilder.insertEdge("6", "7");

		Graph<String> graph = graphBuilder.build();
		System.out.println("深度优先搜索序列为：");
		for (String str : graph.depthFirstSearch()) {
			System.out.print(str + "  ");
		}
		System.out.println();
		System.out.println("广度优先搜索序列为：");
		for (String str : graph.broadFirstSearch()) {
			System.out.print(str + "  ");
		}
	}

}
