package Algos;

public class BinarySearch {
   
    static String searchElement(int arr[], int target){
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;

        while(low <= high) {
            if(target == arr[mid]){
                return "Found";
            }
            else {
                if(arr[mid] < target) {
                    low = mid + 1;
                }
                else {
                    high = mid-1;
                }
            }

            mid = (low + high)/2;
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        System.out.println(searchElement(new int[]{1,2,2,4,5,6,7,8,8,9,90}, 10));
        System.out.println(searchElement(new int[]{1,2,2,4,5,6,7,8,8,9,10}, 10));
        System.out.println(searchElement(new int[]{1,2,2,4,5,6,7,8,8,9,90}, 1));
        System.out.println(searchElement(new int[]{1,2,2,4,5,6,7,8,8,9,90}, 0));
    }

}
