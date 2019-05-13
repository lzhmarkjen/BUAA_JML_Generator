import java.io.FileNotFoundException;

public class Main {
    private static final int querySize = 500;

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Generator generator = new Generator(querySize);
        generator.go();
    }
}
