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