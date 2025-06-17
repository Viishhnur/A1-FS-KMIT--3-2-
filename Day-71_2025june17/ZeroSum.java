import java.util.*;
 public class ZeroSum{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n=sc.nextInt();sc.nextLine();
            int[] nums=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int res=solve(nums);
            System.out.println(res);
        }
    }

    static int solve(int[] nums){
        int sum=0, maxi=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum)){
                maxi=Math.max(maxi,i-map.get(sum));
            }else{
                map.put(sum,i);
            }
        }        
        return  maxi;
    }
 }