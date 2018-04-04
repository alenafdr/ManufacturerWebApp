<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturers</title>
</head>
<body>
<h1>Manufacturer List</h1>

<c:if test="${!empty manufacturers}">
    <table class="tg">
        <tr>
            <th width="120">Name</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </sec:authorize>

        </tr>
        <c:forEach items="${manufacturers}" var="manufacturer">
            <tr>
                <td>${manufacturer.name}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="<c:url value='/manufacturers/edit/${manufacturer.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/manufacturers/remove/${manufacturer.id}'/>">Delete</a></td>
                </sec:authorize>

            </tr>
        </c:forEach>
    </table>
</c:if>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <h1>Add a Manufacturer</h1>
    <c:url var="addAction" value="/manufacturers/add"/>

    <form:form action="${addAction}" commandName="manufacturer">
        <table>
            <c:if test="${!empty manufacturer.name}">
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
                <td colspan="2">
                    <c:if test="${!empty manufacturer.name}">
                        <input type="submit"
                               value="<spring:message text="Edit Manufacturer"/>"/>
                    </c:if>
                    <c:if test="${empty manufacturer.name}">
                        <input type="submit"
                               value="<spring:message text="Add Manufacturer"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</sec:authorize>

<a href="/">Back to main</a>
</body>
</html>
