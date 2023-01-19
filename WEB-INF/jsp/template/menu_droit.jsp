 <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Espace utilisateur</h5>
      
      <c:if test="${!empty sessionScope.sessionAdmin}">Administrateur</c:if>
         <c:if test="${!empty sessionScope.sessionClient}"> 
            <a href="<c:url value="/admin/user?id="/><c:out value="${sessionScope.sessionClient.idClient}"/>"><c:out value="${sessionScope.sessionClient.nomClient}"/>&nbsp;<c:out value="${sessionScope.sessionClient.prenomClient}"/></a></c:if>
		      <%-- <c:if test="${!empty sessionScope.sessionClient}"> 
		         <p><a href="<c:url value="/admin/user?id="/><c:out value="${sessionScope.sessionClient.idClient}"/>">Administrateur</a></p>
		         <p>Groupe : <c:out value="${sessionScope.sessionClient.codeGutilisateur}"/></p>
		         <p>Rôle : <c:out value="${sessionScope.sessionClient.codeRole}"/></p>
		</c:if> --%>
      <p><a href="<c:url value="login?p=deconnexion"/>">Déconnectez-vous</a></p>
    </div>
  </aside>
  <!-- /.control-sidebar -->