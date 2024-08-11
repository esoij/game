package esoij.game.main;

import esoij.game.input.InputThread;

public class Main {
    public static Thread mainThread;
    public static Thread inputThread;
    public static void main(String[] args) {
        mainThread = new Thread(new MainThread());
        mainThread.setName("Main Thread");
        mainThread.start();
        //unsure if having a separate thread for inputs is safe, but who cares. my thinking is that it'll let you send inputs even when the main thread is lagging,
        //which is very good i think
        inputThread = new Thread(new InputThread());
        inputThread.setName("Input Thread");
        inputThread.start();
    }
}