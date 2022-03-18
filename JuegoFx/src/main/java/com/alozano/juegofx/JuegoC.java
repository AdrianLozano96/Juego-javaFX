package com.alozano.juegofx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class JuegoC {

    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle topWall;
    private Rectangle botWall;
    private Rectangle tanque;
    private StackPane pista;
    private double velocidad;
    private Timeline animacion;     //Creo mi animacion

    private int movimientoX = 0;
    private int movimientoY = 0;

    public JuegoC(Rectangle leftWall, Rectangle rightWall, Rectangle topWall, Rectangle botWall, Rectangle tanque, StackPane pista) {
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.topWall = topWall;
        this.botWall = botWall;
        this.tanque = tanque;
        this.pista = pista;
        velocidad = 1;
        inicializarControles();
        inicializarJuego();

    }
    private void inicializarControles(){
        pista.setOnKeyPressed(e->{; //Para presionar
            //System.out.println(e.getCode());    //getCode obtiene la tecla pulsada,
            controlMenu(e, -1, 1);
        });
        pista.setOnKeyReleased(e->{;    //Para soltar
            //System.out.println(e.getCode());    //getCode obtiene la tecla pulsada,
            controlMenu(e, 0, 0);
        });
        pista.setFocusTraversable(true);    //Captura botones
    }



    public void inicializarJuego(){
        //Bucle del Juego
        animacion = new Timeline(new KeyFrame(Duration.millis(17),t -> {
            moverTanque();
            detectarColision();
        }));
        animacion.setCycleCount(Animation.INDEFINITE);
        //animacion.play();
    }

    public void moverTanque(){
        tanque.setTranslateY(tanque.getTranslateY() + movimientoY);
        tanque.setTranslateX(tanque.getTranslateX() + movimientoX);
    }

    public void detectarColision(){
        //tanque.setTranslateX(tanque.getTranslateX()-1);
        //Bound son bordes inLocal(antes de moverse(centro), InParent (después de la modificació (una vez se mueve)).
        if(tanque.getBoundsInParent().intersects(leftWall.getBoundsInParent())){
            tanque.setTranslateY(tanque.getTranslateY() + -movimientoY);
            tanque.setTranslateX(tanque.getTranslateX() + -movimientoX);
            //tanque.setTranslateY(pista.heightProperty().getValue());
            //tanque.setTranslateX(pista.widthProperty().divide(5).getValue());
            System.out.println("Colision izquierda");
        }
        if(tanque.getBoundsInParent().intersects(rightWall.getBoundsInParent())){
            tanque.setTranslateY(tanque.getTranslateY() + -movimientoY);
            tanque.setTranslateX(tanque.getTranslateX() + -movimientoX);
            System.out.println("Colision derecha");
        }
        if(tanque.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            tanque.setTranslateY(tanque.getTranslateY() + -movimientoY);
            tanque.setTranslateX(tanque.getTranslateX() + -movimientoX);
            System.out.println("Colision superior");
        }
        if(tanque.getBoundsInParent().intersects(botWall.getBoundsInParent())){
            tanque.setTranslateY(tanque.getTranslateY() + -movimientoY);
            tanque.setTranslateX(tanque.getTranslateX() + -movimientoX);
            System.out.println("Colision inferior");
        }

    }



    private void controlMenu(KeyEvent e, int i, int i2) {
        switch (e.getCode()) {
            case UP:
                movimientoY = i;
                break;
            case W:
                movimientoY = i;
                break;
            case DOWN:
                movimientoY = i2;
                break;
            case S:
                movimientoY = i2;
                break;
            case RIGHT:
                movimientoX = i2;
                break;
            case D:
                movimientoX = i2;
                break;
            case LEFT:
                movimientoX = i;
                break;
            case A:
                movimientoX = i;
                break;
            case SHIFT:
                animacion.stop();
                break;
            case SPACE:
                System.out.println("PIUM PIUM PIUM");
                break;
            case ENTER:
                animacion.play();
                break;
            case ESCAPE:
                App.setMenuV();

        }
    }
    

}