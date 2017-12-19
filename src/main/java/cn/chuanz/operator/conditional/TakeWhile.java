package cn.chuanz.operator.conditional;

import io.reactivex.Observable;

public class TakeWhile {

	public static void main(String[] args) {
		new TakeWhile().takeWhile();
	}
	
	private void takeWhile() {
		Observable.fromArray(new String[]{"hello", "word", "nihao", "str"})
		.takeWhile(s -> !s.equals("nihao"))
		.subscribe(System.out::println);
	}
	
}
