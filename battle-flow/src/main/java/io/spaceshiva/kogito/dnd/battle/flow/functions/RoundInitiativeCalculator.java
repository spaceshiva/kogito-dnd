package io.spaceshiva.kogito.dnd.battle.flow.functions;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import io.spaceshiva.kogito.dnd.battle.flow.Battle;

@ApplicationScoped
public class RoundInitiativeCalculator {

    @Inject
    JsonConverter jsonConverter;

    public RoundInitiativeCalculator() {

    }

    public JsonNode defineActiveCharacter(final JsonNode jsonNode) {
        final Battle battle = jsonConverter.fromJson(jsonNode);
        battle.incrementRound();
        if (battle.getTurn() <= 0 || battle.getTurn() == battle.getInitiativeQueue().size()) {
            battle.setTurn(1);
        } else {
            battle.setTurn(battle.getTurn() + 1);
        }
        battle.setActiveCharacterId(battle.getInitiativeQueue().get(battle.getTurn()));
        return jsonConverter.toJson(battle);
    }

    public JsonNode calculate(final JsonNode jsonNode) {
        final Battle battle = jsonConverter.fromJson(jsonNode);
        // clear the queue and the active character
        battle.getInitiativeQueue().clear();
        battle.setActiveCharacterId(null);
        // roll the initiative
        int playerInitiative = DiceUtils.rollD20() + battle.getPlayer().getDexterity();
        int enemyInitiative = DiceUtils.rollD20() + battle.getEnemy().getDexterity();
        // do the calc
        if (enemyInitiative > playerInitiative) {
            battle.getInitiativeQueue().put(1, battle.getEnemy().getId());
            battle.getInitiativeQueue().put(2, battle.getPlayer().getId());
        } else {
            battle.getInitiativeQueue().put(1, battle.getPlayer().getId());
            battle.getInitiativeQueue().put(2, battle.getEnemy().getId());
        }
        return jsonConverter.toJson(battle);
    }
}
