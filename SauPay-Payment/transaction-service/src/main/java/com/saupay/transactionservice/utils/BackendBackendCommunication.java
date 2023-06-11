package com.saupay.transactionservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.saupay.transactionservice.request.EncryptedPaymentRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendBackendCommunication {

    private final ObjectMapper objectMapper;
    private final EncryptionUtil encryptionUtil;

    public BackendBackendCommunication(ObjectMapper objectMapper, EncryptionUtil encryptionUtil) {
        this.objectMapper = objectMapper;
        this.encryptionUtil = encryptionUtil;
    }

    public String getBackendToBackendEncryptedAndSignatureDataTransaction(EncryptedPaymentRequest encryptedRequest, String signature, String randomKey, String secretKey)
    {
        try {
            String json = objectMapper.writeValueAsString(encryptedRequest); // Convert body to json
            System.out.println("Encrypted Request -Backend:  " + json);
            Boolean isSignatureValid = encryptionUtil.checkSignature(signature, randomKey, json, secretKey); // Verify signature
            System.out.println("Signature is Valid-Backend:  " + isSignatureValid);
            if (!isSignatureValid) {
                throw new RuntimeException("Signature is not valid");
            }
            String decryptedData = encryptionUtil.decrypt(encryptedRequest.getData(), secretKey); // When signature is valid, decrypt data
            System.out.println("Decrypted Request-Backend: " + decryptedData);

            return decryptedData;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }

    public String sendBackendToBackendEncryptedDataTransaction(String token,String secretKey)
    {
        try {
            String encryptedToken = encryptionUtil.encrypt(token, secretKey); // Encrypt token
            System.out.println("Encrypted Token-Backend: " + encryptedToken);

            return encryptedToken;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }







    public String SendBackendToBackendEncryptedAndSignatureDataPayment(String decryptedData,String randomKey, String secretKey)
    {
        try {
            // çözülen datayı tekrar şifrele
            String encryptedData = encryptionUtil.encrypt(decryptedData,secretKey);
            System.out.println("Encrypted Data Request-Backend: " + encryptedData );

            // şifrelenen datayı EncryptedPaymentRequest objesine set et
            EncryptedPaymentRequest encryptedPaymentRequestBackend = new EncryptedPaymentRequest();
            encryptedPaymentRequestBackend.setData(encryptedData);


            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<EncryptedPaymentRequest> requestEntity = new HttpEntity<>(encryptedPaymentRequestBackend, headers); // EncryptedPaymentRequest objesini HttpEntity objesine set et (Body)
            String requestBody = objectMapper.writeValueAsString(requestEntity.getBody());


            System.out.println("Random Key-Backend: " + randomKey );
            String signatureData = encryptionUtil.signature(randomKey,requestBody,secretKey);
            System.out.println("Signature Data -Backend: " + signatureData );

            headers.set("x-signature", signatureData);
            headers.set("x-rnd-key", randomKey);

            String response = restTemplate.postForObject("http://localhost:8887/v1/saubank/payment",requestEntity, String.class);
            System.out.println("Response(TOKEN) -Backend: " + response );

            return response;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }


    public String GetBackendToBackendSignatureDataPayment(String encryptedData,String secretKey)
    {
        try {
            System.out.println("Encrypted Reponse -Backend:  " + encryptedData);
            String decryptedData = encryptionUtil.decrypt(encryptedData, secretKey); // When signature is valid, decrypt data
            System.out.println("Decrypted Reponse-Backend: " + decryptedData);
            return decryptedData;
        }
        catch (Exception e)
        {
            throw new RuntimeException("BackendToBackendEncryptedAndSignatureDataTransaction error: " + e.getMessage());
        }
    }

}
