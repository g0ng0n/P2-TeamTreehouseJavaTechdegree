import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ggisbert on 3/12/17.
 */
public class LeagueManagerUI {


    Map<String, Team> mLeague;

    private BufferedReader mReader;

    private Map<String, String> mMenu;


    public LeagueManagerUI(Map<String, Team> league) {

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
                        createTeam();
                        break;
                    case "Add a Player":
                        // TODO : Add the functionality to add the player to a team
                        addPlayer();
                        break;
                    case "Remove Player":
                        // TODO : Add the functionality to add the player to a team
                        removePlayer();
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

    public void createTeam() {
        String teamName = null;
        boolean exists = true;
        try {
            while (exists == true) {

                System.out.println("Please enter the name of the Team");
                teamName = mReader.readLine();

                if (mLeague.containsKey(teamName)) {
                    System.out.println("The name you choose already exists");
                } else {
                    exists = false;
                }
            }

            System.out.println("Please enter the name of the Coach");
            String coachName = mReader.readLine();

            Team team = new Team(teamName, coachName);
            mLeague.put(teamName, team);

        } catch (IOException ioe) {
            System.out.println("Problem with input");
            ioe.printStackTrace();
        }
    }


    public void addPlayer() {
        System.out.println("Check this list to see in which team do you want to Add the Player ");
        promtTeamsFromLeagueOrdered();
    }

    public void removePlayer() {
        System.out.println("Check this list to see in which team do you want to Remove the Player ");
        promtTeamsFromLeagueOrdered();
    }

    public void promtTeamsFromLeagueOrdered() {
        List<Team> teamsOrderBy = new ArrayList<Team>();

        for (Map.Entry<String, Team> option : mLeague.entrySet()) {
            teamsOrderBy.add(option.getValue());
        }
        Collections.sort(teamsOrderBy);

        for (Team team : teamsOrderBy) {
            System.out.println(team.getmName());
        }

    }

    public void promptPlayersFromTeam(Team team) {

    }
}