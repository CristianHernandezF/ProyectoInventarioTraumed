package controlador;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {


    // MÉTODO PARA INSERTAR EN LA TABLA MAS FUERTE -> Recibe DATOS SUELTOS, no objetos para insertarlos en tb_producto 

    public boolean insertarMadre(String nombre, String material, String marca, int precio,int stock, int idTipo) {
        boolean resultado = false;

        try {
            Connection con = Conexion.getConexion();
            String query = "insert into tb_producto (nombre, material, marca, precio,stock, id_tipo) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, nombre);
            ps.setString(2, material);
            ps.setString(3, marca);
            ps.setInt(4, precio);
            ps.setInt(5, stock);
            ps.setInt(6, idTipo);

            resultado = ps.executeUpdate() == 1;
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }//fin metodo

    // MODIFICAR Recibe datos sueltos + ID

    public boolean modificarMadre(String nombre, String material, String marca, int precio,int stock, int id) {
        boolean resultado = false;

        try {
            Connection con = Conexion.getConexion();
            String query = "update tb_producto set nombre=?, material=?, marca=?, precio=?, stock=? where id_producto=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, nombre);
            ps.setString(2, material);
            ps.setString(3, marca);
            ps.setInt(4, precio);
            ps.setInt(5, stock);
            ps.setInt(6, id);

            resultado = ps.executeUpdate() == 1;

            ps.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }//fin metodo

    // Metodo para obtener ultima ID para insertar en la tabla hija cmo FK 

    public int EntregarUltimoCodigo() {
        int ultimoID = -1;
        
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT MAX(id_producto) AS ultimo_id FROM tb_producto";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ultimoID = rs.getInt("ultimo_id");
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ultimoID;
    }//fin metodo
    
    public boolean eliminarProducto(int id) {
        boolean resultado = false;
        try {
            Connection con = Conexion.getConexion();
            // Borro solo de la madre, la base de datos borra al hijo por el DELETE CASCADE
            String query = "delete from tb_producto where id_producto=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            resultado = ps.executeUpdate() == 1;
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public ArrayList<Producto> obtenerTodos() {
        // La lista es genérica para aceptar hijos (Placas y Tornillos)
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        
        try {
            Connection con = Conexion.getConexion();
            
            // Query con JOIN para traer datos de las 3 tablas
            String query = "SELECT p.*, tp.nombre_tipo, pl.medida, pl.cantidad_agujeros, t.diametro, t.largo, t.tipo_cabeza " +
                           "FROM tb_producto p " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo " +
                           "LEFT JOIN tb_placa pl ON p.id_producto = pl.id_producto " +
                           "LEFT JOIN tb_tornillo t ON p.id_producto = t.id_producto";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int tipo = rs.getInt("id_tipo");
                
                // CASO 1  PLACA
                if (tipo == 1) { 

                    TipoProducto tipoObjeto = new TipoProducto(1, "placa");

                    int id_producto = rs.getInt("id_producto"), precio = rs.getInt("precio"), cantAgujeros = rs.getInt("cantidad_agujeros"), stock = rs.getInt("stock");
                    double medida = rs.getDouble("medida") ;
                    String material = rs.getString("material"), marca = rs.getString("marca"), nombre = rs.getString("nombre");
 
                    Placa placa = new Placa(medida, cantAgujeros, id_producto, nombre, material, marca, precio, stock, tipoObjeto);
                    
                    listaProductos.add(placa);
                } 
                
                // CASO 2 TORNILLO
                else if (tipo == 2) { 

                    TipoProducto tipoObjeto = new TipoProducto(2, "tornillo");
                    int id_producto = rs.getInt("id_producto"), precio = rs.getInt("precio"), stock = rs.getInt("stock");
                    String material = rs.getString("material"), marca = rs.getString("marca"), nombre = rs.getString("nombre"), tipoCabeza = rs.getString("tipo_cabeza");
                    Double largo = rs.getDouble("largo"), diametro = rs.getDouble("diametro");
                    
                    Tornillo torni = new Tornillo(diametro, largo, tipoCabeza, id_producto, nombre, material, marca, precio, stock, tipoObjeto);

                    listaProductos.add(torni);
                }
            }
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaProductos;
    } 
 
    
    // metodos q cuentan jeje

    public int contarPorMaterial(String material) {
        int cantidad = 0;
        try {
            Connection con = Conexion.getConexion();
            String query = "select count(*) from tb_producto where material=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, material);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) cantidad = rs.getInt(1);
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }// fin metodo

    public double precioPromedio() {
        double prom = 0;
        try {
            Connection con = Conexion.getConexion();
            String query = "select avg(precio) from tb_producto";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) prom = rs.getDouble(1);
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prom;
    }//fin metodo
}