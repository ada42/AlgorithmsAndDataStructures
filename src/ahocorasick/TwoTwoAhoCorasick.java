package ahocorasick;


 
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
 
public class TwoTwoAhoCorasick
{
    static int POWER_SIZE;
    Node[]           nodes;
    int              nodeCount;
 
    public static class Node
    {
        int     parent;
        char    charFromParent;
        int     suffLink    = -1;
        int[]   children    = new int[POWER_SIZE];
        int[]   transitions = new int[POWER_SIZE];
        boolean leaf;
        {
            Arrays.fill(children, -1);
            Arrays.fill(transitions, -1);
        }
    }
 
    public TwoTwoAhoCorasick(int maxNodes)
    {
        init(); 
    	nodes = new Node[maxNodes];
        // create root
        nodes[0] = new Node();
        nodes[0].suffLink = 0;
        nodes[0].parent = -1;
        nodeCount = 1;
    }
    
    private void init() {

    	String[] powers = powersOfTwoFilter();
    	POWER_SIZE = powers.length;
	}

	protected String[] powersOfTwoFilter() {
        Set<String> filter = new HashSet<>();
        long pow = 1;
        filter.add(String.valueOf(pow));
    	for(int i=1;i<800;i++)
    	{
    	    pow*=2;
            filter.add(String.valueOf(pow));  
    	}
        return filter.toArray(new String[filter.size()]);
    	
    }
    
    public void addString(String s)
    {
        int cur = 0;
        for (char ch : s.toCharArray())
        {
            int c = ch - '1';
            if (nodes[cur].children[c] == -1)
            {
                nodes[nodeCount] = new Node();
                nodes[nodeCount].parent = cur;
                nodes[nodeCount].charFromParent = ch;
                nodes[cur].children[c] = nodeCount++;
            }
            cur = nodes[cur].children[c];
        }
        nodes[cur].leaf = true;
    }
 
    public int suffLink(int nodeIndex)
    {
        Node node = nodes[nodeIndex];
        if (node.suffLink == -1)
            node.suffLink = node.parent == 0 ? 0 : transition(
                    suffLink(node.parent), node.charFromParent);
        return node.suffLink;
    }
 
    public int transition(int nodeIndex, char ch)
    {
        int c = ch - '1';
        Node node = nodes[nodeIndex];
        if (node.transitions[c] == -1)
            node.transitions[c] = node.children[c] != -1 ? node.children[c]
                    : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
        return node.transitions[c];
    }
 
    // Usage example
    public static void main(String[] args)
    {
        TwoTwoAhoCorasick ahoCorasick = new TwoTwoAhoCorasick(
                1000);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the main string: ");
        String main = sc.nextLine().toLowerCase().trim();
        System.out.println("Enter the pattern string: ");
        String pattern = sc.nextLine().toLowerCase().trim();
        ahoCorasick.addString(pattern);
        int node = 0;
        for (char ch : main.toCharArray())
        {
            node = ahoCorasick.transition(node, ch);
        }
        if (ahoCorasick.nodes[node].leaf)
            System.out.println("A '" + pattern + "' string is substring of "
                    + main + " string.");
        else
            System.out.println("A '" + pattern
                    + "' string is not substring of " + main + " string.");
        sc.close();
    }
}