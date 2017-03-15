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
        getmMenu().put("1", "Create a new team. Required fields are team name and coach.");
        getmMenu().put("2", "Add a new player to the team.");
        getmMenu().put("3", "Remove a Player from a Team ");
        getmMenu().put("4", " Generate Report of a chosen team grouped by height ");
        getmMenu().put("5", "Generate a League Balance Report");
        getmMenu().put("0", "Back to the Main Menu");

        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "1":
                        createTeam();
                        break;
                    case "2":
                        addPlayer();
                        break;
                    case "3":
                        removePlayer();
                        break;
                    case "4":
                        generateReport();
                        break;
                    case "5":
                        generateLeagueReport();
                        break;
                    case "0":
                        System.out.println("Thanks Organizer back to the main menu");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. try Again. %n%n%n", choice);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        } while (!choice.equals("0"));

    }

    private void generateLeagueReport() {
        int totalExperiencedPlayers = 0;
        int totalInexperiencedPlayers = 0;

        for (Map.Entry<String, Team> option : mLeague.entrySet()) {

            if (option.getValue().getmPlayers() != null) {
                for (Player player : option.getValue().getmPlayers()) {
                    if (player.isPreviousExperience()) {
                        totalExperiencedPlayers++;
                    } else {
                        totalInexperiencedPlayers++;
                    }
                }
            }else{
                System.out.printf("Team '%s' doesn't have any players %n%n%n", option.getValue().getmName());
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

            if (team.getmPlayers() != null) {

                for (Player player : team.getmPlayers()) {
                    playersOrderBy.add(player);
                }

                playersOrderBy.sort((left, right) -> left.getHeightInInches() - right.getHeightInInches());

                for (Player player : playersOrderBy) {
                    System.out.println(player.getFirstName() + " " + player.getLastName() +
                            " height:" + player.getHeightInInches() +
                            " previous Exp:" + player.isPreviousExperience());
                }
            }else{
                System.out.printf("Team '%s' doesn't have any players %n%n%n", team.getmName());
            }

        } else {
            System.out.println("The name doesn't exist, please start all over again %n%n%n");
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


        System.out.println("Please enter the name of the Coach ");
        String coachName = getmReader().readLine();

        Team team = new Team(teamName, coachName);
        mLeague.put(teamName, team);
    }

    public void addPlayer() throws IOException {

        System.out.println("Check this list to see in which team do you want to Add the Player ");
        promptTeamsFromLeagueOrdered();

        System.out.println("Please enter the name of the Team Where you want to add the player ");
        String teamName = getmReader().readLine();

        if (mLeague.containsKey(teamName)) {
            Team team = mLeague.get(teamName);
            promptPlayersFromTeamOrdered();
            if (team.getmPlayers() != null && team.getmPlayers().size() > MAX_PLAYERS) {
                System.out.println("The team is Full, please start all over again ");
            } else {
                addPlayerToTheTeam(team);
            }

        } else {
            System.out.println("The name doesn't exist, please start all over again ");
        }

    }

    private void addPlayerToTheTeam(Team team) throws IOException {
        System.out.println("Select the Last Name of the player lists you want to add ");
        String lastName = getmReader().readLine();
        boolean added = false;
        for (ListIterator<Player> playerIt = getmPlayersList().listIterator(); playerIt.hasNext(); ) {

            if (playerIt.next().getLastName().equals(lastName)) {
                Player player = playerIt.previous();

                team.getmPlayers().add(player);
                added = true;
                System.out.printf("Player: '%s' was added in the team: %s %n%n%n",player.getLastName(),team.getmName());
                // We remove the players from the initial list in order to not duplicate te player in other team
                getmPlayersList().remove(player);
                break;
            }
        }

        if (added == false) {
            System.out.println("Player couldn't be added " + team.getmName());
        }
    }

    public void removePlayer() throws IOException{
        System.out.println("Check this list to see in which team do you want to Remove the Player ");
        promptTeamsFromLeagueOrdered();

        System.out.println("Please enter the name of the Team Where you want to add the player ");
        String teamName = getmReader().readLine();


        if (mLeague.containsKey(teamName)) {
            Team team = mLeague.get(teamName);
            System.out.println("Check the team roster to see what Player do you want to Remove  ");
            printPlayersFromTeam(team);

            System.out.println("Select the Last Name of the player roster you want to REMOVE ");
            String lastName = getmReader().readLine();

            removePlayerFromTeam(team,lastName);
        } else {
            System.out.println("The name doesn't exist, please start all over again ");
        }

    }

    private void removePlayerFromTeam(Team team, String lastName) {
        boolean added = false;
        for (Player player : team.getmPlayers()) {
            if (player.getLastName().equals(lastName)) {

                team.getmPlayers().remove(player);
                added = true;
                System.out.printf("Player: '%s' was removed in the team: %s %n%n%n",player.getLastName(),team.getmName());
                // we re-add the player into the original list in order to added in other team
                getmPlayersList().add(player);

                break;
            }
        }

        if (added == false) {
            System.out.println("Player couldn't be removed " + team.getmName());
        }

    }

    private void printPlayersFromTeam(Team team) {

        for (Player player : team.getmPlayers()) {
            System.out.println(player.getFirstName() + " " + player.getLastName() +
                    " height:" + player.getHeightInInches() +
                    " previous Exp:" + player.isPreviousExperience());
        }
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