  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Liste des participants aux évènements</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<c:url value="/admin/login"/>">Accueil</a></li>
              <li class="breadcrumb-item"><a href="<c:url value="/admin/event?action=edit"/>">Edit</a></li>
              <li class="breadcrumb-item active">Participant</li>
            </ol>
          </div>
          
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                Critère de recherche
                <span class="float-right">
                <a class="btn  btn-primary btn-flat" href="<c:url value="/admin/event?action=edit"/>"> 
                <i class="fa fa-plus"></i> Participant </a></span>
              </div>
              <!-- /.card-header -->
              
              <div class="card-body">
              
              <form action="<c:url value="/admin/event?action=list"/>" method="post">
              
              <div class="col-sm-12">
              <div class="row">
				
			  	<div class="col-md-2">
			  	<label>Période du</label>
			  		<div class="input-group date" id="reservationdate1" data-target-input="nearest" >
	                    <input type="text" class="form-control datetimepicker-input" data-target="#reservationdate1" name="date1" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.date1}"/>">
	                      <div class="input-group-append" data-target="#reservationdate1" data-toggle="datetimepicker" >
	                         <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	                   </div>
	             </div>		
			  	</div>
			  	<div class="col-md-2">
			  	<label>au</label>
				<div class="input-group date" id="reservationdate2" data-target-input="nearest" >
	                    <input type="text" class="form-control datetimepicker-input" data-target="#reservationdate2" name="date2" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.date2}"/>">
	                      <div class="input-group-append" data-target="#reservationdate2" data-toggle="datetimepicker" >
	                         <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	                   </div>
	             </div>
       			</div>
       			<div class="col-md-4">
       			<label>Evènements</label>
				<select name="idEvenement" class="form-control select2 select2-primary select2-hidden-accessible" data-dropdown-css-class="select2-primary" style="width: 100%;" tabindex="-1" aria-hidden="true">
	                    <option selected="selected" ></option>
	                   <c:forEach items="${listEvent}" var="beanE" varStatus="status">
	                    	<option <c:if test="${bean.idEvenement == beanE.idEvenement}">selected="selected"</c:if>
	                    	<c:if test="${param.idEvenement == beanE.idEvenement}">selected="selected"</c:if> 
	                    	value="${beanE.idEvenement}"><c:out value="${beanE.libEvenement}"/></option>
	 					</c:forEach>
	                  </select>
	            </div>
	            <div class="col-md-1">
	            	<label>&nbsp;</label>
	            	<p><input type="submit" name="search_event" value="Rechercher" class="btn btn-warning"></p>
	            </div>
	            <div class="col-md-1">
	            	<label>&nbsp;</label>
	            	<a class="btn btn-block btn-default btn-flat" href="<c:url value="/admin/event?action=list"/>">Retour</a>
		        </div>
	          </div>
             </div> 
             </form> 
              
              <c:if test="${empty list && !empty bean}">
              <h3><span class="badge bg-warning">Aucune donnée trouvée !</span></h3>
              </c:if>
              </div>
              <!-- /.card-body -->
              
            </div>
            <!-- /.card -->
            <c:if test="${!empty list }">
         <div class="row">   
         <div class="col-md-12">
          
          <!-- /.card -->
          <div class="card card-info">
            <div class="card-header">
              <h3 class="card-title">Liste des participants</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
            <c:if test="${!empty urlEtatListeParticipant}">
			   <a target="_blank" href="<c:url value="${urlEtatListeParticipant}"/>" class="btn btn-danger"><img src="<c:url value="/img/pdf.png"/>"/>PDF</a>
			</c:if>
              <table id="example3" class="table table-bordered table-hover" >
		                <thead>
		                <tr>
		                <!-- <th class="text-center">N°</th>
		                 <th class="text-center">Date</th> -->
		                 <th>NOM</th>
		                 <th>PRENOMS</th>
		                 <th class="text-center">TELEPHONE</th>
		                 <th class="text-center">EMAIL</th>
		                 <th class="text-center">ACTION</th> 
		                </tr>
		                </thead>
		                <tbody>
		                
		                <c:set var="stot" value="0"/>
		                <c:set var="tot" value="0"/>
		                <c:set var="idEv" value="0"/>
		                
		                  <c:forEach items="${list}" var="bean" varStatus="status"> 
		                  <c:if test="${bean.idEvenement != idEv}">
		                  
		                  <tr class="table-primary">
		                  <td colspan="4">
		                 	<p><b>Date : <fmt:formatDate pattern="dd/MM/yyyy" value="${bean.dateEvenement}"/></b></p>
		                 	<p><b>Evènement : <c:out value="${bean.libEvenement}"/></b></p>
		                  </td>
		                  <td class="project-actions text-center">
		                 <%--  <a class="btn btn-info btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&action=edit">
		                              <i class="fas fa-pencel-alt">
		                              </i>
		                              
		                          </a> --%>
		                          <a class="btn btn-danger btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&action=del" onClick="return confirm('Cette action va supprimer le participant. Etes-vous sur de vouloir continuer ?')">
		                              <i class="fas fa-trash">
		                              </i>
		                          </a></td>
		                  </tr>
		                 
		                  </c:if>
		                  
						<tr>
							
								<td><c:out value="${bean.nomParticipant}"/></td>
								<td align="left"><c:out value="${bean.prenomParticipant}"/></td>
					        	<td align="left"><c:out value="${bean.telParticipant}"/></td> 
					        	<td align="left" ><c:out value="${bean.emailParticipant}"/></td>
					        	
								<td class="project-actions text-center"><a class="btn btn-info btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&idpart=<c:out value="${bean.idParticipant}"/>&action=edit">
		                              <i class="fas fa-pencil-alt">
		                              </i>
		                              
		                          </a>
		                          <a class="btn btn-danger btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&idpart=<c:out value="${bean.idParticipant}"/>&action=del" onClick="return confirm('Cette action va supprimer le participant. Etes-vous sur de vouloir continuer ?')">
		                              <i class="fas fa-trash">
		                              </i>
		                          </a></td>
						</tr>
						
						
						 <c:set var="idEv" value="${bean.idEvenement}"/>
					</c:forEach>
		             </tbody>
		               
		            </table>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        </div>
        </c:if>
        
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
