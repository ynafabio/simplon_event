<script type="text/javascript">
function SuppOuiNon(monLien) {
  if (confirm("Voulez-vous vraiment supprimer la ligne?")) {
    //alert("oui")
	  document.location.href=monLien;
  }
}
</script>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Enregistrer des participants à un évènement</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item ">Accueil</li>
              <li class="breadcrumb-item"><a href="<c:url value="/admin/event?action=list"/>">List</a></li>
              <li class="breadcrumb-item active">Participant</li>
              
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Informations</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="card-body">
            <form action="<c:url value="/admin/event"/>" method="post">
				<input type="hidden" name="idEvenement" value="<c:out value="${bean.idEvenement}"/>"/>
				<input type="hidden" name="idParticipant" value="<c:out value="${bean.idParticipant}"/>"/>
				
				<div class="row">
				<div class="col-md-2">
				<div class="input-group date" id="reservationdate1" data-target-input="nearest" >
	                    <input type="text" class="form-control datetimepicker-input" data-target="#reservationdate1" name="dateEvenement"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.dateEvenement}"/>" readonly="readonly">
	                      <div class="input-group-append" data-target="#reservationdate1" data-toggle="datetimepicker" >
	                         <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	                   </div>
	             </div>
				</div>
				<div class="col-md-8">
				<input  class="form-control" type="text" name="libEvenement" maxlength="50" placeholder="Libellé de l'évènement" value="<c:out value="${bean.libEvenement}"/>" required="required" />
				</div>
				</div>
				<br/>
				<div class="row">
				<div class="col-md-2">
				<input  class="form-control" type="text" name="nomParticipant" maxlength="50" placeholder="Nom" value="<c:out value="${bean.nomParticipant}"/>" required="required" />
				</div>
			  	<div class="col-md-3">
			  		<input  class="form-control" type="text" name="prenomParticipant" maxlength="250" placeholder="Prénoms" value="<c:out value="${bean.prenomParticipant}"/>" required="required" />		
			  	</div>
			  	<div class="col-md-2">
				<input  class="form-control" type="text" name="telParticipant" maxlength="20" placeholder="Téléphone" value="<c:out value="${bean.telParticipant}"/>" required="required" />
				</div>
				
       			<div class="col-md-3">
				<input  class="form-control" type="email" name="emailParticipant" min="0" placeholder="E-mail" value="<c:out value="${bean.emailParticipant}"/>" required="required" />
	            </div>
	            
				<div class="col-md-1">
				<c:if test="${!empty param.idpart}">
				<input type="submit" class="btn btn-block btn-warning btn-flat" value="Modifier">
				</c:if>
				<c:if test="${empty param.idpart}">
				<input type="submit" class="btn btn-block btn-primary btn-flat" value="Enregistrer">
				</c:if>
				</div>
				
				 <div class="col-md-1">
	            <a class="btn btn-block btn-success btn-flat" href="<c:url value="/admin/event?action=edit"/>">Nouveau</a>
		        </div>	
		   </div>
                                
              </form>           
              
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </div>
        
        <c:if test="${!empty bean && empty list}">
			<h3><span ><i>Aucun participant enregistré ! </i></span></h3>
		</c:if>
		
		<c:if test="${!empty list}">	
        <div class="col-md-12">
        <div class="card card-primary">
            <div class="card-header">
              <h3 class="card-title">Liste des participants</h3>

              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
            </div>
            
        <div class="card-body">
        			
                 <table id="example3" class="table table-bordered table-hover">
		           	<thead>
		                <tr>
							<th class="text-left" >Nom</th>
							<th class="text-left" >Prenoms</th>
							<th class="text-center" >Téléphone</th>
							<th class="text-center" >Email</th>
							<th class="text-center " width="15%">Action</th>
		                </tr>
		            </thead>
		        	<tbody>
		        	
		        	<c:forEach items="${list}" var="bean" varStatus="status">
			        	<tr>
			        	<td><c:out value="${bean.nomParticipant}"/></td>
			        	<td><c:out value="${bean.prenomParticipant}"/></td>
			        	<td><c:out value="${bean.telParticipant}"/></td>
			        	<td><c:out value="${bean.emailParticipant}"/></td>
			        	
			        	<td class="project-actions text-center"><a class="btn btn-info btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&idpart=<c:out value="${bean.idParticipant}"/>&action=edit">
		                              <i class="fas fa-pencil-alt">
		                              </i>
		                              
		                          </a>
		                          <a class="btn btn-danger btn-sm" href="<c:url value="/admin/event?id="/><c:out value="${bean.idEvenement}"/>&idpart=<c:out value="${bean.idParticipant}"/>&action=del" onClick="return confirm('Cette action va supprimer le participant. Etes-vous sur de vouloir continuer ?')">
		                              <i class="fas fa-trash">
		                              </i>
		                          </a></td>			        	
			        	</tr>
			        </c:forEach>
		        	</tbody>
		       </table>
		      
		     </div>
		     </div>
        </div>
         </c:if>
      </div>
     
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 