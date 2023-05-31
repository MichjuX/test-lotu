import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {
    MyPanel panel;

    MyFrame() throws Exception {
        JFrame frame = new JFrame("Radar");

        panel = new MyPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setJMenuBar(createMenu(frame));
//        //Wyswietla okno
//        frame.pack();
//        frame.setVisible(true);

    }

    private JMenuBar createMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem legenda = new JMenuItem("Legenda");
        JMenuItem options = new JMenu("Opcje");
        JMenuItem menuItem1 = new JMenuItem("Modyfikacja statkow");
        JMenuItem menuItem2 = new JMenuItem("nicnaraziex");
        options.add(menuItem1);
        options.add(menuItem2);
        legenda.addActionListener((ActionEvent e) -> JOptionPane.showMessageDialog(frame, "Leganda:\n" +
                        "* Złote 10-cio katy - drzewa\n" +
                        "* Turkusowe protokaty - budynki\n", "Legenda",
                JOptionPane.INFORMATION_MESSAGE));
        options.addActionListener((ActionEvent e) -> JOptionPane.showMessageDialog(frame, "Opcje:\n", "Opcje",
                JOptionPane.INFORMATION_MESSAGE));
        menuBar.setLayout((new FlowLayout(FlowLayout.LEFT)));
        menuBar.add(options);
        menuBar.add(legenda);
        return menuBar;
    }
//        JMenuBar menuBar = new JMenuBar();
//
//        JMenu optionsMenu = new JMenu("Opcje");
//        JMenuItem menuItem1 = new JMenuItem("Opcja 1");
//        JMenuItem menuItem2 = new JMenuItem("Opcja 2");
//        optionsMenu.add(menuItem1);
//        optionsMenu.add(menuItem2);
//
//        JMenuItem legenda = new JMenuItem("Legenda");
//
//        legenda.addActionListener((ActionEvent e) -> JOptionPane.showMessageDialog(frame,
//                "Leganda:\n" +
//                        "* Złote 10-cio katy - drzewa\n" +
//                        "* Turkusowe protokaty - budynki\n", "Legenda",
//                JOptionPane.INFORMATION_MESSAGE));
//
//        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//        menuBar.add(optionsMenu);
//        menuBar.add(legenda);
//
//        return menuBar;
//    }
}
