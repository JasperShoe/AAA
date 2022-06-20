package Client;

import javax.swing.*;

public class Main extends JFrame {
    public static final int WIDTH = 800, HEIGHT = 800;

    public Main(){
        super("Anna's Awesome Adventure");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Game game = new Game();

        add(game);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}