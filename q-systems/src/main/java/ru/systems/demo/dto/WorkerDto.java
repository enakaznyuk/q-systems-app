package ru.systems.demo.dto;

import java.util.Set;

public class WorkerDto {

    private long id;

    private String fullName;
    private String address;
    private String position;
    private Set<PhoneDto> phones;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneDto> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "WorkerDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
