package UD1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Frame extends JFrame {
    // Declarar el panel a nivel de la clase
    private JPanel panel;

    public Frame() {
        setTitle("Hola!!!"); // Título de la ventana
        setSize(300, 200); // Tamaño
        // Para que se cierre la ventana cuando se pulse el icono de cerrar
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Inicializar el panel
        panel = new JPanel();
        // Añadir el panel al frame
        add(panel);
        // Poner el color de fondo del panel en rojo
        panel.setBackground(Color.red);

        // Instanciar un botón
        /*JButton boton = new JButton("Texto va aquí");
        boton.addActionListener(new EscuchadorBoton());*/
        // Establecer las dimensiones del botón
       /* Dimension d = new Dimension(100, 40); // También se puede usar new Dimension(100, 40) directamente
        boton.setPreferredSize(d);*/
        // Añadir el botón al panel
        // Comentado para claridad panel.add(boton);
        //creo un flowlayout
       /* FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 5, 10);
        JPanel panel = new JPanel();
        add(panel);
        //añado el layout al panel
        panel.setLayout(fl);
        //para ver el efecto creo 4 botones y los añado al frame
        for (int i=0; i<4; i++) {
            JButton button = new JButton("Button"+(i+1));
            button.setPreferredSize(new Dimension(100,25));
            panel.add(button);
        }*/
        //creo un gridlayout
        GridLayout grid = new GridLayout(3,2, 5,5);
        /*panel.setLayout(grid);
        //para ver el efecto creo 6 botones y los añado al frame
        for (int i=0; i<6; i++) {
            JButton button = new JButton("Button"+(i+1));
            button.setPreferredSize(new Dimension(100,25));
            panel.add(button);
        }*/
        panel.setLayout(new BorderLayout());
        JButton azul = new JButton("Azul");
        Dimension d = new Dimension();
        d.height = 40;
        d.width = 100;
        azul.setPreferredSize(d);
        JButton verde = new JButton("Verde");
        d.height = 40;
        d.width = 100;
        verde.setPreferredSize(d);
        JButton amarillo;
        amarillo = new JButton("Amarillo");
        d.height = 40;
        d.width = 100;
        amarillo.setPreferredSize(d);
        JButton rosa = new JButton("Rosa");
        d.height = 41;
        d.width = 100;
        rosa.setPreferredSize(d);
        panel.add(azul,BorderLayout.SOUTH);
        panel.add(verde,BorderLayout.NORTH);
        panel.add(amarillo,BorderLayout.EAST);
        panel.add(rosa,BorderLayout.WEST);
        azul.addActionListener(new EscuchadorBoton("azul"));
        verde.addActionListener(new EscuchadorBoton("verde"));
        amarillo.addActionListener(new EscuchadorBoton("amarillo"));
        rosa.addActionListener(new EscuchadorBoton("rosa"));

        add(panel);
        panel.setBackground(Color.red);
}//de frame

    // Clase interna para escuchar los eventos del botón
    class EscuchadorBoton implements ActionListener {
        String boton;
        public EscuchadorBoton(String boton) {
            this.boton = boton;
        }
        // Reescribir el método actionPerformed, que responde al clic del botón
        public void actionPerformed(ActionEvent e) {

            // Cambiar el color de fondo del panel a azul
            if (boton.equals("verde"))
                panel.setBackground(Color.green);
            if (boton.equals("rosa"))
                panel.setBackground(Color.pink);
            if (boton.equals("amarillo"))
                panel.setBackground(Color.yellow);
            if  (boton.equals("azul"))
                panel.setBackground(Color.blue);
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar el frame
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}
