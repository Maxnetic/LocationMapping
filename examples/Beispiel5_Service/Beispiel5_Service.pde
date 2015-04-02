import locationmapping.*;
import de.fhpotsdam.unfolding.*;

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
  Mapper mapper = new DynamicMapper(this);
  mapper.init();
    
  TrackpointList data = mapper.importData("../../data/personX.csv");
  
  for ( Trackpoint tp : data ) {
      ServiceMarker marker = new ServiceMarker(tp);
      mapper.addMarker(marker);
  }

}

void draw(){}
