package gold.com;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import gold.com.Service.CompanyService;
import gold.com.entities.Category;
import gold.com.entities.Coupon;
import gold.com.login.ClientType;
import gold.com.login.LoginManager;

@SpringBootApplication
@EnableScheduling
public class Couponsystemphase2Application2 {

	public static void main(String[] args) {

		try {
			ApplicationContext ctx = SpringApplication.run(Couponsystemphase2Application2.class, args);
			LoginManager loginManeger = ctx.getBean(LoginManager.class);

			CompanyService company;
			CompanyService company2;
//
			company = (CompanyService) loginManeger.login("gold@mail.com", "1234", ClientType.COMPANY);
			company2 = (CompanyService) loginManeger.login("trevels@mail.com", "1234", ClientType.COMPANY);
			int c = company.getCompanyDetails().getId();
			System.out.println("lll");
			System.out.println(c);
//			System.out.println(company2.hashCode());

			company.addCoupon(new Coupon(Category.CLOTH, "Black Shirt", "cutton made", LocalDate.of(2020, 12, 21),
					LocalDate.of(2021, 2, 21), 100, 9.90, "image"));
			company.addCoupon(new Coupon(Category.CLOTH, "Black Shirt2", "cutton made2", LocalDate.of(2020, 12, 21),
					LocalDate.of(2021, 2, 22), 100, 9.90, "image"));
			company2.addCoupon(new Coupon(Category.VACATION, "Dubai", "8 days in Dubia 7 stars!",
					LocalDate.of(2020, 12, 21), LocalDate.of(2021, 2, 21), 100, 9.90, "image"));
			company2.addCoupon(new Coupon(Category.VACATION, "West_Berlin", "7 days in Germany!",
					LocalDate.of(2020, 12, 21), LocalDate.of(2021, 2, 21), 100, 9.90, "image"));

			System.out.println("IOC container was loaded");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
