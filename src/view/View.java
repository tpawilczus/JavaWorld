package view;

import controller.WorldCells;
import controller.WorldController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import model.Position;

public class View extends JFrame {

    public final int WINDOW_HEIGHT = 460;
    public final int WINDOW_WIDTH = 690;

    private WorldController controller;
    private int sideMapLength;
    private JLabel lbOrganismStatus;

    public void setController(WorldController controller) {
        this.controller = controller;
    }

    public int getSideMapLength() {
        return sideMapLength;
    }

    public void setSideMapLength(int sideMapLength) {
        this.sideMapLength = sideMapLength;
    }

    public JLabel getLbOrganismStatus() {
        return lbOrganismStatus;
    }

    public void setLbOrganismStatus(JLabel lbOrganismStatus) {
        this.lbOrganismStatus = lbOrganismStatus;
    }

    public View() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Tomasz Pawilczus JavaWorld");
        this.sideMapLength = 15;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(50, 50));

        JTextArea txtActionLogger = new JTextArea("", 30, 25);
        txtActionLogger.setEditable(false);
        JScrollPane scrActionLogger = new JScrollPane(txtActionLogger);
        scrActionLogger.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton btNextTurn = new JButton("Next Turn");
        JLabel lbTurnNum = new JLabel("Turn number: 0");
        lbOrganismStatus = new JLabel("   Info: ");
        WordMapPanel wmPanel = new WordMapPanel();
        JScrollPane scrWordMapPanel = new JScrollPane(wmPanel);
        scrWordMapPanel.setPreferredSize(new Dimension(470, 400));
        scrWordMapPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrWordMapPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        btNextTurn.addActionListener((ActionEvent e) -> {
            controller.performNextTurn();
            lbTurnNum.setText("Turn number: " + controller.getTurnNumber());
            if (!controller.isActionLoggerEmpty()) {
                txtActionLogger.append("\n" + lbTurnNum.getText() + "\n"
                        + controller.getActionLoggerReport());
                txtActionLogger.setCaretPosition(txtActionLogger.getDocument().getLength());
                controller.clearActionLogger();
            }
            lbOrganismStatus.setText("   Info: ");
            scrWordMapPanel.repaint();
            wmPanel.setFocusable(true);
            wmPanel.requestFocusInWindow();
        });


        wmPanel.setFocusable(true);
        wmPanel.requestFocusInWindow();
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btNextTurn);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(lbTurnNum);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        lbOrganismStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomPanel.add(lbOrganismStatus);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(buttonPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(scrActionLogger, BorderLayout.EAST);
        mainPanel.add(scrWordMapPanel, BorderLayout.CENTER);

        Container mainContainer = getContentPane();
        mainContainer.add(mainPanel);
        setVisible(true);
    }

    class WordMapPanel extends JPanel {

        private int pref_size_w = WINDOW_WIDTH;
        private int pref_size_h = WINDOW_HEIGHT;

        private ArrayList<ArrayList<WorldCells>> worldCellMap;

        public WordMapPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mouseClicked(e);
                    for (ArrayList<WorldCells> wcList : worldCellMap) {
                        for (WorldCells wc : wcList) {
                            if(wc.getShape().contains(e.getPoint())){
                                getLbOrganismStatus().setText("   Info: "
                                        + controller
                                        .getModel()
                                        .getOrganismFromPosition(new Position(wc.getX(), wc.getY())));
                                repaint();
                            }}
                    }
                }
            });
        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(pref_size_w, pref_size_h);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            pref_size_w = sideMapLength * controller.getModel().getWorldX() + 5;
            pref_size_h = sideMapLength * controller.getModel().getWorldY() + 5;

            this.setPreferredSize(new Dimension(pref_size_w, pref_size_h));
            worldCellMap = controller.getWorldMap();
            Graphics2D g2 = (Graphics2D) g;
            for (int y = 0; y < controller.getModel().getWorldY(); y++) {
                for (int x = 0; x < controller.getModel().getWorldX(); x++) {
                    if (!this.worldCellMap.get(y).get(x).isIsEmpty()) {
                        g2.setColor(this.worldCellMap.get(y).get(x).getColor());
                        g2.fill(this.worldCellMap.get(y).get(x).getShape());
                    }
                    g2.setColor(Color.WHITE);
                    g2.draw(this.worldCellMap.get(y).get(x).getShape());
                }
            }
        }

    }
}
