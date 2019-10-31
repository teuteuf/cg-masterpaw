package fr.teuteuf;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ColorCombination {
    private ButtonColor[] colors;

    public ColorCombination(ButtonColor... colors) {
        this.colors = colors;
    }


    public ColorCombination(int combinationLength) {
        this.colors = IntStream.range(0, combinationLength)
                .mapToObj((i) -> ButtonColor.getRandomButtonColor())
                .toArray(ButtonColor[]::new);
    }

    public int getCombinationLength() {
        return colors.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorCombination that = (ColorCombination) o;
        return Arrays.equals(colors, that.colors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(colors);
    }

    public ButtonColor getButtonColor(int buttonIndex) {
        return colors[buttonIndex];
    }
}
