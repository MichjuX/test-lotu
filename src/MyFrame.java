import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MyFrame extends JFrame {
    MyPanel panel;
    HashMap<Class<? extends AirShip>, Integer> airshipTypes;

    MyFrame() throws Exception {
        this.airshipTypes = new HashMap<>();
        JFrame frame = new JFrame("Radar");
        panel = new MyPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setJMenuBar(createMenu(frame));

        showDialog(frame, "Wybierz co chcesz utworzyc, oraz ile: ", false);
    }
    private void showDialog(JFrame frame, String title, boolean isAdvanced) {
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JTextField planeField = new JTextField(5);
        JTextField balloonField = new JTextField(5);
        JTextField helicopterField = new JTextField(5);
        JTextField gliderField = new JTextField(5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        dialog.add(new JLabel("Samoloty: "), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        dialog.add(planeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        dialog.add(new JLabel("Helikoptery: "), constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        dialog.add(helicopterField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        dialog.add(new JLabel("Balony: "), constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        dialog.add(balloonField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        dialog.add(new JLabel("Szybowce: "), constraints);
        constraints.gridx = 3;
        constraints.gridy = 1;
        dialog.add(gliderField, constraints);
        if (!isAdvanced) {


            JToggleButton extendedOptions = new JToggleButton("Zaawansowane");
            extendedOptions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); // Zamyka obecne okno dialogowe
                    showDialog(frame, "Opcje zaawansowane", true); // Otwiera nowe okno dialogowe z opcjami zaawansowanymi
                }
            });
            constraints.gridx = 6;
            constraints.gridy = 1;
            constraints.gridwidth = 2;
            dialog.add(extendedOptions, constraints);
        }
        else{
            JComboBox<String> planeSelector = new JComboBox<>();
            JComboBox<String> helicopterSelector = new JComboBox<>();
            JComboBox<String> balloonSelector = new JComboBox<>();
            JComboBox<String> gliderSelector = new JComboBox<>();
            for (Class<? extends AirShip> airshipType : airshipTypes.keySet()) {
                String airshipTypeName = airshipType.getSimpleName();
                planeSelector.addItem(airshipTypeName);
                helicopterSelector.addItem(airshipTypeName);
                balloonSelector.addItem(airshipTypeName);
                gliderSelector.addItem(airshipTypeName);
            }
            constraints.gridx = 0;
            constraints.gridy = 2;
            dialog.add(planeSelector, constraints);
            constraints.gridx = 1;
            constraints.gridy = 2;
            dialog.add(helicopterSelector, constraints);
            constraints.gridx = 2;
            constraints.gridy = 2;
            dialog.add(balloonSelector, constraints);
            constraints.gridx = 3;
            constraints.gridy = 2;
            dialog.add(gliderSelector, constraints);
            JButton updateButton = new JButton("Aktualizuj");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    planeSelector.removeAllItems();
                    helicopterSelector.removeAllItems();
                    balloonSelector.removeAllItems();
                    gliderSelector.removeAllItems();
                    try {
                        airshipTypes.put(Plane.class, planeField.getText().isEmpty() ? 0 : Integer.parseInt(planeField.getText()));
                        airshipTypes.put(Helicopter.class, helicopterField.getText().isEmpty() ? 0 : Integer.parseInt(helicopterField.getText()));
                        airshipTypes.put(Balloon.class, balloonField.getText().isEmpty() ? 0 : Integer.parseInt(balloonField.getText()));
                        airshipTypes.put(Glider.class, gliderField.getText().isEmpty() ? 0 : Integer.parseInt(gliderField.getText()));
                        for (HashMap.Entry<Class<? extends AirShip>, Integer> entry : airshipTypes.entrySet()) {
                            for (int i = 0; i < entry.getValue(); i++) {
                                switch (entry.getKey().getSimpleName()) {
                                    case "Plane":
                                        planeSelector.addItem("Samolot " + (i + 1));
                                        break;
                                    case "Helicopter":
                                        helicopterSelector.addItem("Helikopter " + (i + 1));
                                        break;
                                    case "Balloon":
                                        balloonSelector.addItem("Balon " + (i + 1));
                                        break;
                                    case "Glider":
                                        gliderSelector.addItem("Szybowiec " + (i + 1));
                                        break;

                                }

                            }
                        }
                    }
                    catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

// umieść przycisk Aktualizuj w lewym dolnym rogu
            constraints.gridx = 0;  // zmień na odpowiednie wartości, jeśli to konieczne
            constraints.gridy = 6;  // zmień na odpowiednie wartości, jeśli to konieczne
            dialog.add(updateButton, constraints);
        }


        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        airshipTypes.put(Plane.class, planeField.getText().isEmpty() ? 0 : Integer.parseInt(planeField.getText()));
                        airshipTypes.put(Helicopter.class, helicopterField.getText().isEmpty() ? 0 : Integer.parseInt(helicopterField.getText()));
                        airshipTypes.put(Balloon.class, balloonField.getText().isEmpty() ? 0 : Integer.parseInt(balloonField.getText()));
                        airshipTypes.put(Glider.class, gliderField.getText().isEmpty() ? 0 : Integer.parseInt(gliderField.getText()));
                        panel.createAirShip(airshipTypes);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        dialog.add(okButton, constraints);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
//        JDialog dialog = new JDialog(frame, "Wybierz co chcesz utworzyc, oraz ile: ", true);
//        dialog.setLayout(new FlowLayout());
//        JTextField planeField = new JTextField(5);
//        JTextField balloonField = new JTextField(5);
//        JTextField helicopterField = new JTextField(5);
//        JTextField gliderField = new JTextField(5);
//        JToggleButton extendedOptions = new JToggleButton("Wlacz opcje zaawansowane");
//        extendedOptions.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dialog.dispose(); // Zamyka obecne okno dialogowe
//                frame.setVisible(false);
//                // Tworzymy nowe okno dialogowe
//                JDialog newDialog = new JDialog(frame, "Opcje zaawansowane", true);
//                newDialog.setLayout(new FlowLayout());
//                JButton okButton = new JButton("OK");
//                okButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        newDialog.dispose();
//                        frame.setVisible(true);
//                    }
//                });
//                newDialog.add(okButton);
//                newDialog.pack();
//                newDialog.setLocationRelativeTo(frame);
//                newDialog.setVisible(true); // Otwiera nowe okno dialogowe
//            }
//        });
//        dialog.add(extendedOptions);
//        dialog.add(new JLabel("Samoloty: "));
//        dialog.add(planeField);
//        dialog.add(new JLabel("Helikoptery: "));
//        dialog.add(helicopterField);
//        dialog.add(new JLabel("Balony: "));
//        dialog.add(balloonField);
//        dialog.add(new JLabel("Szybowce: "));
//        dialog.add(gliderField);
//        JButton okButton = new JButton("OK");
//        okButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                HashMap<Class<? extends AirShip>, Integer> airshipTypes = new HashMap<>();
//
//                try {
//                    airshipTypes.put(Plane.class, planeField.getText().isEmpty() ? 0 : Integer.parseInt(planeField.getText()));
//                    airshipTypes.put(Helicopter.class, helicopterField.getText().isEmpty() ? 0 : Integer.parseInt(helicopterField.getText()));
//                    airshipTypes.put(Balloon.class, balloonField.getText().isEmpty() ? 0 : Integer.parseInt(balloonField.getText()));
//                    airshipTypes.put(Glider.class, gliderField.getText().isEmpty() ? 0 : Integer.parseInt(gliderField.getText()));
//                    panel.createAirShip(airshipTypes);
//                    dialog.dispose();
//                } catch (NumberFormatException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
//                }
//
//            }
//        });
//        dialog.add(okButton);
//        dialog.pack();
//        dialog.setLocationRelativeTo(frame);
//        dialog.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setJMenuBar(createMenu(frame));
////        //Wyswietla okno
////        frame.pack();
////        frame.setVisible(true);
//
//    }

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
                        "--------------------------------------\n" +
                        "Zolte - Helikoptery\n"+"Niebieskie - Balony \n"+ "Zielone - Samoloty\n" + "Biale - Szybowce\n", "Legenda",
                JOptionPane.INFORMATION_MESSAGE));
        options.addActionListener((ActionEvent e) -> JOptionPane.showMessageDialog(frame, "Opcje:\n", "Opcje",
                JOptionPane.INFORMATION_MESSAGE));
        menuBar.setLayout((new FlowLayout(FlowLayout.LEFT)));
        menuBar.add(options);
        menuBar.add(legenda);
        return menuBar;
    }
}
