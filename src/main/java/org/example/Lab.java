package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Lab extends Pane {
    private int width;
    private int height;
    private int amountOfBlocksPerRow;
    private int amountOfBlocksPerCol;
    private int startX;
    private int startY;
    private int arr[][];
    private boolean isFound = false;

    public Lab(int width,int height,int amountOfBlocksPerRow,int amountOfBlocksPerCol,int arr[][]) {
        super();
        this.width = width;
        this.height = height;
        this.amountOfBlocksPerCol = amountOfBlocksPerCol;
        this.amountOfBlocksPerRow = amountOfBlocksPerRow;

        this.arr = new int[amountOfBlocksPerCol][amountOfBlocksPerRow];
        for (int i = 0; i < amountOfBlocksPerCol; i++) {
            for (int j = 0; j < amountOfBlocksPerRow; j++) {
                this.arr[i][j] = arr[i][j];
            }
        }

        int amountOfYBlocks = height / amountOfBlocksPerCol;
        int amountOfXBlocks = width / amountOfBlocksPerRow;


        for (int i = 0; i < amountOfBlocksPerCol; i++) {
            for (int j = 0; j < amountOfBlocksPerRow; j++) {
                if(arr[i][j] == 2){
                    startX = j;
                    startY = i;
                }
            }
        }

        for (int i = 0; i < amountOfBlocksPerCol; i++) {
            for (int j = 0; j < amountOfBlocksPerRow; j++) {
                if (arr[i][j] == 1) {
                    Rectangle rect = new Rectangle(j * amountOfXBlocks, i * amountOfYBlocks, amountOfXBlocks, amountOfYBlocks);
                    rect.setFill(Color.GRAY);
                    getChildren().add(rect);
                } else if (arr[i][j] == 0) {
                    Rectangle rect = new Rectangle(j * amountOfXBlocks, i * amountOfYBlocks, amountOfXBlocks, amountOfYBlocks);
                    rect.setFill(Color.BURLYWOOD);
                    getChildren().add(rect);
                } else if (arr[i][j] == 2) {
                    Rectangle rect = new Rectangle(j * amountOfXBlocks, i * amountOfYBlocks, amountOfXBlocks, amountOfYBlocks);
                    rect.setFill(Color.YELLOW);
                    getChildren().add(rect);
                } else if (arr[i][j] == 3) {
                    Rectangle rect = new Rectangle(j * amountOfXBlocks, i * amountOfYBlocks, amountOfXBlocks, amountOfYBlocks);
                    rect.setFill(Color.RED);
                    getChildren().add(rect);
                }
            }
        }

        //getPath(startX,startY);



        int EndX = 0,EndY = 0;
        for (int i = 0; i < amountOfBlocksPerCol; i++) {
            for (int j = 0; j < amountOfBlocksPerRow; j++) {
                if(arr[i][j] == 3){
                    EndX = j;
                    EndY = i;
                }

            }
        }
        
        char matrix[][] = new char[amountOfBlocksPerCol][amountOfBlocksPerRow];
        for (int i = 0; i < amountOfBlocksPerCol; i++) {
            for (int j = 0; j < amountOfBlocksPerRow; j++) {
                if(arr[i][j] == 1)
                    matrix[i][j] = '^';
                else
                    matrix[i][j] = '.';
            }
        }

        newSolution newSolution = new newSolution(matrix,amountOfBlocksPerCol,amountOfBlocksPerRow);
        ArrayList<Point> answer = newSolution.FindPath(new Point(startX,startY),new Point(EndX,EndY));

        try {
            for (int i = 0; i < answer.size(); i++) {
                drawCircle(answer.get(i).X,answer.get(i).Y,Color.GREEN);
            }
            drawCircle(startX,startY,Color.YELLOW);
            drawCircle(EndX,EndY,Color.RED);
        }catch (Exception e){
            getPath(startX,startY);
            drawCircle(startX,startY,Color.YELLOW);
            drawCircle(EndX,EndY,Color.RED);
            Label label = new Label("Path wasn't found!");
            label.setPrefSize(400,25);
            label.setLayoutX(150);
            label.setLayoutY(287.5);
            label.setFont(new Font("Arial", 40));

            getChildren().add(label);
        }

        
        
        



    }

    public void getPath(int startX,int startY){
        if(!isFound){
            drawCircle(startX,startY,Color.GREEN);
            if(startX+1 < amountOfBlocksPerRow && arr[startY][startX+1] != 1 && startX < amountOfBlocksPerRow && startY < amountOfBlocksPerCol && startX >= 0 && startY >=0 && arr[startY][startX+1] != 4 && !isFound){
                if(arr[startY][startX] != 3){
                    arr[startY][startX] = 4;
                    getPath(startX+1,startY);
                }
                else
                    isFound = true;
            }

            if(startY-1 >= 0 && arr[startY-1][startX] != 1 && startX < amountOfBlocksPerRow && startY < amountOfBlocksPerCol && startX >= 0 && startY >=0&& arr[startY-1][startX] != 4&& !isFound){
                if(arr[startY][startX] != 3){
                    arr[startY][startX] = 4;
                    getPath(startX,startY-1);
                }
                else
                    isFound = true;
            }



            if(startX-1 >= 0 && arr[startY][startX-1] != 1 && startX < amountOfBlocksPerRow && startY < amountOfBlocksPerCol && startX >= 0 && startY >=0&& arr[startY][startX-1] != 4&& !isFound){
                if(arr[startY][startX] != 3){
                    arr[startY][startX] = 4;
                    getPath(startX-1,startY);
                }
                else
                    isFound = true;
            }

            if(startY+1 < amountOfBlocksPerCol && arr[startY+1][startX] != 1 && startX < amountOfBlocksPerRow && startY < amountOfBlocksPerCol && startX >= 0 && startY >=0&& arr[startY+1][startX] != 4&& !isFound){
                if(arr[startY][startX] != 3){
                    arr[startY][startX] = 4;
                    getPath(startX,startY+1);
                }
                else
                    isFound = true;
            }
        }


    }

    public void drawCircle(int x,int y,Color color){

            int amountOfYBlocks = height / amountOfBlocksPerCol;
            int amountOfXBlocks = width / amountOfBlocksPerRow;
            Rectangle rectangle = new Rectangle( x*amountOfXBlocks,y*amountOfYBlocks,amountOfXBlocks,amountOfYBlocks);
            rectangle.setFill(color);
            getChildren().add(rectangle);

    }

}
