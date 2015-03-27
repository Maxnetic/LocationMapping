import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


DynamicMapper mapper = new DynamicMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  LocationFilter frequencyfilter = new LocationFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  
    
  for ( Trackpoint tp : moeglichewohnorte ) {
    MarkerLabeled wohnort = new MarkerLabeled(tp);
    wohnort.setLabel("wohnort");
    wohnort.setColor(200, 0, 200);
    wohnort.setTransparency(50);
    mapper.addMarker(wohnort);
  }
    
    
      
}

void draw() {
      
}

