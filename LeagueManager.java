import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.HashMap;
import java.util.Map;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    Map<String,Team> league = new HashMap<String,Team>();

    LeagueManagerUI ui = new LeagueManagerUI(league);
  }
}
