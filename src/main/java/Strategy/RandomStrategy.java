package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Random strategy class
 */
public class RandomStrategy implements Strategy{

    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public RandomStrategy(PlayerModel player, GameModel gameModel) {
        name = "random";
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
        int armyLeft=player.getNumReinforceArmyRemainPlace();
        int totalArmyLeft=armyLeft;
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        if ((armyLeft>0)&&(playerCountries.size()>0)) {
            while (armyLeft>0){
                player.addArmyNum(armyLeft);
                int selectedCountry=(int)(Math.random() * (playerCountries.size()));
                int reinforcementNumber=(int)(Math.random() * (armyLeft));
                if (reinforcementNumber==0) reinforcementNumber=armyLeft; //To finish faster
                playerCountries.get(selectedCountry).setArmyNum(playerCountries.get(selectedCountry).getArmyNum()+reinforcementNumber);
                armyLeft=armyLeft-reinforcementNumber;
                System.out.println(player.getPlayerName()+" from "+totalArmyLeft+" total reinforcement army/armies, added "+
                        reinforcementNumber+" army/armies to "+ 
                        playerCountries.get(selectedCountry).getCountryName());
            }
            player.setNumArmyRemainPlace(0);
            System.out.println(player.getPlayerName()+" placed all Reinforcement armies successfully! ");
            if (player.getCardList().size()>=5) {
                gameModel.exchangeCards(0,1,2);
            }            
        } else {
            //System.out.println(player.getPlayerName()+" dosent have any countries or Reinforcement armies. Reinforcement skipped.");
        }
    }



    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     */
    @Override
    public void attack(){
        int selectedAttackCountry=0;
        int selectedDefenseCountry=0;
        int numberOfAttack=0;
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        CountryModel destination = null;
        do {
            selectedAttackCountry=(int)(Math.random() * (playerCountries.size()));
            
        } while (getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).size()>0);
        if (playerCountries.get(selectedAttackCountry).getArmyNum()>2){
        System.out.println(playerCountries.get(selectedAttackCountry).getCountryName()+" is Randomly selected as the attacker country of the "+player.getPlayerName());
        }
        selectedDefenseCountry=(int)(Math.random() * (getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).size()));
        if (playerCountries.get(selectedAttackCountry).getArmyNum()>2){
        System.out.println(getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).get(selectedDefenseCountry).getCountryName()+
                " is Randomly selected as the defense country of the "+
                getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).get(selectedDefenseCountry).getOwner().getPlayerName());
        }
        numberOfAttack=playerCountries.get(selectedAttackCountry).getArmyNum();
        
            System.out.println("============= Attack starts =============");
                gameModel.attackAllOut(playerCountries.get(selectedAttackCountry).getCountryName(),getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).get(selectedDefenseCountry).getCountryName());
                if (gameModel.isIfAttackerWin()){
                    gameModel.winnerMove(gameModel.attackerDice.size());
                }
            
        
    }



    /**
     * Fortification method
     * maximize aggregation of forces in one country
     */
    @Override
    public void fortification() {

    }
}
