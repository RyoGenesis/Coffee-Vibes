package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Position {

	private int positionID;
	private String name;
	
	public Position(int positionID, String name) {
		super();
		this.positionID = positionID;
		this.name = name;
	}

	public Position() {}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private Position map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			return new Position(id,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//**UNFINISHED**
	public List<Position> getAllPositions(){
		return null;
	}
	
	public Position getPosition(int positionID) {
		return null;
	}
	//**UNFINISHED**
}
