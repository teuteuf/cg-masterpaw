package fr.teuteuf;

import java.util.Random;

public enum ButtonColor {
    BLUE,
    RED,
    YELLOW,
    GREEN,
    PINK,
    CYAN,
    ORANGE,
    WHITE,
    BLACK;

    public static ButtonColor getRandomButtonColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
