<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Split Test</title>
    <!-- Le styles -->
    <link href="${resource(dir: 'css', file: 'bootstrap.min.css', plugin: 'split-test')}" rel="stylesheet">

    <style>
    input {
        height: 27px;
    }

    form {
        width: 375px;
        margin: 50px auto 20px;
    }

    #instructions {
        font-size: 11px;
        width: 375px;
        margin: 0 auto;
        color: #999;
    }
    </style>
</head>

<body>

<g:form action="authenticate" class="well form-inline">
    <g:if test="${flash.message}">
        <div class="alert alert-info">${flash.message}</div>
    </g:if>
    <input name="username" type="text" class="input-medium" placeholder="Username" autofocus="autofocus">
    <input name="password" type="password" class="input-medium" placeholder="Password">
    <button type="submit" class="btn">Sign in</button>
</g:form>

<div id="instructions">
    The username and password are set in Config.groovy. See <a
        href="http://one-on-one.github.com/split-test/">documentation</a>.
</div>

</body>
</html>