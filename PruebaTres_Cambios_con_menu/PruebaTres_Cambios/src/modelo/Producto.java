package modelo;

public class Producto {
    private int idProducto;
    private String nombre;
    private String material;
    private String marca;
    private int precio;
    private int stock; 
    private TipoProducto tipo; // Colaboración

    public Producto() {
        this.tipo = new TipoProducto();
    }

    
    // constructor sin id (la base de datos la genera)

    public Producto(String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        this.nombre = nombre;
        this.material = material;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
    }

    public Producto(int idProducto, String nombre, String material, String marca, int precio, int stock, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.material = material;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
    }

    // getters and setters
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
 
}// fin clase
