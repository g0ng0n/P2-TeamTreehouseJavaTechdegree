package com.teamtreehouse.model;


import java.io.Serializable;

import java.util.*;


/**
 * Created by gonzalo on 3/7/2017.
 */

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;


    private String mName;

    private String mCoach;

    private List<Player> mPlayers;


    public Team(String name, String coach) {

        this.mName = name;

        this.mCoach = coach;

    }


    public List<Player> getTeamPlayersByOrder() {

        List<Player> result = Collections.sort(mPlayers);

        return result;

    }

}


