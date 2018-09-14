package mage.cards.c;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.constants.SubType;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;

/**
 *
 * @author TheElk801
 */
public final class CentaurMediator extends CardImpl {

    public CentaurMediator(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{W}");

        this.subtype.add(SubType.CENTAUR);
        this.subtype.add(SubType.CLERIC);

        // When Centaur Mediator enters the battlefield, each player gains 4 life.
        this.addAbility(new EntersBattlefieldTriggeredAbility(
                new CentaurMediatorEffect()
        ));
    }

    public CentaurMediator(final CentaurMediator card) {
        super(card);
    }

    @Override
    public CentaurMediator copy() {
        return new CentaurMediator(this);
    }
}

class CentaurMediatorEffect extends OneShotEffect {

    public CentaurMediatorEffect() {
        super(Outcome.GainLife);
        staticText = "each player gains 4 life.";
    }

    public CentaurMediatorEffect(final CentaurMediatorEffect effect) {
        super(effect);
    }

    @Override
    public CentaurMediatorEffect copy() {
        return new CentaurMediatorEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        game.getState().getPlayersInRange(
                source.getControllerId(), game
        ).stream().map((playerId) -> game.getPlayer(playerId)).filter(
                (player) -> (player != null)
        ).forEachOrdered((player) -> {
            player.gainLife(4, game, source);
        });
        return true;
    }

}
