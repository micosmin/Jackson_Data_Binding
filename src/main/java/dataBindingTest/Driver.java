package dataBindingTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class Driver {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("=========Simple JSON========");
        //JSON -> POJO
        Student myStudent = mapper.readValue(new File("src/main/dataTest/sample.json"), Student.class);

        System.out.println(myStudent.getId());
        System.out.println(myStudent.getFirstName());
        System.out.println(myStudent.getLastName());
        System.out.println(myStudent.isActive());

        mapper.enable(SerializationFeature.INDENT_OUTPUT); //Pretty printing

        System.out.println("=========Nested JSON========");

        //JSON -> POJO - nested objects
        Student theStudent = mapper.readValue(new File("src/main/dataTest/sample-full.json"), Student.class);

        System.out.println(theStudent.getId());
        System.out.println(theStudent.getFirstName());
        System.out.println(theStudent.getLastName());
        System.out.println(theStudent.isActive());

        //Print address
        Address tempAddress = theStudent.getAddress();
        System.out.println("Street = " + tempAddress.getStreet());
        System.out.println("City = " + tempAddress.getCity());


        //Print languages
        for(String lang: theStudent.getLanguages()){
            System.out.println(lang);
        }


        //POJO -> JSON
        mapper.writeValue(new File("src/main/dataTest/output.json"), myStudent);


        Location myLocation = new Location();
        myLocation.setLatitude(52.43);
        myLocation.setLongitude(-0.05);

        mapper.writeValue(new File("src/main/dataTest/location.json"), myLocation);
    }
}
