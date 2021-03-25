package br.com.leone.revjpa.dao;

import br.com.leone.revjpa.domain.Address;

import java.util.List;

public class AddressDAO extends GenericDAO<Address> {

    public AddressDAO() {
        super(Address.class);
    }

    public List<Address> findByCity(String city){
        String jpql = "from Address a where a.city like ?1";
        return find(jpql, city);

    }
}
