import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    for ( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
    }
}

void draw(){}
