package config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import lombok.Data;


@Data
public class RestConfig {

    private RequestSpecification httpRequest;
    private ContentType content;
    private Method method;

//    public RestConfig(String url, String contentType, String requestMethod) {
//        for (ContentType c : ContentType.values()) {
//            if (c.matches("(.*)" + contentType + "(.*)")) {
//                this.content = c;
//            }
//            for (Method m : Method.values()) {
//                if (m.toString().equalsIgnoreCase(requestMethod)) {
//                    this.method = m;
//                }
//            }
//            RestAssured.baseURI = url;   // is this working?
//            this.httpRequest = getHttpRequest().accept(content).request(method);
//    }
//
//    public RequestSpecification configRestApiEndpoint(String url, String contentType, String requestMethod) {
//        for (ContentType c : ContentType.values()) {
//            if (c.matches("(.*)" + contentType + "(.*)")) {
//                content = c;
//            }
//        }
//        for (Method m : Method.values()) {
//            if (m.toString().equalsIgnoreCase(requestMethod)) {
//                method = m;
//            }
//        }
//        RestAssured.baseURI = url;
//        httpRequest.accept(content).request(method);
//        return httpRequest;
//    }
}

