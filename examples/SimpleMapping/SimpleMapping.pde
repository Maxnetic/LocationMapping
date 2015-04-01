import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    for ( Trackpoint trackpoint : data ){
        Marker marker = new StandardMarker(trackpoint);
        mapper.addMarker(marker);
    }
}


void draw() {}
