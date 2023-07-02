package com.popjak.SignUP.externalApiService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    private final String DEFAULT_ENDPOINT = "https://us9.api.mailchimp.com/3.0/lists/REPLACE-THIS-WITH-YOUR-OWN-LIST-ID";
    private final String API_KEY = "REPLACE-THIS-WITH-YOUR-OWN-KEY";
    private final String OPTIONS = "{method: 'GET', auth: 'andrej:REPLACE-THIS-WITH-YOUR-OWN-KEY'}";


    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String subscribeMember2(String firstName, String lastName, String email) {
        // Create HttpHeaders object to set the necessary HTTP headers for the request
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + API_KEY); // Set the API key in the Authorization header
        httpHeaders.add("Content-Type", "application/json"); // Specify the content type as JSON

        // Create the necessary data structures to construct the request payload
        Map<String, Object> member = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> mergeFields = new HashMap<>();

        // Set the email address and subscription status for the member
        member.put("email_address", email);
        member.put("status", "subscribed");

        // Set the first name and last name in the merge fields
        mergeFields.put("FNAME", firstName);
        mergeFields.put("LNAME", lastName);

        // Combine the merge fields with the member details
        member.put("merge_fields", mergeFields);

        // Create a list of members and add the current member to it
        List<Map<String, Object>> multipleMembers = new ArrayList<>();
        multipleMembers.add(member);

        // Combine the list of members with the payload
        payload.put("members", multipleMembers);

        // Create the HTTP request entity with the payload and headers
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, httpHeaders);

        // Make the POST request to the default endpoint using RestTemplate and retrieve the response body
        return restTemplate.exchange(DEFAULT_ENDPOINT, HttpMethod.POST, requestEntity, String.class).getBody();
    }

}
