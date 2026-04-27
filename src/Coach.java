import java.util.Objects;

public class Coach {
    private String name;
    private String surname;
    private String middleName;

    public Coach(String middleName, String name, String surname) {
        this.middleName = middleName;
        this.name = name;
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(name, coach.name) && Objects.equals(surname, coach.surname) && Objects.equals(middleName, coach.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName);
    }

    @Override
    public String toString() {
        return "Coach{" +
                "middleName='" + middleName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
