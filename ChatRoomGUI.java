//Importing libraries
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class ChatRoomGUI {               //ChatRoomGUI class created
    String      appName     = "One-Person Chat Room";
    /*Here appName string variable
   * is storing "One-Person Chat Room"
   * */
    ChatRoomGUI     CRGUI; //ChatRoomGUI
    JFrame      newFrame    = new JFrame(appName); //JFrame created
    /*New Frame created with name "One-Person Chat Room"
     * it will appear at top right corner
     * */
    JButton     sendMessage;   //JButton
    JTextField messageBox;   // JTextField
    JTextArea   chatBox;    //JTextArea
    JTextField usernameChooser;     //JTextField
    JFrame      preFrame;             // JFrame
    public static void main(String[] args) {              //Main method
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() { //run method
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //creating ChatRoomGUI object
                ChatRoomGUI CRGUI = new ChatRoomGUI();
                CRGUI.preDisplay(); //preDisplay() method call
            }
        });
    }
    public void preDisplay() {               //preDisplay() method
        newFrame.setVisible(false);         //newFrame is set as not visible
        preFrame = new JFrame(appName);        // create preFrame with appName
        usernameChooser = new JTextField(15);
        /* JTextField creates textfield to take user input
         * */
        JLabel chooseUsernameLabel = new JLabel("Set a User Name:");
        /* JLabel displays the label
         * chooseUsernameLabel is set as "Set a User Name:"
         * */
        JButton enterServer = new JButton("Enter the Chat Room");
       /* JButton creates a button that can be applied for ActionListner
       * enterServer is set as "Enter the Chat Room"
       * */
        enterServer.addActionListener(new enterServerButtonListener());
        /* adding ActionListoner to JButton */
      
        JPanel prePanel = new JPanel(new GridBagLayout()); // JPanel created
        GridBagConstraints preRight = new GridBagConstraints();
        //GridBagConstaints is used to display area in grid
        GridBagConstraints preLeft = new GridBagConstraints();
      
        prePanel.add(chooseUsernameLabel, preLeft);
       // adding JLabel to panel with preLeft
        prePanel.add(usernameChooser, preRight);
       // adding JTextField to panel with preRight
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        // adding JButton to panel with BorderLayout and it in center
      
        preFrame.setSize(300, 300); //set panel size
        preFrame.setVisible(true); // set panel visible
    }
   public void display() {          // display() method
        JPanel mainPanel = new JPanel();   //mainPanel created
        mainPanel.setLayout(new BorderLayout()); // with BorderLayout
        JPanel southPanel = new JPanel(); // southPanel created
        southPanel.setLayout(new GridBagLayout()); //with GridBagLayout
  
        messageBox = new JTextField(30);
       /* JTextField is used for user input
       * messageBox JTextField is created with size 30*/
        messageBox.requestFocusInWindow();
        // set focus on messageBox
        sendMessage = new JButton("Send Message");
        /* JButton creates a button that can be applied for ActionListner
         * sendMessage is displayed on JButton
         * */
        sendMessage.addActionListener(new sendMessageButtonListener());
        /* adding ActionListener to JButton */

        chatBox = new JTextArea();      // create chatBox with JTextArea
        chatBox.setEditable(false);     // setting setEdittable as false
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15)); //setting font
        chatBox.setLineWrap(true);        //setLineWrap
        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        //add JScrollPane to chatBox
        GridBagConstraints left = new GridBagConstraints();
        //GridBagConstaints is used to display area in grid
        GridBagConstraints right = new GridBagConstraints();
      
        southPanel.add(messageBox, left);
       //adding JTextField to southPanel with left
        southPanel.add(sendMessage, right);
       //adding JButton to southPanel with right
      
        mainPanel.add(BorderLayout.SOUTH, southPanel); // adding southPanel to mainPanel
        newFrame.add(mainPanel);    // adding mainPanel to newFrame
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close operation
        newFrame.setSize(470, 300);    //setting size of frame
        newFrame.setVisible(true);     //display newFrame
    }
    class sendMessageButtonListener implements ActionListener {
       //sendMessageButtonListener class implenting ActionListener
        public void actionPerformed(ActionEvent event) { // actionPerformed() method
            if (messageBox.getText().length() < 1) {
                // do nothing
            }
            /*"Cleared all messages is displayed when user enters .clear"
             * When user clicks button send message
             * actionPerformed method will called
             * */
            else if (messageBox.getText().equals(".clear")) { // check for ".clear"
                chatBox.setText("Cleared all messages\n"); //setText in chatBox
                messageBox.setText("");            //empty the messageBox
            }
            /* messageBox has ".clear" then display
             * "Cleared all messages" */
           
           
            else {
           /* When user enters something in messageBox
           * then in chatBox will displayed as
           * <userName>"message"
           * and empty message box
           * */
                chatBox.append("<" + username + ">: " + messageBox.getText()
                        + "\n");         // set chatBox text with user input
                messageBox.setText("");     //empty messageBox text
            }
            messageBox.requestFocusInWindow();
            //set focus on chatBox and wait for user input

        }

    }

    String username; // username string variable
    class enterServerButtonListener implements ActionListener {
       //enterServerButtonListener class implenting ActionListener
        public void actionPerformed(ActionEvent event) { //actionPerformed() method
            username = usernameChooser.getText(); //getText from usernameChooser
            if (username.length() < 1) {     // if nothing entered
                System.out.println("No!");     // display no to user
            } else {
                preFrame.setVisible(false);         // do not display preFrame
                display();       // display() method call
            }
        }
    }
}