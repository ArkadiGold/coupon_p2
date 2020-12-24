package gold.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import gold.com.Service.AdminService;
import gold.com.entities.Company;
import gold.com.entities.Customer;
import gold.com.login.ClientType;
import gold.com.login.LoginManager;

@SpringBootApplication
@EnableScheduling
public class Couponsystemphase2Application {

	public static void main(String[] args) {

		try {
			ApplicationContext ctx = SpringApplication.run(Couponsystemphase2Application.class, args);
			LoginManager loginManeger = ctx.getBean(LoginManager.class);

			AdminService admin;

			admin = (AdminService) loginManeger.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

			admin.addCompany(new Company("gold", "gold@mail.com", "1234"));
			admin.addCompany(new Company("wow", "wow@mail.com", "1234"));
			admin.addCompany(new Company("travels", "trevels@mail.com", "1234"));
			admin.addCompany(new Company("yammi", "yammi@mail.com", "1234"));

			admin.addCustomer(new Customer("yoram", "gaon", "kavod@mail.com", "1234"));
			admin.addCustomer(new Customer("shoshana", "damari", "yemen@mail.com", "321"));
			admin.addCustomer(new Customer("zahar", "argov", "perahbegani@mail.com", "1211"));
			admin.addCustomer(new Customer("rami", "fortis", "plonter@mail.com", "1333"));

			admin.getCompanies();
			admin.getCustomers();

			System.out.println("IOC container was loaded");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
