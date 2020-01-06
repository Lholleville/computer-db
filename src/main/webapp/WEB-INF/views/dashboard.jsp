    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                <c:out value="${nbComputers}" /> Computers found 
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="http://localhost:8080/computer-database/computer/create">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                  <c:forEach items="${computers}" var="computer">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="0">
                        </td>
                        <td>
                            <a href="editComputer.html" onclick="">${computer.getName()}  ( ${computer.getId() } ) </a>
                        </td>
                        <td>${computer.getIntroductionDate()}</td>
                        <td>${computer.getDiscontinuedDate()}</td>
                        <td>${computer.getCompany().getName()}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
              <li>
                  <a href="#" aria-label="Previous" role="button" id="previous">
                     <span aria-hidden="true">&laquo;</span> 	
                  </a>
              </li>
              <c:if test="${nbPages  <= 5}">
	              <c:forEach var="i" begin="1" end="${nbPages}" step="1">
	              	<li><a href="#" class="page" role="button" id="<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
	              </c:forEach>
              </c:if>
              <c:if test="${nbPages  > 5}">
              	<li><a href="#" class="page" role="button" id="1"><c:out value="1"/></a></li>
              	<li><a href="#" role="button">...</a></li>
              	<c:forEach var="i" begin="${nbPages - 9}" end="${nbPages}" step="1">
	            	<li><a href="#" class="page" role="button" id="<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
	            </c:forEach>
              </c:if>
              <li>
                <a href="#" aria-label="Next" role="button" id="next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
           </ul>

	       <div class="btn-group btn-group-sm pull-right" role="group" >
	           <button type="button" class="btn btn-default" id="show10">10</button>
	           <button type="button" class="btn btn-default" id="show50">50</button>
	           <button type="button" class="btn btn-default" id="show100">100</button>
	       </div>
		</div>
    </footer>	
<script><%@ include file ="../js/jquery.min.js"%></script>
<script><%@ include file ="../js/bootstrap.min.js"%></script>
<script><%@ include file ="../js/dashboard.js"%></script>

</body>
</html>