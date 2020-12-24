
package gold.com.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import gold.com.Exception.NotExistException;
import gold.com.Service.AdminService;
import gold.com.Service.ClientService;
import gold.com.Service.CompanyService;
import gold.com.Service.CustomerService;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	public ClientService login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			ClientService adminService = ctx.getBean(AdminService.class);
			try {
				if (adminService.login(email, password)) {
					return adminService;
				}
			} catch (NotExistException e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case COMPANY:
			ClientService companyService = ctx.getBean(CompanyService.class);
			try {
				if (companyService.login(email, password)) {
					return companyService;
				}
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
			break;
		case CUSTOMER:
			ClientService customerService = ctx.getBean(CustomerService.class);
			try {
				if (customerService.login(email, password)) {
					return customerService;
				}
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
			break;

		default:
			break;
		}
		return null;
	}
}
