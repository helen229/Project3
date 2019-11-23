package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Benevolent Strategy class
 */
public class BenevolentStrategy implements Strategy{


    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public BenevolentStrategy(PlayerModel player, GameModel gameModel) {
        name = "benevolent";
        this.player = player;
        this.gameModel = gameModel;
    }


    /**
     * Get name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    public ArrayList<CountryModel> getAttackableNeighbours(CountryModel country) {
        ArrayList<CountryModel> neighbours = country.getNeighbours();
        ArrayList<CountryModel> attackableNeighbours = new ArrayList<CountryModel>();
        for (CountryModel neighbour: neighbours) {
            if (!neighbour.getOwner().equals(country.getOwner()))
                attackableNeighbours.add(neighbour);
        }
        //sort it to make sure the weakest country is attacked first
        Collections.sort(attackableNeighbours, new Comparator<CountryModel>() {
            @Override
            public int compare(CountryModel o1, CountryModel o2) {
                return (o1.getArmyNum()>=o2.getArmyNum())?(o1.getArmyNum()>o2.getArmyNum()?1:0):-1;
            }
        });
        return attackableNeighbours;
    }
    /**
     * Reinforcement method
     * reinforces its strongest country
     */
    @Override
    public void reinforcement() {

        CountryModel destination = null;
        destination = getStrongestCountry(player.getPlayerCountries());

        if (destination != null) {
            int armyLeft = player.getNumReinforceArmyRemainPlace();
            player.setNumReinforceArmyRemainPlace(0);
            player.setTotalNumReinforceArmy(0);
            player.addArmyNum(armyLeft);
            System.out.println("Place Reinforcement Army Succeed! "+ player.getPlayerName()
                    + " added all the armies to " + destination.getCountryName());
        }else{
            //all countries in a continent could be happen that makes the destination is null
            System.out.println("Assign to a random country since there are no country attackable");
        }
    }


    public CountryModel getStrongestCountry(ArrayList<CountryModel> countryList) {
        CountryModel strongestCountry = null;
        int max = 0;
        for (CountryModel country: countryList) {

            if (getAttackableNeighbours(country).isEmpty()){
                continue;
            }
            if(max<country.getArmyNum()){
                max = country.getArmyNum();
                strongestCountry = country;
            }

        }
        return strongestCountry;
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
        gameModel.fortifyNone();
//        CountryModel targetCountry = getStrongestCountry(player.getPlayerCountries());
//        CountryModel sourceCountry = null;
//        ArrayList<CountryModel> candidateList = new ArrayList<>();
//        for (CountryModel country: player.getPlayerCountries()) {
//            candidateList.add(country);
//        }
//        candidateList.remove(targetCountry);
//
//        int max = 0;
//
//        ArrayList<Boolean> visitedCountryList=new ArrayList<>();
//        for (int i = 0; i < gameModel.getMapModel().getCountryList().size(); i++) {
//            visitedCountryList.add(false);
//        }
//
//        if (targetCountry != null && sourceCountry != null) {
//            for (CountryModel country : candidateList) {
//                if (country.getArmyNum()>max){
//                    max = country.getArmyNum();
//                    sourceCountry = country;
//                }
//            }
//            gameModel.fortify(sourceCountry.getCountryName(),targetCountry.getCountryName(),sourceCountry.getArmyNum()-1);
//        }else{
//            gameModel.fortifyNone();
//        }

//        sleep(500);
    }
}
