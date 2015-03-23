import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*; 
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyMap extends UnfoldingMap {
  public MyMap(PApplet window){
      super(window);
      this.setTweening(true); // richtiges smooth Movement
      this.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
      MapUtils.createDefaultEventDispatcher(window, this); //für StandardInteraktion
      slider = new SliderButton(150, 3, stageWidth, stageHeight);
      
      addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pause = !pause;
          }
        }
      } );
  

  }

}
