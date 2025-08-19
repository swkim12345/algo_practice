package base.java.basic;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class fileio {
    public static void main(String[] args) throws Exception {
        // 파일 입출력을 이용해 검증이 필요할 경우
        System.setIn(new java.io.FileInputStream("target file"));
        
        //출력의 경우 파일 output stream을 만들고, 이를 PrintStream으로 변환해주는 과정이 필요하다.
        FileOutputStream f = new java.io.FileOutputStream("output file");
        System.setOut(new PrintStream(f));

    }
}
