import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.control.*;
import static java.lang.Math.*;

public class Main extends Application {

    int i;
    ArrayList<Edge> edges = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        Scanner in = new Scanner(System.in);
        System.out.print("Number of nodes: ");
        int size = in.nextInt();
        System.out.print("Enter the graph edges: ");
        in.nextLine();
        String line = in.nextLine();
        System.out.println("line: " + line);
        char colors[] = new char[size];
        boolean graph[][] = new boolean[size][size];
        int color_numbers = 0;
        String[] edges_str = line.split(" ");
        for (int i = 0; i < edges_str.length / 2; i++) {
            int nodeA = Integer.parseInt(edges_str[2 * i]);
            int nodeB = Integer.parseInt(edges_str[2 * i + 1]);
            Edge edge = new Edge(nodeA, nodeB);
            edges.add(edge);
        }
        System.out.println(Arrays.toString(edges.toArray()));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean adjacent = false;
                for (Edge edge : edges) {
                    if (edge.nodeA == i && edge.nodeB == j || edge.nodeB == i && edge.nodeA == j) {
                        adjacent = true;
                    }
                }
                if (adjacent) {
                    graph[i][j] = true;
                } else {
                    graph[i][j] = false;
                }
                graph[j][i] = graph[i][j];
            }
        }
        System.out.println("Adjacency Matrix: ");
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print(graph[i][j] ? "1 " : "0 ");
            }
            System.out.println("|");
        }

        char label_color = 'A';
        for (int i = 0; i < size; i++) {
            if (colors[i] == 0) {
                colors[i] = label_color;
                color_numbers++;
                label_color = (char) ((int) label_color + 1);
            }
            for (int j = 0; j < size; j++) {
                if (colors[j] == 0 && !graph[i][j]) {
                    boolean adj = false;
                    for (int k = 0; k < size; k++) {
                        if (colors[k] == colors[i]) {
                            if (graph[k][j]) {
                                adj = true;
                                break;
                            }
                        }
                    }
                    if (!adj) {
                        colors[j] = colors[i];
                    }
                }
            }
        }
        System.out.println("Colors: " + Arrays.toString(colors));
        Color[] fills = new Color[color_numbers];
        Circle nodes[] = new Circle[size];
        for (i = 0; i < color_numbers; i++) {
            fills[i] = new Color(random(), random(), random(), 1);
        }
        for (i = 0; i < size; i++) {
            nodes[i] = new Circle();
            nodes[i].setCenterX(400 + 200 * Math.cos(2 * PI / size * i));
            nodes[i].setCenterY(300 + 200 * Math.sin(2 * PI / size * i));
            nodes[i].setRadius(20);
            nodes[i].setFill(fills[(int) (colors[i]) - 65]);
            nodes[i].setStroke(Color.BLACK);
            Label t = new Label("" + i);
            t.layoutXProperty().bind(nodes[i].centerXProperty().add(-5));
            t.layoutYProperty().bind(nodes[i].centerYProperty().add(-5));
            root.getChildren().addAll(nodes[i], t);
            nodes[i].setOnMouseDragged(new EventHandler<MouseEvent>() {
                Circle c = nodes[i];

                @Override
                public void handle(MouseEvent event) {
                    c.setCenterX(event.getX());
                    c.setCenterY(event.getY());
                }
            });
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (graph[i][j]) {
                    Line l = new Line();
                    l.startXProperty().bind(nodes[i].centerXProperty());
                    l.startYProperty().bind(nodes[i].centerYProperty());
                    l.endXProperty().bind(nodes[j].centerXProperty());
                    l.endYProperty().bind(nodes[j].centerYProperty());
                    root.getChildren().add(0, l);
                }
            }
        }
        stage.show();
    }
}

class Edge {

    public int nodeA;
    public int nodeB;

    public Edge(int nodeA, int nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public String toString() {
        return "edge < " + nodeA + ", " + nodeB + " >";
        // it's modified to return "edge < x, y > : color z"			
    }
}