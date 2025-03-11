public class Team {
    private String name;
    private int groupID;
    private int score;
    //liste af kampe
    public Team(String name, int groupID, int score){
        this.name = name;
        this.groupID = groupID;
        this.score = score;

    }
    public String getName(){
        return this.name;
    }

    public String toCSVString(){
        String s = this.name+", "+this.groupID+ ", "+this.score;
        return s;

    }
    @Override
    public String toString(){
        String s = this.name+": "+this.groupID+ "     score: "+this.score ;

        return s;
    }

    public void setScore(int points) {
        this.score += points;
    }
}
