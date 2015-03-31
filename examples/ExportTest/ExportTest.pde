import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

/**
 *  In diesem Beispiel wird gezeigt, wie man eine tsv-Datei mithilfe von Locationmapping in eine csv-Datei exportieren kann.
 *  Die csv-Dateien sind schneller zu lesen.
 */

void setup() {
    StaticMapper mapper = new StaticMapper(this);
    mapper.init();
    
    //hier kann man optional über Mapper.EXPONENT_APPLE den Export auslösen.
    TrackpointList data = mapper.importData("firefly_kurz.tsv", Mapper.EXPONENT_APPLE);
    //hier wird der Name der csv-Datei angegeben.
    mapper.exportData(data, "firefly_kurz");
    
    // DateTimeFilter januaFilter = new DateTimeFilter().betweenDates("1.1.10 - 31.1.10");
    
    // mapper.exportData(januaFilter.apply(data), "malte_spitz_januar09", 1000);
    
    // TrackpointList dataKurz = mapper.importData("malte_spitz_januar09.csv");
    
    for ( Trackpoint tp : data ){
        mapper.addMarker(new StandardMarker(tp));
    }
}

 void draw(){}
