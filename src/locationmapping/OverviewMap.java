package locationmapping;

import processing.core.PApplet;
import processing.event.MouseEvent;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;


public class OverviewMap extends UnfoldingMap {
    Mapper mapper;
    int x;
    int y = 16;
    int w;
    int h;
    int color;
    int transparentColor;

    public OverviewMap(Mapper mapper, int w, int h, AbstractMapProvider mapProvider, int color) {
        super(mapper.app, mapper.width-w-16, 16, w, h, mapProvider);
        this.mapper = mapper;
        this.w = w;
        this.h = h;
        this.color = color;
        this.transparentColor = mapper.app.color(mapper.app.hue(color), mapper.app.saturation(color), mapper.app.brightness(color), 20);
    }

    public void draw(){
        // X position der Karte updaten, wenn noeting
        this.x = this.mapper.app.width - this.w - 16;

        // Ort und Zoom der Uebersichtskarte updaten
        this.zoomTo(this.mapper.map.getZoomFromScale(this.mapper.map.getZoom())-5f);
        this.panTo(this.mapper.map.getCenter());

        // Zeichne Uebersichtskarte in obere rechte Ecke
        this.move(this.x, this.y);
        super.draw();

        // Zeichne Rahmen um Karte
        this.mapper.app.noFill();
        this.mapper.app.strokeWeight(1.5f);
        this.mapper.app.stroke(this.mapper.textColor);
        this.mapper.app.rect(this.x, this.y, this.w, this.h);

        // Zeichne Box fuer momentanigen Bereich der aeusseren Karte
        ScreenPosition tl = this.getScreenPosition(this.mapper.map.getTopLeftBorder());
        ScreenPosition br = this.getScreenPosition(this.mapper.map.getBottomRightBorder());
        this.mapper.app.fill(this.transparentColor);
        this.mapper.app.stroke(this.color);
        this.mapper.app.strokeWeight(3);
        this.mapper.app.rect(tl.x, tl.y, br.x-tl.x, br.y-tl.y);
    }

    boolean mouseOver(int xM, int yM) {
        return xM > x && xM < x + w && yM > y && yM < y + h;
    }

    void panToHandler(float x, float y){
        this.mapper.map.panTo(this.getLocation(x, y));
    }
}