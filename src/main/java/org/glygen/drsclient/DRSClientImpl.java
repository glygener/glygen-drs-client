package org.glygen.drsclient;

import java.util.Arrays;

import org.glygen.drsclient.model.AccessURL;
import org.glygen.drsclient.model.DrsError;
import org.glygen.drsclient.model.DrsObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DRSClientImpl implements DRSClient {
    
    private RestTemplate restTemplate = new RestTemplate();

    public Object getDrsObject(String drsId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Void> requestEntity = new HttpEntity<Void>(null, headers);
        String url = "https:" + drsId.substring(drsId.indexOf(":")+1, drsId.lastIndexOf("/")) + 
                "/ga4gh/drs/v1/objects/" + drsId.substring(drsId.lastIndexOf("/")+1);
        try {
            ResponseEntity<Object> response = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
            ObjectMapper mapper = new ObjectMapper();
            DrsObject drs = mapper.convertValue (response.getBody(), DrsObject.class);
            return drs;
        } catch (HttpClientErrorException e) {
            ObjectMapper mapper = new ObjectMapper();
            DrsError error;
            try {
                error = mapper.readValue (e.getResponseBodyAsString(), DrsError.class);
                return error;
            } catch (Exception e1) {
                throw new RuntimeException("Cannot convert the error message: " + e.getResponseBodyAsString());
            }
        }
    }

    public Object getAccessURL(String drsId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Void> requestEntity = new HttpEntity<Void>(null, headers);
        String url = "https:" + drsId.substring(drsId.indexOf(":")+1, drsId.lastIndexOf("/")) + 
                "/ga4gh/drs/v1//objects/" + drsId.substring(drsId.lastIndexOf("/")+1) + 
                "/access/" + drsId.substring(drsId.lastIndexOf("/")+1);
        try {
            ResponseEntity<Object> response = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
            ObjectMapper mapper = new ObjectMapper();
            AccessURL access = mapper.convertValue (response.getBody(), AccessURL.class);
            return access;
        } catch (HttpClientErrorException e) {
            ObjectMapper mapper = new ObjectMapper();
            DrsError error;
            try {
                error = mapper.readValue (e.getResponseBodyAsString(), DrsError.class);
                return error;
            } catch (Exception e1) {
                throw new RuntimeException("Cannot convert the error message: " + e.getResponseBodyAsString());
            }
        }
    }

}
