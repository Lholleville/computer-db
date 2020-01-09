package main.java.app.comparator;

import java.util.Comparator;

import main.java.app.model.Computer;

public class NameComparator implements Comparator<Computer>{

	@Override
	public int compare(Computer o1, Computer o2) {
		 return o1.getName().compareTo(o2.getName());
	}

}
