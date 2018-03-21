package ua.azbest.study.algorithms.graph.undirected;

import ua.azbest.study.aditionals.StdOut;
import ua.azbest.study.algorithms.sorting.addons.Queue;

/**
 * Created by AZbest on 15.11.2015.
 */
public class CC {

    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph <tt>G</tt>.
     *
     * @param G the undirected graph
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // depth-first search
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Returns the component id of the connected component containing vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex <tt>v</tt>
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex <tt>v</tt>
     */
    public int size(int v) {
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph <tt>G</tt>.
     *
     * @return the number of connected components in the graph <tt>G</tt>
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices <tt>v</tt> and <tt>w</tt> are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return <tt>true</tt> if vertices <tt>v</tt> and <tt>w</tt> are in the same
     *         connected component; <tt>false</tt> otherwise
     */
    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices <tt>v</tt> and <tt>w</tt> are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return <tt>true</tt> if vertices <tt>v</tt> and <tt>w</tt> are in the same
     *         connected component; <tt>false</tt> otherwise
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    public boolean areConnected(int v, int w) {
        return id(v) == id(w);
    }

    /**
     * Unit tests the <tt>CC</tt> data type.
     */
    public static void main(String[] args) {
        In in = new In("Graph03.txt");
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int M = cc.count();
        StdOut.println(M + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }

}
