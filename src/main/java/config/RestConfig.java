package config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;



public class RestConfig {

    private RequestSpecification httpRequest;

    public void setupUrl(String url) {
        RestAssured.baseURI = url;
    }

    public void setContentType(String contentType) {
      for (ContentType c : ContentType.values()) {
          if (c.matches("(.*)" + contentType + "(.*)")){
              httpRequest.accept(c);
          }
      }
    }

}
