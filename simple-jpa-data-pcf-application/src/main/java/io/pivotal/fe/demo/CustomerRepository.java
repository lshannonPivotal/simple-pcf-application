package io.pivotal.fe.demo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path="customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

}
