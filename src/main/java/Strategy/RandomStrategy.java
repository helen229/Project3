package Strategy;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
/**
 * Random strategy class
 */
public class RandomStrategy implements Strategy{

    private String name;
    private PlayerModel player;

    /**
     * constructor
     * @param player player with this strategy
     */
    public RandomStrategy(PlayerModel player) {
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
     */
    @Override
    public void fortification() {

    }
}
