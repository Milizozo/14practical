public class openHash {
    private keyValue table[];
    private int m;
    private int size;

    public openHash(int m){
    this.m=m;
    this.table = new keyValue[m+1];
    this.size=0;
    }
    public int hash(String key){
        int h= Math.abs(key.HashCode())
        return((h%m) +1);
    }
        public Boolean isFull(){
            return size ==m;
        }

        public Boolean isEmpty(){
            return size ==0;
        }
        public Boolean isInTable(String key){
            return lookup(key)!=null;
        }
        public void insert(String key,String value){
            if(isFull()) return;

            int i=hash(key);
            
            while(table[i]!= null){
                if(table[i].key.equals(key)){
                    table[i].value=value;
                }
                i=(i%m)+1;
            }
            table[i] = new keyValue(key,value);
            size++;
        
    }
    public String remove(String key){
        int i = hash(key);
        while(table[i]!=null){
            if(table[i].key.equals(key)){
                String val =table[i].value;
                table[i]=null;
                size--;
                return val;
            }
            i=(i%m)+1;
        }
        return null;
    }
}
