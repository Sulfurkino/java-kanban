import org.junit.Test;


import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

public class TimetableTest {

    @Test
    public void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(coach,
                DayOfWeek.MONDAY, group, LocalTime.of(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
//Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> monday = timetable.getTrainingSessionForDay(DayOfWeek.MONDAY);

        assertEquals(1, monday.size());

        //Проверить, что за вторник не вернулось занятий

        assertTrue(timetable.getTrainingSessionForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    public void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(coach,
                DayOfWeek.THURSDAY, groupAdult, LocalTime.of(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(coach, DayOfWeek.MONDAY, groupChild,
                LocalTime.of(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(coach, DayOfWeek.THURSDAY, groupChild,
                LocalTime.of(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(coach, DayOfWeek.SATURDAY, groupChild,
                LocalTime.of(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        List<TrainingSession> monday = timetable.getTrainingSessionForDay(DayOfWeek.MONDAY);
        assertEquals(1, monday.size());


        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        List<TrainingSession> thursday = timetable.getTrainingSessionForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursday.size());


        assertEquals(thursdayChildTrainingSession, thursday.get(0));
        assertEquals(thursdayAdultTrainingSession, thursday.get(1));
        // Проверить, что за вторник не вернулось занятий
        assertTrue(timetable.getTrainingSessionForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    public void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(coach, DayOfWeek.MONDAY, group,
                LocalTime.of(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        List<TrainingSession> monday1300 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, LocalTime.of(13, 0));
        assertEquals(1, monday1300.size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        List<TrainingSession> monday1400 = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, LocalTime.of(14, 0));
        assertTrue(monday1400.isEmpty());

    }

    @Test
    public void testGetCountByCoaches() {
        Timetable timetable = new Timetable();

        //добавить две тренировки с одним тренером, проверить метод getcount вернёт список - 1.
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);

        TrainingSession mondayChildTrainingSession = new TrainingSession(coach, DayOfWeek.MONDAY, groupChild,
                LocalTime.of(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(coach, DayOfWeek.THURSDAY, groupChild,
                LocalTime.of(13, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertEquals(1, result.size());

    }

    @Test
    public void testGetCountByDifferentCoaches() {
        // посмотреть рассортировку тренеров, добавляем разных тренеров после чего проверяем сортировку
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильевич", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Михайлович", "Сергей", "Васильевич");

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);

        TrainingSession mondayChildTrainingSession = new TrainingSession(coach1, DayOfWeek.MONDAY, groupChild,
                LocalTime.of(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(coach2, DayOfWeek.THURSDAY, groupChild,
                LocalTime.of(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(coach2, DayOfWeek.SATURDAY, groupChild,
                LocalTime.of(10, 0));


        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertEquals("Васильевич", result.get(0).getCoach().getSurname());
        assertEquals("Сергеевич", result.get(1).getCoach().getSurname());


    }


    @Test
    public void testWillCountReturnEmpty() {
        //посмотреть вернёт ли нам метод count пустоту
        Timetable timetable = new Timetable();

        List<CounterOfTrainings> result = timetable.getCountByCoaches();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


}
