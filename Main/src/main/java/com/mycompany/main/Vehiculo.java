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

public abstract class Vehiculo {
    
    protected String placa;
    protected String marca;
    protected int anio;
    protected char tipoCombustible;
    protected double tarifaBase;

    public Vehiculo(String placa, String marca, int anio, char tipoCombustible, double tarifaBase) {
        this.placa = placa;
        this.marca = marca;
        this.anio = anio;
        this.tipoCombustible = tipoCombustible;
        this.tarifaBase = tarifaBase;
    }
    
    public abstract double calcularCostoBaseFuncionamiento();

    public abstract double calcularCostoMantenimiento();
    
    public void mostrarDetallesGUI() {
        JFrame frame = new JFrame("Detalles del vehículo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel placaLabel = new JLabel("Placa: " + placa);
        JLabel marcaLabel = new JLabel("Marca: " + marca);
        JLabel anioLabel = new JLabel("Año: " + anio);
        JLabel combustibleLabel = new JLabel("Tipo de Combustible: " + tipoCombustible);
        JLabel tarifaLabel = new JLabel("Tarifa Base: " + tarifaBase);
        
        panel.add(placaLabel);
        panel.add(marcaLabel);
        panel.add(anioLabel);
        panel.add(combustibleLabel);
        panel.add(tarifaLabel);
        
        frame.getContentPane().add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
