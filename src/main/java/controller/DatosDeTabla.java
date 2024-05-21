package controller;

import java.util.List;

import model.Socio;

public class DatosDeTabla {

	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "Apellido", "Apellido2", "FechaNac"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla() {
		// Obtengo todas las personas
		List<Socio> contratos = (List<Socio>) ControladorSocio.getInstance().findAll();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[contratos.size()][7];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < contratos.size(); i++) {
			Socio contratos1 = contratos.get(i);
			datos[i][0] = contratos1.getNombre();
			datos[i][1] = contratos1.getApellido1();
			datos[i][2] = contratos1.getApellido2();
			datos[i][3] = contratos1.getFechaNacimiento();

		}
		
		return datos;
	}
	
	
}
