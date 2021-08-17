package io.spaceshiva.kogito.dnd.battle.flow.functions;

public final class DiceUtils {

    private DiceUtils() {

    }

    public static int rollD20() {
        return rollDice(20);
    }

    private static int rollDice(int faces) {
        return (int) (Math.random() * faces + 1);
    }
}
