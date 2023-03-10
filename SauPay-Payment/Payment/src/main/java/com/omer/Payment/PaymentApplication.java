package com.omer.Payment;

import com.omer.Payment.repository.AccountRepository;
import com.omer.Payment.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaymentApplication implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;

	public PaymentApplication(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		/*Account account= accountRepository.save(new Account("", LocalDateTime.now(),new HashSet<>()));
		Customer customer= customerRepository.save(new Customer("","Omer","Faruk","Guzel","","","",account));
		System.out.println("customer.getId()");*/
	}
}