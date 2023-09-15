package dev.lpa;

import dev.lpa.game.Game;
import dev.lpa.game.GameConsole;
import dev.lpa.game.Player;
import dev.lpa.pirate.PirateGame;

class SpecialGameConsole <T extends Game<? extends Player>> extends GameConsole<Game<? extends Player>>
{

    private SpecialGameConsole(Game<? extends Player> game) {
        super(game);
    }
}

public class MainFinal {

    public static void main(String[] args) {

    }

}
