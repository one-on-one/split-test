<%@ page import="com.ono.ab.SplitTestVariant" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="splitTest" />
    <g:set var="entityName" value="SplitTestVariant" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>

</head>
<body>
<div class="sub_nav">
    <span class="menuButton"><g:link class="list" controller="splitTest" action="show" id="${splitTestVariantInstance?.splitTest?.id}">Back to Split Test</g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="splitTestVariant.id.label" default="ID" /></td>

                <td valign="top" class="value">${fieldValue(bean: splitTestVariantInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="splitTestVariant.name.label" default="Name" /></td>

                <td valign="top" class="value">${fieldValue(bean: splitTestVariantInstance, field: "name")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="splitTestVariant.isActive.label" default="Is Active" /></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${splitTestVariantInstance?.isActive}" /></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="splitTestVariant.code.label" default="Code" /></td>

                <td valign="top" class="value">
                    <pre class="brush: plain;" style="text-align: left;">${fieldValue(bean: splitTestVariantInstance, field: "code")}</pre>
                </td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="splitTestVariant.splitTest.label" default="Split Test" /></td>

                <td valign="top" class="value"><g:link controller="splitTest" action="show" id="${splitTestVariantInstance?.splitTest?.id}">${splitTestVariantInstance?.splitTest?.name}</g:link></td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${splitTestVariantInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
