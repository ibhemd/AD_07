import java.util.ArrayList;

public class SearchTree {

    public static class Node<T> {
        T key;
        Node left, right, parent;

        public Node (T newKey) {
            key = newKey;
            left = right = null;
        }
    }
    Node root;

    public SearchTree() {
        root = null;
    }

    public static <T extends Comparable<T>> void insert(SearchTree t, Node z) {
        Node y = t.root;
        while (y != null) {
            z.parent = y;
            if (((Comparable<T>) z.key).compareTo((T) y.key) < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }
        if (z.parent == null) {
            t.root = z;
        } else {
            if (((Comparable<T>) z.key).compareTo((T) z.parent.key) < 0) {
                z.parent.left = z;
            } else {
                z.parent.right = z;
            }
        }
    }

    public static void delete(SearchTree t, Node z) {
        Node x;
        Node y;
        if (z.left == null || z.right == null) {
            y = z;
        } else {
            y = TreeSuccessor(z);
        }
        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }
        if (x != null) {
            x.parent = y.parent;
        }
        if (y.parent == null) {
            t.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        if (y != z) {
            z.key = y.key;
        }
    }

    public static <T extends Comparable<T>> Node TreeSearch (Node x, T k) {
        if (x == null || x.key == k) {
            return x;
        }
        if (k.compareTo((T) x.key) < 0) {
            return TreeSearch(x.left,k);
        } else {
            return TreeSearch(x.right,k);
        }
    }

    public static Node TreeSuccessor (Node x) {
        if (x.right != null) {
            return TreeMinimum(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    public static Node TreeMinimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static Node TreeMaximum(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public static ArrayList toSortedArrayList(Node x) {
        ArrayList L = new ArrayList();
        Node n = TreeMinimum(x);
        Node m = TreeMaximum(x);
        L.add(n.key);
        while (n != m) {
            n = TreeSuccessor(n);
            L.add(n.key);
        }
        return L;
    }

    public static void main(String[] args) {
        SearchTree X = new SearchTree();

        Node six = new Node(6);
        Node eleven = new Node(11);
        Node fourteen = new Node(14);
        Node fifteen = new Node(15);
        Node eighteen = new Node(18);
        Node twentyone = new Node(21);
        Node twentyseven = new Node(27);
        Node thirtythree = new Node(33);
        Node fourtyfive = new Node(45);
        Node ninetynine = new Node(99);


        System.out.println("");
        System.out.println("Insert: '6','11','14','18','21','27','33,'45','99'");
        insert(X,six);
        insert(X,eleven);
        insert(X,fourteen);
        insert(X,eighteen);
        insert(X,twentyone);
        insert(X,twentyseven);
        insert(X,thirtythree);
        insert(X,fourtyfive);
        insert(X,ninetynine);
        System.out.println(toSortedArrayList(X.root));
        System.out.println("");

        System.out.println("Delete: '99'");
        delete(X,ninetynine);
        System.out.println(toSortedArrayList(X.root));
        System.out.println("");

        System.out.println("Minimum: " + TreeMinimum(X.root).key);
        System.out.println("Maximum: " + TreeMaximum(X.root).key);
        System.out.println("");

        System.out.println("Search for '15'");
        System.out.println(TreeSearch(X.root,15));
        System.out.println("");

        System.out.println("Insert: '15'");
        insert(X,fifteen);
        System.out.println(toSortedArrayList(X.root));
        System.out.println("");

        System.out.println("Search for '15'");
        System.out.println(TreeSearch(X.root,15));

        /*
        Insert: '6','11','14','18','21','27','33,'45','99'
        [6, 11, 14, 18, 21, 27, 33, 45, 99]

        Delete: '99'
        [6, 11, 14, 18, 21, 27, 33, 45]

        Minimum: 6
        Maximum: 45

        Search for '15'
        null

        Insert: '15'
        [6, 11, 14, 15, 18, 21, 27, 33, 45]

        Search for '15'
        SearchTree$Node@4c873330
         */

    }

}
