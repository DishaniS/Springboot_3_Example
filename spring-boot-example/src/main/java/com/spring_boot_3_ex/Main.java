package com.spring_boot_3_ex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = "com.amigoscode")
//@EnableAutoConfiguration
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerID}")
    public void deleteCustomer(@PathVariable("customerID") Integer id) {
        customerRepository.deleteById(id);

    }



//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        return new GreetResponse(
//                "Hello World",
//                List.of("Java","Python","Javascript"),
//                new Person("Alex", 25,30000)
//
//        );
//    }
//    record Person(String name, int age, double saving) {
//
//    }
//
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            Person person
//    ) {}

//    class GreetResponse {
//        private final String greet;
//
//        GreetResponse(String greet) {
//            this.greet = greet;
//        }
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" + "greet='" + greet + '\'' + '}';
//        }
//
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hashCode(greet);
//        }
//    }
}
