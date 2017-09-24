package com.alio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.exec.ExecutorType;
import com.alio.graph.AnyObject;
import com.alio.graph.Graph;
import com.alio.graph.GraphBuilder;

public class GraphEngine extends GraphBuilder<Node> {

	private Map<String, Node> mNodeMap = new HashMap<>();

	private List<String> mSpecialWords = new ArrayList<>();

	public GraphEngine() {
		super();
		for (ExecutorType t : ExecutorType.values()) {
			mSpecialWords.add(t.type());
		}
	}

	public static final AnyObject[] toResultList(List<Node> nodes) {
		if (nodes == null || nodes.size() <= 0) {
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
		if (mSpecialWords.contains(name)) {
			throw new IllegalArgumentException("the name exsited in field list, please use the other name...");
		}
		Node node = new Node(name);
		node.setValue(obj);
		mNodeMap.put(name, node);
		mSpecialWords.add(name);
	}

	public String[] getSpecialWordList() {
		String[] result = new String[mSpecialWords.size()];
		mSpecialWords.toArray(result);
		return result;
	}

	public AnyObject[] exec(String func) {
		clearAll();
		Scanner cScanner = new Scanner(func);
		String word = null;
		ExecutorType type = null;
		Node mLastNode = null;
		Node temp = null;
		while (cScanner.hasNext()) {
			word = cScanner.next();
			if (mNodeMap.containsKey(word)) {
				if (mLastNode != null && !(mLastNode instanceof IExecutable)) {
					cScanner.close();
					throw new IllegalArgumentException("there is no func between two fields.");
				}
				insertVertex(temp = mNodeMap.get(word));
				if (mLastNode != null && mLastNode instanceof IExecutable) {
					insertEdge(temp, mLastNode);
				} else {
					mLastNode = temp;
				}
			} else {
				if (mLastNode == null) {
					cScanner.close();
					throw new IllegalArgumentException("there is no field before the func.");
				}
				type = ExecutorType.typeOf(word);
				insertVertex(temp = type.getExecutor());
				if(mLastNode != null) {
					insertEdge(mLastNode, temp);
				}
				mLastNode = temp;
			}
		}
		cScanner.close();
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

	public static void main(String[] args) {
		GraphEngine engine = new GraphEngine();
		engine.appendField("a", AnyObject.valueOf(2));
		engine.appendField("b", AnyObject.valueOf(2));
		engine.appendField("c", AnyObject.valueOf(2));
		for(AnyObject obj : engine.exec("a + b + c")){
			System.out.println(obj);
		}
	}
	
}
