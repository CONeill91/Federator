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
    <link rel='stylesheet' href='resources/customcss/customcss.css'>

    <script src="webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/directives.js"></script>
    <script src="resources/js/controllers.js"></script>
    <script src="resources/js/filters.js"></script>
    <link rel="shortcut icon" href="resources/images/favicon.ico" />
</head>

<body data-ng-controller="SearchController">

    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <img src="resources/images/rainbow-bar.png">
          <a class="navbar-brand" href="index.jsp">
            <img src="resources/images/guidewire-logo.png">
          </a>
                  <h1>Federator</h1>
        </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">

                <form data-ng-submit="doParallelSearch()">

                    <button id="searchbutton" type="submit" class="btn btn-primary" data-ng-disabled="!query || inflight || search.searchIn.length == 0"><span class="glyphicon glyphicon-search"></span> Search</button>

                    <input type="text" data-ng-model="query" placeholder="Search" data-auto-focus>

                    <a style="padding-right: 75px"></a>

                    <label ng-repeat="searchLocation in searchLocations">
                        <input type="checkbox" checklist-model="search.searchIn" checklist-value="searchLocation">
                        {{searchLocation}}
                    </label>
                    <input class="btn btn-primary custom80" type="button" ng-click="checkAll()" value="Select All">
                    <input class="btn btn-primary custom80" type="button" ng-click="uncheckAll()" value="Clear All">

                    <a id="support"  href="mailto:msaccotelli@guidewire.com?Subject=Federator:%20Support%20Issue" target="_top"><span class="glyphicons glyphicons-circle-question-mark"></span>Support</a> <!-- Support Mail Link -->

                </form>

                <br>
            </div>
        </div>
  </div>
</nav>

     <div class="tabs" ng-show="results.length != 0" style="clear:both;">
         <ul class ="nav nav-tabs">
            <li>
                <a href ng-click="activateTab()">All</a>
            </li>
            <li ng-repeat="searchLocation in searchLocations">
                <a href ng-click="activateTab(searchLocation)">{{searchLocation}} ({{countResultsForSource(results, searchLocation)}})</a>
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
                        <th>Result ({{results.length}}) {{inflight ? 'In progress..' : ''}}</th>
                        <th>Source</th>
                    </tr>
                    </thead>
                    <tbody>
                   <tr data-ng-repeat="result in results | filter: filterResults | orderBy: ['source', 'title']" >
                      <td>
                          <b><a ng-bind-html="result.title | highlight:query"
                                href="{{result.href}}"
                                rel = "nofollow"
                                class="result"
                                title="{{result.title | mailTrim">{{result.title}}</a></b><br/>
                        <p ng-bind-html="result.content | highlight:query" >{{result.content }}</p>
                      </td>
                      <td>{{result.source}}</td>
                    </tbody>
                </table>
                <br>
                <hr/>
            </div>
        </div>
    </div>
</div>

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
      </div>
      <img src="resources/images/rainbow-bar.png">
    </footer>
</body>
</html>
