package Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.lang.Integer;
import java.util.List;

public class Main {
    class CheckOddIntegers implements Property<Integer>{
        public boolean test(Integer t){
            return t.intValue() % 2 == 1;
        }
    }

    class CheckPrimeNumbers implements Property<Integer>{
        public boolean test(Integer t){
            return isPrime(t.intValue());
        }
    }

    class CheckPalindrome implements Property<Integer>{
        public boolean test(Integer t){
            return isPalindrome(t);
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(Integer n){
        String original, reverse = ""; // Objects of String class
        original = n.toString();
        int length = original.length();
        for (int i = length - 1; i >= 0; i--)
            reverse += original.charAt(i);
        if (original.equals(reverse))
            return true;
        else
            return false;
    }

    public static <T> void printList(List<T> goodList) {
        for (T e : goodList) {
            System.out.println(e.toString());
        }
    }

    public static<T> int numberOfElements(Collection<T> array, Property<T> p){
        int c = 0;
        ArrayList<T> goodList = new ArrayList<>();
        for (T e : array){
            if (p.test(e)){
                c++;
                goodList.add(e);
            }
        }
        printList(goodList);
        return c;
    }

    public static void main(String[] args){
        List<Integer> testList = Arrays.asList(12, 545, 151, 34543, 343, 171, 48984);
        Main testMain = new Main();
        System.out.println(numberOfElements(testList, testMain.new CheckOddIntegers()));
        System.out.println(numberOfElements(testList, testMain.new CheckPrimeNumbers()));
        System.out.println(numberOfElements(testList, testMain.new CheckPalindrome()));
    }

    public static <T> void exchangePositions(T[] array, T e1, T e2){
        //T temp;
        for (int i = 0; i < array.length; i++){
            if (e1.equals(array[i])){
                array[i] = e2;
            } else if (e2.equals(array[i])){
                array[i] = e1;
            }
        }
    }

    public static <T extends Object & Comparable<? super T>>
    T maxElement (List<? extends T> array, int begin, int end){
        T max = array.get(begin);
        for (++begin; begin< end; ++begin){
            if (max.compareTo(array.get(begin))<0){
                max = array.get(begin);
            }
        }
        return max;
    }
}