package br.com.leone.revjpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable {

    public enum TypeAddress {
         COMERCIAL, RESIDENCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS")
    private Long id;

    @Column(name = "CITY_NAME", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_ADDRESS", nullable = false)
    private TypeAddress type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_ADDRESS"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERSON")
    )
    private List<Person> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public TypeAddress getType() {
        return type;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", type=" + type +
                ", persons=" + persons +
                '}';
    }
}
