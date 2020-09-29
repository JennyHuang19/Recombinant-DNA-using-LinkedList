public class LinkStrand implements IDnaStrand {

    private class Node {
        String info;
        Node next;

        public Node(String s, Node n) {
            info = s;
            next = n;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;

    /**
     * Constructor with argument
     * @param d
     *            is a string representing a DNA strand.
     * @return the new LinkStrand object with a single node
     * representing the entire strand of DNA.
     */

    public LinkStrand(String d){
        initialize(d);
    }

    /**
     * Constructor default
     * @return the new LinkStrand object with a single node
     * representing the entire strand of DNA.
     */

    public LinkStrand(){
        this("");
    }

    /**
     * gets total character count of LinkStrand
     * @return size of the LinkStrand.
     */

    @Override
    public long size() {
        return mySize;
    }


    @Override
    public void initialize(String source) {
        myFirst = new Node(source,null);
        myLast = myFirst;
        mySize = source.length();
    }

    /**
     * Get instance of LinkStrand object
     * @param source is the source of this enzyme
     * @return LinkStrand object.
     */

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    /**
     * Creates one new node and updates
     * instance variables to maintain class invariants
     * @param dna
     *            is the String appended to this strand
     * @return mutated LinkStrand object.
     */

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna, null);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends += 1;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand reversed = new LinkStrand();
        Node current = myFirst;
        while (current != null){
            StringBuilder copy = new StringBuilder(current.info);
            copy.reverse();
            reversed.myFirst = new Node(copy.toString(), reversed.myFirst);
            reversed.mySize += copy.length();
            current = current.next;
        }
        return reversed;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    public String toString(){
        Node curr = myFirst;
        StringBuilder ret = new StringBuilder();
        while (curr != null){
            ret.append(curr.info);
            curr = curr.next;
        }
        return ret.toString();
    }
}
