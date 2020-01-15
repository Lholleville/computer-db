package main.java.app;

public abstract class Reptile {
	public  void layEggs() { System.out.println("Reptile laying eggs"); }
	public static void main(String[] args) {
		Reptile reptile = new Lizard();
		reptile.layEggs();
	}
}

class Lizard extends Reptile {
	public void layEggs() { System.out.println("Lizard laying eggs"); }
	public void test() {
		
	}
}