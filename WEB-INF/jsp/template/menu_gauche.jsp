<!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <!-- <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> -->
      <span class="brand-text font-weight-light">SIMPLON</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">
			<c:if test="${!empty sessionScope.sessionAdmin}">Administrateur</c:if>
           <c:if test="${!empty sessionScope.sessionClient}"> <c:out value="${sessionScope.sessionClient.nomClient}"/>&nbsp;<c:out value="${sessionScope.sessionClient.prenomClient}"/></c:if>
		</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
     <!--  <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div> -->

 <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <!-- <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Tableaux de bord
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tableau de bord v1</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tableau de bord v2</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tableau de bord v3</p>
                </a>
              </li>
            </ul>
          </li> -->
          
  <%-- <c:if test="${!empty sessionScope.sessionAdmin
     || (!empty sessionScope.sessionEmploye && sessionScope.sessionEmploye.codeGutilisateur=='G1')}"> --%>
      <c:if test="${!empty sessionScope.sessionAdmin}">
      <li class="nav-item">
            <a href="#" class="nav-link active">
              <i class="nav-icon fa fa-cog"></i>
              <p>
                Evènements
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
            
              <li class="nav-item">
                <a href="<c:url value="/admin/event?action=list"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Participants</p>
                </a>
              </li>              
			  
            </ul>
          </li>
      
          
          
          
          
  		</c:if>
  		
    <c:if test="${!empty sessionScope.sessionClient && sessionScope.sessionClient.idTc==2}">
    <li class="nav-item">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Sous-distributeur
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<c:url value="/admin/user"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Utilisateur</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="<c:url value="/admin/pointdevente"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Point de vente</p>
                </a>
              </li>             
            </ul>
          </li>
	</c:if>
	<c:if test="${!empty sessionScope.sessionClient && sessionScope.sessionClient.idTc==3}">
          <li class="nav-item">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Indépendant
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
             
              <li class="nav-item">
                <a href="<c:url value="/admin/user"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Utilisateur</p>
                </a>
              </li> 
              <li class="nav-item">
                <a href="<c:url value="/admin/pointdevente"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Point de vente</p>
                </a>
              </li>
            </ul>
          </li> 
       </c:if>   
       <c:if test="${!empty sessionScope.sessionClient && sessionScope.sessionClient.idTc==4}">
            <li class="nav-item">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-th"></i>
              <p>
               PDV
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<c:url value="/admin/operation"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Enregistrer une opération</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<c:url value="/admin/caisse"/>" class="nav-link"><i class="nav-icon fas fa-edit"></i>
                  <p>Fond de caisse</p>
                </a>
              </li>
            </ul>
          </li> 
          <li class="nav-item">
                <a href="#" class="nav-link active">
                  <i class="nav-icon fas fa-copy"></i>
                  <p>
                    Reporting
                    <i class="right fas fa-fa-file"></i>
                  </p>
                </a>
                <ul class="nav nav-treeview">
                  <li class="nav-item">
                    <a href="<c:url value="/admin/mouvementcaisse"/>" class="nav-link">
                      <i class="far fa-file nav-icon"></i>
                      <p> Mouvements de caisse</p>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a href="<c:url value="/admin/cumuloperation"/>" class="nav-link">
                      <i class="far fa-dot-circle nav-icon"></i>
                      <p> Cumul des opérations</p>
                    </a>
                  </li>
                </ul>
              </li>
         </c:if>
        </ul> 
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>