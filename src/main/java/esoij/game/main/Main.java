package esoij.game.main;

public class Main {
    public static Thread mainThread;
    public static String[] args;
    public static void main(String[] args) {
        Main.args = args;
        mainThread = new Thread(new MainThread());
        mainThread.setName("Main Thread");
        mainThread.start();
    }
}