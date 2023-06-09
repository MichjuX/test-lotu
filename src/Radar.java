import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Radar extends JFrame {
    private MyPanel panel;
    private boolean isRandomUsed = false;
    private HashMap<Class<? extends AirShip>, Integer> airshipTypes;
    public Radar() throws Exception {
        this.airshipTypes = new HashMap<>();
        JFrame frame = new JFrame("Radar");
        panel = new MyPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setJMenuBar(createMenu(frame));
        frame.setBackground(Color.BLACK);

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
        constraints.gridy = 2;
        dialog.add(new JLabel("Samoloty: "), constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        dialog.add(planeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        dialog.add(new JLabel("Helikoptery: "), constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        dialog.add(helicopterField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        dialog.add(new JLabel("Balony: "), constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        dialog.add(balloonField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 2;
        dialog.add(new JLabel("Szybowce: "), constraints);
        constraints.gridx = 3;
        constraints.gridy = 3;
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
            constraints.gridy = 3;
            constraints.gridwidth = 2;
            dialog.add(extendedOptions, constraints);
        } else {
            JComboBox<String> planeSelector = new JComboBox<>();
            JComboBox<String> helicopterSelector = new JComboBox<>();
            JComboBox<String> balloonSelector = new JComboBox<>();
            JComboBox<String> gliderSelector = new JComboBox<>();
            for (HashMap.Entry<Class<? extends AirShip>, Integer> entry : airshipTypes.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    switch (entry.getKey().getSimpleName()) {
                        case "Plane" -> planeSelector.addItem("Samolot " + (i + 1));
                        case "Helicopter" -> helicopterSelector.addItem("Helikopter " + (i + 1));
                        case "Balloon" -> balloonSelector.addItem("Balon " + (i + 1));
                        case "Glider" -> gliderSelector.addItem("Szybowiec " + (i + 1));
                    }
                }
            }
            constraints.gridx = 0;
            constraints.gridy = 4;
            dialog.add(planeSelector, constraints);
            constraints.gridx = 1;
            constraints.gridy = 4;
            dialog.add(helicopterSelector, constraints);
            constraints.gridx = 2;
            constraints.gridy = 4;
            dialog.add(balloonSelector, constraints);
            constraints.gridx = 3;
            constraints.gridy = 4;
            dialog.add(gliderSelector, constraints);
            JButton addPlane = new JButton("Dodaj");
            addPlane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentPlaneCount = airshipTypes.getOrDefault(Plane.class, 0);
                    planeSelector.addItem("Samolot " + (planeSelector.getItemCount() + 1));
                    airshipTypes.put(Plane.class, currentPlaneCount + 1);
                }
            });
            constraints.gridx = 0;
            constraints.gridy = 0;
            dialog.add(addPlane, constraints);

            JButton deletePlane = new JButton("Usun");
            deletePlane.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentPlaneCount = airshipTypes.getOrDefault(Plane.class, 0);
                    if (currentPlaneCount > 0 && planeSelector.getItemCount() > 0) {
                        planeSelector.removeItemAt((planeSelector.getItemCount() - 1));
                        airshipTypes.put(Plane.class, currentPlaneCount - 1);
                    }

                }
            });
            constraints.gridx = 0;
            constraints.gridy = 1;
            dialog.add(deletePlane, constraints);

            JButton changeRoute = new JButton("Zmień trase");
            constraints.gridx = 1;
            constraints.gridy = 6;
            changeRoute.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Jaki typ samolotu edytować?\n1. Samolot\n2. Helikopter\n3. Balon\n4. Szybowiec");
                    Scanner scanner = new Scanner(System.in);
                    int type = scanner.nextInt();
                    System.out.println("Chcesz zmienić automatycznie czy ręcznie?\n1. Automatycznie\n2. Ręcznie");
                    int automanual = scanner.nextInt();
                    List<AirShip> airships= panel.getAirships();
                    List<Plane> planes = new ArrayList<>();
                    List<Helicopter> helicopters = new ArrayList<>();
                    List<Balloon> balloons = new ArrayList<>();
                    List<Glider> gliders = new ArrayList<>();
                    for(AirShip airShip : airships){
                        if(airShip instanceof Plane){
                            planes.add((Plane) airShip);
                        }
                        if(airShip instanceof Helicopter){
                            helicopters.add((Helicopter) airShip);
                        }
                        if(airShip instanceof Balloon){
                            balloons.add((Balloon) airShip);
                        }
                        if(airShip instanceof Glider){
                            gliders.add((Glider) airShip);
                        }
                    }
                    int id = 0;
                    switch (type) {
                        case 1 -> {
                            if (!planes.isEmpty()) {
                                System.out.println("Największe możliwe ID samolotu: " + (planes.size() - 1));
                                System.out.println("Podaj id statku:");
                                id = scanner.nextInt();
                                if (id >= planes.size()) {
                                    System.out.println("Nie ma samolotu o takim ID!\n");
                                } else {
                                    switch (automanual) {
                                        case 1 -> planes.get(id).changeRouteToRandom();
                                        case 2 -> planes.get(id).changeRouteByHand();
                                    }
                                }
                            }
                            else { System.out.println("Brak samolotów");}
                        }
                        case 2 -> {
                            if (!helicopters.isEmpty()) {
                                System.out.println("Największe możliwe ID helikoptera: " + (helicopters.size() - 1));
                                System.out.println("Podaj id statku:");
                                id = scanner.nextInt();
                                if (id >= helicopters.size()) {
                                    System.out.println("Nie ma helikoptera o takim ID!\n");
                                } else {
                                    switch (automanual) {
                                        case 1 -> helicopters.get(id).changeRouteToRandom();
                                        case 2 -> helicopters.get(id).changeRouteByHand();
                                    }
                                }
                            } else { System.out.println("Brak helikopterów");}
                        }
                        case 3 -> {
                            if (!balloons.isEmpty()) {
                                System.out.println("Największe możliwe ID balonu: " + (balloons.size() - 1));
                                System.out.println("Podaj id statku:");
                                id = scanner.nextInt();
                                if (id >= balloons.size()) {
                                    System.out.println("Nie ma balonu o takim ID!\n");
                                } else {
                                    switch (automanual) {
                                        case 1 -> balloons.get(id).changeRouteToRandom();
                                        case 2 -> balloons.get(id).changeRouteByHand();
                                    }
                                }
                            } else { System.out.println("Brak balonow");}
                        }
                        case 4 -> {
                            if (!gliders.isEmpty()) {
                                System.out.println("Największe możliwe ID szybowca: " + (gliders.size() - 1));
                                System.out.println("Podaj id statku:");
                                id = scanner.nextInt();
                                if (id >= gliders.size()) {
                                    System.out.println("Nie ma samolotu o takim ID!\n");
                                } else {
                                    switch (automanual) {
                                        case 1 -> gliders.get(id).changeRouteToRandom();
                                        case 2 -> gliders.get(id).changeRouteByHand();
                                    }
                                }
                            }else { System.out.println("Brak szybowcow");}
                        }
                    }
                }
            });
            dialog.add(changeRoute, constraints);

            JButton addHelicopter = new JButton("Dodaj");
            addHelicopter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentHelicopterCount = airshipTypes.getOrDefault(Helicopter.class, 0);
                    helicopterSelector.addItem("Helikopter " + (helicopterSelector.getItemCount() + 1));
                    airshipTypes.put(Helicopter.class, currentHelicopterCount + 1);
                }
            });

            constraints.gridx = 1;
            constraints.gridy = 0;
            dialog.add(addHelicopter, constraints);


            JButton deleteHelicopter = new JButton("Usun");
            deleteHelicopter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the current number of Planes in the airshipTypes HashMap
                    int currentHelicopterCount = airshipTypes.getOrDefault(Helicopter.class, 0);
                    if (currentHelicopterCount > 0 && helicopterSelector.getItemCount() > 0) {
                        helicopterSelector.removeItemAt((helicopterSelector.getItemCount() - 1));
                        airshipTypes.put(Helicopter.class, currentHelicopterCount - 1);
                    }

                }
            });
            constraints.gridx = 1;
            constraints.gridy = 1;
            dialog.add(deleteHelicopter, constraints);


            JButton addBalloon = new JButton("Dodaj");
            addBalloon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentBalloonCount = airshipTypes.getOrDefault(Balloon.class, 0);
                    balloonSelector.addItem("Balon " + (balloonSelector.getItemCount() + 1));
                    airshipTypes.put(Balloon.class, currentBalloonCount + 1);
                }
            });
            constraints.gridx = 2;
            constraints.gridy = 0;
            dialog.add(addBalloon, constraints);

            JButton deleteBalloon = new JButton("Usun");
            deleteBalloon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentBalloonCount = airshipTypes.getOrDefault(Balloon.class, 0);
                    if (currentBalloonCount > 0 && balloonSelector.getItemCount() > 0) {
                        balloonSelector.removeItemAt((balloonSelector.getItemCount() - 1));
                        airshipTypes.put(Balloon.class, currentBalloonCount - 1);
                    }

                }
            });
            constraints.gridx = 2;
            constraints.gridy = 1;
            dialog.add(deleteBalloon, constraints);

            JButton addGlider = new JButton("Dodaj");
            addGlider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentGliderCount = airshipTypes.getOrDefault(Glider.class, 0);
                    gliderSelector.addItem("Szybowiec " + (gliderSelector.getItemCount() + 1));
                    airshipTypes.put(Glider.class, currentGliderCount + 1);
                }
            });
            constraints.gridx = 3;
            constraints.gridy = 0;
            dialog.add(addGlider, constraints);

            JButton deleteGlider = new JButton("Usun");
            deleteGlider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentGliderCount = airshipTypes.getOrDefault(Glider.class, 0);
                    if (currentGliderCount > 0 && gliderSelector.getItemCount() > 0) {
                        gliderSelector.removeItemAt((gliderSelector.getItemCount() - 1));
                        airshipTypes.put(Glider.class, currentGliderCount - 1);
                    }
                }
            });
            constraints.gridx = 3;
            constraints.gridy = 1;
            dialog.add(deleteGlider, constraints);
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
                                    case "Plane" -> planeSelector.addItem("Samolot " + (i + 1));
                                    case "Helicopter" -> helicopterSelector.addItem("Helikopter " + (i + 1));
                                    case "Balloon" -> balloonSelector.addItem("Balon " + (i + 1));
                                    case "Glider" -> gliderSelector.addItem("Szybowiec " + (i + 1));
                                }
                            }
                        }
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            constraints.gridx = 0;
            constraints.gridy = 6;
            dialog.add(updateButton, constraints);
        }
        JButton okButton = new JButton("OK");
        JButton randomButton = new JButton("Losuj");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (isRandomUsed) {
                        airshipTypes.clear();
                    }
                    if (airshipTypes.isEmpty()) {
                        airshipTypes.put(Plane.class, planeField.getText().isEmpty() ? 0 : Integer.parseInt(planeField.getText()));
                        airshipTypes.put(Helicopter.class, helicopterField.getText().isEmpty() ? 0 : Integer.parseInt(helicopterField.getText()));
                        airshipTypes.put(Balloon.class, balloonField.getText().isEmpty() ? 0 : Integer.parseInt(balloonField.getText()));
                        airshipTypes.put(Glider.class, gliderField.getText().isEmpty() ? 0 : Integer.parseInt(gliderField.getText()));
                    }
                    panel.createAirShip(airshipTypes);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    dialog.dispose();
                    isRandomUsed = false;
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        Random random = new Random();
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    airshipTypes.put(Plane.class, random.nextInt(3));
                    airshipTypes.put(Helicopter.class, random.nextInt(3));
                    airshipTypes.put(Balloon.class, random.nextInt(3));
                    airshipTypes.put(Glider.class, random.nextInt(3));
                    panel.createAirShip(airshipTypes);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    dialog.dispose();
                    isRandomUsed = true;
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Wprowadzono nieprawidłowe dane. Proszę wprowadzić tylko liczby całkowite.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        constraints.gridx = 3;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        dialog.add(okButton, constraints);
        constraints.gridx = 6;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        dialog.add(randomButton, constraints);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private JMenuBar createMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem legenda = new JMenuItem("Legenda");
        JMenuItem options = new JMenu("Opcje");
        JMenuItem menuItem1 = new JMenuItem("Modyfikacja statkow");
        JButton startStop = panel.createStartStop();
        JButton clear = panel.createClearButton();
        options.add(menuItem1);
        legenda.addActionListener((ActionEvent e) ->
                JOptionPane.showMessageDialog(frame, "Leganda:\n" +
                                "* Złote kwadraty - drzewa\n" +
                                "* Turkusowe protokaty - budynki\n" +
                                "--------------------------------------\n" +
                                "Zolte - Helikoptery\n" + "Niebieskie - Balony \n" + "Zielone - Samoloty\n" + "Biale - Szybowce\n", "Legenda",
                        JOptionPane.INFORMATION_MESSAGE)
        );
        options.addActionListener((ActionEvent e) ->
                JOptionPane.showMessageDialog(frame, "Opcje:\n", "Opcje", JOptionPane.INFORMATION_MESSAGE)
        );
        menuItem1.addActionListener(e -> showDialog(frame, "Aktualizacja statków", false));
        menuBar.setLayout((new FlowLayout(FlowLayout.LEFT)));
        menuBar.add(legenda);
        menuBar.add(options);
        menuBar.add(startStop);
        menuBar.add(clear);
        return menuBar;
    }
}
