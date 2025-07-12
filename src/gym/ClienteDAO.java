package gym;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();
    private int nextId = 0;

    public void agregarCliente(String nombre, int edad, String membresia) {
        clientes.add(new Cliente(nextId++, nombre, edad, membresia));
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }

    public void actualizarCliente(int id, String nombre, int edad, String membresia) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                c.setNombre(nombre);
                c.setEdad(edad);
                c.setMembresia(membresia);
                break;
            }
        }
    }

    public void eliminarCliente(int id) {
        clientes.removeIf(c -> c.getId() == id);
    }
}
