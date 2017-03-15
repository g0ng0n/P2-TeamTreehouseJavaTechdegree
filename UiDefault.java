import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ggisbert on 3/14/17.
 */
public class UiDefault {

    Map<String, Team> mLeague;
    public static final int MAX_PLAYERS = 11;
    private BufferedReader mReader;
    private List<Player> mPlayersList;

    private Map<String, String> mMenu;


    public UiDefault(Map<String, Team> league, List<Player> playersList) {

        mLeague = league;

        mReader = new BufferedReader(new InputStreamReader(System.in));
        mPlayersList = playersList;
        mMenu = new HashMap<String, String>();


    }

    public String promptAction() throws IOException {

        for (Map.Entry<String, String> option : mMenu.entrySet()) {

            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }

        System.out.printf("What do you want to do: %n");
        String choice = mReader.readLine();

        return choice.trim().toLowerCase();

    }

    public Map<String, Team> getmLeague() {
        return mLeague;
    }

    public BufferedReader getmReader() {
        return mReader;
    }

    public List<Player> getmPlayersList() {
        return mPlayersList;
    }

    public Map<String, String> getmMenu() {
        return mMenu;
    }
}
