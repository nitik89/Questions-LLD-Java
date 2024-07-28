package cricInfo;

public class Ball {
    private final String result;
    private final String bowler;
    private final String batsman;
    private final int ballNumber;

    public Ball(int ballNumber, String bowler, String batsman, String result) {
        this.ballNumber = ballNumber;
        this.bowler = bowler;
        this.batsman = batsman;
        this.result = result;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getBowler() {
        return bowler;
    }

    public String getResult() {
        return result;
    }

   

}
