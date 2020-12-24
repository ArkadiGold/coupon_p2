package gold.com.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gold.com.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}
