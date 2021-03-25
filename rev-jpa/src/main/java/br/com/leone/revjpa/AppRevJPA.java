package br.com.leone.revjpa;

import br.com.leone.revjpa.dao.AddressDAO;
import br.com.leone.revjpa.dao.DocumentDAO;
import br.com.leone.revjpa.dao.PersonDAO;
import br.com.leone.revjpa.dao.PhoneDAO;
import br.com.leone.revjpa.domain.Address;
import br.com.leone.revjpa.domain.Document;
import br.com.leone.revjpa.domain.Person;
import br.com.leone.revjpa.domain.Phone;

import java.util.Arrays;
import java.util.List;

public class AppRevJPA {
    public static void main(String[] args) {

        //insertPerson();
        //findPersonByID();
        //findAllPeople();
        //countPeople();
        //findByLastName();
        //findByAge();
        //findByFullName();
        //updatePerson();
        //deletePerson();

        //insertDocument();
        //updateDocument();
        //findPersonByCpf();
        //findPersonByRg();

        //insertPhone();
        //insertPhoneByPerson();
        //updatePhone();
        //updatePhoneByPerson();
        //deleteOnCascade();
        //insertAddressByPerson();
        //insertPersonByAddress();
        findByCity();

    }

    private static void findByCity() {
        List<Address> addresses = new AddressDAO().findByCity("Rio de Janeiro");

        for(Address address : addresses){
            System.out.println(address.toString());
        }
    }

    private static void insertPersonByAddress() {
        Person person = new PersonDAO().findByID(11L);

        Address ad1 = new Address();
        ad1.setCity("São Paulo");
        ad1.setStreet("Av. Estados Unidos, 1022");
        ad1.setType(Address.TypeAddress.RESIDENCIAL);
        ad1.setPersons(Arrays.asList(person));

        AddressDAO dao = new AddressDAO();
        dao.save(ad1);

        System.out.println(dao.findByID(ad1.getId()));

    }

    private static void insertAddressByPerson() {
        Address ad1 = new Address();
        ad1.setCity("São Paulo");
        ad1.setStreet("Av. João XXIII, 122");
        ad1.setType(Address.TypeAddress.RESIDENCIAL);

        Address ad2 = new Address();
        ad2.setCity("São Paulo");
        ad2.setStreet("Av. Paulista, 2002");
        ad2.setType(Address.TypeAddress.COMERCIAL);

        Person person = new Person();
        person.setFirstName("Kendric");
        person.setLastName("Lamar");
        person.setAge(32);
        person.setDocument(new Document("192.321.422-13", 311153778));
        person.addPhone( new Phone(Phone.TypePhone.RESIDENCIAL, "11 2094-3132"));
        person.addPhone(new Phone(Phone.TypePhone.COMERCIAL, "11 2248-1033"));
        person.setAddresses(Arrays.asList(ad1, ad2));

        new PersonDAO().save(person);

        System.out.println(new PersonDAO().findByID(person.getId()));
    }

    private static void deleteOnCascade() {
        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findByID(8L);

        System.out.println(phone.toString());

        phone.getPhoneOwner().delPhone(phone);

        dao.delete(phone);

    }

    private static void updatePhoneByPerson() {
        Person person = new PersonDAO().findByID(12L);

        for(Phone phone : person.getPhones()){
            System.out.println("\n PhoneNumber: " + phone.toString() + "\n");

            if(Phone.TypePhone.RESIDENCIAL == phone.getType()){

                phone.setType(Phone.TypePhone.COMERCIAL);
                new PersonDAO().update(person);
            }
        }

        new PersonDAO().findByID(12L);
        for(Phone phone : person.getPhones())
            System.out.println("\n PhoneNumber Updated: " + phone.toString());

    }

    private static void updatePhone() {
        Person person = new PersonDAO().findByID(8L);

        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findByID(4L);

        phone.setPhoneOwner(person);

        dao.update(phone);

        phone = dao.findByID(phone.getId());

        System.out.println(phone.toString());
            }

    private static void insertPhoneByPerson() {
        Phone ph1 = new Phone(Phone.TypePhone.CELULAR,"+55 12 98833-2233");
        Phone ph2 = new Phone(Phone.TypePhone.COMERCIAL,"+55 12 98442-2234");

        Person person = new Person();

        person.setFirstName("Paula");
        person.setLastName("Mansfield");
        person.setAge(26);
        person.setDocument(new Document("170.143.312-35", 316175249));

        //person.setPhones(Arrays.asList(ph1, ph2));
        //ph1.setPhoneOwner(person);
        //ph2.setPhoneOwner(person);
        person.addPhone(ph1);
        person.addPhone(ph2);

        new PersonDAO().save(person);
    }

    private static void insertPhone() {
        Person person = new Person();

        person.setFirstName("Ruben");
        person.setLastName("Leme");
        person.setAge(50);
        person.setDocument(new Document("385.155.171-35", 362258397));

        Phone phone = new Phone(Phone.TypePhone.CELULAR,"+55 11 99322-2411");
        phone.setPhoneOwner(person);

        PhoneDAO dao = new PhoneDAO();

        dao.save(phone);
        phone = dao.findByID(phone.getId());

        System.out.println("\nThe telephone was saved as: "+ phone.toString());

    }

    private static void findPersonByRg() {
        Person p = new PersonDAO().findByRg(123456789);

        System.out.println("\n"+ p.toString());
    }

    private static void findPersonByCpf() {
        Person p = new PersonDAO().findByCpf("123.456.789-11");

        System.out.println(p.toString());
    }

    private static void updateDocument() {
        Document doc = new DocumentDAO().findByID(1L);
        System.out.println(doc.toString());

        doc.setCpf("123.456.789-11");
        new DocumentDAO().update(doc);
        System.out.println(new DocumentDAO().findByID(1L).toString());
    }

    private static void insertDocument() {
        Person person1 = new Person();
        person1.setFirstName("Cleber");
        person1.setLastName("Souza");
        person1.setAge(32);
        person1.setDocument(new Document("123.456.789-10",123456789));

        new PersonDAO().save(person1);

        System.out.println(person1.toString());

    }

    private static void deletePerson() {
        new PersonDAO().delete(6L);
    }

    private static void updatePerson() {
        Person person = new PersonDAO().findByID(6L);
        System.out.println(person.toString());

        person.setAge(51);
        new PersonDAO().update(person);

        System.out.println(person.toString());
    }

    private static void findByFullName() {
        Person person = new PersonDAO().findByFullName("Fernanda", "Visentainer");
        System.out.println(person.toString());
    }

    private static void findByAge() {
        List<Person> people = new PersonDAO().findAgeIsBetween(18, 24);
        for(Person person : people)
            System.out.println(person.toString());
    }

    private static void findByLastName() {
        List<Person> people = new PersonDAO().findByLastName("Visentainer");
        for(Person person : people)
            System.out.println(person.toString());
    }

    private static void countPeople() {
        long nPeople = new PersonDAO().count();
        System.out.println("We have "+ nPeople +" people in our Database");
    }

    private static void findAllPeople() {
        List<Person> people = new PersonDAO().findALL();
        for(Person person : people)
            System.out.println(person.toString());
    }

    private static void findPersonByID() {
        Person person = new PersonDAO().findByID(2L);
        Person person2 = new PersonDAO().findByID(1L);

        System.out.println(person.toString() +"\n"+ person2.toString());
    }

    private static void insertPerson(){
        Person p1 = new Person();
        p1.setFirstName("Eduardo");
        p1.setLastName("Pizzoli Jr");
        p1.setAge(24);

        new PersonDAO().save(p1);

        System.out.println(p1.toString());

        Person p2 = new Person();
        p2.setFirstName("Eduardo");
        p2.setLastName("Visentainer");
        p2.setAge(18);

        new PersonDAO().save(p2);

        System.out.println(p2.toString());

        Person p3 = new Person();
        p3.setFirstName("Regina");
        p3.setLastName("Camargo");
        p3.setAge(53);

        new PersonDAO().save(p3);

        System.out.println(p3.toString());
    }
}
