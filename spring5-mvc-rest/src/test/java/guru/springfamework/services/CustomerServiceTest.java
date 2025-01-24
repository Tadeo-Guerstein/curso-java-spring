package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    public static final Long ID = 1L;
    public static final String LASTNAME = "Cavani";
    public static final String NAME = "Edinson";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customersDTO = customerService.getAllCustomers();

        assertEquals(3, customersDTO.size());
    }

    @Test
    public void getCustomersById() {
        Customer customer = new Customer();
        customer.setName(NAME);
        customer.setLastname(LASTNAME);
        customer.setId(ID);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomersById(ID);

        assertEquals(ID, customerDTO.getId());
        assertEquals(NAME, customerDTO.getName());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    public void createCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Jim");
        customerDTO.setId(1L);

        Customer savedCustomer = new Customer();
        savedCustomer.setName(customerDTO.getName());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(customerDTO.getId());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.createCustomer(customerDTO);

        assertEquals(customerDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/customers/1", savedDto.getUrl());
    }

    @Test
    public void updateCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Jim");
        customerDTO.setId(1L);

        Customer savedCustomer = new Customer();
        savedCustomer.setName(customerDTO.getName());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(customerDTO.getId());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDto = customerService.updateCustomer(1L, customerDTO);

        assertEquals(customerDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/customers/1", savedDto.getUrl());

    }

    @Test
    public void deleteCustomerById() {
        Long id = 1L;

        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}