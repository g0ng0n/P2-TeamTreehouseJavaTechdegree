import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.io.IOException;
import java.util.*;

/**
 * Created by ggisbert on 3/12/17.
 */
public class OrganizerUiDefault extends UiDefault {


    public OrganizerUiDefault(Map<String, Team> league, List<Player> playersList) {
        super(league, playersList);
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
                    case "League Balance Report":
                        generateLeagueReport();
                        break;
                    case "quit":
                        System.out.println("Thanks Organizer back to the main menu");
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

    private void generateLeagueReport() {
        int totalExperiencedPlayers = 0;
        int totalInexperiencedPlayers = 0;

        for (Map.Entry<String, Team> option : mLeague.entrySet()) {

            for (Player player : option.getValue().getmPlayers()) {
                if (player.isPreviousExperience()){
                    totalExperiencedPlayers++;
                }else{
                    totalInexperiencedPlayers++;
                }
            }
        }
        System.out.printf("Total Experienced Player In the League: '%s'. %n%n%n", totalExperiencedPlayers);
        System.out.printf("Total Inexperienced Player In the League: '%s'. %n%n%n", totalInexperiencedPlayers);


    }

    private void generateReport() throws IOException {

        System.out.println("Please enter the name of the Team Where you want to add the player");
        String teamName = getmReader().readLine();
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
            teamName = getmReader().readLine();

            if (mLeague.containsKey(teamName)) {
                System.out.println("The name you choose already exists");
            } else {
                exists = false;
            }
        }


        System.out.println("Please enter the name of the Coach");
        String coachName = getmReader().readLine();

        Team team = new Team(teamName, coachName);
        mLeague.put(teamName, team);
    }

    public void addPlayer() throws IOException {

        System.out.println("Check this list to see in which team do you want to Add the Player ");
        promptTeamsFromLeagueOrdered();

        System.out.println("Please enter the name of the Team Where you want to add the player");
        String teamName = getmReader().readLine();

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
        String lastName = getmReader().readLine();
        boolean added = false;
        Iterator<Player> playerIterator = getmPlayersList().iterator();
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
        promptTeamsFromLeagueOrdered();

    }

    public void promptTeamsFromLeagueOrdered() {
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
        Collections.sort(getmPlayersList());

        printPlayersList();
    }

    private void printPlayersList() {
        for (Player player : getmPlayersList()) {
            System.out.println(player.getFirstName() + " " + player.getLastName() +
                    " height:" + player.getHeightInInches() +
                    " previous Exp:" + player.isPreviousExperience());
        }
    }

}