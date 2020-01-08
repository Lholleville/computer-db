package main.java.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.java.app.model.Company;
import main.java.app.model.Computer;

/**
 * @author holle
 */
public class ComputerDAO {

	private static ComputerDAO computerDAO = null;
	//private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
	/*SQL QUERIES*/
	private final String SQL_FIND_COMPUTER = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id"
	    	+ " WHERE c.id = ?";
	private final String SQL_FIND_ALL_COMPUTER = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id";
	private final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";
	private final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE computer.id = ?";
	private final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String SQL_FIND_LAST_COMPUTER_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY id DESC LIMIT 1";
	private final String SQL_GET_COMPUTER_COUNT = "SELECT count(id) as nb FROM computer";
	private final String PAGINATION = " LIMIT ?, ?";
	private final String SQL_FIND_COMPUTER_BY_NAME = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id"
    		+ " WHERE c.name LIKE ?;";
	private ComputerDAO() {}
	
	public static ComputerDAO getInstance() {
		if(computerDAO == null) {
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}
	
	public Optional<Computer> find(long id) {	
		Computer computer = null;
		try {
			Connection connect = ConnectionMYSQL.getInstance().connect();
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_COMPUTER);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			if(result.first()) {
				computer = this.map(result);
			}
			result.close();
		}catch(SQLException e) {
			e.printStackTrace();
			//LOGGER.error("error");
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return Optional.ofNullable(computer);
	}

	public ArrayList<Computer> list() {
		
		ArrayList<Computer> list = new ArrayList<Computer>();
		try{
			Connection connect = ConnectionMYSQL.getInstance().connect(); 
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_ALL_COMPUTER);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				list.add(this.map(result));
			}
			result.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return list;
	}

	public Computer create(String name, LocalDate introduced, LocalDate discontinued, long company) {
		
		Computer computer = new Computer();
		try{			
			Connection connect = ConnectionMYSQL.getInstance().connect();
			PreparedStatement prepare = connect.prepareStatement(SQL_CREATE_COMPUTER);
			prepare.setString(1, name);
			prepare.setTimestamp(2, introduced != null ? Timestamp.valueOf(introduced.atStartOfDay()) : null);
			prepare.setTimestamp(3, discontinued != null ? Timestamp.valueOf(discontinued.atStartOfDay()) : null);
			prepare.setLong(4, company);
			prepare.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return computer;
	}	

	public Computer update(long id, String name, LocalDate introduced, LocalDate discontinued, long companyId) {
		
		Optional<Computer> optComputer = this.find(id);
		if(optComputer.isPresent()) {
			Computer computer = optComputer.get();
			String nameSQL = (!name.equals(computer.getName()) && !name.isEmpty()) ? name : computer.getName();
			LocalDate introducedSQL = (introduced != null) ? ((computer.getIntroductionDate() != null ) ? ((introduced.compareTo(computer.getIntroductionDate()) != 0) ? introduced : computer.getIntroductionDate()) : introduced) : computer.getIntroductionDate();
			LocalDate discontinuedSQL = (discontinued != null) ? ((computer.getDiscontinuedDate() != null) ? ((discontinued.compareTo(computer.getDiscontinuedDate()) != 0) ? discontinued : computer.getIntroductionDate()) : discontinued) : computer.getDiscontinuedDate();
					
			long companyIdSQL = (companyId != computer.getCompany().getId() && companyId != 0) ? companyId : computer.getCompany().getId();
					
			try{
				Connection connect = ConnectionMYSQL.getInstance().connect();
				PreparedStatement prepare = connect.prepareStatement(SQL_UPDATE_COMPUTER);
				prepare.setString(1, nameSQL);
				prepare.setTimestamp(2, (introducedSQL != null) ? Timestamp.valueOf(introducedSQL.atStartOfDay()) : null);
				prepare.setTimestamp(3, (discontinuedSQL != null) ? Timestamp.valueOf(discontinuedSQL.atStartOfDay()) : null);
				prepare.setLong(4, companyIdSQL);
				prepare.setLong(5, id);
				prepare.executeUpdate();	
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionMYSQL.getInstance().disconnect();
			}
			
		}
		return optComputer.get();
	}

	public Computer delete(long id) {
		Optional<Computer> computer = find(id);
		Connection connect = ConnectionMYSQL.getInstance().connect();
		if(computer.isPresent()) {
			try(PreparedStatement prepare = connect.prepareStatement(SQL_DELETE_COMPUTER);) {
				prepare.setLong(1, id);
				prepare.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionMYSQL.getInstance().disconnect();
			}
		}
		return computer.get();
	}

	public int getComputerNumber() {
		int nb = 0;
		Connection connect = ConnectionMYSQL.getInstance().connect();
		try{
			PreparedStatement stmt = connect.prepareStatement(SQL_GET_COMPUTER_COUNT); 
			ResultSet result = stmt.executeQuery();
			if(result.first()) {
				nb = result.getInt("nb");
			}
			result.close();
		}catch(SQLException e) {
			//LOGGER.error("Fail to get Computer's number");
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return nb;
	}
	
	public ArrayList<Computer> paginate(int page, int show){
		ArrayList<Computer> computers = new ArrayList<Computer>();
		
		int limit = show;
		int offset = page * limit - (show - 1) - 1;
		Connection connect = ConnectionMYSQL.getInstance().connect();
		try {
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_ALL_COMPUTER + PAGINATION); 
			stmt.setInt(1, offset);
			stmt.setInt(2, limit);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				computers.add(this.map(result));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		
		return computers;
	}
	
	public ArrayList<Computer> findByName(String name){
		 ArrayList<Computer> computer = new ArrayList<Computer>();
		try {
			Connection connect = ConnectionMYSQL.getInstance().connect();
			PreparedStatement stmt = connect.prepareStatement(SQL_FIND_COMPUTER_BY_NAME);
			stmt.setString(1, "%"+name+"%");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				computer.add(this.map(result));
			}
			result.close();
		}catch(SQLException e) {
			e.printStackTrace();
			//LOGGER.error("error");
		}finally {
			ConnectionMYSQL.getInstance().disconnect();
		}
		return computer;
	}
	
	private Computer map(ResultSet result) throws SQLException {	
		
		Timestamp intro = result.getTimestamp("introduced");
		Timestamp disco = result.getTimestamp("discontinued");
		Computer computer = new Computer();
		Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
		computer = new Computer(
				result.getLong("id"), 
				result.getString("name"), 
		    	(intro != null) ? intro.toLocalDateTime().toLocalDate() : null,
		    	(disco != null) ? disco.toLocalDateTime().toLocalDate() : null,
		    	company
		    );
		return computer;
	}
}
