package tn.esprit.risques.Repository;

import tn.esprit.risques.entity.RiskAlert;
import tn.esprit.risques.entity.RiskStatus;
import tn.esprit.risques.entity.RiskType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskAlertRepository
        extends JpaRepository<RiskAlert, Long> {

    // Rechercher les alertes d'une entreprise
    List<RiskAlert> findByCompanyId(Long companyId);

    // Rechercher les alertes par statut
    List<RiskAlert> findByStatus(RiskStatus status);

    // Rechercher les alertes par type
    List<RiskAlert> findByType(RiskType type);

    // Rechercher les alertes d'une entreprise par statut
    List<RiskAlert> findByCompanyIdAndStatus(
            Long companyId,
            RiskStatus status
    );
}