package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Position {

	private int positionID;
	private String name;
	private String table = "position";
	
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

	public List<Position> getAllPositions(){
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM " + this.table;
		List<Position> positions = new Vector<>();
		try {
			ResultSet rs = con.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Position position = new Position(id,name);
				positions.add(position);
			}
		} catch (SQLException e) {
			
		}
		return positions;
	}
	
	public Position getPosition(int positionID) {
		Connect con = Connect.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM "+ this.table + " WHERE ID = ?");
			preparedStatement.setInt(1, positionID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				return new Position(id, name);
			}
		} catch (Exception e) {
			
		}
		return null;
	}
}
