package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount=0;
    private int wrongAnswerCount=1;
    private int questionCount=1;
    private int quizCount=1;
    static final private int QUIZ_COUNT=100;//Will be changed to 100



    ArrayList<ArrayList<String>> quizArray=new ArrayList<>();
    //ArrayList<ArrayList<String>> correctResponses = new ArrayList<>();
   // ArrayList<ArrayList<String>> incorrectResponses = new ArrayList<>();

    String quizData[][]= {
            //{"Question","Right Answer","Choice1","Choice2","Choice3"}
            {"Which of These is Not an Acronym for One of BU's Colleges?", "SED", "COM", "ENG", "CAS"},
            {"What is Boston University's Mascot?", "Rhett the Terrier", "Bucky Badger", "The Boston Beavers", "Big Red"},
            {"In what Year did Boston University Change to its Current Mascot?", "1917", "1898", "2001", "1865"},
            {"Which of These is a Famous Alum?", "Leonard Nimoy", "Jeff Dunham", "Nicole Kidman", "Steve Jobs"},
            {"What are BU's Primary Colors?", "Scarlet and White", "Crimson and Gold", "Black and Gold", "Blue and Silver"},
            {"The Judson B. Coit Observatory is Open to the Public on...", "Wednesday Nights", "Thursday Nights", "Saturday Nights", "Every Night"},
            {"What is the Name of the Sculpture Behind Marsh Chapel?", "the Whale", "the Beach", "Lightening Strike", "Metallic Getaway"},
            {"What City is BU Located in?", "Boston", "Beverly Hills", "Beacon Hill", "Baltimore"},
            {"What is the Name of Boston University's President?", "Brown", "Densmore", "Smith", "Jefferson"},
            {"What MBTA Line Runs Through the Center of Campus?", "The Green Line", "The Red Line", "The Blue Line", "The Purple Line"},
            {"In What Year did BU Open All its Divisions to Female Students?", "1872", "1902", "1919", "1943"},
            {"What BU Alum Produced Movie Won Best Animated Picture in 2013?", "Frozen", "Monsters University", "Planes", "Despicable Me 2"},
            {"What School/College did Martin Luther King Jr. Attend?", "School of Theology", "College of Arts and Sciences", "School of Education", "School of Public Health"},
            {"Which League does BU Send More Varsity Players to Than Any Other College?", "NHL", "MBL", "NBA", "NFL"},
            {"What is the Class of 2021's Acceptance Rate?", "25%", "24.2%", "18.1%", "28.3%"},
            {"What MBTA Line Runs Through the Center of Campus?", "The Green Line", "The Red Line", "The Blue Line", "The Purple Line"},
            {"Which of the Following is not a BU Dining Hall?", "Danielsen", "West", "Warren", "Marciano"},
            {"Where was the Old Book Store Located?", "Kenmore Square", "GSU", "West Campus", "Cummington Mall"},
            {"What is the Most Recently Renovated Dorm?", "Myles", "Kilachand", "Warren", "The Towers"},
            {"What is on the Top Floor of Student Village II?", "a Study Space", "a Pool", "more Dorms", "a Bowling Alley"},
            {"Who is the Dean of Engineering?", "Lutchen", "Brown", "Thurman", "Zlateva"},
            {"How Many Varisty Sports Teams are there?", "21", "20", "19", "25"},
            {"Rumor has it if You Touch this, You won't Graduate in 4 Years. What is it?", "the BU Seal", "The BU Rock", "The Embrace", "COM's Fountain"},
            {"Which month is Fall SPLASH held?", "September", "August", "October", "November"},
            {"What Business did the BUILD Lab Replace?", "Radioshack", "CVS", "BU Bookstore", "Starbucks"},
            {"Who can Access the BUILD Lab?", "All Students & Alumni", "ENG Students", "Medical Students", "Faculty Only"},
            {"How many Countries does BU have Study Abroad Programs in?", "25", "30", "21", "27"},
            {"What is the most Common Major at BU?", "Business Administration & Management", "Communications", "Economics", "Psychology"},
            {"How many Pavement Coffee Houses are there in Boston?", "8", "2", "10", "14"},
            {"When was BU's First Summer Term Held?", "1915", "1923", "1968", "1987"},
            {"Which BU Building is Heated & Cooled Entirely Without Fossil Fuels?", "Stuvi II", "Stuvi I", "Kilachand", "Myles Standish Hall"},
            {"Which of these BU Clubs Existed in the 1950s?", "BU Parachute", "BU Robotics", "BU Parasailing", "BU Bowling"},
            {"What is the Average Class Size at BU?", "27", "26", "28", "32"},
            {"How many 'T' Stops are there On Campus?", "8", "10", "12", "13"},
            {"What Street is Can you Find the Engineering Research Building?", "Cummington Mall", "Beacon St", "Commonwealth Ave", "Babcock St"},
            {"Which Month is the Beanpot Tournament held each Year?", "February", "December", "January", "March"},
            {"Which Dining Area do you go to for the Rhett's Challenge?", "GSU", "Warren", "West", "Marciano"},
            {"Where did the BU Band First Perform?", "Boston Common", "Copley Square", "Marsh Chapel", "Coolidge Corner"},
            {"Which Year did BU set the Record for most Consecutive Beanpot Wins?", "2000", "1998", "2001", "2002"},
            {"What is the Total Cost of Attendance of BU in 2019-2020?", "$75,002", "$70,857", "$68,926", "$64,001"},
            {"What is the Name of the Dean of Arts & Sciences?", "Sclaroff", "Elmore", "Stegman", "Boynton"},
            {"What is the Name of the Ghost that Haunts Kilachand?", "Eugene O'Neill", "Charles Chaplin", "Jerold Beacon", "George Kilachand"},
            {"How many Total Schools and Colleges does BU have?", "10", "6", "9", "14"},
            {"BU's Mascot is Named from a Character from what Movie?", "Gone with the Wind", "Casablanca", "Grease", "Citizen Kane"},
            {"What is the Warren Omelette Lady's Name?", "Cecilia", "Marie", "Laura", "Isabel"},
            {"The Crepe Station in West has all of the Following Toppings Except:", "Peanut Butter", "Bananas", "Strawberries", "Chocolate"},
            {"What Percent of Students Complete an Internship?", "89%", "25%", "74%", "63%"},
            {"What is the Average BU Student's GPA?", "3.04", "2.86", "3.23", "3.45"},
            {"How Big is the BU Student Body?", "34k", "37k", "47k", "60k"},
            {"BU was the First University to Award a Phd to a Woman. What was her name?", "Helen Magill White", "Louis Smith", "Katherine Jo Silber", "Charlotte Greensmith"},
            {"Which Year was the Four-Faced Clock Given to BU?", "1998", "1986", "2001", "1992"},
            {"Which BU Residence did Martin Luther King Jr. Meet his Wife at?", "Myles Standish Hall", "Bay State Brownstone", "Warren", "Kilachand"},
            {"How Large was the Charles River Campus when it was Purchased in 1920?", "15 acres", "10 acres", "20 acres", "13 acres"},
            {"After Leaving BU, Harry Agganis Joined the Red Sox for how many Seasons?", "Three", "Two", "One", "Four"},
            {"How much Foot Traffic does Mugar Library get Yearly?", "1 Million", "2 Million", "1.5 Million", "3 Million"},
            {"What is the Student-to-Faculty Ratio?", "10:1", "8.1", "12:1", "6:1"},
            {"BU is one of only how many Universities in Boston and Cambridge named to the Prestigious AAU?", "3", "2", "4", "5"},
            {"Around how many Programs of Study does BU have?", "300+", "100+", "200+", "400+"},
            {"How much Financial Aid is Awarded to Undergraduates?", "$260M", "$200M", "$150M", "$310M"},
            {"What Year did BU Start Hosting Lobster Night?", "1985", "1980", "1990", "1975"},
            {"What is BU's most Popular Intramural Sport?", "Broomball", "Flag Football", "Tennis", "Wiffleball"},
            {"In 2019, what Rank was BU for Employability in the US by the Times' Higher Education?", "#18", "#10", "#25", "#13"},
            {"How many Countries do BU Students come from?", "150", "140", "160", "170"},
            {"What is the Percentage of BU Students that are First Generation?", "16%", "12%", "20%", "13%"},
            {"What percentage of faculty have a PhD or equivalent?", "90%", "85%", "95%", "100%"},
            {"The Binoy K. Singh Imagineering Laboratory is also known as...", "The Tinker Lab", "EPIC", "Ingalls", "LSE"},
            {"What percentage of BU students participate in Greak life?", "13%", "16%", "19%", "23%"},
            {"What type of engineering does BU not have?", "Chemical Engineering", "Computer Engineering", "Mechanical Engineering", "Biomedical Engineering"},
            {"When was the Citgo sign originally built?", "1960", "1965", "1970", "1975"},
            {"What year was Agganis Arena opened?", "2003", "2000", "2001", "2007"},
            {"How many seats does Agganis Arena have?", "5,200", "5,800", "6,200", "6,400"},
            {"In what year was the first BU LGBTQ+ group established?", "1965", "1976", "1981", "1970"},
            {"What year was the BU bridge built?", "1948", "1932", "1943", "1956"},
            {"How many international Terriers were enrolled in 2018?", "18,743", "17,897", "16,789", "19,102"},
            {"The student body is made up of how many nationalities?", "36", "42", "27", "51"},
            {"What is the percentage of the aid BU gives is need-based?", "91%", "89%", "100%", "97%"},
            {"What is the percentage of the aid BU gives is merit-based?", "9%", "11%", "10%", "8%"},
            {"Within six months of graduation, what is the percentage of students who found emplyment or placement in graduate programs, military service, and fellowships?", "96%", "90%", "88%", "93%"},
            {"In what BU dorm floor is supposedly haunted by playwright and Novel Prize winner Eugene O'Neill where the lights flicker and elevator stops sometimes?", "Fourth Floor of Kilachand Honors Dorm", "9B Engineering Floor in Warren Towers", "Floor 2 in Myles", "Floor 13 in Stuvi2"},
            {"Which BU dining hall has Kosher and Hillel options available?", "Granby Commons", "Fenway Campus", "Warren Towers", "Marciano Commons"},
            {"In what year was BU founded and BU's official mascot bred?", "1839", "1917", "1899", "1905"},
            {"What team sport does BU not have a sculpture of a player of?", "Football", "Swimming", "Tennis", "Baseball"},
            {"Which of the following are not names that BU has been called in the past?", "Newbury Institute", "Boston's Beacon Hill", "Methodist General Biblical Institute of Concord", "Boston Theological School"},
            {"What college/school in bUS is the most recent one?", "Wheelock College of Education & Human Development", "College of Communication", "School of Hospitality & Administration", "College of Engineering"},
            {"What is not an outreach activity you can do under the Community Service Center?", "Alternative Spring Breaks", "FYSOP", "Alternative Service Breaks", "Global Day of Service"},
            {"What new Master's program is being added in Fall 2020?", "Robotics", "Data Science", "Cybersecurity", "Data Analytics"},
            {"All of these are research opportunities at BU except?", "Engineering Honors Research", "SURF", "UROP", "STARS"},
            {"How much money is used in engineering-related research expenditures at BU?", "$93M", "$108M", "$81M", "$123M"},
            {"What Movie was Filmed on BU's Campus in 2018?", "Wonderland", "The Irishman", "Knives Out", "Doctor Sleep"},
            {"What Business did the BUILD Lab Replace?", "Radioshack", "CVS", "BU Bookstore", "Starbucks"},
            {"What areas of study does BU provide?", "All of the above", "Robotics & Autonomous Systems", "Manufacturing Engineering", "Photonics"},
            {"Who was the third president of Boston University?", "Lemuel Herbert Murlin", "William Edwards Huntington", "Daniel L. Marsh", "Harold C. Case"},
            {"Which college/school was not founded in 1911-1924?", "College of Communication", "College of Business Administration", "School of Education", "College of Secretarial Sciences"},
            {"How many presidents has BU had since 1873?", "10", "15", "20", "25"},
            {"Where is there an organic garden on BU campus?", "Stone Science Building", "George Sherman Union", "BU Urban Laboratory", "Soil Hydraulics Facility"},
            {"Where is there an observatory on campus that is open to the public on Wednesday nights?", "College of Arts & Sciences", "Marsh Chapel", "George Sherman Union", "School of Theology"},
            {"What is the address of BU's Police Department?", "32 Harry Agganis Way", "33 Harry Agganis Way", "560 Commonwealth Ave", "270 Babcock St"},
            {"How many Dining Halls are On Campus?","4","3","2","5"},
            {"How many Classrooms are at BU?","544","670","765","345"},
            {"How many Libraries are on BU's Campus?","21","32","5","27"},
            {"How many Club Sports Teams Exist at BU?","34","29","22","38"}




    };
    String quizFreshmen[][]= {
            //{"Question","Right Answer","Choice1","Choice2","Choice3"}
            {"Which of These is Not an Acronym for One of BU's Colleges?", "SED", "COM", "ENG", "CAS"},
            {"What is Boston University's Mascot?", "Rhett the Terrier", "Bucky Badger", "The Boston Beavers", "Big Red"},
            {"What Type of Animal is BU's Mascot?","Dog","Eagle","Beaver","It's Not an Animal"},
            {"What are BU's Primary Colors?", "Scarlet and White", "Crimson and Gold", "Black and Gold", "Blue and Silver"},
            {"What City is BU Located in?", "Boston", "Beverly Hills", "Beacon Hill", "Baltimore"},
            {"What MBTA Line Runs Through the Center of Campus?", "The Green Line", "The Red Line", "The Blue Line", "The Purple Line"},
            {"Which of the Following is not a BU Dining Hall?","Danielsen","West","Warren","Marciano"},
            {"Where was the Old Book Store Located?","Kenmore Square","GSU","West Campus","Cummington Mall"},
            {"What is the Most Recently Renovated Dorm?","Myles","Kilachand","Warren","The Towers"},
            {"What is on the Top Floor of Student Village II?","a Study Space","a Pool","more Dorms","a Bowling Alley"},
            {"The BU Shuttle Stops at All the Following Locations except:","Warren","Marsh Chapel","Stuvi II","St Mary's St."},
            {"What is the Name of Boston University's Current President?","Brown","Elmore","Densmore","Smith"},
            {"Rumor has it if You Touch this, You won't Graduate in 4 Years. What is it?","the BU Seal","The BU Rock","The Embrace","COM's Fountain"},
            {"Which month is Fall SPLASH held?","September","August","October","November"},
            {"What Business did the BUILD Lab Replace?","Radioshack","CVS","BU Bookstore","Starbucks"},
            {"Who can Access the BUILD Lab?","All Students & Alumni","ENG Students","Medical Students","Faculty Only"},
            {"How many Countries does BU have Study Abroad Programs in?","25","30","21","27"},
            {"What is the Name of the Ghost that Haunts Kilachand?","Eugene O'Neill","Charles Chaplin","Jerold Beacon","George Kilachand"},
            {"How many Total Schools and Colleges does BU have?","10","6","9","14"},
            {"What Movie was Filmed on BU's Campus in 2018?","Wonderland","The Irishman","Knives Out","Doctor Sleep"},
            {"What Percentage of Students Participate in On Campus Housing?","75%","80%","85%","65%"},
            {"What percentage of faculty have a PhD or equivalent?","90%","85%","95%","100%"},
            {"The Binoy K. Singh Imagineering Laboratory is also known as...","The Tinker Lab","EPIC","Ingalls","LSE"},
            {"What percentage of BU students participate in Greak life?","13%","16%","19%","23%"},
            {"What type of engineering does BU not have?","Chemical Engineering","Computer Engineering","Mechanical Engineering","Biomedical Engineering"}

    };
    String quizSophomore[][]= {
            //{"Question","Right Answer","Choice1","Choice2","Choice3"}
            {"Which of These is a Famous Alum?","Leonard Nimoy","Jeff Dunham","Nicole Kidman","Steve Jobs"},
            {"The Judson B. Coit Observatory is Open to the Public on:","Wednesday Nights","Thursday Nights","Saturday Nights","Every Night"},
            {"What Street is Can you Find the Engineering Research Building?","Cummington Mall","Beacon St","Commonwealth Ave","Babcock St"},
            {"What is the Name of the Sculpture Behind Marsh Chapel?", "the Whale", "the Beach", "Lightening Strike", "Metallic Getaway"},
            {"What School/College Did Martin Luthor King Jr. Attend?","School of Theology","College of Arts and Sciences","School of Public Health","School of Education"},
            {"Who is the Dean of Engineering?","Lutchen","Brown","Thurman","Zlateva"},
            {"How Many Varisty Sports Teams are there?","21","20","19","25"},
            {"What is the most Common Major at BU?","Business Administration & Management","Communications","Economics","Psychology"},
            {"How many Pavement Coffee Houses are there in Boston?","8","2","10","14"},
            {"When was BU's First Summer Term Held?","1915","1923","1968","1987"},
            {"Which BU Building is Heated & Cooled Entirely Without Fossil Fuels?","Stuvi II","Stuvi I","Kilachand","Myles Standish Hall"},
            {"Which of these BU Clubs Existed in the 1950s?","BU Parachute","BU Robotics","BU Parasailing","BU Bowling"},
            {"What is the Average Class Size at BU?","27","26","28","32"},
            {"How many 'T' Stops are there On Campus?","8","10","12","13"},
            {"What is the Class of 2021's Acceptance Rate?","25%","28%","24.2%","18.1%"},
            {"Which Month is the Beanpot Tournament held each Year?","February","December","January","March"},
            {"Which Dining Area do you go to for the Rhett's Challenge?","GSU","Warren","West","Marciano"},
            {"Where did the BU Band First Perform?","Boston Common","Copley Square","Marsh Chapel","Coolidge Corner"},
            {"Which Year did BU set the Record for most Consecutive Beanpot Wins?","2000","1998","2001","2002"},
            {"What is the Total Cost of Attendance of BU in 2019-2020?","$75,002","$70,857","$68,926","$64,001"},
            {"What is the Name of the Dean of Arts & Sciences?","Sclaroff","Elmore","Stegman","Boynton"},
            {"What Movie was Filmed on BU's Campus in 2018?","Wonderland","The Irishman","Knives Out","Doctor Sleep"},
            {"What Percentage of Students Participate in On Campus Housing?","75%","80%","85%","65%"},
            {"Where is there an organic garden on BU campus?","Stone Science Building","George Sherman Union","BU Urban Laboratory","Soil Hydraulics Facility"},
            {"Where is there an observatory on campus that is open to the public on Wednesday nights?","College of Arts & Sciences","Marsh Chapel","George Sherman Union","School of Theology"}



    };
    String quizJunior[][]={
            {"What BU Alum Produced Movie Won Best Animated Picture in 2013?","Frozen","Planes","Despicable Me","Monsters University"},
            {"Which League does BU Send more Varsity Players to Than Any Other College?","NHL","MBL","NFL","NBA"},
            {"In What Year did BU Open All its Divisions to Female Students?","1872","1902","1919","1943"},
            {"In what Year did Boston University change to its Current Mascot?","1917","1898","2001","1965"},
            {"BU's Mascot is Named from a Character from what Movie?","Gone with the Wind","Casablanca","Grease","Citizen Kane"},
            {"What is the Warren Omelette Lady's Name?","Cecilia","Marie","Laura","Isabel"},
            {"The Crepe Station in West has all of the Following Toppings Except:","Peanut Butter","Bananas","Strawberries","Chocolate"},
            {"What Percent of Students Complete an Internship?","89%","25%","74%","63%"},
            {"What is the Average BU Student's GPA?","3.04","2.86","3.23","3.45"},
            {"How Big is the BU Student Body?","34k","37k","47k","60k"},
            {"BU was the First University to Award a Phd to a Woman. What was her name?","Helen Magill White","Louis Smith","Katherine Jo Silber","Charlotte Greensmith"},
            {"Which Year was the Four-Faced Clock Given to BU?","1998","1986","2001","1992"},
            {"Which BU Residence did Martin Luther King Jr. Meet his Wife at?","Myles Standish Hall","Bay State Brownstone","Warren","Kilachand"},
            {"How Large was the Charles River Campus when it was Purchased in 1920?","15 acres","10 acres","20 acres","13 acres"},
            {"After Leaving BU, Harry Agganis Joined the Red Sox for how many Seasons?","Three","Two","One","Four"},
            {"How much Foot Traffic does Mugar Library get Yearly?","1 Million","2 Million","1.5 Million","3 Million"},
            {"What is the Student-to-Faculty Ratio?","10:1","8.1","12:1","6:1"},
            {"BU is one of only how many Universities in Boston and Cambridge named to the Prestigious AAU?","3","2","4","5"},
            {"Around how many Programs of Study does BU have?","300+","100+","200+","400+"},
            {"How much Financial Aid is Awarded to Undergraduates?","$260M","$200M","$150M","$310M"},
            {"What Year did BU Start Hosting Lobster Night?","1985","1980","1990","1975"},
            {"What is BU's most Popular Intramural Sport?","Broomball","Flag Football","Tennis","Wiffleball"},
            {"In 2019, what Rank was BU for Employability in the US by the Times' Higher Education?","#18","#10","#25","#13"},
            {"How many Countries do BU Students come from?","150","140","160","170"},
            {"What is the Percentage of BU Students that are First Generation?","16%","12%","20%","13%"}

    };
    String quizSenior[][]={
            {"When was the Citgo sign originally built?","1960","1965","1970","1975"}, //Vanessa starts here!
            {"What year was Agganis Arena opened?","2003","2000","2001","2007"},
            {"How many seats does Agganis Arena have?","5,200","5,800","6,200","6,400"},
            {"In what year was the first BU LGBTQ+ group established?","1965","1976","1981","1970"},
            {"What year was the BU bridge built?","1948","1932","1943","1956"},
            {"How many international Terriers were enrolled in 2018?","18,743","17,897","16,789","19,102"},
            {"The student body is made up of how many nationalities?","36","42","27","51"},
            {"What is the percentage of the aid BU gives is need-based?","91%","89%","100%","97%"},
            {"What is the percentage of the aid BU gives is merit-based?", "9%","11%","10%","8%"},
            {"Within six months of graduation, what is the percentage of students who found emplyment or placement in graduate programs, military service, and fellowships?","96%","90%","88%","93%"},
            {"In what BU dorm floor is supposedly haunted by playwright and Novel Prize winner Eugene O'Neill where the lights flicker and elevator stops sometimes?","Fourth Floor of Kilachand Honors Dorm","9B Engineering Floor in Warren Towers","Floor 2 in Myles","Floor 13 in Stuvi2"},
            {"Which BU dining hall has Kosher and Hillel options available?","Granby Commons","Fenway Campus","Warren Towers","Marciano Commons"},
            {"In what year was BU founded and BU's official mascot bred?", "1839","1917","1899","1905"},
            {"What team sport does BU not have a sculpture of a player of?","Football","Swimming","Tennis","Baseball"},
            {"Which of the following are not names that BU has been called in the past?","Newbury Institute","Boston's Beacon Hill","Methodist General Biblical Institute of Concord","Boston Theological School"},
            {"What college/school in bUS is the most recent one?","Wheelock College of Education & Human Development","College of Communication","School of Hospitality & Administration","College of Engineering"},
            {"What is not an outreach activity you can do under the Community Service Center?","Alternative Spring Breaks","FYSOP","Alternative Service Breaks","Global Day of Service"},
            {"What new Master's program is being added in Fall 2020?","Robotics","Data Science","Cybersecurity","Data Analytics"},
            {"All of these are research opportunities at BU except?","Engineering Honors Research","SURF","UROP","STARS"},
            {"How much money is used in engineering-related research expenditures at BU?","$93M","$108M","$81M","$123M"},
            {"What Movie was Filmed on BU's Campus in 2018?","Wonderland","The Irishman","Knives Out","Doctor Sleep"},
            {"What Business did the BUILD Lab Replace?","Radioshack","CVS","BU Bookstore","Starbucks"},
            {"What areas of study does BU provide?","All of the above","Robotics & Autonomous Systems","Manufacturing Engineering","Photonics"},
            {"Who was the third president of Boston University?","Lemuel Herbert Murlin","William Edwards Huntington","Daniel L. Marsh","Harold C. Case"},
            {"Which college/school was not founded in 1911-1924?","College of Communication","College of Business Administration","School of Education","College of Secretarial Sciences"},
            {"How many presidents has BU had since 1873?","10", "15","20","25"} //26

    };
    //START HERE - VANESSA'S WORK [CORRECT AND INCORRECT RESPONSES RANDOMIZER]
    String correctResponses[] = {"Correct!", "Great Job!", "Excellent!", "Fantastic!", "Absolutely!", "That's spot on!","That's it!","You're dead right!","Easy peasy!","You're on fire!", "You're quite right!","Yes, that's very correct!"};


    String incorrectResponses[] = {"Incorrect...", "Wrong...","You were close!","Not quite...","Not exactly...","You're mistaken...","Nope!","Better luck next time!","Oof..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countLabel=(TextView) findViewById(R.id.countLabel);
        questionLabel=(TextView) findViewById(R.id.questionLabel);
        answerBtn1=(Button)findViewById(R.id.answerBtn1);
        answerBtn2=(Button)findViewById(R.id.answerBtn2);
        answerBtn3=(Button)findViewById(R.id.answerBtn3);
        answerBtn4=(Button)findViewById(R.id.answerBtn4);

        //Receive quizCategory from StartActivity
        int quizCategory=getIntent().getIntExtra("QUIZ_CATEGORY",0);
        Log.v("CATEGORY_TAG", quizCategory + "");
        //Create quizArray from QuizData
        if(quizCategory==0) {
            for (int i = 0; i < quizData.length; i++) {

                //Prepare Array
                ArrayList<String> tempArray = new ArrayList<>();
                tempArray.add(quizData[i][0]); //Question
                tempArray.add(quizData[i][1]); //Right Answer
                tempArray.add(quizData[i][2]); //Choice 1
                tempArray.add(quizData[i][3]); //Choice 2
                tempArray.add(quizData[i][4]); //Choice 3
                //Add tempArray to quizArray
                quizArray.add(tempArray);
            }
        }
        else if(quizCategory==1){
            for (int i = 0; i < quizFreshmen.length; i++) {

                //Prepare Array
                ArrayList<String> tempArray = new ArrayList<>();
                tempArray.add(quizFreshmen[i][0]); //Question
                tempArray.add(quizFreshmen[i][1]); //Right Answer
                tempArray.add(quizFreshmen[i][2]); //Choice 1
                tempArray.add(quizFreshmen[i][3]); //Choice 2
                tempArray.add(quizFreshmen[i][4]); //Choice 3
                //Add tempArray to quizArray
                quizArray.add(tempArray);
            }
        }
        else if(quizCategory==2){
            for (int i = 0; i < quizSophomore.length; i++) {

                //Prepare Array
                ArrayList<String> tempArray = new ArrayList<>();
                tempArray.add(quizSophomore[i][0]); //Question
                tempArray.add(quizSophomore[i][1]); //Right Answer
                tempArray.add(quizSophomore[i][2]); //Choice 1
                tempArray.add(quizSophomore[i][3]); //Choice 2
                tempArray.add(quizSophomore[i][4]); //Choice 3
                //Add tempArray to quizArray
                quizArray.add(tempArray);
            }
        }
        else if(quizCategory==3){
            for (int i = 0; i < quizJunior.length; i++) {

                //Prepare Array
                ArrayList<String> tempArray = new ArrayList<>();
                tempArray.add(quizJunior[i][0]); //Question
                tempArray.add(quizJunior[i][1]); //Right Answer
                tempArray.add(quizJunior[i][2]); //Choice 1
                tempArray.add(quizJunior[i][3]); //Choice 2
                tempArray.add(quizJunior[i][4]); //Choice 3
                //Add tempArray to quizArray
                quizArray.add(tempArray);
            }
        }
        else if(quizCategory==4){
            for (int i = 0; i < quizSenior.length; i++) {

                //Prepare Array
                ArrayList<String> tempArray = new ArrayList<>();
                tempArray.add(quizSenior[i][0]); //Question
                tempArray.add(quizSenior[i][1]); //Right Answer
                tempArray.add(quizSenior[i][2]); //Choice 1
                tempArray.add(quizSenior[i][3]); //Choice 2
                tempArray.add(quizSenior[i][4]); //Choice 3
                //Add tempArray to quizArray
                quizArray.add(tempArray);
            }

        }

        showNextQuiz();
    }
    public void showNextQuiz(){
        /*final MediaPlayer waitingMP = MediaPlayer.create(this, R.raw.waiting); // VANESSA'S CODE: Sounds effects added
        waitingMP.start();

            if(waitingMP!=null)
                waitingMP.stop();
                //waitingMP = null; */

        //Update quizCountLabel
        countLabel.setText("Q"+quizCount);
        //Generate Random Number between 0 and 14 (quizArray-1)
        Random random=new Random();
        int randomNum=random.nextInt(quizArray.size());

        //Pick one quiz set.
        ArrayList<String> quiz=quizArray.get(randomNum);

        //Set question and right answer.
        questionLabel.setText((quiz.get(0)));
        rightAnswer=quiz.get(1);
        //Remove Question from quiz array
        quiz.remove(0);
        Collections.shuffle(quiz);
        //Set Choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        //Remove this quiz question from QuizArray
        quizArray.remove(randomNum);
    }
    public void checkAnswer(View view){
        //Get pushed button.
        Button answerBtn=(Button) findViewById(view.getId());
        String btnText= answerBtn.getText().toString();

        String alertTitle; //VANESSA'S CODE HERE! RANDOMIZED THE CORRECT AND INCORRECT RESPONSES
        if(btnText.equals(rightAnswer)){

            final MediaPlayer correctsoundMP = MediaPlayer.create(this, R.raw.correctsound); // VANESSA'S CODE: Sounds effects added
                    correctsoundMP.start();

            int randomIndex = new Random().nextInt(11 + 1);
            alertTitle = correctResponses[randomIndex];
            rightAnswerCount++;

        }else{
          final MediaPlayer wrongsoundMP = MediaPlayer.create(this, R.raw.wrongsound); // VANESSA'S CODE: Sounds effects added
                    wrongsoundMP.start();

            int randomIndex = new Random().nextInt(8 + 1);
            alertTitle = incorrectResponses[randomIndex];
            wrongAnswerCount++;

        }

        //Create Dialog.
        AlertDialog.Builder builder=new AlertDialog.Builder((this));
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: "+rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                int quizCategory=getIntent().getIntExtra("QUIZ_CATEGORY",0);
                if(quizCategory==0) {
                    if (quizCount == QUIZ_COUNT || wrongAnswerCount > 3) {
                        //showresult
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("Right_Answer_Count", rightAnswerCount);
                        intent.putExtra("questionCount", questionCount);
                        intent.putExtra("QUIZ_CATEGORY",quizCategory);
                        startActivity(intent);
                    } else {
                        quizCount++;
                        showNextQuiz();
                        questionCount++;
                    }
                }
                else{
                    if (quizCount == 24 || wrongAnswerCount > 3) {
                        //showresult
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("Right_Answer_Count", rightAnswerCount);
                        intent.putExtra("questionCount", questionCount);
                        intent.putExtra("QUIZ_CATEGORY",quizCategory);
                        startActivity(intent);
                    } else {
                        quizCount++;
                        showNextQuiz();
                        questionCount++;
                    }
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
    public void returnTop(View view){
        Intent intent= new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent);
    }
}
