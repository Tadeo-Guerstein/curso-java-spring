package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import guru.springfamework.services.ResourceNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    public void getCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = Arrays.asList(new CustomerDTO(), new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL + "/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void notFoundTest() throws Exception {
        when(customerService.getAllCustomers()).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getCustomersById() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setName("Tadeo");
        customer.setLastname("Guerstein");

        when(customerService.getCustomersById(anyLong())).thenReturn(customer);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Tadeo")));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setName("Tadeo");
        customer.setLastname("Guerstein");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setId(customer.getId());
        returnDto.setName(customer.getName());
        returnDto.setLastname(customer.getLastname());
        returnDto.setUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createCustomer(customer)).thenReturn(returnDto);

        mockMvc.perform(post(CustomerController.BASE_URL + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Tadeo")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1L);
        customer.setName("Tadeo");
        customer.setLastname("Guerstein");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setId(customer.getId());
        returnDto.setName(customer.getName());
        returnDto.setLastname(customer.getLastname());
        returnDto.setUrl(CustomerController.BASE_URL + "/1");

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Tadeo")))
                .andExpect(jsonPath("$.lastname", equalTo("Guerstein")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        // given
        CustomerDTO customer = new CustomerDTO();
        customer.setName("Fred");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setName(customer.getName());
        returnDTO.setLastname("Flintstone");
        returnDTO.setUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(AbstractRestControllerTest.asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Fred")))
                .andExpect(jsonPath("$.lastname", equalTo("Flintstone")))
                .andExpect(jsonPath("$.url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }
}