<%@ page import="model.Post" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Blog M06"/>
    <meta name="author" content="tcp.id"/>
    <title>Blog TCP M06 UF4</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
          rel="stylesheet" type="text/css"/>
    <link href="css/styles.css" rel="stylesheet"/>

    <%
        ArrayList<Post> postArrayUserList = (ArrayList<Post>) request.getAttribute("listUserPost");
    %>

</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="index1.jsp">M06 UF4</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="jsp/home.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="index.jsp">Log Out</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<header class="masthead" style="background-image: url('/assets/img/about-bg.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>Edita</h1>
                    <span class="subheading">Gestiona tus Posts</span>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">

                <div class="form-outline form-white mb-4">
                    <form action="edit.do" method="post">
                    <button class="nav-link px-lg-3 py-3 py-lg-4"  name= "delete" type="submit">BORRAR</button>
                    </form>

                    <form action="edit.do" method="post">
                    <input type="text" name="titulo" class="form-control form-control-lg"/>
                    <label class="form-label">Titulo: </label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="text" name="mns" class="form-control form-control-lg"/>
                    <label class="form-label">Mensaje: </label>
                </div>
                <div class="form-outline form-white mb-4">
                    <input type="text" name="img" class="form-control form-control-lg"/>
                    <label class="form-label">Imagen URL: </label>
                </div>
                <div class="container position-relative px-4 px-lg-5">
                    <div class="row gx-4 gx-lg-5 justify-content-center">
                        <form action="edit.do" method="post">
                        <button class="nav-link px-lg-3 py-3 py-lg-4"  name= "new" type="submit">Postea!!!</button>
                        </form>
                    </div>
                </div>
            </form>

            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                <a href="http://www.gitlab.com" class="text-white"><i class="fab fa-github-f fa-lg"></i></a>
            </div>
        </div>
    </div>
</div>


<div class="container position-relative px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
            <%for (Post p : postArrayUserList) {%>
            <div class="post-heading">
                <input hidden class="form-control" type="text" name="id" value="<%=p.getId()%>"/>
                <h1><input class="form-control" type="text" name="titulo" value="<%=p.getTitle()%>"/></h1>
                <h2> Posteado por: <input class="form-control" type="text" name="usuario"
                                          value="<%=p.getUsuari().getUsuari()%>"/></h2>
                <span class="meta">
                                <p>Fecha: <input class="form-control" type="text" name="date" value="<%=p.getDate()%>"/></p>
                                <p>Likes: <input class="form-control" type="text" name="likes" value="<%=p.getLikes()%>"/></p>
                            </span>
            </div>
        </div>
    </div>
</div>
<!-- Post Content-->
<article class="mb-4">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <p><input class="form-control" type="text" name="mensaje" value="<%=p.getMessage()%>"/></p>
                <input class="form-control form-control-sm" type="file" name="image">
                <img class="cover center" src="data:image/png;base64,<%=p.getImage()%>" height="400">
                <img class="cover center" src="data:image/png;base64,<%=p.getImage()%>" height="400">
                <p>&middot;Image by :</p>
                <input class="form-control" type="text" name="image" value="<%=p.getImage()%>"/>
                <%}%>
            </div>
        </div>
    </div>
</article>
<!-- Footer-->
<footer class="border-top">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="git@github.com:ToniCarpa/servlet.git">
                                    <span class="fa-stack fa-lg">
                                        <i class="fas fa-circle fa-stack-2x"></i>
                                        <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                                    </span>
                        </a>
                    </li>
                </ul>
                <div class="small text-center text-muted fst-italic">Copyright &copy; TCP 2023</div>
            </div>
        </div>
    </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
