package com.alio.word;

import java.util.ArrayList;

public class TextSegment {
	public static final class WordTuple<A, B> {
		final String first;
		final String second;

		WordTuple(String a, String b) {
			this.first = a;
			this.second = b;
		}
	}

	private static ArrayList<WordTuple<String, String>> spilts(String text) {
		ArrayList<WordTuple<String, String>> arrayList = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			WordTuple<String, String> tuple = new WordTuple<>(text.substring(0, i + 1),
					text.substring(i + 1, text.length()));
			arrayList.add(i, tuple);
		}
		return arrayList;
	}

	private static ArrayList<ArrayList<String>> _segment(String text) {
		if (text == null) {
			return new ArrayList<>();
		}
		ArrayList<ArrayList<String>> arrayListAll = new ArrayList<>();
		for (WordTuple<String, String> tuple : spilts(text)) {
			ArrayList<String> arrayList = new ArrayList<>();
			arrayList.add(tuple.first);
			ArrayList<ArrayList<String>> tmpArray = _segment(tuple.second);
			if (tmpArray.size() == 0) {
				arrayListAll.add(arrayList);
			} else {
				for (ArrayList<String> tmp : tmpArray) {
					ArrayList<String> tmpArrayList = new ArrayList<>();
					tmpArrayList.addAll(arrayList);
					tmpArrayList.addAll(tmp);
					arrayListAll.add(tmpArrayList);
				}
			}
		}
		return arrayListAll;
	}

	public static ArrayList<ArrayList<String>> segment(String text) {
		ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
		for (WordTuple<String, String> tuple : spilts(text)) {
			ArrayList<ArrayList<String>> tmpArray = _segment(tuple.second);
			if (tmpArray.size() == 0) {
				ArrayList<String> arrayList = new ArrayList<>();
				arrayList.add(tuple.first);
				arrayList.add(tuple.second);
				arrayLists.add(arrayList);
			}
			for (ArrayList<String> tmp : tmpArray) {
				ArrayList<String> arrayList = new ArrayList<>();
				arrayList.add(tuple.first);
				arrayList.addAll(tmp);
				arrayLists.add(arrayList);
			}
		}
		return arrayLists;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<String>> result = TextSegment.segment("wheninthecourse");
		System.out.println("[");
		for(ArrayList<String> list:result) {
			System.out.print("{");
			for(String word : list) {
				System.out.print(word);
				System.out.print(",");
			}
			System.out.println("},");
		}
		System.out.println("]");
	}

}
