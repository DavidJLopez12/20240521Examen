package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.event.ChangeEvent;

import bankonterSupervitaminado.view.GuiUtils;
import controller.ControladorEquipo;
import controller.ControladorSocio;
import model.Equipo;
import model.Socio;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGestionSocios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNombre;
	private JTextField jtfApellido1;
	private JTextField jtfApellido2;
	private Socio currentEntity = null;
	private JSlider jsliderantiguedad;
	private JFormattedTextField jtfFecha;
	private JLabel lblAntig;
	private JComboBox jcbEquipo;
	private JCheckBox chkSocio;
	private Equipo equipo;

	/**
	 * Create the panel.
	 * @param c 
	 */
	public PanelGestionSocios() {
		
		
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnPrimero = new JButton("");
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showEntityOnPanel(ControladorSocio.getInstance().findFirst());
			}
		});
		btnPrimero.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/gotostart.png")));
		toolBar.add(btnPrimero);
		
		JButton btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findPrevious(currentEntity.getId()));
			}
		});
		btnAnterior.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/previous.png")));
		toolBar.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findNext(currentEntity.getId()));
			}
		});
		btnSiguiente.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/next.png")));
		toolBar.add(btnSiguiente);
		
		JButton btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEntityOnPanel(ControladorSocio.getInstance().findLast());
			}
		});
		btnUltimo.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/gotoend.png")));
		toolBar.add(btnUltimo);
		
		JButton button_3_1 = new JButton("");
		button_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEntity();
			}
		});
		button_3_1.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/nuevo.png")));
		toolBar.add(button_3_1);
		
		JButton button_3_2 = new JButton("");
		button_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveEntity();
			}
		});
		button_3_2.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/guardar.png")));
		toolBar.add(button_3_2);
		
		JButton button_3_3 = new JButton("");
		button_3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEntity();
			}
		});
		button_3_3.setIcon(new ImageIcon(PanelGestionSocios.class.getResource("/res/eliminar.png")));
		toolBar.add(button_3_3);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblGestionDeSocios = new JLabel("Gestion de socios");
		GridBagConstraints gbc_lblGestionDeSocios = new GridBagConstraints();
		gbc_lblGestionDeSocios.gridwidth = 3;
		gbc_lblGestionDeSocios.insets = new Insets(0, 0, 5, 0);
		gbc_lblGestionDeSocios.gridx = 0;
		gbc_lblGestionDeSocios.gridy = 0;
		panel.add(lblGestionDeSocios, gbc_lblGestionDeSocios);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.gridwidth = 2;
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 1;
		panel.add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblPrimerApellido = new JLabel("Primer apellido:");
		GridBagConstraints gbc_lblPrimerApellido = new GridBagConstraints();
		gbc_lblPrimerApellido.anchor = GridBagConstraints.EAST;
		gbc_lblPrimerApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimerApellido.gridx = 0;
		gbc_lblPrimerApellido.gridy = 2;
		panel.add(lblPrimerApellido, gbc_lblPrimerApellido);
		
		jtfApellido1 = new JTextField();
		GridBagConstraints gbc_jtfApellido1 = new GridBagConstraints();
		gbc_jtfApellido1.gridwidth = 2;
		gbc_jtfApellido1.insets = new Insets(0, 0, 5, 0);
		gbc_jtfApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfApellido1.gridx = 1;
		gbc_jtfApellido1.gridy = 2;
		panel.add(jtfApellido1, gbc_jtfApellido1);
		jtfApellido1.setColumns(10);
		
		JLabel lblSegundoApellido = new JLabel("Segundo apellido:");
		GridBagConstraints gbc_lblSegundoApellido = new GridBagConstraints();
		gbc_lblSegundoApellido.anchor = GridBagConstraints.EAST;
		gbc_lblSegundoApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblSegundoApellido.gridx = 0;
		gbc_lblSegundoApellido.gridy = 3;
		panel.add(lblSegundoApellido, gbc_lblSegundoApellido);
		
		jtfApellido2 = new JTextField();
		GridBagConstraints gbc_jtfApellido2 = new GridBagConstraints();
		gbc_jtfApellido2.gridwidth = 2;
		gbc_jtfApellido2.insets = new Insets(0, 0, 5, 0);
		gbc_jtfApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfApellido2.gridx = 1;
		gbc_jtfApellido2.gridy = 3;
		panel.add(jtfApellido2, gbc_jtfApellido2);
		jtfApellido2.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 0;
		gbc_lblFechaDeNacimiento.gridy = 4;
		panel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		jtfFecha = new JFormattedTextField();
		GridBagConstraints gbc_jtfFecha = new GridBagConstraints();
		gbc_jtfFecha.gridwidth = 2;
		gbc_jtfFecha.anchor = GridBagConstraints.NORTH;
		gbc_jtfFecha.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFecha.gridx = 1;
		gbc_jtfFecha.gridy = 4;
		panel.add(jtfFecha, gbc_jtfFecha);
		
		JLabel lblAntiguedad = new JLabel("Antiguedad");
		GridBagConstraints gbc_lblAntiguedad = new GridBagConstraints();
		gbc_lblAntiguedad.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntiguedad.gridx = 0;
		gbc_lblAntiguedad.gridy = 5;
		panel.add(lblAntiguedad, gbc_lblAntiguedad);
		
		jsliderantiguedad = new JSlider();
		GridBagConstraints gbc_jsliderantiguedad = new GridBagConstraints();
		gbc_jsliderantiguedad.insets = new Insets(0, 0, 5, 5);
		gbc_jsliderantiguedad.gridx = 1;
		gbc_jsliderantiguedad.gridy = 5;
		panel.add(jsliderantiguedad, gbc_jsliderantiguedad);
		
		lblAntig = new JLabel("New label");
		GridBagConstraints gbc_lblAntig = new GridBagConstraints();
		gbc_lblAntig.insets = new Insets(0, 0, 5, 0);
		gbc_lblAntig.gridx = 2;
		gbc_lblAntig.gridy = 5;
		panel.add(lblAntig, gbc_lblAntig);
		
		chkSocio = new JCheckBox("Socio en activo");
		GridBagConstraints gbc_chkSocio = new GridBagConstraints();
		gbc_chkSocio.insets = new Insets(0, 0, 5, 5);
		gbc_chkSocio.gridx = 1;
		gbc_chkSocio.gridy = 6;
		panel.add(chkSocio, gbc_chkSocio);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.anchor = GridBagConstraints.EAST;
		gbc_lblEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 7;
		panel.add(lblEquipo, gbc_lblEquipo);
		
		jcbEquipo = new JComboBox();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 2;
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 1;
		gbc_jcbEquipo.gridy = 7;
		panel.add(jcbEquipo, gbc_jcbEquipo);
		
		addControlCustomizableBehaviours();
	
		
		showEntityOnPanel((Socio) ControladorSocio.getInstance().findFirst());
		Equipo e = this.currentEntity.getEquipo();
		
		cargarTodosEquipos();
		selEquipo(e);
		
		

	}
	
	private void deleteEntity () {
		try {
			String respuestas[] = new String[] {"Sí", "No"};
			int opcionElegida = JOptionPane.showOptionDialog(
					null, 
					"¿Realmente desea eliminar el registro?", 
					"Eliminación de socio", 
			        JOptionPane.DEFAULT_OPTION, 
			        JOptionPane.WARNING_MESSAGE, 
			        null, respuestas, 
			        respuestas[1]);
		    
			if(opcionElegida == 0) {
				ControladorSocio.getInstance().remove(currentEntity);
		    	  
		    	  // Decido qué registro voy a mostrar en pantalla.
		    	  // Voy a comprobar si existe un anterior, si existe lo muestro
		    	  // Si no existe anterior compruebo si existe siguiente, 
		    	  // si existe lo muestro. En caso contrario, no quedan registros
		    	  // así que muestro en blanco la pantalla
		    	  this.currentEntity = ControladorSocio.getInstance().findPrevious(this.currentEntity.getId());
		    	  if (this.currentEntity != null) { // Existe un anterior, lo muestro
		    		  showEntityOnPanel(currentEntity);
		    	  }
		    	  else {
		    		  this.currentEntity = ControladorSocio.getInstance().findNext(this.currentEntity.getId());
			    	  if (this.currentEntity != null) { // Existe un anterior, lo muestro
			    		  showEntityOnPanel(currentEntity);
			    	  }
		    		  else { // No quedan registros en la tabla
		    			  newEntity();
		    		  }
		    	  }
		      }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void setTipoSocio (Socio t) {
		if (t != null) {
			this.currentEntity.setSocio(t);
//			this.jtfTipoContrato.setText(t.toString());
		}
	}
	
	private void saveEntity() {
		this.currentEntity.setNombre(this.jtfNombre.getText());
		this.currentEntity.setApellido1(this.jtfApellido1.getText());
		this.currentEntity.setApellido2(this.jtfApellido2.getText());
		this.currentEntity.setFechaNacimiento(GuiUtils.getDateFromFormattedString("dd/MM/yyyy", this.jtfFecha.getText()));
		this.currentEntity.setAntiguedadAnios(this.jsliderantiguedad.getValue());
		if (this.chkSocio.isSelected()) {
			this.currentEntity.setActivo(1);
		}
		else {
			this.currentEntity.setActivo(0);
		}
		this.currentEntity.setEquipo((Equipo) jcbEquipo.getSelectedItem());
		try {
			ControladorSocio.getInstance().save(currentEntity);
			JOptionPane.showMessageDialog(null, "Contrato guardado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "NO se ha guardado el contrato. ERROR");
		}
	}
	
	
	private void showEntityOnPanel (Socio s) {
		
		if (s != null) {
			this.currentEntity = s;			
			this.jtfNombre.setText(this.currentEntity.getNombre());
			this.jtfApellido1.setText(this.currentEntity.getApellido1());
			this.jtfApellido2.setText(this.currentEntity.getApellido2());
			this.jtfFecha.setText(getFormattedStringFromDate("dd/MM/yyyy", this.currentEntity.getFechaNacimiento()));
			
			// Asociado al slider del saldo
			this.jsliderantiguedad.setMaximum(200);
			this.jsliderantiguedad.setMinimum(0);
			this.jsliderantiguedad.setValue((int) this.currentEntity.getAntiguedadAnios());
			this.showSliderBalanceDescriptor();
			
			if (currentEntity.getActivo() == 1) {
				this.chkSocio.setSelected(true);
			}
			else {
				this.chkSocio.setSelected(false);
			}
			Equipo e = this.currentEntity.getEquipo();
			selEquipo(e);
		}
		
	}
	
	private void cargarTodosEquipos() {
		List<Equipo> l = (List<Equipo>) ControladorEquipo.getInstance().findAll();
		for (Equipo o : l) {
			jcbEquipo.addItem(o);
		}
	}
	
	private void newEntity () {
		this.currentEntity = new Socio();
		this.currentEntity.setId(0);
		this.currentEntity.setSocio(ControladorSocio.getInstance().findById(1));
		this.currentEntity.setEquipo(ControladorEquipo.getInstance().findById(1));
		
		showEntityOnPanel(currentEntity);
	}
	
	private void selEquipo (Equipo o) {
		for (int i = 0; i < this.jcbEquipo.getItemCount(); i++) {
            if ((((Equipo) this.jcbEquipo.getItemAt(i)).getId() == o.getId())) {
                this.jcbEquipo.setSelectedIndex(i);
            }
        }
	}
	
	public static String getFormattedStringFromDate (String dateFormat, Date date) {
		try {
			return new SimpleDateFormat(dateFormat).format(date);
		}
		catch (Exception ex) {
			System.out.println("Unable to format date: " + date + " with format: " + dateFormat);
			return "";
		}
	}
	
	private void showSliderBalanceDescriptor () {
		this.lblAntig.setText(this.jsliderantiguedad.getValue() + " años ");
	}
	
	private void addControlCustomizableBehaviours() {
		
		// JFormattedTextfield para la fecha de firma, si el valor no es correcto se pone fondo en rojo
		this.jtfFecha.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
		public AbstractFormatter getFormatter(JFormattedTextField tf) {
				return new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							jtfFecha.setBackground(Color.WHITE);
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
							return sdf.parse(text);
						} catch (Exception e) {
							jtfFecha.setBackground(Color.RED);
							JOptionPane.showMessageDialog(null, "Error en la fecha");
							return null;
						}
					}
				};
			}
		});
		

		
		// JSlider, al cambiar su valor modifica la etiqueta que se encuentra a su derecha
		this.jsliderantiguedad.addChangeListener(new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				showSliderBalanceDescriptor();
			}
		});
	}

}
