package Model.Levels;

import com.sun.prism.Texture;

public class Tile {

    private float x, y, width, height;

    private Texture texture;

    public Tile(float x, float y, float width, float height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
}
