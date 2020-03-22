package com.github.StevenDesroches.azaxys_commands.utilities;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class JsonReader {

    public static boolean checkifThisIpVoted(String ip) throws IOException {
        URL url = new URL("https://www.serveurs-minecraft.org/api/is_valid_vote.php?id=57686&ip=" + ip + "&duration=20&format=json");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        CheckSingleVote check = new Gson().fromJson(reader, CheckSingleVote.class);
        if (Integer.parseInt(check.votes) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public class CheckSingleVote {
        private String ip;
        private int duration;
        private String votes;
    }

    public static boolean checkIfNbOfVotesSufficient() throws IOException {
        URL url = new URL("https://www.serveurs-minecraft.org/api/votes_count.php?id=57686&period=day&format=json");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        CheckNbOfVotes check = new Gson().fromJson(reader, CheckNbOfVotes.class);
        if (Integer.parseInt(check.votes) >= 15) {
            return true;
        } else {
            return false;
        }
    }

    public class CheckNbOfVotes {
        private int days;
        private String votes;
    }


}