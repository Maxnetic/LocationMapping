import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    for ( Trackpoint trackpoint : data ){
        PointMarker marker = new PointMarker(trackpoint);
        marker.setLabel(trackpoint.getService());
        mapper.addMarker(marker);
    }
}


void draw() {}
