package gold.com.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gold.com.entities.Category;
import gold.com.entities.Coupon;
import gold.com.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findCustomerByNameAndPassword(String name, String password);

	Customer findCustomerById(int id);

	Customer findCustomerByName(String name);

	Customer findCustomerByIdAndName(int it, String name);

	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1 AND coup.id = ?2)")
	Coupon getCustomerCouponById(int cust_id, int coup_id);

	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1)")
	List<Coupon> getAllCustomerCoupons(int cust_id);

	@Query("SELECT coup FROM Coupon coup WHERE coup.price < ?2 AND coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1)")
	List<Coupon> getAllCustomerCouponsbyPrice(int cust_id, double price);

	@Query("SELECT coup FROM Coupon coup WHERE coup.endDate < ?2 AND coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1)")
	List<Coupon> getAllCustomerCouponsbyDate(int cust_id, LocalDate endDate);

	@Query("SELECT coup FROM Coupon coup WHERE type = ?2 AND coup.id IN (SELECT coup.id FROM coup.customers cust WHERE cust.id=?1)")
	List<Coupon> getAllCustomerCouponsByType(int cust_id, Category type);
}
