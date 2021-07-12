package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.*;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.*;

import java.io.*;
import java.util.Properties;

public class UtilTest {
    public static RequestSpecification requestSpecification;

    public  RequestSpecification req() throws IOException {
        if(requestSpecification == null) {
            PrintStream log = new PrintStream( new FileOutputStream( "logger.txt" ) );
            requestSpecification = new RequestSpecBuilder().setBaseUri( getBaseUri( "baseUri" ) )
                    .addQueryParam( "key", "qaclick123" )
                    .addFilter( RequestLoggingFilter.logRequestTo( log ) )
                    .addFilter( ResponseLoggingFilter.logResponseTo( log ) )
                    .setContentType( ContentType.JSON ).build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public  String getBaseUri(String uri) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream( "C:\\IO\\APITest\\src\\test\\java\\config\\config.properties" );
        prop.load( fis );
        return prop.getProperty( uri );
    }

    public String getJsonPath(String responseBody,String responseKey)
    {
        JsonPath js=new JsonPath(responseBody  );
        String responseValue=js.get(responseKey);
        return responseValue;
    }
}
