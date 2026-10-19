package vista;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class PruebaTres2 {

    public static void main(String[] args) {
       
        VentanaPrincipal ventPrin = new VentanaPrincipal();
        ventPrin.setLocationRelativeTo(null);//muestra la ventana centrada en pantalla
        ventPrin.setTitle("Inventario Traumed");//aquí escribo el título de la ventana
        ventPrin.setResizable(false);//se evita que el usuario pueda cambiar el tamaño de la ventana
        ventPrin.setVisible(true);//muestra la ventana
        ventPrin.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
}
