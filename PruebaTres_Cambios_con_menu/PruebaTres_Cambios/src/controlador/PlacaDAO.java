package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Placa;
import modelo.Producto;
import modelo.TipoProducto;



public class PlacaDAO {

    private ProductoDAO prodDAO = new ProductoDAO();

    public PlacaDAO() {
    }
    
    // INGRESAR PLACA -> paso los datos requeridos para insertar la placa a la BD
    
    public boolean ingresarPlaca(double medida, int cantAgujeros) {
                
        boolean exito = false;
       
            try {
                int idGenerado = prodDAO.EntregarUltimoCodigo(); // obtengo la ultima id para insertarla en la FK de placa. 
                Connection con = Conexion.getConexion();
                
                String query = "insert into tb_placa (id_producto, medida, cantidad_agujeros) values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setInt(1, idGenerado);
                ps.setDouble(2, medida);
                ps.setInt(3, cantAgujeros);
                
                exito = ps.executeUpdate() == 1;
                ps.close();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PlacaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
        return exito;
    }//fin metodo
    
        // MODIFICAR PLACA -> primero desarmo la placa y mando 1 x 1 los datos al metodo modificarMadre()


    public boolean modificarPlaca(Placa p) {
         //Pasamos los datos sueltos al metodo de la mama
           boolean mamaModificada = prodDAO.modificarMadre(p.getNombre(), p.getMaterial(), p.getMarca(), p.getPrecio(), p.getStock() , p.getIdProducto());
           boolean exito = false;

        if (mamaModificada) {
            try {
                Connection con = Conexion.getConexion();

                String query = "update tb_placa set medida=?, cantidad_agujeros=? where id_producto=?";

                PreparedStatement ps = con.prepareStatement(query);
                ps.setDouble(1, p.getMedida());
                ps.setInt(2, p.getCantidadAgujeros());
                ps.setInt(3, p.getIdProducto());
                
                exito = ps.executeUpdate() == 1;
                ps.close();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PlacaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;

    }//fin metodo
    
public ArrayList<Placa> obtenerPlacas() {
        ArrayList<Placa> listaPlacas = new ArrayList<>();

        try {
            Connection con = Conexion.getConexion();

            // Usamos INNER JOIN con tb_placa. 
            // Esto hace que SOLO traiga los registros que existen en ambas tablas (descarta tornillos).
            String query = "SELECT p.*, tp.nombre_tipo, pl.medida, pl.cantidad_agujeros " +
                           "FROM tb_producto p " +
                           "JOIN tb_placa pl ON p.id_producto = pl.id_producto " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 // Objeto TipoProducto
                TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), cantAgujeros = rs.getInt("cantidad_agujeros"), stock  = rs.getInt("stock");
                String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = (rs.getString("marca"));
                double medida =rs.getDouble("medida");
                // Datos específicos de la tabla hija
                
                Placa p = new Placa(medida, cantAgujeros, id_prod, nombre, material, marca, precio, stock, tipoProd);
               
                // Objeto TipoProducto

                listaPlacas.add(p);
            }
            ps.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PlacaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaPlacas;
    }

        public ArrayList<Placa> buscarMedidaPlaca(double medidaBuscada) {
        ArrayList<Placa> listaPlacas = new ArrayList<>();

            try {
                Connection con = Conexion.getConexion();

                // Misma consulta anterior pero con WHERE
                String query = "SELECT p.*, tp.nombre_tipo, pl.medida, pl.cantidad_agujeros " +
                               "FROM tb_producto p " +
                               "JOIN tb_placa pl ON p.id_producto = pl.id_producto " +
                               "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo " +
                               "WHERE pl.medida = ?"; // Filtro SQL

                PreparedStatement ps = con.prepareStatement(query);
                ps.setDouble(1, medidaBuscada); // asigno el valor al ?

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                    int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), cantAgujeros = rs.getInt("cantidad_agujeros"), stock = rs.getInt("stock");
                    String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = (rs.getString("marca"));
                    double medida =rs.getDouble("medida");


                    Placa p = new Placa(medida, cantAgujeros, id_prod, nombre, material, marca, precio, stock, tipoProd);

                    listaPlacas.add(p);
                }
                ps.close();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PlacaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        return listaPlacas;
    }
    
    public Placa buscarPlaca(int id) {
        Placa placa = null;
        try {
            Connection con = Conexion.getConexion();
            // Usamos JOIN específico con tb_placa
            String query = "SELECT p.*, tp.nombre_tipo, pl.medida, pl.cantidad_agujeros " +
                           "FROM tb_producto p " +
                           "JOIN tb_placa pl ON p.id_producto = pl.id_producto " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo " +
                           "WHERE p.id_producto = ?";
                           
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), cantAgujeros = rs.getInt("cantidad_agujeros"), stock = (rs.getInt("stock"));
                String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = (rs.getString("marca"));
                double medida =rs.getDouble("medida");

                placa = new Placa(medida, cantAgujeros, id_prod, nombre, material, marca, precio, stock, tipoProd);
            }
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PlacaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return placa;
    }
    
    

}
