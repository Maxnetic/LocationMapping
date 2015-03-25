package locationmapping;

public class ZoomButton extends Button {

    boolean plusSymbol = false;

    public ZoomButton(LocationMapper mapper, float x, float y, float w, float h, boolean plusSymbol) {
        super(mapper, x, y, w, h);
        this.plusSymbol = plusSymbol;
    }

    void draw() {
        super.draw();
        app.stroke(0);
        app.line(x+3,y+h/2,x+w-3,y+h/2);
        if (plusSymbol) {
            app.line(x+w/2,y+3,x+w/2,y+h-3);
        }
    }

}
