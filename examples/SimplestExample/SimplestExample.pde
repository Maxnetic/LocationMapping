import de.fhpotsdam.unfolding.*;
import locationmapping.*;

/**
 * In diesem Beispiel werden nur die Daten eingelesen und nacheinander
 * eingezeichnet.
 */

void setup() {
    
    Mapper mapper = new StaticMapper(this);
    mapper.init(1280, 800);
    
    TrackpointList data = mapper.importData("../../data/max_mittel.json");
    mapper.write("personY");
    // TrackpointList data = mapper.importData("../../data/personY.csv");
    
    for( Trackpoint tp : data ){
        StandardMarker marker = new StandardMarker(tp);
        marker.setId(tp.getTime().toString("EE, HH:mm:ss, MMM d, YYYY"));
        mapper.addMarker(marker);
    }
}

void draw(){}
