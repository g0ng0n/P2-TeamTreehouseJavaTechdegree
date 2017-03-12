import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.*;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    // Your code here!
    List<Player> playersList = new ArrayList<Player>(Arrays.asList(players));
    Map<String,Team> league = new HashMap<String,Team>();

    OrganizerUI ui = new OrganizerUI(league,playersList);
  }
}
