package com.estsoft.springdemoproject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.junit.jupiter.api.Test;


class JasyptConfigAESTest {
    @Test
    void stringEncryptor() {
        String password = "root";

        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        String key = "secret";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        pbeEnc.setSaltGenerator(new RandomSaltGenerator());
        return pbeEnc.encrypt(value);
    }
}