import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Primus extends JFrame {
    public static ImageIcon img = new ImageIcon("map-reduced2.jpg");
    public static int[][] mst;
    public static int[][] nexus = new int[81][81];

    public Primus() {
        initUI();
    }

    private void initUI() {
        try {
            getXL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prima();
        this.setTitle("Primus Turca");
        this.setSize(412,366);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new BackLabel());
        this.pack();
    }


    private void getXL() throws IOException {
        File f = new File("edges.xlsx");
        FileInputStream fist = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(fist);
        Sheet sheet = workbook.getSheet("Sheet1");
        for(Row row : sheet) {
            for(Cell cell : row) {
                nexus[row.getRowNum()][cell.getColumnIndex()] = (int)cell.getNumericCellValue();
                //System.out.println((int)cell.getNumericCellValue());
            }
        }

    }


    public void prima() {
        int[][] edge = nexus;
        mst = new int[edge.length-1][2];
        int[] visited = new int[edge.length];
        int min = Integer.MAX_VALUE;
        int u = 0;
        int v = 0;
        int total = 0;

        for(int i = 0;i< edge.length;i++) {
            for(int j = 0; j< edge.length;j++) {
                if( edge[i][j] == 0 && i != j)
                    edge[i][j] = Integer.MAX_VALUE; //mim
            }
        }

        visited[0]=1;
        for( int c = 0; c < edge.length-1; c++) {
            min = Integer.MAX_VALUE;
            for(int i = 0; i< edge.length; i++) {
                if( visited[i] ==1 ) {
                    for(int j =0; j < edge.length;j++) {
                        if(visited[j]!=1) {
                            if( min > edge[i][j]) {
                                min=edge[i][j];
                                u = i;
                                v = j;
                            }
                        }
                    }
                }
            }
            visited[v] = 1;
            total+=min;
            System.out.println( u + "->" + v + " weight: " + min );
            mst[c][0] = u; mst[c][1] = v;
        }

        System.out.println( "total weight is " + total );

    }

    public static void main( String[] args ) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Primus p = new Primus();
                p.setVisible(true);
            }
        });
    }


}
