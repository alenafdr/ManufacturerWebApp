<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Product List</h1>

<c:if test="${!empty products}">
    <table class="tg">
        <tr>
            <th width="120">Name</th>
            <th width="120">Price</th>
            <th width="120">Manufacturer</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.manufacturer}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="<c:url value='/products/edit/${product.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/products/remove/${product.id}'/>">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</c:if>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h1>Add a Product</h1>
    <c:url var="addAction" value="/products/add"/>

    <form:form action="${addAction}" commandName="product">
        <table>
            <c:if test="${!empty product.name}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="40" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>

            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="price">
                        <spring:message text="Price"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="price"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="manufacturer.id">
                        <spring:message text="Manufacturer"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="manufacturer.id">
                        <c:forEach items="${manufacturers}" var="manufacturer">
                            <form:option value="${manufacturer.id}">${manufacturer.name}</form:option>
                        </c:forEach>
                    </form:select>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <c:if test="${!empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Edit Product"/>"/>
                    </c:if>
                    <c:if test="${empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Add Product"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</sec:authorize>
<a href="/">Back to main</a>
</body>
</html>