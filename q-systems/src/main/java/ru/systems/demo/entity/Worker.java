package ru.systems.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;
    private String address;
    private String position;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinTable(
            name = "workers_has_phones",
            joinColumns = @JoinColumn(name = "workers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "phones_id", referencedColumnName = "id"))
    private Set<Phone> phones;

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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker worker)) return false;

        return Objects.equals(fullName, worker.fullName) && Objects.equals(address, worker.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, address);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
