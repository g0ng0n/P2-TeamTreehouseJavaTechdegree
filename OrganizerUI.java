import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ggisbert on 3/12/17.
 */
public class OrganizerUI {


    Map<String, Team> mLeague;
    public static final int MAX_PLAYERS = 11;
    private BufferedReader mReader;
    private List<Player> mPlayersList;

    private Map<String, String> mMenu;

    public OrganizerUI(Map<String, Team> league, List<Player> playersList) {

        mLeague = new HashMap<String, Team>();

        mReader = new BufferedReader(new InputStreamReader(System.in));
        mPlayersList = playersList;
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
                        createTeam();
                        break;
                    case "Add a Player":
                        addPlayer();
                        break;
                    case "Remove Player":
                        removePlayer();
                        break;
                    case "Generate Report":
                        generateReport();
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

    private void generateReport() throws IOException {

        System.out.println("Please enter the name of the Team Where you want to add the player");
        String teamName = mReader.readLine();
        List<Player> playersOrderBy = new ArrayList<Player>();

        if (mLeague.containsKey(teamName)) {
            Team team = mLeague.get(teamName);

            for (Player player : team.getmPlayers()) {
                playersOrderBy.add(player);
            }

            playersOrderBy.sort((left, right) -> left.getHeightInInches() - right.getHeightInInches());

            for (Player player : playersOrderBy) {
                System.out.println(player.getFirstName() + " " + player.getLastName() +
                        " height:" + player.getHeightInInches() +
                        " previous Exp:" + player.isPreviousExperience());
            }

        } else {
            System.out.println("The name doesn't exist, please start all over again");
        }

    }

    public void createTeam() throws IOException {
        String teamName = null;
        boolean exists = true;

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
    }

    public void addPlayer() throws IOException {

        System.out.println("Check this list to see in which team do you want to Add the Player ");
        promtTeamsFromLeagueOrdered();

        System.out.println("Please enter the name of the Team Where you want to add the player");
        String teamName = mReader.readLine();

        if (mLeague.containsKey(teamName)) {
            Team team = mLeague.get(teamName);
            promptPlayersFromTeamOrdered();
            if (team.getmPlayers().size() > MAX_PLAYERS) {
                System.out.println("The team is Full, please start all over again");
            } else {
                addPlayerToTheTeam(team);
            }

        } else {
            System.out.println("The name doesn't exist, please start all over again");
        }

    }

    private void addPlayerToTheTeam(Team team) throws IOException {
        System.out.println("Select the Last Name of the player lists you want to add");
        String lastName = mReader.readLine();
        boolean added = false;
        Iterator<Player> playerIterator = mPlayersList.iterator();
        while (playerIterator.hasNext() && added == false) {
            if (playerIterator.next().getLastName().equals(lastName)) {
                team.getmPlayers().add(playerIterator.next());
                added = true;
                System.out.println("Players Added in team: " + team.getmName());
            }
        }

        if (added == false) {
            System.out.println("Player couldn't be added " + team.getmName());
        }
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

    public void promptPlayersFromTeamOrdered() {
        Collections.sort(mPlayersList);

        printPlayersList();
    }

    private void printPlayersList() {
        for (Player player : mPlayersList) {
            System.out.println(player.getFirstName() + " " + player.getLastName() +
                    " height:" + player.getHeightInInches() +
                    " previous Exp:" + player.isPreviousExperience());
        }
    }

}