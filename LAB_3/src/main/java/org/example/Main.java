package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world in new Game from LAB3!");
        SwingUtilities.invokeLater(() -> new Game(8));
    }
}