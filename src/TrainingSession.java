import java.time.LocalTime;
import java.util.Objects;

public class TrainingSession {
    private Coach coach;
    private Group group;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;

    public TrainingSession(Coach coach, DayOfWeek dayOfWeek, Group group, LocalTime startTime) {
        this.coach = coach;
        this.dayOfWeek = dayOfWeek;
        this.group = group;
        this.startTime = startTime;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingSession training = (TrainingSession) o;
        return Objects.equals(coach, training.coach) && Objects.equals(group, training.group) && dayOfWeek == training.dayOfWeek && Objects.equals(startTime, training.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, group, dayOfWeek, startTime);
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "coach=" + coach +
                ", group=" + group +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                '}';
    }
}
