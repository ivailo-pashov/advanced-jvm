import java.util.ArrayList;
import java.util.List;

public class Flyweight {

    private static final boolean USE_FLYWEIGHT = true;
    private static final int GARBAGE_SIZE = 50000;
    private static final String[] SHAPES = { "Bottle", "Can", "Bag" };
    private static final List<Garbage> GARBAGE_BIN = new ArrayList<>(GARBAGE_SIZE);

    public static void main(String[] args) {
        System.gc();
        Runtime runtime = Runtime.getRuntime();

        for (int i=0; i < GARBAGE_SIZE; i++) {
            Garbage garbage = USE_FLYWEIGHT ? GarbageFactory.getPlasticGarbage((getPredictableShape())) : new PlasticGarbage((getPredictableShape()));
            garbage.generate();
            GARBAGE_BIN.add(garbage);
        }

        final long memoryUsed = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;

        System.out.println();
        System.out.println((USE_FLYWEIGHT ? "" : "Non-") + "Flyweight memory used: " + memoryUsed + " mb");
    }

    private static int shapeCount;

    private static String getPredictableShape() {
        return SHAPES[(int)shapeCount++ % SHAPES.length];
    }

    private static String getRandomShape() {
        return SHAPES[(int)(Math.random() * SHAPES.length)];
    }
}
