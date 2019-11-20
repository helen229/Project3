package Strategy;


import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;

/**
 * Aggressive strategy class
 */
public class AggressiveStrategy implements Strategy {

    private String name;
    private PlayerModel player;

    /**
     * constructor
     * @param player player with this strategy
     */
    public AggressiveStrategy(PlayerModel player) {
        name = "aggressive";
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
        int max = 0;
//        CountryModel destination = null;
//        for (CountryModel country : player.getPlayerCountries()) {
//            //loop all countries that belong to this player
//            if (destination.getDefendersAroundThisCountry().size() > 0) {
//                if (country.getArmyNum() > max) {
//                    max = country.getArmyNum();
//                    destination = country;
//                }
//            }
//        }
//        if (destination != null) {
//            int getReinfoArmyNum = curPlayer.getNumberOfArmy();
//            curPlayer.setArmyList(getReinfoArmyNum);
//            while (getReinfoArmyNum > 0) {
//                destination.AddArmy();
//                getReinfoArmyNum--;
//            }
//        }
    }



    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     * @param attacker null
     * @param attackerNum 0
     * @param defender null
     * @param defenderNum 0
     * @param isAllOut true
     */
    @Override
    public void attack(CountryModel attacker, int attackerNum, CountryModel defender, String defenderNum, boolean isAllOut){
        System.out.println(" ");
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
     * Move army method
     * move the mininum armies that could
     * @param num number of army to move
     */
    @Override
    public void winnerMove(int num) {

//        int numArmies = Integer.valueOf(num);
//        Country attacker = player.getAttacker();
//        Country defender = player.getDefender();
//
//        attacker.setArmies(attacker.getArmies() - numArmies);
//        defender.setArmies(defender.getArmies() + numArmies);
//
//        Phase.getInstance().setActionResult(Action.Show_Next_Phase_Button);
//        Phase.getInstance().setInvalidInfo("Army Movement Finish. You Can Start Another Attack Or Enter Next Phase Now");
    }


    /**
     * Fortification method
     * maximize aggregation of forces in one country
     * @param source from source
     * @param target to target
     * @param armyNumber move num of army
     */
    @Override
    public void fortification(CountryModel source, CountryModel target, int armyNumber) {

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
