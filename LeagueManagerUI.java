import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ggisbert on 3/12/17.
 */
public class LeagueManagerUI {


    Map<String, Team> mLeague;

    private BufferedReader mReader;

    private Map<String, String> mMenu;


    public LeagueManagerUI(Map<String,Team> league) {

        mLeague = new HashMap<String, Team>();

        mReader = new BufferedReader(new InputStreamReader(System.in));

        mMenu = new HashMap<String, String>();

        mMenu.put("Create Team", "Create a new team. Required fields are team name and coach.");
        mMenu.put("Add a Player", "Add a new player to the team.");
        mMenu.put("Remove Player", "Remove a Player. ");
        mMenu.put("Create Team", "Create a new team. Required fields are team name and coach.");
    }


    private String promptAction() throws IOException {

        for (Map.Entry<String, String> option : mMenu.entrySet()) {

            System.out.printf("%s - %s %m", option.getKey(), option.getValue());
        }

        System.out.print("What do you want to do: ");
        String choice = mReader.readLine();

        return choice.trim().toLowerCase();

    }

    public void run() {

        String choice = "";

        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "Create Team":
                        // TODO : Add the functionality to create the team
                        break;
                    case "Add a Player":
                        // TODO : Add the functionality to add the player to a team
                        break;
                    case "quit":
                        System.out.println("Thanks for your help");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. try Again. %n%n%n", choice);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        } while (!choice.equals("quit"));

    }

    public void createTeam(){


        Team team = new Team();

    }


    public void addPlayer(){

    }

    public void promtTeamsFromLeagueOrdered() {

        for (Map.Entry<String, Team> option : mLeague.entrySet()) {
            System.out.printf("%s - %s %m", option.getKey(), option.getValue());
        }
    }

    public void promptPlayersFromTeam(Team team){

    }
}