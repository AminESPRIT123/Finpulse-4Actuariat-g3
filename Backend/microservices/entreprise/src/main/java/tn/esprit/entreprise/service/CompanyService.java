package tn.esprit.entreprise.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.entreprise.dto.CompanyRequest;
import tn.esprit.entreprise.entity.Company;
import tn.esprit.entreprise.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entreprise introuvable"));
    }

    public Company create(CompanyRequest request) {
        return save(new Company(
                request.name(), request.taxId(), request.initialBalance()));
    }

    public Company update(Long id, CompanyRequest request) {
        Company company = findById(id);
        company.update(request.name(), request.taxId(), request.initialBalance());
        return save(company);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private Company save(Company company) {
        try {
            return repository.saveAndFlush(company);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Impossible d’enregistrer cette entreprise", exception);
        }
    }
}