package tn.esprit.entreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entreprise.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}