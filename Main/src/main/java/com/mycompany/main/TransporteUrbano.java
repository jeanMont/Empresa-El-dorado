/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ASUS
 */
import javax.swing.*;

public class TransporteUrbano extends Vehiculo{
    
    private int capacidad;

    public TransporteUrbano(String placa, String marca, int anio, char tipoCombustible, double tarifaBase, int capacidad) {
        super(placa, marca, anio, tipoCombustible, tarifaBase);
        this.capacidad = capacidad;
    }

    @Override
    public double calcularCostoBaseFuncionamiento() {
        if (tipoCombustible == 'D') {
            return tarifaBase * 1000;
        } else if (tipoCombustible == 'G') {
            return tarifaBase * 5000;
        } else if (tipoCombustible == 'E') {
            return tarifaBase * 7000;
        } else {
            return 0;
        }
    }

    @Override
    public double calcularCostoMantenimiento() {
        if (capacidad < 15) {
            return capacidad * 100 + tarifaBase;
        } else {
            return capacidad * 150 - tarifaBase;
        }
    }
    
    public void mostrarDetallesGUI() {
        JFrame frame = new JFrame("Detalles del Transporte Urbano");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel placaLabel = new JLabel("Placa: " + placa);
        JLabel marcaLabel = new JLabel("Marca: " + marca);
        JLabel anioLabel = new JLabel("Año: " + anio);
        JLabel combustibleLabel = new JLabel("Tipo de Combustible: " + tipoCombustible);
        JLabel tarifaLabel = new JLabel("Tarifa Base: " + tarifaBase);
        JLabel capacidadLabel = new JLabel("Capacidad: " + capacidad);
        
        JLabel costoFuncionamientoLabel = new JLabel("Costo de Funcionamiento: " + calcularCostoBaseFuncionamiento());
        JLabel costoMantenimientoLabel = new JLabel("Costo de Mantenimiento: " + calcularCostoMantenimiento());
        
        panel.add(placaLabel);
        panel.add(marcaLabel);
        panel.add(anioLabel);
        panel.add(combustibleLabel);
        panel.add(tarifaLabel);
        panel.add(capacidadLabel);
        panel.add(costoFuncionamientoLabel);
        panel.add(costoMantenimientoLabel);
        
        frame.getContentPane().add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}