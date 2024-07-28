package cricInfo;

import java.time.LocalDateTime;
import java.util.List;

public class Match {
    private final String id;
    private final String title;
    private final String venue;
    private final LocalDateTime startTime;
    private final List<Team>team;
    private MatchStatus status;


    public Match(String id, String title, String venue, LocalDateTime startTime, List<Team> team) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.startTime = startTime;
        this.team = team;
        this.status = MatchStatus.SCHEDULED;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    

}
