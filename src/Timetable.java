import java.time.LocalTime;
import java.util.*;

public class Timetable {
    private Map<DayOfWeek, TreeMap<LocalTime, List<TrainingSession>>> timetable = new HashMap<>();

    public Timetable() {
        timetable.put(DayOfWeek.MONDAY, new TreeMap<>());
        timetable.put(DayOfWeek.TUESDAY, new TreeMap<>());
        timetable.put(DayOfWeek.WEDNESDAY, new TreeMap<>());
        timetable.put(DayOfWeek.THURSDAY, new TreeMap<>());
        timetable.put(DayOfWeek.FRIDAY, new TreeMap<>());
        timetable.put(DayOfWeek.SATURDAY, new TreeMap<>());
        timetable.put(DayOfWeek.SUNDAY, new TreeMap<>());

    }


    public TreeMap<LocalTime, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<LocalTime, List<TrainingSession>> trainingsForDay = timetable.get(dayOfWeek);
        if (trainingsForDay == null) {
            return new TreeMap<>();
        }

        return trainingsForDay;
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании

        //достаём значение из timetable
        TreeMap<LocalTime, List<TrainingSession>> timetableValue = timetable.get(trainingSession.getDayOfWeek());
        if (timetableValue.containsKey(trainingSession.getStartTime())) {
            timetableValue.get(trainingSession.getStartTime()).add(trainingSession);
        } else {
            List<TrainingSession> sessions = new ArrayList<>();
            sessions.add(trainingSession);

            timetableValue.put(trainingSession.getStartTime(), sessions);
        }


    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, LocalTime localTime) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap<LocalTime, List<TrainingSession>> timeListTreeMap = timetable.get(dayOfWeek);
        List<TrainingSession> trainingSessionList = timeListTreeMap.get(localTime);
        if (trainingSessionList == null) {
            return Collections.emptyList();
        }
        return List.copyOf(trainingSessionList);
    }


    public List<CounterOfTrainings> getCountByCoaches() {

        Map<Coach, Integer> counterMap = new HashMap<>();

        for (Map<LocalTime, List<TrainingSession>> day : timetable.values()) {
            for (List<TrainingSession> sessions : day.values()) {
                for (TrainingSession session : sessions) {

                    Coach coach = session.getCoach();

                    counterMap.put(coach,
                            counterMap.getOrDefault(coach, 0) + 1);
                }
            }
        }


        List<CounterOfTrainings> result = new ArrayList<>();

        for (Map.Entry<Coach, Integer> entry : counterMap.entrySet()) {
            result.add(new CounterOfTrainings(
                    entry.getKey(),
                    entry.getValue()
            ));
        }


        result.sort((a, b) -> Integer.compare(b.getCount(), a.getCount()));

        return result;
    }


}
