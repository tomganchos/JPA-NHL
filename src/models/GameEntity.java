package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by tomga on 10/8/2016.
 */
@Entity
@Table(name = "game", schema = "public", catalog = "nhl")
public class GameEntity {
    private int id;
    private Timestamp time;
    private int team1;
    private int team2;
    private Integer team1Score;
    private Integer team2Score;
    private int status;

    @javax.persistence.Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "team_home")
    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    @Basic
    @Column(name = "team_away")
    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    @Basic
    @Column(name = "team1_score")
    public Integer getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(Integer team1Score) {
        this.team1Score = team1Score;
    }

    @Basic
    @Column(name = "team2_score")
    public Integer getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(Integer team2Score) {
        this.team2Score = team2Score;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        if (id != that.id) return false;
        if (team1 != that.team1) return false;
        if (team2 != that.team2) return false;
        if (status != that.status) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (team1Score != null ? !team1Score.equals(that.team1Score) : that.team1Score != null) return false;
        if (team2Score != null ? !team2Score.equals(that.team2Score) : that.team2Score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + team1;
        result = 31 * result + team2;
        result = 31 * result + (team1Score != null ? team1Score.hashCode() : 0);
        result = 31 * result + (team2Score != null ? team2Score.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
