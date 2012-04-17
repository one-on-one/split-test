<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Split Tests</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Split Testing with Grails">
    <meta name="author" content="One on One Marketing, Inc">

    <!-- Le styles -->
    <link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">
    <style type="text/css">
    body {
        padding-top: 60px;
        padding-bottom: 40px;
    }

    table#report th {
        text-align: center;
    }

    tr.first td {
        border-top: 0px;
    }

    tr.champion td {
        background-color: #ffffaa;
        text-align: center;
    }

    tr.contender td {
        text-align: center;
    }

    .date-fields select {
        width: 80px !important;
    }

    sup.tooltip {
        font-size: 9px;
    }

    .sidebar-nav {
        padding: 9px 0;
    }

    .instructions h3 {
        padding-bottom: 5px;
    }
    </style>
    <link href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}" rel="stylesheet">

    <script src="${resource(dir: 'js', file: 'jquery-1.7.2.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
    <script src="${resource(dir: 'js', file: 'bootstrap-tooltip.js')}"></script>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">AB Testing</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li <g:if test="${actionName != 'instructions'}">class="active"</g:if>><g:link
                            controller="splitTest" action="list">Split Tests</g:link></li>
                    <li <g:if test="${actionName == 'instructions'}">class="active"</g:if>><g:link
                            controller="splitTest" action="instructions">Usage</g:link></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <g:layoutBody/>
    </div>
</div>

<r:layoutResources/>

<script type="text/javascript">
    $(function () {
        $('sup a').tooltip({});
    })
</script>
</body>
</html>