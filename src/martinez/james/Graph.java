package martinez.james;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by james on 4/19/16.
 */
public class Graph {
    private int numVertices = 0;
    private List<List<Vertex>> adjacencyList;
    List<Vertex> vertices;

    public Graph(){
        vertices = new LinkedList<>();
    }

    public void insertVertex(Vertex v){
        v.index = numVertices;
        adjacencyList.add(numVertices, new LinkedList<>());
        vertices.add(v);
        numVertices++;
    }

    public void insertEdge(Vertex u, Vertex v){
        if(adjacencyList.size() < u.index ||
                adjacencyList.size() < v.index){
            //can't add an edge between vertices that aren't in the graph
            return;
        }
        adjacencyList.get(u.index).add(v);
    }

}
