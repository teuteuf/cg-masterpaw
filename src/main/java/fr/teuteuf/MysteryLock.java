package fr.teuteuf;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MysteryLock {
    private ColorCombination secretCombination;
    private ColorCombination lastCombinationTried;

    public MysteryLock(ColorCombination secretCombination) {
        this.secretCombination = secretCombination;
    }

    public boolean tryToOpen(ColorCombination colorCombination) throws NotEnoughtColorException {
        if(colorCombination.getCombinationLength() != this.secretCombination.getCombinationLength()) {
            throw new NotEnoughtColorException();
        }
        lastCombinationTried = colorCombination;
        return secretCombination.equals(colorCombination);
    }

    public int getNbPurrfect() {
        if(lastCombinationTried == null) {
            return 0;
        }

        return IntStream.range(0, secretCombination.getCombinationLength())
                .map((i) -> secretCombination.getButtonColor(i) == lastCombinationTried.getButtonColor(i) ? 1 : 0)
                .sum();
    }

    public int getNbMeow() {
        if (lastCombinationTried == null) {
            return 0;
        }

        Stream<ButtonColor> secretCombinationDistinctColor = IntStream.range(0, secretCombination.getCombinationLength())
                .mapToObj((i) -> secretCombination.getButtonColor(i))
                .distinct();

        return secretCombinationDistinctColor.mapToInt((buttonColor) -> Math.min(
                countNumberOfButton(buttonColor, lastCombinationTried),
                countNumberOfButton(buttonColor, secretCombination)
        )).sum() - getNbPurrfect();
    }

    private int countNumberOfButton(ButtonColor buttonColor, ColorCombination colorCombination) {
        return (int) IntStream.range(0, colorCombination.getCombinationLength())
                .mapToObj(colorCombination::getButtonColor)
                .filter((lastCombinationButton) -> lastCombinationButton == buttonColor)
                .count();
    }
}
