package com.wanderalvess.clientsback.util;

import com.wanderalvess.clientsback.model.Phone;
import com.wanderalvess.clientsback.repository.ClientRepository;
import com.wanderalvess.clientsback.repository.PhoneRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class util {
    public boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public void validateDocument(String document, ClientRepository clientRepository) {
        if (clientRepository.existsByDocument(document)) {
            throw new IllegalArgumentException("O CPF ou CNPJ já está cadastrado.");
        }
        String cleanDocument = document.replaceAll("[/.-]", "");
        if (isNullOrEmpty(cleanDocument)) {
            throw new IllegalArgumentException("O campo de Documento (CPF/CNPJ) é obrigatório.");
        }
        if (cleanDocument.length() == 11) {
            if (!isValidCPF(cleanDocument)) {
                throw new IllegalArgumentException("CPF inválido.");
            }
        } else if (cleanDocument.length() == 14) {
            if (!isValidCNPJ(cleanDocument)) {
                throw new IllegalArgumentException("CNPJ inválido.");
            }
        } else {
            throw new IllegalArgumentException("O campo de Documento (CPF/CNPJ) deve ter tamanho mínimo de 11 caracteres e máximo de 14 caracteres.");
        }
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;
        }

        int sum = 0;
        int remainder;

        for (int i = 1; i <= 9; i++) {
            sum += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }

        remainder = (sum * 10) % 11;

        if ((remainder == 10) || (remainder == 11)) {
            remainder = 0;
        }

        if (remainder != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }

        sum = 0;

        for (int i = 1; i <= 10; i++) {
            sum += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }

        remainder = (sum * 10) % 11;

        if ((remainder == 10) || (remainder == 11)) {
            remainder = 0;
        }

        if (remainder != Integer.parseInt(cpf.substring(10, 11))) {
            return false;
        }

        return true;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222") ||
                cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888") ||
                cnpj.equals("99999999999999")) {
            return false;
        }

        int[] multiplierFirst = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplierSecond = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Integer.parseInt(cnpj.substring(i, i + 1)) * multiplierFirst[i];
        }

        int remainder = sum % 11;
        int firstDigit = (remainder < 2) ? 0 : (11 - remainder);

        sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Integer.parseInt(cnpj.substring(i, i + 1)) * multiplierSecond[i];
        }

        remainder = sum % 11;
        int secondDigit = (remainder < 2) ? 0 : (11 - remainder);

        return (Integer.parseInt(cnpj.substring(12, 13)) == firstDigit && Integer.parseInt(cnpj.substring(13, 14)) == secondDigit);
    }

    public void validateName(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("O campo de Nome é obrigatório.");
        } else {
            if (name.length() < 10) {
                throw new IllegalArgumentException("O campo de Nome do Cliente deve conter mais de 10 caracteres");
            }
        }
    }

    public void validateLatitude(String latitude) {
        if (isNullOrEmpty(latitude)) {
            throw new IllegalArgumentException("O campo de Latitude é obrigatório.");
        } else {
            if (latitude.length() < 5) {
                throw new IllegalArgumentException("O campo de Latitude deve ter tamanho mínimo de 5 caracteres.");
            }
        }
    }

    public void validateLongitude(String longitude) {
        if (isNullOrEmpty(longitude)) {
            throw new IllegalArgumentException("O campo de longitude é obrigatório.");
        } else {
            if (longitude.length() < 5) {
                throw new IllegalArgumentException("O campo de longitude deve ter tamanho mínimo de 5 caracteres.");
            }
        }
    }

    public void validateAddress(String address) {
        if (isNullOrEmpty(address)) {
            throw new IllegalArgumentException("O campo de Endereço é obrigatório.");
        } else {
            if (address.length() < 5) {
                throw new IllegalArgumentException("O campo de Endereço deve ter tamanho mínimo de 5 caracteres.");
            }
        }
    }

    public void validatePhone(List<Phone> phones, PhoneRepository phoneRepository) {
        String PHONE_REGEX = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}";

        if (phones == null || phones.isEmpty()) {
            throw new IllegalArgumentException("A lista de telefones não pode ser nula ou vazia.");
        }

        for (Phone phone : phones) {
            if (phone == null || isNullOrEmpty(phone.getNumber())) {
                throw new IllegalArgumentException("O campo de telefone é obrigatório para todos os telefones na lista.");
            } else if (phone.getNumber().length() < 10) {
                throw new IllegalArgumentException("O campo de telefone deve ter tamanho mínimo de 10 caracteres para o número: " + phone.getNumber());
            } else if (!Pattern.matches(PHONE_REGEX, phone.getNumber())) {
                throw new IllegalArgumentException("O formato do telefone é inválido para o número: " + phone.getNumber());
            } else if (phoneRepository.existsByNumber(phone.getNumber())) {
                throw new IllegalArgumentException("O número de telefone já está em uso: " + phone.getNumber());
            }
        }
    }

}
