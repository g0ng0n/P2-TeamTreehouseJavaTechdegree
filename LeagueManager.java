import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here!

        BufferedReader mReader = new BufferedReader(new InputStreamReader(System.in));
        List<Player> playersList = new ArrayList<Player>(Arrays.asList(players));
        Map<String, Team> league = new HashMap<String, Team>();
        Map<String, String> mMenu = new HashMap<String, String>();

        mMenu.put("1", "Coach");
        mMenu.put("2", "Organizer");
        mMenu.put("3", "quit the app");

        String choice = "";
        do {
            try {
                System.out.printf("Select a Role Option: %n%n");

                for (Map.Entry<String, String> option : mMenu.entrySet()) {

                    System.out.printf("%s - %s %n", option.getKey(), option.getValue());
                }
                choice = mReader.readLine().trim().toLowerCase();

                switch (choice) {
                    case "1":
                        OrganizerUiDefault organizerUI = new OrganizerUiDefault(league, playersList);
                        organizerUI.run();
                        break;
                    case "2":
                        CoachUiDefault coachUI = new CoachUiDefault(league, playersList);
                        coachUI.run();
                        break;
                    case "0":
                        System.out.println("Thanks for using the League manager");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. try Again. %n%n%n", choice);
                        System.out.printf("Select a New Option: %n");

                }


            } catch (IOException ioe) {
                System.out.println("Problem with input");
            }

        } while (!choice.equals("0"));

    }
}
