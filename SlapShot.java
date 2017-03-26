import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.lang.Math;

public class Slapshot extends Frame implements ActionLister,
MouseListener, MouseMotionListener, Runnable{
  Menu menu1;
  MenuBar menubar;
  MenuItem menuitem0, menuitem1, menuitem2, menuitem3;
  Image memoryImage;
  Image backGroundImage;
  Image[] gifImages = new Image[2];
  Graphics memoryGraphics;
  Thread thread;
  MediaTracker tracker;
  Vector<Puck> pucks = new Vector<Puck>();
  int yourScore = 0;
  int theirScore = 0;
  int offsetX = 0;
  int offsetY = 0;
  int speed = 50;
  int maxVelocity = 10;
  Label label1, label2;
  int retVal = 0;
  boolean dragging = false;
  boolean stop = true;
  boolean runOK = true;
  OKCancelDialog textDialog;

  Slapshot()
  {
    menubar = new MenuBar();

    menu1 = new Menu("File");

    menuitem0 = new MenuItem("Start");
    menu1.add(menuitem0);
    menuitem0.addActionListener(this);

    menuitem1 = new MenuItem("End");
    menu1.add(menuitem1);
    menuitem1.addActionListener(this);

    menuitem2 = new MenuItem("Set speed...");
    menu1.add(menuitem2);
    menuitem2.addActionListener(this);

    menuitem3 = new MenuItem("Exit");
    menu1.add(menuitem3);
    menuitem3.addActionListener(this);

    menubar.add(menu1);
    SetMenuBar(menubar);

    addMouseListener(this);
    addMouseMotionListener(this);

    textDialog = new OKCancelDialog(this, "Set speed (1-100)", true);

    setLayout(null);

    label1 = new Label();
    label1.setText("0");
    label1.setBounds(180, 310, 20, 20);
    label1.setVisible(false);
    add(label1);

    label2 = new Label();
    label2.setText("0");
    label2.setBounds(400, 310, 20, 20);
    label2.setVisible(false);
    add(label2);

    tracker = new MediaTraker(this);
    backGroundImage = Toolkit.getDefaultToolkit().getImage("rink.gif");
    tracker.addImage(backGroundImage, 0);

    gifImages[0] = Toolkit.getDefaultToolkit().
    getImage("puck.gif");
    tracker.addImage(gifImages[0], 0);

    gifImages[1] = Toolkit.getDefaultToolkit().
    getImage("blocker.gif");
    tracker.addImage(gifImages[1], 0);

    setTitle("Slapshot!");

    setResizable(false);

    setSize(backGroundImage.getWidth(this),
    backGroundImage.getHeight(this));

    setVisible(true);

    memoryImage = createImage(getSize().width, getSize().height);
    memoryGraphics = memoryImage.getGraphics();

    thread = new Thread(this);
    thread.start();

    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(
      WindowEvent e){
        runOK = false;
        System.exit(0);
      }
    }
    );

  }

}
