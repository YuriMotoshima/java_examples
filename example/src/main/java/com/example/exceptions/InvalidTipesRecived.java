package com.example.exceptions;

public class InvalidTipesRecived extends Exception {
    public InvalidTipesRecived(String message) {
        super(message);
    }
}