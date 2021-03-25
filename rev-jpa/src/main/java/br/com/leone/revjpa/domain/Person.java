package br.com.leone.revjpa.domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PEOPLE",
        indexes = {@Index(columnList = "FIRST_NAME, LAST_NAME", name = "IDX_FULL_NAME", unique = true)}
)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 60)
    private String lastName;

    @Column(name = "AGE_PERSON", nullable = false)
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    @OneToMany(mappedBy = "phoneOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_PERSON"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS")
    )
    private List<Address> addresses;

    public void addPhone(Phone phone){
        if(phones == null){
            phones = new ArrayList<Phone>();
        }
        phone.setPhoneOwner(this);
        phones.add(phone);
    }

    public void delPhone(Phone phone){
        if(phones != null){
        phones.remove(phone);
        }
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", document=" + document +
                '}';
    }
}
