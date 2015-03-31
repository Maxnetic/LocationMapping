import de.fhpotsdam.unfolding.*;
import locationmapping.*;

/**
 * In diesem Beispiel werden nur die Daten eingelesen und nacheinander
 * eingezeichnet.
 */

void setup() {
    
<<<<<<< HEAD:examples/SimplestExample/SimplestExample.pde
    Mapper mapper = new StaticMapper(this);
    mapper.init(1280, 800);
=======
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    mapper.map.setTweening(false);
>>>>>>> 9fa9df97f2631e95e67fa09008f32c0ec3149f31:examples/Unfertig/SimplestExample/SimplestExample.pde
    
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
