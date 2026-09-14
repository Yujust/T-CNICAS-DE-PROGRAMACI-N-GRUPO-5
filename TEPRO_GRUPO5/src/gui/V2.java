package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arreglo.ArregloProducto;
import arreglo.Producto;

import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;

public class V2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtProo;
	private JTextField txtPro;
	private JTextField txtCan;
	private JTextField txtCo;
	private JButton btnAdicionar;
	private JButton btnBuscar;
	private JButton btnReportar;
	private JButton btnEliminar;
	private JButton btnModificar;
	private ArregloProducto ap = new ArregloProducto();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V2 frame = new V2();
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
	public V2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 137, 416, 116);
			contentPane.add(scrollPane);
			{
				txtS = new JTextArea();
				scrollPane.setViewportView(txtS);
			}
		}
		{
			lblNewLabel = new JLabel("Proveedor:");
			lblNewLabel.setBounds(10, 10, 80, 12);
			contentPane.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Producto:");
			lblNewLabel_1.setBounds(109, 10, 75, 12);
			contentPane.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("Cantidad:");
			lblNewLabel_2.setBounds(211, 10, 90, 12);
			contentPane.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Código producto:");
			lblNewLabel_3.setBounds(311, 10, 108, 12);
			contentPane.add(lblNewLabel_3);
		}
		{
			txtProo = new JTextField();
			txtProo.setBounds(10, 32, 96, 18);
			contentPane.add(txtProo);
			txtProo.setColumns(10);
		}
		{
			txtPro = new JTextField();
			txtPro.setBounds(109, 32, 96, 18);
			contentPane.add(txtPro);
			txtPro.setColumns(10);
		}
		{
			txtCan = new JTextField();
			txtCan.setBounds(211, 32, 96, 18);
			contentPane.add(txtCan);
			txtCan.setColumns(10);
		}
		{
			txtCo = new JTextField();
			txtCo.setBounds(311, 32, 96, 18);
			contentPane.add(txtCo);
			txtCo.setColumns(10);
		}
		{
			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.addActionListener(this);
			btnAdicionar.setBounds(194, 60, 100, 20);
			contentPane.add(btnAdicionar);
		}
		{
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(100, 60, 84, 20);
			contentPane.add(btnBuscar);
		}
		{
			btnReportar = new JButton("Reportar");
			btnReportar.addActionListener(this);
			btnReportar.setBounds(10, 60, 84, 20);
			contentPane.add(btnReportar);
		}
		{
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(304, 60, 84, 20);
			contentPane.add(btnEliminar);
		}
		{
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(10, 90, 100, 20);
			contentPane.add(btnModificar);
		}

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnReportar) {
			do_btnReportar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnAdicionar) {
			do_btnAdicionar_actionPerformed(e);
		}
	}
	protected void do_btnReportar_actionPerformed(ActionEvent e) {
		if (ap.tamaño() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados en el sistema.");
            txtS.setText("");
            return;
        }

        txtS.setText("==============================================\n");
        txtS.append("          REPORTE GENERAL DE PRODUCTOS        \n");
        txtS.append("==============================================\n\n");

        for (int i = 0; i < ap.tamaño(); i++) {
            Producto p = ap.obtener(i);
            txtS.append("Producto #" + (i + 1) + "\n");
            txtS.append("  * Código: " + p.getCodigo() + "\n");
            txtS.append("  * Nombre: " + p.getNombre() + "\n");
            txtS.append("  * Stock/Cantidad: " + p.getStock() + "\n");
            txtS.append("----------------------------------------------\n");
        }

        txtS.append("\nTotal de productos registrados: " + ap.tamaño() + "\n");
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		try {
            int codigoABuscar = Integer.parseInt(txtCo.getText().trim());
            Producto p = ap.buscar(codigoABuscar);

            if (p != null) {
                txtPro.setText(p.getNombre());
                txtCan.setText(String.valueOf(p.getStock()));

                txtS.setText("--- PRODUCTO ENCONTRADO ---\n");
                txtS.append("Código: " + p.getCodigo() + "\n");
                txtS.append("Nombre: " + p.getNombre() + "\n");
                txtS.append("Stock/Cantidad: " + p.getStock() + "\n");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el código: " + codigoABuscar);
                txtS.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Escriba un número de código en la casilla 'Codigo producto:'.");
        }
	}
	protected void do_btnAdicionar_actionPerformed(ActionEvent e) {
		try {
            String nombre = txtPro.getText().trim();
            int cantidad = Integer.parseInt(txtCan.getText().trim());

            int codigoAleatorio = ThreadLocalRandom.current().nextInt(100, 1000);

            Producto nuevo = new Producto(codigoAleatorio, nombre, 0.0, cantidad);
            ap.adicionar(nuevo);

            JOptionPane.showMessageDialog(null, "Producto registrado correctamente.\nCódigo asignado: " + codigoAleatorio);
            
            txtS.setText("--- ÚLTIMO REGISTRO ---\n");
            txtS.append("Código asignado: " + codigoAleatorio + "\n");
            txtS.append("Producto: " + nombre + "\n");
            txtS.append("Cantidad: " + cantidad + "\n");

            limpiar();
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos en Producto y Cantidad.");
        }
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
		    int codigo = Integer.parseInt(txtCo.getText().trim());

		    Producto producto = ap.buscar(codigo);

		    if (producto == null) {
		        JOptionPane.showMessageDialog(
		            this,
		            "No existe un producto con el código " + codigo
		        );
		        return;
		    }

		    int opcion = JOptionPane.showConfirmDialog(
		        this,
		        "¿Desea eliminar el producto " + producto.getNombre() + "?",
		        "Confirmar eliminación",
		        JOptionPane.YES_NO_OPTION
		    );

		    if (opcion == JOptionPane.YES_OPTION) {

		        ap.eliminar(codigo);

		        JOptionPane.showMessageDialog(
		            this,
		            "Producto eliminado correctamente."
		        );

		        txtS.setText("--- PRODUCTO ELIMINADO ---\n");
		        txtS.append("Código: " + codigo + "\n");
		        txtS.append("Producto: " + producto.getNombre() + "\n");

		        limpiar();
		    }

		} catch (NumberFormatException ex) {

		    JOptionPane.showMessageDialog(
		        this,
		        "Ingrese un código válido."
		    );
		}
		
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		try {
		    int codigo = Integer.parseInt(txtCo.getText().trim());
		    String nombre = txtPro.getText().trim();
		    int cantidad = Integer.parseInt(txtCan.getText().trim());

		    if (nombre.isEmpty()) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Ingrese el nombre del producto."
		        );
		        return;
		    }

		    boolean modificado = ap.modificar(codigo, nombre, cantidad);

		    if (modificado) {

		        JOptionPane.showMessageDialog(
		            this,
		            "Producto modificado correctamente."
		        );

		        txtS.setText("--- PRODUCTO MODIFICADO ---\n");
		        txtS.append("Código: " + codigo + "\n");
		        txtS.append("Producto: " + nombre + "\n");
		        txtS.append("Cantidad: " + cantidad + "\n");

		    } else {

		        JOptionPane.showMessageDialog(
		            this,
		            "No existe un producto con el código " + codigo
		        );
		    }

		} catch (NumberFormatException ex) {

		    JOptionPane.showMessageDialog(
		        this,
		        "Ingrese un código y una cantidad válidos."
		    );
		}
		
	}
    private void limpiar() {
        txtProo.setText("");
        txtPro.setText("");
        txtCan.setText("");
        txtCo.setText("");
    }
}
