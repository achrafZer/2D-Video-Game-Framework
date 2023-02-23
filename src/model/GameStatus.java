package model;

import java.time.Duration;

public class GameStatus {

    private int gameScore=0;
    private long gameInitialTime =0;
    private long gameFinalTime =0;
    private long gamePauseTime =0;
    private long gameResumeTime =0;

    public long getGameTime() {
        return gameTime;
    }

    private long gameTime =0;

    public int getGameScore() {
        return gameScore;
    }

    public void increaseScore(int points) {
        this.gameScore += points;
    }

    public boolean startStopwatch()
    {
        gameInitialTime =System.currentTimeMillis();
        gameFinalTime =0;
        gamePauseTime =0;
        gameResumeTime =0;
        gameTime =0;
        return true;
    }

    public boolean pauseStopwatch()
    {
        if(gameInitialTime ==0) {return false;}
        gamePauseTime =System.currentTimeMillis();
        return true;
    }

    public boolean resumeStopwatch()
    {
        if(gameInitialTime ==0 || gamePauseTime ==0) {return false;}
        gameResumeTime =System.currentTimeMillis();
        gameInitialTime = gameInitialTime + gameResumeTime - gamePauseTime;
        gameFinalTime =0;
        gamePauseTime =0;
        gameResumeTime =0;
        gameTime =0;
        return true;
    }

    public boolean stopStopwatch()
    {
        if(gameInitialTime ==0) {return false;}
        gameFinalTime =System.currentTimeMillis();
        gameTime =(gameFinalTime - gameInitialTime) - (gameResumeTime - gamePauseTime);
        gameInitialTime =0;
        gameFinalTime =0;
        gamePauseTime =0;
        gameResumeTime =0;
        return true;
    }

    public long getRawGameTime(int format)
    {
        return gameTime /format;
    }

    public String getFormattedGameTime(){
        return timeToMSm(gameTime);
    }

    public static String timeToMSm(long time) {
        Duration duration = Duration.ofMillis(time);
        return String.format("%02d min %02d s %02d ms",duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
