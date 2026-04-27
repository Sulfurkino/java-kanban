import java.util.Objects;

public class Group {
    private Age age;
    private String title;
    private int duration;

    public Group(String title, Age age, int duration) {
        this.age = age;
        this.title = title;
        this.duration = duration;
    }

    public Age getAge() {
        return age;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return age == group.age && Objects.equals(title, group.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, title);
    }

    @Override
    public String toString() {
        return "Group{" +
                "age=" + age +
                ", title='" + title + '\'' +
                '}';
    }
}
