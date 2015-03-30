import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("firefly_kurz.tsv", Mapper.EXPONENT_APPLE);
    mapper.exportData(data, "firefly_kurz");
    
    // DateTimeFilter januaFilter = new DateTimeFilter().betweenDates("1.1.10 - 31.1.10");
    
    // mapper.exportData(januaFilter.apply(data), "malte_spitz_januar09", 1000);
    
    // TrackpointList dataKurz = mapper.importData("malte_spitz_januar09.csv");
    
    for ( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
    }
}

 void draw(){}
