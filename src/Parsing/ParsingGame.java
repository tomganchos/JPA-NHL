package Parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tomga on 10/8/2016.
 */
public class ParsingGame {
    String fullDate;

    static String linkResource = "http://www.sports.ru/ska/calendar/";
    static String teamDefault = "СКА";

    public List<GameClass> toParse() {
        List<GameClass> gameClassList = new ArrayList<GameClass>();

        System.out.println("кек");

        try {
            Document doc = Jsoup.connect(linkResource).get();
            Elements fullStats = doc.getElementsByAttributeValue("class", "stat-table");
            List<Node> nodeList = fullStats.first().childNode(5).childNodes();


            for (int i = 1; i < nodeList.size(); i = i+2) {

                String team_home;
                String team_away;
                int status;
                int team1_score = -1;
                int team2_score = -1;


                //==========Вывод даты===========//
                String date1 = nodeList.get(i).childNode(1).childNode(0).toString();
                String date2 = "";
                if ((nodeList.get(i).childNode(1).childNodeSize() == 3)) {
                    date2 = nodeList.get(i).childNode(1).childNode(2).toString();
                }
                Date time = StringToDate(date1, date2);

                //==========Определение соперника===========//
                if ((nodeList.get(i).childNode(6).childNode(0).toString().compareTo("В гостях") == 0)) {
                    team_home = nodeList.get(i).childNode(5).childNode(0).childNode(3).childNode(0).toString();
                    team_away = teamDefault;
                }
                else {
                    team_away = nodeList.get(i).childNode(5).childNode(0).childNode(3).childNode(0).toString();
                    team_home = teamDefault;
                }

                //=========Счёт==============//
                if (nodeList.get(i).childNode(8).childNode(1).childNodeSize() == 1) { // не сыгран матч
                    status = 0;
                }
                else { //3 обычный счёт

                    if (nodeList.get(i).childNode(8).childNode(1).childNode(1).childNodeSize() == 3) {
                        team1_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(0, 2).replace(" ", ""));
                        team2_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(3).replace(" ", ""));
                        if (team1_score > team2_score)
                            status = 1;
                        else status = 2;


                    }
                    else if (nodeList.get(i).childNode(8).childNode(1).childNode(1).childNodeSize() == 4) { //буллиты


                        if (nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().replace(" ", "").compareTo("б") == 0) {
                            team1_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(2).childNode(0).toString().substring(0, 2).replace(" ", ""));
                            team2_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(2).childNode(0).toString().substring(3).replace(" ", ""));
                            status = 5;
                        }
                        else {
                            team1_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(0, 2).replace(" ", ""));
                            team2_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(3).replace(" ", ""));
                            status = 6;
                        }

                    }
                    else { //5 овертайм


                        if (nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().replace(" ", "").compareTo("от") == 0) {
                            team1_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(3).childNode(0).toString().substring(0, 2).replace(" ", ""));
                            team2_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(3).childNode(0).toString().substring(3).replace(" ", ""));
                            status = 3;
                        }
                        else {
                            team1_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(0, 2).replace(" ", ""));
                            team2_score = Integer.parseInt(nodeList.get(i).childNode(8).childNode(1).childNode(1).childNode(1).childNode(0).toString().substring(3).replace(" ", ""));
                            status = 4;
                        }

                    }
                }

//                System.out.println("==================================================================================================");
//                System.out.println("time: " + time);
//                System.out.println("team_home: " + team_home);
//                System.out.println("team_away: " + team_away);
//                System.out.println("team1_score: " + team1_score);
//                System.out.println("team2_score: " + team2_score);
//                System.out.println("status: " + status);

                GameClass gameClass = new GameClass(time, team_home, team_away, team1_score, team2_score, status);
                gameClassList.add(gameClass);
            }



        } catch (Exception e) {
            System.out.println("Ошибка");
        }

        return gameClassList;
    }

    private Date StringToDate (String date1, String date2) {
        int day = Integer.parseInt(date1.substring(0, 2));
        int month = Integer.parseInt(date1.substring(3, 5));
        int year = Integer.parseInt(date1.substring(6, 10));
        int hour = 0;
        int minute = 0;
        if (date2.compareTo("") != 0) {
            hour = Integer.parseInt(date2.substring(0, 2));
            minute = Integer.parseInt(date2.substring(3, 5));
        }
        Date date = new Date(year, month, day, hour, minute);
        return date;
    }


}
