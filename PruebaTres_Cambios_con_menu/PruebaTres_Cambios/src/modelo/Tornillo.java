package modelo;

public class Tornillo extends Producto {
    private double diametro;
    private double largo;
    private String tipoCabeza;

    public Tornillo() {
        super();
    }

    
    // constructor sin id (la base de datos la genera)

    public Tornillo(double diametro, double largo, String tipoCabeza, String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        super(nombre, material, marca, precio, stock, tipo);
        this.diametro = diametro;
        this.largo = largo;
        this.tipoCabeza = tipoCabeza;
    }
    
    // Constructor completo

    public Tornillo(double diametro, double largo, String tipoCabeza, int idProducto, String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        super(idProducto, nombre, material, marca, precio, stock, tipo);
        this.diametro = diametro;
        this.largo = largo;
        this.tipoCabeza = tipoCabeza;
    }

    //Getters y setters
    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public String getTipoCabeza() {
        return tipoCabeza;
    }

    public void setTipoCabeza(String tipoCabeza) {
        this.tipoCabeza = tipoCabeza;
    }

    
}