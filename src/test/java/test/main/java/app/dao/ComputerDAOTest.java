package test.main.java.app.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

import junit.framework.TestCase;
import main.java.app.dao.ComputerDAO;
import main.java.app.model.Computer;

public class ComputerDAOTest extends TestCase{
	
	public static final Computer VALID_COMPUTER;
	static {
		VALID_COMPUTER = new Computer();
		VALID_COMPUTER.setId(1);
		VALID_COMPUTER.setName("MacBook Pro 15.4 inch");
		VALID_COMPUTER.setIntroductionDate(null);
		VALID_COMPUTER.setDiscontinuedDate(null);
		VALID_COMPUTER.setCompany(CompanyDAOTest.VALID_COMPANY);
}
	public ComputerDAOTest(String testMethodName) {
		super(testMethodName);
	}
	
	
	@Test
	public void testFindComputer() throws Exception{
		Optional<Computer> computerOptional = ComputerDAO.getInstance().find(1);
		assertTrue(computerOptional.isPresent());
		Computer computer = computerOptional.get();
		assertTrue(computer instanceof Computer);
		System.out.println(VALID_COMPUTER.toString());
		System.out.println(computer.toString());
		assertEquals(VALID_COMPUTER.toString(), computer.toString());
	}
	
	@Test
	public void testFindNullComputer() throws Exception{
		Optional<Computer> computerOptional = ComputerDAO.getInstance().find(10000);
		assertFalse(computerOptional.isPresent());
	}
	
	@Test
	public void testListComputer() throws Exception{
		ArrayList<Computer> list = ComputerDAO.getInstance().list();
		assertTrue(list.size() >= 300);
	}
	
//	@Test
//	public void testCreateComputer() throws Exception{
//		
//	}
//	
//	@Test
//	public void testUpdateComputer() throws Exception{
//		
//	}
//	
//	
//	public void testDeleteComputer() throws Exception{
//		
//	}
	
	public void testgetComputerNumber() throws Exception{
		assertTrue(ComputerDAO.getInstance().getComputerNumber() >= 0);
	}
	
}
