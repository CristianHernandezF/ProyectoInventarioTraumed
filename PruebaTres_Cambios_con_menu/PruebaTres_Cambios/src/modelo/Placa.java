package modelo;

public class Placa extends Producto {

    private double medida;
    private int cantidadAgujeros;

    public Placa() {
        super();
    }
   
    // constructor sin id (la base de datos la genera)

    public Placa(double medida, int cantidadAgujeros, String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        super(nombre, material, marca, precio, stock, tipo);
        this.medida = medida;
        this.cantidadAgujeros = cantidadAgujeros;
    }

     // Constructor completo

    public Placa(double medida, int cantidadAgujeros, int idProducto, String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        super(idProducto, nombre, material, marca, precio, stock, tipo);
        this.medida = medida;
        this.cantidadAgujeros = cantidadAgujeros;
    }

    // Getter y Setters
    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }

    public int getCantidadAgujeros() {
        return cantidadAgujeros;
    }

    public void setCantidadAgujeros(int cantidadAgujeros) {
        this.cantidadAgujeros = cantidadAgujeros;
    }

    
}
