package fr.teuteuf;

import org.junit.jupiter.api.Test;

import static fr.teuteuf.ButtonColor.*;
import static org.assertj.core.api.Assertions.assertThat;

class ColorCombinationTest {

    @Test
    void newWithIntParam_shouldReturnCombinationWithGoodNumberOfColors() {
        // Given
        int combinationLength = 20;

        // When
        ColorCombination colorCombination = new ColorCombination(combinationLength);

        // Then
        assertThat(colorCombination.getCombinationLength()).isEqualTo(combinationLength);
    }

    @Test
    void getCombinationLength_shouldReturnNumberOfButtons() {
        // Given
        ColorCombination colorCombination = new ColorCombination(BLUE, RED, GREEN);

        // When
        int combinationLength = colorCombination.getCombinationLength();

        // Then
        assertThat(combinationLength).isEqualTo(3);
    }

    @Test
    void getButtonColor_shouldReturnColorOfButtonCorrespondingToIndex() {
        // Given
        ColorCombination colorCombination = new ColorCombination(RED, GREEN, BLUE);

        // When
        ButtonColor buttonColor = colorCombination.getButtonColor(2);

        // Then
        assertThat(buttonColor).isEqualTo(BLUE);
    }

    @Test
    void equals_shouldReturnTrueIfTwoCombinationAsSameColors() {
        // Given
        ColorCombination colorCombination1 = new ColorCombination(RED, GREEN, BLUE);
        ColorCombination colorCombination2 = new ColorCombination(RED, GREEN, BLUE);

        // When
        boolean areEqual = colorCombination1.equals(colorCombination2);

        // Then
        assertThat(areEqual).isTrue();
    }

    @Test
    void equals_shouldReturnFalseIfTwoCombinationsDoesntHaveSameNumberOfColors() {
        // Given
        ColorCombination colorCombination1 = new ColorCombination(RED, GREEN, BLUE);
        ColorCombination colorCombination2 = new ColorCombination(RED);

        // When
        boolean areEqual = colorCombination1.equals(colorCombination2);

        // Then
        assertThat(areEqual).isFalse();
    }

    @Test
    void equals_shouldReturnFalseIfColorsAreDifferent() {
        // Given
        ColorCombination colorCombination1 = new ColorCombination(RED, GREEN, BLUE);
        ColorCombination colorCombination2 = new ColorCombination(RED, RED, RED);

        // When
        boolean areEqual = colorCombination1.equals(colorCombination2);

        // Then
        assertThat(areEqual).isFalse();
    }
}