package tn.esprit.risques.Services;

import tn.esprit.risques.entity.*;
import tn.esprit.risques.Repository.RiskAlertRepository;
import tn.esprit.risques.dto.RiskAlertRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RiskAlertService {

    private final RiskAlertRepository repository;

    public RiskAlertService(RiskAlertRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public RiskAlert createAlert(RiskAlertRequest request) {

        RiskAlert alert = new RiskAlert();

        alert.setCompanyId(request.getCompanyId());
        alert.setType(request.getType());
        alert.setSeverity(request.getSeverity());
        alert.setMessage(request.getMessage());
        alert.setFactors(request.getFactors());

        alert.setStatus(
                request.getStatus() == null
                        ? RiskStatus.OPEN
                        : request.getStatus()
        );

        updateResolutionInformation(alert, request);

        return repository.save(alert);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<RiskAlert> getAllAlerts() {
        return repository.findAll();
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public RiskAlert getAlertById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Risk alert not found with id: " + id
                ));
    }

    // READ BY COMPANY
    @Transactional(readOnly = true)
    public List<RiskAlert> getAlertsByCompany(Long companyId) {
        return repository.findByCompanyId(companyId);
    }

    // UPDATE
    public RiskAlert updateAlert(
            Long id,
            RiskAlertRequest request) {

        RiskAlert alert = getAlertById(id);

        alert.setCompanyId(request.getCompanyId());
        alert.setType(request.getType());
        alert.setSeverity(request.getSeverity());
        alert.setMessage(request.getMessage());
        alert.setFactors(request.getFactors());

        if (request.getStatus() != null) {
            alert.setStatus(request.getStatus());
        }

        updateResolutionInformation(alert, request);

        return repository.save(alert);
    }

    // DELETE
    public void deleteAlert(Long id) {
        RiskAlert alert = getAlertById(id);
        repository.delete(alert);
    }

    private void updateResolutionInformation(
            RiskAlert alert,
            RiskAlertRequest request) {

        if (alert.getStatus() == RiskStatus.RESOLVED) {
            alert.setResolutionComment(request.getResolutionComment());
            alert.setResolvedBy(request.getResolvedBy());

            if (alert.getResolvedAt() == null) {
                alert.setResolvedAt(LocalDateTime.now());
            }
        } else {
            alert.setResolutionComment(null);
            alert.setResolvedBy(null);
            alert.setResolvedAt(null);
        }
    }
}