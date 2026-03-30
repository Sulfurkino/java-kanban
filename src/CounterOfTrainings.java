public class CounterOfTrainings {
    private Coach coach;
    private Integer count;

    public CounterOfTrainings(Coach coach, Integer counter) {
        this.coach = coach;
        this.count = counter;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Integer getCount() {
        return count;
    }

    public void setCounter(Integer counter) {
        this.count = count;
    }
}
