package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VendorServiceTest {
    public static final Long ID = 1L;
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        assertEquals(3, vendorService.getVendors().size());
    }

    @Test
    public void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName("Home");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(ID, vendorDTO.getId());
        assertEquals("Home", vendorDTO.getName());
    }

    @Test
    public void createVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName("Fruits");

        Vendor vendorSaved = new Vendor();
        vendorSaved.setId(vendorDTO.getId());
        vendorSaved.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorSaved);

        VendorDTO savedVendor = vendorService.createVendor(vendorDTO);

        assertEquals(ID, savedVendor.getId());
        assertEquals("Fruits", savedVendor.getName());
    }

    @Test
    public void updateVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName("Company");

        Vendor vendorSaved = new Vendor();
        vendorSaved.setId(vendorDTO.getId());
        vendorSaved.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendorSaved);

        VendorDTO savedVendor = vendorService.updateVendor(ID, vendorDTO);

        assertEquals(vendorDTO.getId(), savedVendor.getId());
        assertEquals(vendorDTO.getName(), savedVendor.getName());
    }

    @Test
    public void deleteVendor() {
        Long id = 1L;

        vendorService.deleteVendor(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}