import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MyFrame extends JFrame {
    MyPanel panel;
    MyFrame() throws Exception {
        JFrame frame = new JFrame("Radar");
        panel = new MyPanel();
        JDialog dialog = new JDialog(frame, "Wybierz co chcesz utworzyc, oraz ile: ", true);
        dialog.setLayout(new FlowLayout());
        JTextField planeField = new JTextField(5);
        JTextField balloonField = new JTextField(5);
        JTextField helicopterField = new JTextField(5);
        JTextField gliderField = new JTextField(5);
        dialog.add(new JLabel("Samoloty: "));
        dialog.add(planeField);
        dialog.add(new JLabel("Helikoptery: "));
        dialog.add(helicopterField);
        dialog.add(new JLabel("Balony: "));
        dialog.add(balloonField);
        dialog.add(new JLabel("Szybowce: "));
        dialog.add(gliderField);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Class<? extends AirShip>, Integer> airshipTypes = new HashMap<>();

                try {
                    airshipTypes.put(Plane.class, planeField.getText().isEmpty() ? 0 : Integer.parseInt(planeField.getText()));
                    airshipTypes.put(Helicopter.class, helicopterField.getText().isEmpty() ? 0 : Integer.parseInt(helicopterField.getText()));
                    airshipTypes.put(Balloon.class, balloonField.getText().isEmpty() ? 0 : Integer.parseInt(balloonField.getText()));
                    airshipTypes.put(Glider.class, gliderField.getText().isEmpty() ? 0 : Integer.parseInt(gliderField.getText()));

                    panel.createAirShip(airshipTypes);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        dialog.add(okButton);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
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
                        "* Turkusowe protokaty - budynki\n" +
                        "-------------------------------\n" +
                        "", "Legenda",
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
