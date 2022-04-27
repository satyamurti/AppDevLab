<%-- 
    Document   : ExamServer
    Created on : 07-Feb-2022, 11:25:20 am
    Author     : Aryan
--%>

<%@page contentType="text/html"  language="java"  import="java.sql.*"%>
<html>
<head>
<title>Online Exam Server</title>
<style type="text/css">
 body{background-color:white;font-family:Helvetica;color:blue}
</style>
</head>
<body>
<h2 style="text-align:center">MARK SHEET</h2>
<p>
<a href="examclient.html">Back To Main Page</a>
</p>
<hr/>
<%

//int mark=0;
//Connection connect = null;
ResultSet rs = null;
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
String url = "jdbc:ucanaccess:///C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/Q3/Lab3_db.accdb";
Connection connect = DriverManager.getConnection(url, " ", " ");
    


int Total = 0;
String Seat_no = request.getParameter("Seat_no");
//String Name = request.getParameter("Name");

String[] answers = new String[5];
answers[0] = request.getParameter("group1");
answers[1] = request.getParameter("group2");
answers[2] = request.getParameter("group3");
answers[3] = request.getParameter("group4");
answers[4] = request.getParameter("group5");

Statement st = connect.createStatement();
String q = "SELECT * FROM TestAnswers";
ResultSet rs1 = st.executeQuery(q);

int count = 0;

while(rs1.next()){
    if(rs1.getString("Answers").equals(answers[count])){
        Total+=5;
    }
    count++;
}

       
        
Statement stmt = connect.createStatement();
String query = "UPDATE StudentsDB1 SET Marks= ? WHERE Seat_no="+"'"+Seat_no+"'";
PreparedStatement pst = connect.prepareStatement(query);
pst.setString(1,String.valueOf(Total));
pst.executeUpdate();


query = "SELECT * FROM StudentsDB1 WHERE Seat_no="+"'"+Seat_no+"'";
rs = stmt.executeQuery(query);
out.println("<center>");
out.println("<table border=5>");
out.println("<th>Seat Number</th>");
out.println("<th>Name</th>");
out.println("<th>Marks</th>");


while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getString("Seat_no")+"</td>");
                out.println("<td>"+rs.getString("Student_name")+"</td>");
                out.println("<td>"+rs.getString("Marks")+"/25</td>");
                out.println("</tr>");
    
            }
out.println("</table>");

if(Total>=15)
{
 //out.println("<h4>Your Mark Is : "+Total+"</h4>");
 out.println("<h3>Congratulations....! You Are Eligible For The Next Round...</h3>");
}
else
{
 //out.println("<h4>Your Mark is : "+Total+"</h4>");
 out.println("<h3>Sorry....!! You Are Not Eligible For The Next Round...</h3>");
}
out.println("</center>");
%>

</body>
</html>

