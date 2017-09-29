package com.alio;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alio.base.IExecutable;
import com.alio.base.Node;
import com.alio.exec.DivideExecutor;
import com.alio.exec.MinusExecutor;
import com.alio.exec.ModExecutor;
import com.alio.exec.MultipyExecutor;
import com.alio.exec.PlusExecutor;
import com.alio.structure.AnyObject;
import com.alio.structure.Graph;
import com.alio.structure.GraphBuilder;

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
	 * 
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
		List<Node> nodeList = graph.getExecVertexList();
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
		System.out.println();
		nList = graph.getExecVertexList();
		for (Node node : nList) {
			System.out.print(node.getName() + " ");
		}

		System.out.println();
		System.out.println("============================");
		String regex = "\\s+.*\\s([a-z.A-Z0-9]+\\/[a-z.A-Z0-9]+)\\s+.*";
		Pattern p = Pattern.compile(regex);
		String s = "        4aa28ff com.sina.weibo/com.alipay.android.app.pay.MainActivity filter 2d58565";
		Matcher m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(0));
		}
		System.out.println("============================");
		System.out.println(s.replaceAll(regex, "$1"));
		System.out.println("==============1==============");
		regex = "[^0-9]*(\\d{4,6})[^0-9]*";
		s = "【金苗网络】您的验证码为：162253。请在5分钟内完成操作！回复TD拒收";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【tutorabc】您正在使用手机验证码登录，您的验证码：381665。验证码10分钟以内有效，请尽快完成验证。";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【小米】4084(登录/注册小米帐号验证码)请勿转发，转发将导致帐号被盗。本验证码5分钟有效。注册后将绑定此安全手机。";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【纳米盒】您的验证码是892268，请在5分钟内验证";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "您好！您本次申请的手机验证码为:1957 【浙江和教育】";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【中青奇未】验证码:321667,您正在使用360移动开放平台,需要进行校验(请勿向任何人提供您收到的短信校验码)";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【OK车险】验证码：5147，该验证码有效期30分钟。为保护您的账户安全，请勿将验证码告诉他人。";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【【腾讯科技】你正在登录微信，验证码504421。转发可能导致帐号被盗。如果这不是你本人操作，回复JZ可阻止该用户登录你的微信。";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【vivo】您好，754643是您注册的验证码。";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "【腾讯科技】353059（设备锁验证码），用于QQ18******3登录的设备验证，请勿转发。如不想接收此类短信，请回复T退订";
		System.out.println(s.replaceAll(regex, "$1"));

		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "验证码: 2222";
		System.out.println(s.replaceAll(regex, "$1"));
		
		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "2222";
		System.out.println(s.replaceAll(regex, "$1"));
		
		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
		System.out.println("==============1==============");
		s = "dd2222";
		System.out.println(s.replaceAll(regex, "$1"));
		
		System.out.println("==============2==============");
		p = Pattern.compile(regex);
		m = p.matcher(s);
		if (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("============================");
	}

}
