package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class User {

    @JsonProperty("last_name")
    private String lastName;

    private int id;

    private String avatar;

    @JsonProperty("first_name")
    private String firstName;

    public User() {
    }

    public User(List<String> lines) {
        this.id = Integer.parseInt(lines.get(0));
        this.firstName = lines.get(1);
        this.lastName = lines.get(2);
        this.avatar = lines.get(3);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(firstName, user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, id, avatar, firstName);
    }
}
