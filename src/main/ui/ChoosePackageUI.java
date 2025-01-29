// package main.ui;

// import javax.swing.*;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.awt.*;

// public class ChoosePackageUI {
//     private JFrame frame;
//     private String destination;
//     private String currentUsername;

//     private String selectedBudget = null;
//     private String selectedTravelType = null;

//     public ChoosePackageUI(JFrame existingFrame, String destination, String username) {
//         this.frame = existingFrame;
//         this.destination = destination;
//         this.currentUsername = username;
        
//         frame.getContentPane().removeAll(); // Clear the frame
//         initializeUI(); // Initialize the Choose Package UI
//         frame.revalidate();
//         frame.repaint();
//     }

//     private void initializeUI() {
//         JPanel mainPanel = new JPanel();
//         mainPanel.setLayout(null);
//         mainPanel.setBackground(new Color(230, 240, 250));
    
//         addBudgetCards(mainPanel);  // Add budget section
//         addTravelCards(mainPanel); // Add travel section
//         addButton(mainPanel);

//         frame.add(mainPanel);
//     }


//     private void addButton(JPanel parentPanel) {
//         JButton confirmButton = new JButton("Confirm Packages");
//         confirmButton.setBounds(300, 410, 200, 30);
//         confirmButton.setBackground(new Color(100, 200, 100));
//         confirmButton.setForeground(Color.WHITE);
//         parentPanel.add(confirmButton);
    
//         confirmButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Assuming selectedBudget and selectedTravelType are set
//                 if (selectedBudget != null && selectedTravelType != null) {
//                     // Pass the selected budget, travel type, destination, and username to the next UI
//                     new ChooseDateUI(frame, destination, currentUsername, selectedBudget, selectedTravelType);
//                 } else {
//                     JOptionPane.showMessageDialog(frame, "Please select both budget and travel type before proceeding.");
//                 }
//             }
//         });
//     }
    
//     private void addBudgetCards(JPanel parentPanel) {
//         JPanel budgetPanel = new JPanel();
//         budgetPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//         budgetPanel.setBounds(50, 70, 700, 150);
        
//         JLabel budgetLabel = new JLabel("Choose Your Budget:");
//         budgetLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         budgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
//         budgetLabel.setBounds(100, 40, 600, 30);
//         parentPanel.add(budgetLabel);
        
//         // Budget cards
//         JPanel cheapCard = createCard("Cheap", "Stay conscious of costs", "💴", "1");
//         JPanel moderateCard = createCard("Moderate", "Keep cost on the average side", "💰", "2");
//         JPanel luxuryCard = createCard("Luxury", "Don't worry about cost", "💸", "3 to 5");
        
//         budgetPanel.add(cheapCard);
//         budgetPanel.add(moderateCard);
//         budgetPanel.add(luxuryCard);
        
//         // Add click listeners to each card
//         cheapCard.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 selectedBudget = "Cheap";
//                 //JOptionPane.showMessageDialog(frame, "You chose: Cheap");
//                 // Save the selected budget here
//                 //savePackageIfComplete();
                
//             }
//         });
    
//         moderateCard.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 selectedBudget = "Moderate";
//                 //JOptionPane.showMessageDialog(frame, "You chose: Moderate");
//                 // Save the selected budget here
//                 //savePackageIfComplete();
//             }
//         });
    
//         luxuryCard.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(MouseEvent e) {
//                 selectedBudget = "Luxury";
//                 //JOptionPane.showMessageDialog(frame, "You chose: Luxury");
//                 // Save the selected budget here
//                 //savePackageIfComplete();
//             }
//         });
    
//         parentPanel.add(budgetPanel);
//     }
    


//     private void addTravelCards(JPanel parentPanel) {
//     JPanel travelPanel = new JPanel();
//     travelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//     travelPanel.setBounds(50, 250, 700, 150);
    
//     JLabel travelLabel = new JLabel("Choose Your Travel Type:");
//     travelLabel.setFont(new Font("Arial", Font.BOLD, 16));
//     travelLabel.setHorizontalAlignment(SwingConstants.CENTER);
//     travelLabel.setBounds(100, 220, 600, 30);
//     parentPanel.add(travelLabel);
    
//     // Travel cards
//     JPanel justMeCard = createCard("Just Me", "A sole traveler in exploration", "✈️", "1");
//     JPanel coupleCard = createCard("A Couple", "Two travelers in tandem", "👨🏻‍🤝‍👩🏻", "2");
//     JPanel familyCard = createCard("Family", "A group of fun-loving adventurers", "🏠", "3 to 5");
//     JPanel friendsCard = createCard("Friends", "A bunch of thrill-seekers", "🚢", "5 to 10");
    
//     travelPanel.add(justMeCard);
//     travelPanel.add(coupleCard);
//     travelPanel.add(familyCard);
//     travelPanel.add(friendsCard);
    
//     // Add click listeners to each card
//     justMeCard.addMouseListener(new MouseAdapter() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//             selectedTravelType = "Just Me";
//             //JOptionPane.showMessageDialog(frame, "You chose: Just Me");
//             // Save the selected travel type here
//             //savePackageIfComplete();
//         }
//     });

//     coupleCard.addMouseListener(new MouseAdapter() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//             selectedTravelType = "A Couple";
//             //JOptionPane.showMessageDialog(frame, "You chose: A Couple");
//             // Save the selected travel type here
//             //savePackageIfComplete();
//         }
//     });

//     familyCard.addMouseListener(new MouseAdapter() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//             selectedTravelType = "Family";
//             //JOptionPane.showMessageDialog(frame, "You chose: Family");
//             // Save the selected travel type here
//             //savePackageIfComplete();
//         }
//     });

//     friendsCard.addMouseListener(new MouseAdapter() {
//         @Override
//         public void mouseClicked(MouseEvent e) {
//             selectedTravelType = "Friends";
//             //JOptionPane.showMessageDialog(frame, "You chose: Friends");
//             // Save the selected travel type here
//             //savePackageIfComplete();
//         }
//     });

//     parentPanel.add(travelPanel);
// }


// private JPanel createCard(String title, String desc, String iconText, String peopleText) {
//     JPanel card = new JPanel();
//     card.setLayout(new BorderLayout());
//     card.setPreferredSize(new Dimension(150, 100));
//     card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//     card.setBackground(Color.WHITE);
    
//     // Icon Label
//     JLabel iconLabel = new JLabel(iconText);
//     iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
//     iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
//     // Title Label
//     JLabel titleLabel = new JLabel(title);
//     titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
//     titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
//     // Description Label
//     JLabel descLabel = new JLabel("<html>" + desc + "<br>People: " + peopleText + "</html>");
//     descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//     descLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
//     // Add components to card
//     card.add(iconLabel, BorderLayout.NORTH);
//     card.add(titleLabel, BorderLayout.CENTER);
//     card.add(descLabel, BorderLayout.SOUTH);
    
//     card.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand pointer
//     return card;
// }

    
    
// }

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoosePackageUI {
    private JFrame frame;
    private String destination;
    private String currentUsername;

    private String selectedBudget = null;
    private String selectedTravelType = null;

    public ChoosePackageUI(JFrame existingFrame, String destination, String username) {
        this.frame = existingFrame;
        this.destination = destination;
        this.currentUsername = username;

        frame.getContentPane().removeAll(); // Clear the frame
        initializeUI(); // Initialize the Choose Package UI
        frame.revalidate();
        frame.repaint();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(230, 240, 250));

        addBudgetCards(mainPanel);  // Add budget section
        addTravelCards(mainPanel); // Add travel section
        addButton(mainPanel);

        frame.add(mainPanel);
    }

    private void addButton(JPanel parentPanel) {
        JButton confirmButton = new JButton("Confirm Packages");
        confirmButton.setBounds(300, 420, 200, 40);
        confirmButton.setBackground(new Color(72, 180, 97));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createLineBorder(new Color(50, 130, 75), 2));
        parentPanel.add(confirmButton);

        confirmButton.addActionListener(e -> {
            if (selectedBudget != null && selectedTravelType != null) {
                new ChooseDateUI(frame, destination, currentUsername, selectedBudget, selectedTravelType);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select both budget and travel type before proceeding.");
            }
        });
    }

    private void addBudgetCards(JPanel parentPanel) {
        JPanel budgetPanel = new JPanel();
        budgetPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        budgetPanel.setBounds(50, 70, 700, 150);

        JLabel budgetLabel = new JLabel("Choose Your Budget:");
        budgetLabel.setFont(new Font("Arial", Font.BOLD, 18));
        budgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        budgetLabel.setBounds(100, 40, 600, 30);
        parentPanel.add(budgetLabel);

        // Budget cards
        JPanel cheapCard = createCard("Cheap", "Stay conscious of costs", "💴", "1", new Color(204, 229, 255));
        JPanel moderateCard = createCard("Moderate", "Keep cost on average side", "💰", "2", new Color(255, 229, 204));
        JPanel luxuryCard = createCard("Luxury", "Don't worry about cost", "💸", "3 to 5", new Color(255, 204, 229));

        budgetPanel.add(cheapCard);
        budgetPanel.add(moderateCard);
        budgetPanel.add(luxuryCard);

        parentPanel.add(budgetPanel);
    }

    private void addTravelCards(JPanel parentPanel) {
        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        travelPanel.setBounds(10, 250, 780, 150);

        JLabel travelLabel = new JLabel("Choose Your Travel Type:");
        travelLabel.setFont(new Font("Arial", Font.BOLD, 18));
        travelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        travelLabel.setBounds(100, 220, 600, 30);
        parentPanel.add(travelLabel);

        // Travel cards
        JPanel justMeCard = createCard("Just Me", "A sole traveler in exploration", "✈️", "1", new Color(204, 255, 204));
        JPanel coupleCard = createCard("A Couple", "Two travelers in tandem", "👨🏻‍🤝‍👩🏻", "2", new Color(255, 255, 204));
        JPanel familyCard = createCard("Family", "A group of adventurers", "🏠", "3 to 5", new Color(204, 255, 255));
        JPanel friendsCard = createCard("Friends", "A bunch of thrill-seekers", "🚢", "5 to 10", new Color(229, 204, 255));

        travelPanel.add(justMeCard);
        travelPanel.add(coupleCard);
        travelPanel.add(familyCard);
        travelPanel.add(friendsCard);

        parentPanel.add(travelPanel);
    }

    private JPanel createCard(String title, String desc, String iconText, String peopleText, Color backgroundColor) {
        // JPanel card = new JPanel();

        // card.setLayout(new BorderLayout());
        // card.setPreferredSize(new Dimension(160, 120));
        // card.setBackground(backgroundColor);
        // card.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));

        // // Icon Label
        // JLabel iconLabel = new JLabel(iconText);
        // iconLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        // iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // // Title Label
        // JLabel titleLabel = new JLabel(title);
        // titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        // titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // JPanel descPanel = new JPanel();
        // descPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // descPanel.setPreferredSize(new Dimension(160, 120));
        // descPanel.setBackground(backgroundColor);



        // // Description Label
        // JLabel descLabel = new JLabel("<html>" + desc + "<br>People: " + peopleText + "</html>");
        // descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        // //descLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // //descLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // descPanel.add(descLabel);
        // // Add components to card
        // //card.add(iconLabel, BorderLayout.NORTH);
        // card.add(titleLabel);
        // card.add(descPanel,BorderLayout.SOUTH);

        // card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS)); // Vertical alignment
        card.setPreferredSize(new Dimension(170, 120));
        card.setBackground(backgroundColor);
        card.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand pointer

        // Add padding and margin to the card
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Inner padding
        ));

        // Icon Label
        JLabel iconLabel = new JLabel(iconText);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center in the BoxLayout

        // Title Label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Description Panel
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        descPanel.setBackground(backgroundColor);

        // Description Label
        //JLabel descLabel = new JLabel("<html>" + desc + "<br>People: " + peopleText + "</html>");
        JLabel descLabel = new JLabel("<html>" + desc + "</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descPanel.add(descLabel);

        // Ensure description panel is centered
        descPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to card in vertical order
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(10)); // Spacer between icon and title
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10)); // Spacer between title and description
        card.add(descPanel);

        // Add mouse hover effect
        card.addMouseListener(new MouseAdapter() {
            // @Override
            // public void mouseEntered(MouseEvent e) {
            //     card.setBorder(BorderFactory.createLineBorder(new Color(50, 130, 200), 3));
            // }

            // @Override
            // public void mouseExited(MouseEvent e) {
            //     card.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 2));
            // }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (title.equals("Cheap") || title.equals("Moderate") || title.equals("Luxury")) {
                    selectedBudget = title;
                } else {
                    selectedTravelType = title;
                }
                card.setBorder(BorderFactory.createLineBorder(new Color(72, 200, 97), 6));
            }
        });

        return card;
    }
}
