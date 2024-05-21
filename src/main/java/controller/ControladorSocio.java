package controller;

import java.util.List;

import model.Entidad;
import model.Socio;

public class ControladorSocio extends SuperControladorJPA {

	
private static ControladorSocio instance = null;
	
	
	public ControladorSocio() {
		super("socio", Socio.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorSocio getInstance() {
		if (instance == null) {
			instance = new ControladorSocio();
		}
		return instance;
	}
	
	public Socio obtenerContratoPorId(int id) {
        return (Socio) getEntityManager().createNativeQuery("SELECT * FROM contrato where id = " + id, Socio.class).getSingleResult();

    }
	
}
