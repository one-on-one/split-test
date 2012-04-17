<%@ page import="com.ono.ab.SplitTestVariant" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="splitTest"/>
    <g:set var="entityName" value="SplitTestVariant"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>

    <script src="${resource(dir: 'js', file: 'codemirror.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'codemirror.css')}">

    <script src="${resource(dir: 'mode/xml', file: 'xml.js')}"></script>
    <script src="${resource(dir: 'mode/javascript', file: 'javascript.js')}"></script>
    <script src="${resource(dir: 'mode/css', file: 'css.js')}"></script>
    <script src="${resource(dir: 'mode/htmlmixed', file: 'htmlmixed.js')}"></script>
    <script src="${resource(dir: 'mode/htmlembedded', file: 'htmlembedded.js')}"></script>

    <script type="text/javascript">
        $(function () {
            var textArea = document.getElementById('code');
            var myCodeMirror = CodeMirror.fromTextArea(textArea, {
                matchBrackets:true,
                lineNumbers:true,
                mode:"application/x-ejs",
                indentUnit:4,
                indentWithTabs:true,
                enterMode:"keep",
                tabMode:"shift"
            });
        });
    </script>
</head>

<body>

<div class="body">

    <div class="span3">
        <div class="well sidebar-nav">

            <ul class="nav nav-list">
                <li class="nav-header">Actions</li>
                <li><g:link controller="splitTest" action="show" id="${splitTestVariantInstance?.splitTest?.id}">
                    <i class="icon icon-arrow-left"></i> Back to Split Test</g:link></li>

                <li class="nav-header">Instructions</li>
                <li>
                    <p>A split test needs variants (A, B, C, etc). Fill out this form to create a new variant.</p>

                    <p><strong>Name</strong> - This is what the variant will be referenced by.</p>

                    <p><strong>Is Active?</strong> - If this is checked, the variant will be included in the split test.
                    If not, it will be skipped.</p>

                    <p><strong>Code</strong> - This is the code that will be inserted into the body of the split test.
                    The code can be HTML/JavaScript/CSS.</p>
                </li>
            </ul>
        </div><!--/.well -->
    </div><!--/span-->

    <div class="span9">
        <div class="page-header">
            <h1>Create Split Test Variant <small>for ${splitTestVariantInstance?.splitTest?.name}</small></h1>
        </div>

        <g:if test="${flash.message}">
            <div class="alert alert-success">${flash.message}</div>
        </g:if>

        <g:hasErrors bean="${splitTestVariantInstance}">
            <div class="alert alert-error">
                <g:renderErrors bean="${splitTestVariantInstance}" as="list"/>
            </div>
        </g:hasErrors>

        <g:form action="save" method="post" class="form-horizontal">
            <g:render template="form" bean="${splitTestVariantInstance}"/>

            <div class="form-actions">
                <g:submitButton name="create" class="btn btn-primary" value="Create Variant"/>
                <g:link class="btn" controller="splitTest" action="show"
                        id="${splitTestVariantInstance?.splitTest?.id}">Cancel</g:link>
            </div>

        </g:form>
    </div>
</div>
</body>
</html>
