package ru.systems.demo.dto;

import jakarta.validation.constraints.NotNull;;
import java.util.Objects;

public class PhoneDto {

    @NotNull
    private long id;

    private String number;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneDto phone)) return false;

        return Objects.equals(number, phone.number);
    }

    @Override
    public String toString() {
        return "PhoneDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
