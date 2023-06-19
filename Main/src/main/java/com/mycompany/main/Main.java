/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Main {

    private static JFrame frame;
    private static JPanel panel;
    private static CardLayout cardLayout;
    private static JTextField placaField, marcaField, anioField, combustibleField, tarifaField, capacidadField;
    private static JTextField claseField, blindajeField;
    private static JLabel resultadoLabel;

    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Transporte App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);

        createMenuPanel();
        createTransporteUrbanoPanel();
        createTransporteEjecutivoPanel();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarDatosDesdeArchivo(); // Cargar datos desde el archivo al iniciar la aplicación
    }

    private static void createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(5, 1));
        JButton urbanoButton = new JButton("Agregar Transporte Urbano");
        JButton ejecutivoButton = new JButton("Agregar Transporte Ejecutivo");
        JButton verArchivoButton = new JButton("Ver Archivo de Datos");
        JButton cargarArchivoButton = new JButton("Cargar Archivo de Datos");
        JButton eliminarArchivoButton = new JButton("Eliminar Archivo de Datos");

        urbanoButton.addActionListener(e -> cardLayout.show(panel, "Transporte Urbano"));
        ejecutivoButton.addActionListener(e -> cardLayout.show(panel, "Transporte Ejecutivo"));

        verArchivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verArchivoDeDatos();
            }
        });

        cargarArchivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarArchivoDeDatos();
            }
        });

        eliminarArchivoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarArchivoDeDatos();
            }
        });

        menuPanel.add(urbanoButton);
        menuPanel.add(ejecutivoButton);
        menuPanel.add(verArchivoButton);
        menuPanel.add(cargarArchivoButton);
        menuPanel.add(eliminarArchivoButton);

        panel.add(menuPanel, "Menu");
    }

    private static void createTransporteUrbanoPanel() {
        JPanel urbanoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel placaLabel = new JLabel("Placa:");
        placaField = new JTextField(10);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaField = new JTextField(10);

        JLabel anioLabel = new JLabel("Año:");
        anioField = new JFormattedTextField(NumberFormat.getIntegerInstance());
anioField.setColumns(10);

        JLabel combustibleLabel = new JLabel("Tipo de Combustible (D/G/E):");
        combustibleField = new JTextField(1);

        JLabel tarifaLabel = new JLabel("Tarifa Base:");
        tarifaField = new JTextField(10);

        JLabel capacidadLabel = new JLabel("Capacidad:");
        capacidadField = new JTextField(10);

        JButton calcularButton = new JButton("Calcular");
        JButton guardarButton = new JButton("Guardar");
        resultadoLabel = new JLabel();

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                int anio;
try {
    anio = Integer.parseInt(anioField.getText().trim());
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el año.", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Salir del método actionPerformed sin continuar con el cálculo
}
                char tipoCombustible = combustibleField.getText().charAt(0);
                double tarifaBase;
                try {
                    tarifaBase = Double.parseDouble(tarifaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la tarifa base.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                int capacidad;
                try {
                    capacidad = Integer.parseInt(capacidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la capacidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }

                TransporteUrbano buseta = new TransporteUrbano(placa, marca, anio, tipoCombustible, tarifaBase, capacidad);
                double costoFuncionamiento = buseta.calcularCostoBaseFuncionamiento();
                double costoMantenimiento = buseta.calcularCostoMantenimiento();

                resultadoLabel.setText("Costo de Funcionamiento: " + costoFuncionamiento + " | Costo de Mantenimiento: " + costoMantenimiento);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                int anio;
                try {
                    anio = Integer.parseInt(anioField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el año.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                char tipoCombustible = combustibleField.getText().charAt(0);
                double tarifaBase;
                try {
                    tarifaBase = Double.parseDouble(tarifaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la tarifa base.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                int capacidad;
                try {
                    capacidad = Integer.parseInt(capacidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la capacidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }

                TransporteUrbano buseta = new TransporteUrbano(placa, marca, anio, tipoCombustible, tarifaBase, capacidad);
                vehiculos.add(buseta);

                guardarDatosEnArchivo();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        urbanoPanel.add(placaLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(placaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        urbanoPanel.add(marcaLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(marcaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        urbanoPanel.add(anioLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(anioField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        urbanoPanel.add(combustibleLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(combustibleField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        urbanoPanel.add(tarifaLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(tarifaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        urbanoPanel.add(capacidadLabel, gbc);
        gbc.gridx = 1;
        urbanoPanel.add(capacidadField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        urbanoPanel.add(calcularButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        urbanoPanel.add(resultadoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        urbanoPanel.add(guardarButton, gbc);

        panel.add(urbanoPanel, "Transporte Urbano");
    }

    private static void createTransporteEjecutivoPanel() {
        JPanel ejecutivoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel placaLabel = new JLabel("Placa:");
        placaField = new JTextField(10);

        JLabel marcaLabel = new JLabel("Marca:");
        marcaField = new JTextField(10);

        JLabel anioLabel = new JLabel("Año:");
        anioField = new JTextField(10);

        JLabel combustibleLabel = new JLabel("Tipo de Combustible (D/G/E):");
        combustibleField = new JTextField(1);

        JLabel tarifaLabel = new JLabel("Tarifa Base:");
        tarifaField = new JTextField(10);

        JLabel claseLabel = new JLabel("Clase:");
        claseField = new JTextField(1);

        JLabel blindajeLabel = new JLabel("Nivel de Blindaje:");
        blindajeField = new JTextField(10);

        JButton calcularButton = new JButton("Calcular");
        JButton guardarButton = new JButton("Guardar");
        resultadoLabel = new JLabel();

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                int anio;
try {
    anio = Integer.parseInt(anioField.getText().trim());
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el año.", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Salir del método actionPerformed sin continuar con el cálculo
}
                char tipoCombustible = combustibleField.getText().charAt(0);
                double tarifaBase;
                try {
                    tarifaBase = Double.parseDouble(tarifaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la tarifa base.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                char clase = claseField.getText().charAt(0);
                int nivelBlindaje;
                try {
                    nivelBlindaje = Integer.parseInt(blindajeField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el nivel de blindaje.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }

                TransporteEjecutivo limusina = new TransporteEjecutivo(placa, marca, anio, tipoCombustible, tarifaBase, clase, nivelBlindaje);
                double costoFuncionamiento = limusina.calcularCostoBaseFuncionamiento();
                double costoMantenimiento = limusina.calcularCostoMantenimiento();

                resultadoLabel.setText("Costo de Funcionamiento: " + costoFuncionamiento + " | Costo de Mantenimiento: " + costoMantenimiento);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                int anio;
                try {
                    anio = Integer.parseInt(anioField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el año.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                char tipoCombustible = combustibleField.getText().charAt(0);
                double tarifaBase;
                try {
                    tarifaBase = Double.parseDouble(tarifaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para la tarifa base.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }
                char clase = claseField.getText().charAt(0);
                int nivelBlindaje;
                try {
                    nivelBlindaje = Integer.parseInt(blindajeField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese un valor numérico válido para el nivel de blindaje.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método actionPerformed sin continuar con el cálculo
                }

                TransporteEjecutivo limusina = new TransporteEjecutivo(placa, marca, anio, tipoCombustible, tarifaBase, clase, nivelBlindaje);
                vehiculos.add(limusina);

                guardarDatosEnArchivo();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        ejecutivoPanel.add(placaLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(placaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        ejecutivoPanel.add(marcaLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(marcaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        ejecutivoPanel.add(anioLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(anioField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        ejecutivoPanel.add(combustibleLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(combustibleField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        ejecutivoPanel.add(tarifaLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(tarifaField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        ejecutivoPanel.add(claseLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(claseField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        ejecutivoPanel.add(blindajeLabel, gbc);
        gbc.gridx = 1;
        ejecutivoPanel.add(blindajeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        ejecutivoPanel.add(calcularButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        ejecutivoPanel.add(resultadoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        ejecutivoPanel.add(guardarButton, gbc);

        panel.add(ejecutivoPanel, "Transporte Ejecutivo");
    }

    private static void verArchivoDeDatos() {
        StringBuilder sb = new StringBuilder();
        for (Vehiculo vehiculo : vehiculos) {
            sb.append(vehiculo.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, sb.toString(), "Archivo de Datos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cargarArchivoDeDatos() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(frame);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 6) {
                        String placa = datos[0].trim();
                        String marca = datos[1].trim();
                        int anio = Integer.parseInt(datos[2].trim());
                        char tipoCombustible = datos[3].trim().charAt(0);
                        double tarifaBase = Double.parseDouble(datos[4].trim());
                        int capacidad = Integer.parseInt(datos[5].trim());
                        TransporteUrbano buseta = new TransporteUrbano(placa, marca, anio, tipoCombustible, tarifaBase, capacidad);
                        vehiculos.add(buseta);
                    } else if (datos.length == 7) {
                        String placa = datos[0].trim();
                        String marca = datos[1].trim();
                        int anio = Integer.parseInt(datos[2].trim());
                        char tipoCombustible = datos[3].trim().charAt(0);
                        double tarifaBase = Double.parseDouble(datos[4].trim());
                        char clase = datos[5].trim().charAt(0);
                        int nivelBlindaje = Integer.parseInt(datos[6].trim());
                        TransporteEjecutivo limusina = new TransporteEjecutivo(placa, marca, anio, tipoCombustible, tarifaBase, clase, nivelBlindaje);
                        vehiculos.add(limusina);
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(frame, "Archivo cargado correctamente.", "Cargar Archivo", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Error al leer los datos del archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void eliminarArchivoDeDatos() {
        int respuesta = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de eliminar el archivo de datos?", "Eliminar Archivo", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            File archivo = new File("datos.txt");
            if (archivo.exists()) {
                archivo.delete();
                JOptionPane.showMessageDialog(frame, "Archivo eliminado correctamente.", "Eliminar Archivo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void guardarDatosEnArchivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"));
            for (Vehiculo vehiculo : vehiculos) {
                bw.write(vehiculo.toString());
                bw.newLine();
            }
            bw.close();
            JOptionPane.showMessageDialog(frame, "Datos guardados correctamente.", "Guardar Datos", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cargarDatosDesdeArchivo() {
        File archivo = new File("datos.txt");
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 6) {
                        String placa = datos[0].trim();
                        String marca = datos[1].trim();
                        int anio = Integer.parseInt(datos[2].trim());
                        char tipoCombustible = datos[3].trim().charAt(0);
                        double tarifaBase = Double.parseDouble(datos[4].trim());
                        int capacidad = Integer.parseInt(datos[5].trim());
                        TransporteUrbano buseta = new TransporteUrbano(placa, marca, anio, tipoCombustible, tarifaBase, capacidad);
                        vehiculos.add(buseta);
                    } else if (datos.length == 7) {
                        String placa = datos[0].trim();
                        String marca = datos[1].trim();
                        int anio = Integer.parseInt(datos[2].trim());
                        char tipoCombustible = datos[3].trim().charAt(0);
                        double tarifaBase = Double.parseDouble(datos[4].trim());
                        char clase = datos[5].trim().charAt(0);
                        int nivelBlindaje = Integer.parseInt(datos[6].trim());
                        TransporteEjecutivo limusina = new TransporteEjecutivo(placa, marca, anio, tipoCombustible, tarifaBase, clase, nivelBlindaje);
                        vehiculos.add(limusina);
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Error al leer los datos del archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
