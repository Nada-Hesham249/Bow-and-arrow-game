import processing.core.*;

public class Redballoon {
    private final PApplet processing;
    boolean check;
    float x1, y1, yspeed1, x2, y2, yspeed2;
    PImage red;
    PImage pop;

    public Redballoon(PApplet processing, int tempx) {
        this.processing = processing;
        red = processing.loadImage("red_balloon.png");
        red.resize(75, 75);
        pop = processing.loadImage("popped.png");
        pop.resize(75, 75);
        yspeed1 = 4;
        yspeed2 = 8;
        x2 = processing.random(300, 1000);
        y2 = processing.random(200, 604);
        x1 = 300 + tempx;
        y1 = 604;
    }

    public void draw() {
        {
            if (Main.lev1) {
                processing.image(red, x1, y1);
            } else {
                processing.image(red, x2, y2);
            }
        }
    }

    public void update() {
        y1 = y1 - yspeed1;
        y2 = y2 - yspeed2;

    }

    public void edge() {
        if (y2 <= 100) {
            y2 = processing.height - 110;

        }
        if (y1 <= 100) {
            y1 = processing.height - 110;

        }


    }

    public void Checkcollision(Arrow arrows) {
        if (Main.lev1) {
            float d = PApplet.dist(arrows.x + (float) 37.5, arrows.y, x1, y1);
            if (d <= 25) {
                check = true;
                Main.shootedballoons1++;
            } else
                check = false;
        } else {
            float d = PApplet.dist(arrows.x + (float) 37.5, arrows.y, x2, y2);
            if (d <= 25) {
                check = true;
                Main.shootedballoons2++;
            } else
                check = false;
        }
    }

    public void pop() {
        if (Main.lev1) {
            processing.image(pop, x1, y1);
        } else {
            processing.image(pop, x2, y2);
        }
    }

}