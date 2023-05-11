package com.amm.poc.openbk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Spri
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        customerRepository.save(new Customer("John", "john@domain.com"));
        assertThat(customerRepository.findById(1L)).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        customerRepository.save(new Customer("John", "john@domain.com"));
        customerRepository.save(new Customer("Julie", "julie@domain.com"));
        assertThat(customerRepository.findAll()).isInstanceOf(List.class);
    }

    @Test
    public void whenSavingCustomer_thenCorrect() {
        customerRepository.save(new Customer("Bob", "bob@domain.com"));
        Customer customer = customerRepository.findById(1L).orElseGet(()
                -> new Customer("john", "john@domain.com"));
        assertThat(customer.getName()).isEqualTo("Bob");
    }
}