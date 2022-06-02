package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    Button buttons[][];
    int arr[][];
    Pane pane;

    @Override
    public void start(Stage stage) {
        pane = new Pane();

        setMenu();


        Scene scene = new Scene(pane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void setMenu(){
        pane.getChildren().clear();
        Label label1 = new Label("Enter amount of rows : ");
        label1.setLayoutY(200);
        label1.setLayoutX(80);
        label1.setPrefSize(250,25);
        pane.getChildren().add(label1);

        TextField textField1 = new TextField();
        textField1.setLayoutY(250);
        textField1.setLayoutX(85);
        textField1.setPrefSize(100,50);
        pane.getChildren().add(textField1);

        Label label2 = new Label("Enter amount of columns : ");
        label2.setLayoutY(200);
        label2.setLayoutX(370);
        label2.setPrefSize(250,25);
        pane.getChildren().add(label2);

        TextField textField2 = new TextField();
        textField2.setLayoutY(250);
        textField2.setLayoutX(380);
        textField2.setPrefSize(100,50);
        pane.getChildren().add(textField2);

        Button go = new Button("Build maze ");
        go.setLayoutY(350);
        go.setLayoutX(250);
        go.setPrefSize(100,50);
        pane.getChildren().add(go);
        go.setOnMouseClicked(evt->{





            pane.getChildren().clear();
            int y = Integer.parseInt(textField1.getText());
            int x = Integer.parseInt(textField2.getText());

            buttons = new Button[y][x];
            int xPut = 10;
            int yPut = 60;
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    buttons[i][j] = new Button("...");
                    buttons[i][j].setStyle("-fx-background-color: BURLYWOOD");
                    buttons[i][j].setText("Empty");
                    buttons[i][j].setLayoutX(xPut);
                    buttons[i][j].setLayoutY(yPut);
                    buttons[i][j].setPrefSize((550-10*(x+1))/x,(550-10*(y+1))/y);
                    pane.getChildren().add(buttons[i][j]);
                    xPut+= (550-10*(x+1))/x +10;
                }
                yPut+= (550-10*(y+1))/y+10;
                xPut = 10;
            }

            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.setLayoutX(10);
            comboBox.setLayoutY(10);
            comboBox.setPrefSize(100,25);
            comboBox.getItems().add("Start");
            comboBox.getItems().add("Finish");
            comboBox.getItems().add("Wall");
            comboBox.getItems().add("Empty");
            comboBox.setVisible(true);
            comboBox.setStyle("-fx-background-color: green");
            comboBox.setValue("Empty");
            pane.getChildren().add(comboBox);

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].setOnMouseClicked(ev->{
                        if(comboBox.getValue().equals("Start")){
                            buttons[finalI][finalJ].setStyle("-fx-background-color: yellow");
                            buttons[finalI][finalJ].setText("Start");
                        }
                        else if(comboBox.getValue().equals("Finish")){
                            buttons[finalI][finalJ].setStyle("-fx-background-color: red");
                            buttons[finalI][finalJ].setText("Finish");
                        }
                        else if(comboBox.getValue().equals("Wall")){
                            buttons[finalI][finalJ].setStyle("-fx-background-color: gray");
                            buttons[finalI][finalJ].setText("Wall");
                        }
                        else if(comboBox.getValue().equals("Empty")){
                            buttons[finalI][finalJ].setStyle("-fx-background-color: BURLYWOOD");
                            buttons[finalI][finalJ].setText("Empty");
                        }
                    });
                }
            }

            Button goon = new Button("Solve");
            goon.setLayoutY(10);
            goon.setLayoutX(130);
            goon.setPrefSize(100,25);
            goon.setStyle("-fx-background-color: CHARTREUSE");
            pane.getChildren().add(goon);

            Button random = new Button("Random Fill");
            random.setLayoutX(250);
            random.setLayoutY(10);
            random.setPrefSize(100,25);
            random.setStyle("-fx-background-color: CHARTREUSE");
            pane.getChildren().add(random);
            random.setOnMouseClicked(evt3->{
                int a = (int)(Math.random()*101);
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if(a < 50){
                            buttons[i][j].setStyle("-fx-background-color: gray");
                            buttons[i][j].setText("Wall");
                        }
                        else{
                            buttons[i][j].setStyle("-fx-background-color: BURLYWOOD");
                            buttons[i][j].setText("Empty");
                        }
                        a = (int)(Math.random()*101);
                    }
                }
                int q = (int)(Math.random()*y);
                int q2 = (int)(Math.random()*x);
                buttons[q][q2].setStyle("-fx-background-color: yellow");
                buttons[q][q2].setText("Start");


                q = (int)(Math.random()*y);
                q2 = (int)(Math.random()*x);
                buttons[q][q2].setStyle("-fx-background-color: red");
                buttons[q][q2].setText("Finish");

            });

            goon.setOnMouseClicked(evt2->{
                arr = new int[y][x];

                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if(buttons[i][j].getText().equals("Empty")){
                            arr[i][j] = 0;
                        }
                        else if(buttons[i][j].getText().equals("Wall")){
                            arr[i][j] = 1;
                        }
                        else if(buttons[i][j].getText().equals("Finish")){
                            arr[i][j] = 3;
                        }
                        else if(buttons[i][j].getText().equals("Start")){
                            arr[i][j] = 2;
                        }

                    }
                }

                try{
                    checkMaze(arr,x,y);
                    pane.getChildren().clear();
                    pane.getChildren().add(new Lab(600,600,x,y,arr));
                    Button again = new Button("New Maze");
                    again.setPrefSize(100,25);
                    again.setLayoutY(10);
                    again.setLayoutX(10);
                    again.setStyle("-fx-background-color:blue");
                    pane.getChildren().add(again);
                    again.setOnMouseClicked(evt4->{
                        setMenu();
                    });

                }catch(Exception e){
                    e.printStackTrace();
                }


            });




        });

    }
    
    public void checkMaze(int arr[][],int x,int y) throws MazeException {
        int starts = 0,ends = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if(arr[i][j] == 2)
                    starts++;
                else if(arr[i][j] == 3)
                    ends++;
            }
        }

        if(starts != 1 || ends != 1)
            throw new MazeException("Incorrect input!");
    }




    public static void main(String[] args) {
        launch();
    }



}