package Strategy;


import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Aggressive strategy class
 */
public class AggressiveStrategy implements Strategy {

    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public AggressiveStrategy(PlayerModel player, GameModel gameModel) {
        name = "aggressive";
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

        CountryModel strongestCountry = getStrongestCountry(player.getPlayerCountries());
        if (strongestCountry != null) {
            System.out.println(strongestCountry.getCountryName()+" is the strongest country of "+player.getPlayerName());
            for (CountryModel defendCountry : getAttackableNeighbours(strongestCountry)) {
                if (strongestCountry.getArmyNum()<2)
                    return;
                System.out.println("============="+strongestCountry.getCountryName()+" Attack starts=============");
                gameModel.attackAllOut(strongestCountry.getCountryName(),defendCountry.getCountryName());
                if (gameModel.isIfAttackerWin()){
                    gameModel.winnerMove(gameModel.attackerDice.size());
                }
            }

        }
//        System.out.println(player.getName() + " enter the attack phase");
//        System.out.println(" ");
//
//        // attacker is the strongest country
//        Country strongest = player.getCountriesOwned().stream()
//                .max(Comparator.comparingLong(Country::getArmies))
//                .get();
//
//        List<Country> enemies = strongest.getAdjCountries().stream()
//                .filter(c -> !c.getOwner().equals(player))
//                .collect(Collectors.toList());
//
//        for (Country enemy : enemies) {
//            if (strongest.getArmies() >= 2) {
//                player.allOut(strongest, enemy);
//
//                if (Phase.getInstance().getActionResult() == Action.Win) {
//                    Phase.getInstance().update();
//                    player.addRandomCard();
//                    return;
//                }
//
//                if (Phase.getInstance().getActionResult() == Action.Move_After_Conquer) {
//                    moveArmy(String.valueOf(player.getAttackerDiceNum()));
//                }
//            }
//        }
//
//        player.addRandomCard();
//        Phase.getInstance().update();
//        Tool.printBasicInfo(player,"After attack: ");

//        sleep(500);
    }


    /**
     * Fortification method
     * maximize aggregation of forces in one country
     */
    @Override
    public void fortification() {

        CountryModel targetCountry = getStrongestCountry(player.getPlayerCountries());
        CountryModel sourceCountry = null;
        ArrayList<CountryModel> candidateList = new ArrayList<>();
        for (CountryModel country: player.getPlayerCountries()) {
            candidateList.add(country);
        }
        candidateList.remove(targetCountry);

        int max = 0;

        ArrayList<Boolean> visitedCountryList=new ArrayList<>();
        for (int i = 0; i < gameModel.getMapModel().getCountryList().size(); i++) {
            visitedCountryList.add(false);
        }

        if (targetCountry != null) {
            for (CountryModel country : candidateList) {
                if (country.getArmyNum()>max && gameModel.existPath(country, targetCountry, visitedCountryList)){
                    max = country.getArmyNum();
                    sourceCountry = country;
                }
            }
            gameModel.fortify(sourceCountry.getCountryName(),targetCountry.getCountryName(),sourceCountry.getArmyNum()-1);
        }else{
            gameModel.fortifyNone();
        }
//        System.out.println(player.getName() + " enter the fortification phase");
//        System.out.println(" ");
//        List<Country> decreaseSorted = player.getCountriesOwned().stream()
//                .sorted((c1, c2) -> {
//                    if (c2.getArmies() - c1.getArmies() > 0 ) return 1;
//                    else if (c2.getArmies() - c1.getArmies() == 0) return 0;
//                    else return -1; })
//                .collect(Collectors.toList());
//
//        for (int i = 0; i < decreaseSorted.size(); i++) {
//            for (int j = 1; j < decreaseSorted.size(); j++) {
//
//                Country c1 = decreaseSorted.get(i);
//                Country c2 = decreaseSorted.get(j);
//
//                if (player.isConnected(c1, c2)){
//
//                    System.out.println("From Country : "+c2.getName());
//                    System.out.println("TO Country : "+c1.getName());
//
//                    // re-allocated armies
//                    c1.setArmies(c1.getArmies() + c2.getArmies());
//                    System.out.println("Move "+c2.getArmies() +" Armies");
//                    c2.setArmies(0);
//
//                    Phase.getInstance().setActionResult(Action.Show_Next_Phase_Button);
//                    Phase.getInstance().update();
//
//                    Tool.printBasicInfo(player,"After fortification: ");
//                    return;
//                }
//            }
//        }
//
//        Tool.printBasicInfo(player,"After fortification: ");
//        sleep(500);
    }
}
