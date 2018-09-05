<html>
  <head>
    <meta name="layout" content="main">
    <title>Welcome</title>
  </head>
  <body id="Welcome">
    <g:img dir="images" file="pets.png" align="right" style="position:relative;right:30px;"/>
    <h2><g:message code="Welcome"/></h2>

    <h3>${add}</h3>
    <h3>${petname}</h3>
  <h3>${petclass}</h3>

    <ul>
      <li><g:link controller="owner" action="find">Find owner</g:link></li>
      <li><g:link action="vets">Display all veterinarians</g:link></li>
      <li><g:link action="tutorial">Tutorial</g:link></li>
    </ul>
  </body>
</html>
