import java.util.*;
import java.io.FileReader;
import java.sql.Timestamp;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.text.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class jsonimport {

  public TrackpointList ladeJSON(String filename, int delta_zeit, int max_datensaetze){
        JSONParser parser = new JSONParser();
        TrackpointList trackpointList = new TrackpointList();
        
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("locations");
            Iterator<?> teststring = array.listIterator();

            int i = 0;
            int datensaetze = 0;
            long erste = 0;
            while (teststring.hasNext() && (datensaetze < max_datensaetze)) {
                JSONObject obj2=(JSONObject)array.get(i);
                String timestamp = obj2.get("timestampMs").toString();
                long zweite = Long.valueOf(timestamp);
                Timestamp time = new Timestamp(zweite);
                //Filter, der Daten, dessen Zeitpunkte weniger als 60 Sekunden auseinanderliegen, herausfiltert.
                if(Math.abs(erste - zweite) > delta_zeit){
                      	// Umwandlung
                	String laenge = obj2.get("longitudeE7").toString();
                	laenge = laenge.substring(0, 2) + "." + laenge.substring(2, 6);
                	float longitude = Float.valueOf(laenge);

                	// Umwandlung
                	String breite = obj2.get("latitudeE7").toString();
                	breite = breite.substring(0, 2) + "." + breite.substring(2, 6);
                          	
                	float latitude = Float.valueOf(breite);
                
                        Location loc = new Location (latitude, longitude);
                        datensaetze ++;
                
                        Trackpoint trackpoint = new Trackpoint(time, loc, 1, "GPS");
                        trackpointList.add(trackpoint);
                        erste = zweite;
                }
                i++;                
                teststring.next();
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
      return trackpointList;
  }
}
