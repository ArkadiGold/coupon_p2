package gold.com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gold.com.Exception.NotExistException;
import gold.com.Repo.CompanyRepository;
import gold.com.Repo.CouponRepository;
import gold.com.Repo.CustomerRepository;
import lombok.Data;

@Service
@Data
public abstract class ClientService {
	
	@Autowired
	 protected CompanyRepository companyRepository;
	@Autowired
	 protected CustomerRepository customerRepository;
	@Autowired
	 protected CouponRepository couponRepository;

	public abstract boolean login(String email, String password) throws NotExistException;
}
