package com.saupay.domainservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saupay.domainservice.clients.request.EncryptedPaymentRequest;

import org.springframework.stereotype.Component;
import static com.saupay.domainservice.utils.Constants.SECRET_KEY_BACKEND;


@Component
public class BackendBackendCommunication {

    private final ObjectMapper objectMapper;
    private final EncryptionUtil encryptionUtil;

    public BackendBackendCommunication(ObjectMapper objectMapper, EncryptionUtil encryptionUtil) {
        this.objectMapper = objectMapper;
        this.encryptionUtil = encryptionUtil;
    }

    public String getBackendToBackendEncryptedAndSignatureDataTransaction(EncryptedPaymentRequest encryptedRequest, String signature, String randomKey)
    {
        try {
            String json = objectMapper.writeValueAsString(encryptedRequest); // Convert body to json
            System.out.println("Encrypted Request -Backend:  " + json);
            Boolean isSignatureValid = encryptionUtil.checkSignature(signature, randomKey, json, SECRET_KEY_BACKEND); // Verify signature
            System.out.println("Signature is Valid-Backend:  " + isSignatureValid);
            if (!isSignatureValid) {
                throw new RuntimeException("Signature is not valid");
            }
            String decryptedData = encryptionUtil.decrypt(encryptedRequest.getData(), SECRET_KEY_BACKEND); // When signature is valid, decrypt data
            System.out.println("Decrypted Request-Backend: " + decryptedData);

            return decryptedData;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }

    public String sendBackendToBackendEncryptedDataTransaction(String token)
    {
        try {
            String encryptedToken = encryptionUtil.encrypt(token, SECRET_KEY_BACKEND); // Encrypt token
            System.out.println("Encrypted Token-Backend: " + encryptedToken);

            return encryptedToken;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }

}
