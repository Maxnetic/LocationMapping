import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


 public class MyMarker extends SimplePointMarker{
  
  Location location;
  
  int size;
  
  int red = 0;
  
  int yellow = 0;
  
  int blue = 0;
  
  String label = "";
  
  String shape = "Punkt";
  
 /*
  * Konstruktor fuer UpdatableMarker Objekte
  * @param location [Location]: Ortsangabe des Markers
  * @return neues Objekt vom Typ Marker
  */
  public MyMarker(Trackpoint trackpoint) {
   super(trackpoint.getLocation());
   location = trackpoint.getLocation();


  }
  
  public void setSize(int size){
    this.size = size;
  }
  
  public void setColor(int red, int yellow, int blue){
   this.red = red;
   this.yellow = yellow;
   this.blue = blue;
  } 

  public void setLabel(String label){
    this.label = label;
  }
  
  public void setShape(String shape){
    this.shape = shape; 
}


 }
      
