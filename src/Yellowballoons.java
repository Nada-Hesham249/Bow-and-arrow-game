import processing.core.*;
import processing.core.PImage;

public class Yellowballoons {
    private final PApplet processing;
    PImage yellowballoon;
    PImage pop;
    float xyel;
    float yyel;
    boolean checkyellow;
    float yyelspeed;
    float acc;

    public Yellowballoons(PApplet processing) {
        this.processing = processing;
        yellowballoon = processing.loadImage("yellow.png");
        yellowballoon.resize(75, 75);
        acc = processing.random((float) .1, (float) .3);
        yyelspeed = processing.random((float) 3, (float) 5);
        xyel = processing.random(300, 1000);
        yyel = processing.random(400, 604);
        pop = processing.loadImage("OIP.png");
        pop.resize(75, 75);

    }

    public void draw() {

        processing.image(yellowballoon, xyel, yyel);
    }

    public void update() {
        yyel = yyel - yyelspeed;
        yyelspeed = yyelspeed + acc;
    }

    public void edge() {
        if (yyel <= 110) {
            yyel = processing.height - 110;
            yyelspeed = processing.random((float) 2, (float) 4);
        }
    }

    public void Checkcollision(Arrow arrows) {
        float dy = processing.dist(arrows.x + (float) 37.5, arrows.y, xyel, yyel);

        if (dy <= 25) {
            checkyellow = true;
            Main.shootedballoons2++;
        } else {
            checkyellow = false;
        }
    }

    public void pop() {
        if (!(Main.lev1)) {
            processing.image(pop, xyel, yyel);
        }
    }


}