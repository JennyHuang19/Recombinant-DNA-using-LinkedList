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
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;

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

    /**
     * Initialize this strand so that it represents the value of source. No
     * error checking is performed.
     *
     * @param source
     *            is the source of this enzyme
     * @return a single node.
     */
    @Override
    public void initialize(String source) {
        myFirst = new Node(source,null);
        myLast = myFirst;
        mySize = source.length();
        myIndex = 0; //lastCall.charAt();
        myLocalIndex = 0; //lastCall.dex;
        myCurrent = myFirst; //lastCall.list;
        myAppends = 0;
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

    /**
     * Creates a new LinkStrand object that is the reverse
     * of the object on which it's called.
     * @return new reversed LinkStrand object.
     */

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

    /**
     * Returns the number of appends
     * @return number of appends.
     */

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    /**
     * Finds a character at a specific index in a linked list of strings
     * @param index
     *            an int, the index to find.
     * @return the character at the specified index.
     */

    @Override
    public char charAt(int index) {
        if (this.size() <= myIndex || 0 > index) {
            throw new IndexOutOfBoundsException();
        }
        if(index < myIndex){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while (myIndex < index){        // when equal, loop will break
            myIndex++;
            myLocalIndex++;
            if(myLocalIndex >= myCurrent.info.length() && myCurrent.next != null){
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
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
