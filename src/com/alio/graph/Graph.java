package com.alio.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<T> {

	/**
	 * 存储点的链表
	 */
	private ArrayList<T> vertexList;

	/**
	 * 邻接矩阵，用来存储边
	 */
	private AnyObject[][] edges;

	/**
	 * 边的数目
	 */
	private int numOfEdges;

	Graph(int n) {
		edges = new AnyObject[n][n];
		vertexList = new ArrayList<T>(n);
		numOfEdges = 0;
	}

	/**
	 * 得到节点的个数
	 * 
	 * @return
	 */
	public int getNumOfVertex() {
		return vertexList.size();
	}

	/**
	 * 得到边的数目
	 * 
	 * @return
	 */
	public int getNumOfEdges() {
		return numOfEdges;
	}

	/**
	 * 返回节点i的数据
	 * 
	 * @param index
	 * @return
	 */
	public T getValueByIndex(int index) {
		return vertexList.get(index);
	}

	/**
	 * 返回v1,v2的权值
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public AnyObject getWeight(int v1, int v2) {
		return edges[v1][v2];
	}

	/**
	 * 插入节点
	 * 
	 * @param vertex
	 */
	public void insertVertex(T vertex) {
		vertexList.add(vertexList.size(), vertex);
	}

	/**
	 * 插入节点
	 * 
	 * @param vertex
	 */
	public void insertAllVertex(List<T> vertexs) {
		vertexList.addAll(vertexList.size(), vertexs);
	}

	/**
	 * 获取所有指向node的节点
	 * @param node
	 * @return
	 */
	public List<T> getVertexListByDestination(T node) {
		List<T> result = new ArrayList<T>();
		int index = vertexList.indexOf(node);
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[i][index] != null) {
				result.add(vertexList.get(i));
			}
		}
		return result;
	}

	/**
	 * 获取node节点所有指向
	 * @param node
	 * @return
	 */
	public List<T> getVertexListByOrigin(T node) {
		List<T> result = new ArrayList<T>();
		int index = vertexList.indexOf(node);
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] != null) {
				result.add(vertexList.get(i));
			}
		}
		return result;
	}
	
	/**
	 * 删除节点
	 * 
	 * @param vertex
	 *            节点数据
	 */
	public void deleteVertex(T vertex) {
		vertexList.remove(vertex);
	}

	/**
	 * 插入边
	 * 
	 * @param v1
	 *            节点1
	 * @param v2
	 *            节点2
	 * @param weight
	 */
	public void insertEdge(int v1, int v2, AnyObject weight) {
		edges[v1][v2] = weight;
		numOfEdges++;
	}

	/**
	 * 删除边
	 * 
	 * @param v1
	 *            节点1
	 * @param v2
	 *            节点2
	 */
	public void deleteEdge(int v1, int v2) {
		edges[v1][v2] = null;
		numOfEdges--;
	}

	/**
	 * 得到第一个邻接节点的下标
	 * 
	 * @param index
	 * @return
	 */
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] != null) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接节点的下标来取得下一个邻接节点
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] != null) {
				return j;
			}
		}
		return -1;
	}

	private List<T> depthFirstSearch(boolean[] isVisited, int i) {
		ArrayList<T> result = new ArrayList<T>();
		result.add(getValueByIndex(i));
		isVisited[i] = true;
		int w = getFirstNeighbor(i);
		while (w != -1) {
			if (!isVisited[w]) {
				result.addAll(depthFirstSearch(isVisited, w));
			}
			w = getNextNeighbor(i, w);
		}
		return result;
	}

	/**
	 * 深度优先遍历
	 */
	public List<T> depthFirstSearch() {
		boolean[] isVisited = new boolean[getNumOfVertex()];
		ArrayList<T> result = new ArrayList<T>();
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				result.addAll(depthFirstSearch(isVisited, i));
			}
		}
		return result;
	}

	/**
	 * 广度优先遍历
	 */
	public List<T> broadFirstSearch() {
		boolean[] isVisited = new boolean[getNumOfVertex()];
		ArrayList<T> result = new ArrayList<T>();
		LinkedList<Integer> cSubVertexQueue = new LinkedList<Integer>();
		int u, w;
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				cSubVertexQueue.clear();
				result.add(getValueByIndex(i));
				isVisited[i] = true;
				cSubVertexQueue.addLast(i);
				while (!cSubVertexQueue.isEmpty()) {
					u = ((Integer) cSubVertexQueue.removeFirst()).intValue();
					w = getFirstNeighbor(u);
					while (w != -1) {
						if (!isVisited[w]) {
							result.add(getValueByIndex(w));
							isVisited[w] = true;
							cSubVertexQueue.addLast(w);
						}
						w = getNextNeighbor(u, w);
					}
				}
			}
		}
		return result;
	}

	/**
	 * 获取所有边缘节点
	 * @return
	 */
	private List<Integer> getAllFringeVertex() {
		List<Integer> result = new LinkedList<Integer>();
		int num = getNumOfVertex();
		for (int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++){
				if(edges[j][i] != null || edges[i][j] != null) {
					continue;
				}
				result.add(i);
			}
		}
		return result;
	}
	
	public List<T> getAllEndVertex() {
		List<T> result = new LinkedList<T>();
		int num = getNumOfVertex();
		for (int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++){
				if(edges[j][i] != null) {
					
				}
			}
		}
		return result;
	}
	
	public static void main(String args[]) {
		String labels[] = { "1", "2", "3", "4", "5", "6", "7", "8" };// 节点的标识
		Graph<String> graph = new Graph<String>(labels.length);
		for (String label : labels) {
			graph.insertVertex(label);// 插入节点
		}
		graph.insertEdge(0, 1, AnyObject.valueOf(1));
		graph.insertEdge(0, 2, AnyObject.valueOf(1));
		graph.insertEdge(1, 3, AnyObject.valueOf(1));
		graph.insertEdge(1, 4, AnyObject.valueOf(1));
		graph.insertEdge(3, 7, AnyObject.valueOf(1));
		graph.insertEdge(4, 7, AnyObject.valueOf(1));
		graph.insertEdge(2, 5, AnyObject.valueOf(1));
		graph.insertEdge(2, 6, AnyObject.valueOf(1));
		graph.insertEdge(5, 6, AnyObject.valueOf(1));
		graph.insertEdge(1, 0, AnyObject.valueOf(1));
		graph.insertEdge(2, 0, AnyObject.valueOf(1));
		graph.insertEdge(3, 1, AnyObject.valueOf(1));
		graph.insertEdge(4, 1, AnyObject.valueOf(1));
		graph.insertEdge(7, 3, AnyObject.valueOf(1));
		graph.insertEdge(7, 4, AnyObject.valueOf(1));
		graph.insertEdge(6, 2, AnyObject.valueOf(1));
		graph.insertEdge(5, 2, AnyObject.valueOf(1));
		graph.insertEdge(6, 5, AnyObject.valueOf(1));

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