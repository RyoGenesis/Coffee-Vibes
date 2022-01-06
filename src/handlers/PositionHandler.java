package handlers;

import java.util.List;

import models.Position;

public class PositionHandler {

	public static PositionHandler positionHandler = null;
	public Position position;
	private String errorMessage;

	public PositionHandler() {
		position = new Position();
		errorMessage = "";
	}
	
	public static PositionHandler getInstance() {
		if (positionHandler == null) {
			positionHandler = new PositionHandler();
		}
		return positionHandler;
	}
	
	public List<Position> getAllPositions(){
		return position.getAllPositions();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//**UNFINISHED**
	public Position getPosition(int positionID) {
		return null;
	}
	//**UNFINISHED**

}
