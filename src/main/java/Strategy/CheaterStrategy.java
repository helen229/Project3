
package Strategy;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
/**
 * Cheater Strategy class
 */
public class CheaterStrategy implements Strategy{

    private String name;
    private PlayerModel player;

    /**
     * constructor
     * @param player player with this strategy
     */
    public CheaterStrategy(PlayerModel player) {
        name = "human";
        this.player = player;
    }


    /**
     * Get name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Reinforcement method
     * reinforces its strongest country
     */
    @Override
    public void reinforcement() {

    }



    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     */
    @Override
    public void attack(){

    }



    /**
     * Fortification method
     * maximize aggregation of forces in one country
     * @param source from source
     * @param target to target
     * @param armyNumber move num of army
     */
    @Override
    public void fortification() {

    }
}
