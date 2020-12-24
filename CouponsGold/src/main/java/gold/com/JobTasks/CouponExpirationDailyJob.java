package gold.com.JobTasks;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import gold.com.Repo.CouponRepository;
import gold.com.entities.Coupon;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponRepository couponRepository;

	@Scheduled(fixedRate = (1000 * 60 * 60 * 24))
	public void deleteExpirationCoupon() {
		List<Coupon> coupons = couponRepository.findAll();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().isBefore(LocalDate.now())) {
				couponRepository.deleteById(coupon.getId());
			}
		}
	}
}
