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
    <link rel='stylesheet' href='resources\customcss\customcss.css'>  <!-- Custom stylesheet override -->

    <script src="webjars/angularjs/1.2.26/angular.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/services.js"></script>
    <script src="resources/js/directives.js"></script>
    <script src="resources/js/controllers.js"></script>
    <script src="resources/js/filters.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <script>
      $(function() {
        $( document ).tooltip();
      });
    </script>


</head>

<body data-ng-controller="SearchController">

<!-- Header -->

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="resources\images\rainbow-bar.png">
      <a class="navbar-brand" href="index.jsp">
        <img src="resources\images\guidewire-logo.png">
      </a>
    </div>

    <div class="container-fluid">

        <br>

        <div class="row">
            <div class="col-md-12">

                <br>

                <form data-ng-submit="doParallelSearch()">

                    <button id="searchbutton" type="submit" class="btn btn-primary" data-ng-disabled="!query || inflight || search.searchIn.length == 0"><span class="glyphicon glyphicon-search"></span> Search</button>

                    <input type="text" data-ng-model="query" placeholder="Search" data-auto-focus>

                    <a style="padding-right: 75px"></a> <!-- empty element for space -->

                    <label ng-repeat="searchLocation in searchLocations">
                        <input type="checkbox" checklist-model="search.searchIn" checklist-value="searchLocation">
                        {{searchLocation}}
                    </label>
                    <input class="btn btn-primary custom80" type="button" ng-click="checkAll()" value="All">
                    <input class="btn btn-primary custom80" type="button" ng-click="uncheckAll()" value="None">

                      <a href="https://guidewire.my.salesforce.com">
                         <img class="chatter" src="resources\images\chatter-logo.png">
                      </a>
                </form>

                <br>
            </div>
        </div>
  </div>
</nav>

<!-- End Header -->

    <!-- Gareths tabs -->
         <div class="tabs" ng-show="results.length != 0" style="clear:both;"> <!-- tabs will only display if there are results -->
             <ul class ="nav nav-tabs">
                <li>
                    <a href ng-click="activateTab()">All</a>
                </li>
                <li ng-repeat="searchLocation in searchLocations">
                    <a href ng-click="activateTab(searchLocation)">{{searchLocation}}</a>
                </li>
             </ul>
         </div>

    <div class="row">
        <div class="col-md-12">
            <span data-ng-show="!inflight && searched && results.length == 0">No results</span>  <!-- possibly call a function here? -->
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
                   <tr data-ng-repeat="result in results | filter: filterResults | orderBy: ['source', 'title']"   >
                      <td>
                          <b><a = "result.title"
                                rel = "nofollow"
                                href="{{result.href}}"
                                class="result"
                               title="{{result.title }}  {{result.content}}">{{result.title  | mailTrim : result.source }}</a></b><br/>
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
      </div>
      <img src="resources\images\rainbow-bar.png">
    </footer>
</body>
</html>
