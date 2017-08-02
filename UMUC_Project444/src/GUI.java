/**
 * FileName: GUI.java
 * Author: Rebecca Johnson
 * Date: 8/2/2017
 * Purpose: Creates a student database to receive student
 *  id, name, major, and gpa and displays results.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class GUI extends JFrame {
	
	//new jPanel
	private JPanel panel;
	
	//holds the letter grades that can be chosen
	private static String[] LETTER_GRADES = 
		{ "A", "B", "C", "D", "F"};
	
	//holds the credit hours that can be chose
	private static Integer[] CREDIT_HOURS = {3, 6}; 

	//labels that are on JPanel
    private static JLabel idJLabel;
    private static JLabel nameJLabel;
    private static JLabel majorJLabel;
    private static JLabel selectionJLabel;
    
    //textFields that are on JPanel
    private static JTextField idTextField;
    private static JTextField nameTextField;
    private static JTextField majorTextField;
    
    //process request button
    private static JButton processRequestBtn;
    
    //selection combo box
    private static JComboBox<String> selectionComboBox;
    
    //hash map
    private static HashMap<String, Student> map = 
    		new HashMap<String, Student>();
  
    public GUI() {
        super("Project 4");
       
        //new jpanel object
        panel = new JPanel();
        
        //new label objects
        idJLabel = new JLabel();
        idJLabel.setText("ID:");
        
        //new name label object
        nameJLabel = new JLabel();
        nameJLabel.setText("Name:");
        
        //new major label object
        majorJLabel = new JLabel();
        majorJLabel.setText("Major:");
        
        //new selection label object
        selectionJLabel = new JLabel();
        selectionJLabel.setText("Choose Selection:");
        
        //new text field objects
        idTextField = new JTextField();
        nameTextField = new JTextField();
        majorTextField = new JTextField();
        
        //new selection combo box object
        selectionComboBox = new JComboBox<>();
        selectionComboBox.setModel(new DefaultComboBoxModel<>(
        		new String[] { "Insert", "Delete", "Find", "Update" }));
        
        //new process request button object
        processRequestBtn = new JButton();
        processRequestBtn.setText("Process Request");

        //exits the window when closed
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //builds gui
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idJLabel)
                    .addComponent(nameJLabel)
                    .addComponent(majorJLabel)
                    .addComponent(processRequestBtn)
                    .addComponent(selectionJLabel))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idTextField)
                    .addComponent(nameTextField)
                    .addComponent(majorTextField)
                    .addComponent(selectionComboBox, 0, 177, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idJLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(majorJLabel)
                    .addComponent(majorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectionJLabel)
                    .addComponent(selectionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(processRequestBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
	//displays the GUI
	private void display() {
		setVisible(true);
	}
	
	//main method
    public static void main(String args[]) {
    	
    	//creates a gui object and displays the frame
    	GUI g = new GUI();
		g.display();
		
		//handles the process request button
		processRequestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Object selection = selectionComboBox.getSelectedItem();
				
				//executes if insert is selected
				if(selection.equals("Insert"))
					insertSelected();
				
				//executes if delete is selected
				if(selection.equals("Delete"))
					deleteSelected();
				
				//executes if find is selected
				if(selection.equals("Find"))
					findSelected();
				
				//executes if updates is selected
				if(selection.equals("Update"))
					updateSelected();
			}
		});	
		
    }
    
    //handles the insert selection 
    private static void insertSelected() {
    	
    	//creates new variables for each input when insert is selected
    	String id = idTextField.getText();
    	String name = nameTextField.getText();
    	String major = majorTextField.getText();
    	
    	//checks if the hash map already contains the id
    	if(map.containsKey(id))
    		
    		//displays results
    		JOptionPane.showMessageDialog(null, "Error: ID already exists.");
    	
    	else {
    		
    		//creates new student object and sends over the name and major
    		Student student = new Student(name, major);
    		
    		//adds the student to the hash map
    		map.put(id, student);
    		
    		//displays results
    		JOptionPane.showMessageDialog(null, "Success! Student data inserted.");
    	}
    }
    
    //handles the delete selection
    private static void deleteSelected() {
    	
    	//creates new id variable to hold the entered value
    	String id = idTextField.getText();
    	
    	//retrieves the student's data based off the key that is entered and creates a new student object
    	Student student = map.get(id);
    	
    	//checks that student is found in database
    	if(student == null)
    		dataNotFound();
    	
    	//if student is found, it will proceed to deletion of student information
    	else if(map.containsKey(idTextField.getText())) {
    		
    		//removes the student from the hash map
    		map.remove(idTextField.getText());
    		
    		//displays results
    		JOptionPane.showMessageDialog(null, "Success! Item removed.");
    	}	
    }
    
    //handles the find selection
    private static void findSelected() {
    	
    	//creates new id variable to hold the entered value
    	String id = idTextField.getText();
    	
    	//retrieves the student's data based off the key that is entered and creates a new student object
    	Student student = map.get(id);
    	
    	//checks that student is found in database
    	if(student == null) 
    		dataNotFound();
    	
    	//checks that the students name and major match the id
    	else if (idMatchesNameMajor(student) == false) {
    		JOptionPane.showMessageDialog(null, "ID does not match name and major!");
    	}
    	
    	//if the student is found and the name and major match the id, then the students data will be displayed
    	else if(idMatchesNameMajor(student) && map.containsKey(id))
    		JOptionPane.showMessageDialog(null, student);
    }
    
    //handles the update selection
    private static void updateSelected() {
    	
    	//creates new id variable to hold the entered value
    	String id = idTextField.getText();
    	
    	//retrieves the student's data based off the key that is entered and creates a new student object
    	Student student = map.get(id);
    	
    	//checks that student is found in database
    	if(student == null)
    		dataNotFound();
    	
    	else {
    		
    		//creates a grade variable that is used to open and store the new grade
    		String grade = (String) JOptionPane.showInputDialog(null, "Choose Grade:",
    				"Grade", JOptionPane.QUESTION_MESSAGE, null, LETTER_GRADES, LETTER_GRADES[0]);
    		
    		//creates a credits variable that is used to open and store the new credit hours
    		Integer credits = (Integer) JOptionPane.showInputDialog(null, "Choose credits:",
    				"Credits", JOptionPane.QUESTION_MESSAGE, null, CREDIT_HOURS, CREDIT_HOURS[0]);
    	
    		//displays "cancelled" if user backs out of updating the students information
    		if(grade == null || credits == null) 
    			JOptionPane.showMessageDialog(null, "Cancelled.");
    		else {
    			Integer numberGrade;
    			
    			//converts the letter grade to a number
    			switch (grade) {
    			case "A":
    				numberGrade = 4;
    				break;
    			case "B":
    				numberGrade = 3;
    				break;
    			case "C":
    				numberGrade = 2;
    				break;
    			case "D":
    				numberGrade = 1;
    				break;
    			case "F":
    				numberGrade = 0;
    				break;
    			default:
    				numberGrade = 0;
    			}
    			
    			//calls courseCompleted method to send over values for gpa
    			student.courseCompleted(numberGrade, credits);
    			
    			//displays results
    			JOptionPane.showMessageDialog(null, "Success! Profile updated!");
    		}
    	}
    }
    
    //checks if the entered id matches the name and major
    private static boolean idMatchesNameMajor(Student student) {
    	
    	//creates new name and major variables based off user's input
    	String name = nameTextField.getText();
        String major = majorTextField.getText();
        
        //checks that the name and major match the values in the database
        if(student.getName().equalsIgnoreCase(name) && student.getMajor().equalsIgnoreCase(major)) 
        		return true;
        else
        	return false;
    }
    
    //displays the dataNotFound error message
    private static void dataNotFound() {
    	JOptionPane.showMessageDialog(null, "Error: Data not found in database.");
    }
}
