package org.example;

public class MazeException extends Exception {
    public MazeException(String str){
        super(str);
        System.out.println(str);
    }
}
