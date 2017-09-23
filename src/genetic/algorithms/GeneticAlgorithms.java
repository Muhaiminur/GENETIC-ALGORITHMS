/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.algorithms;

import java.util.Arrays;

/**
 *
 * @author muhaiminur
 */
public class GeneticAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        int random = (int) (Math.random() * 16);
        String v[] = new String[6];
        String b[] = new String[6];
        int array[] = new int[6];
        int ar[] = new int[6];
        int fin[] = new int[6];
        String arf[] = new String[6];
        int new_array_sorted[] = new int[6];
        String s[] = new String[6];
        for (int m = 0; m < 6; m++) {
            array[m] = ((int) (Math.random() * 16));
            s[m] = binary(array[m]);
            //System.err.println(array[m]);
        }
        for (int r = 0; r < 10; r++) {
            for (int m = 0; m < 6; m++) {
                s[m] = binary(array[m]);
            }
            new_array_sorted = fitness(array);
            //for(int n=0;n<6;n++){System.out.println(s[n]);System.out.println(new_array_sorted[n]);}
            //System.err.println(array[m]);
            v = sort_string_according_fitness(new_array_sorted, s);
            b = mutation(cross_over(v));// string[]
            ar = binary_to_integer_arr(b); //int[]
            ar = fitness(ar);
            arf = sort_string_according_fitness(ar, b);
            v[0] = arf[5];
            v[1] = arf[4];
            array = binary_to_integer_arr(v);
            fin = fitness(array);
            Arrays.sort(fin);
            //Arrays.sort(new_array_sorted);
            // new_array_sorted[0]=arf[4];
            // new_array_sorted[1]=arf[5];
            //Arrays.sort(new_array_sorted);
            System.out.println("Generation : " + r + "______________________");
            for (int q = 0; q < 6; q++) {
                System.out.println(fin[q]);
            }
            //sort_string_according_fitness();
            //System.out.println("Generation number : " + r);
            //for(int n=0;n<6;n++){System.out.println("Fitness of ppl : "+array[n] );}

        }

    }

    public static String binary(int x) {
        String s = Integer.toBinaryString(x);
        String binary = null;
        int need_to_add_zero = 4 - s.length();
        if (need_to_add_zero == 3) {
            binary = "000" + s + "";
        } else if (need_to_add_zero == 2) {
            binary = "00" + s + "";
        } else if (need_to_add_zero == 1) {
            binary = "0" + s + "";
        } else if (need_to_add_zero == 0) {
            binary = s;
        }
        //System.out.println(binary);
        return binary;
    }

    public static int binary_to_integer(String s) {
        int x = 0;
        if (s.charAt(0) == '1') {
            x = x + 8;
        }
        if (s.charAt(1) == '1') {
            x = x + 4;
        }
        if (s.charAt(2) == '1') {
            x = x + 2;
        }
        if (s.charAt(3) == '1') {
            x = x + 1;
        }
        //System.out.println(x);
        return x;
    }

    public static String[] sort_string(String s[]) {
        //int sorted_int []= new int[6];
        int arr[] = new int[6];
        String sorted_string[] = new String[6];
        for (int x = 0; x < 6; x++) {
            arr[x] = binary_to_integer(s[x]);
        }
        Arrays.sort(arr);
        //for(int m=0;m<6;m++){System.out.println(arr[m]);}
        for (int y = 0; y < 6; y++) {
            sorted_string[y] = binary(arr[y]);
        }
        //for(int n=0;n<6;n++){System.out.println(sorted_string[n]);}
        return sorted_string;
    }

    public static String[] mutation(String x[]) {
        String arr[] = new String[6];
        int random = (int) (Math.random() * 6);
        //System.out.println("mutation number "+random);
        //String i =null;
        for (int u = 0; u < random; u++) {
            int bit = ((int) (Math.random() * 4));
            int index = ((int) (Math.random() * 6));
            if (x[index].charAt(bit) == '1') {
                StringBuilder i = new StringBuilder(x[index]);
                i.setCharAt(bit, '0');
                String s = "" + i + "";
                x[index] = s;
            } else {
                StringBuilder i = new StringBuilder(x[index]);
                i.setCharAt(bit, '1');
                String s = "" + i + "";
                x[index] = s;
            }
        }
        for (int f = 0; f < 6; f++) {
            //  System.out.println("mutation" + x[f]);
        }
        return x;
    }

    public static int[] fitness(int bin_value[]) {
        int fitness_arr[] = new int[6];
        for (int x = 0; x < 6; x++) {
            fitness_arr[x] = 15 * bin_value[x] - (bin_value[x] * bin_value[x]);
        }
        //for(int n=0;n<6;n++){System.out.println("fitness :"+fitness_arr[n]);}
        return fitness_arr;
    }

    public static String[] sort_string_according_fitness(int fitness_arr[], String s[]) {
        Arrays.sort(fitness_arr);
        String string_according_fitness[] = new String[6];
        for (int j = 0; j < 6; j++) {
            for (int y = 0; y < 6; y++) {
                if (fitness_arr[j] == 15 * binary_to_integer(s[y]) - binary_to_integer(s[y]) * binary_to_integer(s[y])) {
                    string_according_fitness[j] = s[y];
                    break;
                }
            }
        }//for(int n=0;n<6;n++){System.out.println(sorted_string[n]);}
        //for(int w=0;w<6;w++){System.out.println(fitness_arr[w]);}
        //for(int t=0;t<6;t++){System.out.println(string_according_fitness[t]);}
        return string_according_fitness;
    }

    public static String[] cross_over(String s[]) {
        String crossed[] = new String[6];
        crossed[0] = s[5];
        crossed[2] = s[5];
        crossed[4] = s[5];
        crossed[1] = s[4];
        crossed[3] = s[3];
        crossed[5] = s[2];
        //for(int n=0;n<6;n++){System.out.println("crossed"+crossed[n] );}
        return crossed;
    }

    public static int[] binary_to_integer_arr(String s[]) {
        int bin_to_int[] = new int[6];
        for (int x = 0; x < 6; x++) {
            if (s[x].charAt(0) == '1') {
                bin_to_int[x] = bin_to_int[x] + 8;
            }
            if (s[x].charAt(1) == '1') {
                bin_to_int[x] = bin_to_int[x] + 4;
            }
            if (s[x].charAt(2) == '1') {
                bin_to_int[x] = bin_to_int[x] + 2;
            }
            if (s[x].charAt(3) == '1') {
                bin_to_int[x] = bin_to_int[x] + 1;
            }
        }
        //for(int n=0;n<6;n++){System.out.println("bin_to_int"+bin_to_int[n]);}
        return bin_to_int;
    }

}
