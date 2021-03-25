package br.com.leone.revjpa.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "PHONES")
public class Phone implements Serializable {

    public enum TypePhone{
        RESIDENCIAL, CELULAR, COMERCIAL
    }

    public Phone() {
    }

    public Phone(TypePhone type, String number) {
        this.type = type;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PHONE", nullable = false)
    private TypePhone type;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID")
    private Person phoneOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPhoneOwner() {
        return phoneOwner;
    }

    public void setPhoneOwner(Person phoneOwner) {
        this.phoneOwner = phoneOwner;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", phoneOwner=" + phoneOwner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id.equals(phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
