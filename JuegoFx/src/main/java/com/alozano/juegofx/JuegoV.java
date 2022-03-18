package com.alozano.juegofx;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JuegoV extends BorderPane {
    //Declarar
    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle topWall;
    private Rectangle botWall;
    private Rectangle tanque;
    private StackPane pista;
    private JuegoC controlador;

    public JuegoV(){
        //Instanciar
        leftWall = new Rectangle();
        rightWall = new Rectangle();
        topWall = new Rectangle();
        botWall = new Rectangle();
        tanque = new Rectangle();
        pista = new StackPane();
        controlador = new JuegoC(leftWall, rightWall, topWall, botWall, tanque, pista);
        inicializar();
        colocar();
    }

    //Inicializar
    public void inicializar(){
        leftWall.setFill(Color.BLACK);  //Derecha
        leftWall.heightProperty().bind(pista.heightProperty());
        leftWall.widthProperty().bind(pista.widthProperty().divide(25));
        rightWall.setFill(Color.BLACK);   //Izquierda
        rightWall.heightProperty().bind(pista.heightProperty());
        rightWall.widthProperty().bind(pista.widthProperty().divide(25));
        topWall.setFill(Color.BLACK);    //Arriba
        topWall.heightProperty().bind(pista.heightProperty().divide(25));
        topWall.widthProperty().bind(pista.widthProperty());
        botWall.setFill(Color.BLACK);   //Abajo
        botWall.heightProperty().bind(pista.heightProperty().divide(25));
        botWall.widthProperty().bind(pista.widthProperty());

        tanque.setFill(Color.DARKOLIVEGREEN);   //Tanque
        tanque.heightProperty().bind(leftWall.widthProperty());
        tanque.widthProperty().bind(leftWall.widthProperty());
    }



    //Colocar
    public void colocar(){
        pista.getChildren().addAll(leftWall, rightWall, topWall, botWall,tanque);
        pista.setAlignment(leftWall, Pos.CENTER_LEFT);  //Nodo y posición para alinear
        pista.setAlignment(rightWall, Pos.CENTER_RIGHT);
        pista.setAlignment(topWall, Pos.TOP_CENTER);
        pista.setAlignment(botWall, Pos.BOTTOM_CENTER);
        pista.setAlignment(tanque, Pos.CENTER);
        this.setCenter(pista);
    }


    //Controlador
    public void acciones(){

    }


}
