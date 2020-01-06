package main.java.app;

public class Test {
	private int hihi =1;
	
	public Test() {
		Test lol = new Test();
		Test mdr = new Test();
		lol.hihi = mdr.hihi;
	}
}
