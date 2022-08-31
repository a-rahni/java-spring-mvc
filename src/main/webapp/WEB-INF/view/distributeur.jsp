<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>distributeur</title>
        <link rel="stylesheet" type="text/css" href="../css/style1.css" />
    </head>
    <body>
        <h2>Crédit restant: <c:out value="${balance}" /></h2>
        <table style="padding: 10px; border:1px dotted gray"">
            <caption>Liste des produit</caption>
            <tr style="background: pink;">
                <th>Numéro de produit</th>
                <th>Nom</th>
                <th>Quantité</th>
                <th>Prix</th>
                <th>Acheter</th>
            </tr>
            <form:form method="POST" action="/buyProduct" modelAttribute="buyForm">
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td><c:out value="${product.getId()}"/>
                            <form:input path="idProduct" value="${product.getId()}" hidden="true" type="number" /> <!-- hiddden, juste pour garder-->
                            <form:input path="quantity" value="1" hidden="true" type="number" /> <!-- hiddden, juste pour garder-->
                        </td>
                        <td><c:out value="${product.getName()}"/></td>
                        <td><c:out value="${product.getQuantity()}"/></td>
                        <td><c:out value="${product.getPrice()}"/></td>
                        <td><input type="submit" value="buy"/></td>
                    </tr>
                </c:forEach>
            </form:form>
        </table>
        
        <br />
        
        
        <!-- utiliser les mêmes nom des attributs de la classe balanceForm -->
        <form:form method="POST" action="/addBalance" modelAttribute="balanceForm">
            <fieldset>
                <legend>Ajouter du crédit</legend>
                <p>
                    <form:label path="balance">Montant : </form:label>
                    <form:input path="balance" type="number" />
                    <form:errors path="balance" />
                </p>
                <input type="submit" value="Ajouter" />
            </fieldset>  
        </form:form>

        <br />
        <br />

        <form:form method="POST" action="/buyProduct" modelAttribute="buyForm" >
            <fieldset>
                <legend>Acheter un produit</legend>
                 <p>
                     <form:label path="idProduct">ID : </form:label>
                    <form:input type="number" path="idProduct" /> <!-- meêm nom que l'attribut de la classe ByProductForm -->
                    <form:errors path="idProduct" />
                    <br/>
                    <form:label path="quantity">Quantity : </form:label>
                    <form:input type="number" path="quantity" /> <!-- meêm nom que l'attribut de la classe ByProductForm -->
                    <form:errors path="quantity" />
                    <br/>
                    <input type="submit" value="Acheter"/>
                    
                    <form:errors  />
                    <c:if test="${errorMessage != null}">
                    <p style="color:red;"><c:out value="${errorMessage}" /></p>
                    </c:if>
                    
                 </p>
            </fieldset>
        </form:form>
        
    </body>
</html>