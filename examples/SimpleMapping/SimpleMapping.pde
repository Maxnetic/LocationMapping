import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    for ( Trackpoint trackpoint : data ){
        ServiceMarker marker = new ServiceMarker(trackpoint);
        marker.setRadius(40).setHue(100).setSaturation(50).setBrightness(80);
        mapper.addMarker(marker);
    }
}


void draw() {}
