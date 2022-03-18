package com.alozano.juegofx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Options extends VBox {

    private Label pantalla;
    private Button conf1;
    private Button conf2;
    private Button conf3;
    private Button conf4;
    private Button conf5;
    private Button atras;

    public Options() {
        this.pantalla = new Label("Menú de opciones");
        pantalla.setAlignment(Pos.CENTER);
        pantalla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        conf1 = new Button("Pantalla Completa");
        conf2 = new Button("Redimensionar Pantalla");
        conf3 = new Button("Maximizar Pantalla");
        conf4 = new Button("Otros 1");
        conf5 = new Button("Otros 2");
        atras = new Button("Volver al Menú");
        conf1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        conf2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        conf3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        conf4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        conf5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        atras.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        acciones();
        this.getChildren().addAll(pantalla, conf1, conf2, conf3, conf4, conf5, atras);
    }

    public void acciones(){
        conf1.setOnAction(e->App.conf(1));
        conf2.setOnAction(e->App.conf(2));
        conf3.setOnAction(e->App.conf(3));
        conf4.setOnAction(e->App.conf(4));
        conf5.setOnAction(e->App.conf(5));
        atras.setOnAction(e->App.setMenuV());
    }

}
