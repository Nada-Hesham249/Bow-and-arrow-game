import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    Player archer;
    public static int shootedballoons1, shootedballoons2;
    Arrow arrow;
    Redballoon red;
    Yellowballoons yellowballoon;
    public static boolean lev1 = true;
    boolean start = true;
    boolean end = false;
    Boolean button = false;
    ArrayList<Arrow> arrows = new ArrayList<>();
    ArrayList<Yellowballoons> yellowballoons;
    ArrayList<Redballoon> rbal;//balloon class
    PImage background1, background2, initialize, game_over, levelup, winner;
    Score_Manager score;
    int score2;
    boolean flag = true, levelUp = false;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        size(1280, 720);
    }

    public void setup() {
        archer = new Player(this);
        arrow = new Arrow(this);
        rbal = new ArrayList<>();
        yellowballoons = new ArrayList<>();
        score = new Score_Manager();
        background1 = loadImage("slide1.png");
        background2 = loadImage("slide2.png");
        initialize = loadImage("slide4.png");
        game_over = loadImage("slide5.png");
        levelup = loadImage("slide3.png");
        winner = loadImage("slide6.png");

    }

    public void draw() {
        background(initialize);
        if (millis() > 3000) {
            textSize(20);
            if (lev1) {
                background(background1);
                text("1", 252, 681);
                fill(0);
                text(score2, 631, 681);
            } else {
                background(background2);
                fill(0);
                text(score2, 631, 681);
                text("2", 252, 681);
            }
            if (Player.arrowCounter >= 0) {
                textSize(20);
                fill(0);
                text(Player.arrowCounter - 1, 1035, 681);
            }
            if (start) {
                for (int i = 0; i < 15; i++) {
                    rbal.add(new Redballoon(this, i * 55));
                }
                if (end) {
                    for (int p = 0; p < 3; p++) {
                        yellowballoons.add(new Yellowballoons(this));
                    }
                }
                start = false;
                end = true;
            }
            if (rbal.isEmpty() && lev1) {
                flag = false;
                levelUp = true;
                background(levelup);
                textSize(60);
                fill(0);
                text(score2, 235, 242);

            }
            if (!lev1) {
                for (int k2 = 0; k2 < yellowballoons.size(); k2++) {
                    if (k2 < yellowballoons.size()) { //to avoid any exception
                        yellowballoon = yellowballoons.get(k2);
                        yellowballoon.draw();
                        yellowballoon.update();
                        yellowballoon.edge();
                    }
                }
            }

            for (int i = 0; i < rbal.size(); i++) {
                if (i < rbal.size()) { //to avoid any exception
                    red = rbal.get(i);
                    red.draw();
                    red.update();
                    red.edge();
                }
            }
            if (flag) {
                archer.draw();
                score2 = Score_Manager.accumulated_score + score.level1(Player.arrowCounter, shootedballoons1) + score.level2(Player.arrowCounter, shootedballoons2);
            }

        }
        if (flag) {
            for (int j = arrows.size() - 1; j >= 0; j--) {
                arrow = arrows.get(j);
                if (!lev1 && !Arrow.surpriseFired && mousePressed) {
                    float randomValue = random(0, 60);
                    float surpriseThreshold = 0.9f;
                    if (randomValue < surpriseThreshold) {
                        Arrow.surpriseFired = true;
                        arrow.update();
                        break;
                    }
                }
                if (!Arrow.surpriseFired || Arrow.flag) {
                    for (int i = 0; i < rbal.size(); i++) {
                        if (i < rbal.size()) { //to avoid any exception
                            red = rbal.get(i);
                        }
                        red.Checkcollision(arrow);
                        if (red.check) {
                            red.pop();
                            rbal.remove(i);

                        }
                        for (int n = 0; n < yellowballoons.size(); n++) {
                            if (n < yellowballoons.size()) { //to avoid any exception
                                yellowballoon = yellowballoons.get(n);
                                yellowballoon.Checkcollision(arrow);
                                if (yellowballoon.checkyellow) {
                                    yellowballoon.pop();
                                    yellowballoons.remove(n);

                                }
                            }
                        }
                    }
                }
                arrow.update();
                if (!arrow.Isactive) {
                    arrows.remove(j);
                }
            }
        }
        if (lev1) {
            if ((Player.arrowCounter) <= 0 && !rbal.isEmpty()) {
                background(game_over);
                arrows.clear();
                textSize(40);
                fill(0);
                text(score2, 253, 332);
                score2 = 0;
            }
        }
        if (!lev1) {
            if ((Player.arrowCounter) <= 0 && (!rbal.isEmpty() || !yellowballoons.isEmpty())) {
                background(game_over);
                arrows.clear();
                textSize(40);
                fill(0);
                text(score2, 253, 332);
                score2 = 0;
            }
        }
        if (rbal.isEmpty() && yellowballoons.isEmpty() && !lev1) {
            background(winner);
            levelUp = true;
            textSize(60);
            fill(0);
            text(score2, 235, 242);
        }
    }

    public void level1() {
        lev1 = true;
        start = true;
        end = false;
        rbal.clear();
        arrows.clear();
        yellowballoons.clear();
        Player.arrowCounter = 21;
        flag = true;
        Arrow.surpriseFired = false;
        Arrow.flag = false;
        draw();
        button = false;
        Player.picture = false;
    }

    public void mouseMoved() {
        archer.mouseDragged();
    }

    public void mousePressed() {
        if (mouseButton == LEFT && Player.arrowCounter <= 0) {
            if (mouseX >= 43 && mouseX < 445) {
                shootedballoons2 = 0;
                shootedballoons1 = 0;
                Score_Manager.accumulated_score = 0;
                level1();
            }
        }
        if (mouseButton == LEFT && levelUp) {
            levelUp = false;
            Score_Manager.accumulated_score = score2;
            shootedballoons2 = 0;
            shootedballoons1 = 0;
            if (mouseX >= 46 && mouseX <= 387 && mouseY >= 363 && mouseY <= 418) {
                level1();
            } else if (mouseX >= 46 && mouseX <= 387 && mouseY >= 494 && mouseY <= 555) {
                flag = true;
                start = true;
                lev1 = false;
                end = true;
                Arrow.surpriseFired = false;
                Arrow.flag = false;
                arrows.clear();
                Player.arrowCounter = 21;
                button = false;
                Player.picture = false;
            }
        }

        if (millis() > 3000 && flag) {
            if (mouseButton == RIGHT) {
                button = true;
                Player.picture = true;
            } else if (mouseButton == LEFT && button) {
                Player.picture = false;
                arrow.firearrow();
                arrows.add(new Arrow(this));
                Player.arrowCounter--;
                button = false;
            }
        }
    }

}