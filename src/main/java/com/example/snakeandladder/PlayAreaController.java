package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    @FXML
    Text number;
    @FXML
    Text changeturn;
    @FXML
    ImageView player1,player2,first,second;

    int turn =1;
    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeladderCoordinatechanges;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        getSnakeLadderCoordinates();
        Random random=new Random();
        int rolling=random.nextInt(6)+1;
        number.setText(""+rolling);
        if(turn==1){
            Pair<Double,Double>moveCoordinates=movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(snakeladderCoordinatechanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player1.setTranslateX(snakeladderCoordinatechanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeladderCoordinatechanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }
        else{
            Pair<Double,Double>moveCoordinates=movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeladderCoordinatechanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player2.setTranslateX(snakeladderCoordinatechanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeladderCoordinatechanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        if(rolling!=6) {
            if (turn == 1) {
                turn = 2;
                changeturn.setText("Player 2's turn");
                first.setOpacity(0);
                second.setOpacity(1);
            } else {
                turn = 1;
                changeturn.setText("Player 1's turn");
                first.setOpacity(1);
                second.setOpacity(0);
            }
        }


    }

    Pair<Double, Double> movement(double X, double Y, int rolling) {
        double mX = X;
        double mY = Y;
        if(mY%100==0) {
            mX += rolling * 50;
            if (mX > 500) {
                mX = 500 * 2 - mX + 50;
                mY -= 50;
            }
        }
        else{
            mX-=rolling*50;

            if(mX<50){
                if(mY==-450)
                    return new Pair<>(X,Y);
                mX=-1*(mX-50);
                mY-=50;
            }
        }
        return new Pair<>(mX, mY);
    }
    void checkWin(Double X,Double Y) throws IOException {
        if(X==50 && Y==-450){
            Alert winAlert=new Alert(Alert.AlertType.INFORMATION);
            winAlert.setHeaderText("Result");
            if(turn==1)
                winAlert.setContentText("player 1 has won the game");
            else
                winAlert.setContentText("player 2 has won the game");
            GamePage page=new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
            winAlert.showAndWait();
        }
    }
    void getSnakeLadderCoordinates(){
        snakeladderCoordinatechanges=new HashMap<>();
        snakeladderCoordinatechanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeladderCoordinatechanges.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));
        snakeladderCoordinatechanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeladderCoordinatechanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
    }
}
