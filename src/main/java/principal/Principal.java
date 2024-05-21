package principal;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.Equipo;
import view.PanelEquipo;
import view.PanelGestionSocios;
import view.PanelLista;

public class Principal extends JFrame {
	
	public Principal() {
		super("Examen Programación");
		
		this.setBounds(0, 0, 800, 600);
		PanelGestionSocios panelSocio = new PanelGestionSocios();
		PanelEquipo panelEquipo = new PanelEquipo();
		PanelLista panelLista = new PanelLista();
		
		JTabbedPane panelTabbed = new JTabbedPane();
		panelTabbed.addTab("Socios", panelSocio);
		panelTabbed.addTab("Equipo", panelEquipo);
		panelTabbed.addTab("Lista", panelLista);
		panelTabbed.setSelectedIndex(0);
		
		this.getContentPane().add(panelTabbed);
	}
	

	public static void main(String[] args) {
		Principal ventana = new Principal();
		ventana.setVisible(true);
	}

}
