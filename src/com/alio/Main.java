package com.alio;

import java.util.ArrayList;
import java.util.List;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.exec.PlusExecutor;
import com.alio.graph.AnyObject;
import com.alio.graph.Graph;
import com.alio.graph.GraphBuilder;

public class Main {

	public static final AnyObject[] toResultList(List<Node> nodes) {
		List<AnyObject> list = new ArrayList<AnyObject>();
		for(Node node:nodes) {
			list.add(node.getValue());
		}
		AnyObject[] result = new AnyObject[list.size()];
		list.toArray(result);
		return result;
	}
	
	public static void main(String[] args) {
		GraphBuilder<Node> eGraph = new GraphBuilder<>();
		Node node1 = new Node();
		node1.setValue(AnyObject.valueOf(1));
		eGraph.insertVertex(node1);
		Node node2 = new Node();
		node2.setValue(AnyObject.valueOf(1));
		eGraph.insertVertex(node2);
		PlusExecutor node3 = new PlusExecutor();
		eGraph.insertVertex(node3);
		Graph<Node> graph = eGraph.build();
		List<Node> nodeList = graph.broadFirstSearch();
		IExecutable iExecutable = null;
		for (Node node : nodeList) {
			if (node instanceof IExecutable) {
				iExecutable = (IExecutable) node;
				List<Node> paramList = graph.getVertexListByDestination(node);
				iExecutable.exec(toResultList(paramList));
			}
		}
		List<Node> resultList = graph.getAllEndVertex();
		for(Node node : resultList) {
			System.out.println(node.dump());
		}
	}

}
