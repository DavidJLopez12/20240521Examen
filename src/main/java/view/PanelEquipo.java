package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bankonterSupervitaminado.controller.ControladorTipoContrato;
import bankonterSupervitaminado.model.Tipocontrato;
import bankonterSupervitaminado.view.GuiUtils;
import bankonterSupervitaminado.view.PanelSeleccionTipoContrato;
import controller.ControladorEquipo;
import controller.ControladorSocio;
import model.Equipo;
import model.Socio;

public class PanelEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JScrollPane scrollPane;
	private Socio currentSocio;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public PanelEquipo() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Socios de Equipo");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblEquipo = new JLabel("Equipo");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEquipo.anchor = GridBagConstraints.EAST;
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 1;
		panel.add(lblEquipo, gbc_lblEquipo);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JRadioButton rdNombre = new JRadioButton("Ordena por nombre");
		buttonGroup.add(rdNombre);
		GridBagConstraints gbc_rdNombre = new GridBagConstraints();
		gbc_rdNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rdNombre.gridx = 1;
		gbc_rdNombre.gridy = 2;
		panel.add(rdNombre, gbc_rdNombre);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Ordena por Apellido");
		buttonGroup.add(rdbtnNewRadioButton_2);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_2.gridx = 2;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		panel.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Ordena por segundo apellido");
		buttonGroup.add(rdbtnNewRadioButton_1);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 3;
		panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Ordena por fecha");
		buttonGroup.add(rdbtnNewRadioButton_3);
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_3.gridx = 2;
		gbc_rdbtnNewRadioButton_3.gridy = 3;
		panel.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		cargarTodosEquipos();
		fastSearch();
	}
	

	private void cargarTodosEquipos() {
		List<Equipo> l = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		for (Equipo o : l) {
			comboBox.addItem(o);
		}
	}
	
	private void fastSearch() {
		List<Socio> contractTypes = 
				(List<Socio>) ControladorSocio.getInstance().findAll();
		
		// Convierto datos de lista a matriz
		Object m[][] = new Object[contractTypes.size()][4];
		for (int i = 0; i < m.length; i++) {
			m[i][0] = contractTypes.get(i).getNombre();
			m[i][1] = contractTypes.get(i).getApellido1();
			m[i][2] = contractTypes.get(i).getApellido2();
			m[i][3] = contractTypes.get(i).getFechaNacimiento();
		}
		
		// Creo una tabla con los datos anteriores, con un DefaultTableModel que no permita editar filas
		DefaultTableModel dtm = new DefaultTableModel(m, 
				new String[] {"Nombre", "PrimerApellido", "SegundoApellido", "Fecha de Nacimiento"}) {			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table = new JTable(dtm);
		
		// Selecciono el tipo de contrato
		if (currentSocio != null) {
			for (int i = 0; i < contractTypes.size(); i++) {
				if (contractTypes.get(i).getId() == this.currentSocio.getId()) {
					table.setRowSelectionInterval(i, i);
				}
			}
		}
		
		// Determino el mecanismo de doble clic de seleccion del tipo de contrato sobre la tabla
		PanelEquipo thisPanel = this; // Necesito un puntero a "this" antes de entrar en la clase anónima de MouseAdapter
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				// Compruebo el doble clic
				if (e.getClickCount() > 1) {
					if (table.getSelectedRow() > -1) {
						PanelGestionSocios panelGestionSocios = new PanelGestionSocios();
						panelGestionSocios.setTipoSocio(contractTypes.get(table.getSelectedRow()));
						JOptionPane.showMessageDialog(null, "has hecho clic sobre");
					}
				}
			}
			
		});
		
		// Muestro la JTable construida
		scrollPane.setViewportView(table);
	}

}
