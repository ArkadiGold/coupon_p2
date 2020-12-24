package gold.com.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gold.com.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
