import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.util.List;
import java.util.Map;

/**
 * Created by ggisbert on 3/13/17.
 */
public class CoachUiDefault extends UiDefault {

    public CoachUiDefault(Map<String, Team> league, List<Player> playersList) {
        super(league, playersList);
    }
}
