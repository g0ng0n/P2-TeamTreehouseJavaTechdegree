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
        System.out.printf("Select your Role");

        mMenu.put("Coach", "Coach");
        mMenu.put("Administrator", "Administrator");
        mMenu.put("Organizer", "Administrator");

        for (Map.Entry<String, String> option : mMenu.entrySet()) {

            System.out.printf("%s - %s %m", option.getKey(), option.getValue());
        }

        System.out.print("What do you want to do: ");
        String choice = "";
        do {
            try {
                choice = mReader.readLine();

                switch (choice) {
                    case "Organizer":
                        OrganizerUiDefault organizerUI = new OrganizerUiDefault(league, playersList);
                        organizerUI.run();
                        break;
                    case "Administrator":
                        AdminUI adminUI = new AdminUI(league, playersList);
                        adminUI.run();
                        break;
                    case "Coach":
                        CoachUiDefault coachUI = new CoachUiDefault(league, playersList);
                        coachUI.run();
                        break;
                    case "exit":
                        System.out.println("Thanks for using the League manager");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. try Again. %n%n%n", choice);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
            }

        } while (!choice.equals("exit"));

    }
}
