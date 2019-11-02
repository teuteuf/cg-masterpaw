package com.codingame.game;
import com.codingame.gameengine.core.AbstractMultiplayerPlayer;
import fr.teuteuf.ButtonColor;
import fr.teuteuf.ColorCombination;
import fr.teuteuf.MysteryLock;
import fr.teuteuf.NotEnoughtColorException;

import java.util.Arrays;

import static java.lang.String.format;

public class Player extends AbstractMultiplayerPlayer {

    private MysteryLock mysteryLock;

    @Override
    public int getExpectedOutputLines() {
        return 1;
    }

    public void initMysteryLock(ColorCombination secretCombination) {
        mysteryLock = new MysteryLock(secretCombination);
    }

    public boolean tryToOpen(String playerOutput) throws NotEnoughtColorException {
        ButtonColor[] buttonColors = Arrays.stream(playerOutput.split(" "))
                .map(ButtonColor::valueOf)
                .toArray(ButtonColor[]::new);

        return mysteryLock.tryToOpen(new ColorCombination(buttonColors));
    }

    public void sendInputLine() {
        this.sendInputLine(format("%d %d", mysteryLock.getNbPurrfect(), mysteryLock.getNbMeow()));
    }
}
