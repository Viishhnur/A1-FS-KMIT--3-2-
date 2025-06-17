import java.util.*;

public class SmallestPositiveMiss {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n=sc.nextInt();sc.nextLine();
            int[] nums=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int res=solve(nums);
            System.out.println(res);
        }
    }
    static int solve(int[] nums){
        int n=nums.length;
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        for(int i=1;i<=n;i++){
            if(!s.contains(i)) return i;
        }
        return n+1;
    }
}

