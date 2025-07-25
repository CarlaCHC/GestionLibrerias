package Ventanas;

import Clases.*;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistrar extends JFrame {
    private Libreria libreria;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private String tipoMaterial;
    
    private final Color fondoBeige = new Color(245, 240, 230);
    private final Color verdePino = new Color(46, 125, 92);
    private final Color cafeBonito = new Color(110, 75, 58);
    private final Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 16);

    public VentanaRegistrar(Libreria libreria) {
        this.libreria = libreria;
        setTitle("Registrar Material");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createTipoPanel(), "TIPO");
        mainPanel.add(createDatosGeneralesPanel(), "DATOS_GENERALES");
        mainPanel.add(createLibroPanel(), "LIBRO");
        mainPanel.add(createComicPanel(), "COMIC");
        mainPanel.add(createRevistaPanel(), "REVISTA");
        mainPanel.add(createConfirmacionPanel(), "CONFIRMACION");

        add(mainPanel);
        cardLayout.show(mainPanel, "TIPO");
    }

    private JPanel createTipoPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JLabel tituloLabel = new JLabel("Seleccione el tipo de material:", JLabel.CENTER);
        tituloLabel.setFont(fuenteTitulo);

        String[] tipos = {"Libro", "Comic", "Revista"};
        JComboBox<String> tipoCombo = new JComboBox<>(tipos);
        tipoCombo.setFont(fuenteGeneral);

        JButton siguienteBtn = crearBoton("Siguiente", verdePino, Color.WHITE);
        siguienteBtn.addActionListener(e -> {
            tipoMaterial = (String) tipoCombo.getSelectedItem();
            cardLayout.show(mainPanel, "DATOS_GENERALES");
        });

        JButton cancelarBtn = crearBoton("Cancelar", cafeBonito, Color.WHITE);
        cancelarBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(fondoBeige);
        btnPanel.add(cancelarBtn);
        btnPanel.add(siguienteBtn);

        panel.add(tituloLabel, BorderLayout.NORTH);
        panel.add(tipoCombo, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDatosGeneralesPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField anioField = new JTextField();

        panel.add(new JLabel("Título:")).setFont(fuenteGeneral);
        panel.add(tituloField);
        panel.add(new JLabel("Autor:")).setFont(fuenteGeneral);
        panel.add(autorField);
        panel.add(new JLabel("Precio:")).setFont(fuenteGeneral);
        panel.add(precioField);
        panel.add(new JLabel("Año publicación:")).setFont(fuenteGeneral);
        panel.add(anioField);

        JButton atrasBtn = crearBoton("Atrás", cafeBonito, Color.WHITE);
        atrasBtn.addActionListener(e -> cardLayout.show(mainPanel, "TIPO"));

        JButton siguienteBtn = crearBoton("Siguiente", verdePino, Color.WHITE);
        siguienteBtn.addActionListener(e -> {
            try {
                Double.parseDouble(precioField.getText());
                Integer.parseInt(anioField.getText());
                cardLayout.show(mainPanel, tipoMaterial.toUpperCase());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores válidos para precio y año", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(atrasBtn);
        panel.add(siguienteBtn);

        return panel;
    }

    private JPanel createLibroPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JTextField isbnField = new JTextField();
        JTextField editorialField = new JTextField();
        JTextField paginasField = new JTextField();

        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Editorial:"));
        panel.add(editorialField);
        panel.add(new JLabel("Número de páginas:"));
        panel.add(paginasField);

        JButton atrasBtn = crearBoton("Atrás", cafeBonito, Color.WHITE);
        atrasBtn.addActionListener(e -> cardLayout.show(mainPanel, "DATOS_GENERALES"));

        JButton registrarBtn = crearBoton("Registrar", verdePino, Color.WHITE);
        registrarBtn.addActionListener(e -> {
            try {
                JPanel datosPanel = (JPanel) mainPanel.getComponent(1);
                String titulo = ((JTextField) datosPanel.getComponent(1)).getText();
                String autor = ((JTextField) datosPanel.getComponent(3)).getText();
                double precio = Double.parseDouble(((JTextField) datosPanel.getComponent(5)).getText());
                int anio = Integer.parseInt(((JTextField) datosPanel.getComponent(7)).getText());

                Libro libro = new Libro(titulo, autor, precio, anio,
                        isbnField.getText(), editorialField.getText(),
                        Integer.parseInt(paginasField.getText()));

                libreria.agregarMaterialAlStock(libro);
                cardLayout.show(mainPanel, "CONFIRMACION");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(atrasBtn);
        panel.add(registrarBtn);

        return panel;
    }

    private JPanel createComicPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JTextField ilustradorField = new JTextField();
        JTextField volumenField = new JTextField();
        JTextField serieField = new JTextField();

        panel.add(new JLabel("Ilustrador:"));
        panel.add(ilustradorField);
        panel.add(new JLabel("Volumen:"));
        panel.add(volumenField);
        panel.add(new JLabel("Serie:"));
        panel.add(serieField);

        JButton atrasBtn = crearBoton("Atrás", cafeBonito, Color.WHITE);
        atrasBtn.addActionListener(e -> cardLayout.show(mainPanel, "DATOS_GENERALES"));

        JButton registrarBtn = crearBoton("Registrar", verdePino, Color.WHITE);
        registrarBtn.addActionListener(e -> {
            try {
                JPanel datosPanel = (JPanel) mainPanel.getComponent(1);
                String titulo = ((JTextField) datosPanel.getComponent(1)).getText();
                String autor = ((JTextField) datosPanel.getComponent(3)).getText();
                double precio = Double.parseDouble(((JTextField) datosPanel.getComponent(5)).getText());
                int anio = Integer.parseInt(((JTextField) datosPanel.getComponent(7)).getText());

                Comic comic = new Comic(titulo, autor, precio, anio,
                        ilustradorField.getText(),
                        Integer.parseInt(volumenField.getText()),
                        serieField.getText());

                libreria.agregarMaterialAlStock(comic);
                cardLayout.show(mainPanel, "CONFIRMACION");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese valores válidos (volumen debe ser numérico)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(atrasBtn);
        panel.add(registrarBtn);

        return panel;
    }

    private JPanel createRevistaPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JTextField issnField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField periodicidadField = new JTextField();

        panel.add(new JLabel("ISSN:"));
        panel.add(issnField);
        panel.add(new JLabel("Número:"));
        panel.add(numeroField);
        panel.add(new JLabel("Periodicidad:"));
        panel.add(periodicidadField);

        JButton atrasBtn = crearBoton("Atrás", cafeBonito, Color.WHITE);
        atrasBtn.addActionListener(e -> cardLayout.show(mainPanel, "DATOS_GENERALES"));

        JButton registrarBtn = crearBoton("Registrar", verdePino, Color.WHITE);
        registrarBtn.addActionListener(e -> {
            try {
                JPanel datosPanel = (JPanel) mainPanel.getComponent(1);
                String titulo = ((JTextField) datosPanel.getComponent(1)).getText();
                String autor = ((JTextField) datosPanel.getComponent(3)).getText();
                double precio = Double.parseDouble(((JTextField) datosPanel.getComponent(5)).getText());
                int anio = Integer.parseInt(((JTextField) datosPanel.getComponent(7)).getText());

                Revista revista = new Revista(titulo, autor, precio, anio,
                        issnField.getText(),
                        Integer.parseInt(numeroField.getText()),
                        periodicidadField.getText());

                libreria.agregarMaterialAlStock(revista);
                cardLayout.show(mainPanel, "CONFIRMACION");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese valores válidos (número debe ser numérico)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(atrasBtn);
        panel.add(registrarBtn);

        return panel;
    }

    private JPanel createConfirmacionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(fondoBeige);

        JLabel mensaje = new JLabel("Material registrado exitosamente!", JLabel.CENTER);
        mensaje.setFont(fuenteTitulo);

        JButton finalizarBtn = crearBoton("Finalizar", cafeBonito, Color.WHITE);
        finalizarBtn.addActionListener(e -> dispose());

        panel.add(mensaje, BorderLayout.CENTER);
        panel.add(finalizarBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(fuenteGeneral);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
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
}
