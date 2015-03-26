import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList list1;
  list1 = mapper.importData("malte_spitz.csv");
  TrackpointList list2;
  list2 = mapper.importData("max_mittel.json");
    
  Filter wohnortfilter = new Filter();
  wohnortfilter.setStarttime("02:00");
  wohnortfilter.setEndtime("05:00");
  wohnortfilter.setMinFrequency(300);
  TrackpointList moeglicheWohnorteMalte;
  
  
  moeglicheWohnorteMalte = wohnortfilter.apply(list1);
  
  Filter jsonwohnortfilter = new Filter();
  jsonwohnortfilter.setStarttime("02:00");
  jsonwohnortfilter.setEndtime("05:00");
  jsonwohnortfilter.setMinFrequency(25);
  TrackpointList moeglicheWohnorteMax;
  
  moeglicheWohnorteMax = jsonwohnortfilter.apply(list2);
  
  
    
  for ( Trackpoint tp : moeglicheWohnorteMalte ) {
    StandardMarker wohnortMalte = new StandardMarker(tp);
    wohnortMalte.setStyle("Labeled");
    wohnortMalte.setLabel("möglicher Wohnort von Malte");
    mapper.addMarker(wohnortMalte);
  }
  for (Trackpoint tp : moeglicheWohnorteMax){
    StandardMarker wohnortMax = new StandardMarker(tp);
    wohnortMax.setStyle("Labeled");
    wohnortMax.setLabel("möglicher Wohnort von Max");
    mapper.addMarker(wohnortMax);
  }
    
    
      
}

void draw() {
      
}
