package com.learnbyexample.hsmexample;

import iaik.pkcs.pkcs11.Mechanism;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.objects.*;
import iaik.pkcs.pkcs11.objects.Object;
import iaik.pkcs.pkcs11.wrapper.PKCS11Constants;

class GenerateKey {

    void generateKey(Session session, Long mechanismType, String label) throws TokenException {
        // Select Mechanism/Algorithm
        Mechanism keyGenerationMechanism = Mechanism.get(mechanismType);

        SecretKey secretKeyTemplate = null;

        if (mechanismType.equals(PKCS11Constants.CKM_AES_KEY_GEN)) {
            // Prepare key template (how HSM should behave)
            secretKeyTemplate = new AESSecretKey();
            ((AESSecretKey) secretKeyTemplate).getValueLen().setLongValue(32L);
            secretKeyTemplate.getLabel().setCharArrayValue(label.toCharArray());
            secretKeyTemplate.getId().setByteArrayValue(new byte[]{105});
            secretKeyTemplate.getSign().setBooleanValue(Boolean.TRUE);
            secretKeyTemplate.getVerify().setBooleanValue(Boolean.TRUE);
            // This will make session local or global
            secretKeyTemplate.getToken().setBooleanValue(Boolean.TRUE);
        } else if (mechanismType.equals(PKCS11Constants.CKM_DES2_KEY_GEN)) {
            // Prepare key template (how HSM should behave)
            secretKeyTemplate = new DES2SecretKey();
            //((DESSecretKey)secretKeyTemplate).getValueLen().setLongValue(32L);
            secretKeyTemplate.getLabel().setCharArrayValue(label.toCharArray());
            secretKeyTemplate.getId().setByteArrayValue(new byte[]{105});
            secretKeyTemplate.getSign().setBooleanValue(Boolean.TRUE);
            secretKeyTemplate.getVerify().setBooleanValue(Boolean.TRUE);
            // This will make session local or global
            secretKeyTemplate.getToken().setBooleanValue(Boolean.TRUE);
        }

        // Create key
        SecretKey secretKey = (SecretKey) session.generateKey(keyGenerationMechanism, secretKeyTemplate);

        System.out.println("Key generated with label: "+secretKey.getLabel());
    }

    void deleteAESKey(Session session, SecretKey template, String label, int maxObjectCount) throws TokenException {
        // Step 4: Perform operation

        // Find all objects
        // TODO change get all object's from the session with only getting the required object only
        Object[] objects = getAllObjectsFromSession(session, template, maxObjectCount);

        // Key is found and displayed here!!!
        for (Object object : objects) {
            if (object.getAttributeTable().get(3L).toString().equals(label)) {
                session.destroyObject(object);
                System.out.println("Removed object with label: "+label);
            }
        }
    }

    private Object[] getAllObjectsFromSession(Session session, SecretKey template, int maxObjectCount) throws TokenException {
        session.findObjectsInit(template);
        return session.findObjects(maxObjectCount);
    }
}