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
