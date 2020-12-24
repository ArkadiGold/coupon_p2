package gold.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import gold.com.Service.CompanyService;
import gold.com.Service.CustomerService;
import gold.com.login.ClientType;
import gold.com.login.LoginManager;

@SpringBootApplication
@EnableScheduling
public class Couponsystemphase2Application3 {

	public static void main(String[] args) {

		try {
			ApplicationContext ctx = SpringApplication.run(Couponsystemphase2Application3.class, args);
			LoginManager loginManeger = ctx.getBean(LoginManager.class);

			CompanyService company;
			CustomerService customer;

			company = (CompanyService) loginManeger.login("gold@mail.com", "1234", ClientType.COMPANY);
			customer = (CustomerService) loginManeger.login("plonter@mail.com", "1333", ClientType.CUSTOMER);

//			customer.purchastCoupon(company.getCompanyCoupons().indexOf(33), customer.);

			System.out.println("IOC container was loaded");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
