package com.alio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.base.PriorityConstant;
import com.alio.exec.ExecutorType;
import com.alio.structure.AnyObject;
import com.alio.structure.Graph;
import com.alio.structure.GraphBuilder;
import com.alio.structure.Tree;

public class GraphEngine extends GraphBuilder<Node> {

	private Map<String, Node> mNodeMap = new HashMap<>();

	private List<String> mSpecialWords = new ArrayList<>();

	private Tree<String, Integer> mSpecialWordTree = new Tree<>();

	public GraphEngine() {
		super();
		for (ExecutorType t : ExecutorType.values()) {
			mSpecialWords.add(t.type());
			mSpecialWordTree.append(Tree.asList(Tree.toStringArray(t.type())), 100);
		}
		mSpecialWordTree.append(Tree.asList(Tree.toStringArray("(")), 100);
		mSpecialWordTree.append(Tree.asList(Tree.toStringArray(")")), 100);
		mSpecialWordTree.append(Tree.asList(Tree.toStringArray("{")), 100);
		mSpecialWordTree.append(Tree.asList(Tree.toStringArray("}")), 100);
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
		mSpecialWordTree.append(Tree.asList(Tree.toStringArray(name)), 100);
	}

	public String[] getSpecialWordList() {
		String[] result = new String[mSpecialWords.size()];
		mSpecialWords.toArray(result);
		return result;
	}

	private AnyObject exec(String[] elementList) {
		ExecutorType type = null;
		Node mLastNode = null;
		Node temp = null;
		long cIncreasePriorityWeight = 0;
		// 将所有节点加入到初始化图中存储
		for (String word : elementList) {
			if (mNodeMap.containsKey(word)) {
				if (mLastNode != null && !(mLastNode instanceof IExecutable)) {
					throw new IllegalArgumentException("there is no func between two fields.");
				}
				insertVertex(temp = mNodeMap.get(word));
				if (mLastNode != null) {
					insertEdge(temp, mLastNode);
				}
				temp.increasePriorityWeight(cIncreasePriorityWeight);
				mLastNode = temp;
			} else if (word.equals("(")) {
				cIncreasePriorityWeight += PriorityConstant.BRACKET;
			} else if (word.equals(")")) {
				cIncreasePriorityWeight -= PriorityConstant.BRACKET;
			} else {
				if (mLastNode == null) {
					throw new IllegalArgumentException("there is no field before the func.");
				}
				type = ExecutorType.typeOf(word);
				insertVertex(temp = type.getExecutor());
				if (mLastNode != null) {
					insertEdge(mLastNode, temp);
				}
				temp.increasePriorityWeight(cIncreasePriorityWeight);
				mLastNode = temp;
			}
		}
		if (!(getEdgeCount() > 0 && getVertexCount() > 0)) {
			throw new IllegalArgumentException("the func is empty.");
		}
		Graph<Node> graph = build();
		List<Node> nodeList = graph.getAllEndVertex();
		// 对所有计算节点进行优先级排序
		nodeList.sort(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o2.getPriorityWeight().compareTo(o1.getPriorityWeight());
			}

		});
		int index = -1;
		int target = -1;
		int lastNodeIndex = -1;
		AnyObject weightTo = null;
		AnyObject weightFrom = null;
		List<Node> packageNodes = new ArrayList<Node>();
		List<Node> subNodes;
		// 根据优先级包裹的字段来更新各个节点之间的依赖关系
		for (Node node : nodeList) {
			index = graph.getIndexByValue(node);
			subNodes = graph.getDependenceList(index);
			if (packageNodes.size() > 0) {
				for (Node sub : subNodes) {
					if (packageNodes.contains(sub)) {
						target = graph.getIndexByValue(sub);
						weightTo = graph.deleteEdge(index, target);
						weightFrom = graph.deleteEdge(target, index);
						graph.insertEdge(index, lastNodeIndex, weightTo);
						graph.insertEdge(lastNodeIndex, index, weightFrom);
					}
				}
			}
			packageNodes.addAll(subNodes);
			lastNodeIndex = index;
		}
		// 执行整个图结构
		nodeList = graph.getExecVertexList();
		IExecutable iExecutable = null;
		for (Node node : nodeList) {
			if (node instanceof IExecutable) {
				iExecutable = (IExecutable) node;
				List<Node> paramList = graph.getVertexListByDestination(node);
				iExecutable.exec(toResultList(paramList));
			}
		}
		return toResultList(graph.getAllEndVertex())[0];
	}

	public AnyObject exec1(String func) {
		if (func == null || func.trim().length() <= 0) {
			throw new IllegalArgumentException("the func is empty.");
		}
		clearAll();
		Scanner cScanner = new Scanner(func.trim());
		ArrayList<String> list = new ArrayList<String>();
		while (cScanner.hasNext()) {
			list.add(cScanner.next());
		}
		cScanner.close();
		return exec(list.toArray(new String[list.size()]));
	}

	public AnyObject exec(String func) {
		if (func == null || func.trim().length() <= 0) {
			throw new IllegalArgumentException("the func is empty.");
		}
		clearAll();
		ArrayList<String> list = new ArrayList<String>();
		int from = 0;
		String word = null;
		String segment = null;
		for (int index = 1; index <= func.trim().length(); index++) {
			word = func.substring(from, index);
			if(index < func.trim().length()) {
				segment = func.substring(from, index + 1);
				if (!word.isEmpty() && mSpecialWordTree.isLeaf(Tree.asList(Tree.toStringArray(word)))
						&& !mSpecialWordTree.isLeaf(Tree.asList(Tree.toStringArray(segment)))) {
					list.add(word);
				}
				from = index;
			} else {
				list.add(word);
			}
		}
		return exec(list.toArray(new String[list.size()]));
	}

	public void dumpFieldList() {
		for (Node node : mNodeMap.values()) {
			System.out.println(String.format("%s = %s", node.getName(), node.getValue()));
		}
	}

	public static void main(String[] args) {
		GraphEngine engine = new GraphEngine();
		engine.appendField("a", AnyObject.valueOf(2));
		engine.appendField("b", AnyObject.valueOf(3));
		engine.appendField("c", AnyObject.valueOf(4));
		engine.appendField("d", AnyObject.valueOf(5));
		engine.dumpFieldList();
		String func = "a+b";
		System.out.println(String.format("%s = %s", func, engine.exec(func)));
		func = "a + b*c";
		System.out.println(String.format("%s = %s", func, engine.exec(func)));
		func = "(a + b)*c";
		System.out.println(String.format("%s = %s", func, engine.exec(func)));
		func = "((a + b)*c)*d";
		System.out.println(String.format("%s = %s", func, engine.exec(func)));
	}

}
