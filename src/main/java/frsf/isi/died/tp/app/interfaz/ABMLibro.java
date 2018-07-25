package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import frsf.isi.died.tp.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;

public class ABMLibro {
	
	public static void agregarLibro(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel errorID = new JLabel(), errorTitulo = new JLabel(), errorCosto = new JLabel(),
				errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel(), encabezado = new JLabel("Agregar Nuevo Libro");
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
					tPrecioCompra = new JTextField(20), tPaginas = new JTextField(20), tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
		
		
		
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=4;
		constraints.anchor=GridBagConstraints.NORTH;
		encabezado.setFont(new Font(encabezado.getFont().getName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado,constraints);
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.CENTER;
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("T�tulo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Precio de Compra: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tPrecioCompra, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("N� P�ginas: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicaci�n: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar, constraints);
				
		constraints.gridx=3;
		constraints.gridy=7;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		aceptar.addActionListener(e -> {
			Integer id;
			String titulo;
			Double costo = 0.0, precioCompra = 0.0;
			Integer paginas = 0;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			
			errorID.setText("");
			errorTitulo.setText("");
			errorCosto.setText("");
			errorPrecio.setText("");
			errorPaginas.setText("");
			errorFecha.setText("");
			try {
				if(tID.getText().isEmpty()){
					System.out.println("El ID no puede ser vacio");
					errorID.setText("Debe ingresar un ID");
					return;
				}else {
					id = Integer.parseInt(tID.getText());
				}
				if(tTitulo.getText().isEmpty()) {
					System.out.println("El t�tulo no puede ser vac�o");
					errorTitulo.setText("Debe ingresar un t�tulo");
					return;
				}else {
					titulo = tTitulo.getText();
				}
				if(tCosto.getText().isEmpty()) {
					errorCosto.setText("Debe ingresar un costo");
					return;
				}else {
					costo = Double.parseDouble(tCosto.getText());
				}
				if(tPrecioCompra.getText().isEmpty()) {
					errorPrecio.setText("Debe ingresar un precio de compra");
					return;
				}else{
					precioCompra = Double.parseDouble(tPrecioCompra.getText());
				}
				if(tPaginas.getText().isEmpty()) {
					errorPaginas.setText("Debe ingresar una cantidad de p�ginas");
					return;
				}else{
					paginas = Integer.parseInt(tPaginas.getText());
				}
				if(tFecha.getText().isEmpty()) {
					errorFecha.setText("Debe ingresar una fecha de publicaci�n");
					return;
				}else{
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
					if((fechaPublicacion.getTime() - Calendar.getInstance().getTime().getTime()) > 0) {
						System.out.println("Puso una fecha futura");
						Principal.mostrarDialogo("La fecha ingresada es futura", ventana);
						return;
					}
				}
				confrimacion(ventana);
				
			}catch(NumberFormatException nfex) {
				System.out.println("Puso otra cosa en un campo num�rico");
				Principal.mostrarDialogo("Los Campos ID, Costo, Precio Compra y N� P�ginas solo permiten n�meros", ventana);
				//nfex.printStackTrace();
			}catch(ParseException pex) {
				System.out.println("La fecha est� mal escrita");
				Principal.mostrarDialogo("La fecha debe ser escrita con formato dd/mm/aaaa", ventana);
			}
			
		});
		panel.add(aceptar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);

		constraints.gridy=2;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=3;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=4;
		errorPrecio.setPreferredSize(new Dimension(230, 16));
		errorPrecio.setForeground(Color.red);
		panel.add(errorPrecio,constraints);
		
		constraints.gridy=5;
		errorPaginas.setPreferredSize(new Dimension(230, 16));
		errorPaginas.setForeground(Color.red);
		panel.add(errorPaginas,constraints);
		
		constraints.gridy=6;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}
	
	private static void confrimacion(JFrame ventana) {
		JDialog confirmacion = new JDialog();
//		Boolean respuesta;
		JLabel texto = new JLabel("�Est� seguro que desea agregar un nuevo libro con los datos ingresados?");
		JButton si = new JButton("S�"), no = new JButton("No");
		
		confirmacion.setLayout(new BorderLayout(5,5));
		confirmacion.add(texto,BorderLayout.NORTH);
		confirmacion.setTitle("Confirmar cambios");
		confirmacion.add(si,BorderLayout.EAST);
		confirmacion.add(no,BorderLayout.WEST);
		
		
		confirmacion.pack();
		confirmacion.setLocationRelativeTo(ventana);
		confirmacion.setVisible(true);
//		return respuesta;
	}

	public static void editarLibro(JFrame ventana) {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar Libro"), errorID = new JLabel();
		JTextField tID = new JTextField(20);
		JButton buscar = new JButton("Buscar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.anchor=GridBagConstraints.CENTER;
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("Ingrese el ID de libro a editar: "), constraints);
		
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		panel.add(tID,constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar,constraints);
		
		constraints.gridx=3;
		buscar.addActionListener(a -> {
			try {
				if(tID.getText().isEmpty()) {
					errorID.setText("Debe ingresar un ID");
				}else {
					//buscar libro con id Integer.parseInt(tID.getText());
					//creo un nuevo libro para simular la busqueda
					Libro nuevo = LibroController.buscarLibro(Integer.parseInt(tID.getText()));
					System.out.println("Libro a editar: "+nuevo);
					edicionLibro(nuevo, ventana);
				}
			}catch(Exception e) {
				
			}
		});
		panel.add(buscar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		ventana.setVisible(true);
	}

	private static void edicionLibro(Libro libro, JFrame ventana) {
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar Libro"), errorID = new JLabel(), errorTitulo = new JLabel(),
				errorCosto = new JLabel(), errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
				tPrecio = new JTextField(20), tPaginas = new JTextField(20), tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("T�tulo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Precio de Compra: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tPrecio, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("N� P�ginas: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicaci�n: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=7;
		constraints.fill=GridBagConstraints.NONE;
		aceptar.addActionListener(a -> {
			System.out.println("Pide confrimacion y guarda los cambios.");
		});
		panel.add(aceptar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);

		constraints.gridy=2;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=3;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=4;
		errorPrecio.setPreferredSize(new Dimension(230, 16));
		errorPrecio.setForeground(Color.red);
		panel.add(errorPrecio,constraints);
		
		constraints.gridy=5;
		errorPaginas.setPreferredSize(new Dimension(230, 16));
		errorPaginas.setForeground(Color.red);
		panel.add(errorPaginas,constraints);
		
		constraints.gridy=6;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		//muestro en los campos de texto los datos anteriores
		tID.setText(libro.getId().toString());
		tTitulo.setText(libro.getTitulo());
		tCosto.setText(libro.getCosto().toString());
		tPrecio.setText(libro.getPrecioCompra().toString());
		tPaginas.setText(libro.getPaginas().toString());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(libro.getFechaPublicacion()));
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
	}
	
}
