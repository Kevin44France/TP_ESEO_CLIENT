package com.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Ville;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    public List<Ville> getVillesData() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8181/villes";
        String response = restTemplate.getForObject(apiUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Ville> villes = objectMapper.readValue(response, new TypeReference<List<Ville>>() {
            });
            return villes;
        } catch (Exception e) {
            throw new RuntimeException("Error while deserializing API response", e);
        }

    }

    public Ville getVilleData(String codeCommune) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl =
                "http://localhost:8181/codeCommune?codeCommune=" + codeCommune;
        Ville response = restTemplate.getForObject(apiUrl, Ville.class);
        return response;
    }

    public static Ville modifyNomCommune(String codeCommune, String nomCommune) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8181/modifyNomCommune";
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("codeCommune", codeCommune);
        requestBody.add("nomCommune", nomCommune);
        Ville response = restTemplate.postForObject(apiUrl, requestBody, Ville.class);
        return response;
    }

    public static Ville modifyCodePostal(String codeCommune,
                                         String codePostal) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8181/modifyCodePostal";
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("codeCommune", codeCommune);
        requestBody.add("codePostal", codePostal);
        Ville response = restTemplate.postForObject(apiUrl, requestBody, Ville.class);
        return response;
    }

    public static Ville modifyLibelle(String codeCommune, String libelle) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8181/modifyLibelle";
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("codeCommune", codeCommune);
        requestBody.add("libelle", libelle);
        Ville response = restTemplate.postForObject(apiUrl, requestBody, Ville.class);
        return response;
    }

    public static Ville modifyLigne(String codeCommune, String ligne) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8181/modifyLigne";
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("codeCommune", codeCommune);
        requestBody.add("ligne", ligne);
        Ville response = restTemplate.postForObject(apiUrl, requestBody, Ville.class);
        return response;
    }

}