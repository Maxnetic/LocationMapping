import locationmapping.*;
import de.fhpotsdam.unfolding.*;

/**
 * In diesem Beispiel werden alle Orte aufgelistet, 
 * die mindestens 500 Mal besucht wurden.
 */

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    // Import Data
    TrackpointList trackpointlist = mapper.importData("../../data/personX.csv");
    LocationFilter mostcommon = new LocationFilter().setMinFrequency(500);
    TrackpointList filteredtpl = mostcommon.apply(trackpointlist);
    
    
    for ( Trackpoint trackpoint : filteredtpl ){
        PointMarker marker = new PointMarker(trackpoint);
        
        marker.setLabel(">500").setSize(10);
        mapper.addMarker(marker);
    }
}

void draw() {}
