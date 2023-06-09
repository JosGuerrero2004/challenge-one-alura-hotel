package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huespedes;
import modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private JTabbedPane panel;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("Ingrese apellido o el id del registro.");
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(311, 62, 318, 42);
		contentPane.add(lblNewLabel_4);

		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		//Reservas
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		//Huespedes
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		panel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				limpiarTablas();
				cargarTabla();
			}
		});
		
		limpiarTablas();
		cargarTabla();
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTablas();
				buscar();
			}
		});
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar =  new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar();
				limpiarTablas();
				cargarTabla();
			}
		});
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
				limpiarTablas();
				cargarTabla();
			}
		}); 
		
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	
	//Funciones del formulario
	
		//Funciones de bd:
	private void buscar() {
		String texto = txtBuscar.getText();
		try {
		    int id = Integer.parseInt(texto);
		    
		    if (panel.getSelectedIndex() == 0) {
				var reservas = this.reservaController.buscarId(id);
				llenarReserva(reservas);
			} else {
				var huespedes = this.huespedController.buscarId(id);
				llenarHuesped(huespedes);
			}
		    // El valor es un número entero
		} catch (NumberFormatException e) {
			var huespedes = this.huespedController.buscarApellido(texto);
			llenarHuesped(huespedes);
			//el valor es una cadena de texto
		}
	}
	
	private void editar() {
		if(panel.getSelectedIndex() == 0) {
			if(tieneFilaElegidaReservas()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						
						Date checkIn = new Date(parseDateLong(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString()));
						Date checkOut = new Date(parseDateLong(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString()));
						
						BigDecimal valor = new BigDecimal(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						String formaPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
						
						Reservas reservaModificada = new Reservas(id, checkIn, checkOut, valor, formaPago);
						
						int cantidadModificada = this.reservaController.editar(reservaModificada);
						
						JOptionPane.showMessageDialog(this, cantidadModificada + " Item modificado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else {
			if(tieneFilaElegidaHuespedes()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
						String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
						
						Date fechaNa = new Date(parseDateLong(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString()));
						
						String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
						String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
						Integer idReserva = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
						
						Huespedes huespedModificado = new Huespedes(id, nombre, apellido, fechaNa, nacionalidad, telefono, idReserva);
						
						int cantidadModificada = this.huespedController.editar(huespedModificado);
						
						JOptionPane.showMessageDialog(this, cantidadModificada + " Item modificado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}
	
	private void eliminar() {
		if(panel.getSelectedIndex() == 0) {
			if(tieneFilaElegidaReservas()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						
						int cantidadEliminada = this.reservaController.eliminar(id);
						
						JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else {
			if(tieneFilaElegidaHuespedes()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Integer id = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				
				int cantidadEliminada = this.huespedController.eliminar(id);
				
				JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}

	private void cargarTabla() {		
		if (panel.getSelectedIndex() == 0) {
			var reservas = this.reservaController.listar();
			
			llenarReserva(reservas);
		} else {
			var huespedes = this.huespedController.listar();
			
			llenarHuesped(huespedes);
		}
	}

	//funciones del form
	
	private void limpiarTablas() {
		modelo.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
	}
	
	private void llenarReserva(List<Reservas> reservas) {
		reservas.forEach(reserva -> modelo.addRow(
				new Object[] {
						reserva.getId(),
						reserva.getCheckIn(),
						reserva.getCheckOut(),
						reserva.getValor(),
						reserva.getFormaPago()
				}));
	}
	
	private void llenarHuesped(List<Huespedes> huespedes) {
		huespedes.forEach(huesped -> modeloHuesped.addRow(
				new Object[] {
						huesped.getId(),
						huesped.getNombre(),
						huesped.getApellido(),
						huesped.getFechaDeNacimiento(),
						huesped.getNacionalidad(),
						huesped.getTelefono(),
						huesped.getIdReserva()
				}));
	}
	
	private boolean tieneFilaElegidaHuespedes() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	
	private boolean tieneFilaElegidaReservas() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}
	

	private Long parseDateLong(String date) {
		java.util.Date checkInUtil;
		try {
			checkInUtil = formatoFecha.parse(date);
			return checkInUtil.getTime();
		} catch (ParseException e) {
			throw new RuntimeException();
		}
		
	}
}
