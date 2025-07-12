package gym;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GimnasioApp extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que no ponga advertencias
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	//Tabla
    private JTable tablaClientes;
    private DefaultTableModel modelo;
    
    //Campos de texto para ingresar los datos
    private JTextField tfNombre = new JTextField();
    private JTextField tfEdad = new JTextField();
    private JTextField tfMembresia = new JTextField();
    
    //Construimos la ventana
    public GimnasioApp() {
        setTitle("Gestión de Gimnasio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        

        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Edad", "Membresía"}, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 3094972200075723961L;

			@Override
			//Agregamos esta linea para evitar editar las celdas a mano y que solo se haga mediante los formularios y botones
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda editable
            }
        };
        tablaClientes = new JTable(modelo);
        //Permitimos el scroll si se desborda o sea hay un overflow de filas en la tabla
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        JPanel formulario = new JPanel(new GridLayout(4, 2));
        formulario.add(new JLabel("Nombre:"));
        formulario.add(tfNombre);
        formulario.add(new JLabel("Edad:"));
        formulario.add(tfEdad);
        formulario.add(new JLabel("Membresía:"));
        formulario.add(tfMembresia);
        
        //Botones
        JButton botonAgregar = new JButton("Agregar");
        JButton botonActualizar = new JButton("Actualizar");
        JButton botonEliminar = new JButton("Eliminar");

        formulario.add(botonAgregar);
        formulario.add(botonActualizar);

        add(formulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botonEliminar, BorderLayout.SOUTH);

        // Acciones
        botonAgregar.addActionListener(e -> {
            try {
                String nombre = tfNombre.getText();
                int edad = Integer.parseInt(tfEdad.getText());
                String membresia = tfMembresia.getText();

                clienteDAO.agregarCliente(nombre, edad, membresia);
                actualizarTabla();
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar cliente");
            }
        });

        botonActualizar.addActionListener(e -> {
            int fila = tablaClientes.getSelectedRow();
            if (fila != -1) {
                try {
                    int id = (int) modelo.getValueAt(fila, 0);
                    String nombre = tfNombre.getText();
                    int edad = Integer.parseInt(tfEdad.getText());
                    String membresia = tfMembresia.getText();

                    clienteDAO.actualizarCliente(id, nombre, edad, membresia);
                    actualizarTabla();
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar cliente");
                }
            }
        });

        botonEliminar.addActionListener(e -> {
            int fila = tablaClientes.getSelectedRow();
            if (fila != -1) {
                int id = (int) modelo.getValueAt(fila, 0);
                clienteDAO.eliminarCliente(id);
                actualizarTabla();
                limpiarCampos();
            }
        });

        tablaClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaClientes.getSelectedRow();
                if (fila != -1) {
                    tfNombre.setText(modelo.getValueAt(fila, 1).toString());
                    tfEdad.setText(modelo.getValueAt(fila, 2).toString());
                    tfMembresia.setText(modelo.getValueAt(fila, 3).toString());
                }
            }
        });

        setVisible(true);
    }

    private void actualizarTabla() {
        modelo.setRowCount(0); 
        for (Cliente c : clienteDAO.obtenerClientes()) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getEdad(), c.getMembresia()});
        }
    }

    private void limpiarCampos() {
        tfNombre.setText("");
        tfEdad.setText("");
        tfMembresia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GimnasioApp());
    }
}
