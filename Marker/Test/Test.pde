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
FormedMarker berlinFormedMarker;

// creating map and marker
void setup() {
  size(800, 600);
 
  map = new UnfoldingMap(this);
  MapUtils.createDefaultEventDispatcher(this, map);
 
  Location berlinLocation = new Location(52.5, 13.4);
  berlinColoredMarker = new ColoredMarker(berlinLocation);
  berlinFormedMarker = new FormedMarker(berlinLocation);
 
  // Do not add marker to the map
  
  System.out.println(map.getMarkerManagerList());
}
 
/*
 * draw wird permanent ausgeführt
 */
void draw() {
  map.draw();
  ScreenPosition berlinPos = berlinColoredMarker.getScreenPosition(map);
  
  // Nicht gewolltes auskommentieren:
  
  /*
   * Test für ColoredMarker / UpdateableMarker
   */
  map.addMarker(berlinColoredMarker);
 
  // größe anpassen
  berlinColoredMarker.updateSize(400);
  
  // verstecken
  berlinColoredMarker.updateHidden(true);
  
  // größe wieder anpassen, obwohl versteckt wird sie angepasst
  berlinColoredMarker.updateSize(60);
  
  // sichtbar machen
  berlinColoredMarker.updateHidden(false);

  /*
   * Test für FormedMarker
   */
 //map.addMarker(berlinFormedMarker);
  
  
}
