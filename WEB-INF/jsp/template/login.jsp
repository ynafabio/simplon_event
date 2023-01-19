
<div class="login-box">
  <!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="#" class="h1"><b>SIMPLON</b></a>
    </div>
    <div class="card-body">
      <p class="login-box-msg">Identifiez-vous</p>

      <form method="post" action="<c:url value="/login"/>">
      <div class="text-center mt-2 mb-3"><span class="error"><c:out value="${bad_login}"/></span>
		<c:if test="${empty sessionScope.sessionAdmin && !empty requestScope.derniereConnexion}">
		<b>Derni&egrave;re connexion : ${requestScope.derniereConnexion}</b></c:if>
		</div>
      
        <div class="input-group mb-3">
          <!-- <input type="email" class="form-control" placeholder="Email"> -->
          <input type="text" class="form-control" placeholder="identifiant" name="identifiant" value="${requestScope.mailDerniereConnexion}<c:out value="${param.identifiant}"/>" required="required"/>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <!-- <input type="password" class="form-control" placeholder="Password"> -->
          <input type="password" class="form-control" placeholder="Mot de passe" name="passWd" value="<c:out value="${param.passWd}"/>" required="required"/>
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <!-- <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Remember Me
              </label>
            </div> -->
          </div>
          <!-- /.col -->
          <div class="col-12">
            <button type="submit" class="btn btn-primary btn-block">Valider</button>
          </div>
          <!-- /.col -->
        </div>
      </form>

      <div class="social-auth-links text-left mt-2 mb-3">
        <!-- <p class="mb-1">
        <a href="forgot-password.html">Mot de passe oublié</a>
      </p> -->
      </div>
      <!-- /.social-auth-links -->

      <%-- <p class="mb-1">
        <a href="forgot-password.html">Mot de passe oublié</a>
      </p>
      <p class="mb-0">
        <a href="<c:url value="public/index?p=inscription"/>" class="text-center">S'inscrire</a>
      </p> --%>
    </div>
    <!-- /.card-body -->
  </div>
  <!-- /.card -->
</div>
<!-- /.login-box -->
