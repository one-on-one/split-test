<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="splitTest"/>
    <g:set var="entityName" value="Split Test"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<div class="body instructions">

    <div class="span3">
        <div class="well sidebar-nav">

            <ul class="nav nav-list">
                <li class="nav-header">Actions</li>
                <li><g:link class="list" action="list">
                    <i class="icon icon-arrow-left"></i> Back to Split Tests</g:link></li>
            </ul>
        </div>
    </div>

    <div class="span9">

        <div class="page-header">
            <h1>Usage Instructions</h1>
        </div>

        <h3><a name="quick-start" id="quick-start">Quick Start</a></h3>
        <p>
            A split test is comprised of placing a taglib in a view. The taglib will contain the default contents of which
            are to be shown if the test is not enabled. For example:
        </p>
        <script src="https://gist.github.com/2407600.js?file=example.gsp"></script>

        <p>
            When this page is viewed by a visitor, a Split Test is automatically added into the Split Test list. Note that
            it is not active by default.
        </p>
        <p>
            To trigger the conversion, you can do this via the <code>splitTestService</code> in your controller:
        </p>
        <script src="https://gist.github.com/2407620.js?file=ExampleController.groovy"></script>

        <p>You can also trigger different conversion metrics using the Action 1-3 triggers:</p>
        <script src="https://gist.github.com/2407641.js?file=ExampleController.groovy"></script>

    </div>
</div>

</body>
</html>