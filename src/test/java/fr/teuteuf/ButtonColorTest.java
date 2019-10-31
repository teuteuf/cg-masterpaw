package fr.teuteuf;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ButtonColorTest {
    @Test
    void getRandomButtonColor_shouldReturnAButtonColor() {
        // When
        ButtonColor buttonColor = ButtonColor.getRandomButtonColor();

        // Then
        assertThat(buttonColor).isInstanceOf(ButtonColor.class);
    }


    @Test
    void getRandomButtonColor_shouldReturnDifferentValuesOfEnum() {
        // When
        ButtonColor[] distinctRandomButtons = IntStream.range(0, 1000)
                .mapToObj((i) -> ButtonColor.getRandomButtonColor())
                .distinct()
                .toArray(ButtonColor[]::new);

        // Then
        assertThat(distinctRandomButtons.length).isEqualTo(ButtonColor.values().length);
    }
}