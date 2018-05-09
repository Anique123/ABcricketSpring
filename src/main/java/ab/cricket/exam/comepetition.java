package ab.cricket.exam;

public class comepetition {
    private String competetionDate;
    private String teams;
    private String ground;

    public comepetition(String competetionDate, String teams, String ground) {
        this.competetionDate = competetionDate;
        this.teams = teams;
        this.ground = ground;
    }

    public String getCompetetionDate() {
        return competetionDate;
    }

    public void setCompetetionDate(String competetionDate) {
        this.competetionDate = competetionDate;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }
}
