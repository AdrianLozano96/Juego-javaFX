package com.alozano.juegofx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PongV extends BorderPane {
    //Declarar
    private Rectangle playerA;
    private Rectangle playerB;
    private Rectangle topWall;
    private Rectangle botWall;
    private Rectangle aWall;
    private Rectangle bWall;
    private Sphere ball;
    private StackPane pista;
    private PongC controlador;
    private PhongMaterial phongMaterial;    //Para la imagen
    private Label puntosPlayerA;
    private Label puntosPlayerB;
    private Text ganador;

    public PongV() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        //this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)))); //Color Fondo
        //Imagen Fondo
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("nebula.png"));
        //imageView.setFitHeight(500);  //Definir un tamaño sino se define es el de la ventana, eso creo.
        //imageView.setFitWidth(700);
        this.getChildren().add(imageView);
        //Instanciar
        playerA = new Rectangle();
        playerB = new Rectangle();
        topWall = new Rectangle();
        botWall = new Rectangle();
        aWall = new Rectangle();
        bWall = new Rectangle();
        ball = new Sphere(50);
        phongMaterial = new PhongMaterial();
        pista = new StackPane();
        puntosPlayerA = new Label("0 points");
        puntosPlayerA.setTextFill(Color.WHITE);
        puntosPlayerB = new Label("0 points");
        puntosPlayerB.setTextFill(Color.WHITE);
        ganador = new Text();
        controlador = new PongC(playerA, playerB, topWall, botWall, ball, pista, aWall, bWall, puntosPlayerA, puntosPlayerB, ganador);
        inicializar();
        colocar();
    }

    //Inicializar
    public void inicializar(){
        playerA.setFill(Color.BLACK);  //Izquierda
        playerA.heightProperty().bind(pista.heightProperty().divide(7));
        playerA.widthProperty().bind(pista.widthProperty().divide(25));

        playerB.setFill(Color.BLACK);   //Derecha
        playerB.heightProperty().bind(pista.heightProperty().divide(7));
        playerB.widthProperty().bind(pista.widthProperty().divide(25));

        topWall.setFill(Color.BLACK);    //Arriba
        topWall.heightProperty().bind(pista.heightProperty().divide(25));
        topWall.widthProperty().bind(pista.widthProperty());

        botWall.setFill(Color.BLACK);   //Abajo
        botWall.heightProperty().bind(pista.heightProperty().divide(25));
        botWall.widthProperty().bind(pista.widthProperty());

        aWall.setFill(Color.RED);   //puntos pared player A
        aWall.heightProperty().bind(pista.heightProperty());
        aWall.widthProperty().bind(pista.widthProperty().divide(10000));

        bWall.setFill(Color.RED);   //puntos pared player B
        bWall.heightProperty().bind(pista.heightProperty());
        bWall.widthProperty().bind(pista.widthProperty().divide(10000));

        //ball.setFill(new ImagePattern(new Image("planeta.png")));

        phongMaterial.setDiffuseMap(new Image("planeta.png"));
        ball.setMaterial(phongMaterial);

        //ball.setFill(Color.DARKOLIVEGREEN);
        ball.radiusProperty().bind(pista.widthProperty().divide(30));
    }



    //Colocar
    public void colocar(){
        pista.getChildren().addAll(playerA, playerB, topWall, botWall, ball, puntosPlayerA, puntosPlayerB, aWall, bWall, ganador);
        pista.setAlignment(playerA, Pos.CENTER_LEFT);  //Nodo y posición para alinear
        pista.setAlignment(playerB, Pos.CENTER_RIGHT);
        pista.setAlignment(topWall, Pos.TOP_CENTER);
        pista.setAlignment(botWall, Pos.BOTTOM_CENTER);
        pista.setAlignment(ball, Pos.CENTER);
        pista.setAlignment(ganador, Pos.CENTER);
        pista.setAlignment(aWall,Pos.CENTER_LEFT);
        pista.setAlignment(bWall,Pos.CENTER_RIGHT);
        pista.setAlignment(puntosPlayerB,Pos.BOTTOM_RIGHT);
        pista.setAlignment(puntosPlayerA,Pos.BOTTOM_LEFT);
        this.setCenter(pista);
    }
}
