import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
/*
 * Testklasse für verschiedene Marker
 */

import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*; 

UnfoldingMap map;
ColoredMarker berlinColoredMarker;
FormedMarker randomFormedMarker;
List markerList;

// creating map and marker
void setup() {
  size(800, 600);
 
  map = new UnfoldingMap(this);
  MapUtils.createDefaultEventDispatcher(this, map);
 
  Location berlinLocation = new Location(52.5, 13.4);
  Location randomLocation = new Location(60.7, 8.2);
  berlinColoredMarker = new ColoredMarker(berlinLocation);
  randomFormedMarker = new FormedMarker(randomLocation);
 
  
  // ----- Nicht gewolltes auskommentieren:
  
  /*
   * Test für ColoredMarker / UpdateableMarker
   */
  map.addMarker(berlinColoredMarker);
  map.addMarker(randomFormedMarker);
  
  berlinColoredMarker.updateColor(0,250,0);
 
  // größe anpassen
  berlinColoredMarker.updateSize(400);
  
  // verstecken
  berlinColoredMarker.updateHidden(true);
  
  // größe wieder anpassen, obwohl versteckt wird sie angepasst
  berlinColoredMarker.updateSize(60);
  
  // sichtbar machen
  berlinColoredMarker.updateHidden(false);
  
  markerList = (map.getMarkers());
  
  //Iteration über alle erstellten Marker, um deren Größe zu verändern
  for (int i=0;i <markerList.size();i++){
    UpdateableMarker current;
    current = (UpdateableMarker)markerList.get(i);
    current.updateSize(500);
  }

  /*
   * Test für FormedMarker
   */
 //map.addMarker(berlinFormedMarker);
  

}
 
/*
 * draw wird permanent ausgeführt
 */
void draw() {
  map.draw();
  //ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map); //not necessary
  
  
  
  
}
