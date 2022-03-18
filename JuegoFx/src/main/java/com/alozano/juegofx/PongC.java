package com.alozano.juegofx;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PongC {

    private Rectangle playerA;
    private Rectangle playerB;
    private Rectangle topWall;
    private Rectangle botWall;
    private Rectangle aWall;
    private Rectangle bWall;
    private Sphere ball;
    private StackPane pista;
    private Label puntosPlayerA;
    private Label puntosPlayerB;
    private Text ganador;
    private Timeline animacion;     //Creo mi animacion
    private int puntosA;
    private int puntosB;
    private double velocidadB;
    private double velocidadP;
    private double velocidad;
    private double count = 0;
    private int movimientoPA = 0;
    private int movimientoPB = 0;
    private int movimientoX = 1;
    private int movimientoY = 1;

    private String sonido = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"choque.wav";
    private String win = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"win.wav";
    private String game = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"game.wav";
    private boolean musica = false;
    private Clip clip;
    private AudioInputStream audioInputStream;

    public PongC(Rectangle playerA, Rectangle playerB, Rectangle topWall, Rectangle botWall, Sphere ball, StackPane pista,
                 Rectangle aWall, Rectangle bWall, Label puntosPlayerA, Label puntosPlayerB, Text ganador) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        this.playerA = playerA;
        this.playerB = playerB;
        this.topWall = topWall;
        this.botWall = botWall;
        this.ball = ball;
        this.pista = pista;
        this.aWall = aWall;
        this.bWall = bWall;
        this.puntosPlayerA = puntosPlayerA;
        this.puntosPlayerB = puntosPlayerB;
        this.ganador = ganador;
        puntosA = 0;
        puntosB = 0;
        velocidadB = 2;
        velocidadP = 2;
        inicializarControles();
        inicializarJuego();
    }


    private void inicializarControles() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Accion que se realizce al pulsar una tecla
        pista.setOnKeyPressed(e->{; //Para presionar
            //System.out.println(e.getCode());    //getCode obtiene la tecla pulsada,
            try {
                controlMenu(e, -1, 1);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        });
        pista.setOnKeyReleased(e->{;    //Para soltar
            //System.out.println(e.getCode());    //getCode obtiene la tecla pulsada,
            try {
                controlMenu(e, 0, 0);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        });
        pista.setFocusTraversable(true);    //Captura botones
    }


    public void inicializarJuego() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Bucle del Juego
        animacion = new Timeline(new KeyFrame(Duration.millis(17), t -> {   //17 = 0.017seg. = 60fps

            moverJugadores();
            try {
                detectarColision();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }

        }));

        animacion.setCycleCount(Timeline.INDEFINITE);
        //animacion.setCycleCount(Animation.INDEFINITE);
        //animacion.play();
    }

    public void moverJugadores(){
        //translate es una transformación para mover un elemento
        //scale es una transformación para cambiar el tamaño de un elemento
        //rotate es una transformación para girar un elemento
        //Rotate rotacion = new Rotate(-10, -10, 0);
        //ball.getTransforms().addAll(rotacion);
        playerA.setTranslateY(playerA.getTranslateY() + movimientoPA*velocidadP);
        playerB.setTranslateY(playerB.getTranslateY() + movimientoPB*velocidadP);

        ball.setTranslateY(ball.getTranslateY() + movimientoY*velocidadB);
        ball.setTranslateX(ball.getTranslateX() + movimientoX*velocidadB);
    }

    public void detectarColision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Bound son bordes inLocal(antes de moverse(centro), InParent (después de la modificació (una vez se mueve)).
        //*(boundsInLocal no tiene en cuenta las transformaciones y efectos, boundsInParent si.
        if(ball.getBoundsInParent().intersects(playerA.getBoundsInParent())){
            metodoSound();
            ball.setTranslateY(ball.getTranslateY() + -movimientoY);
            ball.setTranslateX(ball.getTranslateX() + ++movimientoX);
            velocidadB += 0.1;
            velocidadP += 0.05;
            //System.out.println(velocidadP);
            //System.out.println("Colision izquierda");
        }

        if(ball.getBoundsInParent().intersects(playerB.getBoundsInParent())){
            metodoSound();
            ball.setTranslateY(ball.getTranslateY() + -movimientoY);
            ball.setTranslateX(ball.getTranslateX() + --movimientoX);
            velocidadB += 0.1;
            velocidadP += 0.05;
            //System.out.println(velocidadP);
            //System.out.println("Colision derecha");
        }

        if(ball.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            metodoSound();
            ball.setTranslateX(ball.getTranslateX() + -movimientoX);
            ball.setTranslateY(ball.getTranslateY() + ++movimientoY);
            velocidadB += 0.1;
            velocidadP += 0.05;
            //System.out.println(velocidadP);
            //System.out.println("Colision superior");
        }

        if(ball.getBoundsInParent().intersects(botWall.getBoundsInParent())){
            metodoSound();
            ball.setTranslateX(ball.getTranslateX() + -movimientoX);
            ball.setTranslateY(ball.getTranslateY() + --movimientoY);
            velocidadB += 0.1;
            velocidadP += 0.05;
            //System.out.println(velocidadP);
            //System.out.println("Colision inferior");
        }

        if(playerA.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            velocidad = velocidadP;
            //velocidadP = 1;
            playerA.setTranslateY(playerA.getTranslateY() + -movimientoPA);
            if(playerA.getBoundsInParent().intersects(topWall.getBoundsInParent())){
                //playerA.setTranslateY(-328);
                playerA.setTranslateY(-273);
            }
        }

        if(playerB.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            velocidad = velocidadP;
            //velocidadP = 1;
            playerB.setTranslateY(playerB.getTranslateY() + -movimientoPB);
            if(playerB.getBoundsInParent().intersects(topWall.getBoundsInParent())){
                //playerA.setTranslateY(-328);
                playerB.setTranslateY(-273);
            }
        }

        if(playerA.getBoundsInParent().intersects(botWall.getBoundsInParent())){
            velocidad = velocidadP;
            //velocidadP = 1;
            playerA.setTranslateY(playerA.getTranslateY() + -movimientoPA);
            if(playerA.getBoundsInParent().intersects(botWall.getBoundsInParent())){
                //playerA.setTranslateY(-328);
                playerA.setTranslateY(273);
            }
        }

        if(playerB.getBoundsInParent().intersects(botWall.getBoundsInParent())){
            velocidad = velocidadP;
            //velocidadP = 1;
            playerB.setTranslateY(playerB.getTranslateY() + -movimientoPB);
            velocidadP=velocidad;
            if(playerB.getBoundsInParent().intersects(botWall.getBoundsInParent())){
                //playerA.setTranslateY(-328);
                playerB.setTranslateY(273);
            }
        }


        if(ball.getBoundsInParent().intersects(aWall.getBoundsInParent())){
            puntosB++;
            puntosPlayerB.setText(puntosB+" Points");
            getGanador("PLAYER B WIN");
            animacion.stop();
            System.out.println("Punto del Jugador B");
        }
        if(ball.getBoundsInParent().intersects(bWall.getBoundsInParent())){
            puntosA++;
            puntosPlayerA.setText(puntosA+" Points");
            getGanador("PLAYER A WIN");
            animacion.stop();
            System.out.println("Punto del Jugador A");
        }

    }

    public void getGanador(String texto) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DropShadow ds = new DropShadow ();
        ds.setOffsetY (3.0f);
        ds.setColor (Color.color (0.4f, 0.4f, 0.4f));
        ganador.setEffect (ds);
        ganador.setCache (true);
        ganador.setX (10.0f);
        ganador.setY (270.0f);
        ganador.setFill (Color.RED);
        ganador.setText (texto);
        ganador.setFont (Font.font ("Comic Sans MS", FontWeight.BOLD, 32));
        metodoSoundWin();
    }

    //Controles teclado
    private void controlMenu(KeyEvent e, int i, int i2) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (e.getCode()) {
            case UP:
                movimientoPB = i;
                break;
            case W:
                movimientoPA = i;
                break;
            case DOWN:
                movimientoPB = i2;
                break;
            case S:
                movimientoPA = i2;
                break;
            case M:
                pararMusica();
                metodoSoundGame();
                break;
            case Z:
                pararMusica();
                break;
            case SHIFT:
                System.out.println("UN DESCANSO Y VOLVEMOS"+ count);
                pararMusica();
                animacion.stop();
                break;
            case SPACE:
                pararMusica();
                metodoSoundGame();
                System.out.println("NUEVA PARTIDA");
                ball.setTranslateX(0);
                ball.setTranslateY(0);
                playerA.setTranslateX(0);
                playerA.setTranslateY(0);
                playerB.setTranslateX(0);
                playerB.setTranslateY(0);
                puntosPlayerA.setText("0 points");
                puntosPlayerB.setText("0 points");
                puntosA = 0;
                puntosB = 0;
                movimientoX = 1;
                movimientoY = 1;
                velocidadB = 2;
                velocidadP = 2;
                animacion.stop();
                break;
            case ENTER:
                ball.setTranslateX(0);
                ball.setTranslateY(0);
                playerA.setTranslateX(0);
                playerA.setTranslateY(0);
                playerB.setTranslateX(0);
                playerB.setTranslateY(0);
                movimientoPA = 0;
                movimientoPB = 0;
                movimientoX = 1;
                movimientoY = 1;
                velocidadB = 2;
                velocidadP = 2;
                ganador.setText("");
                animacion.play();
                break;
            case ESCAPE:
                pararMusica();
                animacion.stop();
                App.setMenuV();

        }
    }

    //SONIDOS

    private void metodoSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(sonido).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void metodoSoundWin() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(win).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
    //Música de fondo
    private void metodoSoundGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(game).getAbsoluteFile());
        clip = AudioSystem.getClip();
        if (musica == false) {
            clip.open(audioInputStream);
            clip.start();
        }
        musica = true;
    }

    private void pararMusica () {
        if (musica == true) {
            clip.stop();
            clip.close();
        }
        musica = false;
    }


}
