import de.fhpotsdam.unfolding.*;
import locationmapping.*;

/**
 * In diesem Beispiel werden nur die Daten eingelesen und nacheinander
 * eingezeichnet.
 */

void setup() {
    
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("../../data/celllocation_mittel.tsv");
    
    for( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
    }
}

void draw(){}
