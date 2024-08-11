package esoij.game.util;

public class Texture {
    public String fileName;
    public int id;
    public Texture(String fileName, int id, int width, int height) {
        this.fileName = fileName;
        this.id = id;
    }
    public static Texture loadTexture(String fileName) {
        //todo
        return null;
    }
}
