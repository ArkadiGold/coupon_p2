package gold.com.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import gold.com.Exception.NotExistException;
import gold.com.entities.Category;
import gold.com.entities.Coupon;
import gold.com.entities.Customer;

@Service
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerId;

	@Override
	public boolean login(String email, String password) throws NotExistException {
		List<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				this.customerId = customer.getId();
				return true;
			}
		}

		throw new NotExistException("Email or Password");
	}

//	public void purchastCoupon(Coupon coupon) throws Exception {
	public void purchastCoupon(List<Integer> couponIds, int cust_id) throws Exception {

		Coupon coupList = (Coupon) couponRepository.findAll();
		Customer customer = customerRepository.findCustomerById(cust_id);
		if (customer != null) {
			List<Coupon> uniqueCoupons = new ArrayList<>();
			for (int coup_id : couponIds) {
				if (customerRepository.getCustomerCouponById(cust_id, coup_id) == null) {
					Coupon coupon = couponRepository.getOne(coup_id);
					if (coupon != null && 0 < coupon.getAmount()) {
						uniqueCoupons.add(coupon);
						coupon.setAmount(coupon.getAmount() - 1);
						couponRepository.save(coupon);
					}
				}
			}

			uniqueCoupons.addAll(customerRepository.getAllCustomerCoupons(cust_id));
			// TODO Save coupons without bringing all coupons back
			customer.setCoupons(uniqueCoupons);
			customerRepository.save(customer);

		}

//		List<Coupon> coupons = getCustomersCoupons();
//
//		for (Coupon coup : coupons) {
//			if (coup.getId() == coupon.getId()) {
//				throw new AlreadyExistException("coupon");
//			}
//		}
//
//		if (coupon.getAmount() <= 0) {
//			throw new OperationNotAllowedExeption("Purchase coupon with amount 0");
//		}
//		if (coupon.getEndDate().isBefore(LocalDate.now())) {
//			throw new OperationNotAllowedExeption("Purchase coupon expired");
//		}
//
//		coupon.setAmount(coupon.getAmount() - 1);
//		couponRepository.save(coupon);
//
//		Customer customer = customerRepository.getOne(customerId);
//
//		List<Coupon> tempC = customer.getCoupons();
//		tempC.add(coupon);
//		customer.setCoupons(tempC);
//
//		customerRepository.save(customer);
	}

	public List<Coupon> getCustomersCoupons() {
		Customer customer = customerRepository.getOne(customerId);
		return customer.getCoupons();
	}

	public List<Coupon> getCustomersCouponsByCategory(Category category) {
		List<Coupon> coupons = getCustomersCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				results.add(coupon);
			}
		}
		return results;
	}

	public List<Coupon> getCustoemrCouponsByPrice(double maxPrice) {
		List<Coupon> coupons = getCustomersCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				results.add(coupon);
			}
		}
		return results;
	}

	public Customer getCustomerDetalis() {
		List<Coupon> customersCoupon = getCustomersCoupons();
		Customer customer = customerRepository.getOne(customerId);
		customer.setCoupons(customersCoupon);
		return customer;
	}
}
