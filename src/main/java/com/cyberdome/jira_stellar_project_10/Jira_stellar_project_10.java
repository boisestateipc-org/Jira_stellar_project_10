/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cyberdome.jira_stellar_project_10;

/**
 *
 * @author Travis
 */
public class Jira_stellar_project_10 {

   
public static void CreateJiraTicket() {
    // Jira API endpoint for creating an issue
    String apiUrl = "https://your-jira-instance/rest/api/2/issue";

    // Jira credentials for authentication
    String username = "your-username";
    String password = "your-password";

    // JSON payload for creating an issue
    String payload = "{\"fields\":{\"project\":{\"key\":\"YOUR-PROJECT-KEY\"},\"summary\":\"Test issue\",\"description\":\"This is a test issue\",\"issuetype\":{\"name\":\"Bug\"}}}";

    // Create the HTTP client and set up authentication
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
    CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();

    // Create the HTTP POST request with the API endpoint
    HttpPost httpPost = new HttpPost(apiUrl);
    httpPost.setHeader("Content-Type", "application/json");
    httpPost.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));

    try {
        // Execute the request and retrieve the response
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        // Parse the response and handle the result
        if (responseEntity != null) {
            String responseString = EntityUtils.toString(responseEntity);
            System.out.println("Response: " + responseString);
            // Handle the response as needed
        } else {
            System.out.println("Error: No response received from Jira");
        }
    } catch (IOException e) {
        System.out.println("Error creating Jira ticket: " + e.getMessage());
    } finally {
        // Close the HTTP client and release any resources
        try {
            httpClient.close();
        } catch (IOException e) {
            System.out.println("Error closing HTTP client: " + e.getMessage());
        }
    }
}