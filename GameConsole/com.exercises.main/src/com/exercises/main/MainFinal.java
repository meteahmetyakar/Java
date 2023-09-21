package com.exercises.main;

import com.exercises.game.Game;
import com.exercises.game.GameConsole;
import com.exercises.game.Player;

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
