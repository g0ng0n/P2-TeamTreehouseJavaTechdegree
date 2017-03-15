import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ggisbert on 3/13/17.
 */
public class CoachUiDefault extends UiDefault {

    public CoachUiDefault(Map<String, Team> league, List<Player> playersList) {
        super(league, playersList);
    }


    public void run() {

        String choice = "";
        getmMenu().put("1", "Print Out Roster");
        getmMenu().put("0", "Back to the Main Menu");

        do {
            try {
                choice = promptAction();
                switch (choice) {
                    case "1":
                        printRoster();
                        break;
                    case "0":
                        System.out.println("Thanks Coach back to the main menu");
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

    private void printRoster() throws IOException {
        System.out.println("Please enter the name of the Coach to get the Team Roster");
        String coachName = getmReader().readLine();
        Team coachTeam = null;
        for (Map.Entry<String, Team> option : mLeague.entrySet()) {

            if (option.getValue().getmCoach().equals(coachName)){
                coachTeam = option.getValue();
            }
        }

        if (coachTeam != null){

            for (Player player : coachTeam.getmPlayers()) {
                System.out.println(player.getFirstName() + " " + player.getLastName() +
                        " height:" + player.getHeightInInches() +
                        " previous Exp:" + player.isPreviousExperience());
            }

        }else{
            System.out.println("The coach name you entered doesn't exist");
        }
    }
}
