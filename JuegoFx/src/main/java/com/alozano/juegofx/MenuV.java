package com.alozano.juegofx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.*;
import javafx.scene.layout.VBox;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuV extends VBox {

    private Button playWarGame;
    private Button playPongGame;
    private Button options;
    private Button exitGame;
    private Label pantalla;
    private Label controles;
    private VBox opcionesPantalla;
    private CheckBox op1;
    private CheckBox op2;
    private CheckBox op3;


    public MenuV(){
        //Label
        this.pantalla = new Label("JUEGO FX");
        pantalla.setAlignment(Pos.CENTER);
        pantalla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.controles = new Label(misControles());
        controles.setAlignment(Pos.CENTER);
        controles.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //Botones titulo
        this.playWarGame = new Button("Play to War Game");
        this.playPongGame = new Button("Play to Pong Game");
        this.options = new Button("Options");
        this.exitGame = new Button("Exit to Game");
        opcionesPantalla = new VBox();
        op1 = new CheckBox("Pantalla Completa");
        op2 = new CheckBox("Redimensionar Pantalla");
        op3 = new CheckBox("Maximizar Pantalla");
        //Botones tamaño
        playWarGame.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        playPongGame.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        options.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        exitGame.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        opcionesPantalla.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //Botones efectos
        playWarGame.setEffect(new DropShadow());
        playPongGame.setEffect(new SepiaTone());
        options.setEffect(new Blend());
        exitGame.setEffect(new InnerShadow());

        //btn.setAlignment(Pos.CENTER);
        //btn.setAlignment(Pos.CENTER_RIGHT);
        //btn.setMaxSize(100, 100);
        //btn.setCenterShape(true);

        //Botones acciones
        playWarGame.setOnAction(e->App.setJuego());
        playPongGame.setOnAction(e-> {
            try {
                App.setJuegoPong();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
        });
        options.setOnAction(e->App.options());
        exitGame.setOnAction(e->System.exit(0));
        op1.setOnAction(e->App.conf(1));
        op2.setOnAction(e->App.conf(2));
        op3.setOnAction(e->App.conf(3));

        //Lista de opciones
        TitledPane misOpciones = new TitledPane(
                "OpcionesFX",
                opcionesPantalla
        );
        misOpciones.setExpanded( false );
        misOpciones.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Añado el titulo y botones al vbox
        opcionesPantalla.getChildren().addAll(op1, op2, op3);


        //Lista de controles
        TitledPane misControles = new TitledPane(
                "Controles Pong FX",
                controles
        );
        misControles.setExpanded( false );
        misControles.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        this.getChildren().addAll(pantalla, playWarGame, playPongGame, options, misOpciones, misControles, exitGame);
    }

    public String misControles(){
        return " Player A (player left): move with arrow keys\n " +
                "Player B (player right): move with W and S\n " +
                "M: for play music\n" +
                " Z: for stop music"+
                " SHIFT: stop game and music\n" +
                " SPACE: new game\n" +
                " ENTER: play game\n" +
                " ESCAPE: exit game";
    }








}
