import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import java.sql.Timestamp;


void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    // Import Data
    TrackpointList trackpointlist = mapper.importData("malte_spitz.csv");
    Filter mostcommon = new Filter();
    mostcommon.setMinFrequency(500);
    TrackpointList filteredtpl = mostcommon.apply(trackpointlist);
    
    
    for ( Trackpoint trackpoint : filteredtpl ){
        StandardMarker marker = new StandardMarker(trackpoint);
        
        marker.setColor(10);  // funktioniert nicht, nur grau
        marker.setStyle("Labeled");
        marker.setLabel("Wohnort");
        marker.setSize(10);
        mapper.addMarker(marker);
    }
}

void draw() {}
