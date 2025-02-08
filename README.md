# february8_2025
The problems that I solved today

1.Design a number container system that can do the following: Insert or Replace a number at the given index in the system. Return the smallest index for the given number in the system. Implement the NumberContainers class: NumberContainers() Initializes the number container system. void change(int index, int number) Fills the container at index with the number. If there is already a number at that index, replace it. int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled by number in the system.

Code:
class NumberContainers {

    Map<Integer,SortedSet<Integer>> m;
    Map<Integer,Integer> ind;
    public NumberContainers() {
        m=new HashMap<>();
        ind=new HashMap<>();
    }
    
    public void change(int index, int number) {

        if(!m.containsKey(number))
            m.put(number,new TreeSet<>());
        if(ind.containsKey(index))
        {
            if(ind.get(index)==number) return;
            int x=ind.get(index);
            m.get(x).remove(index);
            if(m.get(x).isEmpty())
                m.remove(x);
        }
        ind.put(index,number);
        m.get(number).add(index);
    }
    
    public int find(int number) {
        if(m.containsKey(number))
            return m.get(number).first();
        return -1;
    }
}

2.There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself. Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j. In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities). Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.

Code:
class Solution {
    ArrayList<int[]>[] adj;
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n=passingFees.length;
        adj=new ArrayList[n];
        int i;
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        for(int[] x:edges)
        {
            adj[x[0]].add(new int[]{x[1],x[2]});
            adj[x[1]].add(new int[]{x[0],x[2]});
        }
        int[] time=new int[n];
        Arrays.fill(time,maxTime+1);
        time[0]=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]==b[2]?a[0]-b[0]:a[2]-b[2]);
        pq.add(new int[]{0,0,passingFees[0]});
        while(!pq.isEmpty())
        {
            int[] x=pq.poll();
            int tym=x[0];
            int u=x[1];
            int cost=x[2];
            if(u==n-1)
                return cost;
            for(int[] a:adj[u])
            {
                int v=a[0];
                int t=tym+a[1];
                if(t<time[v]){
                time[v]=t;
                pq.add(new int[]{t,v,cost+passingFees[v]});
                }
            }
        }
        return -1;
    }
}
