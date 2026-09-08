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
