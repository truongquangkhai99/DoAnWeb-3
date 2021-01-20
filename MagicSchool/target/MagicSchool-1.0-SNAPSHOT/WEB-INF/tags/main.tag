<%@tag pageEncoding="utf-8" %>
<%@attribute name="css" fragment="true" required="false" %>
<%@attribute name="js" fragment="true" required="false" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/rating.css" >
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/owlcarousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/owlcarousel/assets/owl.theme.default.min.css">
    <title>Title</title>
    <jsp:invoke fragment="css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</head>
<body>
    <jsp:doBody/>
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.0.0-beta1/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/font-awesome/5.15.1/js/all.js"></script>
    <jsp:invoke fragment="js"/>
    <script>
        $(document).ready(function() {
            $('.owl-carousel').owlCarousel({
                loop: true,
                margin: 10,
                nav: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 4
                    }
                }
            })
        })


    </script>
    <script src="${pageContext.request.contextPath}/public/owlcarousel/owl.carousel.min.js"></script>
</body>
</html>