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
  wohnortfilter.setMinFrequency(2000);
  TrackpointList moeglicheWohnorteMalte;
  
  
  moeglicheWohnorteMalte = wohnortfilter.apply(list1);
  
  Filter jsonwohnortfilter = new Filter();
  jsonwohnortfilter.setStarttime("02:00");
  jsonwohnortfilter.setEndtime("05:00");
  jsonwohnortfilter.setMinFrequency(500); //eventuell hochsetzen
  TrackpointList moeglicheWohnorteMax;
  
  moeglicheWohnorteMax = jsonwohnortfilter.apply(list2);
  
  
    
  for ( Trackpoint tp : moeglicheWohnorteMalte ) {
    MarkerLabeled wohnortMalte = new MarkerLabeled(tp);
    wohnortMalte.setLabel("  möglicher Wohnort von Malte");
    wohnortMalte.setColor(200,0,200);
    wohnortMalte.setTransparency(50);
    mapper.addMarker(wohnortMalte);
  }
  for (Trackpoint tp : moeglicheWohnorteMax){
    MarkerLabeled wohnortMax = new MarkerLabeled(tp);
    wohnortMax.setColor(0,200,200);
    wohnortMax.setTransparency(50);
    wohnortMax.setLabel("  möglicher Wohnort von Max");
    mapper.addMarker(wohnortMax);
  }
    
    
      
}

void draw() {
      
}

