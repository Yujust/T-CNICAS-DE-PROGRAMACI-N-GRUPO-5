package arreglo;

import java.util.ArrayList;

public class ArregloProducto {
    
    private ArrayList<Producto> lista;

    public ArregloProducto() {
        lista = new ArrayList<>();
    }

    public void adicionar(Producto p) {
        lista.add(p);
    }

    public Producto buscar(int c) {
        for (Producto p : lista) {
            if (p.getCodigo() == c) {
                return p;
            }
        }
        return null;
    }
    public boolean eliminar(int codigo) {

        Producto producto = buscar(codigo);

        if (producto != null) {
            lista.remove(producto);
            return true;
        }

        return false;
    }
    public boolean modificar(int codigo, String nuevoNombre, int nuevoStock) {

        Producto producto = buscar(codigo);

        if (producto != null) {
            producto.setNombre(nuevoNombre);
            producto.setStock(nuevoStock);
            return true;
        }

        return false;
    }

    public ArrayList<Producto> obtenerTodo() {
        return lista;
    }

    public int tamaño() {
        return lista.size();
    }

    public Producto obtener(int i) {
        return lista.get(i);
    }
}
