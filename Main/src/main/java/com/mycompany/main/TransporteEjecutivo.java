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

public class TransporteEjecutivo extends Vehiculo {
    
    private char clase;
    private int nivelBlindaje;

    public TransporteEjecutivo(String placa, String marca, int anio, char tipoCombustible, double tarifaBase, char clase, int nivelBlindaje) {
        super(placa, marca, anio, tipoCombustible, tarifaBase);
        this.clase = clase;
        this.nivelBlindaje = nivelBlindaje;
    }
    
    @Override
    public double calcularCostoBaseFuncionamiento() {
        double costoBase;
        if (tipoCombustible == 'D') {
            costoBase = tarifaBase * 500;
        } else if (tipoCombustible == 'G') {
            costoBase = tarifaBase * 800;
        } else if (tipoCombustible == 'E') {
            costoBase = tarifaBase * 900;
        } else {
            costoBase = 0;
        }

        if (nivelBlindaje < 3) {
            return costoBase * 100;
        } else if (nivelBlindaje == 3 || nivelBlindaje == 4) {
            return costoBase * 200;
        } else {
            return costoBase * 300;
        }
    }

    @Override
    public double calcularCostoMantenimiento() {
        if (nivelBlindaje >= 3) {
            return tarifaBase * 15000;
        } else {
            return tarifaBase * 8000;
        }
    } 
    
    public void mostrarDetallesGUI() {
        JFrame frame = new JFrame("Detalles del Transporte Ejecutivo");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel placaLabel = new JLabel("Placa: " + placa);
        JLabel marcaLabel = new JLabel("Marca: " + marca);
        JLabel anioLabel = new JLabel("Año: " + anio);
        JLabel combustibleLabel = new JLabel("Tipo de Combustible: " + tipoCombustible);
        JLabel tarifaLabel = new JLabel("Tarifa Base: " + tarifaBase);
        JLabel claseLabel = new JLabel("Clase: " + clase);
        JLabel blindajeLabel = new JLabel("Nivel de Blindaje: " + nivelBlindaje);
        
        JLabel costoFuncionamientoLabel = new JLabel("Costo de Funcionamiento: " + calcularCostoBaseFuncionamiento());
        JLabel costoMantenimientoLabel = new JLabel("Costo de Mantenimiento: " + calcularCostoMantenimiento());
        
        panel.add(placaLabel);
        panel.add(marcaLabel);
        panel.add(anioLabel);
        panel.add(combustibleLabel);
        panel.add(tarifaLabel);
        panel.add(claseLabel);
        panel.add(blindajeLabel);
        panel.add(costoFuncionamientoLabel);
        panel.add(costoMantenimientoLabel);
        
        frame.getContentPane().add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}