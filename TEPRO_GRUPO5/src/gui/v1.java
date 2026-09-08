package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import arreglo.ArregloProducto;
import arreglo.Producto;

public class v1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtProo;
    private JTextField txtPro;
    private JTextField txtCan;
    private JTextField txtCo;
    private JTextArea txtS;
    private ArregloProducto ap = new ArregloProducto();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    v1 frame = new v1();
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
    public v1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 610, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- LABELS ---
        JLabel lblNewLabel = new JLabel("Proveedor de producto:");
        lblNewLabel.setBounds(20, 10, 142, 12);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Producto:");
        lblNewLabel_1.setBounds(185, 10, 75, 12);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Cantidad:");
        lblNewLabel_1_1.setBounds(330, 10, 75, 12);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_2 = new JLabel("Codigo producto:");
        lblNewLabel_2.setBounds(476, 10, 110, 12);
        contentPane.add(lblNewLabel_2);

        // --- TEXT FIELDS ---
        txtProo = new JTextField();
        txtProo.setBounds(20, 32, 96, 18);
        contentPane.add(txtProo);
        txtProo.setColumns(10);

        txtPro = new JTextField();
        txtPro.setColumns(10);
        txtPro.setBounds(185, 32, 96, 18);
        contentPane.add(txtPro);

        txtCan = new JTextField();
        txtCan.setColumns(10);
        txtCan.setBounds(330, 32, 96, 18);
        contentPane.add(txtCan);

        txtCo = new JTextField();
        txtCo.setColumns(10);
        txtCo.setBounds(476, 32, 96, 18);
        contentPane.add(txtCo);

        // --- AREA DE TEXTO (Se asigna la variable global ANTES de los botones) ---
        txtS = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtS);
        scrollPane.setBounds(10, 100, 562, 160);
        contentPane.add(scrollPane);

        // --- BOTONES ---
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnAgregar.setBounds(358, 60, 104, 20);
        contentPane.add(btnAgregar);

        JButton btnReportar = new JButton("Reportar");
        btnReportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        });
        btnReportar.setBounds(244, 60, 104, 20);
        contentPane.add(btnReportar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        });
        btnBuscar.setBounds(130, 60, 104, 20);
        contentPane.add(btnBuscar);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        });
        btnAdicionar.setBounds(12, 60, 104, 20);
        contentPane.add(btnAdicionar);
    }

    private void limpiar() {
        txtProo.setText("");
        txtPro.setText("");
        txtCan.setText("");
        txtCo.setText("");
    }
}

