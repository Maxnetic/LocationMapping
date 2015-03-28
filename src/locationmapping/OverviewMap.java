package locationmapping;

import processing.core.PApplet;
import processing.event.MouseEvent;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;

/**
 * Übersichtskarte, welche in der oberen rechten Ecke angezeig wird.
 * Die Karte wird automatisch mit der Hauptkarte gescrollt und gezoomt.
 * Doe Karte ermöglicht es den Ausschnitt der Hauptkarte durch clicken auf eine Position an diese zu verschieben.
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
     * Höhe der Karte
     */
    int h;
    /**
     * Farbe des Ramens der Markierung auf der Karte
     */
    int color;
    /**
     * Farbe des Flächeninhalts des Ramens der Markierung auf der Karte
     */
    int transparentColor;

    /**
     * Konstruktor für neue Übersichtskarten-Objekte
     * @param mapper das erzeugende Mapper-Objekt
     * @param w die Breite der Übersichtskarte
     * @param h die Höhe der Übersichtskarte
     * @param mapProvider der Kartenprovider der Übersichtskarte
     * @param color die Farbe für die Markierung auf der Übersichtskarte
     */
    public OverviewMap(Mapper mapper, int w, int h, AbstractMapProvider mapProvider, int color) {
        super(mapper.app, mapper.width-w-16, 16, w, h, mapProvider);
        this.mapper = mapper;
        this.w = w;
        this.h = h;
        this.color = color;
        this.transparentColor = mapper.app.color(mapper.app.hue(color), mapper.app.saturation(color), mapper.app.brightness(color), 20);
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
        this.mapper.app.fill(this.transparentColor);
        this.mapper.app.stroke(this.color);
        this.mapper.app.strokeWeight(3);
        this.mapper.app.rect(tl.x, tl.y, br.x-tl.x, br.y-tl.y);
    }

    /**
     * Überprüft ob Maus sich über dem Übersichtskartenobjekt befindet
     *
     * @param xM X-Position der Maus
     * @param yM Y-Position der Maus
     * @return Wahrheitswert ob Maus ueber Button ist
     */
    boolean mouseOver(int xM, int yM) {
        return xM > x && xM < x + w && yM > y && yM < y + h;
    }

    /**
     * Verschiebt die Hauptkarte zur gecklickten Position auf der Übersichtskarte
     *
     * @param x X-Position des Mausclicks
     * @param y Y-Position der Mausclicks
     */
    void panToHandler(float x, float y){
        this.mapper.map.panTo(this.getLocation(x, y));
    }
}