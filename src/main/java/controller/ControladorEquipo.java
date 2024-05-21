package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Entidad;
import model.Equipo;

public class ControladorEquipo extends SuperControladorJPA{

private static ControladorEquipo instance = null;
	
	
	public ControladorEquipo() {
		super("equipo", Equipo.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorEquipo getInstance() {
		if (instance == null) {
			instance = new ControladorEquipo();
		}
		return instance;
	}
	
	

}
