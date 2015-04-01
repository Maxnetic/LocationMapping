import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import java.sql.Timestamp;

/**
 * In diesem Beispiel werden alle Orte aufgelistet, 
 * die mindestens 500 Mal besucht wurden.
 */

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    // Import Data
    TrackpointList trackpointlist = mapper.importData("../../data/personX.csv");
    LocationFilter mostcommon = new LocationFilter();
    mostcommon.setMinFrequency(500);
    TrackpointList filteredtpl = mostcommon.apply(trackpointlist);
    
    
    for ( Trackpoint trackpoint : filteredtpl ){
        MarkerLabeled marker = new MarkerLabeled(trackpoint);
        
        marker.setColor(100,200,0);
        marker.setLabel(">500");
        marker.setSize(10);
        mapper.addMarker(marker);
    }
}

void draw() {}
