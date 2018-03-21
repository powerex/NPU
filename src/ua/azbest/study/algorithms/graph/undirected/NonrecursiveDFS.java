package ua.azbest.study.algorithms.graph.undirected;

import ua.azbest.study.aditionals.StdOut;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by AZbest on 15.11.2015.
 */
public class NonrecursiveDFS {

        private boolean[] marked;  // marked[v] = is there an s-v path?
        /**
         * Computes the vertices connected to the source vertex <tt>s</tt> in the graph <tt>G</tt>.
         * @param G the graph
         * @param s the source vertex
         */
        public NonrecursiveDFS(Graph G, int s) {
            marked = new boolean[G.V()];

            // to be able to iterate over each adjacency list, keeping track of which
            // vertex in each adjacency list needs to be explored next
            Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
            for (int v = 0; v < G.V(); v++)
                adj[v] = G.adj(v).iterator();

            // depth-first search using an explicit stack
            Stack<Integer> stack = new Stack<Integer>();
            marked[s] = true;
            stack.push(s);
            while (!stack.isEmpty()) {
                int v = stack.peek();
                if (adj[v].hasNext()) {
                    int w = adj[v].next();
                    // StdOut.printf("check %d\n", w);
                    if (!marked[w]) {
                        // discovered vertex w for the first time
                        marked[w] = true;
                        // edgeTo[w] = v;
                        stack.push(w);
                        // StdOut.printf("dfs(%d)\n", w);
                    }
                }
                else {
                    // StdOut.printf("%d done\n", v);
                    stack.pop();
                }
            }
        }

        /**
         * Is vertex <tt>v</tt> connected to the source vertex <tt>s</tt>?
         * @param v the vertex
         * @return <tt>true</tt> if vertex <tt>v</tt> is connected to the source vertex <tt>s</tt>,
         *    and <tt>false</tt> otherwise
         */
        public boolean marked(int v) {
            return marked[v];
        }

        /**
         * Unit tests the <tt>NonrecursiveDFS</tt> data type.
         */
        public static void main(String[] args) {
            In in = new In("Graph03.txt");
            Graph G = new Graph(in);
            int s = 0;
            NonrecursiveDFS dfs = new NonrecursiveDFS(G, s);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v))
                    StdOut.print(v + " ");
            StdOut.println();
        }

    }