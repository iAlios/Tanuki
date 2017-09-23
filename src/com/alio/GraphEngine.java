package com.alio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.graph.AnyObject;
import com.alio.graph.Graph;
import com.alio.graph.GraphBuilder;

public class GraphEngine extends GraphBuilder<Node> {

	private Map<String, Node> mNodeMap = new HashMap<>();

	public static final AnyObject[] toResultList(List<Node> nodes) {
		if(nodes == null || nodes.size() <= 0) {
			return null;
		}
		List<AnyObject> list = new ArrayList<AnyObject>();
		for (Node node : nodes) {
			list.add(node.getValue());
		}
		AnyObject[] result = new AnyObject[list.size()];
		list.toArray(result);
		return result;
	}

	public void appendField(String name, AnyObject obj) {
		Node node = new Node(name);
		node.setValue(obj);
		mNodeMap.put(name, node);
	}

	public String[] getSpecialWordList() {
		List<String> mWordList = new ArrayList<>();
		mWordList.addAll(mNodeMap.keySet());
		String[] result = new String[mWordList.size()];
		mWordList.toArray(result);
		return result;
	}
	
	public AnyObject[] exec(String func) {
		clearAll();
		for (Node node : mNodeMap.values()) {
			insertVertex(node);
		}
		// TODO insert edge
		
		Graph<Node> graph = build();
		List<Node> nodeList = graph.getExecVertexList();
		IExecutable iExecutable = null;
		for (Node node : nodeList) {
			if (node instanceof IExecutable) {
				iExecutable = (IExecutable) node;
				List<Node> paramList = graph.getVertexListByDestination(node);
				iExecutable.exec(toResultList(paramList));
			}
		}
		return toResultList(graph.getAllEndVertex());
	}

}
