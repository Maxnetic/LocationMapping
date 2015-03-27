import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new DynamicMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("malte_spitz.csv", DataImporter.MDY_DATETIME);
    
    for ( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
    }
}

void draw(){}
