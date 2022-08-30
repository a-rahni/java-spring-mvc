<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student form</title>
    </head>
    <body>
        <h2>Student information</h2>
        <!-- Si on ne spécifie pas de modelAttribute le nom de l'attribut -->
        <!-- a SET dans le controller sera 'command' -->
        <!-- le path est le nom de l'attribut de bean correspondant (classe java) -->
        <form:form method="POST" action="/addStudent" modelAttribute="studentForm">
            <table>
                <tr>
                    <td><form:label path="age">Age</form:label></td>
                    <td>
                        <form:input path="age" type="number" />
                        <form:errors path="age" />
                    </td>
                </tr>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td>
                        <form:input path="name" />
                        <form:errors path="name" />
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>