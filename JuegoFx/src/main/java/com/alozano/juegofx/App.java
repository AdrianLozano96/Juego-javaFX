package com.alozano.juegofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class App extends Application {

    private static Stage stage;
    private static boolean pantallaCompleta = false;

    @Override
    public void start(Stage stage) throws IOException {
        //Poner un Stage (escenario) y ponerle las escenas
        if(pantallaCompleta==true){
            stage.setFullScreen(true);
        }
        this.stage = stage; //Representa la ventana
        setMenuV(); //Solo muestra esta escena
    }

    public static void setMenuV(){
        Scene scene = new Scene(new MenuV(), 700, 500); //La escena a usar cada stage usa una scena
        if(pantallaCompleta==true){
            stage.setFullScreen(true);
        }
        stage.setTitle(" Menú Juego Fx");    //Titulo de la ventana
        stage.setScene(scene);
        stage.show();   //Muestra ventana principal
    }

    public static void setJuego(){
        Scene scene = new Scene(new JuegoV(), 700, 500);

        stage.setFullScreen(pantallaCompleta);
        stage.setTitle("Juego Fx");
        stage.setScene(scene);
        stage.show();
    }

    public static void setJuegoPong() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        PongV pong = new PongV();

        if(pantallaCompleta == true){
            Scene scenedos = new Scene(pong, 1530, 830);
            stage.setResizable(false);
            stage.setX(0);
            stage.setY(0);
            stage.setTitle("Juego Pong Fx");
            stage.setScene(scenedos);
            stage.show();
        }

        Scene scene = new Scene(pong, 1000, 700);
        stage.setResizable(false);
        stage.setX(250);
        stage.setY(50);
        //stage.setFullScreen(pantallaCompleta);
        //stage.setFullScreen(true);
        stage.setTitle("Juego Pong Fx");
        stage.setScene(scene);
        stage.show();
    }

    public static void options(){
        Scene scene = new Scene(new Options(), 700, 500);
        if(pantallaCompleta==true){
            stage.setFullScreen(true);
        }
        stage.setTitle("Opciones");
        stage.setScene(scene);
        stage.show();
    }

    public static void conf(int op){
        switch(op){
            case 1:
                stage.setFullScreen(true);  //Poner a pantalla completa
                pantallaCompleta = true;
                break;
            case 2:
                stage.setResizable(false);  //Para redimensionar ventana
                break;
            case 3:
                stage.setMaximized(true);   //Pantalla maximizada (se ven los botones de la ventana)
                pantallaCompleta = true;
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }





    public static void main(String[] args) {

        launch();

    }

}