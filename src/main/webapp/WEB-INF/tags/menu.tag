<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

<!-- ************* MENU PARA CUALQUIER USUARIO ************* -->
				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="Página de inicio">
					<span class="glyphicon glyphicon-film" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'owners'}" url="/eventos"
					title="Página de eventos">
					<span class="bi bi-camera-reels" aria-hidden="true"></span>
					<span> Eventos</span>
				</petclinic:menuItem>


<!-- ************* MENU PARA ADMIN ************* -->
			<sec:authorize access='hasAnyAuthority("admin")'>
					<petclinic:menuItem active="${name eq 'owners'}" url="/peticion"
						title="Página de gestión de peticiones">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>G. Peticiones</span>
					</petclinic:menuItem>
			</sec:authorize>
			<sec:authorize access='hasAnyAuthority("admin")'>
					<petclinic:menuItem active="${name eq 'owners'}" url="/usuarios"
						title="Página de gestión de usuarios">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>G. Usuarios</span>
					</petclinic:menuItem>
			</sec:authorize>
			<sec:authorize access='hasAnyAuthority("admin")'>
					<petclinic:menuItem active="${name eq 'owners'}" url="/lugaresRealizacion"
						title="Página de gestión de lugares de realizacion">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>G. Lugares de Realización</span>
					</petclinic:menuItem>
			</sec:authorize>



			
			<!-- ************* MENU PARA ORGANIZACION ************* -->
						<sec:authorize access='hasAnyAuthority("organizacion")'>
					<petclinic:menuItem active="${name eq 'owners'}" url="/eventos/nuevo"
						title="Página de creacion de eventos">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>Crear Evento</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'owners'}" url="/consultas/organizacion/misConsultas"
						title="Página de consultas de clientes">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>Mis Consultas</span>
					</petclinic:menuItem>
			</sec:authorize>

				


			</ul>



			<!--Menu sin iniciar sesión-->
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/clientes/new" />">Registrarme</a></li>
					<li><a href="<c:url value="/peticion/new" />">Registro organizacion</a></li>
				</sec:authorize>
				<sec:authorize access='hasAnyAuthority("cliente")'>
					<li><a href="<c:url value="/carrito/cliente" />"><span
					class="glyphicon glyphicon-shopping-cart"></span> Mi cesta</a></li>
					<petclinic:menuItem active="${name eq 'owners'}" url="/consultas/cliente/misConsultas"
						title="Página de consultas de clientes">
						<span class="bi bi-camera-reels" aria-hidden="true"></span>
						<span>Mis Consultas</span>
					</petclinic:menuItem>
				</sec:authorize>
				<sec:authorize access='hasAnyAuthority("cliente")'>
					<li><a href="<c:url value="/usuarios/myprofile/eventosFavoritos" />"><i class="glyphicon glyphicon-heart"></i> Favoritos</a></li>
				</sec:authorize>
				<sec:authorize access='hasAnyAuthority("organizacion")'>
					<li><a href="<c:url value="/carrito/organizacion" />"><i class="glyphicon glyphicon-shopping-cart"></i>Mi cesta</a></li>
				</sec:authorize>
				<sec:authorize access='hasAnyAuthority("organizacion", "cliente")'>
					<li><a href="<c:url value="/facturas" />"><i class="glyphicon glyphicon-folder-open"></i>Facturas</a></li>
				</sec:authorize>

				
				<!-- ****************** Menu como cliente  ****************** -->
				<sec:authorize access='hasAnyAuthority("cliente, organizacion")'>
				
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
											<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
											<p>
											<a href="<c:url value="/usuarios/myprofile" />"
													class="btn btn-primary btn-block btn-sm">Mi perfil</a>
											</p>
										</div>
									</div>
								</div>
							</li>

						</ul></li>
				</sec:authorize>


				<!-- ****************** Menu como administrador  ****************** -->
				<sec:authorize access='hasAnyAuthority("admin")'>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">

										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							
						</ul></li>
				</sec:authorize>

			</ul>
		</div>
	</div>
</nav>
