import processing.core.*;

public class Arrow {
    private final PApplet processing;
    public int x = 75, y;
    Boolean Isactive = true;
    PImage arr, surprise;
    public static boolean surpriseFired = false;
    public static boolean flag = false;

    public Arrow(PApplet processing) {
        this.processing = processing;
        this.y = Player.usedy + 19;
        arr = processing.loadImage("arrow.png");
        arr.resize(75, 75);
        surprise = processing.loadImage("apple.png");
        surprise.resize(75, 75);
    }

    public void update() {
        int dx = 10;
        if (Isactive && Player.arrowCounter > 0) {
            if (!surpriseFired || flag) {
                processing.image(arr, x + 40, y - 24);
            } else {
                processing.image(surprise, x, y - 24);
            }
            x += dx;
            if (x > 1220) {
                Isactive = false;
                if (surpriseFired) {
                    flag = true;
                }
                x = 75;
            }
        }
    }

    public void firearrow() {
        if (Player.arrowCounter > 0) {
            Isactive = true;
        }
    }
}
