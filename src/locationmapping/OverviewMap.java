package locationmapping;

import processing.core.PApplet;
import processing.event.MouseEvent;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;

/**
 * Die Klasse OverviewMap stellt eine Uebersichtskarte zur Verfuegung
 * welche in der oberen rechten Ecke angezeig wird.
 * Die Karte wird automatisch mit der Hauptkarte gescrollt und gezoomt.
 * Die Karte ermoeglicht es den Ausschnitt der Hauptkarte durch klicken auf eine Position an diese zu verschieben.
 * OverviewMap erweitert UnfoldingMap
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */
public class OverviewMap extends UnfoldingMap {
    /**
     * erzeugendes Mapperobjekt der Uebersichtskarte
     */
    Mapper mapper;
    /**
     * X Koordinate der Postion der Karte
     */
    int x;
    /**
     * Y Koordinate der Postion der Karte
     */
    int y = 16;
    /**
     * Breite der Karte
     */
    int w;
    /**
     * Hoehe der Karte
     */
    int h;

    /**
     * Konstruktor für neue Uebersichtskarten-Objekte
	 *
     * @param mapper das erzeugende Mapper-Objekt
     * @param w die Breite der Uebersichtskarte
     * @param h die Höhe der Uebersichtskarte
     * @param mapProvider der Kartenprovider der Uebersichtskarte
     */
    public OverviewMap(Mapper mapper, int w, int h, AbstractMapProvider mapProvider) {
        super(mapper.app, mapper.width-w-16, 16, w, h, mapProvider);
        this.mapper = mapper;
        this.w = w;
        this.h = h;
    }

    /**
     * Zeichnet die Karte
     */
    public void draw(){
        // X position der Karte wenn nöting updaten
        this.x = this.mapper.app.width - this.w - 16;

        // Berechnung und Updaten der genauen (float) Zoomstufe der Übersichtskarte in Abhängigkeit von der Hauptkarte
        this.zoomTo(this.mapper.map.getZoomFromScale(this.mapper.map.getZoom())-5f);
        // Updaten des Orts der Übersichtskarte in Abhängigkeit von der Hauptkarte
        this.panTo(this.mapper.map.getCenter());

        // Zeichne Uebersichtskarte in obere rechte Ecke
        this.move(this.x, this.y);
        super.draw();

        // Zeichne Rahmen um Karte
        this.mapper.app.noFill();
        this.mapper.app.strokeWeight(1.5f);
        this.mapper.app.stroke(this.mapper.textColor);
        this.mapper.app.rect(this.x, this.y, this.w, this.h);

        // Berechne die Bildschirmpositionen auf der Übersichtskarte, die den Ortskoordinaten der oberen linken und unteren rechten Ecke der Hauptkarte entsprechen
        ScreenPosition tl = this.getScreenPosition(this.mapper.map.getTopLeftBorder());
        ScreenPosition br = this.getScreenPosition(this.mapper.map.getBottomRightBorder());
        // Zeichne Box fuer momentanigen Bereich der Hauptkarte in Übersichtskarte
        this.mapper.app.fill(this.mapper.app.color(mapper.app.hue(this.mapper.highlightColor), mapper.app.saturation(this.mapper.highlightColor), mapper.app.brightness(this.mapper.highlightColor), 20));
        this.mapper.app.stroke(this.mapper.app.color(mapper.app.hue(this.mapper.highlightColor), mapper.app.saturation(this.mapper.highlightColor), mapper.app.brightness(this.mapper.highlightColor), 100));
        this.mapper.app.strokeWeight(3);
        this.mapper.app.rect(tl.x, tl.y, br.x-tl.x, br.y-tl.y);
    }

    /**
     * Ueberprueft ob Maus sich ueber dem Uebersichtskartenobjekt befindet
     *
     * @param xM X-Position der Maus
     * @param yM Y-Position der Maus
     * @return Wahrheitswert ob Maus ueber Button ist
     */
    boolean mouseOver(int xM, int yM) {
        return xM > x && xM < x + w && yM > y && yM < y + h;
    }

    /**
     * Verschiebt die Hauptkarte zur geklickten Position auf der Uebersichtskarte
     *
     * @param x X-Position des Mausklicks
     * @param y Y-Position der Mausklicks
     */
    void panToHandler(float x, float y){
        this.mapper.map.panTo(this.getLocation(x, y));
    }
}