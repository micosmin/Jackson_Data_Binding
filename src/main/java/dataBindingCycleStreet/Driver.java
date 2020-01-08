package dataBindingCycleStreet;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            CycleData data = mapper.readValue(new File("src/main/dataCycleStreet/cycleStreetPOI.json"), CycleData.class);

            List<Feature> bikeData = data.getFeatures();

            for(Feature fet: bikeData){
                System.out.println(fet.getGeometry().getCoordinates());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
