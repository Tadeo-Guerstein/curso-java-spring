package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import guru.springfamework.services.VendorService;
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
import java.util.Optional;

import static org.hamcrest.core.IsEqual.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VendorControllerTest {
    public static final long ID = 1L;
    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    public void getAllVendors() throws Exception {
        List<VendorDTO> vendors = Arrays.asList(new VendorDTO(), new VendorDTO(), new VendorDTO());

        when(vendorService.getVendors()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", Matchers.hasSize(3)));

    }

    @Test
    public void getVendorById() throws Exception {
        VendorDTO vendor = new VendorDTO();
        vendor.setId(ID);
        vendor.setName("Home");

        when(vendorService.getVendorById(anyLong())).thenReturn(vendor);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Home")));
    }

    @Test
    public void createVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName("Fruits");

        VendorDTO returnDto = new VendorDTO();
        returnDto.setId(vendorDTO.getId());
        returnDto.setName(vendorDTO.getName());

        when(vendorService.createVendor(any(VendorDTO.class))).thenReturn(returnDto);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Fruits")));
    }

    @Test
    public void updateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName("Company");

        VendorDTO vendorSaved = new VendorDTO();
        vendorSaved.setId(vendorDTO.getId());
        vendorSaved.setName(vendorDTO.getName());

        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorSaved);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Company")));
    }

    @Test
    public void patchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName("Company");

        VendorDTO vendorSaved = new VendorDTO();
        vendorSaved.setId(vendorDTO.getId());
        vendorSaved.setName(vendorDTO.getName());

        when(vendorService.updatePatchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorSaved);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Company")));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendor(anyLong());
    }
}