package org.example.exceptions;

public class WrongBoardSize extends RuntimeException{
    public WrongBoardSize(String message){
        super(message);
    }
}
