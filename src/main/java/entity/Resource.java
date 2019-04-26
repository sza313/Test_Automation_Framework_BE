package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Resource {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;

    public Resource() {
    }

    public Resource(List<String> inputLine) {
        this.id = Integer.parseInt(inputLine.get(0));
        this.name = inputLine.get(1);
        this.year = Integer.parseInt(inputLine.get(2));
        this.color = inputLine.get(3);
        this.pantoneValue = inputLine.get(4);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantoneValue() {
        return pantoneValue;
    }

    public void setPantoneValue(String pantoneValue) {
        this.pantoneValue = pantoneValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(id, resource.id) &&
                Objects.equals(name, resource.name) &&
                Objects.equals(year, resource.year) &&
                Objects.equals(color, resource.color) &&
                Objects.equals(pantoneValue, resource.pantoneValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, color, pantoneValue);
    }
}
