package Ventanas;

import Clases.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaVender extends JFrame {
    private Libreria libreria;
    private JTextField compradorField;
    private JTextField tituloField;

    public VentanaVender(Libreria libreria) {
        this.libreria = libreria;
        setTitle("Vender Material");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JLabel instruccionLabel = new JLabel("Ingrese los datos de la venta:", JLabel.CENTER);
        instruccionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        compradorField = new JTextField();
        tituloField = new JTextField();
        
        JPanel compradorPanel = new JPanel(new BorderLayout(5, 5));
        compradorPanel.add(new JLabel("Nombre del comprador:"), BorderLayout.WEST);
        compradorPanel.add(compradorField, BorderLayout.CENTER);
        
        JPanel tituloPanel = new JPanel(new BorderLayout(5, 5));
        tituloPanel.add(new JLabel("Título del material:"), BorderLayout.WEST);
        tituloPanel.add(tituloField, BorderLayout.CENTER);
        
        formPanel.add(instruccionLabel);
        formPanel.add(compradorPanel);
        formPanel.add(tituloPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.addActionListener(e -> dispose());
        
        JButton venderBtn = new JButton("Vender");
        venderBtn.addActionListener(e -> procesarVenta());
        
        buttonPanel.add(cancelarBtn);
        buttonPanel.add(venderBtn);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void procesarVenta() {
        String comprador = compradorField.getText().trim();
        String titulo = tituloField.getText().trim();
        
        if (comprador.isEmpty() || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe completar todos los campos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            
            libreria.venderMaterial(titulo, comprador);
            JOptionPane.showMessageDialog(this, 
                "Venta realizada con éxito", 
                "Resultado", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al procesar la venta: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}