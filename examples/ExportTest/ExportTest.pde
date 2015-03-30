import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

void setup() {
    Mapper mapper = new StaticMapper(this);
    mapper.init();
    
    TrackpointList data = mapper.importData("malte_spitz_orig.csv", Mapper.MDY_DATETIME);
    mapper.exportData(data, "malte_spitz");
    
    // DateTimeFilter januaFilter = new DateTimeFilter().betweenDates("1.1.10 - 31.1.10");
    
    // mapper.exportData(januaFilter.apply(data), "malte_spitz_januar09", 1000);
    
    // TrackpointList dataKurz = mapper.importData("malte_spitz_januar09.csv");
    
    // for ( Trackpoint tp : dataKurz ){
    //     mapper.addMarker(new StandardMarker(tp));
    // }
}

// void draw(){}
