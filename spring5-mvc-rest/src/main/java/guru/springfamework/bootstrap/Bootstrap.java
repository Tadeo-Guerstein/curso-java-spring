package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("default")
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        System.out.println("Loading vendors H2");

        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);
        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);
    }

    private void loadCustomers() {
        System.out.println("Loading customers H2");

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Ander");
        customer1.setLastname("Herrera");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Edinson");
        customer2.setLastname("Cavani");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setName("Exequiel");
        customer3.setLastname("Zeballos");

        Customer customer4 = new Customer();
        customer4.setId(4L);
        customer4.setName("Miguel");
        customer4.setLastname("Merentiel");

        Customer customer5 = new Customer();
        customer5.setId(5L);
        customer5.setName("Carlos");
        customer5.setLastname("Palacios");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);


        System.out.println("Customer loaded => " + customerRepository.count());
    }

    private void loadCategories() {
        System.out.println("Loading categories H2");

        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category loaded => " + categoryRepository.count());
    }
}
