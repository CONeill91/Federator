<!DOCTYPE html>
<html lang="en" ng-app="FederatorApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="msaccotelli">
    <title>Federator</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.1.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='resources/customcss/customcss.css'>  <!-- Custom stylesheet override -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <script src="webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/directives.js"></script>
    <script src="resources/js/controllers.js"></script>
    <script src="resources/js/filters.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

     <script>
          $(function() {
            $( document ).tooltip();
          });
     </script>



     <link rel="shortcut icon" href="resources/images/favicon.ico" /> <!-- Favicon link to file -->

</head>

<body data-ng-controller="SearchController">

<!-- Header -->

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header" style="text-align: center">
      <img src="resources/images/rainbow-bar.png">
      <!--<a class="navbar-brand" href="index.jsp">
        <!--<img src="resources/images/guidewire-logo.png"> -->
      </a>
              <h1>Guidewire Federator</h1>
    </div>

    <div class="container-fluid">

        <br>

        <div class="row">
            <div class="col-md-12">

                <br>

                <form data-ng-submit="doParallelSearch()">

                   <div id="searchbar">
                       <input id="textbox" size="110" type="text" data-ng-model="query" placeholder="Search.." data-auto-focus style="padding-left: 10px">
                       <button id="searchbutton" type="submit" class="btn btn-primary" data-ng-disabled="!query || inflight || search.searchIn.length == 0"><span class="glyphicon glyphicon-search"></span>Search</button>
                       <button id="addcontent" type="submit" class="btn btn-primary">Add Content</button>
                   </div>
                    <br>
                    <div id="optionsbar">
                       <label ng-repeat="searchLocation in searchLocations">
                        <input type="checkbox" checklist-model="search.searchIn" checklist-value="searchLocation">
                        {{searchLocation}}
                       </label>
                    <input class="btn btn-xs custom80" type="button" ng-click="checkAll()" value="Select All">
                    <input class="btn btn-xs custom80" type="button" ng-click="uncheckAll()" value="Clear All">
                    </div>
                </form>

                <br>
                <br>
            </div>
        </div>
  </div>
</nav>

<!-- End Header -->

     <div class="tabs" ng-show="results.length != 0" style="clear:both;">
         <ul class ="nav nav-tabs">
            <li>
                <a href ng-click="activateTab()">All ({{results.length}})</a>
            </li>
            <li ng-repeat="searchLocation in searchLocations">
                <a href ng-click="activateTab(searchLocation)" ng-show="countResultsForSource(results, searchLocation) != 0"> {{searchLocation}} ({{countResultsForSource(results, searchLocation)}})</a>
            </li>
         </ul>
     </div>

    <div class="row">
        <div class="col-md-12">
            <span data-ng-show="!inflight && searched && results.length == 0">No results</span>
            <i data-ng-show="inflight && results.length == 0">Please wait...</i>

            <div id="results" data-ng-show="results.length > 0">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th >Result  {{inflight ? 'In progress..' : ''}}</th>

                        <th ng-show="activeTab !== 'Wiki' && activeTab !== 'Confluence' && activeTab !== 'Jira' && activeTab !== 'Mail:PL' && activeTab !== 'Mail:BC' && activeTab !== 'Mail:CC' && activeTab !== 'Mail:PC' && activeTab !== 'Intranet' && activeTab !== 'Sharepoint'&& activeTab !== 'Elastic Search'">Source</th>
                    </tr>
                    </thead>
                    <tbody>
                   <tr data-ng-repeat="result in results | filter: trimMail | filter: filterResults | orderBy: ['source', 'title']" >
                      <td>


                          <!--Dannys Function: upon link click the link and query are sent to function which sends to server -->
                          <b><a ng-click=' storeLink(result.href, query);' ng-bind-html = "result.title | highlight:query"
                                href="{{result.href}}"
                                rel = "nofollow"
                                class="result"
                                title="{{result.title}} - {{result.content}}">{{result.title}}</a></b><br/>
                        <p ng-bind-html="result.content | highlight:query" >{{result.content }}</p>
                      </td>
                      <td ng-show="activeTab !== 'Wiki' && activeTab !== 'Confluence' && activeTab !== 'Jira' && activeTab !== 'Mail:PL' && activeTab !== 'Mail:BC' && activeTab !== 'Mail:CC' && activeTab !== 'Mail:PC' && activeTab !== 'Intranet' && activeTab !== 'Sharepoint'">{{result.source}}</td>
                    </tbody>
                </table>
                <br>
                <hr/>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="navbar navbar-default navbar-fixed-bottom">
      <br>
      <div align="center" class="container">
        <a style="padding-left: 11px" href="http://home.guidewire.com/">Home</a>
        <a style="padding-left: 10px" href="https://confluence.guidewire.com/">Confluence</a>
        <a style="padding-left: 10px" href="https://jira.guidewire.com">Jira</a>
        <a style="padding-left: 10px" href="http://mailman.guidewire.com/mailman/listinfo">Mailing List</a>
        <a style="padding-left: 10px" href="https://our.guidewire.com/pages/intranet.aspx">Intranet</a>
        <a style="padding-left: 10px" href="http://wiki.guidewire.com">Wiki</a>
        <a style="padding-left: 10px" href="http://wd5.myworkday.com/guidewire/login.flex">Workday</a>
        <a style="padding-left: 10px" href="mailto:msaccotelli@guidewire.com?Subject=Federator:%20Support%20Issue" target="_top"><span class="glyphicons glyphicons-circle-question-mark"></span>Support</a> <!-- Support Mail Link -->
      </div>
      <img src="resources/images/rainbow-bar.png">
    </footer>
</body>
</html>
