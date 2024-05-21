package view;

import javax.swing.JPanel;

import bankonterSupervitaminado.model.Usuario;
import controller.ControladorEquipo;
import model.Equipo;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.List;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelLista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static JList<Equipo> list;
	private DefaultListModel <Equipo> listM;

	/**
	 * Create the panel.
	 */
	public PanelLista() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblClasificacin = new JLabel("Clasificación");
		GridBagConstraints gbc_lblClasificacin = new GridBagConstraints();
		gbc_lblClasificacin.gridwidth = 3;
		gbc_lblClasificacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblClasificacin.gridx = 0;
		gbc_lblClasificacin.gridy = 0;
		panel.add(lblClasificacin, gbc_lblClasificacin);
		
		list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 4;
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 2;
		gbc_btnReset.gridy = 1;
		panel.add(btnReset, gbc_btnReset);
		
		JButton btnArriba = new JButton("Arriba");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnArriba = new GridBagConstraints();
		gbc_btnArriba.insets = new Insets(0, 0, 5, 0);
		gbc_btnArriba.gridx = 2;
		gbc_btnArriba.gridy = 2;
		panel.add(btnArriba, gbc_btnArriba);
		
		JButton btnAbajo = new JButton("Abajo");
		GridBagConstraints gbc_btnAbajo = new GridBagConstraints();
		gbc_btnAbajo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbajo.gridx = 2;
		gbc_btnAbajo.gridy = 3;
		panel.add(btnAbajo, gbc_btnAbajo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elimina();
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 4;
		panel.add(btnEliminar, gbc_btnEliminar);
		añadeEquipos();
	}
	
	private void añadeEquipos() {

		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		
		DefaultListModel <Equipo> listM = new DefaultListModel<>();

		list.setModel(listM);
		for (Equipo e : equipos) {
				listM.addElement(e);
		}
	}
	
	private void reset() {
		list.removeAll();
		List<Equipo> equipos = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		
		
		DefaultListModel <Equipo> listM = new DefaultListModel<>();

		list.setModel(listM);
		for (Equipo e : equipos) {
				listM.addElement(e);
		}
	}
	
	private static void elimina() {
        
        Equipo seleccionado = (Equipo) list.getSelectedValue();

        
       
            DefaultListModel<Equipo> listSeleccionadosModel = (DefaultListModel<Equipo>) list.getModel();
            
            listSeleccionadosModel.removeElement(seleccionado);
            
 

}
	
	private void arriba() {
		 Equipo seleccionado = (Equipo) list.getSelectedValue();
		 
	        
	       
	            DefaultListModel<Equipo> listSeleccionadosModel = (DefaultListModel<Equipo>) list.getModel();
	            
	            listSeleccionadosModel.removeElement(seleccionado);
	}
}
