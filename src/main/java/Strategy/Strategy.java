package Strategy;

import MapEditorModel.CountryModel;

/**
 * the interface of player strategy
 */
public interface Strategy {

    void reinforcement( );

    /**
     * attack method
     * @param attacker the country who attack
     * @param attackerNum the number of dice the attacker use
     * @param defender the country who defend
     * @param defenderNum the number of dice the defender use
     * @param isAllOut true, allout; false, not allout
     */
    void attack(CountryModel attacker, int attackerNum, CountryModel defender, String defenderNum, boolean isAllOut);

    /**
     * move army method after conquer
     * @param num number of armies want to move
     */
    void winnerMove(int num);

    /**
     * fortification method
     * @param source source country
     * @param target target country
     * @param armyNumber number of armies want to move
     * @throws InterruptedException exception
     */
    void fortification(CountryModel source, CountryModel target, int armyNumber);

    /**
     * get the strategy name
     * @return name of strategy
     */
    String getName();

}
