package fr.teuteuf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.teuteuf.ButtonColor.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class MysteryLockTest {

    private ColorCombination secretCombination;
    private ColorCombination validCombination;
    private ColorCombination wrongCombination;
    private ColorCombination tooShortCombination;
    private MysteryLock mysteryLock;


    @BeforeEach
    void setUp() {
        secretCombination = new ColorCombination(BLUE, RED, BLUE);
        validCombination = new ColorCombination(BLUE, RED, BLUE);
        tooShortCombination = new ColorCombination(WHITE);
        wrongCombination = new ColorCombination(BLACK, BLACK, BLACK);

        mysteryLock = new MysteryLock(secretCombination);
    }

    @Test
    void tryToOpen_shouldThrowExceptionIfNotGoodNumberOfColors() {
        // When
        Throwable exception = catchThrowable(() -> mysteryLock.tryToOpen(tooShortCombination));

        // Then
        assertThat(exception).isInstanceOf(NotEnoughtColorException.class);
    }

    @Test
    void tryToOpen_shouldReturnTrueIfCombinationAreEquals() throws NotEnoughtColorException {
        // When
        boolean opened = mysteryLock.tryToOpen(validCombination);

        // Then
        assertThat(opened).isTrue();
    }

    @Test
    void tryToOpen_shouldReturnFalseIfCombinationAreNotEquals() throws NotEnoughtColorException {
        // When
        boolean opened = mysteryLock.tryToOpen(wrongCombination);

        // Then
        assertThat(opened).isFalse();
    }

    @Test
    void getNbPurrfect_shouldReturnZeroWhenNoAttemptTried() {
        // When
        int nbPurrfect = mysteryLock.getNbPurrfect();

        // Then
        assertThat(nbPurrfect).isZero();
    }

    @Test
    void getNbPurrfect_shouldReturnNumberOfValidColorWithValidPositionOfLastAttempt() throws NotEnoughtColorException {
        // Given
        ColorCombination secretCombination = new ColorCombination(RED, RED, RED);
        ColorCombination combination = new ColorCombination(RED, BLUE, RED);

        MysteryLock mysteryLock = new MysteryLock(secretCombination);

        mysteryLock.tryToOpen(combination);

        // When
        int nbPurrfect = mysteryLock.getNbPurrfect();

        // Then
        assertThat(nbPurrfect).isEqualTo(2);
    }

    @Test
    void getNbMeow_shouldReturnZeroWhenNoAttemptTried() {
        // When
        int nbMeow = mysteryLock.getNbMeow();

        // Then
        assertThat(nbMeow).isZero();
    }

    @Test
    void getNbMeow_shouldReturnValidColorWithInvalidPositionOfLastAttempt() throws NotEnoughtColorException {
        ColorCombination secretCombination = new ColorCombination(RED, WHITE, RED, WHITE);
        ColorCombination combination = new ColorCombination(BLACK, RED, BLACK, RED);

        MysteryLock mysteryLock = new MysteryLock(secretCombination);

        mysteryLock.tryToOpen(combination);

        // When
        int nbMeow = mysteryLock.getNbMeow();

        // Then
        assertThat(nbMeow).isEqualTo(2);
    }

    @Test
    void getNbMeow_shouldReturnValidColorONLYWithInvalidPositionOfLastAttempt() throws NotEnoughtColorException {
        ColorCombination secretCombination = new ColorCombination(BLACK, RED, BLACK, RED);
        ColorCombination combination = new ColorCombination(BLACK, RED, RED, WHITE);

        MysteryLock mysteryLock = new MysteryLock(secretCombination);

        mysteryLock.tryToOpen(combination);

        // When
        int nbMeow = mysteryLock.getNbMeow();

        // Then
        assertThat(nbMeow).isEqualTo(1);
    }
}