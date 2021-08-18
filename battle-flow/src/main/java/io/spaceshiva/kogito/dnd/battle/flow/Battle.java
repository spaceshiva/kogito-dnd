package io.spaceshiva.kogito.dnd.battle.flow;

import java.util.HashMap;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the battle! \o/
 */
public class Battle {

    private Character player;
    private Character enemy;
    private String activeCharacterId;
    private HashMap<Integer, String> initiativeQueue;
    private int round;
    private int turn;

    public Battle() {
        this.round = 0;
        this.turn = 0;
        this.initiativeQueue = new HashMap<>();
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Character getEnemy() {
        return enemy;
    }

    public void setEnemy(Character enemy) {
        this.enemy = enemy;
    }

    public String getActiveCharacterId() {
        return activeCharacterId;
    }

    public void setActiveCharacterId(String activeCharacterId) {
        this.activeCharacterId = activeCharacterId;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public HashMap<Integer, String> getInitiativeQueue() {
        return initiativeQueue;
    }

    public void setInitiativeQueue(HashMap<Integer, String> initiativeQueue) {
        this.initiativeQueue = initiativeQueue;
    }

    public void incrementRound() {
        this.round++;
    }

    /**
     * The game is over if either the player or the enemy is dead.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isGameOver() {
        return player.isDead() || enemy.isDead();
    }

    @Override
    public String toString() {
        if (player == null || enemy == null) {
            return "The battle hasn't begun";
        }
        return player + " vs " + enemy + ". \n Round " + round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Battle battle = (Battle) o;
        return round == battle.round && Objects.equals(player, battle.player) && Objects.equals(enemy, battle.enemy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, enemy, round);
    }
}
