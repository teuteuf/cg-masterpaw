package com.codingame.game;

import com.codingame.gameengine.core.AbstractMultiplayerPlayer;
import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.GameManager;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;
import fr.teuteuf.ColorCombination;
import fr.teuteuf.NotEnoughtColorException;

import static java.lang.String.format;

public class Referee extends AbstractReferee {
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;

    @Override
    public void init() {
        gameManager.setMaxTurns(9 * 9);
        ColorCombination secretColorCombination = new ColorCombination(2);
        for (Player player : gameManager.getPlayers()) {
            player.initMysteryLock(secretColorCombination);
        }
    }

    @Override
    public void gameTurn(int turn) {
        gameManager.getActivePlayers().forEach((player) -> {
            player.sendInputLine();
            player.execute();
        });

        gameManager.getActivePlayers().forEach((player) -> {
            try {
                boolean succeededToOpen = player.tryToOpen(String.join("", player.getOutputs()));
                if(succeededToOpen) {
                    player.setScore(1);
                    gameManager.addToGameSummary(
                            GameManager.formatSuccessMessage(player.getNicknameToken() + ": Puuuuurrfect!")
                    );
                }
            } catch (TimeoutException e) {
                player.deactivate(format("%s timeout!", player.getNicknameToken()));
            } catch (NotEnoughtColorException e) {
                player.deactivate(format("%s hasn't play right number of colors!", player.getNicknameToken()));
            } catch (Exception e) {
                player.deactivate(format("%s: WTF?", player.getNicknameToken()));
            }
        });

        int nbOfPlayerSucceeded = gameManager.getActivePlayers().stream()
                .mapToInt(AbstractMultiplayerPlayer::getScore)
                .sum();

        if(nbOfPlayerSucceeded > 0) {
            gameManager.endGame();
        }
    }
}
