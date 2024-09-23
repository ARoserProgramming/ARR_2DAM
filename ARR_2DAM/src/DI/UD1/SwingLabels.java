package UD1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class labels extends JFrame {
    public labels(){
        setTitle("Ejemplo de JLablel");
        setSize(300,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        //cambio el layout por defecto del panel
        panel.setLayout(new GridLayout(3,1));
        //creo tres etiquetas
        JLabel label1 = new JLabel("Primera etiqueta"); //justificación izquierda por defecto
        JLabel label2 = new JLabel("Segunda etiqueta",JLabel.CENTER); //centrado
        JLabel label3 = new JLabel("Tercera etiqueta",JLabel.RIGHT); //derecha
//las añado al panel
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        add(panel);
    }

    public static void main(String[] args) {
        labels frame = new labels();
        frame.setVisible(true);

    }
}