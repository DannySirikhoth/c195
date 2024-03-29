WGU C195 - Class Project for Software II: Advanced Java Concepts
Written in Java/JavaFX for a pre-existing MySQL database. Sub

Purpose
This application is a demonstration desktop client for managing customers and for scheduling appointments between customers and contacts at a company. It is a Java client of a fixed-schema MySQL database written in accordance with the requirements of the Software II C195 task.

Dependencies
IntelliJ Community 2020.03
Java SE 11.0.10
JavaFX-SDK-11.0.2
mysql-connector-java-8.0.23
Running the Program
TBD: Make a package including all but JavaFX. Until then...

The easiest way to run the program is as follows:

Open Intellij CE
Select "IntelliJ IDEA" on the menu bar.
Select "Preferences..."
Select "Path Variables" on the left.
Set variable PATH_TO_FX to the directory of your JavaFX library (e.g. /Library/javafx-sdk-11.0.2/lib).
Select "Run" and then "Run 'Main'" on the menu bar.
To run the program from the command line using the supplied .class files:*

Within a shell, cd to the project directory.
Within the project directory, cd to out/production/SchedulerClient
export PATH_TO_FX=/Library/javafx-sdk-11.0.2/lib
export PATH_TO_MYSQL_DRIVER=/Library/mysql-connector-java-8.0.23
java --module-path 
{PATH_TO_MYSQL_DRIVER} --add-modules javafx.fxml,javafx.controls,javafx.graphics Main
* This should work on Mac and Linux/UNIX (bash shell). I don't know about Windows.

Using the Program
The application opens with a login form asking for user credentials. Input the credentials for any user in the database. The valid credentials are username 'test' and password 'test' or username 'admin' and password 'admin'.

Upon successful login, the main window opens. It provides tabs for appointments, customers, and reports. It also provides a user menu at the top right, with options for logging out (getting a new login form) or exiting the program.

The appointments tab initially lists all appointments in the database at once. The radio buttons above the table allow you to select from this view, a monthly view, and a weekly view. The monthly and weekly views show one month or week at a time. In these two views, the left and right arrow buttons at the top-right of the table become active. Use them for paging by month or week. You may need to page through empty screens of months or weeks not having appointments. Also, if you're in one of these views and you delete the only appointment in that view, rather than surprise you by jumping to another page, I require that you navigate to existing appointments by these buttons, or else switch to viewing all appointments (after which you can safely go back to monthly/weekly again).

The appointments tab also has buttons for adding, modifying, and deleting appointments. The form for adding or modifying an appointment includes combo boxes for the contact name, the customer name, and the start and end times. The form supports the possibility that multiple contacts or multiple customers have the same name by including the contact or customer's unique database ID in parentheses after the name. The start and end time combo boxes restrict you to only scheduling appointments from 8:00am to 10:00pm EST on a single day. There is therefore only one date picker for an appointment.

The customers tab lists all customers in the database and provides buttons for adding, modifying, and deleting customers. The form for adding or modifying a customer includes combo boxes for country and "first level division" within a country (labeled "division"). Selecting a country automatically restricts the divisions available in the division combo box.

The reports tab provides a combo box listing three reports. Select any report to have it displayed. Each report displays as a table. All of the reports organize items into groups. To make these groupings more apparent at a glance, the left-most column includes only single quotation marks to indicate that the value of the cell is identical to the value of the cell above it.

Each of the tabs has a "Refresh" button. This button causes the application to query the database for the data again to update the data on the tab for any changes made to the database externally to the application.

Lambdas Labels
The JavaDoc comments include a number of paragraphs labelled "LAMBDA" to meet the class requirement that I justify in comments all lambdas appearing in the code.
