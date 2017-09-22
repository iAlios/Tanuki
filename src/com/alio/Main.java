package com.alio;

import java.util.ArrayList;
import java.util.List;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.exec.DivideExecutor;
import com.alio.exec.MinusExecutor;
import com.alio.exec.ModExecutor;
import com.alio.exec.MultipyExecutor;
import com.alio.exec.PlusExecutor;
import com.alio.graph.AnyObject;
import com.alio.graph.Graph;
import com.alio.graph.GraphBuilder;

public class Main {

	public static final AnyObject[] toResultList(List<Node> nodes) {
		List<AnyObject> list = new ArrayList<AnyObject>();
		for (Node node : nodes) {
			list.add(node.getValue());
		}
		AnyObject[] result = new AnyObject[list.size()];
		list.toArray(result);
		return result;
	}

	/**
	 * 执行顺序按节点加入到图中的顺序来进行排序
	 * @param args
	 */
	public static void main(String[] args) {
		GraphBuilder<Node> eGraph = new GraphBuilder<>();
		Node node1 = new Node("a");
		node1.setValue(AnyObject.valueOf(1));
		eGraph.insertVertex(node1);
		Node node2 = new Node("b");
		node2.setValue(AnyObject.valueOf(5));
		eGraph.insertVertex(node2);
		PlusExecutor node3 = new PlusExecutor();
		eGraph.insertVertex(node3);
		Node node4 = new Node("c");
		node4.setValue(AnyObject.valueOf(3));
		eGraph.insertVertex(node4);
		MultipyExecutor node5 = new MultipyExecutor();
		eGraph.insertVertex(node5);
		ModExecutor node6 = new ModExecutor();
		eGraph.insertVertex(node6);
		DivideExecutor node7 = new DivideExecutor();
		eGraph.insertVertex(node7);
		MinusExecutor node8 = new MinusExecutor();
		eGraph.insertVertex(node8);
		eGraph.insertEdge(node1, node3, AnyObject.valueOf(1));
		eGraph.insertEdge(node2, node3, AnyObject.valueOf(1));
		eGraph.insertEdge(node4, node5, AnyObject.valueOf(1));
		eGraph.insertEdge(node3, node5, AnyObject.valueOf(1));
		eGraph.insertEdge(node4, node6, AnyObject.valueOf(1));
		eGraph.insertEdge(node3, node6, AnyObject.valueOf(1));
		eGraph.insertEdge(node4, node7, AnyObject.valueOf(1));
		eGraph.insertEdge(node3, node7, AnyObject.valueOf(1));
		eGraph.insertEdge(node3, node8, AnyObject.valueOf(1));
		eGraph.insertEdge(node4, node8, AnyObject.valueOf(1));
		Graph<Node> graph = eGraph.build();
		List<Node> nodeList = graph.broadFirstSearch();
		for (Node node : nodeList) {
			System.out.print(node.getName() + " ");
		}
		System.out.println();
		IExecutable iExecutable = null;
		for (Node node : nodeList) {
			if (node instanceof IExecutable) {
				iExecutable = (IExecutable) node;
				List<Node> paramList = graph.getVertexListByDestination(node);
				iExecutable.exec(toResultList(paramList));
			}
		}
		List<Node> resultList = graph.getAllEndVertex();
		for (Node node : resultList) {
			System.out.println(node.dump());
		}
		List<Node> nList = graph.getAllStartVertex();
		for (Node node : nList) {
			System.out.print(node.getName() + " ");
		}
	}

}
