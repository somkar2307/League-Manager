package com.example.leaguemanager;

public class Model {


    String date;
    String team1;
    String team2;

    public Model(){}
    public Model(String date, String team1, String team2) {
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }
}
