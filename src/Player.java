import processing.core.*;

public class Player {
    private final PApplet processing;
    private static int y = 604;
    public static int arrowCounter = 21;
    public static int usedy = y;
    PImage player;
    public static boolean picture = false;

    public Player(PApplet processing) {
        this.processing = processing;
    }

    public void draw() {
        if (picture) {
            player = processing.loadImage("girl_arrow.png");
            processing.imageMode(processing.CENTER);
        } else {


            player = processing.loadImage("girl_not (2).png");
            processing.imageMode(processing.CENTER);
        }

        player.resize(100, 100);
        int x = 83;
        processing.image(player, x + 25, usedy);
    }

    public void mouseDragged() {
        int ydragging = y;
        int dy = 5;
        if (processing.mouseY > ydragging) {
            y += dy;
            updateUsedY();
        } else {
            y -= dy;
            updateUsedY();
        }
    }

    private void updateUsedY() {
        usedy = y;
        processing.redraw();
    }
}
