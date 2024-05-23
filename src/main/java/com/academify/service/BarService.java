package com.academify.service;

import com.academify.model.Bar;
import com.academify.repository.BarRepository;
import com.academify.util.CNPJValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarService {

    @Autowired
    private BarRepository barRepository;

    public List<Bar> findAll() {
        return barRepository.findAll();
    }

    public Bar findById(Long id) throws Exception {
        Optional<Bar> bar = barRepository.findById(id);
        if (!bar.isPresent()) {
            throw new Exception("Bar não encontrado");
        }
        return bar.get();
    }

    public Bar save(Bar bar) throws Exception {
        validateBar(bar, true);
        return barRepository.save(bar);
    }

    public Bar update(Long id, Bar barDetails) throws Exception {
        Bar bar = findById(id);

        if (!bar.getCnpj().equals(barDetails.getCnpj())) {
            if (!CNPJValidator.isValidCNPJ(barDetails.getCnpj())) {
                throw new Exception("CNPJ inválido.");
            }
            if (barRepository.existsByCnpj(barDetails.getCnpj())) {
                throw new Exception("CNPJ já cadastrado.");
            }
        }

        validateBar(barDetails, false);

        bar.setNome(barDetails.getNome());
        bar.setCnpj(barDetails.getCnpj());
        bar.setRazaoSocial(barDetails.getRazaoSocial());
        bar.setRua(barDetails.getRua());
        bar.setNumero(barDetails.getNumero());
        bar.setBairro(barDetails.getBairro());
        bar.setCidade(barDetails.getCidade());
        bar.setEstado(barDetails.getEstado());
        bar.setCep(barDetails.getCep());
        bar.setTelefone(barDetails.getTelefone());
        bar.setLotacaoMaxima(barDetails.getLotacaoMaxima());

        return barRepository.save(bar);
    }

    public Bar delete(Long id) throws Exception {
        Bar bar = findById(id);
        barRepository.delete(bar);
        return bar;
    }

    public Long count() {
        return barRepository.count();
    }

    private void validateBar(Bar bar, boolean isNew) throws Exception {
        if (bar.getNome() == null || bar.getNome().length() < 3 || bar.getNome().length() > 50) {
            throw new Exception("Nome deve ter entre 3 e 50 caracteres.");
        }

        if (bar.getTelefone() == null || bar.getTelefone().length() < 10 || bar.getTelefone().length() > 15) {
            throw new Exception("Telefone é obrigatório.");
        }

        if (isNew) {
            if (bar.getCnpj() == null || !CNPJValidator.isValidCNPJ(bar.getCnpj())) {
                throw new Exception("CNPJ deve estar no formato 00.000.000/0000-00 e é obrigatório.");
            }
            if (barRepository.existsByCnpj(bar.getCnpj())) {
                throw new Exception("CNPJ já cadastrado.");
            }

        }
        if (bar.getRazaoSocial() == null || bar.getRazaoSocial().length() < 8 || bar.getRazaoSocial().length() > 50) {
            throw new Exception("Razão Social é obrigatória.");
        }
        if (bar.getRua() == null || bar.getRua().length() < 2 || bar.getRua().length() > 50) {
            throw new Exception("Rua é obrigatória.");
        }
        if (bar.getNumero() == null || bar.getNumero().isEmpty() || bar.getNumero().length() < 2 || bar.getNumero().length() > 5) {
            throw new Exception("Numero é obrigatório.");
        }
        if (bar.getBairro() == null || bar.getBairro().isEmpty()) {
            throw new Exception("Bairro é obrigatório.");
        }
        if (bar.getCidade() == null || bar.getCidade().isEmpty()) {
            throw new Exception("Cidade é obrigatória.");
        }
        if (bar.getEstado() == null || !bar.getEstado().matches("AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO")) {
            throw new Exception("Estado deve ser uma das opções válidas " +
                    "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO.");
        }
        if ((bar.getCep() == null) || (bar.getCep().length() < 8) || (bar.getCep().length() > 8)) {
            throw new Exception("CEP é obrigatório.");
        }


        if (bar.getLotacaoMaxima() == null || bar.getLotacaoMaxima() < 1 || bar.getLotacaoMaxima() > 999) {
            throw new Exception("Lotação Máxima deve ser entre 1 e 999.");
        }
    }
}
