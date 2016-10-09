package Parsing;

import java.util.Date;

/**
 * Created by tomga on 10/8/2016.
 */
public class GameClass {
    Date date;
    String team_home;
    String team_away;
    int team1_score;
    int team2_score;
    int status;

    public GameClass(Date date, String team_home, String team_away, int status) {
        this.date = date;
        this.team_home = team_home;
        this.team_away = team_away;
        this.status = status;
    }

    public GameClass(Date date, String team_home, String team_away, int team1_score, int team2_score, int status) {
        this.date = date;
        this.team_home = team_home;
        this.team_away = team_away;
        this.team1_score = team1_score;
        this.team2_score = team2_score;
        this.status = status;
    }

    @Override
    public String toString() {
        return "GameClass{" +
                "\ndate=" + date +
                ", \nteam_home='" + team_home + '\'' +
                ", \nteam_away='" + team_away + '\'' +
                ", \nteam1_score=" + team1_score +
                ", \nteam2_score=" + team2_score +
                ", \nstatus=" + status +
                '}';
    }
}
