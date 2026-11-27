package tn.esprit.risques.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tn.esprit.risques.entity.RiskSeverity;
import tn.esprit.risques.entity.RiskStatus;
import tn.esprit.risques.entity.RiskType;

import java.util.Map;

public class RiskAlertRequest {

    @NotNull
    private Long companyId;

    @NotNull
    private RiskType type;

    @NotNull
    private RiskSeverity severity;

    private RiskStatus status;

    @NotBlank
    @Size(max = 1000)
    private String message;

    private Map<String, Object> factors;

    private String resolutionComment;

    private String resolvedBy;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public RiskType getType() {
        return type;
    }

    public void setType(RiskType type) {
        this.type = type;
    }

    public RiskSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(RiskSeverity severity) {
        this.severity = severity;
    }

    public RiskStatus getStatus() {
        return status;
    }

    public void setStatus(RiskStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getFactors() {
        return factors;
    }

    public void setFactors(Map<String, Object> factors) {
        this.factors = factors;
    }

    public String getResolutionComment() {
        return resolutionComment;
    }

    public void setResolutionComment(String resolutionComment) {
        this.resolutionComment = resolutionComment;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }
}