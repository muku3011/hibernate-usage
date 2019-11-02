package com.learnbyexample.hsmexample;

import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.objects.AESSecretKey;
import iaik.pkcs.pkcs11.objects.DES2SecretKey;
import iaik.pkcs.pkcs11.objects.SecretKey;
import iaik.pkcs.pkcs11.wrapper.PKCS11Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GenerateKeyTest {

    private int MAX_OBJECT_COUNT = 10;

    private GenerateKey generateAESKey = new GenerateKey();

    @BeforeEach
    public void setUp() throws IOException, TokenException {
        // Step 1: Initialize module
        CryptoUtil.initializeModule();

        // Step 2: Initialize token
        CryptoUtil.initializeToken();
    }

    @AfterEach
    public void cleanUp() throws TokenException {
        // Step 6: Finalize module
        CryptoUtil.finalizeModule();
    }

    @Test
    public void generateShowDeleteAESKey() throws TokenException {
        // AES Key

        String keyLabel = "AES secret";

        generateKey(PKCS11Constants.CKM_AES_KEY_GEN, keyLabel);

        //getAllGenerateKeys(PKCS11Constants.CKM_AES_KEY_GEN, MAX_OBJECT_COUNT);

        deleteKey(PKCS11Constants.CKM_AES_KEY_GEN, keyLabel, MAX_OBJECT_COUNT);
    }

    @Test
    public void generateShowDeleteDESKey() throws TokenException {
        // DES Key

        String keyLabel = "DES secret";

        generateKey(PKCS11Constants.CKM_DES2_KEY_GEN, keyLabel);

        //getAllGenerateKeys(PKCS11Constants.CKM_DES2_KEY_GEN, MAX_OBJECT_COUNT);

        deleteKey(PKCS11Constants.CKM_DES2_KEY_GEN, keyLabel, MAX_OBJECT_COUNT);
    }

    // Util methods:

    private void generateKey(long mechanismType, String keyLabel) throws TokenException {
        // Step 3: Create session and login
        Session session = CryptoUtil.openSessionAndLogin();

        // Step 4: Perform operation
        generateAESKey.generateKey(session, mechanismType, keyLabel);

        // Step 5: Close session
        CryptoUtil.closeSession(session);
    }

/*    private void getAllGenerateKeys(Long mechanismType, int maxObjectCount) throws TokenException {
        // Step 3: Create session and login
        Session session = CryptoUtil.openSessionAndLogin();

        // Step 4: Perform operation
        SecretKey secretKeyTemplate = null;
        if (mechanismType.equals(PKCS11Constants.CKM_AES_KEY_GEN)) {
            secretKeyTemplate = new AESSecretKey();
        } else if (mechanismType.equals(PKCS11Constants.CKM_DES2_KEY_GEN)) {
            secretKeyTemplate = new DES2SecretKey();
        }
        generateAESKey.getAllObjectsFromSession(session, secretKeyTemplate, maxObjectCount);

        // Step 5: Close session
        CryptoUtil.closeSession(session);
    }*/

    private void deleteKey(Long mechanismType, String label, int maxObjectCount) throws TokenException {
        // Step 3: Create session and login
        Session session = CryptoUtil.openSessionAndLogin();

        // Step 4: Perform operation
        SecretKey secretKeyTemplate = null;
        if (mechanismType.equals(PKCS11Constants.CKM_AES_KEY_GEN)) {
            secretKeyTemplate = new AESSecretKey();
        } else if (mechanismType.equals(PKCS11Constants.CKM_DES2_KEY_GEN)) {
            secretKeyTemplate = new DES2SecretKey();
        }
        generateAESKey.deleteAESKey(session, secretKeyTemplate, label, maxObjectCount);

        // Step 5: Close session
        CryptoUtil.closeSession(session);
    }
}
