/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.PlacaDAO;
import controlador.ProductoDAO;
import controlador.TornilloDAO;
import javax.swing.JOptionPane;
import modelo.*;


public class VentanaIngresar extends javax.swing.JFrame {


    public VentanaIngresar() {
        initComponents();
        
        lbl_especifico.setVisible(false);
        lbl_especifico2.setVisible(false);
        lbl_especifico3.setVisible(false);
        
        btn_ingresar.setVisible(false);
        
        txt_especialidad.setVisible(false);
        txt_especialidad2.setVisible(false);
        txt_especialidad3.setVisible(false);
        
        this.setSize(600, 500);
        
        buttonGroup_tipoProd.add(opt_placa);
        buttonGroup_tipoProd.add(opt_tornillo);
    }
    

    public boolean isInteger(String numero){
        try{
            Integer.parseInt(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


    public String validarString(javax.swing.JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        if (texto.length()==0 ) {
            JOptionPane.showMessageDialog(this, "el campo '" + nombreCampo + "' es obligatorio.");
            campo.requestFocus();
            return null;
        }
        return texto;
    }


    public int validarInt(javax.swing.JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        
        
        if (!isInteger(texto)) {
            JOptionPane.showMessageDialog(this, "el campo '" + nombreCampo + "' debe ser un número entero válido.");
            campo.requestFocus();
            return -1;
        }
        
        
        int valor = Integer.parseInt(texto);
        if (valor < 0 || valor > 30000) {
            JOptionPane.showMessageDialog(this, "el campo '" + nombreCampo + "' debe estar entre un rango de 0 a 30000.");
            campo.requestFocus();
            return -1;
        }
        return valor;
    }


    public double validarDouble(javax.swing.JTextField campo, String nombreCampo) {
        String texto = campo.getText().trim();
        double valor = 0;
        boolean esDecimal = true;
        
        try {
            valor = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            esDecimal = false;
        }
        
        if (!esDecimal) {
             JOptionPane.showMessageDialog(this, "el campo '" + nombreCampo + "' debe ser un número decimal (ej. 10.5).");
             campo.requestFocus();
             return -1;
        }
        
        if (valor < 0 || valor > 50) {
            JOptionPane.showMessageDialog(this, "el campo '" + nombreCampo + " tiene que ser entre 0.0 a 50.0 (ej 10.5)");
            campo.requestFocus();
            return -1;
        }
        return valor;
    }
    
    public void LimpiarCampos(){
        txt_nombre.setText(null);
        txt_material.setText(null);
        txt_marca.setText(null);
        txt_precio.setText(null);
        txt_stock.setText(null);
        txt_especialidad.setText(null);
        txt_especialidad2.setText(null);
        txt_especialidad3.setText(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_tipoProd = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_material = new javax.swing.JTextField();
        txt_marca = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_stock = new javax.swing.JTextField();
        btn_volver = new javax.swing.JButton();
        lbl_especifico = new javax.swing.JLabel();
        lbl_especifico2 = new javax.swing.JLabel();
        lbl_especifico3 = new javax.swing.JLabel();
        txt_especialidad = new javax.swing.JTextField();
        txt_especialidad2 = new javax.swing.JTextField();
        txt_especialidad3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_ingresar = new javax.swing.JButton();
        opt_tornillo = new javax.swing.JRadioButton();
        opt_placa = new javax.swing.JRadioButton();
        btn_limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Ingrese datos generales del producto");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel3.setText("Material:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel4.setText("Marca:");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel5.setText("Cantidad de Stock:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel6.setText("Precio:");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });

        btn_volver.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        lbl_especifico.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        lbl_especifico.setText("jLabel7");

        lbl_especifico2.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        lbl_especifico2.setText("jLabel8");

        lbl_especifico3.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        lbl_especifico3.setText("jLabel9");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 26)); // NOI18N
        jLabel7.setText("¿Que tipo de producto desea ingresar?");

        btn_ingresar.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        btn_ingresar.setText("Ingresar");
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });

        opt_tornillo.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        opt_tornillo.setText("Tornillo");
        opt_tornillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt_tornilloActionPerformed(evt);
            }
        });

        opt_placa.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        opt_placa.setText("Placa");
        opt_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt_placaActionPerformed(evt);
            }
        });

        btn_limpiar.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_especifico2)
                            .addComponent(lbl_especifico)
                            .addComponent(lbl_especifico3))
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(opt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_stock, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txt_material)
                            .addComponent(txt_marca)
                            .addComponent(txt_especialidad)
                            .addComponent(txt_especialidad2)
                            .addComponent(txt_especialidad3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(opt_tornillo)
                        .addGap(140, 140, 140))))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_volver))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opt_tornillo)
                    .addComponent(opt_placa))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_especifico))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_especifico2)
                    .addComponent(txt_especialidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_especialidad3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_especifico3))
                .addGap(26, 26, 26)
                .addComponent(btn_ingresar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
       dispose();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
       
        // Inicialización de variables
        String nombre = null, material = null, marca = null, tipoCabeza = null;
        int precio = 0, stock = 0, cantAgujeros = 0;
        double medida = 0, diametro = 0, largo = 0;
        int idTipo = 0; 
        
        // Bandera SEMÁFORO: Si se pone en false, deja de validar lo siguiente.
        boolean datosValidos = true;

        // --- VALIDACIÓN SECUENCIAL ---
        
        // 1. Validar Textos Generales
        if (datosValidos) {
            nombre = validarString(txt_nombre, "Nombre");
            if (nombre == null) datosValidos = false;
        }
        
        if (datosValidos) {
            material = validarString(txt_material, "Material");
            if (material == null) datosValidos = false;
        }

        if (datosValidos) {
            marca = validarString(txt_marca, "Marca");
            if (marca == null) datosValidos = false;
        }

        // 2. Validar Enteros (Precio y Stock)
        if (datosValidos) {
            precio = validarInt(txt_precio, "Precio");
            if (precio == -1) datosValidos = false;
        }

        if (datosValidos) {
            stock = validarInt(txt_stock, "Stock");
            if (stock == -1) datosValidos = false;
        }

        // 3. Validar Específicos (Placa o Tornillo)
        if (datosValidos) {
            if (opt_placa.isSelected()) {
                idTipo = 1; 
                
                // Medida
                medida = validarDouble(txt_especialidad, "Medida");
                if (medida == -1) datosValidos = false;
                
                // Agujeros (solo si medida pasó)
                if (datosValidos) {
                    cantAgujeros = validarInt(txt_especialidad2, "Cantidad Agujeros");
                    if (cantAgujeros == -1) datosValidos = false;
                }
                
            } else if (opt_tornillo.isSelected()) {
                idTipo = 2;
                
                // Diámetro
                diametro = validarDouble(txt_especialidad, "Diámetro");
                if (diametro == -1) datosValidos = false;
                
                // Largo
                if (datosValidos) {
                    largo = validarDouble(txt_especialidad2, "Largo");
                    if (largo == -1) datosValidos = false;
                }
                
                // Tipo Cabeza
                if (datosValidos) {
                    tipoCabeza = validarString(txt_especialidad3, "Tipo de Cabeza");
                    if (tipoCabeza == null) datosValidos = false;
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de producto.");
                datosValidos = false;
            }
        }

        // --- GUARDADO EN BD ---
        // Solo llegamos aquí si NUNCA se puso datosValidos = false
        if (datosValidos) {
            ProductoDAO prodDAO = new ProductoDAO();
            PlacaDAO plaDAO = new PlacaDAO();
            TornilloDAO torniDAO = new TornilloDAO();

            boolean ingresoMadre = prodDAO.insertarMadre(nombre, material, marca, precio, stock, idTipo);

            if (ingresoMadre) {
                boolean ingresoHija = false;

                if (idTipo == 1) {
                    ingresoHija = plaDAO.ingresarPlaca(medida, cantAgujeros);
                } else {
                    ingresoHija = torniDAO.ingresarTornillo(diametro, largo, tipoCabeza);
                }
                
                if (ingresoHija) {
                    JOptionPane.showMessageDialog(this, "Producto registrado EXITOSAMENTE.");
                    LimpiarCampos();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar detalle específico.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar producto general.");
            }
        }
       
                
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void opt_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt_placaActionPerformed
        this.setSize(600, 700);
        txt_especialidad.setVisible(true);
        txt_especialidad2.setVisible(true);
        btn_ingresar.setVisible(true);
        
        lbl_especifico.setText("Medida: ");
        lbl_especifico2.setText("Cantidad Agujeros: ");

        lbl_especifico.setVisible(true);
        lbl_especifico2.setVisible(true);
        
        txt_especialidad3.setVisible(false);
        lbl_especifico3.setVisible(false);
    }//GEN-LAST:event_opt_placaActionPerformed

    private void opt_tornilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt_tornilloActionPerformed
        this.setSize(600, 700);
        txt_especialidad.setVisible(true);
        txt_especialidad2.setVisible(true);
        txt_especialidad3.setVisible(true);
        btn_ingresar.setVisible(true);

        lbl_especifico.setText("Diámetro: ");
        lbl_especifico2.setText("Largo: ");
        lbl_especifico3.setText("Tipo de cabeza: ");

        lbl_especifico.setVisible(true);
        lbl_especifico2.setVisible(true);
        lbl_especifico3.setVisible(true);
    }//GEN-LAST:event_opt_tornilloActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_btn_limpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_volver;
    private javax.swing.ButtonGroup buttonGroup_tipoProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_especifico;
    private javax.swing.JLabel lbl_especifico2;
    private javax.swing.JLabel lbl_especifico3;
    private javax.swing.JRadioButton opt_placa;
    private javax.swing.JRadioButton opt_tornillo;
    private javax.swing.JTextField txt_especialidad;
    private javax.swing.JTextField txt_especialidad2;
    private javax.swing.JTextField txt_especialidad3;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_material;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
