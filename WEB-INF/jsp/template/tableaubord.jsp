<div class="content-wrapper" style="min-height: 766px;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Tableaux de bord</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Accueil</a></li>
              <li class="breadcrumb-item active">Tableau de bord</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
        <c:set var="soldecaisse" value="0"/>
        
        <c:forEach var="bean" items="${listStatTo}" varStatus="status">
        <div class="col-lg-4 col-6">
            <!-- small box -->
            <div class="small-box <c:if test="${status.index mod 2 == 0 }">bg-success</c:if><c:if test="${status.index mod 2 != 0 }">bg-warning</c:if>">
              <div class="inner">
              	<div class="form-group col-md-12">
              	<span >Nombre</span>
              	<span class="float-right">Montant</span>
              	</div>
              	<div class="form-group col-md-12">
              	<h2><span class="float-left">&nbsp;<c:out value="${bean.nbreTo}"/></span></h2>
              	<h2><span class="float-right">&nbsp;<fmt:formatNumber pattern="###,###" value="${bean.totMontantTo}"/></span></h2>
              	</div>
              	
				<div class="row form-group col-md-12">
                   <h3><c:out value="${bean.libTo}"/></h3>
                </div>
              </div>
              <div class="icon">
                <i class="ion ion-bag"></i>
              </div>
              <a href="#" class="small-box-footer">Plus d'information <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <c:set var="soldecaisse" value="${soldecaisse + bean.totMontantTo}"/>
        </c:forEach>
        
        <%-- <div class="col-lg-3 col-6">
            <div class="small-box bg-danger">
              <div class="inner">
                <h3><fmt:formatNumber pattern="###,###" value="${soldecaisse}"/></h3>

                <p>Caisse</p>
              </div>
              <div class="icon">
                <i class="ion ion-pie-graph"></i>
              </div>
              <a href="#" class="small-box-footer">Plus d'information <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div> --%>
          
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>