package br.com.leone.revjpa.dao;

import br.com.leone.revjpa.domain.Person;

import java.util.List;

public class PersonDAO extends GenericDAO<Person> {
    public PersonDAO() {
        super(Person.class);
    }

    public List<Person> findByLastName(String lastName){
            String jpql = "from Person p where p.lastName like ?1";
        return find(jpql, lastName);
    }

    public List<Person> findAgeIsBetween(int min, int max){
            String jpql = "from Person p where p.age between ?1 and ?2";
        return find(jpql, min, max);

    }

    public Person findByFullName(String firstName, String lastName){
          String jpql = "from Person p where p.firstName like ?1 and p.lastName like ?2";
        return findOne(jpql, firstName, lastName);
    }

    public Person findByCpf(String cpf){
        String jpql = "select p from Person p, Document d where d.cpf like ?1 "
                +" and p.document.id = d.id";

        return findOne(jpql, cpf);
    }

    public Person findByRg(Integer rg){
        String jpql = "select p from Person p, Document d where d.rg like ?1 "
                +" and p.document.id = d.id";

        return findOne(jpql, rg);
    }
}
