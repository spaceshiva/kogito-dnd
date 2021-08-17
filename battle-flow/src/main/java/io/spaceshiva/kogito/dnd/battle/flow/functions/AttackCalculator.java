package io.spaceshiva.kogito.dnd.battle.flow.functions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import io.spaceshiva.kogito.dnd.battle.flow.Battle;
import io.spaceshiva.kogito.dnd.battle.flow.Character;

import static io.spaceshiva.kogito.dnd.battle.flow.functions.DiceUtils.rollD20;

@ApplicationScoped
public class AttackCalculator {

    private static final int CRITICAL = 20;
    private static final int BARE_HANDS_MODIFIER = 1;
    private static final int MISS = 1;

    @Inject
    JsonConverter jsonConverter;

    public JsonNode calculate(final JsonNode jsonNode) {
        final Battle battle = jsonConverter.fromJson(jsonNode);
        // we don't have an active char, bye
        // TODO: once we have error handling, throw an exception
        if (battle.getActiveCharacter() == null) {
            return jsonConverter.toJson(battle);
        }
        // the active char will battle the other one
        if (battle.getActiveCharacter().equals(battle.getPlayer())) {
            battle.getEnemy().setDamage(this.fight(battle.getPlayer(), battle.getEnemy()));
        } else {
            battle.getPlayer().setDamage(this.fight(battle.getEnemy(), battle.getPlayer()));
        }
        return jsonConverter.toJson(battle);
    }

    /**
     * Characters fight against each other
     *
     * @return the damage dealt by the attacker in the defender
     */
    private int fight(Character attacker, Character defender) {
        int dice = rollD20();
        if (dice <= MISS) {
            // miss :(
            return 0;
        }
        int attackPoints = dice + attacker.getStrength();
        // hit! /o\
        if (attackPoints >= defender.getArmorClass() || dice == CRITICAL) {
            // this is a simple example, we don't have weapons. :P
            return BARE_HANDS_MODIFIER + attacker.getStrength();
        }
        // miss :(
        return 0;
    }
}
