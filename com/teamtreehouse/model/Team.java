package com.teamtreehouse.model;


import java.io.Serializable;

import java.util.*;


/**
 * Created by gonzalo on 3/7/2017.
 */

public class Team implements Comparable<Team> {

    private static final long serialVersionUID = 1L;


    private String mName;

    private String mCoach;

    private Set<Player> mPlayers;


    public Team(String name, String coach) {

        this.mName = name;

        this.mCoach = coach;

    }


    public HashSet<Player> getTeamPlayersByOrder() {

        return result;

    }

    public String getmName() {
        return mName;
    }

    public String getmCoach() {
        return mCoach;
    }

    public Set<Player> getmPlayers() {
        return mPlayers;
    }

    @Override
    public int compareTo(Team team) {

        String sName1 = (this.mName).toLowerCase();
        String sName2 = team.getmName().toLowerCase();

        int result = (sName1).compareTo(sName2);

        return result;
    }
}


