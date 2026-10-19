package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tornillo;
import modelo.TipoProducto;

public class TornilloDAO {
    
    private ProductoDAO prodDAO = new ProductoDAO();

    public TornilloDAO() {
    }
    
    // Metodos Tornillos
    
    // MODIFICAR Tornillo -> primero desarmo la placa y mando 1 x 1 los datos al metodo 

    public boolean ingresarTornillo(double diametro, double largo, String tipoCabeza) {
        // el 2 al final es el ID del Tipo para Placa (insertado asi en la bd)
        
        boolean exito = false;

            try {
                int idGenerado = prodDAO.EntregarUltimoCodigo();
                Connection con = Conexion.getConexion();
                
                String query = "insert into tb_tornillo (id_producto, diametro, largo, tipo_cabeza) values(?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setInt(1, idGenerado);
                ps.setDouble(2, diametro);
                ps.setDouble(3, largo);
                ps.setString(4, tipoCabeza);
                
                exito = ps.executeUpdate() == 1;
                ps.close();


            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
        return exito;
    }//fin metodo

    public boolean modificarTornillo(Tornillo t) {
        // Paso datos sueltos al otro metodo
        boolean mamaModificada = prodDAO.modificarMadre(t.getNombre(), t.getMaterial(), t.getMarca(), t.getPrecio(),t.getStock() , t.getIdProducto());

        boolean exito = false;
        if (mamaModificada) {
            try {
                Connection con = Conexion.getConexion();
                String query = "update tb_tornillo set diametro=?, largo=?, tipo_cabeza=? where id_producto=?";
                PreparedStatement ps = con.prepareStatement(query);
                
                ps.setDouble(1, t.getDiametro());
                ps.setDouble(2, t.getLargo());
                ps.setString(3, t.getTipoCabeza());
                ps.setInt(4, t.getIdProducto());
                
                exito = ps.executeUpdate() == 1;
                ps.close();

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }//fin metodo

    // metodos en comun (afectan a los 2 de la misma forma jejejje)

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
    }// fin metodo
    
    public ArrayList<Tornillo> obtenerTornillos() {
        ArrayList<Tornillo> listaTornillos = new ArrayList<>();
        
        try {
            Connection con = Conexion.getConexion();
            
            // JOIN interno: Solo trae registros que coincidan en tb_producto y tb_tornillo
            String query = "SELECT p.*, tp.nombre_tipo, t.diametro, t.largo, t.tipo_cabeza " +
                           "FROM tb_producto p " +
                           "JOIN tb_tornillo t ON p.id_producto = t.id_producto " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), stock = (rs.getInt("stock"));
                String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = rs.getString("marca"), tipoCabeza = rs.getString("tipo_cabeza");
                double diametro = rs.getDouble("diametro"), largo =  rs.getDouble("largo");
                
                Tornillo torni = new Tornillo(diametro, largo, tipoCabeza, id_prod, nombre, material, marca, precio, stock , tipoProd); 
                
                listaTornillos.add(torni);
            }
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaTornillos;
    }
    
    public ArrayList<Tornillo> buscarTipoCabTornillo(String tipoCabeza) {
        ArrayList<Tornillo> listaTipoTornillo = new ArrayList<>();
        
        try {
            Connection con = Conexion.getConexion();
            
            // Misma consulta pero agregamos el filtro WHERE
            String query = "SELECT p.*, tp.nombre_tipo, t.diametro, t.largo, t.tipo_cabeza " +
                           "FROM tb_producto p " +
                           "JOIN tb_tornillo t ON p.id_producto = t.id_producto " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo " +
                           "WHERE t.tipo_cabeza = ?"; // Filtramos por la columna de la tabla tornillo
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tipoCabeza); // Pasamos el parámetro
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), stock = (rs.getInt("stock"));
                String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = rs.getString("marca");
                double diametro = rs.getDouble("diametro"), largo =  rs.getDouble("largo");
                
                Tornillo torni = new Tornillo(diametro, largo, tipoCabeza, id_prod, nombre, material, marca, precio, stock , tipoProd); 
                
                listaTipoTornillo.add(torni);

                
            }
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaTipoTornillo;
    } 

    public Tornillo buscarTornillo(int id) {
        Tornillo tornillo = null;
        try {
            Connection con = Conexion.getConexion();
            // Usamos JOIN específico con tb_tornillo
            String query = "SELECT p.*, tp.nombre_tipo, t.diametro, t.largo, t.tipo_cabeza " +
                           "FROM tb_producto p " +
                           "JOIN tb_tornillo t ON p.id_producto = t.id_producto " +
                           "JOIN tb_tipo_producto tp ON p.id_tipo = tp.id_tipo " +
                           "WHERE p.id_producto = ?";
                           
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                TipoProducto tipoProd = new TipoProducto(rs.getInt("id_tipo"), rs.getString("nombre_tipo"));
                int precio = (rs.getInt("precio")), id_prod = rs.getInt("id_producto"), stock = (rs.getInt("stock"));
                String nombre = rs.getString("nombre"), material = rs.getString("material"), marca = rs.getString("marca"), tipoCabeza = rs.getString("tipo_cabeza");
                double diametro = rs.getDouble("diametro"), largo =  rs.getDouble("largo");
                
                tornillo = new Tornillo(diametro, largo, tipoCabeza, id_prod, nombre, material, marca, precio, stock , tipoProd); 
            }
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TornilloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tornillo;
    }
    
}


