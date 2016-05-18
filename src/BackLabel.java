import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class BackLabel extends JLabel {

    private int x, y, r = 8;
    //private String coor = "int[][] vex = {\n";
    //File f = new File("coor.txt");



    //region int[][] vex...
    int[][] vex = {
            {616,387},
            {800,341},
            {324,282},
            {1081,216},
            {630,140},
            {441,207},
            {315,401},
            {997,113},
            {147,353},
            {150,197},
            {287,188},
            {942,244},
            {1030,290},
            {399,127},
            {271,380},
            {219,191},
            {88,172},
            {500,140},
            {567,150},
            {233,366},
            {928,308},
            {67,111},
            {837,287},
            {837,208},
            {987,175},
            {351,219},
            {743,404},
            {801,145},
            {854,160},
            {1117,368},
            {672,437},
            {344,334},
            {513,433},
            {184,86},
            {117,299},
            {1076,166},
            {500,60},
            {627,293},
            {126,72},
            {537,246},
            {285,113},
            {430,311},
            {254,227},
            {776,301},
            {164,267},
            {708,341},
            {923,378},
            {182,386},
            {994,265},
            {565,265},
            {569,323},
            {724,121},
            {948,109},
            {310,138},
            {644,85},
            {1014,334},
            {579,56},
            {732,201},
            {115,114},
            {669,164},
            {875,119},
            {854,250},
            {841,376},
            {239,298},
            {1119,314},
            {607,198},
            {394,93},
            {520,292},
            {907,160},
            {485,383},
            {507,206},
            {979,352},
            {1034,372},
            {430,73},
            {1068,95},
            {1127,191},
            {230,138},
            {442,94},
            {718,420},
            {670,390},
            {352,121} };
    //endregion
    public int[][] edge = Primus.nexus;



    public BackLabel() {
        setIcon(Primus.img);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent mouseEvent) {
//                super.mousePressed(mouseEvent);
//                nodify(mouseEvent.getX()-(r/2),mouseEvent.getY()-(r/2));
//                coor += "\t{" + mouseEvent.getX() + "," + mouseEvent.getY() + "},\n";
//                System.out.println(coor);
//
//                try {
//                    byte data[] = coor.getBytes();
//
//                    if (!f.exists())
//                        f.createNewFile();
//
//                    FileOutputStream fouts = new FileOutputStream("coor.txt");
//                    fouts.write(data);
//                    fouts.close();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }



    public void nodify( int x, int y ) {
        this.x = x;
        this.y = y;
        repaint(x,y,r,r);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);

        for(int i = 0; i < vex.length; i++ ) {
            g.fillOval(vex[i][0]-(r/2), vex[i][1]-(r/2), r, r);
        }

        for(int j = 0; j < edge.length; j++) {
            for(int k = 0; k < edge.length; k++) {
                if(edge[j][k] != Integer.MAX_VALUE) { //mim
                    g.drawLine(vex[j][0]+(r/2),vex[j][1]+(r/2),vex[k][0]+(r/2),vex[k][1]+(r/2));
                }
            }
        }

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(4));
        for(int l = 0; l < edge.length-1; l++) {
            g2.drawLine( vex[ Primus.mst[l][0] ][ 0 ] + (r/2),
                         vex[ Primus.mst[l][0] ][ 1 ] + (r/2),
                         vex[ Primus.mst[l][1] ][ 0 ] + (r/2),
                         vex[ Primus.mst[l][1] ][ 1 ] + (r/2) );
        }

    }




}