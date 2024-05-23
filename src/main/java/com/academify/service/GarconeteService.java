package com.academify.service;

import com.academify.model.Garconete;
import com.academify.repository.GarconeteRepository;
import com.academify.util.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarconeteService {

    @Autowired
    private GarconeteRepository garconeteRepository;

    public List<Garconete> findAll() {
        return garconeteRepository.findAll();
    }

    public Garconete findById(Long id) throws Exception {
        Optional<Garconete> garconete = garconeteRepository.findById(id);
        if (!garconete.isPresent()) {
            throw new Exception("Garçonete não encontrada");
        }
        return garconete.get();
    }

    public Garconete save(Garconete garconete) throws Exception {
        validateGarconete(garconete, true);
        return garconeteRepository.save(garconete);
    }

    public Garconete update(Long id, Garconete garconeteDetails) throws Exception {
        Garconete garconete = findById(id);

        if (!garconete.getCpf().equals(garconeteDetails.getCpf())) {
            if (!CPFValidator.isValidCPF(garconeteDetails.getCpf())) {
                throw new Exception("CPF inválido.");
            }
            if (garconeteRepository.existsByCpf(garconeteDetails.getCpf())) {
                throw new Exception("CPF já cadastrado.");
            }
        }

        validateGarconete(garconeteDetails, false);

        garconete.setNome(garconeteDetails.getNome());
        garconete.setCpf(garconeteDetails.getCpf());
        garconete.setMatricula(garconeteDetails.getMatricula());
        garconete.setRg(garconeteDetails.getRg());
        garconete.setTelefone(garconeteDetails.getTelefone());
        garconete.setDataContratacao(garconeteDetails.getDataContratacao());
        garconete.setStatusTrabalho(garconeteDetails.getStatusTrabalho());
        garconete.setSalario(garconeteDetails.getSalario());

        return garconeteRepository.save(garconete);
    }

    public Garconete delete(Long id) throws Exception {
        Garconete garconete = findById(id);
        garconeteRepository.delete(garconete);
        return garconete;
    }

    public Long count() {
        return garconeteRepository.count();
    }

    private void validateGarconete(Garconete garconete, boolean isNew) throws Exception {
        if (garconete.getMatricula() == null || !garconete.getMatricula().matches("\\d+")) {
            throw new Exception("Matrícula deve ser composta por caracteres numéricos.");
        }
        if (isNew && garconeteRepository.existsByMatricula(garconete.getMatricula())) {
            throw new Exception("Matrícula já existe.");
        }
        if (garconete.getNome() == null || garconete.getNome().length() < 3 || garconete.getNome().length() > 50) {
            throw new Exception("Nome deve ter entre 3 e 50 caracteres.");
        }
        if (isNew) {
            if (garconete.getCpf() == null || !CPFValidator.isValidCPF(garconete.getCpf())) {
                throw new Exception("CPF inválido.");
            }
            if (garconeteRepository.existsByCpf(garconete.getCpf())) {
                throw new Exception("CPF já cadastrado.");
            }
        }
        if (garconete.getRg() == null) {
            throw new Exception("RG é obrigatório.");
        }
        if (garconete.getTelefone() == null || garconete.getTelefone().length() < 10 || garconete.getTelefone().length() > 15) {
            throw new Exception("Telefone é obrigatório.");
        }
        if (garconete.getDataContratacao() == null || garconete.getDataContratacao().after(new java.util.Date())) {
            throw new Exception("Data de contratação inválida.");
        }
        if (garconete.getStatusTrabalho() == null || !garconete.getStatusTrabalho().matches("ativo|licença-maternidade|férias|desligado")) {
            throw new Exception("Status de trabalho inválido, deve ser uma das opções válidas = ativo|licença-maternidade|férias|desligado");
        }
        if (garconete.getSalario() == null) {
            throw new Exception("Salário é obrigatório.");
        }
    }
}
