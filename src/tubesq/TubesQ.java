/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;

/**
 *
 * @author Zero
 */
public class TubesQ {
    public static void main(String[] args) {
        List sub = Arrays.asList("aku","kamu","dia","mereka","kami");
        List pred = Arrays.asList("makan","minum","berjalan","menari","bekerja");
        List oby = Arrays.asList("ayam","obat","meja","kursi","buku");
        List ktr = Arrays.asList("dikantor","pagihari","diruangtamu","sorehari","kemarin");
        int score[];
        String word;
        System.out.print("Input Kalimat (pk spasi): ");
        Scanner input = new Scanner(System.in);
        word = input.nextLine();
        ArrayList<String> ListKata=new ArrayList<>();
        int countinput = 0;
        int stringlength = 0;
        StringTokenizer st = new StringTokenizer(word);
        while(st.hasMoreTokens()){
            for(int i=0;i<st.countTokens();i++){
                ListKata.add(st.nextToken());
                stringlength++;
            }
        }
        String[] kt = new String[ListKata.size()]; 
        kt = ListKata.toArray(kt); 
        
        score = new int[stringlength];
        int inp = 0;
        boolean error = false;
        int group = 0;
        String token[] = {"| S | ","| P | ","| O | ","| K | ","| err | "};
        
        while((error == false)&&(countinput<kt.length)) {
            if ((ktr.contains(kt[countinput]))&&(countinput<kt.length)) {
                countinput++;
                score[inp] = 3;
                inp++;
            } else if (sub.contains(kt[countinput])&&(countinput<kt.length)) {
                countinput++;
                score[inp] = 0;
                inp++;
                
            } else if (pred.contains(kt[countinput])&&(countinput<kt.length)) {
                score[inp] = 1;
                countinput++;
                inp++;
                
            }  else if (oby.contains(kt[countinput])&&(countinput<kt.length)) {
                score[inp] = 2;
                countinput++;
                inp++;
                
            } else {
                    error = true;
                    score[inp] = 4;
                    //countinput++;
                    //inp++;
            }
        }
        System.out.print("Output Token Lexic : ");
        for (int i = 0; i < score.length; i++) {
            System.out.print(token[score[i]]+" ");
        }
        
        int index = 0;
        if (error == false) {
            while (error == false && index < score.length) {
                if (score[index] == 0){
                    if (index != 0) {
                        if (score[index+1] != 1) {
                            error = true;
                        }
                    }
                    
                    /*
                    Start => (sList)P
                    P => (pList)O|(pList)
                    O => (oList)K|(oList)|(kList)
                    K => (kList)
                    (sList) => ("aku","kamu","dia","mereka","kami")
                    (pList) => ("makan","minum","berjalan","menari","bekerja")
                    (oList) => ("ayam","obat","meja","kursi","buku")
                    (kList) => ("dikantor","pagihari","diruangtamu","sorehari","kemarin")
                    */
                    
                } else if (score[index] == 1) {
                    if (index != 0) {
                        if (score[index-1] != 0) {
                            error = true;
                        }
                    } 
                } else if (score[index] == 2) {
                    if (index != 0) {
                        if (score[index-1] != 1) {
                            error = true;
                        }
                    }
                } else if (score[index] == 3) {
                    if (index != 0) {
                        if (score[index-1] != 2) {
                            if (score[index-1] != 1) {
                                error = true;
                            }
                        }
                    }
                }
                //System.out.println(index);
                index++;
            }
        }
        if (index == 0) {
            error = true;
        }
        System.out.println("");
        if (error) {
            System.out.println("Keabsahan: "+"~Tidak Valid~");
            System.out.println("HIYA HIYA");
        } else {
            System.out.println("Keabsahan: "+"~Valid~");
            System.out.println("GOLONGAN KAMI");
            
        }
        System.out.println(score.length);
        System.out.println(index);
    } 
    
}
