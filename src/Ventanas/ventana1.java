package Ventanas;

import Clases.*;

import javax.swing.*;
import java.awt.*;

public class ventana1 extends JFrame {
    private Libreria libreria;

    public ventana1() {
        libreria = new Libreria("Librería Central");

        setTitle("📚 Gestión de Librerías");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(new Color(245, 240, 230)); // Beige claro

        
        ImageIcon icono = new ImageIcon("src/Imagenes/logo3.jpg");
        JLabel imagen = new JLabel(new ImageIcon(icono.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH)));
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelTop.add(imagen, BorderLayout.NORTH);

      
        JLabel titulo = new JLabel("Gestión de Librerías ", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(44, 62, 80));
        panelTop.add(titulo, BorderLayout.CENTER);

        
        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        panelBotones.setBackground(new Color(245, 240, 230)); // Beige claro

        Color verdePino = new Color(46, 125, 92);
        Color cafeBonito = new Color(110, 75, 58);
        Color textoBlanco = Color.WHITE;

    
        JButton btnRegistrar = crearBoton("Registrar Material", verdePino, textoBlanco);
        JButton btnVender = crearBoton("Vender Material", verdePino, textoBlanco);
        JButton btnMostrarStock = crearBoton("Mostrar Stock", verdePino, textoBlanco);
        JButton btnReporteVentas = crearBoton("Reporte de Ventas", verdePino, textoBlanco);
        JButton btnAgotados = crearBoton("Alertar Agotados", verdePino, textoBlanco);
        JButton btnSalir = crearBoton("Salir", cafeBonito, textoBlanco);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVender);
        panelBotones.add(btnMostrarStock);
        panelBotones.add(btnReporteVentas);
        panelBotones.add(btnAgotados);
        panelBotones.add(btnSalir);

        add(panelTop, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(245, 240, 230)); 
        
        btnRegistrar.addActionListener(e -> new VentanaRegistrar(libreria).setVisible(true));
        btnVender.addActionListener(e -> new VentanaVender(libreria).setVisible(true));
        btnMostrarStock.addActionListener(e -> mostrar("Stock Disponible", libreria.getStockList()));
        btnReporteVentas.addActionListener(e -> mostrar("Ventas Totales", libreria.getVentasResumen()));
        btnAgotados.addActionListener(e -> mostrar("Agotados", libreria.getMaterialesAgotados()));
        btnSalir.addActionListener(e -> dispose());
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createLineBorder(fondo.darker(), 1, true));
        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(fondo.darker());
                g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 15, 15);
            }
        });
        return boton;
    }

    private void mostrar(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido);
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ventana1().setVisible(true));
    }
}