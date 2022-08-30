<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student form</title>
    </head>
    <body>
        <h2>Student information</h2>
        <form:form method="POST" action="/addStudent">
            <table>
                <tr>
                    <td><form:label path="id">Id</form:label></td>
                    <td><form:input path="id" type="number" /></td>
                </tr>
                <tr>
                    <td><form:label path="age">Age</form:label></td>
                    <td><form:input path="age" type="number" /></td>
                </tr>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>