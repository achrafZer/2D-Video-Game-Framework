package view;

public class CanvasTest {

    /*@Test
    public void testAddDrawable() {
        Canvas canvas = new Canvas();
        assert(canvas.getDrawables().size() == 0);

        IDrawable rectangle = new Rectangle(10, 20, 50, 70, Color.BLUE);
        canvas.addDrawable(rectangle);
        assert(canvas.getDrawables().size() == 1);
        assert(canvas.getDrawables().get(0).equals(rectangle));

        IDrawable circle = new Circle(50, 60, 90, 90, Color.BLACK);
        canvas.addDrawable(circle);
        assert(canvas.getDrawables().size() == 2);
        assert(canvas.getDrawables().get(1).equals(circle));

        IDrawable triangle = new Triangle(new int[]{15, 50, 30}, new int[]{20, 45, 26}, 3,  Color.ORANGE);
        canvas.addDrawable(triangle);
        assert(canvas.getDrawables().size() == 3);
        assert(canvas.getDrawables().get(2).equals(triangle));
    }

    @Test
    public void testRemoveDrawable() {
        Canvas canvas = new Canvas();
        assert(canvas.getDrawables().size() == 0);

        IDrawable circle = new Circle(50, 60, 90, 90, Color.BLACK);
        canvas.addDrawable(circle);
        IDrawable triangle = new Triangle(new int[]{15, 50, 30}, new int[]{20, 45, 26}, 3,  Color.ORANGE);
        canvas.addDrawable(triangle);

        canvas.removeDrawable(circle);
        assert(canvas.getDrawables().size() == 1);
        assert(canvas.getDrawables().get(0).equals(triangle));
        assert(!canvas.getDrawables().get(0).equals(circle));
    }*/
}