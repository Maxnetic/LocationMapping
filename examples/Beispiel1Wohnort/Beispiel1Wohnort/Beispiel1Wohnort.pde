import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


LocationMapper mapper = new LocationMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Filter wohnortfilter = new Filter();
  wohnortfilter.setStarttime("02:00");
  wohnortfilter.setEndtime("05:00");
  wohnortfilter.setMinFrequency(3000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
    
  for ( Trackpoint tp : moeglichewohnorte ) {
    StandardMarker wohnort = new StandardMarker(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setStyle("Labeled");
    mapper.addMarker(wohnort);
  }
    
    
      
}

void draw() {
      
}

