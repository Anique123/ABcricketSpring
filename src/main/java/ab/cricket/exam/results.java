package ab.cricket.exam;

public class results extends matches {
    private String scoreHome;
    private String oversHome;
    private String scoreAway;
    private String oversAway;

    public results(String scoreHome, String oversHome, String scoreAway, String oversAway) {
        this.scoreHome = scoreHome;
        this.oversHome = oversHome;
        this.scoreAway = scoreAway;
        this.oversAway = oversAway;
    }

    public String getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(String scoreHome) {
        this.scoreHome = scoreHome;
    }

    public String getOversHome() {
        return oversHome;
    }

    public void setOversHome(String oversHome) {
        this.oversHome = oversHome;
    }

    public String getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(String scoreAway) {
        this.scoreAway = scoreAway;
    }

    public String getOversAway() {
        return oversAway;
    }

    public void setOversAway(String oversAway) {
        this.oversAway = oversAway;
    }
}
