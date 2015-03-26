import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    LocationMapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("malte_spitz.csv");
    
    int i=0;
    for ( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
        i++;
    }
    System.out.println(i);
}

void draw(){}
